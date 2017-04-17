package com.zyl.hospital.test;


import junit.framework.TestCase;

import org.junit.Test;

/**
 * Created by zhangyinglong on 2017/4/17.
 */
public class TestTest extends TestCase{

    @Test
    public void testLogin() throws Exception {
        String account = "18380586504";
        String pwd = "123456";
        String urlStr = "http://192.168.1.102:9090/account/login?account="+account+"&pwd="+pwd;
        RequestUtil.reqeustGet(urlStr);
    }


    @Test
    public void testRegister() throws Exception {
        String account = "18380586504";
        String pwd = "123456";
        String urlStr = "http://192.168.1.102:9090/account/register?account="+account+"&pwd="+pwd;
        RequestUtil.reqeustGet(urlStr);
    }

}