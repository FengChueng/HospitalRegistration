package com.zyl.hospital.registration.utils.encrypt.code;

/**
 * 编解码
 *
 * @author keven
 * @data 16/5/8 下午11:06
 * 
 */

public class BASE64 {
    /***
     * BASE64编码
     *
     * @param data 待编码的数据
     * @return 编码后的数据
     */
    public static String base64encoder(String data) {
        byte[] b = android.util.Base64.encode(data.getBytes(), android.util.Base64.DEFAULT);
        return new String(b);
    }

    /**
     * Base64解码
     *
     * @param data 待解码的数据
     * @return 解码后的数据
     */
    public static String base64decode(String data) {
        byte[] b = android.util.Base64.decode(data.getBytes(), android.util.Base64.DEFAULT);
        return new String(b);
    }
}
