/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.lu.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 项目Entity
 * @author 张斌
 * @version 2018-08-20
 */
public class Project extends DataEntity<Project> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 项目名
	private String startTime;		// 项目开放时间
	private String endTime;		// 项目结束时间
	private User administrator;		// 项目控制员
	private Integer groupNum;		// 每批人数
	private Integer allGroup;		// 批次上限
	private Integer groupData;		// 每批时间/分
	private String status;		// 项目状态
	private String isdelete;		// 删除
	private String address;		// 项目地址
	private String features;		// 项目特色
	private String url;		// 项目图片
	
	public Project() {
		super();
	}

	public Project(String id){
		super(id);
	}
	
	@Length(min=0, max=100, message="项目名长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	public User getAdministrator() {
		return administrator;
	}

	public void setAdministrator(User administrator) {
		this.administrator = administrator;
	}
	
	public Integer getGroupNum() {
		return groupNum;
	}

	public void setGroupNum(Integer groupNum) {
		this.groupNum = groupNum;
	}
	
	@NotNull(message="批次上限不能为空")
	public Integer getAllGroup() {
		return allGroup;
	}

	public void setAllGroup(Integer allGroup) {
		this.allGroup = allGroup;
	}
	
	public Integer getGroupData() {
		return groupData;
	}

	public void setGroupData(Integer groupData) {
		this.groupData = groupData;
	}
	
	@Length(min=0, max=1, message="项目状态长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=1, max=1, message="删除长度必须介于 1 和 1 之间")
	public String getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(String isdelete) {
		this.isdelete = isdelete;
	}
	
	@Length(min=0, max=255, message="项目地址长度必须介于 0 和 255 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=255, message="项目特色长度必须介于 0 和 255 之间")
	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}
	
	@Length(min=0, max=5000, message="项目图片长度必须介于 0 和 5000 之间")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}