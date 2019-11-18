package com.xiaoming;

import org.I0Itec.zkclient.ZkClient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ServerScoekt服务端
 *
 * @blame Android Team
 */
public class ZkServerScoekt implements Runnable {
	private static int port = 18081;

	public static void main(String[] args) throws IOException {
		ZkServerScoekt server = new ZkServerScoekt(port);
		Thread thread = new Thread(server);
		thread.start();
	}

	public ZkServerScoekt(int port) {
		ZkServerScoekt.port = port;
	}

	/**
	 * 注册服务
	 * 1.建立 Zookeeper 连接
	 * 2.创建父节点
	 * 3.创建子节点
	 */
	public void regServer(){
		//1.建立 Zookeeper 连接
		ZkClient zkClient = new ZkClient("127.0.0.1:2181", 5000, 10000);
		//2.创建父节点
		String root = "/xiaoming_service";
		if (!zkClient.exists(root)) {
			//如果父节点不存在，直接创建父节点
			zkClient.createPersistent(root);
		}
		//3.创建子节点
		String nodeName =root + "/service_" + port;
		String nodeValue = "127.0.0.1:" + port;
		if (!zkClient.exists(nodeName)){
			zkClient.delete(nodeName);
		}
		//创建子节点（临时），可以用于Dubbo服务发现
		zkClient.createEphemeral(nodeName, nodeValue);
		System.out.println("服务注册成功");
	}

	@Override
	public void run() {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(port);
			regServer();
			System.out.println("Server start port:" + port);
			Socket socket = null;
			while (true) {
				socket = serverSocket.accept();
				new Thread(new ServerHandler(socket)).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (serverSocket != null) {
					serverSocket.close();
				}
			} catch (Exception e2) {

			}
		}
	}

}
