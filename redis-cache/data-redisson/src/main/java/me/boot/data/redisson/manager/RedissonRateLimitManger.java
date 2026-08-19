package me.boot.data.redisson.manager;

import java.time.Duration;
import me.boot.base.manager.RateLimitManger;
import me.boot.base.property.RateLimitProperty;
import me.boot.data.redisson.util.RedissonUtils;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateType;

/**
 * RedissonRateLimitManger
 *
 * @since 2024/04/11
 **/
public class RedissonRateLimitManger implements RateLimitManger {

    @Override
    public boolean totalLimit(RateLimitProperty rateLimit) {
        RRateLimiter rateLimiter = RedissonUtils.getRateLimiter(rateLimit.getKey());
        rateLimiter.trySetRate(RateType.OVERALL, rateLimit.getQps(), Duration.ofSeconds(1));
        Duration timeout = rateLimit.getTimeout();
        return rateLimiter.tryAcquire(timeout);
    }

}
