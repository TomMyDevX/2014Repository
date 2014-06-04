package com.tmd.androidlibs;
/**
 * 
 * About
 * -----
 * 
 * A utility class for dynamically setting the height and width of screen objects in android,
 * based on the current screen dimensions.
 * 
 * Also has some methods for grabbing screen data, such as the height and width in pixels.
 * 
 * Android devices come in all shapes and sizes, so this class has helped me make my android
 * applications more "responsive" based on the device they are running on.
 * 
 * Hopefully this will help you set up your screen objects across devices too.
 * 
 * I wouldn't recommend using this utility class for bigger devices like tablets, as the objects
 * just end up looking gigantic if you're only using a few of them on screen at a time.
 * 
 * Legal stuff
 * -----------
 * 
 * Copyright (c) Paul Hallett 2012
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>
 * 
 * @author Paul Hallett www.phalt.co.uk
 */
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tmd.customview.CircleImageView;
import com.tmd.deviceinfo.Deviceinfo;
public class ScreenUtility{
	private Activity	_activity;
	private int			_height;
	private int			_width;
	/**
	 * Default constructor
	 * 
	 * @param activity
	 *            The activity the objects are in.
	 */
	public ScreenUtility(Activity activity) {
		try{
			setActivity(activity);
			discoverActivityScreenSize();
		} catch (Exception e){
			Log.e("ScreenUtility.java", e.getMessage());
		}
	}
	/*
	 * ========================================================================
	 * Public functions
	 * ========================================================================
	 */
	/**
	 * Sets a view object height and width to a supplied percentage of the
	 * device screen.
	 * 
	 * @param heightPercentage
	 *            The desired height in percentage (between 1 and 100).
	 * @param widthPercentage
	 *            The desired width in percentage (between 1 and 100).
	 * @param viewObject
	 *            The view object that you are setting the dimensions for.
	 */
	public void setViewObjectDimensionsAsPercentage(int heightPercentage, int widthPercentage, View viewObject) {
		LayoutParams layout = viewObject.getLayoutParams();
		setDynamicDimensions(layout, heightPercentage, widthPercentage);
	}
	/**
	 * setDynamicInRelativeLayout Floating of the device screen.
	 * 
	 * @param viewObject
	 *            The view object that you are setting the dimensions for.
	 * @param Width
	 *            Floating size width of the device screen.
	 * @param Height
	 *            Floating size height of the device screen.
	 * @param LeftMargin
	 *            Floating left margin of the device screen.
	 * @param TopMargin
	 *            Floating top margin of the device screen.
	 */
	public void setDynamicInRelativeLayout(View viewObject, Float Width, Float Height, Float LeftMargin, Float TopMargin) {
		RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) viewObject.getLayoutParams();
		layoutParams.leftMargin = convertFloatingToPixle(ScreenCalulator.Width, LeftMargin);
		layoutParams.topMargin = convertFloatingToPixle(ScreenCalulator.Height, TopMargin);
		layoutParams.width = convertFloatingToPixle(ScreenCalulator.Width, Width);
		layoutParams.height = convertFloatingToPixle(ScreenCalulator.Height, Height);
	}
	/**
	 * setDynamicInRelativeLayout Floating of the device screen.
	 * 
	 * @param RelativeLayout
	 *            parentRelativeLayout.
	 * @param drawableID
	 *            drawable resource.
	 * @param Width
	 *            Floating size width of the device screen.
	 * @param Height
	 *            Floating size height of the device screen.
	 * @param LeftMargin
	 *            Floating left margin of the device screen.
	 * @param TopMargin
	 *            Floating top margin of the device screen.
	 * @return View
	 */
	public View setDynamicInRelativeLayout(RelativeLayout parentRelativeLayout, int drawableID, Float Width, Float Height, Float LeftMargin, Float TopMargin) {
		ImageView viewObject = new ImageView(_activity);
		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(20, 20);
		layoutParams.leftMargin = convertFloatingToPixle(ScreenCalulator.Width, LeftMargin);
		layoutParams.topMargin = convertFloatingToPixle(ScreenCalulator.Height, TopMargin);
		layoutParams.width = convertFloatingToPixle(ScreenCalulator.Width, Width);
		layoutParams.height = convertFloatingToPixle(ScreenCalulator.Height, Height);
		parentRelativeLayout.addView(viewObject, layoutParams);
		viewObject.setImageResource(drawableID);
		viewObject.setScaleType(ScaleType.FIT_XY);
		return viewObject;
	}
	/**
	 * setDynamicInRelativeLayout Floating of the device screen.
	 * 
	 * @param parentRelativeLayout
	 *            parentRelativeLayout.
	 * @param drawableID
	 *            drawable resource.
	 * @param Width
	 *            Floating size width of the device screen.
	 * @param Height
	 *            Floating size height of the device screen.
	 * @param LeftMargin
	 *            Floating left margin of the device screen.
	 * @param TopMargin
	 *            Floating top margin of the device screen.
	 * @param scaleType
	 *            ScaleType Class
	 * @return View
	 */
	public View setDynamicInRelativeLayout(RelativeLayout parentRelativeLayout, int drawableID, Float Width, Float Height, Float LeftMargin, Float TopMargin, ScaleType scaleType) {
		ImageView viewObject = new ImageView(_activity);
		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(20, 20);
		layoutParams.leftMargin = convertFloatingToPixle(ScreenCalulator.Width, LeftMargin);
		layoutParams.topMargin = convertFloatingToPixle(ScreenCalulator.Height, TopMargin);
		layoutParams.width = convertFloatingToPixle(ScreenCalulator.Width, Width);
		layoutParams.height = convertFloatingToPixle(ScreenCalulator.Height, Height);
		parentRelativeLayout.addView(viewObject, layoutParams);
		viewObject.setImageResource(drawableID);
		viewObject.setScaleType(scaleType);
		viewObject.setAdjustViewBounds(true);
		return viewObject;
	}
	/**
	 * setDynamicInRelativeLayout Floating of the device screen.
	 * 
	 * @param parentRelativeLayout
	 *            parentRelativeLayout.
	 * @param drawableID
	 *            drawable resource.
	 * @param Width
	 *            Floating size width of the device screen.
	 * @param Height
	 *            Floating size height of the device screen.
	 * @param LeftMargin
	 *            Floating left margin of the device screen.
	 * @param TopMargin
	 *            Floating top margin of the device screen.
	 * @param scaleType
	 *            ScaleType Class
	 * @return View
	 */
	public View setDynamicInRelativeLayoutWithNotification(RelativeLayout parentRelativeLayout, int drawableID, Float Width, Float Height, Float LeftMargin, Float TopMargin, ScaleType scaleType) {
		NotifyImageView viewObject = new NotifyImageView(_activity);
		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(20, 20);
		layoutParams.leftMargin = convertFloatingToPixle(ScreenCalulator.Width, LeftMargin);
		layoutParams.topMargin = convertFloatingToPixle(ScreenCalulator.Height, TopMargin);
		layoutParams.width = convertFloatingToPixle(ScreenCalulator.Width, Width);
		layoutParams.height = convertFloatingToPixle(ScreenCalulator.Height, Height);
		parentRelativeLayout.addView(viewObject, layoutParams);
		viewObject.setImageResource(drawableID);
		viewObject.setScaleType(scaleType);
		viewObject.setAdjustViewBounds(true);
		return viewObject;
	}
	/**
	 * setDynamicInRelativeLayout Floating of the device screen.
	 * 
	 * @param parentRelativeLayout
	 *            parentRelativeLayout.
	 * @param drawableID
	 *            drawable resource.
	 * @param Width
	 *            Floating size width of the device screen.
	 * @param Height
	 *            Floating size height of the device screen.
	 * @param LeftMargin
	 *            Floating left margin of the device screen.
	 * @param TopMargin
	 *            Floating top margin of the device screen.
	 * @param scaleType
	 *            ScaleType Class
	 * @return View
	 */
	// public View setDynamicViewFilperInRelativeLayout(RelativeLayout
	// parentRelativeLayout, Float Width, Float Height, Float LeftMargin, Float
	// TopMargin) {
	// FlipViewController viewObject = new FlipViewController(_activity,
	// FlipViewController.HORIZONTAL);
	// RelativeLayout.LayoutParams layoutParams = new
	// RelativeLayout.LayoutParams(20, 20);
	// layoutParams.leftMargin = convertFloatingToPixle(ScreenCalulator.Width,
	// LeftMargin);
	// layoutParams.topMargin = convertFloatingToPixle(ScreenCalulator.Height,
	// TopMargin);
	// layoutParams.width = convertFloatingToPixle(ScreenCalulator.Width,
	// Width);
	// layoutParams.height = convertFloatingToPixle(ScreenCalulator.Height,
	// Height);
	// parentRelativeLayout.addView(viewObject, layoutParams);
	// return viewObject;
	// }
	/**
	 * convertFloatingToPixle Floating of the device screen.
	 * 
	 * @param widthCalulator
	 *            enum ScreenCalulator[Width,Height]
	 * @param floatVal
	 *            Floating of the device screen.
	 */
	public int convertFloatingToPixle(ScreenCalulator widthCalulator, Float floatVal) {
		if (widthCalulator == ScreenCalulator.Width){
			return (int) (getScreenWidth() * floatVal);
		} else if (widthCalulator == ScreenCalulator.Height){
			boolean fullScreen = (_activity.getWindow().getAttributes().flags & WindowManager.LayoutParams.FLAG_FULLSCREEN) != 0;
			if (fullScreen){
				return (int) ((getScreenHeight()) * floatVal);
			} else{
				return (int) ((getScreenHeight() - Deviceinfo.getStatusBarHeight(_activity)) * floatVal);
			}
		}
		return 0;
	}
	/*
	 * ========================================================================
	 * Getters and setters for activity, screen height and screen width
	 * ========================================================================
	 */
	private void setActivity(Activity activity) {
		if (activity == null){
			throw new ActivityNotFoundException();
		} else{
			this._activity = activity;
		}
	}
	private Activity getActivity() {
		return this._activity;
	}
	private void setHeight(int height) {
		this._height = height;
	}
	/**
	 * Get the screen height
	 * 
	 * @return The screen height.
	 */
	public int getScreenHeight() {
		return this._height;
	}
	/**
	 * Get the screen height
	 * 
	 * @return The screen height as a double.
	 */
	public Double getScreenHeightAsDouble() {
		return Double.valueOf(this._height);
	}
	private void setWidth(int width) {
		this._width = width;
	}
	/**
	 * Get the screen width
	 * 
	 * @return The screen width.
	 */
	public int getScreenWidth() {
		return this._width;
	}
	/**
	 * Get the screen width
	 * 
	 * @return The screen width as a double.
	 */
	public Double getScreenWidthAsDouble() {
		return Double.valueOf(this._width);
	}
	/*
	 * ========================================================================
	 * Private functions for use internally
	 * ========================================================================
	 */
	/**
	 * Sets the view object to it's dynamic height and width
	 * 
	 * @param layout
	 * @param height
	 * @param width
	 */
	private void setDynamicDimensions(LayoutParams layout, int height, int width) {
		double[] dimensions = setDimensions(height, width);
		layout.height = (int) dimensions[0];
		layout.width = (int) dimensions[1];
	}
	/**
	 * Set the dimensions needed for view objects
	 * 
	 * @param heightPercentage
	 * @param widthPercentage
	 * @return
	 */
	private double[] setDimensions(int heightPercentage, int widthPercentage) {
		double[] dimensions = new double[2];
		dimensions[0] = ((double) heightPercentage * getScreenHeight()) / 100;
		dimensions[1] = ((double) widthPercentage * getScreenWidth()) / 100;
		return dimensions;
	}
	/**
	 * Discover the screen height and width
	 */
	private void discoverActivityScreenSize() {
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		setHeight(displaymetrics.heightPixels);
		setWidth(displaymetrics.widthPixels);
	}
	public TextView setDynamicInRelativeLayouts(RelativeLayout parentRelativeLayout, int name, float width, float height, float leftMargin, float topMargin) {
		TextView viewObjects = new TextView(_activity);
		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(20, 20);
		layoutParams.leftMargin = convertFloatingToPixle(ScreenCalulator.Width, leftMargin);
		layoutParams.topMargin = convertFloatingToPixle(ScreenCalulator.Height, topMargin);
		layoutParams.width = convertFloatingToPixle(ScreenCalulator.Width, width);
		layoutParams.height = convertFloatingToPixle(ScreenCalulator.Height, height);
		parentRelativeLayout.addView(viewObjects, layoutParams);
		viewObjects.setText(name);
		return viewObjects;
	}
	public void setDynamicInRelativeLayoutsWithOldView(RelativeLayout parentRelativeLayout, Object viewObj, Class classType, float width, float height, float leftMargin, float topMargin) {
		if (classType == WebView.class){
			RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(20, 20);
			layoutParams.leftMargin = convertFloatingToPixle(ScreenCalulator.Width, leftMargin);
			layoutParams.topMargin = convertFloatingToPixle(ScreenCalulator.Height, topMargin);
			layoutParams.width = convertFloatingToPixle(ScreenCalulator.Width, width);
			layoutParams.height = convertFloatingToPixle(ScreenCalulator.Height, height);
			parentRelativeLayout.addView((WebView) viewObj, layoutParams);
		} else if (classType == ListView.class){
			RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(20, 20);
			layoutParams.leftMargin = convertFloatingToPixle(ScreenCalulator.Width, leftMargin);
			layoutParams.topMargin = convertFloatingToPixle(ScreenCalulator.Height, topMargin);
			layoutParams.width = convertFloatingToPixle(ScreenCalulator.Width, width);
			layoutParams.height = convertFloatingToPixle(ScreenCalulator.Height, height);
			parentRelativeLayout.addView((ListView) viewObj, layoutParams);
		} else if (classType == TextView.class){
			RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(20, 20);
			layoutParams.leftMargin = convertFloatingToPixle(ScreenCalulator.Width, leftMargin);
			layoutParams.topMargin = convertFloatingToPixle(ScreenCalulator.Height, topMargin);
			layoutParams.width = convertFloatingToPixle(ScreenCalulator.Width, width);
			layoutParams.height = convertFloatingToPixle(ScreenCalulator.Height, height);
			parentRelativeLayout.addView((TextView) viewObj, layoutParams);
		} else if (classType == ImageView.class){
			RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(20, 20);
			layoutParams.leftMargin = convertFloatingToPixle(ScreenCalulator.Width, leftMargin);
			layoutParams.topMargin = convertFloatingToPixle(ScreenCalulator.Height, topMargin);
			layoutParams.width = convertFloatingToPixle(ScreenCalulator.Width, width);
			layoutParams.height = convertFloatingToPixle(ScreenCalulator.Height, height);
			parentRelativeLayout.addView((TextView) viewObj, layoutParams);
		}
	}
	public void setDynamicInRelativeLayoutsWithOldView(RelativeLayout parentRelativeLayout, Object viewObj, Class classType, float width, float height, float leftMargin, float topMargin, boolean isAddView) {
		if (classType == WebView.class){
			RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(20, 20);
			layoutParams.leftMargin = convertFloatingToPixle(ScreenCalulator.Width, leftMargin);
			layoutParams.topMargin = convertFloatingToPixle(ScreenCalulator.Height, topMargin);
			layoutParams.width = convertFloatingToPixle(ScreenCalulator.Width, width);
			layoutParams.height = convertFloatingToPixle(ScreenCalulator.Height, height);
			if (isAddView)
				parentRelativeLayout.addView((WebView) viewObj, layoutParams);
		} else if (classType == ListView.class){
			RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(20, 20);
			layoutParams.leftMargin = convertFloatingToPixle(ScreenCalulator.Width, leftMargin);
			layoutParams.topMargin = convertFloatingToPixle(ScreenCalulator.Height, topMargin);
			layoutParams.width = convertFloatingToPixle(ScreenCalulator.Width, width);
			layoutParams.height = convertFloatingToPixle(ScreenCalulator.Height, height);
			if (isAddView)
				parentRelativeLayout.addView((ListView) viewObj, layoutParams);
		} else if (classType == TextView.class){
			RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(20, 20);
			layoutParams.leftMargin = convertFloatingToPixle(ScreenCalulator.Width, leftMargin);
			layoutParams.topMargin = convertFloatingToPixle(ScreenCalulator.Height, topMargin);
			layoutParams.width = convertFloatingToPixle(ScreenCalulator.Width, width);
			layoutParams.height = convertFloatingToPixle(ScreenCalulator.Height, height);
			if (isAddView)
				parentRelativeLayout.addView((TextView) viewObj, layoutParams);
		} else if (classType == ImageView.class){
			RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(20, 20);
			layoutParams.leftMargin = convertFloatingToPixle(ScreenCalulator.Width, leftMargin);
			layoutParams.topMargin = convertFloatingToPixle(ScreenCalulator.Height, topMargin);
			layoutParams.width = convertFloatingToPixle(ScreenCalulator.Width, width);
			layoutParams.height = convertFloatingToPixle(ScreenCalulator.Height, height);
			if (isAddView)
				parentRelativeLayout.addView((TextView) viewObj, layoutParams);
		} else if (classType == CircleImageView.class){
			RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(20, 20);
			layoutParams.leftMargin = convertFloatingToPixle(ScreenCalulator.Width, leftMargin);
			layoutParams.topMargin = convertFloatingToPixle(ScreenCalulator.Height, topMargin);
			layoutParams.width = convertFloatingToPixle(ScreenCalulator.Width, width);
			layoutParams.height = convertFloatingToPixle(ScreenCalulator.Height, height);
			if (isAddView)
				parentRelativeLayout.addView((CircleImageView) viewObj, layoutParams);
		}
	}
	public View setDynamicInRelativeLayoutCircleImageView(RelativeLayout parentRelativeLayout, Bitmap bm, Float Width, Float Height, Float LeftMargin, Float TopMargin) {
		CircleImageView viewObject = new CircleImageView(_activity);
		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(20, 20);
		layoutParams.leftMargin = convertFloatingToPixle(ScreenCalulator.Width, LeftMargin);
		layoutParams.topMargin = convertFloatingToPixle(ScreenCalulator.Height, TopMargin);
		layoutParams.width = convertFloatingToPixle(ScreenCalulator.Width, Width);
		layoutParams.height = convertFloatingToPixle(ScreenCalulator.Height, Height);
		parentRelativeLayout.addView(viewObject, layoutParams);
		viewObject.setImageBitmap(bm);
		// viewObject.setScaleType(ScaleType.FIT_XY);
		return viewObject;
	}
}
