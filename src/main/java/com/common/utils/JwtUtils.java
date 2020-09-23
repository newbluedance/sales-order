package com.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.licf.bgManage.entity.BgEmployee;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author lichufeng
 **/
@Slf4j
public class JwtUtils {

    private static  final String KEY_JWT_SECRET="47008690a35c11e8aa35288023a06360";
    /**
     * 校验token是否正确
     *
     * @param token     登录令牌
     * @param userId    用户ID
     * @param loginName 用户登录名
     * @param name      用户姓名
     * @return 是否正确
     */
    public static boolean verify(String token, int userId, String loginName, String name) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(KEY_JWT_SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("userId", userId)
                    .withClaim("loginName", loginName)
                    .withClaim("name", name)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception e) {
            log.error("", e);
            return false;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @param token 登录令牌
     * @return token中包含的用户名
     */
    public static String getLoginName(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("loginName").asString();
        } catch (JWTDecodeException e) {
            log.error("", e);
            return null;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @param token 登录令牌
     * @return token中包含的用户名
     */
    public static DecodedJWT getDecodeInfo(String token) {
        try {
            return JWT.decode(token);
        } catch (JWTDecodeException e) {
            log.error("", e);
            return null;
        }
    }

    /**
     * 生成签名
     *
     * @param userId    用户ID
     * @param account 用户登录名
     * @return 加密的token
     */
    public static String sign(int userId, String account) {

            Date date = new Date(System.currentTimeMillis() + 900000L);
            Algorithm algorithm = Algorithm.HMAC256(KEY_JWT_SECRET);
            // 附带username信息
            return JWT.create()
                    .withClaim("userId", userId)
                    .withClaim("account", account)
                    .withExpiresAt(date)
                    .sign(algorithm);

    }

    /**
     * 从 request 中获取登录用户信息
     * @param request
     * @return
     */
    public static BgEmployee getLoginUserFromRequestToken(HttpServletRequest request) {
        return getLoginUserFromToken(getTokenFromRequest(request));
    }

    /**
     * 根据 token 字符串获取登录用户信息
     * @param token
     * @return
     */
    public static BgEmployee getLoginUserFromToken(String token) {
        DecodedJWT jwtInfo = getDecodeInfo(token);
        return getLoginUserFromToken(jwtInfo);
    }


    /**
     * 根据 jwt 对象获取登录用户信息
     * @param jwtInfo
     * @return
     */
    public static BgEmployee getLoginUserFromToken(DecodedJWT jwtInfo) {
        if (jwtInfo != null) {
            BgEmployee loginUser = new BgEmployee();
            loginUser.setId(jwtInfo.getClaim("userId").asInt());
            loginUser.setAccount(jwtInfo.getClaim("account").asString());
            return loginUser;
        }
        return null;
    }

    /**
     * 从 request 中获取 jwt token
     * @param request
     * @return
     */
    public static String getTokenFromRequest(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null && cookies.length > 0) {
                for (Cookie cookie : cookies) {
                    if (StringUtils.equalsIgnoreCase("token", cookie.getName())) {
                        token = cookie.getValue();
                    }
                }
            }
        }
        return token;
    }
}
