package com.cjb.hospital.bean;

import java.io.Serializable;
import java.util.UUID;

public class Appointment implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String appointId;//预约id
	private float price;//价格
	private long clinicDate;//就诊时间
	private long appointDate;//预约时间 提前1-3天开始预约
	private int status;//0未处理,1已处理,2超时未处理,3已取消预约
	private String location;	
	private String hospitalId;
	private String deptId;
	private String patientId;
	private String doctorId;
	public String getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	
	private String doctorAdvice;//医嘱
	
	public Appointment() {
		this.appointId = UUID.randomUUID().toString();
	}
	public String getAppointId() {
		return appointId;
	}

	public void setAppointId(String appointId) {
		this.appointId = appointId;
	}

	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public long getClinicDate() {
		return clinicDate;
	}
	public void setClinicDate(long clinicDate) {
		this.clinicDate = clinicDate;
	}
	public long getAppointDate() {
		return appointDate;
	}
	public void setAppointDate(long appointDate) {
		this.appointDate = appointDate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDoctorAdvice() {
		return doctorAdvice;
	}
	public void setDoctorAdvice(String doctorAdvice) {
		this.doctorAdvice = doctorAdvice;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
}
