package com.cjb.hospital.bean;

import java.util.UUID;

/**
 * 科室表
 * @author Administrator
 *
 */
public class Department extends BaseBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String deptId;
	private String deptName;//科室名称
	private String info;//简介
	private long createDate;//成立时间
	
//	private Set<DoctorBean> doctors;
	
	public Department() {
		deptId = UUID.randomUUID().toString();
	}

	public String getDeptId() {
		return deptId;
	}



	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}



	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}

//	public Set<DoctorBean> getDoctors() {
//		return doctors;
//	}
//
//	public void setDoctors(Set<DoctorBean> doctors) {
//		this.doctors = doctors;
//	}
}
