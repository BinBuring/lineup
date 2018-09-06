/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.lu.web;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.lu.entity.MemRec;
import com.thinkgem.jeesite.modules.lu.entity.Project;
import com.thinkgem.jeesite.modules.lu.service.GroupService;
import com.thinkgem.jeesite.modules.lu.service.MemRecService;
import com.thinkgem.jeesite.modules.lu.service.ProjectService;

/**
 * 会员预约Controller
 * @author 张斌
 * @version 2018-08-20
 */
@Controller
@RequestMapping(value = "${adminPath}/lu/memRec")
public class MemRecController extends BaseController {

	@Autowired
	private MemRecService memRecService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private GroupService groupService;
	
	@ModelAttribute
	public MemRec get(@RequestParam(required=false) String id) {
		MemRec entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = memRecService.get(id);
		}
		if (entity == null){
			entity = new MemRec();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(MemRec memRec, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MemRec> page = memRecService.findPage(new Page<MemRec>(request, response), memRec); 
		model.addAttribute("page", page);
		return "modules/lu/memRecList";
	}

	@RequestMapping(value = "form")
	public String form(MemRec memRec, Model model) {
		model.addAttribute("memRec", memRec);
		return "modules/lu/memRecForm";
	}

	@RequestMapping(value = "save")
	public String save(MemRec memRec, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, memRec)){
			return form(memRec, model);
		}
		memRecService.save(memRec);
		addMessage(redirectAttributes, "保存会员预约成功");
		return "redirect:"+Global.getAdminPath()+"/lu/memRec/?repage";
	}
	
	@RequestMapping(value = "subscribe")
	public String subscribe(MemRec memRec, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request) {
		memRec.setGroId(request.getParameter("groId"));  //获取到预约批次id
		memRec.setRecState("1");						 //设置默认预约状态
		memRec.setProId(groupService.get(request.getParameter("groId")).getProId());   //获取到预约项目id
		memRec.setMemId("192c6cdb2caa4436834a356a82092b0d");	//获取到预约会员id
		memRec.setCreateDate(new Date());
		
		if(!memRecService.isRec("192c6cdb2caa4436834a356a82092b0d")){
			addMessage(redirectAttributes, "会员预约已达上限");
			return "redirect:"+Global.getAdminPath()+"/lu/group/?repage";
		}
		if (!beanValidator(model, memRec)){
			return form(memRec, model);
		}
		memRecService.save(memRec);
		addMessage(redirectAttributes, "会员预约成功");
		return "redirect:"+Global.getAdminPath()+"/lu/memRec/?repage";
	}
	
	
	@RequestMapping(value = "delete")
	public String delete(MemRec memRec, RedirectAttributes redirectAttributes) {
		memRecService.delete(memRec);
		addMessage(redirectAttributes, "删除会员预约成功");
		return "redirect:"+Global.getAdminPath()+"/lu/memRec/?repage";
	}
	/**
	 * 获取机构JSON数据。
	 * @param extId 排除的ID
	 * @param type	类型（1：公司；2：部门/小组/其它：3：用户）
	 * @param grade 显示级别
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, @RequestParam(required=false) String type,
			@RequestParam(required=false) Long grade, @RequestParam(required=false) Boolean isAll, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<Project> list = projectService.findList(new Project());
		for (int i=0; i<list.size(); i++){
			Project e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()))){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}
}