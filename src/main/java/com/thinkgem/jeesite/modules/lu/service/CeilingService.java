/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.lu.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.lu.entity.Ceiling;
import com.thinkgem.jeesite.modules.lu.dao.CeilingDao;

/**
 * 预约上限Service
 * @author 张斌
 * @version 2018-08-20
 */
@Service
@Transactional(readOnly = true)
public class CeilingService extends CrudService<CeilingDao, Ceiling> {

	public Ceiling get(String id) {
		return super.get(id);
	}
	
	public List<Ceiling> findList(Ceiling ceiling) {
		return super.findList(ceiling);
	}
	
	public Page<Ceiling> findPage(Page<Ceiling> page, Ceiling ceiling) {
		return super.findPage(page, ceiling);
	}
	
	@Transactional(readOnly = false)
	public void save(Ceiling ceiling) {
		super.save(ceiling);
	}
	
	@Transactional(readOnly = false)
	public void delete(Ceiling ceiling) {
		super.delete(ceiling);
	}
	
}