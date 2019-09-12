package com.youyuan.mybatis.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 部门javabean
 * @author zhangyu
 * @date 2018-3-14 下午9:59:41
 */
public class Department implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long guid;
	private String departmentName;
	private List<Employee> emps;
	
	public Department() {
		super();
	}
	
	public Department(Long guid) {
		super();
		this.guid = guid;
	}

	public Long getGuid() {
		return guid;
	}
	public void setGuid(Long guid) {
		this.guid = guid;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public List<Employee> getEmps() {
		return emps;
	}
	public void setEmps(List<Employee> emps) {
		this.emps = emps;
	}
	@Override
	public String toString() {
		return "Department [guid=" + guid + ", departmentName="
				+ departmentName + ", emps=" + emps + "]";
	}
}
