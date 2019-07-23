package com.rht.dao;

import java.util.List;

import com.rht.model.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SysMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);
    
	List<SysMenu> findPage();

	List<SysMenu> findPageByName(@Param(value = "name") String name);
	
	List<SysMenu> findAll();

	List<SysMenu> findByUserName(@Param(value = "userName") String userName);

	List<SysMenu> findRoleMenus(@Param(value = "roleId") Long roleId);
}