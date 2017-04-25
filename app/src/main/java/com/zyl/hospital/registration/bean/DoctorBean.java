package com.zyl.hospital.registration.bean;

import java.util.List;

public class DoctorBean extends BaseBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String doctorAccount;
	private String realName;
	private int age;
	private int sex;/*{@link com.zyl.utils.Constant;}*/
	private long birthDay;// 生日
	private String mobilePhone;// 联系电话
	private String portraint;//用户头像
	
	private String info;//简介
	private int level;/*{@link com.zyl.utils.Constant;}*/
	private int orderCount;//被预约次数

	private List<DoctorSchedule> doctorSchedules;
	
	private List<Appointment> appointments;

	public DoctorBean() {
	}

	public DoctorBean(String doctorAccount, String realName, int age, int sex, long birthDay, String mobilePhone,
			String portraint, String info, int level, int orderCount) {
		super();
		this.doctorAccount = doctorAccount;
		this.realName = realName;
		this.age = age;
		this.sex = sex;
		this.birthDay = birthDay;
		this.mobilePhone = mobilePhone;
		this.portraint = portraint;
		this.info = info;
		this.level = level;
		this.orderCount = orderCount;
//		this.doctorSchedules = doctorSchedules;
//		this.appointments = appointments;
	}

	public String getDoctorAccount() {
		return doctorAccount;
	}

	public void setDoctorAccount(String doctorAccount) {
		this.doctorAccount = doctorAccount;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public long getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(long birthDay) {
		this.birthDay = birthDay;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getPortraint() {
		return portraint;
	}

	public void setPortraint(String portraint) {
		this.portraint = portraint;
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

	public int getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}

	public List<DoctorSchedule> getDoctorSchedules() {
		return doctorSchedules;
	}

	public void setDoctorSchedules(List<DoctorSchedule> doctorSchedules) {
		this.doctorSchedules = doctorSchedules;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}
	
}
