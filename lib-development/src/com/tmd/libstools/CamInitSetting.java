package com.tmd.libstools;

import java.util.List;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.Parameters;
import android.os.Build;
import android.util.Log;
import android.view.SurfaceView;
import android.view.ViewGroup;

import com.tmd.deviceinfo.Deviceinfo;
import com.tmd.systemtools.PrintLog;

public class CamInitSetting {
	private String		TAG	= "CamInitSetting";
	private Camera		cam;
	private Activity	activity;
	private DeviceData	deviceData;
	private String[]	devicearraydata;

	public CamInitSetting(Camera cam, Activity activity, String orentation) {
		try {
			this.activity = activity;
			this.cam = cam;
			deviceData = new DeviceData();
			devicearraydata = deviceData.getDeviceCamSetting(Deviceinfo.BUILD_MODEL, orentation, activity);

			PrintLog.print(activity.getApplicationContext(), Deviceinfo.BUILD_MODEL);
			PrintLog.print(activity.getApplicationContext(), Deviceinfo.getDeviceScreenOrentation(activity));
			PrintLog.print(activity.getApplicationContext(), "W" + Deviceinfo.getDeviceScreenSize(activity)[0] + "|H" + Deviceinfo.getDeviceScreenSize(activity)[1] + "|A" + Deviceinfo.getStatusBarHeight(activity));

			setCameraDisplaySetting();

		} catch (Exception e) {
			Log.e(TAG, "cam is null", e);
		}
	}

	public CamInitSetting(Camera cam, Activity activity, String orentation, SurfaceView sufview) {
		try {
			this.activity = activity;
			this.cam = cam;
			deviceData = new DeviceData();
			devicearraydata = deviceData.getDeviceCamSetting(Deviceinfo.BUILD_MODEL, orentation, activity);

			PrintLog.print(activity.getApplicationContext(), Deviceinfo.BUILD_MODEL);
			PrintLog.print(activity.getApplicationContext(), Deviceinfo.getDeviceScreenOrentation(activity));
			PrintLog.print(activity.getApplicationContext(), "W" + Deviceinfo.getDeviceScreenSize(activity)[0] + "|H" + Deviceinfo.getDeviceScreenSize(activity)[1] + "|A" + Deviceinfo.getStatusBarHeight(activity));

			if (Deviceinfo.BUILD_MODEL.contains("HTC")) {
				sufview.setLayoutParams(new ViewGroup.LayoutParams(Deviceinfo.getDeviceScreenSize(activity)[0], Deviceinfo.getDeviceScreenSize(activity)[1]));
			}
			else {
				int w = 0, h = 0;
				double per_of_apect = 0.0;
				per_of_apect = (((Deviceinfo.getDeviceScreenSize(activity)[1] - Deviceinfo.getStatusBarHeight(activity)) * 100.0) / Deviceinfo.getDeviceScreenSize(activity)[1]) / 100;
				w = (int) ((cam.getParameters().getPreviewSize().height * 2) * (per_of_apect));
				h = Deviceinfo.getDeviceScreenSize(activity)[1] - Deviceinfo.getStatusBarHeight(activity);
				sufview.setLayoutParams(new ViewGroup.LayoutParams(w, h));
			}

			setCameraDisplaySetting();

		} catch (Exception e) {
			Log.e(TAG, "cam is null", e);
		}
	}

