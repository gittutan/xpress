package com.wuyuncheng.xpress.util;

import com.wuyuncheng.xpress.config.XPressProperties;
import com.wuyuncheng.xpress.model.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.util.Date;

@Component
public class JWTUtils {

    /**
     * 静态方法不能直接使用 @Autowired 注入
     */
    private static XPressProperties properties;

    @Autowired
    private XPressProperties propertiesTemp;

    @PostConstruct
    public void init() {
        JWTUtils.properties = propertiesTemp;
    }

    private JWTUtils() {
    }

    public static String generateToken(User user) {
        long jwtTimeoutSeconds = Duration.ofMinutes(properties.getJwtTimeout()).getSeconds();
        Date jwtTimeoutDate = DateUtils.toDate(DateUtils.nowUnix() + jwtTimeoutSeconds);
        return Jwts.builder()
                .setId(String.valueOf(user.getUserId())) // token ID
                .setSubject(user.getUsername()) // 主题为 username
                .setExpiration(jwtTimeoutDate) // token 过期时间
                .signWith(SignatureAlgorithm.HS256, properties.getJwtSecret())
                .compact();
    }

    public static boolean validateToken(String token) {
        if (null == token) {
            return false;
        }
        Claims claimsBody;
        // 验证 token 是否合法
        try {
            claimsBody = Jwts.parser()
                    .setSigningKey(properties.getJwtSecret())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException e) {
            return false;
        }
        // 判断 token 是否过期
        if (claimsBody.getExpiration().before(new Date())) {
            return false;
        }
        return true;
    }

    public static Integer getCurrentUserId() {
        HttpServletRequest request = ServletUtils.getCurrentRequest().get();
        String token = request.getHeader(properties.getJwtHeader());
        String userId = Jwts.parser()
                .setSigningKey(properties.getJwtSecret())
                .parseClaimsJws(token)
                .getBody()
                .getId();
        return Integer.valueOf(userId);
    }

}
