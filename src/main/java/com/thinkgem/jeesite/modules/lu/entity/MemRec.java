/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.lu.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 会员预约Entity
 * @author 张斌
 * @version 2018-08-20
 */
public class MemRec extends DataEntity<MemRec> {
	
	private static final long serialVersionUID = 1L;
	private String memId;		// 会员id
	private Member member;
	private String proId;		// 项目id
	private Project project;
	private String recState;		// 预约状态1是已预约，2是已验证，3是已取消
	private String groId;		// 批次id
	private Group group;
	private String groNum;		//用作检索
	private String memTel;		//用作检索
	
	public String getGroNum() {
		return groNum;
	}

	public void setGroNum(String groNum) {
		this.groNum = groNum;
	}

	public String getMemTel() {
		return memTel;
	}

	public void setMemTel(String memTel) {
		this.memTel = memTel;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public MemRec() {
		super();
	}

	public MemRec(String id){
		super(id);
	}

	@Length(min=1, max=64, message="会员id长度必须介于 1 和 64 之间")
	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}
	
	@Length(min=0, max=64, message="项目id长度必须介于 0 和 64 之间")
	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}
	
	@Length(min=0, max=1, message="预约状态1是已预约，2是已验证，3是已取消长度必须介于 0 和 1 之间")
	public String getRecState() {
		return recState;
	}

	public void setRecState(String recState) {
		this.recState = recState;
	}
	
	@Length(min=0, max=64, message="批次id长度必须介于 0 和 64 之间")
	public String getGroId() {
		return groId;
	}

	public void setGroId(String groId) {
		this.groId = groId;
	}
	
}