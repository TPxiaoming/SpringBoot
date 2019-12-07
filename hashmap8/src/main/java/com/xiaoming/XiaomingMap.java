package com.xiaoming;

/**
 * @author xiaoming
 * @Date 2019/11/25
 */
public interface XiaomingMap<K, V> {
    /**
     * 集合的大小，个数
     * @return
     */
    int size();

    /**
     * put 方法
     * @param key
     * @param value
     * @return
     */
    V put(K key, V value);

    /**
     * 查询
     * @param key
     * @return
     */
    V get(K key);

    interface Entry<K,V> {
        /**
         * 获取 key
         * @return
         */
        K getKey();

        /**
         * 获取 value
         * @return
         */
        V getValue();

    }
}
