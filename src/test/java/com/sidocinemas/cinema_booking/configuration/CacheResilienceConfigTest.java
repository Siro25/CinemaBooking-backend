package com.sidocinemas.cinema_booking.configuration;

import org.junit.jupiter.api.Test;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.RedisConnectionFailureException;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class CacheResilienceConfigTest {
    @Test
    void catalogueStillLoadsWhenRedisReadAndWriteFail() {
        try (var context = new AnnotationConfigApplicationContext(TestConfig.class)) {
            var cache = context.getBean(Cache.class);
            var failure = new RedisConnectionFailureException("Redis unavailable");
            when(cache.get("all")).thenThrow(failure);
            doThrow(failure).when(cache).put("all", "database movies");

            assertThat(context.getBean(Catalogue.class).movies()).isEqualTo("database movies");
            verify(cache).get("all");
            verify(cache).put("all", "database movies");
        }
    }

    @Test
    void invalidationFailuresRemainVisible() {
        var handler = new CacheResilienceConfig().errorHandler();
        var cache = mock(Cache.class);
        var failure = new RedisConnectionFailureException("Redis unavailable");
        assertThatThrownBy(() -> handler.handleCacheEvictError(failure, cache, "all")).isSameAs(failure);
        assertThatThrownBy(() -> handler.handleCacheClearError(failure, cache)).isSameAs(failure);
    }

    @Configuration
    @EnableCaching
    @Import(CacheResilienceConfig.class)
    static class TestConfig {
        @Bean Cache cache() {
            var cache = mock(Cache.class);
            when(cache.getName()).thenReturn("movies");
            return cache;
        }
        @Bean CacheManager cacheManager(Cache cache) {
            var manager = mock(CacheManager.class);
            when(manager.getCache("movies")).thenReturn(cache);
            return manager;
        }
        @Bean Catalogue catalogue() { return new Catalogue(); }
    }

    static class Catalogue {
        @Cacheable(value = "movies", key = "'all'")
        public String movies() { return "database movies"; }
    }
}
