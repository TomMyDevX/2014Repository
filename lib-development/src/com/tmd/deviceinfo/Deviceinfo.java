package com.tmd.deviceinfo;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.provider.Settings.SettingNotFoundException;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.WindowManager;

public class Deviceinfo {
	public static String	BUILD_OS_VERSION	= System.getProperty("os.version"); // OS version
	public static int		BUILD_SDK_VERSION	= android.os.Build.VERSION.SDK_INT; // API Level
	public static String	BUILD_DEVICE		= android.os.Build.DEVICE;			// Device
	public static String	BUILD_MODEL			= android.os.Build.MODEL;			// Model
	public static String	BUILD_PRODUCT		= android.os.Build.PRODUCT;		// Product
	public static String	BUILD_BOARD			= android.os.Build.BOARD;			// Product
	public static String	BUILD_ID			= android.os.Build.ID;

	public static int[] getDeviceScreenSize(Activity activity) {
		DisplayMetrics displaymetrics = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		int height = displaymetrics.heightPixels;
		int width = displaymetrics.widthPixels;
		return new int[] { width, height };
	}

	public static int getDeviceScreenDpi(Activity activity) {
		DisplayMetrics metrics = activity.getResources().getDisplayMetrics();
		return metrics.densityDpi;
	}

	public static int getDeviceScreenBrightness(Activity activity) throws SettingNotFoundException {
		int curBrightnessValue = android.provider.Settings.System.getInt(activity.getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS);
		return curBrightnessValue;
	}

	public static void setDeviceScreenBrightness(Activity activity, int curBrightnessValue) {
		WindowManager.LayoutParams layoutParams = activity.getWindow().getAttributes();
		layoutParams.screenBrightness = curBrightnessValue / 100.0f;
		activity.getWindow().setAttributes(layoutParams);
	}

	public static String getDeviceScreenOrentation(Activity activity) {
		Display display = ((WindowManager) activity.getSystemService(activity.getApplicationContext().WINDOW_SERVICE)).getDefaultDisplay();
		int orientation = display.getOrientation();
		switch (orientation) {
			case Surface.ROTATION_0:
				return "portrait";
			case Surface.ROTATION_90:
				return "landscape";
			case Surface.ROTATION_180:
				return "reverse_portrait";
			default:
				return "reverse_landscape";
		}
	}

	public static boolean getFlashAvailableOnDevice(Activity activity) {
		if (activity.getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
			return true;
		}
		else {
			return false;
		}
	}

	public static int getStatusBarHeight(Activity ac) {
		int result = 0;
		int resourceId = ac.getResources().getIdentifier("status_bar_height", "dimen", "android");
		if (resourceId > 0) {
			result = ac.getResources().getDimensionPixelSize(resourceId);
		}
		return result;
	}

	public void showDeviceInfo(int log) {
		if (Log.ERROR == log) {
			Log.e("BUILD_OS_VERSION", BUILD_OS_VERSION);
			Log.e("BUILD_SDK_VERSION", BUILD_SDK_VERSION + "");
			Log.e("BUILD_DEVICE", BUILD_DEVICE);
			Log.e("BUILD_MODEL", BUILD_MODEL);
			Log.e("BUILD_PRODUCT", BUILD_PRODUCT);
			Log.e("BUILD_BOARD", BUILD_BOARD);
			Log.e("BUILD_ID", BUILD_ID);
		}
		else if (Log.DEBUG == log) {
			Log.e("BUILD_OS_VERSION", BUILD_OS_VERSION);
			Log.e("BUILD_SDK_VERSION", BUILD_SDK_VERSION + "");
			Log.e("BUILD_DEVICE", BUILD_DEVICE);
			Log.e("BUILD_MODEL", BUILD_MODEL);
			Log.e("BUILD_PRODUCT", BUILD_PRODUCT);
			Log.e("BUILD_BOARD", BUILD_BOARD);
			Log.e("BUILD_ID", BUILD_ID);
		}
	}
}
