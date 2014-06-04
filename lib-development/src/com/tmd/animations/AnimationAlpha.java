package com.tmd.animations;


import android.os.Build;
import android.view.View;
import com.nineoldandroids.view.ViewHelper;





public class AnimationAlpha{
	/**
	 * 
	 * @param v View
	 * @param alpha 0.0f - 1.0f
	 */
	public static void Run(View v,float alpha){
		if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ) {
			v.setAlpha(alpha);
		} else {
			ViewHelper.setAlpha(v,alpha);
		}
	}
}
