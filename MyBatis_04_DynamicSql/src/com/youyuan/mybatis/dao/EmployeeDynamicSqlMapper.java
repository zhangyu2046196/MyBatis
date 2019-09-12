package com.youyuan.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.youyuan.mybatis.domain.Employee;

/**
 * 动态sql
 * @author zhangyu
 * @date 2018-3-16 下午11:09:04
 */
public interface EmployeeDynamicSqlMapper {
	
	/**
	 * 动态拼装sql接口
	 * @param emp
	 * @return
	 */
	public List<Employee> getEmpByDynamicSql(Employee emp);
	
	/**
	 * 动态sql中trim标签
	 * @param emp
	 * @return
	 */
	public List<Employee> getEmpByDynamicSqlTrim(Employee emp);
	
	/**
	 * 动态sql中choose标签
	 * @param emp
	 * @return
	 */
	public List<Employee> getEmpByDynamicSqlChoose(Employee emp);
	
	/**
	 * 动态sql中set或trim标签实现更新操作
	 * @param emp
	 * @return
	 */
	public boolean updateEmpByDynamicSqlSetOrTrim(Employee emp);
	
	/**
	 * 动态sql中foreach标签操作
	 * @param ids
	 * @return
	 */
	public List<Employee> getEmpByDynamicSqlForeach(@Param("ids")List<Long> ids);
	
	/**
	 * 批量 保存
	 * @param emps
	 * @return
	 */
	public boolean addEmps(@Param("emps")List<Employee> emps);
	
	/**
	 * mybatis中的两个内置参数
	 * 	_parameter:代表整个参数,如果传递过来的是一个参数_parameter就代表是这个参数,如果传递过来的是多个参数,因为多个参数要封装成map,所以这时的_parameter就代表map
	 *  _databseId:数据库的别名(主要用于切换数据源后执行sql)
	 * @param emp
	 * @return
	 */
	public List<Employee> getEmpsByInnerParam(Employee emp);
	
	/**
	 * bind绑定用法
	 * @param emp
	 * @return
	 */
	public List<Employee> getEmpByBind(Employee emp);

}
