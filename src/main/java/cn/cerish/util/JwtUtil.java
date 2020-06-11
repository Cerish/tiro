package cn.cerish.util;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Component
public class JwtUtil {
    // 秘钥
    @Value("${jwt.secret}")
    private String secret;

    // 有效期 单位：s
    @Value("${jwt.expiration}")
    private long expiration;
    // 签名算法
    private final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

    // 生成token的失效时间 Date
    private Date getExpirationTime() {
        return new Date(System.currentTimeMillis() + this.expiration * 1000);
    }
    // 生成 token
    public String generateToken(Map<String, Object> claims) {
        Date createdTime = new Date(System.currentTimeMillis()); // 当前时间
        Date expirationTime = this.getExpirationTime(); // 失效时间

        return Jwts.builder()
                .setClaims(claims) // 字段 map
                .setIssuedAt(createdTime) // 签发时间
                .setNotBefore(createdTime) // 生效时间
                .setExpiration(expirationTime) // 失效时间
                // params1: HS256 算法， params2: 签名 key 值
                .signWith(SIGNATURE_ALGORITHM, this.secret)
                .compact(); // 生成 token 字符串
    }

    // 从 token 中获取 Claims
    public Claims getClaimsFromToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(this.secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | IllegalArgumentException e) {
            System.out.println("token 解析错误");
            throw new IllegalArgumentException("Token invalided.");
        }
    }
    // 获取 token 的过期时间
    public Date getExpirationDateFromToken(String token) {
        return getClaimsFromToken(token)
                .getExpiration();
    }
    // 判断 token 是否过期
    public Boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
    // 判断 token 是否非法
    public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

}
