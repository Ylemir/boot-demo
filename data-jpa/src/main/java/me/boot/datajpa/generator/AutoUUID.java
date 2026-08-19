package me.boot.datajpa.generator;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.hibernate.annotations.IdGeneratorType;

/**
 * 自动生成UUID主键注解
 * <p>
 * 如果主键已有值则保留原值，否则自动生成32位UUID
 * </p>
 *
 * @since 2026/08/19
 */
@IdGeneratorType(AutoUUIDGenerator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.FIELD, java.lang.annotation.ElementType.METHOD})
public @interface AutoUUID {
}
