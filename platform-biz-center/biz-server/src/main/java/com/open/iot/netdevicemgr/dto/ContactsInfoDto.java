package com.open.iot.netdevicemgr.dto;

import com.open.iot.modelandutils.base.PageRequest;
import java.util.Date;
import lombok.Data;

/**
 * <p>
 * 联系人信息
 * </p>
 *
 * @author huy
 * @since 2019-12-02
 */
@Data
public class ContactsInfoDto  extends PageRequest{


    /**  
	* @Fields field:field:{todo}(用一句话描述这个变量表示什么)  
	*/
	private static final long serialVersionUID = 1L;

	/**
     * 主键
     */
    private Integer id;

    /**
     * 联系人名称
     */
    private String contactName;

    /**
     * 联系人电话
     */
    private String contactTel;

    /**
     * 联系人邮件
     */
    private String contactEmail;

    /**
     * 所属站点分组
     */
    private String contactGroupCode;

    /**
     * 分组状态：1:启用;2:停用;3:删除
     */
    private Integer state;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;



}
