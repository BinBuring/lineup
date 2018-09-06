/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.lu.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 项目批数Entity
 * @author 张斌
 * @version 2018-08-20
 */
public class Group extends DataEntity<Group> {
	
	private static final long serialVersionUID = 1L;
	private String groId;		// 批次编号
	private String proId;		// 项目id
	private Integer number;		// 批数
	private String startTime;	// 批次开始时间
	private String endTime;		// 批次结束时间
	private Project project;	//项目
	
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Group() {
		super();
	}

	public Group(String id){
		super(id);
	}

	@Length(min=1, max=64, message="批次编号长度必须介于 1 和 64 之间")
	public String getGroId() {
		return groId;
	}

	public void setGroId(String groId) {
		this.groId = groId;
	}
	
	@Length(min=1, max=64, message="项目id长度必须介于 1 和 64 之间")
	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}
	
	@NotNull(message="批数不能为空")
	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
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
	
}