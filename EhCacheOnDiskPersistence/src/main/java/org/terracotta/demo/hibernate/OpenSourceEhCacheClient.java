package org.terracotta.demo.hibernate;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * Created by vinay on 8/12/15.
 */
public class OpenSourceEhCacheClient {
    public static void main(String[] args){
        //initialize the cache manager
        CacheManager cacheManager = CacheManager.getInstance();
        Cache cache = cacheManager.getCache("cacheOne");
        
        //try reading an entry from the cache ( should be null the first time we run)
        System.out.println("Reading from cache "+cache.get("a"));
        cache.put(new Element("a","b"));
        System.out.println("Writing to cache "+cache.get("a"));
        
        //cleanly shutdown the cache & manager
        cache.flush();
        cacheManager.shutdown();
        
    }
}
