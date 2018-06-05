package com.lecheng.hello.thirdapp.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Cheng on 2016/8/15.lecheng复写
 */

public class MySP {

    private static final String FILE_NAME = "save_file_name";        //存储的sharedpreferences文件名

    /**
     * 保存数据到文件
     *
     * @param context
     * @param key
     * @param data
     */
    public static void saveData(Context context, String key, Object data) {

        String type = data.getClass().getSimpleName();
        SharedPreferences sharedPreferences = context
                .getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if ("Integer".equals(type)) {
            editor.putInt(key, (Integer) data);
        } else if ("Boolean".equals(type)) {
            editor.putBoolean(key, (Boolean) data);
        } else if ("String".equals(type)) {
            editor.putString(key, (String) data);
        } else if ("Float".equals(type)) {
            editor.putFloat(key, (Float) data);
        } else if ("Long".equals(type)) {
            editor.putLong(key, (Long) data);
        }

        editor.commit();
    }

    /**
     * 从文件中读取数据
     *
     * @param context
     * @param key
     * @param defValue
     * @return //
     */
//    public static Object loadData(Context context, String key, Object defValue) {
//
//        String type = defValue.getClass().getSimpleName();
//        SharedPreferences sharedPreferences = context.getSharedPreferences
//                (FILE_NAME, Context.MODE_PRIVATE);
//
//        //defValue为为默认值，如果当前获取不到数据就返回它
//        if ("Integer".equals(type)) {
//            return sharedPreferences.getInt(key, (Integer) defValue);
//        } else if ("Boolean".equals(type)) {
//            return sharedPreferences.getBoolean(key, (Boolean) defValue);
//        } else if ("String".equals(type)) {
//            return sharedPreferences.getString(key, (String) defValue);
//        } else if ("Float".equals(type)) {
//            return sharedPreferences.getFloat(key, (Float) defValue);
//        } else if ("Long".equals(type)) {
//            return sharedPreferences.getLong(key, (Long) defValue);
//        }
//        return null;
//    }
    public static String loadData(Context context, String key, String defValue) {

//        String type = defValue.getClass().getSimpleName();
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);

        //defValue为为默认值，如果当前获取不到数据就返回它
//        if ("Integer".equals(type)) {
//            return sharedPreferences.getInt(key, (Integer) defValue);
//        } else if ("Boolean".equals(type)) {
//            return sharedPreferences.getBoolean(key, (Boolean) defValue);
//        } else if ("String".equals(type)) {
        try {
            return sharedPreferences.getString(key, (String) defValue);
        } catch (Exception e) {
            e.printStackTrace();
            return defValue;
        }

//        } else if ("Float".equals(type)) {
//            return sharedPreferences.getFloat(key, (Float) defValue);
//        } else if ("Long".equals(type)) {
//            return sharedPreferences.getLong(key, (Long) defValue);
//        }

    }

    public static int loadData(Context context, String key, int defValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        try {
            return sharedPreferences.getInt(key, defValue);
        } catch (Exception e) {
            e.printStackTrace();
            return defValue;
        }
    }

    public static Float loadData(Context context, String key, Float defValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        try {
            return sharedPreferences.getFloat(key, defValue);
        } catch (Exception e) {
            e.printStackTrace();
            return defValue;
        }
    }

    public static Long loadData(Context context, String key, Long defValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        try {
            return sharedPreferences.getLong(key, defValue);
        } catch (Exception e) {
            e.printStackTrace();
            return defValue;
        }
    }

    public static boolean loadData(Context context, String key, boolean defValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        try {
            return sharedPreferences.getBoolean(key, defValue);
        } catch (Exception e) {
            e.printStackTrace();
            return defValue;
        }
    }
}
