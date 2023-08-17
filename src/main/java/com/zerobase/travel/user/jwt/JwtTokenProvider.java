package com.zerobase.travel.user.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Date;


@Slf4j
@RequiredArgsConstructor
public class JwtTokenProvider implements InitializingBean{
    private Key key;
    private long accessTokenValidityInMilliseconds = 1800 * 1000L;
    private long refreshTokenValidityInMilliseconds = 1209600 * 1000L;
    private String secretKey = "gjg8ha2slh5ge8sa9f9fa3sdf8ggj48g48has40g04ea2ks0";
    private final JwtUserService jwtUserService;


    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }


    // accessToken 발행
    public String createAccessToken(String userEmail) {
        Claims claims = Jwts.claims().setSubject(userEmail);
        claims.put("role", "ROLE_USER");

        Date now = new Date();
        Date validity = new Date(now.getTime() + accessTokenValidityInMilliseconds);

        return Jwts.builder()
                .setSubject(userEmail)
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
    // refreshToken 발행
    public String createRefreshToken(String userEmail) {
        Claims claims = Jwts.claims().setSubject(userEmail);

        Date now = new Date();
        Date validity = new Date(now.getTime() + refreshTokenValidityInMilliseconds);

        return Jwts.builder()
                .setSubject(userEmail)
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
    // Token 에 담겨있는 정보로 Authentication 객체를 반환
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = this.jwtUserService.loadUserByUsername(this.getUserEmail(token));

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
    // 유저 이메일 추출
    public String getUserEmail(String token) {
        return this.parseClaims(token).getSubject();
    }

    //token 파싱 후 발생하는 예외처리 : 문제 발생 시 false 반환
    public boolean validateToken(String token) {
        if (!StringUtils.hasText(token)) return false;

        var claims = this.parseClaims(token);
        return !claims.getExpiration().before(new Date());
    }

    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(accessToken)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}
