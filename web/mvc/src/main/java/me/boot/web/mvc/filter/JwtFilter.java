package me.boot.web.mvc.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.boot.base.context.BootContext;
import me.boot.base.context.BootContextHolder;
import me.boot.jwt.service.JwtService;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * JwtInterceptor
 *
 * @since 2024/03/17
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
        @NotNull HttpServletResponse response,
        @NotNull FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.isNotBlank(token)) {
            String jwt = StringUtils.removeStart(token, "Bearer ");
            Map<String, Object> payload = jwtService.verify(jwt);
            BootContext context = BootContextHolder.getContext();
            context.setProperties(payload);
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        BootContextHolder.clearContext();
    }
}