	public void setSurfaceLayoutToFullScreenNotincludeActionbar(SurfaceView sufview) {
		double per_of_apect;
		int w = Deviceinfo.getDeviceScreenSize(activity)[0], h = Deviceinfo.getDeviceScreenSize(activity)[1];
		if (Deviceinfo.getDeviceScreenSize(activity)[0] == 1280 && Deviceinfo.getDeviceScreenSize(activity)[1] == 800 || Deviceinfo.getDeviceScreenSize(activity)[1] == 1280 && Deviceinfo.getDeviceScreenSize(activity)[0] == 800) {
			per_of_apect = (((Deviceinfo.getDeviceScreenSize(activity)[1] - Deviceinfo.getStatusBarHeight(activity)) * 100.0) / Deviceinfo.getDeviceScreenSize(activity)[1]) / 100;
			w = (int) ((cam.getParameters().getPreviewSize().height * 2) * (per_of_apect));
			h = Deviceinfo.getDeviceScreenSize(activity)[1] - Deviceinfo.getStatusBarHeight(activity);
		}
		if (Deviceinfo.getDeviceScreenSize(activity)[1] == 800 && Deviceinfo.getDeviceScreenSize(activity)[0] == 480 || Deviceinfo.getDeviceScreenSize(activity)[0] == 800 && Deviceinfo.getDeviceScreenSize(activity)[1] == 480) {
			w = 600;
			h = 800;
		}
		else if (Deviceinfo.getDeviceScreenSize(activity)[1] == 800 && Deviceinfo.getDeviceScreenSize(activity)[0] == 1232 || Deviceinfo.getDeviceScreenSize(activity)[0] == 800 && Deviceinfo.getDeviceScreenSize(activity)[1] == 1232) {
			w = 924;
			h = 1232;
		}

		sufview.setLayoutParams(new ViewGroup.LayoutParams(w, h));
	}

	public void setCameraDisplaySetting() {

		// //Set Orentation
		if (!devicearraydata[2].equals("BYPASS")) { // --> For Camera Orentation Support
			cam.setDisplayOrientation(Integer.parseInt(devicearraydata[2]));
		}
		// //Set Camera Preview
		// Parameters param = cam.getParameters();
		// param.setPreviewSize(Integer.parseInt(devicearraydata[4]), Integer.parseInt(devicearraydata[3]));
		try {
			// cam.setParameters(param);
			setPictureSizeOfScreenAndPreviewsize();
		} catch (Exception e) {
			PrintLog.print(activity, "Can't setPreviewSize");
			Parameters param = cam.getParameters();
			param.setPreviewSize(480, 640);
			cam.setParameters(param);
			PrintLog.print(activity, "set lowest PreviewSize 480x640");
		}
		PrintLog.print(activity.getApplicationContext(), Deviceinfo.BUILD_MODEL);
		PrintLog.print(activity.getApplicationContext(), Deviceinfo.getDeviceScreenOrentation(activity));
		PrintLog.print(activity.getApplicationContext(), "W" + Deviceinfo.getDeviceScreenSize(activity)[0] + "|H" + Deviceinfo.getDeviceScreenSize(activity)[1] + "|A" + Deviceinfo.getStatusBarHeight(activity));
	}

	// TODO >>>> Set Picture Size From Screen
	public void setPictureSizeOfScreenAndPreviewsize() {
		Parameters params = cam.getParameters();
		List<Camera.Size> sizes = params.getSupportedPictureSizes();
		boolean sizesupportofscreen = false;
		for (int i = 0; i < sizes.size(); i++) {
			PrintLog.print(activity.getApplicationContext(), sizes.get(i).width + "|" + sizes.get(i).height + " : : " + Deviceinfo.getDeviceScreenSize(activity)[0] + "|" + Deviceinfo.getDeviceScreenSize(activity)[1]);

			if ((sizes.get(i).width == Deviceinfo.getDeviceScreenSize(activity)[0] && sizes.get(i).height == Deviceinfo.getDeviceScreenSize(activity)[1]) || (sizes.get(i).width == Deviceinfo.getDeviceScreenSize(activity)[1] && sizes.get(i).height == Deviceinfo.getDeviceScreenSize(activity)[0])) {
				if (Deviceinfo.getDeviceScreenOrentation(activity).equals("portrait")) {
					PrintLog.print(activity.getApplicationContext(), sizes.get(i).width + "|" + sizes.get(i).height + " is support!");
					params.setPictureSize(sizes.get(i).width, sizes.get(i).height);

					if (getFrontCameraStatus()) { // Front Facing Detect
						params.setPreviewSize(sizes.get(i).width, sizes.get(i).height);
					}

					if (getBackCameraStatus()) { // back Facing Detect
						params.setPreviewSize(Integer.parseInt(devicearraydata[4]), Integer.parseInt(devicearraydata[3]));
					}

					try {
						cam.setParameters(params);
					} catch (Exception e) {
						PrintLog.print(activity, "Can't setPictureSizeOfScreen");
					}
				}
				sizesupportofscreen = true;
				break;

			}
			else {
				sizesupportofscreen = false;
			}

		}
		//
		// if(!sizesupportofscreen){
		// params.setPreviewSize(1200,1600);
		// try{
		// cam.setParameters(params);
		// }catch (Exception e){
		// PrintLog.print(activity,"Can't setPictureSizeOfScreen");
		// }
		//
		// }

		if (Deviceinfo.BUILD_MODEL.equals("GT-S5360")) {

			params.setPictureSize(1280, 960);
			try {
				cam.setParameters(params);
			} catch (Exception e) {
				PrintLog.print(activity, "Can't setPictureSizeOfScreen");
			}
		}
	}

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	boolean getFrontCameraStatus() {
		Camera.CameraInfo ci = new Camera.CameraInfo();
		for (int i = 0; i < Camera.getNumberOfCameras(); i++) {
			Camera.getCameraInfo(i, ci);
			if (ci.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) return true;
		}
		return false; // No front-facing camera found
	}

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	boolean getBackCameraStatus() {
		Camera.CameraInfo ci = new Camera.CameraInfo();
		for (int i = 0; i < Camera.getNumberOfCameras(); i++) {
			Camera.getCameraInfo(i, ci);
			if (ci.facing == Camera.CameraInfo.CAMERA_FACING_BACK) return true;
		}
		return false; // No front-facing camera found
	}

