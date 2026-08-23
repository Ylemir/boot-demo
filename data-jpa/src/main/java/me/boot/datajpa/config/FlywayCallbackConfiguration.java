package me.boot.datajpa.config;

import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.api.callback.BaseCallback;
import org.flywaydb.core.api.callback.Context;
import org.flywaydb.core.api.callback.Event;
import org.springframework.stereotype.Component;

/**
 * Flyway Java 回调，在迁移的不同阶段执行自定义逻辑。
 *
 * @since 2026/08/23
 **/
@Slf4j
@Component
public class FlywayCallbackConfiguration extends BaseCallback {

    long startTime = 0;

    @Override
    public void handle(Event event, Context context) {
        switch (event) {
            case BEFORE_CONNECT:
                log.info("[Flyway] start connect to database by user: {}",
                    context.getConfiguration().getUser());
                break;
            case BEFORE_MIGRATE:
                startTime = System.currentTimeMillis();
                break;
            case AFTER_MIGRATE:
                long duration = System.currentTimeMillis() - startTime;
                log.info("迁移耗时: {} ms", duration);
                break;
            default:
                break;
        }
    }

}
