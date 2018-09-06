/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.lu.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.lu.entity.MemFace;

/**
 * 会员头像DAO接口
 * @author 张斌
 * @version 2018-08-20
 */
@MyBatisDao
public interface MemFaceDao extends CrudDao<MemFace> {
	
}