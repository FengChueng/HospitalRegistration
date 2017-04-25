package com.zyl.hospital.registration.bean;

/**
 * Created by zhangyinglong on 2017/2/21.
 */

public class ResponseEntity<BaseBean> {
    /** 状态码 ApiConstant.REQUEST_SUCCESS/ApiConstant.REQUEST_FAIL */
    public int status;
    /** 返回信息*/
    public String msg;
    /** 返回数据*/
    public BaseBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public BaseBean getData() {
        return data;
    }

    public void setData(BaseBean data) {
        this.data = data;
    }
}
