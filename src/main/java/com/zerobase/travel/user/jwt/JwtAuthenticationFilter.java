package com.zerobase.travel.user.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer";


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = this.resolveTokenFromRequest(request);

        log.info("TOKEN : " + token);
        if (StringUtils.hasText(token) && this.jwtTokenProvider.validateToken(token)) {
            log.info("ValidToken : " + jwtTokenProvider.validateToken(token));
            // 토큰 유효성 검증
            Authentication authentication = this.jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String resolveTokenFromRequest(HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("Raw Token: " + token);
        if (!ObjectUtils.isEmpty(token) && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return null;
    }
}
