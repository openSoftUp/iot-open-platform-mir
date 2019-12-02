package com.open.iot.netdevicemgr.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 
* @ClassName: OauthClientSite 
* @Description: 客户端与站点关系管理
* @author huy
* @date 2019年12月1日 上午10:57:56 
*
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OauthClientSite extends Model<OauthClientSite> {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    /**
     * 客户端ID
     */
    @TableId(value = "id", type = IdType.INPUT)
    private String clientId;

    /**
     * 站点，资源，多个用逗号隔开
     */
    private String siteIds;
    
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


    @Override
    protected Serializable pkVal() {
        return this.clientId;
    }

}
