/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.lu.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.lu.entity.MemTic;
import com.thinkgem.jeesite.modules.lu.dao.MemTicDao;

/**
 * 会员门票关系Service
 * @author 张斌
 * @version 2018-08-20
 */
@Service
@Transactional(readOnly = true)
public class MemTicService extends CrudService<MemTicDao, MemTic> {

	public MemTic get(String id) {
		return super.get(id);
	}
	
	public List<MemTic> findList(MemTic memTic) {
		return super.findList(memTic);
	}
	
	public Page<MemTic> findPage(Page<MemTic> page, MemTic memTic) {
		return super.findPage(page, memTic);
	}
	
	@Transactional(readOnly = false)
	public void save(MemTic memTic) {
		super.save(memTic);
	}
	
	@Transactional(readOnly = false)
	public void delete(MemTic memTic) {
		super.delete(memTic);
	}
	
}