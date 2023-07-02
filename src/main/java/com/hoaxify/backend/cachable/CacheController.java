package com.hoaxify.backend.cachable;


import com.github.benmanes.caffeine.cache.Cache;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("cache")
@RequiredArgsConstructor
@Slf4j
public class CacheController {

  private final CacheManager cacheManager;
  @GetMapping("/{cacheName}")
  public CacheResponse getCacheNames(@PathVariable("cacheName") String cacheName) {
    CaffeineCache caffeineCache = (CaffeineCache) cacheManager.getCache(cacheName);
    Cache<Object, Object> nativeCache = caffeineCache.getNativeCache();
    List<CacheItem> cacheItems = new ArrayList<>();
    for (Map.Entry<Object, Object> entry : nativeCache.asMap().entrySet()) {
      cacheItems.add(CacheItem.builder()
          .name(entry.getKey().toString())
          .value(entry.getValue().toString())
        .build());
    }

    return CacheResponse.builder()
      .name(caffeineCache.getName())
      .cacheItems(cacheItems)
      .build();
  }
}
