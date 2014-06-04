/*
 * Copyright (C) 2013 Evgeny Shishkin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.devspark.robototextview;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.android.clicnect.libdev.R;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Environment;
import android.util.SparseArray;

/**
 * The manager of roboto typefaces.
 * 
 * @author Evgeny Shishkin
 */
public class RobotoTypefaceManager {

	/*
	 * Available values ​​for the "typeface" attribute.
	 */
	public final static int ROBOTO_THIN = 0;
	public final static int ROBOTO_THIN_ITALIC = 1;
	public final static int ROBOTO_LIGHT = 2;
	public final static int ROBOTO_LIGHT_ITALIC = 3;
	public final static int ROBOTO_REGULAR = 4;
	public final static int ROBOTO_ITALIC = 5;
	public final static int ROBOTO_MEDIUM = 6;
	public final static int ROBOTO_MEDIUM_ITALIC = 7;
	public final static int ROBOTO_BOLD = 8;
	public final static int ROBOTO_BOLD_ITALIC = 9;
	public final static int ROBOTO_BLACK = 10;
	public final static int ROBOTO_BLACK_ITALIC = 11;
	public final static int ROBOTO_CONDENSED_LIGHT = 12;
	public final static int ROBOTO_CONDENSED_LIGHT_ITALIC = 13;
	public final static int ROBOTO_CONDENSED_REGULAR = 14;
	public final static int ROBOTO_CONDENSED_ITALIC = 15;
	public final static int ROBOTO_CONDENSED_BOLD = 16;
	public final static int ROBOTO_CONDENSED_BOLD_ITALIC = 17;
	public final static int ROBOTO_SLAB_THIN = 18;
	public final static int ROBOTO_SLAB_LIGHT = 19;
	public final static int ROBOTO_SLAB_REGULAR = 20;
	public final static int ROBOTO_SLAB_BOLD = 21;
	/**
	 * Array of created typefaces for later reused.
	 */
	private final static SparseArray<Typeface> mTypefaces = new SparseArray<Typeface>(
			20);

	/**
	 * Obtain typeface.
	 * 
	 * @param context
	 *            The Context the widget is running in, through which it can
	 *            access the current theme, resources, etc.
	 * @param typefaceValue
	 *            The value of "typeface" attribute
	 * @return specify {@link Typeface}
	 * @throws IllegalArgumentException
	 *             if unknown `typeface` attribute value.
	 */
	public static Typeface obtainTypeface(Context context, int typefaceValue)
			throws IllegalArgumentException {
		Typeface typeface = mTypefaces.get(typefaceValue);
		if (typeface == null) {
			typeface = createTypeface(context, typefaceValue);
			mTypefaces.put(typefaceValue, typeface);
		}
		return typeface;
	}

