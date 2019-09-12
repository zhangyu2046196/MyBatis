package com.youyuan.mybatis.dao;

import com.youyuan.mybatis.domain.Department;

/**
 * 部门接口
 * @author zhangyu
 * @date 2018-3-15 下午10:26:36
 */
public interface DepartmentMapper {

	/**
	 * 根据主键查询
	 * @param guid
	 * @return
	 */
	public Department getDepartMentById(Long guid);
	
	/**
	 * 根据主键查询增强
	 * @param guid
	 * @return
	 */
	public Department getDepartMentByIdPlus(Long guid);
	
	/**
	 * collection分步查询
	 * @param guid
	 * @return
	 */
	public Department getDepartMentStepById(Long guid);
	
}