	public void playAutoFocus(boolean isFocus) {
		if (isFocus) {
			if (devicearraydata[0].equals("GT-S7562")) {
				if (IsFlash) {
					playFlash(true);
				}
			}
			else {
				cam.autoFocus(new AutoFocusCallback() {
					@Override
					public void onAutoFocus(boolean success, Camera camera) {
					}
				});
			}
		}
		else {
			cam.cancelAutoFocus();
		}
	}

	boolean	IsFlash	= false;

	public void playFlash(boolean isFlash) {
		this.IsFlash = isFlash;
		if (Deviceinfo.getFlashAvailableOnDevice(activity)) {
			Parameters param = cam.getParameters();
			List<String> supportedFlashModes = param.getSupportedFlashModes();
			PrintLog.print(activity.getApplicationContext(), supportedFlashModes.toString());
			// For Support Mode Torch
			if (supportedFlashModes != null && supportedFlashModes.contains(Camera.Parameters.FLASH_MODE_TORCH)) {
				if (isFlash) {
					param.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
				}
				else {
					param.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
				}
				cam.setParameters(param);
			}
			else {
				// For unSupport Mode Torch
				if (isFlash) {
					if (devicearraydata[0].equals("GT-S5830")) {
						param.setFlashMode(Parameters.FLASH_MODE_ON);
						cam.setParameters(param);
						cam.autoFocus(new Camera.AutoFocusCallback() {
							public void onAutoFocus(final boolean success, Camera camera) {
							}
						});
						cam.startPreview();
						param = cam.getParameters();
						param.setFlashMode(Parameters.FLASH_MODE_OFF);
						cam.setParameters(param);
					}
				}
				else {
					if (devicearraydata[0].equals("GT-S5830")) {
						param.setFlashMode(Parameters.FLASH_MODE_ON);
						cam.setParameters(param);
						cam.autoFocus(new Camera.AutoFocusCallback() {
							public void onAutoFocus(final boolean success, Camera camera) {
							}
						});
						cam.startPreview();
					}
				}
			}
		}
		else {
			PrintLog.print(activity.getApplicationContext(), "Not Found Flash On Device.");
		}
	}

	public void takePicture() {
		cam.takePicture(null, null, new Camera.PictureCallback() {
			@Override
			public void onPictureTaken(byte[] bytes, Camera camera) {
				Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
				ImagesIO.writeImage(activity, bmp, Integer.parseInt(devicearraydata[2]));

			}
		});

	}

	public void setAutoFocus() {
		Parameters params = cam.getParameters();
		List<String> focusModes = params.getSupportedFocusModes();
		try {
			if (focusModes.contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)) {
				params.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);

			}
			else if (focusModes.contains(Camera.Parameters.FOCUS_MODE_AUTO)) {
				params.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);

			}
			else if (focusModes.contains(Camera.Parameters.FOCUS_MODE_INFINITY)) {
				params.setFocusMode(Camera.Parameters.FOCUS_MODE_INFINITY);

			}
		} catch (Exception ex) {

		}
	}

}
