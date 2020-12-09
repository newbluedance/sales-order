package com.licf.app.entity.dto;

import com.common.base.BaseResult;
import com.common.utils.RedisHelper;
import com.common.view.Title;
import com.licf.bgManage.entity.BgEmployee;
import com.licf.bgManage.enums.EOrderStatus;
import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author lichunfeng
 * @date 2020-08-24 18:34:54
 */
@Data
public class UsrOrderResult extends BaseResult {

    private static final long serialVersionUID = 84260164317125111L;

    @Title("ID")
    private Integer id;

    @Title("订单编号")
    private String orderNo;

    private List<UsrOrderGoodsShot> goods;

    @Title("客户名称")
    private String customerName;

    @Title("手机号")
    private String phone;

    @Title("所属业务员")
    private Integer salesman;

    @Title("所属部门")
    private Integer departmentId;

    @Title("订单金额")
    private BigDecimal orderAmount;

    @Title("实际金额")
    private BigDecimal actualAmount;

    private Double weight;

    @Title("支付方式")
    private String payMode;

    @Title("收货人")
    private String receiver;

    @Title("收货人电话")
    private String receiverPhone;

    //收货人省份
    private String province;
    //收货人城市
    private String city;
    //收货人区县
    private String county;

    @Title("收货地址")
    private String address;

    @Title("物流编号")
    private Integer logisticsId;

    @Title("物流名称")
    private String logisticsName;

    @Title("物流单号")
    private String logisticsNo;

    @Title("大头笔信息")
    @Column
    private String arrivedOrgSimpleName;

    @Title("订单状态")
    private EOrderStatus status;

    @Title("原因")
    private String reason;

    @Title("注释")
    private String comments;

    @Title("删除标志")
    private Integer deleted;

    @Title("创建时间")
    private LocalDateTime createTime;

    @Title("更新时间")
    private LocalDateTime updateTime;

    @Title("更新人")
    private String updatedBy;

    private String departmentName;

    public String getSalesmanName() {
        if (salesman != null) {
            BgEmployee employee = RedisHelper.getEmployee(salesman);
            if (employee != null) {
                return employee.getEmployeeName();
            }

        }
        return null;
    }

    public String getSalesmanPhone() {
        if (salesman != null) {
            BgEmployee employee = RedisHelper.getEmployee(salesman);
            if (employee != null) {
                return employee.getPhone();
            }
        }
        return null;
    }
}