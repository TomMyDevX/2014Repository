package com.tmd.libdev;
import android.app.Activity;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tmd.androidlibs.ScreenCalulator;
import com.tmd.androidlibs.ScreenUtility;
public class ToolScreenUtility extends ScreenUtility{
	public ToolScreenUtility(Activity activity) {
		super(activity);
	}
	/**
	 * 
	 * 
	 * @deprecated use {@link setViewPosition(View viewObject, Float Width, Float Height, Float LeftMargin, Float TopMargin)} instead.
	 */
	@Deprecated
	@Override
	public View setDynamicInRelativeLayout(RelativeLayout parentRelativeLayout, int drawableID, Float Width, Float Height, Float LeftMargin, Float TopMargin) {
		return super.setDynamicInRelativeLayout(parentRelativeLayout, drawableID, Width, Height, LeftMargin, TopMargin);
	}
	public View setViewInRelativeLayout(View viewObject, Float Width, Float Height, Float LeftMargin, Float TopMargin) {
		RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) viewObject.getLayoutParams();
		layoutParams.leftMargin = convertFloatingToPixle(ScreenCalulator.Width, LeftMargin);
		layoutParams.topMargin = convertFloatingToPixle(ScreenCalulator.Height, TopMargin);
		layoutParams.width = convertFloatingToPixle(ScreenCalulator.Width, Width);
		layoutParams.height = convertFloatingToPixle(ScreenCalulator.Height, Height);
		viewObject.setLayoutParams(layoutParams);
		return viewObject;
	}
	/**
	 * 
	 * 
	 * @deprecated use {@link addViewInRelativeLayout(RelativeLayout parentRelativeLayout, View viewObject, float width, float height, float leftMargin, float topMargin)} instead.
	 */
	@Override
	public void setDynamicInRelativeLayoutsWithOldView(RelativeLayout parentRelativeLayout, Object viewObj, Class classType, float width, float height, float leftMargin, float topMargin) {
		super.setDynamicInRelativeLayoutsWithOldView(parentRelativeLayout, viewObj, classType, width, height, leftMargin, topMargin);
	}
	/**
	 * 
	 * 
	 * @deprecated use {@link addViewInRelativeLayout(RelativeLayout parentRelativeLayout, View viewObject, float width, float height, float leftMargin, float topMargin)} instead.
	 */
	@Override
	public void setDynamicInRelativeLayoutsWithOldView(RelativeLayout parentRelativeLayout, Object viewObj, Class classType, float width, float height, float leftMargin, float topMargin, boolean isAddView) {
		super.setDynamicInRelativeLayoutsWithOldView(parentRelativeLayout, viewObj, classType, width, height, leftMargin, topMargin, isAddView);
	}
	/**
	 * 
	 * 
	 * @deprecated instead.
	 */
	@Override
	public View setDynamicInRelativeLayoutWithNotification(RelativeLayout parentRelativeLayout, int drawableID, Float Width, Float Height, Float LeftMargin, Float TopMargin, ScaleType scaleType) {
		// TODO Auto-generated method stub
		return super.setDynamicInRelativeLayoutWithNotification(parentRelativeLayout, drawableID, Width, Height, LeftMargin, TopMargin, scaleType);
	}
	/**
	 * 
	 * 
	 * @deprecated instead.
	 */
	@Override
	public TextView setDynamicInRelativeLayouts(RelativeLayout parentRelativeLayout, int name, float width, float height, float leftMargin, float topMargin) {
		// TODO Auto-generated method stub
		return super.setDynamicInRelativeLayouts(parentRelativeLayout, name, width, height, leftMargin, topMargin);
	}
	/**
	 * 
	 * 
	 * @deprecated instead.
	 */
	@Override
	public View setDynamicInRelativeLayoutCircleImageView(RelativeLayout parentRelativeLayout, Bitmap bm, Float Width, Float Height, Float LeftMargin, Float TopMargin) {
		// TODO Auto-generated method stub
		return super.setDynamicInRelativeLayoutCircleImageView(parentRelativeLayout, bm, Width, Height, LeftMargin, TopMargin);
	}
	/**
	 * 
	 * 
	 * @deprecated instead.
	 */
	@Override
	public void setDynamicInRelativeLayout(View viewObject, Float Width, Float Height, Float LeftMargin, Float TopMargin) {
		// TODO Auto-generated method stub
		super.setDynamicInRelativeLayout(viewObject, Width, Height, LeftMargin, TopMargin);
	}
	/**
	 * 
	 * 
	 * @deprecated instead.
	 */
	@Override
	public View setDynamicInRelativeLayout(RelativeLayout parentRelativeLayout, int drawableID, Float Width, Float Height, Float LeftMargin, Float TopMargin, ScaleType scaleType) {
		// TODO Auto-generated method stub
		return super.setDynamicInRelativeLayout(parentRelativeLayout, drawableID, Width, Height, LeftMargin, TopMargin, scaleType);
	}
	public void addViewInRelativeLayout(RelativeLayout parentRelativeLayout, View viewObject, float width, float height, float leftMargin, float topMargin) {
		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(20, 20);
		layoutParams.leftMargin = convertFloatingToPixle(ScreenCalulator.Width, leftMargin);
		layoutParams.topMargin = convertFloatingToPixle(ScreenCalulator.Height, topMargin);
		layoutParams.width = convertFloatingToPixle(ScreenCalulator.Width, width);
		layoutParams.height = convertFloatingToPixle(ScreenCalulator.Height, height);
		parentRelativeLayout.addView(viewObject, layoutParams);
	}
}
