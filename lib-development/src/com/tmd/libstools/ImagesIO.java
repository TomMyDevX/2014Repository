package com.tmd.libstools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;

import com.tmd.deviceinfo.Deviceinfo;

/**
 * Created by TomMy on 6/6/13.
 */
public class ImagesIO {
	public static void writeImage(Activity activity, Bitmap bmImg, int degree) {

		File filename;
		try {
			bmImg = getResizedBitmap(bmImg, Deviceinfo.getDeviceScreenSize(activity)[1]);
			bmImg = rotate(bmImg, degree);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
			String currentDateandTime = sdf.format(new Date());
			String path = Environment.getExternalStorageDirectory().toString();
			new File(path + "/" + activity.getPackageName()).mkdirs();
			filename = new File(path + "/" + activity.getPackageName() + "/" + currentDateandTime + ".png");
			FileOutputStream out = new FileOutputStream(filename);
			bmImg.compress(Bitmap.CompressFormat.PNG, 100, out);
			out.flush();
			out.close();
			MediaStore.Images.Media.insertImage(activity.getContentResolver(), filename.getAbsolutePath(), filename.getName(), filename.getName());
			Log.e("IMG ERROR", "File:" + filename);
			// Toast.makeText(activity.getApplicationContext(),"File is Saved in  " + filename, 1000).show();
			bmImg.recycle();
		} catch (Exception e) {
			Log.e("IMG ERROR", "File Save ERROR", e);
			bmImg.recycle();
		}

	}

	public static String writeImageWithReturnURL(Activity activity, Bitmap bmImg, int degree, boolean isFilp, boolean isCenterCrop, boolean isResize, boolean isRotate) {
		File filename;
		try {
			bmImg = isResize ? getResizedBitmap(bmImg, Deviceinfo.getDeviceScreenSize(activity)[0]) : bmImg;
			bmImg = isRotate ? rotate(bmImg, degree) : bmImg;
			bmImg = isFilp ? flip(bmImg) : bmImg;
			bmImg = isCenterCrop ? scaleCenterCrop(bmImg, Deviceinfo.getDeviceScreenSize(activity)[0], Deviceinfo.getDeviceScreenSize(activity)[0]) : bmImg;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
			String currentDateandTime = sdf.format(new Date());
			String path = Environment.getExternalStorageDirectory().toString();
			new File(path + "/" + activity.getPackageName()).mkdirs();
			filename = new File(path + "/" + activity.getPackageName() + "/" + currentDateandTime + ".png");
			FileOutputStream out = new FileOutputStream(filename);
			bmImg.compress(Bitmap.CompressFormat.PNG, 100, out);
			out.flush();
			out.close();
			MediaStore.Images.Media.insertImage(activity.getContentResolver(), filename.getAbsolutePath(), filename.getName(), filename.getName());
			Log.e("writeImageWithReturnURL", "File:" + filename);
			// Toast.makeText(activity.getApplicationContext(),"File is Saved in  " + filename, 1000).show();
			bmImg.recycle();
			return filename.getAbsolutePath();
		} catch (Exception e) {
			Log.e("IMG ERROR", "File Save ERROR", e);
			bmImg.recycle();
			return "";
			// xxx

		}

	}

	public static String writeImageTempNonExtensionWithReturnURL(Activity activity, Bitmap bmImg, int degree, boolean isFilp, boolean isCenterCrop, boolean isResize, boolean isRotate, String path) {
		File filename;
		try {
			bmImg = isResize ? getResizedBitmap(bmImg, Deviceinfo.getDeviceScreenSize(activity)[1]) : bmImg;
			bmImg = isRotate ? rotate(bmImg, degree) : bmImg;
			bmImg = isFilp ? flip(bmImg) : bmImg;
			bmImg = isCenterCrop ? scaleCenterCrop(bmImg, Deviceinfo.getDeviceScreenSize(activity)[1], Deviceinfo.getDeviceScreenSize(activity)[0]) : bmImg;
			// SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
			// String currentDateandTime = sdf.format(new Date());

			File mediaStorageDir = new File(path, "Temp");
			if (!mediaStorageDir.exists()) {
				if (!mediaStorageDir.mkdirs()) {

					return null;
				}
			}
			deletefile(mediaStorageDir.getPath() + File.separator + "IMG");
			File mediaFile;
			mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG");

			// String path = Environment.getExternalStorageDirectory().toString();
			// new File(path + "/"+activity.getPackageName()).mkdirs();
			// filename = new File(path + "/"+activity.getPackageName()+"/"+currentDateandTime);
			FileOutputStream out = new FileOutputStream(mediaFile);
			bmImg.compress(Bitmap.CompressFormat.JPEG, 100, out);
			out.flush();
			out.close();

			// MediaStore.Images.Media.insertImage(activity.getContentResolver(),filename.getAbsolutePath(), filename.getName(),filename.getName());
			Log.e("writeImageTempNonExtensionWithReturnURL", "File:" + mediaFile.getAbsolutePath());
			// Toast.makeText(activity.getApplicationContext(),"File is Saved in  " + filename, 1000).show();
			bmImg.recycle();
			bmImg = null;
			return mediaFile.getAbsolutePath();
		} catch (Exception e) {
			bmImg.recycle();
			Log.e("IMG ERROR", "File Save ERROR", e);
			return "";

		}

	}

