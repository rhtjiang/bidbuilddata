package com.rht.sevice;

import com.rht.model.SysUser;
import com.rht.model.SysUserRole;
import com.rht.service.CurdService;

import java.util.List;
import java.util.Set;



/**
 * 用户管理
 */
public interface SysUserService extends CurdService<SysUser> {

	SysUser findByName(String username);

	/**
	 * 查找用户的菜单权限标识集合
	 * @param userName
	 * @return
	 */
	Set<String> findPermissions(String userName);

	/**
	 * 查找用户的角色集合
	 * @return
	 */
	List<SysUserRole> findUserRoles(Long userId);

}
