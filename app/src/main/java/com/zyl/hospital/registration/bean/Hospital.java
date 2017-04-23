package com.zyl.hospital.registration.bean;

import java.util.Set;
import java.util.UUID;

/**
 * 医院表
 * 
 * @author Administrator
 *
 */
public class Hospital extends BaseBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String hospitalId;
	private String hospitalName;
	private String info;// 简介
	private int level;// 50001-5009
	private long createDate;// 创建时间;

	private String location;// 地理位置
	private float longitude;// 经度
	private float latitude;// 纬度

	private String img;

	private Set<Department> departments;// 科室

	public Hospital() {
		hospitalId = UUID.randomUUID().toString();
	}

	public String getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public Set<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(Set<Department> departments) {
		this.departments = departments;
	}
}
