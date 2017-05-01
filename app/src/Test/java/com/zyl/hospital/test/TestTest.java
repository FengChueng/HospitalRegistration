package com.zyl.hospital.test;


import junit.framework.TestCase;

import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangyinglong on 2017/4/17.
 */
public class TestTest extends TestCase{

//    @Test
//    public void testLogin() throws Exception {
//        String account = "18380586504";
//        String pwd = "123456";
//        String urlStr = "http://192.168.1.104:9090/account/login?account="+account+"&pwd="+pwd;
//        RequestUtil.reqeustGet(urlStr);
//    }


//    @Test
//    public void testRegister() throws Exception {
//        String account = "18380586504";
//        String pwd = "123456";
//        String urlStr = "http://192.168.1.104:9090/account/register?account="+account+"&pwd="+pwd;
//        RequestUtil.reqeustGet(urlStr);
//    }

    @Test @Ignore
    public void testModify() throws Exception {
        String account = "18380586504";
        String pwd = "123456";
        String name = "张三";
        String urlStr = "http://192.168.1.104:8888/patient/modifyPatientInfo?account="+account+"&pwd="+pwd+"&realName="+name;
        RequestUtil.reqeustGet(urlStr);
    }

    @Test @Ignore
    public void testModify2() throws Exception {
        String account = "18380586504";
        String pwd = "123456";
        String urlStr = "http://192.168.1.104:8888/patient/modifyPatientInfo?account="+account+"&pwd="+pwd;
        Map<String,String> requetBody = new HashMap<>();
        requetBody.put("account",account);
        requetBody.put("realName","张三");
        RequestUtil.reqeustParam(urlStr,requetBody);
    }

    @Test
    public void testGetDoctor() throws Exception {
        String doctorId = "10000000003";
        String urlStr = "http://120.24.183.87:8888/doctor/queryDoctorByDoctorAccount?account="+doctorId;
        RequestUtil.reqeustGet(urlStr);
    }


}