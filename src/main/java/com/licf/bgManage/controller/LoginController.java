package com.licf.bgManage.controller;

import com.common.constants.CodeConstant;
import com.common.constants.SystemConstant;
import com.common.constants.YesNoConstant;
import com.common.net.RestResponse;
import com.common.utils.JwtUtils;
import com.licf.bgManage.entity.BgEmployee;
import com.licf.bgManage.entity.dto.BgEmployeeResult;
import com.licf.bgManage.entity.dto.LoginToken;
import com.licf.bgManage.mapper.BgEmployeeMapper;
import com.licf.bgManage.mapperstruct.BgEmployeeConverter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * @author lichunfeng
 */
@Slf4j
@RestController
@RequestMapping
public class LoginController {

    @Resource
    private BgEmployeeMapper employeeMapper;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @PostMapping("/system/login")
    @ResponseBody
    public RestResponse<BgEmployeeResult> login(@RequestBody LoginToken body) {

        String loginName = body.getLoginName();
        String password = body.getPassword();
        if (StringUtils.isBlank(loginName) || StringUtils.isBlank(password)) {
            return new RestResponse(CodeConstant.ERROR_PARAMETER, "用户名密码不能为空");
        }

        BgEmployee params = new BgEmployee();
        params.setAccount(loginName);
        BgEmployee loginBgEmployee = employeeMapper.selectOne(params);
        if (loginBgEmployee == null) {
            return new RestResponse(CodeConstant.ERROR_LOGIN, "用户不存在");
        }
        if (!password.equals(loginBgEmployee.getPassword()) && !DigestUtils.md5Hex(password).equals(loginBgEmployee.getPassword())) {
            return new RestResponse(CodeConstant.ERROR_LOGIN, "密码不正确");
        }
        if (YesNoConstant.YES.equals(loginBgEmployee.getDeleted())) {
            return new RestResponse(CodeConstant.ERROR_LOGIN, "用户已被删除");
        }

        String redisKey = SystemConstant.LOGIN_USER_KEY_PREFIX.concat(loginBgEmployee.getAccount());
        // 清除用户存储在缓存中的权限信息
        redisTemplate.delete(redisKey);

        //生成token
        String newToken = JwtUtils.sign(loginBgEmployee.getId(), loginBgEmployee.getAccount());

        BgEmployeeResult adminResult = BgEmployeeConverter.INSTANCE.entityToResult(
                loginBgEmployee);

        redisTemplate.opsForValue().set(redisKey, adminResult);
        //redis失效时间60分钟
        redisTemplate.expire(redisKey, 3600000L,
                TimeUnit.MILLISECONDS);


        adminResult.setToken(newToken);

        return RestResponse.success(adminResult);
    }


    @PostMapping("/system/logout")
    @ResponseBody
    public RestResponse logout(HttpServletRequest request) {
        // 从 request 中获取 Token 值
        String tokenStr = JwtUtils.getTokenFromRequest(request);

        BgEmployee loginUser = JwtUtils.getLoginUserFromToken(tokenStr);

        // 清除用户存储在缓存中的权限信息
        redisTemplate.delete(SystemConstant.LOGIN_USER_KEY_PREFIX.concat(loginUser.getAccount()));

        return RestResponse.success("注销成功");
    }


}
