package com.hndfsj.springboot.admin.domain;

import lombok.Data;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * <pre>
 * TODO：
 * </pre>
 *
 * @author MS
 * @date 2019/7/24
 */
@Data
public class User implements Serializable {
// columns START
    /**
     * ID UUID
     */
    private java.lang.String id;
    /**
     * 账号 必须唯一
     */
    private java.lang.String username;
    /**
     * 密码
     */
    private java.lang.String password;
    /**
     * 姓名
     */
    private java.lang.String realname;
    /**
     * 性别 1:男,0:女
     */
    private java.lang.String sex;
    /**
     * 职务
     */
    private java.lang.String post;
    /**
     * 手机号码
     */
    private java.lang.String mobile;
    /**
     * 手机号码
     */
    private java.lang.String mobile1;
    /**
     * 联系电话
     */
    private java.lang.String phone;
    /**
     * 电子邮箱
     */
    private java.lang.String email;
    /**
     * 住址
     */
    private java.lang.String address;
    /**
     * 用户类型 [0:超级管理员],1:普通人员
     */
    private java.lang.String userType;
    /**
     * 用户状态 1:可用,0:不可用
     */
    private java.lang.String isValid;
    /**
     * 所属部门 外键关联sys_dept表
     */
    private java.lang.String deptId;
    /**
     * 登录时间
     */
    private java.util.Date loginTime;
    /**
     * 签名图片路径
     */
    private java.lang.String signPath;
    /**
     * 排序
     */
    private java.lang.Integer sort;
    // columns END
    /**
     * 头像路径
     */
    private java.lang.String headImg;
    /**
     * deptName
     */
    private java.lang.String deptName;
}
