package com.licf.app.entity.dto;

import com.alibaba.fastjson.JSON;
import com.common.base.BaseParam;
import com.common.validation.group.Add;
import com.common.validation.group.Update;
import com.fasterxml.jackson.core.JsonParser;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author lichunfeng
 * @date 2020-09-16 18:37:39
 */
@Data
public class UsrCartParam extends BaseParam {

    /** 商品信息 */
    private String goodId;

    private int num;

}