package com.open.iot.model.system;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import lombok.Data;

/**
 * 用户实体
 */
@Data
@TableName("sys_user")
public class SysUser  extends Model<SysUser>  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5886012896705137070L;
	@TableId(value="id",type=IdType.AUTO) 
	@JsonSerialize(using=ToStringSerializer.class)
	private Long id;
	private String username;
	private String password;
	private String nickname;
	private String headImgUrl;
	private String phone;
	private Integer sex;
	private Boolean enabled;
	private String type;
	@TableField(value="createTime")
	private Date createTime;
	@TableField(value="updateTime")
	private Date updateTime;
	
	@TableField(exist=false)
	private List<SysRole> roles;
	
	@TableField(exist=false)
	private String roleId;

	@TableField(exist=false)
	private String oldPassword;
	@TableField(exist=false)
	private String newPassword;

}
