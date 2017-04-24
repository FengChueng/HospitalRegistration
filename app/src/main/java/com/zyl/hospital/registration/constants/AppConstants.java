package com.zyl.hospital.registration.constants;

/**
 * Created by zhouyou on 2016/6/22.
 * Class desc:
 *
 * APP 常量类，用于存放APP中用到的常量。
 */
public interface AppConstants {


    String CONTENT_TYPE_FILE = "multipart/form-data";

    String CONTENT_TYPE_JPG = "image/jpg";

    String CONTENT_TYPE_PNG = "image/png";

    String CONTENT_TYPE_TEXT = "text/plain";


    String KEY_USER_JSON = "userJson";
    // SharedPreferences key Name
    String KEY_IS_FIRST_LOGIN = "is_first_login";

    String KEY_IS_LOGIN = "is_login";

    // Extra
    String EXTRA_TITLE        = "title";
    String EXTRA_URL          = "url";
    String KEY_USER_OBJ = "user";
    String KEY_ROLE = "role";
    int NEWS_FRAGMENT = 0;
    int APPOINTMENT_FRAGMENT = 1;
    int APPOINTMENT_MANAGER_FRAGMENT = 2;
    int PERSONAL_FRAGMENT = 3;
    //是否需要登录
    String KEY_IS_NEED_LOGIN = "isneedlogin";
    String KEY_HOSPITAL_ID = "hospitalId";
    String KEY_DEPT_ID = "deptId";
    String KEY_DEPT_NAME = "deptName";
}
