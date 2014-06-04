package com.tmd.email;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;

/**
 * Created by TomMy on 7/26/13.
 */
public class Emailer {
	public static Intent SendEmail2GmailApp(Context context, String title, String FilePath) {
		Intent intent = new Intent(android.content.Intent.ACTION_SEND);
		intent.setType("text/html");
		final PackageManager pm = context.getPackageManager();
		final List<ResolveInfo> matches = pm.queryIntentActivities(intent, 0);
		ResolveInfo best = null;
		for (final ResolveInfo info : matches) {
			if (info.activityInfo.packageName.endsWith(".gm") || info.activityInfo.name.toLowerCase().contains("gmail")) {
				best = info;
				break;
			}
		}
		if (best != null) {
			intent.setClassName(best.activityInfo.packageName, best.activityInfo.name);
		}
		intent.putExtra(android.content.Intent.EXTRA_SUBJECT, title);
		Bitmap icon = BitmapFactory.decodeFile(FilePath);
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		icon.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
		File f = new File(Environment.getExternalStorageDirectory() + File.separator + "temporary_file.jpg");
		try {
			f.createNewFile();
			FileOutputStream fo = new FileOutputStream(f);
			fo.write(bytes.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
		}
		intent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///sdcard/temporary_file.jpg"));
		// intent.putExtra(android.content.Intent.EXTRA_TEXT, Html.fromHtml("YOUR EXTRAS"));
		// context.startActivity(intent);
		return intent;
	}
}
