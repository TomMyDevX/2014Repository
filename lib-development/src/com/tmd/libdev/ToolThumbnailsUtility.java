package com.tmd.libdev;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.util.Log;
public class ToolThumbnailsUtility{
	public static Bitmap getThumbnailsInSecond(Context context, String source_file) {
		MediaMetadataRetriever retriever = new MediaMetadataRetriever();
		retriever.setDataSource(source_file);
		String time = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
		long timeInmillisec = Long.parseLong(time);
		long duration = timeInmillisec / 1000;
		long hours = duration / 3600;
		long minutes = (duration - hours * 3600) / 60;
		long seconds = duration - (hours * 3600 + minutes * 60);
		Bitmap bitmap = null;
		try{
			Log.e("getDataSource", ((int) ((timeInmillisec / 2) / 1000)) * 1000 + "");
			bitmap = retriever.getFrameAtTime((int) ((timeInmillisec / 2) / 1000) * 1000000, MediaMetadataRetriever.OPTION_CLOSEST_SYNC);
			retriever.release();
		} catch (IllegalArgumentException ex){
			Log.e("ERROR", "ERROR", ex);
		} catch (RuntimeException ex){
			Log.e("ERROR", "ERROR", ex);
		} finally{
			try{
				retriever.release();
			} catch (RuntimeException ex){
				Log.e("ERROR", "ERROR", ex);
			}
		}
		return bitmap;
	}
}
