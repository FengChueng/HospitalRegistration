package com.cjb.hospital.bean;

import java.io.Serializable;

/**
 * The type Appointment detail.
 */
public class AppointmentDetail implements Serializable{
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
	private String hospitalName;
	private String hospitalLocation;
	private String deptName;
	private String doctorName;
	private String patientName;
	private String doctorAdvice;//医嘱


	public String getDoctorAdvice() {
		return doctorAdvice;
	}

	public void setDoctorAdvice(String doctorAdvice) {
		this.doctorAdvice = doctorAdvice;
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
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public String getHospitalLocation() {
		return hospitalLocation;
	}
	public void setHospitalLocation(String hospitalLocation) {
		this.hospitalLocation = hospitalLocation;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
}
