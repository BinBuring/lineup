/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.lu.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.lu.entity.MemFace;
import com.thinkgem.jeesite.modules.lu.dao.MemFaceDao;

/**
 * 会员头像Service
 * @author 张斌
 * @version 2018-08-20
 */
@Service
@Transactional(readOnly = true)
public class MemFaceService extends CrudService<MemFaceDao, MemFace> {

	public MemFace get(String id) {
		return super.get(id);
	}
	
	public List<MemFace> findList(MemFace memFace) {
		return super.findList(memFace);
	}
	
	public Page<MemFace> findPage(Page<MemFace> page, MemFace memFace) {
		return super.findPage(page, memFace);
	}
	
	@Transactional(readOnly = false)
	public void save(MemFace memFace) {
		super.save(memFace);
	}
	
	@Transactional(readOnly = false)
	public void delete(MemFace memFace) {
		super.delete(memFace);
	}
	
}