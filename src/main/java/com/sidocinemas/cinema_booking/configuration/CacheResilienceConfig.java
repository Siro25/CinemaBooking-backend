package com.sidocinemas.cinema_booking.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.SimpleCacheErrorHandler;
import org.springframework.context.annotation.Configuration;

/** Redis catalogue caches are optional; seat holds still require Redis. */
@Slf4j
@Configuration
public class CacheResilienceConfig implements CachingConfigurer {
    @Override
    public CacheErrorHandler errorHandler() {
        return new SimpleCacheErrorHandler() {
            @Override
            public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
                log.warn("Cache {} read failed ({}); loading from database", cache.getName(),
                        exception.getClass().getSimpleName());
            }

            @Override
            public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
                log.warn("Cache {} write failed ({}); returning database result", cache.getName(),
                        exception.getClass().getSimpleName());
            }
            // Keep eviction/clear failures visible to avoid silently leaving stale data.
        };
    }
}
