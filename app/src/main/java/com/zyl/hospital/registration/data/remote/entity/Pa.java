package com.zyl.hospital.registration.data.remote.entity;

/**
 * Created by cjb
 */


public class Pa extends Role{
    private String name;
    private Integer age;
    private Integer sex;//0表示男性,1表示女性
    private Integer counts;//记录预约次数
    private CaseHistory caseHistory;//病历
}
