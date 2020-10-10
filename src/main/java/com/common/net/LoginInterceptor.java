package com.common.net;

import com.common.BusinessException;
import com.common.authory.RequiredPermission;
import com.common.constants.CodeConstant;
import com.common.constants.SystemConstant;
import com.common.utils.JwtUtils;
import com.licf.bgManage.entity.BgEmployee;
import com.licf.bgManage.service.BgEmployeeService;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.method.HandlerMethod;
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
                             Object handler) {
        // 从 request 中获取 Token 值
        String tokenStr = JwtUtils.getTokenFromRequest(request);

        BgEmployee loginUser = JwtUtils.getLoginUserFromToken(tokenStr);

        BgEmployee loginAdmin = (BgEmployee) redisTemplate.opsForValue().get(SystemConstant.LOGIN_USER_KEY_PREFIX.concat(loginUser.getAccount()));
        if (loginAdmin != null) {
            //redis失效时间60分钟
            String redisKey = SystemConstant.LOGIN_USER_KEY_PREFIX.concat(loginAdmin.getAccount());
            redisTemplate.expire(redisKey, 3600000L,
                    TimeUnit.MILLISECONDS);
            request.setAttribute("loginUser", loginAdmin);

            // 验证权限
            if (!this.hasPermission(handler, loginAdmin)) {
                throw new BusinessException(CodeConstant.NO_PERMIT, "你无法进行此操作!");
            }

            return true;
        } else {
            throw new BusinessException(CodeConstant.NO_LOGIN, "请先登录!");
        }


    }

    /**
     * 是否有权限
     * @param handler
     * @return
     */
    private boolean hasPermission(Object handler, BgEmployee loginAdmin) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 获取方法上的注解
            RequiredPermission requiredPermission = handlerMethod.getMethod().getAnnotation(RequiredPermission.class);
            // 如果方法上的注解为空 则获取类的注解
            if (requiredPermission == null) {
                requiredPermission = handlerMethod.getMethod().getDeclaringClass().getAnnotation(RequiredPermission.class);
            }
            if (requiredPermission == null) {
                return true;
            }

            String rolesStr = requiredPermission.roles();
            if (StringUtils.isNotBlank(rolesStr)) {
                String[] permitRoles = rolesStr.split(",");
                Integer[] roleIds = loginAdmin.getRoleIds();

                for (Integer roleId : roleIds) {
                    if (ArrayUtils.contains(permitRoles, String.valueOf(roleId))) {
                        return true;
                    }
                }

            }
        }
        return false;
    }


}
