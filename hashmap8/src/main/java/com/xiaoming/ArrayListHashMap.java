package com.xiaoming;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaoming
 * @Date 2019/11/25
 */
public class ArrayListHashMap<K, V> implements XiaomingMap<K, V> {
    private int size;
    private List<Node> nodes = new ArrayList<>();

    @Override
    public int size() {
        return size;
    }

    @Override
    public V put(K key, V value) {
        Node node = getNode(key);
        if (node != null){
            node.v = value;
            return node.getValue();
        }
        //如果 key 不存在直接添加
        Node newNode = new Node(key, value);
        nodes.add(newNode);
        size ++;
        return value;
    }

    @Override
    public V get(K key) {
        return getNode(key).getValue();
    }

    private Node getNode(K key){
        for (Node node : nodes) {
            //时间复杂度为 O(n) 从头查询尾部
            //使用 index 查询 O(1)
            if (key.equals(node.getKey())){
                return node;
            }
        }
        return null;
    }

    class Node implements Entry<K, V>{
        private K k;
        private V v;

        public Node(K k, V v) {
            this.k = k;
            this.v = v;
        }

        @Override
        public K getKey() {
            return k;
        }

        @Override
        public V getValue() {
            return v;
        }
    }

    public static void main(String[] args) {
        ArrayListHashMap<String, String> hashMap = new ArrayListHashMap<>();
        hashMap.put("xiao", "ming");
        System.out.println(hashMap.get("xiao"));
    }
}
