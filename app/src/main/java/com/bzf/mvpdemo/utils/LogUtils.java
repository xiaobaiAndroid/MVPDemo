package com.bzf.mvpdemo.utils;

import android.util.Log;

/**
 * 日志类
 * @author baizhengfu
 * @version 1.0
 * @since JDK1.8
 * @date 2018/10/23
 */
public class LogUtils {

    private static String TAG = LogUtils.class.getName();

    public static void logI(String message){
        Log.i(TAG,message);
    }

}
