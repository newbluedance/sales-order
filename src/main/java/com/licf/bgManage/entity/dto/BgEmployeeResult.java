package com.licf.bgManage.entity.dto;

import com.common.base.BaseResult;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.common.utils.RedisHelper;
import com.licf.bgManage.entity.BgDepartment;
import com.licf.bgManage.entity.PubRole;
import com.licf.bgManage.enums.PermitEnum;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;

/**
 *
 * @author lichunfeng
 * @date 2020-08-24 18:34:54
 */
@Data
public class BgEmployeeResult extends BaseResult {

    private static final long serialVersionUID = 52843144704117635L;

    /** ID主键自增 */
    private Integer id;

    /** 姓名 */
    private String employeeName;

    /** 手机号 */
    private String phone;

    /** 账号 */
    private String account;

    /** 密码 */
//    private String password;

    /** 所属部门id */
    private Integer departmentId;

    /** 职位 */
    private String position;

    /** 角色id */
    private Integer[] roleIds;

    /** 状态 */
    private String status;

    /** 注释 */
    private String comments;

   private String token;

    public String getDepartmentName() {
        if (departmentId != null) {
            BgDepartment dept = RedisHelper.getDept(departmentId);
            if (dept != null)
                return dept.getDepartmentName();
        }
        return null;
    }

    public PermitEnum[] getPermits() {

        if (ArrayUtils.isNotEmpty(roleIds)) {
            List<PubRole> roles = Arrays.stream(roleIds).map(t -> RedisHelper.getRole(t)).collect(Collectors.toList());
            Set<Integer> permitIdSet = new HashSet<>();
            for (PubRole role : roles) {
                if (ArrayUtils.isNotEmpty(role.getPermitIds())) {
                    for (Integer permitId : role.getPermitIds()) {
                        permitIdSet.add(permitId);
                    }
                }
            }
            List<Integer> permitIds = permitIdSet.stream().sorted().collect(Collectors.toList());

            PermitEnum[] permitEnums = new PermitEnum[permitIds.size()];
            for (int i = 0; i < permitIds.size(); i++) {
                permitEnums[i] = PermitEnum.values()[permitIds.get(i)];
            }
            return permitEnums;

        }

        return new PermitEnum[0];
    }

}