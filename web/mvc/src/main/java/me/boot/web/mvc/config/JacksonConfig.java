package me.boot.web.mvc.config;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import me.boot.base.util.DateUtils;
import org.springframework.boot.jackson.autoconfigure.JsonMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.ext.javatime.deser.InstantDeserializer;
import tools.jackson.databind.ext.javatime.deser.LocalDateDeserializer;
import tools.jackson.databind.ext.javatime.deser.LocalDateTimeDeserializer;
import tools.jackson.databind.ext.javatime.deser.LocalTimeDeserializer;
import tools.jackson.databind.ext.javatime.ser.InstantSerializer;
import tools.jackson.databind.ext.javatime.ser.LocalDateSerializer;
import tools.jackson.databind.ext.javatime.ser.LocalDateTimeSerializer;
import tools.jackson.databind.ext.javatime.ser.LocalTimeSerializer;
import tools.jackson.databind.module.SimpleModule;

/**
 * 日期时间转换配置
 *
 * @since 2023/07/15
 **/
@Configuration
public class JacksonConfig {

    /**
     * 默认日期时间格式
     */
    private final String dateTimeFormat = DateUtils.DATE_TIME_FORMAT;
    /**
     * 默认日期格式 "yyyy-MM-dd"
     */
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
    /**
     * 默认时间格式 "HH:mm:ss"
     */
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_LOCAL_TIME;

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public JsonMapperBuilderCustomizer jacksonCustomizer() {
        return builder -> {
            builder.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

            final SimpleModule simpleModule = new SimpleModule();

            // 序列化-日期时间指定格式
            simpleModule.addSerializer(LocalDateTime.class,
                new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimeFormat)));
            simpleModule.addSerializer(LocalDate.class, new LocalDateSerializer(dateFormatter));
            simpleModule.addSerializer(LocalTime.class, new LocalTimeSerializer(timeFormatter));
            simpleModule.addSerializer(Instant.class, InstantSerializer.INSTANCE);

            // 反序列化-日期时间指定格式
            simpleModule.addDeserializer(LocalDateTime.class,
                new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(dateTimeFormat)));
            simpleModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(dateFormatter));
            simpleModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(timeFormatter));
            simpleModule.addDeserializer(Instant.class, InstantDeserializer.INSTANT);
            builder.addModule(simpleModule);
        };
    }
}

