/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.lu.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 预约上限Entity
 * @author 张斌
 * @version 2018-08-20
 */
public class Ceiling extends DataEntity<Ceiling> {
	
	private static final long serialVersionUID = 1L;
	private String recCeiling;		// 预约上限
	
	public Ceiling() {
		super();
	}

	public Ceiling(String id){
		super(id);
	}

	@Length(min=1, max=2, message="预约上限长度必须介于 1 和 2 之间")
	public String getRecCeiling() {
		return recCeiling;
	}

	public void setRecCeiling(String recCeiling) {
		this.recCeiling = recCeiling;
	}
	
}