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
import com.thinkgem.jeesite.modules.lu.entity.Member;
import com.thinkgem.jeesite.modules.lu.service.MemberService;

/**
 * 会员信息Controller
 * @author 张斌
 * @version 2018-08-20
 */
@Controller
@RequestMapping(value = "${adminPath}/lu/member")
public class MemberController extends BaseController {

	@Autowired
	private MemberService memberService;
	
	@ModelAttribute
	public Member get(@RequestParam(required=false) String id) {
		Member entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = memberService.get(id);
		}
		if (entity == null){
			entity = new Member();
		}
		return entity;
	}
	
	@RequiresPermissions("lu:member:view")
	@RequestMapping(value = {"list", ""})
	public String list(Member member, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Member> page = memberService.findPage(new Page<Member>(request, response), member); 
		model.addAttribute("page", page);
		return "modules/lu/memberList";
	}

	@RequiresPermissions("lu:member:view")
	@RequestMapping(value = "form")
	public String form(Member member, Model model) {
		model.addAttribute("member", member);
		return "modules/lu/memberForm";
	}

	@RequiresPermissions("lu:member:edit")
	@RequestMapping(value = "save")
	public String save(Member member, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, member)){
			return form(member, model);
		}
		memberService.save(member);
		addMessage(redirectAttributes, "保存会员成功");
		return "redirect:"+Global.getAdminPath()+"/lu/member/?repage";
	}
	
	@RequiresPermissions("lu:member:edit")
	@RequestMapping(value = "ban")
	public String ban(Member member, Model model, RedirectAttributes redirectAttributes) {
		member.setStatus("2");
		if (!beanValidator(model, member)){
			return form(member, model);
		}
		memberService.save(member);
		addMessage(redirectAttributes, "禁止成功");
		return "redirect:"+Global.getAdminPath()+"/lu/member/?repage";
	}
	
	@RequiresPermissions("lu:member:edit")
	@RequestMapping(value = "relieve")
	public String relieve(Member member, Model model, RedirectAttributes redirectAttributes) {
		member.setStatus("1");
		if (!beanValidator(model, member)){
			return form(member, model);
		}
		memberService.save(member);
		addMessage(redirectAttributes, "解除成功");
		return "redirect:"+Global.getAdminPath()+"/lu/member/?repage";
	}
	@RequiresPermissions("lu:member:edit")
	@RequestMapping(value = "delete")
	public String delete(Member member, RedirectAttributes redirectAttributes) {
		memberService.delete(member);
		addMessage(redirectAttributes, "删除会员成功");
		return "redirect:"+Global.getAdminPath()+"/lu/member/?repage";
	}

}