	/**
	 * Create typeface from assets.
	 * 
	 * @param context
	 *            The Context the widget is running in, through which it can
	 *            access the current theme, resources, etc.
	 * @param typefaceValue
	 *            The value of "typeface" attribute
	 * @return Roboto {@link Typeface}
	 * @throws IllegalArgumentException
	 *             if unknown `typeface` attribute value.
	 */
	private static Typeface createTypeface(Context context, int typefaceValue)
			throws IllegalArgumentException {
		Typeface typeface;
		switch (typefaceValue) {
		case ROBOTO_THIN:
			typeface =FileStreamTypeface(R.raw.robotothin, context); //Typeface.createFromAsset(context.getAssets(),"fonts/Roboto-Thin.ttf");
			break;
		case ROBOTO_THIN_ITALIC:
			typeface =FileStreamTypeface(R.raw.robotothinitalic, context); 
			//typeface = Typeface.createFromAsset(context.getAssets(),
			//		"fonts/Roboto-ThinItalic.ttf");
			break;
		case ROBOTO_LIGHT:
			typeface =FileStreamTypeface(R.raw.robotolight, context); 
			//typeface = Typeface.createFromAsset(context.getAssets(),
				//	"fonts/Roboto-Light.ttf");
			break;
		case ROBOTO_LIGHT_ITALIC:
			typeface =FileStreamTypeface(R.raw.robotolightitalic, context); 
			//typeface = Typeface.createFromAsset(context.getAssets(),
			//		"fonts/Roboto-LightItalic.ttf");
			break;
		case ROBOTO_REGULAR:
			//typeface = Typeface.createFromAsset(context.getAssets(),
			//		"fonts/Roboto-Regular.ttf");
			typeface =FileStreamTypeface(R.raw.robotoregular, context); 
			break;
		case ROBOTO_ITALIC:
			//typeface = Typeface.createFromAsset(context.getAssets(),
			//		"fonts/Roboto-Italic.ttf");
			typeface =FileStreamTypeface(R.raw.robotoitalic, context); 
			break;
		case ROBOTO_MEDIUM:
		//	typeface = Typeface.createFromAsset(context.getAssets(),
			//		"fonts/Roboto-Medium.ttf");
			typeface =FileStreamTypeface(R.raw.robotomedium, context); 
			break;
		case ROBOTO_MEDIUM_ITALIC:
		//	typeface = Typeface.createFromAsset(context.getAssets(),
			//		"fonts/Roboto-MediumItalic.ttf");
			typeface =FileStreamTypeface(R.raw.robotomediumitalic, context); 
			break;
		case ROBOTO_BOLD:
			//typeface = Typeface.createFromAsset(context.getAssets(),
			//		"fonts/Roboto-Bold.ttf");
			typeface =FileStreamTypeface(R.raw.robotobold, context); 
			break;
		case ROBOTO_BOLD_ITALIC:
			//typeface = Typeface.createFromAsset(context.getAssets(),
			//		"fonts/Roboto-BoldItalic.ttf");
			typeface =FileStreamTypeface(R.raw.robotolightitalic, context); 
			break;
		case ROBOTO_BLACK:
			//typeface = Typeface.createFromAsset(context.getAssets(),
			//		"fonts/Roboto-Black.ttf");
			typeface =FileStreamTypeface(R.raw.robotoblack, context); 
			break;
		case ROBOTO_BLACK_ITALIC:
			//typeface = Typeface.createFromAsset(context.getAssets(),
			//		"fonts/Roboto-BlackItalic.ttf");
			typeface =FileStreamTypeface(R.raw.robotoblackitalic, context); 
			break;
		case ROBOTO_CONDENSED_LIGHT:
			//typeface = Typeface.createFromAsset(context.getAssets(),
			//		"fonts/RobotoCondensed-Light.ttf");
			typeface =FileStreamTypeface(R.raw.robotocondensedlight, context); 
			break;
		case ROBOTO_CONDENSED_LIGHT_ITALIC:
		//	typeface = Typeface.createFromAsset(context.getAssets(),
			//		"fonts/RobotoCondensed-LightItalic.ttf");
			typeface =FileStreamTypeface(R.raw.robotocondensedlightitalic, context); 
			break;
		case ROBOTO_CONDENSED_REGULAR:
			//typeface = Typeface.createFromAsset(context.getAssets(),
					//"fonts/RobotoCondensed-Regular.ttf");
			typeface =FileStreamTypeface(R.raw.robotocondensedregular, context); 
			break;
		case ROBOTO_CONDENSED_ITALIC:
			//typeface = Typeface.createFromAsset(context.getAssets(),
			//		"fonts/RobotoCondensed-Italic.ttf");
			typeface =FileStreamTypeface(R.raw.robotocondenseditalic, context); 
			break;
			
		case ROBOTO_CONDENSED_BOLD:
			//typeface = Typeface.createFromAsset(context.getAssets(),
			//		"fonts/RobotoCondensed-Bold.ttf");
			typeface =FileStreamTypeface(R.raw.robotocondensedbold, context); 
			break;
		case ROBOTO_CONDENSED_BOLD_ITALIC:
			//typeface = Typeface.createFromAsset(context.getAssets(),
				//	"fonts/RobotoCondensed-BoldItalic.ttf");
			typeface =FileStreamTypeface(R.raw.robotocondensedbolditalic, context); 
			break;
		case ROBOTO_SLAB_THIN:
			//typeface = Typeface.createFromAsset(context.getAssets(),
			//		"fonts/RobotoSlab-Thin.ttf");
			typeface =FileStreamTypeface(R.raw.robotoslabthin, context); 
			break;
		case ROBOTO_SLAB_LIGHT:
			//typeface = Typeface.createFromAsset(context.getAssets(),
			//		"fonts/RobotoSlab-Light.ttf");
			typeface =FileStreamTypeface(R.raw.robotoslablight, context); 
			break;
		case ROBOTO_SLAB_REGULAR:
			//typeface = Typeface.createFromAsset(context.getAssets(),
			//		"fonts/RobotoSlab-Regular.ttf");
			typeface =FileStreamTypeface(R.raw.robotoslabregular, context); 
			break;
		case ROBOTO_SLAB_BOLD:
			//typeface = Typeface.createFromAsset(context.getAssets(),
			//		"fonts/RobotoSlab-Bold.ttf");
			typeface =FileStreamTypeface(R.raw.robotoslabbold, context); 
			break;
		default:
			throw new IllegalArgumentException(
					"Unknown `typeface` attribute value " + typefaceValue);
		}
		return typeface;
	}

	static Typeface FileStreamTypeface(int resource,Context context) {
		Typeface tf = null;

		InputStream is = context.getResources().openRawResource(resource);
		String path = Environment.getExternalStorageDirectory()
				.getAbsolutePath() + "/gmg_underground_tmp";
		File f = new File(path);
		if (!f.exists()) {
			if (!f.mkdirs())
				return null;
		}

		String outPath = path + "/tmp.raw";

		try {
			byte[] buffer = new byte[is.available()];
			BufferedOutputStream bos = new BufferedOutputStream(
					new FileOutputStream(outPath));

			int l = 0;
			while ((l = is.read(buffer)) > 0) {
				bos.write(buffer, 0, l);
			}
			bos.close();

			tf = Typeface.createFromFile(outPath);

			File f2 = new File(outPath);
			f2.delete();
		} catch (IOException e) {
			return null;
		}

		return tf;
	}
}
