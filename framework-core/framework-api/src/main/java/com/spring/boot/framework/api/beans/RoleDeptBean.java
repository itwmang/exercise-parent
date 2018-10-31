package com.spring.boot.framework.api.beans;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: wangmian
 * @Date: 2018/8/30 22:54
 * @Description: 角色部门组合信息
 */
@Data
@ToString
@Getter
@Setter
public class RoleDeptBean implements Serializable {
    /**
     * 角色id
     */
    private Integer roleId;
    /**
     * 角色编码 唯一
     */
    private String roleCode;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色描述
     */
    private String roleDesc;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 最后修改时间
     */
    private Date updateDate;
    /**
     * 状态 0：正常 1：删除
     */
    private Integer state = 0;
    /**
     * 部门id
     */
    private Integer deptId;
    /**
     * 上级部门id
     */
    private Integer parentDeptId;
    /**
     * 部门名称
     */
    private String deptName;
}
