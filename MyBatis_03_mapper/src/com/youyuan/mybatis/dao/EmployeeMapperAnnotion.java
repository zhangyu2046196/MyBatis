package com.youyuan.mybatis.dao;

import org.apache.ibatis.annotations.Select;

import com.youyuan.mybatis.domain.Employee;

/**
 * 通过在方法上注解写sql
 * @author zhangyu
 * @date 2018-3-11 下午7:20:18
 */
public interface EmployeeMapperAnnotion {
	
	/**
	 * 通过主键查询
	 * @param guid
	 * @return
	 */
	@Select("select * from EMPLOYEE where GUID=#{guid}")
	public Employee getEmployeeById(long guid); 

}
