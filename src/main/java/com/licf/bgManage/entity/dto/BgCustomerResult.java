package com.licf.bgManage.entity.dto;

import com.common.base.BaseResult;
import com.common.utils.RedisHelper;
import com.common.view.Title;
import com.licf.bgManage.entity.BgDepartment;
import com.licf.bgManage.entity.BgEmployee;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author lichunfeng
 * @date 2020-08-24 18:34:54
 */
@Data
public class BgCustomerResult extends BaseResult {

    private static final long serialVersionUID = 28212764319486588L;

    @Title("ID")
    private Integer id;

    @Title("客户名称")
    private String customerName;

    @Title("手机号")
    private String phone;

    //收货人省份
    private String province;
    //收货人城市
    private String city;
    //收货人区县
    private String county;

    @Title("地址")
    private String address;

    @Title("所属部门id")
    private Integer departmentId;

    @Title("业务员")
    private Integer salesman;

    @Title("注释")
    private String comments;

    @Title("删除标志")
    private Integer deleted;

    @Title("创建时间")
    private LocalDateTime createTime;

    @Title("创建人")
    private String createdBy;

    @Title("更新时间")
    private LocalDateTime updateTime;

    @Title("更新人")
    private String updatedBy;

    private String departmentName;

    public String getDepartmentName() {
        if (departmentId != null) {
            BgDepartment dept = RedisHelper.getDept(departmentId);
            if (dept != null) {
                return dept.getDepartmentName();
            }
        }
        return null;
    }

    public String getSalesmanName() {
        if (salesman != null) {
            BgEmployee employee = RedisHelper.getEmployee(salesman);
            if (employee != null) {
                return employee.getEmployeeName();
            }
        }
        return null;
    }
}