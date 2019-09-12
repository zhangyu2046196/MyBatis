package com.youyuan.mybatis.domain;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

/**
 * 实体
 * @author zhangyu
 * @date 2018-3-8 下午10:18:38
 */
@Alias("emp")
public class Employee implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long guid;//主键
	private String userName;//用户名
	private String gender;//性别
	private String email;//邮箱
	private Department dep;//部门
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Employee(Long guid, String userName, String gender, String email) {
		super();
		this.guid = guid;
		this.userName = userName;
		this.gender = gender;
		this.email = email;
	}
	
	public Employee(Long guid, String userName, String gender, String email,
			Department dep) {
		super();
		this.guid = guid;
		this.userName = userName;
		this.gender = gender;
		this.email = email;
		this.dep = dep;
	}

	public Long getGuid() {
		return guid;
	}

	public void setGuid(Long guid) {
		this.guid = guid;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Department getDep() {
		return dep;
	}

	public void setDep(Department dep) {
		this.dep = dep;
	}

	@Override
	public String toString() {
		return "Employee [guid=" + guid + ", userName=" + userName
				+ ", gender=" + gender + ", email=" + email + ", dep=" + dep
				+ "]";
	}
}
