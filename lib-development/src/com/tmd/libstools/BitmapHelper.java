package com.tmd.libstools;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.Log;

import com.tmd.deviceinfo.Deviceinfo;
import com.tmd.systemtools.PrintLog;

/**
 * Created by TomMy on 7/30/13.
 */
public class BitmapHelper {
	// Scale and keep aspect ratio
	static public Bitmap scaleToFitWidth(Bitmap b, int width, Activity ac) {
		float factor = width / (float) b.getWidth();
		// PrintLog.printC(BitmapHelper.class, "WD"+width + "|WH" + (int) (b.getHeight() * factor));
		// return Bitmap.createScaledBitmap(b, width, (int) (b.getHeight() * factor), false);
		float aspect = (float) Deviceinfo.getDeviceScreenSize(ac)[0] / (float) Deviceinfo.getDeviceScreenSize(ac)[1];
		float scaleWidth = ((float) Deviceinfo.getDeviceScreenSize(ac)[0]) / aspect;// 0.4
		float scaleHeight = ((float) Deviceinfo.getDeviceScreenSize(ac)[1]) / aspect;// 0.4
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);

		Bitmap bitmap = Bitmap.createBitmap(width, width, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);

		// float factor = width / (float) b.getWidth();

		bitmap = combineImages(bitmap, resizeImage(b, (int) (Deviceinfo.getDeviceScreenSize(ac)[0] * aspect), (int) (Deviceinfo.getDeviceScreenSize(ac)[1] * aspect)));
		return bitmap;

	}

	// Scale and keep aspect ratio
	static public Bitmap scaleToFitHeight(Bitmap b, int height) {
		Log.e("Hieght", height + "");
		float factor = height / (float) b.getHeight();

		return Bitmap.createScaledBitmap(b, (int) (b.getWidth() * factor), height, false);

	}

	// Scale and keep aspect ratio
	static public Bitmap scaleToFill(Bitmap b, int width, int height) {
		float factorH = height / (float) b.getWidth();
		float factorW = width / (float) b.getWidth();
		float factorToUse = (factorH > factorW) ? factorW : factorH;
		return Bitmap.createScaledBitmap(b, (int) (b.getWidth() * factorToUse), (int) (b.getHeight() * factorToUse), false);
	}

	// Scale and dont keep aspect ratio
	static public Bitmap strechToFill(Bitmap b, int width, int height) {
		float factorH = height / (float) b.getHeight();
		float factorW = width / (float) b.getWidth();
		return Bitmap.createScaledBitmap(b, (int) (b.getWidth() * factorW), (int) (b.getHeight() * factorH), false);
	}

	static public Bitmap scaleBitmapSques(Bitmap b, Activity ac) {

		Bitmap bitmapOrg = b;

		int width = bitmapOrg.getWidth();
		int height = bitmapOrg.getHeight();
		PrintLog.printC(BitmapHelper.class, "BMP|" + width + "|" + height);
		// calculate the scale - in this case = 0.4f
		PrintLog.printC(BitmapHelper.class, "DW>>" + Deviceinfo.getDeviceScreenSize(ac)[0] + "|DH>>" + Deviceinfo.getDeviceScreenSize(ac)[1]);
		float aspect = (float) Deviceinfo.getDeviceScreenSize(ac)[0] / (float) Deviceinfo.getDeviceScreenSize(ac)[1];
		PrintLog.printC(BitmapHelper.class, "Aspect" + aspect);
		// float scaleWidth = ((float) newWidth) / aspect;//0.71
		// float scaleHeight = ((float) newHeight) / aspect;//0.4
		float scaleWidth = ((float) Deviceinfo.getDeviceScreenSize(ac)[0]) / aspect;// 0.4
		float scaleHeight = ((float) Deviceinfo.getDeviceScreenSize(ac)[1]) / aspect;// 0.4

		// createa matrix for the manipulation
		Matrix matrix = new Matrix();
		// resize the bit map
		matrix.postScale(scaleWidth, scaleHeight);

		// recreate the new Bitmap

		Bitmap resizedBitmap = Bitmap.createBitmap(bitmapOrg, 0, 0, width, height, matrix, false);

		return resizedBitmap;
	}

	private static Bitmap overlay(Bitmap bmp1, Bitmap bmp2, Activity ac) {
		Bitmap bmOverlay = Bitmap.createBitmap(bmp1.getWidth(), bmp1.getHeight(), bmp1.getConfig());
		Canvas canvas = new Canvas(bmOverlay);
		canvas.drawBitmap(bmp1, new Matrix(), null);

		float aspect = (float) Deviceinfo.getDeviceScreenSize(ac)[0] / (float) Deviceinfo.getDeviceScreenSize(ac)[1];
		float scaleWidth = ((float) Deviceinfo.getDeviceScreenSize(ac)[0]) / aspect;// 0.4
		float scaleHeight = ((float) Deviceinfo.getDeviceScreenSize(ac)[1]) / aspect;// 0.4
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);
		canvas.drawBitmap(bmp2, matrix, null);
		return bmOverlay;
	}

	public static Bitmap combineImages(Bitmap frame, Bitmap image) {

		Bitmap cs = null;
		Bitmap rs = null;

		rs = Bitmap.createScaledBitmap(frame, image.getWidth(), image.getHeight(), true);

		cs = Bitmap.createBitmap(frame.getWidth(), frame.getHeight(), Bitmap.Config.RGB_565);

		Canvas comboImage = new Canvas(cs);

		comboImage.drawBitmap(rs, 0, 0, null);
		PrintLog.printC(BitmapHelper.class, ((frame.getWidth() / 2) / 2) + "");
		comboImage.drawBitmap(image, (frame.getWidth() - image.getWidth()) / 2, 0, null);

		if (rs != null) {
			rs.recycle();
			rs = null;
		}
		Runtime.getRuntime().gc();

		return cs;
	}

	public static Bitmap resizeImage(Bitmap image, int maxWidth, int maxHeight) {
		int imageWidth = image.getWidth();
		int imageHeight = image.getHeight();

		double imageAspect = (double) imageWidth / imageHeight;
		double canvasAspect = (double) maxWidth / maxHeight;
		double scaleFactor;

		if (imageAspect < canvasAspect) {
			scaleFactor = (double) maxHeight / imageHeight;
		}
		else {
			scaleFactor = (double) maxWidth / imageWidth;
		}

		float scaleWidth = ((float) scaleFactor) * imageWidth;
		float scaleHeight = ((float) scaleFactor) * imageHeight;

		// create a matrix for the manipulation
		Matrix matrix = new Matrix();
		// resize the bit map
		matrix.postScale(scaleWidth, scaleHeight);

		// recreate the new Bitmap
		return Bitmap.createScaledBitmap(image, (int) scaleWidth, (int) scaleHeight, true);
	}
}