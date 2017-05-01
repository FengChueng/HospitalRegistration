package com.cjb.hospital.http.api;


import com.cjb.hospital.bean.Appointment;
import com.cjb.hospital.bean.AppointmentDetail;
import com.cjb.hospital.bean.DoctorBean;
import com.cjb.hospital.bean.DoctorSchedule;
import com.cjb.hospital.bean.ResponseEntity;
import com.cjb.hospital.constants.ApiConstant;
import com.cjb.hospital.bean.Department;
import com.cjb.hospital.bean.Hospital;
import com.cjb.hospital.bean.PatientBean;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiService {
    //--------------医院数据请求
    @GET(ApiConstant.getHospitals)
    Observable<ResponseEntity<List<Hospital>>> getHospitals();

    //--------------部门数据请求
    @GET(ApiConstant.getdepartmentsbyhid)
    Observable<ResponseEntity<List<Department>>> getDepartsments(
            @Query("hospitalId") String hospitalId,
            @Query("page") int page,
            @Query("size") int size);


    //--------------医生数据请求

    /**
     * 根据deptId获取医生列表
     * @param deptId
     * @param page
     * @param size
     * @return
     */
    @GET(ApiConstant.queryAllDoctorById)
    Observable<ResponseEntity<List<DoctorBean>>> getDoctors(
            @Query("deptId") String deptId,
            @Query("page") int page,
            @Query("size") int size);

    /**
     * 根据医生id获取详细信息
     * @param account
     * @param page
     * @param size
     * @return
     */
    @GET(ApiConstant.queryDoctorById)
    Observable<ResponseEntity<DoctorBean>> getDoctor(
            @Query("account") String account,
            @Query("page") int page,
            @Query("size") int size);

    @POST(ApiConstant.doctorlogin)
    @FormUrlEncoded
    Observable<ResponseEntity<DoctorBean>> doctorlogin(
            @Field("account") String account,
            @Field("pwd") String password);





    //--------------病人数据请求
    @POST(ApiConstant.patientlogin)
    @FormUrlEncoded
    Observable<ResponseEntity<PatientBean>> patientlogin(
            @Field("account") String account,
            @Field("pwd") String password);

    @POST(ApiConstant.patientregister)
    @FormUrlEncoded
    Observable<ResponseEntity<PatientBean>> patientregister(
            @Field("account") String account,
            @Field("pwd") String password);

    @GET(ApiConstant.modifypatientinfo)
    Observable<ResponseEntity<PatientBean>> modifyPatientInfo(
            @Query(value = "account") String account,
            @Query(value = "oldPwd") String oldPwd,
            @Query(value = "newPwd") String newPwd,
            @Query(value = "realName") String realName,
            @Query(value = "sex") Integer sex,
            @Query(value = "birthDay") Long birthDay,
            @Query(value = "portraint") String portraint,
            @Query(value = "mobilePhone") String mobilePhone);


    //--------------医生日程数据请求
    ///schedule/queryAllScheduleByDoctorAccount
    @GET(ApiConstant.getdoctorschedules)
    Observable<ResponseEntity<List<DoctorSchedule>>> queryAllScheduleByDoctorAccount(
            @Query("doctorAccount") String doctorAccount);

    //添加日程
    @GET(ApiConstant.addschedule)
    Observable<ResponseEntity<DoctorSchedule>> addSchedule(@Query("doctorAccount") String doctorAccount,
                                                              @Query("status") int status,
                                                              @Query("maxAppointmentCount") int maxAppointmentCount,
                                                              @Query("scheduleDate") long scheduleDate);

    //--------------预约数据请求
    @GET(ApiConstant.makeappointment)
    Observable<ResponseEntity<Appointment>> makeAppointment(@Query(value = "hospitalId") String hospitalId,
                                                            @Query(value = "deptId") String deptId,
                                                            @Query(value = "patientId") String patientId,
                                                            @Query(value = "doctorId") String doctorId,
                                                            @Query(value = "doctorScheduleId") String doctorScheduleId);

    @GET(ApiConstant.getAppointmentInfo)
    Observable<ResponseEntity<AppointmentDetail>> appointmentInfo(@Query(value = "hospitalId") String hospitalId,
                                                                  @Query(value = "deptId") String deptId,
                                                                  @Query(value = "patientId") String patientId,
                                                                  @Query(value = "doctorId") String doctorId,
                                                                  @Query(value = "doctorScheduleId") String doctorScheduleId);
    @GET(ApiConstant.completeappointment)
    Observable<ResponseEntity<Appointment>>completeAppointment(@Query(value = "appointId") String appointId,
                                                               @Query(value = "doctorAdvice") String doctorAdvice);

    @GET(ApiConstant.timeoutappointment)
    Observable<ResponseEntity<Appointment>>timeOutappointment(@Query(value = "appointId") String appointId);

    @GET(ApiConstant.startappointment)
    Observable<ResponseEntity<Appointment>>startAppointment(@Query(value = "appointId") String appointId);

    @GET(ApiConstant.cancelappointment)
    Observable<ResponseEntity<Appointment>>cancelAppointment(@Query(value = "appointId") String appointId);


    @GET(ApiConstant.querydetail)
    Observable<ResponseEntity<AppointmentDetail>> queryDetailInfo(@Query(value = "appointId") String appointId);


    @GET(ApiConstant.queryAllAppointmentByPid)
    Observable<ResponseEntity<List<Appointment>>> queryAllAppointmentByPid(@Query(value = "patientId") String patientId,
                                                                           @Query(value = "page") int page,
                                                                           @Query(value = "size") int size);
    @GET(ApiConstant.queryAllByDid)
    Observable<ResponseEntity<List<Appointment>>> queryAllAppointmentByDid(@Query(value = "doctorId") String doctorId,
                                                                           @Query(value = "page") int page,
                                                                           @Query(value = "size") int size);
    @GET(ApiConstant.queryAllByDidAndStatus)
    Observable<ResponseEntity<List<Appointment>>> queryByDoctorIdAndStatus(@Query(value = "doctorId") String doctorId,
                                                                           @Query(value = "status") int status,
                                                                           @Query(value = "page") int page,
                                                                           @Query(value = "size") int size);
    @GET(ApiConstant.queryAllByPidAndStatus)
    Observable<ResponseEntity<List<Appointment>>> queryByPatientIdAndStatus(@Query(value = "patientId") String patientId,
                                                                            @Query(value = "status") int status,
                                                                            @Query(value = "page") int page,
                                                                            @Query(value = "size") int size);
    //queryByPatientId

}
