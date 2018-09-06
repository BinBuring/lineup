/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.lu.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 会员头像Entity
 * @author 张斌
 * @version 2018-08-20
 */
public class MemFace extends DataEntity<MemFace> {
	
	private static final long serialVersionUID = 1L;
	private String faceId;		// 脸部id
	private String memId;		// 会员id
	private String url;		// 脸部图片
	
	public MemFace() {
		super();
	}

	public MemFace(String id){
		super(id);
	}

	@Length(min=1, max=64, message="脸部id长度必须介于 1 和 64 之间")
	public String getFaceId() {
		return faceId;
	}

	public void setFaceId(String faceId) {
		this.faceId = faceId;
	}
	
	@Length(min=1, max=64, message="会员id长度必须介于 1 和 64 之间")
	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}
	
	@Length(min=0, max=255, message="脸部图片长度必须介于 0 和 255 之间")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}