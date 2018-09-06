/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.lu.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.lu.entity.Project;
import com.thinkgem.jeesite.modules.lu.dao.ProjectDao;

/**
 * 项目Service
 * @author 张斌
 * @version 2018-08-20
 */
@Service
@Transactional(readOnly = true)
public class ProjectService extends CrudService<ProjectDao, Project> {

	public Project get(String id) {
		return super.get(id);
	}
	
	public List<Project> findList(Project project) {
		return super.findList(project);
	}
	
	public Page<Project> findPage(Page<Project> page, Project project) {
		return super.findPage(page, project);
	}
	
	@Transactional(readOnly = false)
	public boolean saves(Project project) {
		if (project.getIsNewRecord()){
			project.preInsert();
			dao.insert(project);
			return true;
		}else{
			project.preUpdate();
			if (dao.get(project.getId()).getAllGroup() == project.getAllGroup()) {
				dao.update(project);
				return false;
			}else {
				dao.update(project);
				return true;
			}
		}
	}
	//根据string类型的开始结束时间算出批数
	public int getGroup(String start,String end,int groupData){
		int ss = Integer.valueOf(start.split(":")[0])*60+Integer.valueOf(start.split(":")[1]);
		int sd = Integer.valueOf(end.split(":")[0])*60+Integer.valueOf(end.split(":")[1]);
		int group = (sd-ss)/groupData;
		return group;
	}
	
	@Transactional(readOnly = false)
	public void delete(Project project) {
		super.delete(project);
	}
	
}