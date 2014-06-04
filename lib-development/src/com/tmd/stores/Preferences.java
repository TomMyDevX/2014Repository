package com.tmd.stores;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {

	private SharedPreferences	mSharedPreferences;

	public Preferences(Context context) {
		mSharedPreferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
	}

	public void setInstalledBundledData(boolean installed) {
		mSharedPreferences.edit().putBoolean("installed_bundled_data20141702", installed).commit();
	}

	public boolean getInstalledBundledData() {
		return mSharedPreferences.getBoolean("installed_bundled_data20141702", false);
	}

	@SuppressWarnings({ "rawtypes" })
	public Object getPreferences(String key, Class class1) {
		Object value = null;
		if (Boolean.class == class1) {
			value = mSharedPreferences.getBoolean(key, false);
		}
		else if (Long.class == class1) {
			value = mSharedPreferences.getLong(key, -1);
		}
		else if (Integer.class == class1) {
			value = mSharedPreferences.getLong(key, -1);
		}
		return value;
	}

	@SuppressWarnings({ "rawtypes" })
	public void setPreferences(String key, Class class1, Object value) {
		if (Boolean.class == class1) {
			mSharedPreferences.edit().putBoolean(key, (Boolean) value).commit();
		}
		else if (Long.class == class1) {
			mSharedPreferences.edit().putLong(key, (Long) value).commit();
		}
		else if (Integer.class == class1) {
			mSharedPreferences.edit().putLong(key, (Integer) value).commit();
		}

	}
}
