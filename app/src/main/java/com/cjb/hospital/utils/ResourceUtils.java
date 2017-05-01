package com.cjb.hospital.utils;

import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;

import com.cjb.hospital.App;

/**
 * Created by zhangyinglong on 2017/1/10.
 */
public class ResourceUtils {

    public static int getColor(@ColorRes int resId){
        return ContextCompat.getColor(App.getInstance(),resId);
    }

    public static Drawable getDrawable(@DrawableRes int resId){
        return ContextCompat.getDrawable(App.getInstance(),resId);
    }

    public static String getString(@StringRes int resId){
        return App.getInstance().getString(resId);
    }
}
