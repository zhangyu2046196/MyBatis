package com.youyuan.mybatis.dao;

import com.youyuan.mybatis.domain.Employee;

/**
 * mybatis 接口式编程
 * @author zhangyu
 * @date 2018-3-9 下午9:18:55
 */
public interface EmployeeMapper {

	/**
	 * 根据主键查询
	 * @param guid
	 * @return
	 */
	public Employee getEmployeeById(long guid);
}
