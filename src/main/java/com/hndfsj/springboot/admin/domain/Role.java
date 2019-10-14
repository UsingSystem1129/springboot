package com.hndfsj.springboot.admin.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * <pre>
 * TODO：
 * </pre>
 *
 * @author MS
 * @date 2019/7/29
 */
@Data
public class Role implements Serializable {

    //columns START
    /**
     * ID  UUID
     */
    private java.lang.String id;
    /**
     * 角色名
     */
    private java.lang.String name;
    /**
     * 角色描述
     */
    private java.lang.String description;
    /**
     * 是否可用0：不可用，1：可用
     */
    private java.lang.Boolean isValid;
    /**
     * sort
     */
    private java.lang.Integer sort = 0;
    //columns END
}
