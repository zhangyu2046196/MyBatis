package com.youyuan.mybatis.dao;

import java.util.List;

import com.youyuan.mybatis.domain.Employee;

/**
 * 查询接口自定义封装结果
 * @author zhangyu
 * @date 2018-3-14 下午9:12:09
 */
public interface EmployeeMapperPlus {
	
	/**
	 * 根据主键查询
	 * @param guid
	 * @return
	 */
	public Employee getEmpById(Long guid);
	
	/**
	 * 关联查询emp和  dep
	 * @param guid
	 * @return
	 */
	public Employee getEmpAndDepById(Long guid);
	
	/**
	 * 测试使用association进行分步查询
	 * @param guid
	 * @return
	 */
	public Employee getEmpAndDepStepById(Long guid);
	
	/**
	 * 根据部门id查询列表
	 * @param deptId
	 * @return
	 */
	public List<Employee> getEmpListByDepId(Long deptId);
	
	/**
	 * discriminator鉴别器(获取某列值改变封装结果),女生返回部门信息,男生不返回部门信息
	 * @param guid
	 * @return
	 */
	public Employee getEmpByDisId(Long guid);
}
