package com.tmd.systemtools;

import android.content.Context;
import android.util.Log;

public class PrintLog {
	public static void print(Context con, String msg) {
		Log.e(con.getApplicationInfo() + ">>" + con.getClass().getSimpleName(), msg);
	}

	public static void printC(Class con, String msg) {
		Log.e(con.getPackage().getName() + ">>" + con.getClass().getSimpleName(), msg);
	}
}
