/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.lu.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.lu.entity.Group;
import com.thinkgem.jeesite.modules.lu.dao.GroupDao;

/**
 * 项目批数Service
 * @author 张斌
 * @version 2018-08-20
 */
@Service
@Transactional(readOnly = true)
public class GroupService extends CrudService<GroupDao, Group> {

	public Group get(String id) {
		return super.get(id);
	}
	/**
	 * 根据预约时间和项目找出批次表中对应信息
	 * @param time 预约时间
	 * @param proId	项目id
	 * @return
	 */
	public Group getByTimeProId(String time,String proId){
		return dao.getByTimeProId(time,proId);
	}
	
	public List<Group> findList(Group group) {
		return super.findList(group);
	}
	
	public Page<Group> findPage(Page<Group> page, Group group) {
		return super.findPage(page, group);
	}
	
	@Transactional(readOnly = false)
	public void save(Group group) {
		super.save(group);
	}
	
	@Transactional(readOnly = false)
	public void delete(Group group) {
		super.delete(group);
	}
	@Transactional(readOnly = false)
	public void deleteByProId(String proId) {
		dao.deleteByProId(proId);
	}
	/**
	 * 根据传递的时间求出批次时间
	 * @param group 当前批次
	 * @param date	开放或结束时间
	 * @param groupTime 每批时间
	 * @return
	 */
	public String getGroupTime(int group,String date,int groupTime){
		int hh = group*groupTime/60;//批次时
		int ss = group*groupTime%60;//批次分
		String[] time = date.split(":");
		hh = Integer.valueOf(time[0])+hh;
		ss = Integer.valueOf(time[1])+ss;
		if (ss>=60) {
			ss = ss-60;
			hh = hh+1;
		}
		date = String.valueOf(hh)+":"+String.valueOf(ss)+":00";
		return date;
	}
	/**
	 * 根据传递的时间求出批次结束时间
	 * @param date 批次开始时间
	 * @param groupTime 每批时间
	 * @return
	 */
	public String getEndGroupTime(String date,int groupTime){
		String[] time = date.split(":");
		int hh = Integer.valueOf(time[0]);
		int ss = Integer.valueOf(time[1])+groupTime;
		if (ss>=60) {
			ss = ss-60;
			hh = hh+1;
		}
		date = String.valueOf(hh)+":"+String.valueOf(ss)+":00";
		return date;
	}
	
}