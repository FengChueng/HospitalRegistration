package com.zyl.hospital.registration.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by cjb
 */
public class RouterUtils {

    public static void gotoNextNoData(Context context,Class<?> clz){
        Intent intent = new Intent(context,clz);
        context.startActivity(intent);
    }
    public static void gotoNext(Context context, Class<?> clz, Bundle bundle){
        Intent intent = new Intent(context,clz);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
