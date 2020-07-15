package cn.cerish.util;

import cn.cerish.common.exception.JwtException;
import cn.cerish.entity.User;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtUtil {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private HttpServletResponse response;

    private static final String CLAIM_KEY_USER_ID = "user_id";
    private static final String CLAIM_KEY_USER_NAME = "user_name";
    // 秘钥
    private String secret;

    // 有效期 单位：s
    private long expiration;
    // 存储在redis 的有效期 单位：s
    private long redis_expiration;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public long getExpiration() {
        return expiration;
    }

    public void setExpiration(long expiration) {
        this.expiration = expiration;
    }

    public long getRedis_expiration() {
        return redis_expiration;
    }

    public void setRedis_expiration(long redis_expiration) {
        this.redis_expiration = redis_expiration;
    }

    // 签名算法
    private final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

    // 生成token的失效时间 Date
    private Date getExpirationTime() {
        return new Date(System.currentTimeMillis() + this.expiration * 1000);
    }

    // 生成 token
    public String generateToken(User user) {
        Map<String, Object> claims = generateClaims(user);
        // claims.put(CLAIM_KEY_AUTHORITIES, authoritiesToArray(hpUser.getAuthorities()).get(0));

        return generateAccessToken(user.getUsername(), claims);
    }

    // 把 id 放进要存储的 claims
    private Map<String, Object> generateClaims(User user) {
        Map<String, Object> claims = new HashMap<>(16);
        claims.put(CLAIM_KEY_USER_ID, user.getId());
        claims.put(CLAIM_KEY_USER_NAME, user.getUsername());
        return claims;
    }
    private String generateAccessToken(String issuer, Map<String, Object> claims) {
        return generateToken(issuer, claims, expiration);
    }

    // 真正生成 token 的方法

    // 生成 token
    private String generateToken(String issuer,
                                 Map<String, Object> claims,
                                 long expiration) {
        Date createdTime = new Date(System.currentTimeMillis()); // 当前时间
        Date expirationTime = this.getExpirationTime(); // 失效时间

        redisUtil.set(issuer, claims, redis_expiration);
        String token = Jwts.builder()
                .setSubject("subject")
                .setClaims(claims) // 字段 map
                .setIssuer(issuer)
                .setIssuedAt(createdTime) // 签发时间
                .setNotBefore(createdTime) // 生效时间
                .setExpiration(expirationTime) // 失效时间
                // params1: HS256 算法， params2: 签名 key 值
                .signWith(SIGNATURE_ALGORITHM, this.secret)
                .compact(); // 生成 token 字符串
        response.setHeader("x-authorization", token);
        return token;
    }

    // 从 token 中获取 Claims
    public Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(this.secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            claims = e.getClaims();

            long expire = redisUtil.getExpire(claims.getIssuer());

            if(expire < -1) {
                throw new JwtException(401, "token 已过期，请重新登录！");
            }else {
                generateAccessToken(claims.getIssuer(), claims);
            }
        } catch ( UnsupportedJwtException e) {
            throw new JwtException(401, "token信息不能被解析，请重新登录");
        } catch ( MalformedJwtException e) {
            throw new JwtException(401, "token格式错误，请重新登录！");
        } catch (IllegalArgumentException e) {
            throw new JwtException(401, "token为空，请重新登录！");
        }
        return claims;
    }
    // 获取 token 的过期时间
    public Date getExpirationDateFromToken(String token) {
        return getClaimsFromToken(token)
                .getExpiration();
    }
    // 判断 token 是否过期: true 已过期， false 未过期
    public Boolean isTokenExpired(String token) {
        try {
            final Date expiration = getExpirationDateFromToken(token);
            return expiration.before(new Date());
        } catch (ExpiredJwtException expiredJwtException) {
            return true;
        }
    }
    // 判断 token 是否非法
    public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

}