	public static File getPathIO() {
		File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Temp");
		return new File(mediaStorageDir.getPath() + File.separator + "IMG");
	}

	public static String getImagesIOPath() {

		File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Temp");
		File mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG.jpg");
		FileOutputStream out = null;
		try {
			Bitmap bmImg = BitmapFactory.decodeFile(mediaStorageDir.getPath() + File.separator + "IMG");
			out = new FileOutputStream(mediaFile);
			bmImg.compress(Bitmap.CompressFormat.JPEG, 100, out);
			out.flush();
			out.close();
			Log.e("com.clicknect.apps.android.nimonyim", "getImagesIOPath Write File to Share.");
		} catch (FileNotFoundException e) {
			Log.e("com.clicknect.apps.android.nimonyim", "getImagesIOPath File not found.");
		} catch (IOException io) {
			Log.e("com.clicknect.apps.android.nimonyim", "getImagesIOPath IOException.");
		}
		return mediaFile.getAbsolutePath();
	}

	public static String getImagesIOPathInstragram(Activity ac) {

		File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Temp");
		File mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMGIN.jpg");
		FileOutputStream out = null;
		try {
			Bitmap bmImg = getImageBitmapInstragramPath(ac);
			out = new FileOutputStream(mediaFile);
			bmImg.compress(Bitmap.CompressFormat.JPEG, 100, out);
			out.flush();
			out.close();
			Log.e("com.clicknect.apps.android.nimonyim", " getImagesIOPathInstragram Write File to Share.");
		} catch (FileNotFoundException e) {
			Log.e("com.clicknect.apps.android.nimonyim", " getImagesIOPathInstragram File not found.");
		} catch (IOException io) {
			Log.e("com.clicknect.apps.android.nimonyim", " getImagesIOPathInstragram IOException.");
		}
		return mediaFile.getAbsolutePath();
	}

	public static Bitmap getImageBitmapInstragramPath(Activity ac) {
		File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Temp");
		Bitmap bmImg = BitmapFactory.decodeFile(mediaStorageDir.getPath() + File.separator + "IMG");
		//
		// Bitmap background = Bitmap.createBitmap((int) Deviceinfo.getDeviceScreenSize(ac)[0], Deviceinfo.getDeviceScreenSize(ac)[0], Bitmap.Config.ARGB_8888);
		// float originalWidth = bmImg.getWidth(), originalHeight = bmImg.getHeight();
		// Canvas canvas = new Canvas(background);
		// float scale = Deviceinfo.getDeviceScreenSize(ac)[0]/originalWidth;
		// float xTranslation = 0.0f, yTranslation = (Deviceinfo.getDeviceScreenSize(ac)[1] - originalHeight * scale)/2.0f;
		// Matrix transformation = new Matrix();
		// transformation.postTranslate(xTranslation, yTranslation);
		// transformation.preScale(scale, scale);
		// Paint paint = new Paint();
		// paint.setFilterBitmap(true);
		// canvas.drawBitmap(bmImg, transformation, paint);

		float aspect = (float) Deviceinfo.getDeviceScreenSize(ac)[0] / (float) Deviceinfo.getDeviceScreenSize(ac)[1];
		return BitmapHelper.scaleToFitWidth(bmImg, (Deviceinfo.getDeviceScreenSize(ac)[0]), ac);
	}

