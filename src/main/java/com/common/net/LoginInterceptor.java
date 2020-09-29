package com.common.net;

import com.common.BusinessException;
import com.common.constants.CodeConstant;
import com.common.constants.SystemConstant;
import com.common.utils.JwtUtils;
import com.licf.bgManage.entity.BgEmployee;
import com.licf.bgManage.service.BgEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * 拦截未登录的请求
 * @author lichunfeng
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    BgEmployeeService employeeService;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object object) {
        try {
            String redisToken;
            // 从 request 中获取 Token 值
            String tokenStr = JwtUtils.getTokenFromRequest(request);

            BgEmployee loginUser = JwtUtils.getLoginUserFromToken(tokenStr);

            BgEmployee loginAdmin = (BgEmployee) redisTemplate.opsForValue().get(SystemConstant.LOGIN_USER_KEY_PREFIX.concat(loginUser.getAccount()));
            if (loginAdmin != null) {
                //redis失效时间20分钟
                String redisKey = SystemConstant.LOGIN_USER_KEY_PREFIX.concat(loginAdmin.getAccount());
                redisTemplate.expire(redisKey, 1200000L,
                        TimeUnit.MILLISECONDS);
                request.setAttribute("loginUser", loginAdmin);
                return true;
            } else {
                throw new BusinessException(CodeConstant.NO_LOGIN, "请先登录!");
            }

        } catch (Exception e) {
            throw new BusinessException(CodeConstant.NO_LOGIN, "请先登录!");
        }

    }


}
