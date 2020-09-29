package com.licf.bgManage.enums;

/**
 * @description:
 * @author: licf
 * @time: 2020/9/24 8:58
 */
public enum PermitEnum {
    CategoryDelete("类别", "删除"),
    CategoryInsert("类别", "新增"),
    CategoryQuery("类别", "查询"),
    CategoryUpdate("类别", "修改"),
    CustomerDelete("客户", "删除"),
    CustomerInsert("客户", "新增"),
    CustomerQuery("客户", "查询"),
    CustomerUpdate("客户", "修改"),
    DepartmentDelete("部门", "删除"),
    DepartmentInsert("部门", "新增"),
    DepartmentQuery("部门", "查询"),
    DepartmentUpdate("部门", "修改"),
    EmployeeDelete("员工", "删除"),
    EmployeeInsert("员工", "新增"),
    EmployeeQuery("员工", "查询"),
    EmployeeUpdate("员工", "修改"),
    GoodsDelete("商品", "删除"),
    GoodsInsert("商品", "新增"),
    GoodsQuery("商品", "查询"),
    GoodsUpdate("商品", "修改"),
    LogisticsDelete("物流", "删除"),
    LogisticsInsert("物流", "新增"),
    LogisticsQuery("物流", "查询"),
    LogisticsUpdate("物流", "修改"),
    PayModeDelete("付款方式", "删除"),
    PayModeInsert("付款方式", "新增"),
    PayModeQuery("付款方式", "查询"),
    PayModeUpdate("付款方式", "修改"),
    RoleDelete("角色", "删除"),
    RoleInsert("角色", "新增"),
    RoleQuery("角色", "查询"),
    RoleUpdate("角色", "修改"),
    CartSave("购物车",""),
    CartQuery("购物车",""),
    OrderAdd("订单",""),
    OrderQuerySelf("订单",""),
    OrderQuery("订单",""),
    OrderReview("订单",""),
    OrderDeliver("订单",""),
    OrderFinance("订单",""),
    OrderCanCle("订单","");


    private String module;
    private String permit;

    PermitEnum(String module, String permit) {
        this.module = module;
        this.permit = permit;
    }

    public String getModule() {
        return module;
    }

    public String getPermit() {
        return permit;
    }
    }
