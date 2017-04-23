package com.zyl.hospital.registration.utils;

import android.content.Context;
import android.widget.Toast;

import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperToast;
import com.zyl.hospital.registration.R;

/**
 * Created by zhouyou on 2016/6/28.
 * Class desc: 单例土司
 */
public class ToastUtils {

    public static Toast mToast;

    /**
     * 显示吐司
     * @param context
     * @param message
     */
    public static void show(final Context context, final String message){
        if (mToast == null){
            mToast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        }else{
            mToast.setText(message);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    /**
     * 显示吐司
     * @param context
     * @param messageResId
     */
    public static void show(final Context context, final int messageResId){
        if (mToast == null){
            mToast = Toast.makeText(context, messageResId, Toast.LENGTH_SHORT);
        }else{
            mToast.setText(messageResId);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    public static void showMetrailToast(Context context,String msg){
        new SuperToast(context)
                .setText(msg)
                .setDuration(Style.DURATION_LONG)
                .setColor(ResourceUtils.getColor(R.color.load_red))
                .setAnimations(Style.ANIMATIONS_FLY)
                .show();
    }


}
