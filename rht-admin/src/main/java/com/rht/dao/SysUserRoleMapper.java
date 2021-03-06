package com.rht.dao;

import java.util.List;

import com.rht.model.SysUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SysUserRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);

    SysUserRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUserRole record);

    int updateByPrimaryKey(SysUserRole record);

	List<SysUserRole> findUserRoles(@Param(value = "userId") Long userId);

	int deleteByUserId(@Param(value = "userId") Long userId);
}