package com.zyl.hospital.registration.constants;

/**
 * Created by zhouyou on 2016/6/24.
 * Class desc: api constant store
 */
public interface ApiConstant {
    /** 网络请求成功 */
    int REQUEST_SUCCESS = 0;
    /**网路请求失败*/
    int REQUEST_FAIL = 1;

    /** 服务器地址 */
    String API_SERVER_URL = "http://120.24.183.87:8888/";

    //客户端相应结果码
    int SUCCESS = 1000;//客户端请求成功
    int FIAL = 1001;//客户端请求失败

    //预约状态 未处理,正在处理中,已处理,超时未处理,已取消预约
    int APPOINT_UN_HANDLE = 2000;//未处理
    int APPOINT_HANDLE_ING = 2001;//正在处理中
    int APPOINT_COMPLETED = 2002;//已处理
    int APPOINT_TIMEOUT = 2003;//超时未处理
    int APPOINT_CANCEL = 2004;//已被取消

    //医生工作安排状态
    int DOCTOR_SCHEDULE_FULL = 3000;//预约已满,不可预约;
    int DOCTOR_SCHEDULE_POSSIBLE = 3001;//可以被预约
    int DOCTOR_SCHEDULE_REST = 3002;//休息,不可预约

    //医生级别
    int DOCTOR_NORMAL = 4000;//普通
    int DOCTOR_PROFESSOR = 4001;//专家

    //医院级别
    int HOSPITAL_LEVEL_1_C = 5000;
    int HOSPITAL_LEVEL_1_B = 5001;
    int HOSPITAL_LEVEL_1_A = 5002;
    int HOSPITAL_LEVEL_2_C = 5003;
    int HOSPITAL_LEVEL_2_B = 5004;
    int HOSPITAL_LEVEL_2_A = 5005;
    int HOSPITAL_LEVEL_3_C = 5006;
    int HOSPITAL_LEVEL_3_B = 5007;
    int HOSPITAL_LEVEL_3_A = 5008;

    //性别
    int SEX_MALE = 60000;
    int SEX_FEMALE = 60001;

    //角色
    int DOCTOR = 7000;//病人
    int PATIENT = 70001;//医生

    String getHospitals = "/hospital/queryall";//查询所有医院
    String patientlogin = "/patient/login";//病人登录
    String patientregister = "/patient/register/";
    String getdepartmentsbyhid = "/hospital/queryByHospitalId/";
    String doctorlogin = "/doctor/login";
    String getdoctorschedules = "/schedule/queryAllScheduleByDoctorAccount/";
    String addschedule = "/schedule/addScheduleByDoctorAccount/";
    String makeappointment = "/appointment/makeappointment/";
    String querydetail = "/appointment/queryByAppointId/";
    String queryAllAppointmentByPid = "/appointment/queryByPatientId/";
    String queryAllByPidAndStatus = "/appointment/queryByPatientIdAndStatus/";
    String queryAllByDid = "/appointment/queryByDoctorId/";
    String queryAllByDidAndStatus = "/appointment/queryByDoctorIdAndStatus/";
    String modifypatientinfo = "/patient/modifyPatientInfo/";
}
