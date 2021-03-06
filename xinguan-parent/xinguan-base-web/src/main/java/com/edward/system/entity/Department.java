package com.edward.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author Edward
 * @since 2021-03-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_department")
@ApiModel(value="Department对象", description="")
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "系名")
    private String name;

    @ApiModelProperty(value = "系办公电话")
    private String phone;

    @ApiModelProperty(value = "办公室地点")
    private String address;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date modifiedTime;

    @ApiModelProperty(value = "系主任id，关联用户表")
    private Long mgrId;

    @ApiModelProperty(value = "分组查询中的部门人数")
    @TableField(exist = false)
    private Integer deptCount;

}
