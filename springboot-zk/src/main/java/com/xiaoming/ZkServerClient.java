package com.xiaoming;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * ZkServerClient
 *
 * @blame Android Team
 */
public class ZkServerClient {
	public static List<String> listServer = new ArrayList<String>();

	public static void main(String[] args) {
		initServer();
		ZkServerClient 	client= new ZkServerClient();
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			String name;
			try {
				name = console.readLine();
				if ("exit".equals(name)) {
					System.exit(0);
				}
				client.send(name);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 注册所有server
	 */
	public static void initServer() {
		listServer.clear();
//		listServer.add("127.0.0.1:18080");
		//1.建立 Zookeeper 连接
		ZkClient zkClient = new ZkClient("127.0.0.1:2181", 5000, 10000);
		//2.读取父节点
		String root = "/xiaoming_service";
		List<String> children = zkClient.getChildren(root);
		for (String child : children) {
			String path = root + "/" + child;
			String value = zkClient.readData(path);
			listServer.add(value);
		}
		System.out.println("服务发现："+listServer.toString());
		serverCount = listServer.size();

		//使用 zookeeper 事件监听，如果服务器发生宕机情况下，重新读取新的节点
		zkClient.subscribeChildChanges(root, new IZkChildListener() {
			@Override
			public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
				System.out.println("有服务器宕机了，重新获取新服务器的节点信息：" + listServer.toString());
				listServer.clear();
				for (String child : currentChilds) {
					String path = root + "/" + child;
					String value = zkClient.readData(path);
					listServer.add(value);
				}
				System.out.println("服务发现："+listServer.toString());
				serverCount = listServer.size();
			}
		});
	}

	/**
	 * 请求总数
	 */
	private static int reqCount = 1;
	/**
	 * 服务器个数
	 */
	private static int serverCount = 0;

	// 获取当前server信息
	public static String getServer() {
		//本地负载均衡轮询算法
		String serverName = listServer.get(reqCount % serverCount);
		System.out.println("客户端请求次数：" + reqCount + "，对应的请求服务器：" + serverName);
		++reqCount;
		return serverName;
	}
	
	public void send(String name) {

		String server = ZkServerClient.getServer();
		String[] cfg = server.split(":");

		Socket socket = null;
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			socket = new Socket(cfg[0], Integer.parseInt(cfg[1]));
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);

			out.println(name);
			while (true) {
				String resp = in.readLine();
				if (resp == null) {
					break;
				} else if (resp.length() > 0) {
					System.out.println("Receive : " + resp);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
