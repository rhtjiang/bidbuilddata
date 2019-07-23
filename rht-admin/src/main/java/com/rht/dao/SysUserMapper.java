package com.rht.dao;

import java.util.List;

import com.rht.model.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SysUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
    
    List<SysUser> findPage();
    
    SysUser findByName(@Param(value = "name") String name);
    
	List<SysUser> findPageByName(@Param(value = "name") String name);
	
	List<SysUser> findPageByNameAndEmail(@Param(value = "name") String name, @Param(value = "email") String email);
}