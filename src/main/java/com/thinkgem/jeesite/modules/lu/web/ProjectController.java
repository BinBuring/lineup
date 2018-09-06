/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.lu.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.lu.entity.Group;
import com.thinkgem.jeesite.modules.lu.entity.Project;
import com.thinkgem.jeesite.modules.lu.service.GroupService;
import com.thinkgem.jeesite.modules.lu.service.ProjectService;

/**
 * 项目Controller
 * @author 张斌
 * @version 2018-08-20
 */
@Controller
@RequestMapping(value = "${adminPath}/lu/project")
public class ProjectController extends BaseController {

	@Autowired
	private ProjectService projectService;
	@Autowired
	private GroupService groupService;
	
	@ModelAttribute
	public Project get(@RequestParam(required=false) String id) {
		Project entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = projectService.get(id);
		}
		if (entity == null){
			entity = new Project();
		}
		return entity;
	}
	
	@RequiresPermissions("lu:project:view")
	@RequestMapping(value = {"list", ""})
	public String list(Project project, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Project> page = projectService.findPage(new Page<Project>(request, response), project); 
		model.addAttribute("page", page);
		return "modules/lu/projectList";
	}

	@RequiresPermissions("lu:project:view")
	@RequestMapping(value = "form")
	public String form(Project project, Model model) {
		model.addAttribute("project", project);
		return "modules/lu/projectForm";
	}

	@RequiresPermissions("lu:project:edit")
	@RequestMapping(value = "save")
	public String save(Project project, Model model, RedirectAttributes redirectAttributes) {
		project.setAllGroup(projectService.getGroup(project.getStartTime(),project.getEndTime(),project.getGroupData()));
		if (!beanValidator(model, project)){
			return form(project, model);
		}
		project.setIsdelete("1");
		boolean isupdate = projectService.saves(project);
		if (isupdate) {
			groupService.deleteByProId(project.getId());//先删掉已有的批次
			Group group = new Group();
			for (int i = 0; i < project.getAllGroup(); i++) {
				group.setIsNewRecord(true);
				group.setGroId(IdGen.uuid());
				group.setProId(project.getId());
				group.setNumber(i+1);
				group.setStartTime(groupService.getGroupTime(i, project.getStartTime(), project.getGroupData()));
				group.setEndTime(groupService.getEndGroupTime(group.getStartTime(),project.getGroupData()));
				groupService.save(group);
			}
		}
		addMessage(redirectAttributes, "保存项目成功");
		return "redirect:"+Global.getAdminPath()+"/lu/project/?repage";
	}

	
	
	@RequiresPermissions("lu:project:edit")
	@RequestMapping(value = "delete")
	public String delete(Project project, RedirectAttributes redirectAttributes) {
		projectService.delete(project);
		addMessage(redirectAttributes, "删除项目成功");
		return "redirect:"+Global.getAdminPath()+"/lu/project/?repage";
	}

}