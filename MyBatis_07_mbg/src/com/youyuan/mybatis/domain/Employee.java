package com.youyuan.mybatis.domain;

import java.io.Serializable;

public class Employee implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column EMPLOYEE.GUID
     *
     * @mbg.generated Tue Mar 20 18:51:30 CST 2018
     */
    private Long guid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column EMPLOYEE.USER_NAME
     *
     * @mbg.generated Tue Mar 20 18:51:30 CST 2018
     */
    private String userName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column EMPLOYEE.GENDER
     *
     * @mbg.generated Tue Mar 20 18:51:30 CST 2018
     */
    private String gender;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column EMPLOYEE.EMAIL
     *
     * @mbg.generated Tue Mar 20 18:51:30 CST 2018
     */
    private String email;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column EMPLOYEE.DEPARTMENT_ID
     *
     * @mbg.generated Tue Mar 20 18:51:30 CST 2018
     */
    private Long departmentId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column EMPLOYEE.GUID
     *
     * @return the value of EMPLOYEE.GUID
     *
     * @mbg.generated Tue Mar 20 18:51:30 CST 2018
     */
    public Long getGuid() {
        return guid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column EMPLOYEE.GUID
     *
     * @param guid the value for EMPLOYEE.GUID
     *
     * @mbg.generated Tue Mar 20 18:51:30 CST 2018
     */
    public void setGuid(Long guid) {
        this.guid = guid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column EMPLOYEE.USER_NAME
     *
     * @return the value of EMPLOYEE.USER_NAME
     *
     * @mbg.generated Tue Mar 20 18:51:30 CST 2018
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column EMPLOYEE.USER_NAME
     *
     * @param userName the value for EMPLOYEE.USER_NAME
     *
     * @mbg.generated Tue Mar 20 18:51:30 CST 2018
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column EMPLOYEE.GENDER
     *
     * @return the value of EMPLOYEE.GENDER
     *
     * @mbg.generated Tue Mar 20 18:51:30 CST 2018
     */
    public String getGender() {
        return gender;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column EMPLOYEE.GENDER
     *
     * @param gender the value for EMPLOYEE.GENDER
     *
     * @mbg.generated Tue Mar 20 18:51:30 CST 2018
     */
    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column EMPLOYEE.EMAIL
     *
     * @return the value of EMPLOYEE.EMAIL
     *
     * @mbg.generated Tue Mar 20 18:51:30 CST 2018
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column EMPLOYEE.EMAIL
     *
     * @param email the value for EMPLOYEE.EMAIL
     *
     * @mbg.generated Tue Mar 20 18:51:30 CST 2018
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column EMPLOYEE.DEPARTMENT_ID
     *
     * @return the value of EMPLOYEE.DEPARTMENT_ID
     *
     * @mbg.generated Tue Mar 20 18:51:30 CST 2018
     */
    public Long getDepartmentId() {
        return departmentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column EMPLOYEE.DEPARTMENT_ID
     *
     * @param departmentId the value for EMPLOYEE.DEPARTMENT_ID
     *
     * @mbg.generated Tue Mar 20 18:51:30 CST 2018
     */
    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

	@Override
	public String toString() {
		return "Employee [guid=" + guid + ", userName=" + userName
				+ ", gender=" + gender + ", email=" + email + ", departmentId="
				+ departmentId + "]";
	}
}