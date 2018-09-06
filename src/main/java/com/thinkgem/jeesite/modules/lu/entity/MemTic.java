/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.lu.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 会员门票关系Entity
 * @author 张斌
 * @version 2018-08-20
 */
public class MemTic extends DataEntity<MemTic> {
	
	private static final long serialVersionUID = 1L;
	private String ticketsId;		// 门票号
	private String memId;		// 会员id
	
	public MemTic() {
		super();
	}

	public MemTic(String id){
		super(id);
	}

	@Length(min=1, max=64, message="门票号长度必须介于 1 和 64 之间")
	public String getTicketsId() {
		return ticketsId;
	}

	public void setTicketsId(String ticketsId) {
		this.ticketsId = ticketsId;
	}
	
	@Length(min=0, max=64, message="会员id长度必须介于 0 和 64 之间")
	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}
	
}