package com.wangjf.myutils;

import android.util.Log;

/**
 * Created by wangjf on 18-1-20.
 */

public class MyLogUtils {
    final static String TAG = "WJF";
    final static boolean LOG_E = true;
    final static boolean LOG_I = true;
    final static boolean LOG_D = true;
    final static boolean LOG_V = true;
    final static boolean LOG_W = true;

    private MyLogUtils(){
        /* Protect from instantiations */
    }

    public static void e(String message){
        if (!LOG_E)return;

        Log.e(TAG,message);
    }


    public static void i(String message){
        if (!LOG_I)
            return;

        Log.i(TAG, message);
    }

    public static void d(String message){
        if (!LOG_D)
            return;

        Log.d(TAG, getCallPrefix() + message);
    }

    //获取调用者的类名和行号
    public static String getCallPrefix() {
        StackTraceElement stack[] = (new Throwable()).getStackTrace();
        /*
        for (int i = 0; i < stack.length; i++)
        {
            StackTraceElement s = stack[i];
            System.out.format(" ClassName:%d\t%s\n", i, s.getClassName());
            System.out.format("MethodName:%d\t%s\n", i, s.getMethodName());
            System.out.format("  FileName:%d\t%s\n", i, s.getFileName());
            System.out.format("LineNumber:%d\t%s\n\n", i, s.getLineNumber());
        }
        */

        String prefix = stack[2].getClassName() + "::" + stack[2].getMethodName() + "(" +
                stack[2].getLineNumber() + ") ";

        return prefix;
    }

    public static void v(String message){
        if (!LOG_V)
            return;

        Log.v(TAG, message);
    }

    public static void w(String message){
        if (!LOG_W)
            return;

        Log.w(TAG, message);
    }

}