	public static Bitmap scaleCenterCrop(Bitmap source, int newHeight, int newWidth) {
		int sourceWidth = source.getWidth();
		int sourceHeight = source.getHeight();
		float xScale = (float) newWidth / sourceWidth;
		float yScale = (float) newHeight / sourceHeight;
		float scale = Math.max(xScale, yScale);

		// get the resulting size after scaling
		float scaledWidth = scale * sourceWidth;
		float scaledHeight = scale * sourceHeight;

		// figure out where we should translate to
		float dx = (newWidth - scaledWidth) / 2;
		float dy = (newHeight - scaledHeight) / 2;
		// float dx = (newWidth - scaledWidth) ;
		// float dy = (newHeight - scaledHeight) ;

		Bitmap dest = Bitmap.createBitmap(newWidth, newHeight, source.getConfig());
		Canvas canvas = new Canvas(dest);
		Matrix matrix = new Matrix();
		matrix.postScale(scale, scale);
		matrix.postTranslate(dx, dy);
		canvas.drawBitmap(source, matrix, null);
		source.recycle();
		return dest;
	}

	public static boolean deletefile(String FilePath) {
		File file = new File(FilePath);
		boolean delstate = false;
		if (file.delete()) {
			Log.e("Delete", "Delete File|" + FilePath);
			delstate = true;
		}
		else {
			delstate = false;
		}
		return delstate;
	}

	public static Bitmap flip(Bitmap d) {
		Matrix m = new Matrix();
		m.preScale(-1, 1);
		Bitmap src = d;
		Bitmap dst = Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), m, false);
		dst.setDensity(DisplayMetrics.DENSITY_DEFAULT);
		return dst;
	}

	public static Bitmap rotate(Bitmap b, int degrees) {
		if (degrees != 0 && b != null) {

			// try {
			Matrix matrix = new Matrix();
			// matrix.setRotate(degrees, (float) b.getWidth() / 2, (float) b.getHeight() / 2);
			matrix.postRotate(degrees);
			b = Bitmap.createBitmap(b, 0, 0, b.getWidth(), b.getHeight(), matrix, true);
			// if (b != b2) {
			// b.recycle();
			// b = b2;
			// }
			// } catch (OutOfMemoryError ex) {
			// Log.e("IMG ERROR","File RORATE ERROR",ex);
			// }
		}
		return b;
	}

	private static Bitmap getResizedBitmap(Bitmap bm, int newWidth) {

		int width = bm.getWidth();

		int height = bm.getHeight();

		float aspect = (float) width / height;

		float scaleWidth = newWidth;

		float scaleHeight = scaleWidth / aspect; // yeah!

		// create a matrix for the manipulation

		Matrix matrix = new Matrix();

		// resize the bit map

		matrix.postScale(scaleWidth / width, scaleHeight / height);

		// recreate the new Bitmap

		bm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
		//
		// bm.recycle();
		// if (bm != resizedBitmap)
		// bm.recycle();
		// bm = null;

		return bm;
	}

	private static Bitmap getResizedBitmapHieght(Bitmap bm, int newH) {

		int width = bm.getWidth();

		int height = bm.getHeight();
		Log.e("width|height", width + "|" + height);
		float aspect = (float) width / height;
		Log.e("aspect", aspect + "");
		float scaleHeight = newH;

		float scaleWidth = width / aspect; // yeah!
		Log.e("scaleWidth|scaleHeight", scaleWidth + "|" + scaleHeight);
		// create a matrix for the manipulation

		Matrix matrix = new Matrix();

		// resize the bit map

		Log.e("PIX", scaleWidth / width + "|" + scaleHeight / height);

		matrix.postScale(scaleWidth / width, scaleHeight / height);

		// recreate the new Bitmap

		bm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
		//
		// bm.recycle();
		// if (bm != resizedBitmap)
		// bm.recycle();
		// bm = null;

		return bm;
	}

	public static void manageAlbum(Context context, String filename) {
		String path = Environment.getExternalStorageDirectory().toString();
		File dir = new File(path, "/ktzmico/");
		if (!dir.isDirectory()) {
			dir.mkdirs();
		}

		File file = new File(filename);
		String imagePath = file.getAbsolutePath();
		MediaScannerConnection.scanFile(context, new String[] { imagePath }, null, new MediaScannerConnection.OnScanCompletedListener() {
			public void onScanCompleted(String path, Uri uri) {
				// if (Config.LOG_DEBUG_ENABLED) {
				Log.e("Class", "scanned : " + path);
				// }
			}
		});
	}

}
