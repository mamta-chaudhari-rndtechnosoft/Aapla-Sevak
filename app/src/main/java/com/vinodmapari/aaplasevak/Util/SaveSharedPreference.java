package com.vinodmapari.aaplasevak.Util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SaveSharedPreference {

    static final String PREF_USER_ID = "id";
    static final String PREF_USER_NAME = "username";
    static final String PREF_USER_MOBILE = "mobile";

    static final String PREF_USER_ROLE = "role";

    static SharedPreferences getSharedPreferences(Context ctx){
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }


    public static void setPrefUserId(Context ctx, String userId){

        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_ID,userId);
        editor.apply();
    }

    public static String getUserId(Context ctx){
        return getSharedPreferences(ctx).getString(PREF_USER_ID,"");
        //return getSharedPreferences(ctx).getInt(PREF_USER_ID,15);
    }

    public static void clearUserId(Context ctx){
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.remove(PREF_USER_ID);
        editor.apply();
    }


    public static void setPrefUserName(Context ctx, String userName){

        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_NAME,userName);
        editor.apply();
    }

    public static String getUserName(Context ctx){
        return getSharedPreferences(ctx).getString(PREF_USER_NAME,"");
    }

    public static void clearUserName(Context ctx){
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.remove(PREF_USER_NAME);
        editor.apply();
    }

    public static void setPrefUserMobile(Context ctx, String userMobile){

        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        //String userMobileString = String.valueOf(userMobile);
        //editor.putString(PREF_USER_MOBILE, userMobileString);
        editor.putString(PREF_USER_MOBILE, userMobile);
        editor.apply();
    }

    public static String getUserNumber(Context ctx){
        return getSharedPreferences(ctx).getString(PREF_USER_MOBILE,"");
    }

    public static void clearUserNumber(Context ctx){
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.remove(PREF_USER_MOBILE);
        editor.apply();
    }

    public static void setUserRole(Context ctx, String userRole){
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_ROLE,userRole);
        editor.apply();
    }

    public static String getUserRole(Context ctx){
        return  getSharedPreferences(ctx).getString(PREF_USER_ROLE,"");
    }

    public static void clearUserRole(Context ctx){
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.remove(PREF_USER_ROLE);
        editor.apply();
    }
}
