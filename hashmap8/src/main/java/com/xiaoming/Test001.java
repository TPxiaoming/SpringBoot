package com.xiaoming;

import java.util.HashMap;
import java.util.Hashtable;

/**
 * hashMap 与 hashTable 区别：线程安全、key为空 jdk 1.7 与 1.8
 *
 * hashmap 线程不安全，效率比较高
 * hashtable 线程安全，效率比较低
 * 当多个线程同时共享一个变量做写的操作的时候可能会发生线程安全问题
 * 当真正发送线程安全问题的时候，使用什么集合？ConcurrentHashMap
 *
 * hashMap的key是否可以为空？可以
 *      如果key为空是否有hashcode值？没有
 *      如果key为空没有hashcode值存放什么位置，默认存放在 0 位置
 *      if(key == null) table[0]=key
 * hashtable 的key是否可以为空？不可以，会抛出空指针异常
 *      public synchronized V put(K key, V value) {
 *         // Make sure the value is not null
 *         if (value == null) {
 *             throw new NullPointerException();
 *         }
 *
 *  map集合是否可以存放key为对象？可以
 *
 *  使用hashmap特征：
 *      基于 key 和 value 的键值对，基于 key 查询 value
 *   手写 HashMap
 *      1.基于 ArrayList 实现   数组
 *      2.基于 linkedList 实现 单向链表
 *      3.基于数组+链表实现
 *      4.基于数组+链表+红黑树
 *
 *   key 和 value map接口时如果保证的？ enter
 *
 * @author xiaoming
 * @Date 2019/11/25
 */
public class Test001 {
    public static void main(String[] args) {
        HashMap<String, String> hashMap = new HashMap<>();
        Hashtable<String, String> hashtableashtable = new Hashtable<>();
    }
}
