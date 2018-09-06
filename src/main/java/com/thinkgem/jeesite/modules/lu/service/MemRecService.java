/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.lu.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.lu.entity.MemRec;
import com.thinkgem.jeesite.modules.lu.dao.MemRecDao;

/**
 * 会员预约Service
 * @author 张斌
 * @version 2018-08-20
 */
@Service
@Transactional(readOnly = true)
public class MemRecService extends CrudService<MemRecDao, MemRec> {

	public MemRec get(String id) {
		return super.get(id);
	}
	
	public List<MemRec> findList(MemRec memRec) {
		return super.findList(memRec);
	}
	
	public Page<MemRec> findPage(Page<MemRec> page, MemRec memRec) {
		return super.findPage(page, memRec);
	}
	
	@Transactional(readOnly = false)
	public void save(MemRec memRec) {
		super.save(memRec);
	}
	
	@Transactional(readOnly = false)
	public void delete(MemRec memRec) {
		super.delete(memRec);
	}
	//判断该用户已预约个数是否小于预约上限
	public boolean isRec(String memId){
		int ceil = dao.getCeilling();
		int memCeil = dao.getMemCeilling(memId);
		if (ceil > memCeil) {
			return true;
		} else {
			return false;
		}
	}
	
}