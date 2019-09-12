package com.youyuan.mybatis.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

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
	
	/**
	 * 添加
	 * @param emp
	 * @return
	 */
	public boolean addEmp(Employee emp);
	
	/**
	 * 修改
	 * @param emp
	 * @return
	 */
	public Integer update(Employee emp);
	
	/**
	 * 删除
	 * @param guid
	 * @return
	 */
	public Integer deleteByGuid(Long guid);
	
	/**
	 * 根据主键查询
	 * @param guid
	 * @return
	 */
	public String getUserNameByGuid(Long guid);
	
	/**
	 * 多参数查询(命名参数)
	 * @param guid
	 * @param userName
	 * @return
	 */
	public Employee getEmployeeByGuidAndUserName(@Param("guid")Long guid,@Param("userName")String userName);
	
	/**
	 * 多参数查询(map)
	 * @param paramMap
	 * @return
	 */
	public Employee getEmployeeByMap(Map<String, Object> paramMap);
	
	/**
	 * 返回集合
	 * @param userName
	 * @return
	 */
	public List<Employee> getEmployeeList(String userName);
	
	/**
	 * 返回map
	 * @param guid
	 * @return
	 */
	public Map<String, Object> getEmpByIdToMap(Long guid);
	
	/**
	 * 查询多条记录返回map结构
	 * @param userName
	 * @return
	 */
	@MapKey("guid")//指定key为实体bean的guid也就是数据库中的GUID
	public Map<Long, Employee> getEmpByUserNameToMap(String userName);
}
