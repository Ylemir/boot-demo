package me.boot.data.redisson.config;

import java.time.Duration;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 缓存配置属性，对应 spring.cache 下的配置项
 *
 * @since 2026/08/19
 **/
@Data
@ConfigurationProperties(prefix = "spring.cache")
public class CacheProperties {

    /**
     * Redis缓存配置
     */
    private Redis redis = new Redis();

    /**
     * Redis缓存配置
     */
    @Data
    public static class Redis {

        /**
         * 缓存过期时间，默认null表示不过期
         */
        private Duration timeToLive;

        /**
         * 缓存键前缀，默认null表示使用缓存名作为前缀
         */
        private String keyPrefix;

        /**
         * 是否缓存空值，默认true
         */
        private boolean cacheNullValues = true;

        /**
         * 是否使用键前缀，默认true
         */
        private boolean useKeyPrefix = true;

    }

}
