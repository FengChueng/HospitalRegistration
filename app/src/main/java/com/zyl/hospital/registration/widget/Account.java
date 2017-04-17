package com.zyl.hospital.registration.widget;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 账号表
 * 
 * @author Administrator
 *
 */
@Entity // 是一个JPA实体
@Table(name = "account") // 指定数据库中映射的表名
@DynamicInsert // 是否动态添加
@DynamicUpdate // 是否动态更新
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "account_id")
	private String accountId;

	@Column(name = "mobile_phone", length = 11, nullable = false, unique = true)
	private String mobilePhone;// 账号

	@Column(name = "password", nullable = false)
	private String password;// 密码

	@Column(name = "role", nullable = false)
	private int role;// 角色分类 0表示病人,1表示医生
	public Account() {
		this.accountId = UUID.randomUUID().toString();
	}

	public Account(String mobilePhone, String password, int role) {
		super();
		this.accountId = UUID.randomUUID().toString();
		this.mobilePhone = mobilePhone;
		this.password = password;
		this.role = role;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}
}
