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
import com.thinkgem.jeesite.modules.lu.entity.MemTic;
import com.thinkgem.jeesite.modules.lu.service.MemTicService;

/**
 * 会员门票关系Controller
 * @author 张斌
 * @version 2018-08-20
 */
@Controller
@RequestMapping(value = "${adminPath}/lu/memTic")
public class MemTicController extends BaseController {

	@Autowired
	private MemTicService memTicService;
	
	@ModelAttribute
	public MemTic get(@RequestParam(required=false) String id) {
		MemTic entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = memTicService.get(id);
		}
		if (entity == null){
			entity = new MemTic();
		}
		return entity;
	}
	
	@RequiresPermissions("lu:memTic:view")
	@RequestMapping(value = {"list", ""})
	public String list(MemTic memTic, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MemTic> page = memTicService.findPage(new Page<MemTic>(request, response), memTic); 
		model.addAttribute("page", page);
		return "modules/lu/memTicList";
	}

	@RequiresPermissions("lu:memTic:view")
	@RequestMapping(value = "form")
	public String form(MemTic memTic, Model model) {
		model.addAttribute("memTic", memTic);
		return "modules/lu/memTicForm";
	}

	@RequiresPermissions("lu:memTic:edit")
	@RequestMapping(value = "save")
	public String save(MemTic memTic, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, memTic)){
			return form(memTic, model);
		}
		memTicService.save(memTic);
		addMessage(redirectAttributes, "保存会员门票关系成功");
		return "redirect:"+Global.getAdminPath()+"/lu/memTic/?repage";
	}
	
	@RequiresPermissions("lu:memTic:edit")
	@RequestMapping(value = "delete")
	public String delete(MemTic memTic, RedirectAttributes redirectAttributes) {
		memTicService.delete(memTic);
		addMessage(redirectAttributes, "删除会员门票关系成功");
		return "redirect:"+Global.getAdminPath()+"/lu/memTic/?repage";
	}

}