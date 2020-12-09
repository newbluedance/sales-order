package com.common.constants;

import com.common.view.Title;

/**
 * @author lichunfeng
 */
public interface CodeConstant {

    @Title("成功")
    String SUCCESS = "1000";

    @Title("参数错误")
    String ERROR_PARAMETER = "1001";


    @Title("登录错误")
     String ERROR_LOGIN = "1002";

    @Title("未登录")
    String NO_LOGIN = "1003";

    @Title("没有权限")
    String NO_PERMIT = "1004";

    @Title("重复操作")
    String DUPLICATE = "1005";

    @Title("未知错误")
    String UNDIFINE = "1009";

}