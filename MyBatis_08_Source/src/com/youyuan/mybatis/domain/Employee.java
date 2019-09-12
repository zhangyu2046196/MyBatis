package com.youyuan.mybatis.domain;

import java.io.Serializable;

/**
 * 实体
 * @author zhangyu
 * @date 2018-3-8 下午10:18:38
 */
public class Employee implements Serializable {
	private long guid;//主键
	private String userName;//用户名
	private String gender;//性别
	private String email;//邮箱
	public long getGuid() {
		return guid;
	}
	public void setGuid(long guid) {
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
	public String toString() {
		return "Employy [guid=" + guid + ", userName=" + userName + ", gender="
				+ gender + ", email=" + email + "]";
	}
}
