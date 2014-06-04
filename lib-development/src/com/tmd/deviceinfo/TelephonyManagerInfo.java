package com.tmd.deviceinfo;

import android.content.Context;
import android.telephony.TelephonyManager;

public class TelephonyManagerInfo {
	public static String getIMIE(Context context) {
		TelephonyManager mngr = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
		return mngr.getDeviceId();
	}
}
