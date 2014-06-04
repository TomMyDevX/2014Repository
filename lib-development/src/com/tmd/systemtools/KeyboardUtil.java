package com.tmd.systemtools;


import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;





public class KeyboardUtil{
	public static void hideKeyboard(Activity activity,EditText myEditText){
		try {
			InputMethodManager immx=(InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
			immx.hideSoftInputFromWindow(myEditText.getWindowToken(),0);
			InputMethodManager imm=(InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
			if ( imm != null ) {
				imm.toggleSoftInput(0,InputMethodManager.HIDE_NOT_ALWAYS);
			}
		}
		catch (Exception e) {
			// Ignore exceptions if any
			Log.e("KeyBoardUtil",e.toString(),e);
		}
	}
}