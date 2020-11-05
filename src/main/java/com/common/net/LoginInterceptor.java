package com.common.net;

import com.common.BusinessException;
import com.common.authory.RequiredPermission;
import com.common.constants.CodeConstant;
import com.common.constants.SystemConstant;
import com.common.utils.JwtUtils;
import com.licf.bgManage.entity.BgEmployee;
import com.licf.bgManage.entity.dto.BgEmployeeResult;
import com.licf.bgManage.enums.PermitEnum;
import com.licf.bgManage.service.BgEmployeeService;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * 拦截未登录的请求
 *
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

        String redisKey = SystemConstant.LOGIN_USER_KEY_PREFIX.concat(loginUser.getAccount());
        BgEmployeeResult employeeResult = (BgEmployeeResult) redisTemplate.opsForValue().get(redisKey);
        if (employeeResult != null) {
            //redis失效时间60分钟
            redisTemplate.expire(redisKey, 3600000L,
                    TimeUnit.MILLISECONDS);
            request.setAttribute("loginUser", employeeResult);

            // 验证权限
            if (!this.hasPermission(handler, employeeResult)) {
                throw new BusinessException(CodeConstant.NO_PERMIT, "你没有权限进行此操作!");
            }

            return true;
        } else {
            throw new BusinessException(CodeConstant.NO_LOGIN, "请先登录!");
        }


    }

    /**
     * 是否有权限
     *
     * @param handler
     * @return
     */
    private boolean hasPermission(Object handler, BgEmployeeResult employeeResult) {

        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            String name = handlerMethod.getBeanType().getName();
            if (name.contains("BasicErrorController")) {
                return true;
            }

            // 获取方法上的注解
            RequiredPermission requiredPermission = handlerMethod.getMethod().getAnnotation(RequiredPermission.class);
            // 如果方法上的注解为空 则获取类的注解
            if (requiredPermission == null) {
                requiredPermission = handlerMethod.getMethod().getDeclaringClass().getAnnotation(RequiredPermission.class);
            }
            if (requiredPermission == null) {
                return false;
            }

            String rolesStr = requiredPermission.roles();
            // 如果角色有权限 直接返回true
            if (StringUtils.isNotBlank(rolesStr)) {
                String[] permitRoles = rolesStr.split(",");
                Integer[] roleIds = employeeResult.getRoleIds();
                for (Integer roleId : roleIds) {
                    if (ArrayUtils.contains(permitRoles, String.valueOf(roleId))) {
                        return true;
                    }
                }
            }

            // 获取用户权限
            PermitEnum permit = requiredPermission.permit();
            PermitEnum[] permits = employeeResult.getPermits();
            if (permit != null && ArrayUtils.isNotEmpty(permits)) {
                if (ArrayUtils.contains(permits, permit)) {
                    return true;
                }
            }
        }
        return false;
    }


}
