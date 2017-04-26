package com.zyl.hospital.registration.http.api;


import com.zyl.hospital.registration.bean.Appointment;
import com.zyl.hospital.registration.bean.Department;
import com.zyl.hospital.registration.bean.DoctorBean;
import com.zyl.hospital.registration.bean.DoctorSchedule;
import com.zyl.hospital.registration.bean.Hospital;
import com.zyl.hospital.registration.bean.PatientBean;
import com.zyl.hospital.registration.bean.ResponseEntity;
import com.zyl.hospital.registration.constants.ApiConstant;

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
    @GET(ApiConstant.queryAllDoctorById)
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
    @FormUrlEncoded
    Observable<ResponseEntity<List<DoctorSchedule>>> queryAllScheduleByDoctorAccount(
            @Query("doctorAccount") String doctorAccount);

    //添加日程
    @GET(ApiConstant.addschedule)
    @FormUrlEncoded
    Observable<ResponseEntity<DoctorSchedule>> addSchedule(@Query("doctorAccount") String doctorAccount,
                                                           @Query("status") int status,
                                                           @Query("maxAppointmentCount") int maxAppointmentCount,
                                                           @Query("scheduleDate") long scheduleDate);

    //--------------预约数据请求
    @GET(ApiConstant.makeappointment)
    @FormUrlEncoded
    Observable<ResponseEntity<?>> makeAppointment(@Query(value = "patientId") String patientId,
                                                  @Query(value = "doctorId") String doctorId,
                                                  @Query(value = "doctorScheduleId") String doctorScheduleId,
                                                  @Query(value = "price") float price,
                                                  @Query(value = "clinicDate") long clinicDate,
                                                  @Query(value = "appointDate") long appointDate,
                                                  @Query(value = "location") String location);
    @GET(ApiConstant.querydetail)
    @FormUrlEncoded
    Observable<ResponseEntity<Appointment>> queryDetailInfo(@Query(value = "doctorScheduleId") String doctorScheduleId);


    @GET(ApiConstant.queryAllAppointmentByPid)
    @FormUrlEncoded
    Observable<ResponseEntity<List<Appointment>>> queryAllAppointmentByPid(@Query(value = "patientId") String patientId,
                                                                           @Query(value = "page") int page,
                                                                           @Query(value = "size") int size);
    @GET(ApiConstant.queryAllByDid)
    @FormUrlEncoded
    Observable<ResponseEntity<List<Appointment>>> queryAllAppointmentByDid(@Query(value = "doctorId") String doctorId,
                                                                           @Query(value = "page") int page,
                                                                           @Query(value = "size") int size);
    @GET(ApiConstant.queryAllByDidAndStatus)
    @FormUrlEncoded
    Observable<ResponseEntity<List<Appointment>>> queryByDoctorIdAndStatus(@Query(value = "doctorId") String doctorId,
                                                                           @Query(value = "status") int status,
                                                                           @Query(value = "page") int page,
                                                                           @Query(value = "size") int size);
    @GET(ApiConstant.queryAllAppointmentByPid)
    @FormUrlEncoded
    Observable<ResponseEntity<List<Appointment>>> queryByPatientIdAndStatus(@Query(value = "patientId") String patientId,
                                                                            @Query(value = "status") int status,
                                                                            @Query(value = "page") int page,
                                                                            @Query(value = "size") int size);
    //queryByPatientId

}
