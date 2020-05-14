package com.laundry.ordermgmt.repository.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity(name = "USERS")
public class Users {

	private Long userId;

	@NotNull(message = "User Name is required")
	private String userName;

	@NotNull(message = "Email Id is required")
	private String emailId;

	private String password;

	private Date lastLoginDate;
	private Date createdDate;
	private Date modifiedDate;

	@Column(name = "USER_ID")
	public Long getUserId() {
		return userId;
	}

	@Id
	@Column(name = "USER_NAME")
	public String getUserName() {
		return userName;
	}

	@Column(name = "PASSWORD")
	public String getPassword() {
		return password;
	}

	@Column(name = "EMAIL_ID")
	public String getEmailId() {
		return emailId;
	}

	@Column(name = "LAST_LOGIN_DATE")
	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	@Column(name = "CREATED_DATE")
	public Date getCreatedDate() {
		return createdDate;
	}

	@Column(name = "MODIFIED_DATE")
	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

}
