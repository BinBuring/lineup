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
import com.thinkgem.jeesite.modules.lu.entity.MemFace;
import com.thinkgem.jeesite.modules.lu.service.MemFaceService;

/**
 * 会员头像Controller
 * @author 张斌
 * @version 2018-08-20
 */
@Controller
@RequestMapping(value = "${adminPath}/lu/memFace")
public class MemFaceController extends BaseController {

	@Autowired
	private MemFaceService memFaceService;
	
	@ModelAttribute
	public MemFace get(@RequestParam(required=false) String id) {
		MemFace entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = memFaceService.get(id);
		}
		if (entity == null){
			entity = new MemFace();
		}
		return entity;
	}
	
	@RequiresPermissions("lu:memFace:view")
	@RequestMapping(value = {"list", ""})
	public String list(MemFace memFace, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MemFace> page = memFaceService.findPage(new Page<MemFace>(request, response), memFace); 
		model.addAttribute("page", page);
		return "modules/lu/memFaceList";
	}

	@RequiresPermissions("lu:memFace:view")
	@RequestMapping(value = "form")
	public String form(MemFace memFace, Model model) {
		model.addAttribute("memFace", memFace);
		return "modules/lu/memFaceForm";
	}

	@RequiresPermissions("lu:memFace:edit")
	@RequestMapping(value = "save")
	public String save(MemFace memFace, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, memFace)){
			return form(memFace, model);
		}
		memFaceService.save(memFace);
		addMessage(redirectAttributes, "保存会员头像成功");
		return "redirect:"+Global.getAdminPath()+"/lu/memFace/?repage";
	}
	
	@RequiresPermissions("lu:memFace:edit")
	@RequestMapping(value = "delete")
	public String delete(MemFace memFace, RedirectAttributes redirectAttributes) {
		memFaceService.delete(memFace);
		addMessage(redirectAttributes, "删除会员头像成功");
		return "redirect:"+Global.getAdminPath()+"/lu/memFace/?repage";
	}

}