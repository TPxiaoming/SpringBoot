package com.xiaoming.service;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Component;

/**
 * EhCacheUtils
 *
 * @blame Android Team
 */
@Component
public class EhCacheUtils {


	@Autowired
	private EhCacheCacheManager ehCacheCacheManager;

	/**
	 * 添加本地缓存 (相同的key 会直接覆盖)
	 * @param cacheName
	 * @param key
	 * @param value
	 */
	public void put(String cacheName, String key, Object value) {
		Cache cache = ehCacheCacheManager.getCacheManager().getCache(cacheName);
		Element element = new Element(key, value);
		cache.put(element);
	}

	/**
	 * 获取本地缓存
	 * @param cacheName
	 * @param key
	 * @return
	 */
	public Object get(String cacheName, String key) {
		Cache cache = ehCacheCacheManager.getCacheManager().getCache(cacheName);
		Element element = cache.get(key);
		return element == null ? null : element.getObjectValue();
	}

	public void remove(String cacheName, String key) {
		Cache cache = ehCacheCacheManager.getCacheManager().getCache(cacheName);
		cache.remove(key);
	}

}
