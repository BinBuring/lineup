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
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.lu.entity.Ceiling;
import com.thinkgem.jeesite.modules.lu.service.CeilingService;

/**
 * 预约上限Controller
 * @author 张斌
 * @version 2018-08-20
 */
@Controller
@RequestMapping(value = "${adminPath}/lu/ceiling")
public class CeilingController extends BaseController {

	@Autowired
	private CeilingService ceilingService;
	
	@ModelAttribute
	public Ceiling get(@RequestParam(required=false) String id) {
		Ceiling entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ceilingService.get(id);
		}
		if (entity == null){
			entity = new Ceiling();
		}
		return entity;
	}
	
	@RequiresPermissions("lu:ceiling:view")
	@RequestMapping(value = {"list", ""})
	public String list(Ceiling ceiling, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Ceiling> page = ceilingService.findPage(new Page<Ceiling>(request, response), ceiling); 
		model.addAttribute("page", page);
		return "modules/lu/ceilingList";
	}

	@RequiresPermissions("lu:ceiling:view")
	@RequestMapping(value = "form")
	public String form(Ceiling ceiling, Model model) {
		model.addAttribute("ceiling", ceiling);
		return "modules/lu/ceilingForm";
	}

	@RequiresPermissions("lu:ceiling:edit")
	@RequestMapping(value = "save")
	public String save(Ceiling ceiling, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ceiling)){
			return form(ceiling, model);
		}
		ceilingService.save(ceiling);
		addMessage(redirectAttributes, "保存预约上限成功");
		return "redirect:"+Global.getAdminPath()+"/lu/ceiling/?repage";
	}
	
	@RequiresPermissions("lu:ceiling:edit")
	@RequestMapping(value = "delete")
	public String delete(Ceiling ceiling, RedirectAttributes redirectAttributes) {
		ceilingService.delete(ceiling);
		addMessage(redirectAttributes, "删除预约上限成功");
		return "redirect:"+Global.getAdminPath()+"/lu/ceiling/?repage";
	}

}