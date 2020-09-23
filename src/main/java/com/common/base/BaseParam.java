package com.common.base;

import lombok.Data;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

/**
 * 用户请求Dto
 * @author lichunfeng
 */
@Data
public abstract class BaseParam implements Serializable {

    private String orderByClause;

}