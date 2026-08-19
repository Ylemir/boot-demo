package me.boot.base.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.boot.base.context.SpringContextHolder;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * i18 message
 *
 * @since 2022/10/07
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class I18nUtils {

    private static final MessageSource messageSource = SpringContextHolder.getBean(
        MessageSource.class);

    public static String message(String code) {
        return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
    }

    /**
     * 获取国际化翻译值
     *
     * @param code 消息键
     * @param args 参数
     * @return 国际化翻译值
     */
    public static String message(String code, Object[] args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }

    public static String message(String code, String defaultMessage, Object... args) {
        return messageSource.getMessage(code, args, defaultMessage,
            LocaleContextHolder.getLocale());
    }
}
