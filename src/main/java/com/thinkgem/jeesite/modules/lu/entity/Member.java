/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.lu.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 会员信息Entity
 * @author 张斌
 * @version 2018-08-20
 */
public class Member extends DataEntity<Member> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 会员名
	private Integer recNum;		// 当前已预约个数
	private String tel;		// 手机号
	private String age;		// 年龄
	private String sex;		// 性别
	private String email;		// 邮箱
	private String status;		// 会员状态1是正常，2是禁止预约
	private String isdelete;		// 删除标示
	
	public Member() {
		super();
	}

	public Member(String id){
		super(id);
	}

	@Length(min=0, max=64, message="会员名长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getRecNum() {
		return recNum;
	}

	public void setRecNum(Integer recNum) {
		this.recNum = recNum;
	}
	
	@Length(min=0, max=64, message="手机号长度必须介于 0 和 64 之间")
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	@Length(min=0, max=64, message="年龄长度必须介于 0 和 64 之间")
	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	@Length(min=0, max=1, message="性别长度必须介于 0 和 1 之间")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Length(min=0, max=200, message="邮箱长度必须介于 0 和 200 之间")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Length(min=0, max=1, message="会员状态1是正常，2是禁止预约长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=1, message="删除标示长度必须介于 0 和 1 之间")
	public String getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(String isdelete) {
		this.isdelete = isdelete;
	}
	
}