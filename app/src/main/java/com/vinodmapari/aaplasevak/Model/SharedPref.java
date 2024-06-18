package com.vinodmapari.aaplasevak.Model;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.vinodmapari.aaplasevak.BuildConfig;

import java.util.HashMap;

public class SharedPref {

    static SharedPreferences shPreferences;
	public static String strPrefName = BuildConfig.APPLICATION_ID;
	public static String USER_ID = "userid";

	public static String USER_NAME = "username";

	public static String MIN_MSG = "min_msg";
	public static String WALLET = "wallet";

	public static String DASHED = "dashed";
	public static String APP_STATUS = "app_status";

	Editor editor;
	Context context;
	public static final String SESSION_REMEMBERME="rememberMe";

	public static final String IS_REMEMBERME = "IsRemeberMe" ;

	public static final String Password = "passwordKey";

    public static void clearAllPreferences(Activity activity) {
		shPreferences = activity.getSharedPreferences(strPrefName,
				Context.MODE_PRIVATE);

		Editor editor = shPreferences.edit();
		editor.clear();
		editor.apply(); // important! Don't forget!
	}

	public static String getPreference(String key, String Default,
									   Activity activity) {
		shPreferences = activity.getSharedPreferences(strPrefName,
				Context.MODE_PRIVATE);
		return shPreferences.getString(key, Default);
	}

    public static boolean setPreference(String key, String value,
                                        Activity activity) {
		if (value != null) {
			shPreferences = activity.getSharedPreferences(strPrefName,
					Context.MODE_PRIVATE);
			Editor editor = shPreferences.edit();
			editor.putString(key, value);
			editor.apply();
			return true;
		}
		return false;
	}

    public static String getUserName(Context activity) {

		shPreferences = activity.getSharedPreferences(strPrefName,
				Context.MODE_PRIVATE);
		return shPreferences.getString(USER_NAME, "");
	}



	public static String getUserId(Context activity) {

		shPreferences = activity.getSharedPreferences(strPrefName,
				Context.MODE_PRIVATE);
		return shPreferences.getString(USER_ID, "");
	}



	public static String getWallet(Context activity) {
		shPreferences = activity.getSharedPreferences(strPrefName,
				Context.MODE_PRIVATE);
		return shPreferences.getString(WALLET, "");
	}

	public static String getMinMsg(Context activity) {
		shPreferences = activity.getSharedPreferences(strPrefName,
				Context.MODE_PRIVATE);
		return shPreferences.getString(MIN_MSG, "");
	}



	public static String getDASHED(Context activity) {
		shPreferences = activity.getSharedPreferences(strPrefName,
				Context.MODE_PRIVATE);
		return shPreferences.getString(DASHED, "");
	}

	public static String getAppStatus(Context activity) {
		shPreferences = activity.getSharedPreferences(strPrefName,
				Context.MODE_PRIVATE);
		return shPreferences.getString(APP_STATUS, "");
	}

	public void createRememberMeSession(String email,String password){
		editor.putBoolean(IS_REMEMBERME,true);

		editor.putString(USER_NAME,email);
		editor.putString(Password,password);

		editor.commit();

	}

	public HashMap<String,String> getRememberMeDetailsFromSession(){
		HashMap<String,String> userdata=new HashMap<>();

		userdata.put(USER_NAME,shPreferences.getString(USER_NAME,null));
		userdata.put(Password,shPreferences.getString(Password,null));

		return userdata;
	}

	public boolean checkRememberMe(){
		if (shPreferences.getBoolean(IS_REMEMBERME,false)){
			return true;
		}
		else
			return false;

	}

}
