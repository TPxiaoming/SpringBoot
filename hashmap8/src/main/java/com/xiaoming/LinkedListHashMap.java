package com.xiaoming;

import java.util.LinkedList;

/**
 * @author xiaoming
 * @Date 2019/11/25
 */
public class LinkedListHashMap<K, V> implements XiaomingMap<K, V>  {

    private LinkedList<Node>[] tables = new LinkedList[100];

    @Override
    public int size() {
        return 0;
    }

    @Override
    public V put(K key, V value) {
        Node newNode = new Node(key, value);
        //计算key存放的下标位置
        int index = key.hashCode() % tables.length;
        LinkedList<Node> linkedList =  tables[index];
        if (linkedList == null){
            //说明没有产生冲突
            linkedList = new LinkedList<>();
            linkedList.add(newNode);
            tables[index] = linkedList;
        } else {
            for (Node node : linkedList) {
                if (key.equals(node.getKey())){
                    node.v = value;
                } else {
                    linkedList.add(newNode);
                    return value;
                }
            }
        }
        return value;
    }

    @Override
    public V get(K key) {
        //计算key查询node
        int index = key.hashCode() % tables.length;
        LinkedList<Node> nodes = tables[index];
        for (Node node : nodes) {
            if (key.equals(node.getKey())){
                return node.getValue();
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
        LinkedListHashMap<Object, String> hashMap = new LinkedListHashMap<>();
        hashMap.put("a", "xiaoming");
        hashMap.put(new Integer(97), "xiaohei");
        System.out.println(hashMap.get("a"));
        //该方法根据key查询时间复杂度是1
        //hashcode 与 equals
        //比较两个对象 如果 hashcode 相等 但是对象值不一定相等
        //比较两个对象 如果 equals 相等，那么对象值一定相等
        System.out.println("a".hashCode());
        System.out.println(new Integer(97).hashCode());
        System.out.println("97".hashCode());
    }
}
