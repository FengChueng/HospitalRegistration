package com.cjb.hospital.bean;

public class PatientBean extends BaseBean{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String patientAccount;// 账号
	private String realName;
	private int age;
	private int sex;// 0表示男性,1表示女性
	private long birthDay;// 生日
	private String mobilePhone;// 联系电话
	private String portraint;//用户头像

//	private List<Appointment> appointments;
	
	public PatientBean() {
	}

	public PatientBean(String patientAccount, String realName, int age, int sex, long birthDay,
			String mobilePhone, String portraint) {
		super();
		this.patientAccount = patientAccount;
		this.realName = realName;
		this.age = age;
		this.sex = sex;
		this.birthDay = birthDay;
		this.mobilePhone = mobilePhone;
		this.portraint = portraint;
//		this.appointments = appointments;
	}

	public PatientBean(String patientAccount, String mobilePhone) {
		this.patientAccount = patientAccount;
		this.mobilePhone = mobilePhone;
	}

	public String getPatientAccount() {
		return patientAccount;
	}

	public void setPatientAccount(String patientAccount) {
		this.patientAccount = patientAccount;
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

//	public List<Appointment> getAppointments() {
//		return appointments;
//	}
//
//	public void setAppointments(List<Appointment> appointments) {
//		this.appointments = appointments;
//	}

}