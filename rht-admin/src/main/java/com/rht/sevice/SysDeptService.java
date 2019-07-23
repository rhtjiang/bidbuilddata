package com.rht.sevice;

import com.rht.model.SysDept;
import com.rht.service.CurdService;

import java.util.List;



/**
 * 机构管理
 */
public interface SysDeptService extends CurdService<SysDept> {

	/**
	 * 查询机构树
	 * @param
	 * @return
	 */
	List<SysDept> findTree();
}
