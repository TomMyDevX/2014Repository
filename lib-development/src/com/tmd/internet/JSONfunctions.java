package com.tmd.internet;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONObject;
import android.os.AsyncTask;
import android.util.Log;
public class JSONfunctions{
	public static void getJSONfromURL(final String url, final boolean isConnected, final String fileCache, final JSONType jsonType, final OnHttpListener onHttpListener) throws Exception {
		new AsyncTask<String, String, Object>(){
			@Override
			protected void onPreExecute() {
				if (onHttpListener != null)
					onHttpListener.onStarting();
			}
			@Override
			protected Object doInBackground(String... params) {
				Object object = new Object();
				Log.e("getJSONfromURL", url);
				if (JSONType.JSONArray == jsonType){
					object = getJSArray(url, isConnected, fileCache, onHttpListener);
				} else if (JSONType.JSONObject == jsonType){
					object = getJSObject(url, isConnected, fileCache, onHttpListener);
				}
				return object;
			}
			@Override
			protected void onPostExecute(Object result) {
				if (onHttpListener != null)
					onHttpListener.onCompleted(result);
			}
		}.execute();
	}
	public static JSONArray getJSArray(String url, boolean isConnected, String fileCache, OnHttpListener onHttpListener) {
		boolean error = false;
		InputStream is = null;
		String result = "";
		JSONArray jArray = null;
		try{
			if (isConnected){
				HttpParams httpParameters = new BasicHttpParams();
				int timeoutConnection = 1000;
				HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
				int timeoutSocket = 1000;
				HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
				HttpClient httpclient = new DefaultHttpClient();
				HttpPost httppost = new HttpPost(url);
				HttpResponse response;
				response = httpclient.execute(httppost);
				HttpEntity entity = response.getEntity();
				is = entity.getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"), 8);
				StringBuilder sb = new StringBuilder();
				String line = null;
				while ((line = reader.readLine()) != null){
					sb.append(line + "\n");
				}
				is.close();
				result = sb.toString();
				try{
					if (fileCache != null){
						File f = new File(fileCache);
						if (f.exists())
							f.delete();
						f.createNewFile();
						long fileLength = f.length();
						RandomAccessFile raf = new RandomAccessFile(f, "rw");
						raf.seek(fileLength);
						raf.writeBytes(result);
						raf.close();
						Log.e("cacheFile", f.getAbsolutePath());
						jArray = new JSONArray(result);
					} else{
						jArray = new JSONArray(result);
					}
				} catch (Exception e){
					if (fileCache != null){
						jArray = readJSONFromSD(fileCache);
					} else{
						return null;
					}
				}
			} else{
				if (fileCache != null){
					jArray = readJSONFromSD(fileCache);
				} else{
					return null;
				}
			}
		} catch (ClientProtocolException e){
			Log.e("ERROR", "ClientProtocolException", e);
			error = true;
			if (fileCache != null){
				jArray = readJSONFromSD(fileCache);
			} else{
				return null;
			}
		} catch (IOException e){
			error = true;
			if (fileCache != null){
				jArray = readJSONFromSD(fileCache);
			} else{
				return null;
			}
		}
		return jArray;
	}
	public static JSONObject getJSObject(String url, boolean isConnected, String fileCache, OnHttpListener onHttpListener) {
		boolean error = false;
		InputStream is = null;
		String result = "";
		JSONObject jArray = null;
		try{
			if (isConnected){
				HttpParams httpParameters = new BasicHttpParams();
				int timeoutConnection = 1000;
				HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
				int timeoutSocket = 1000;
				HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
				HttpClient httpclient = new DefaultHttpClient();
				HttpPost httppost = new HttpPost(url);
				HttpResponse response;
				response = httpclient.execute(httppost);
				HttpEntity entity = response.getEntity();
				is = entity.getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"), 8);
				StringBuilder sb = new StringBuilder();
				String line = null;
				while ((line = reader.readLine()) != null){
					sb.append(line + "\n");
				}
				is.close();
				result = sb.toString();
				try{
					if (fileCache != null){
						File f = new File(fileCache);
						if (f.exists())
							f.delete();
						f.createNewFile();
						long fileLength = f.length();
						RandomAccessFile raf = new RandomAccessFile(f, "rw");
						raf.seek(fileLength);
						raf.writeBytes(result);
						raf.close();
						Log.e("cacheFile", f.getAbsolutePath());
						jArray = new JSONObject(result);
					} else{
						jArray = new JSONObject(result);
					}
				} catch (Exception e){
					if (fileCache != null){
						jArray = readJSONObjectSD(fileCache);
					} else{
						return null;
					}
				}
			} else{
				if (fileCache != null){
					jArray = readJSONObjectSD(fileCache);
				} else{
					return null;
				}
			}
		} catch (ClientProtocolException e){
			Log.e("ERROR", "ClientProtocolException", e);
			error = true;
			if (fileCache != null){
				jArray = readJSONObjectSD(fileCache);
			} else{
				return null;
			}
		} catch (IOException e){
			error = true;
			if (fileCache != null){
				jArray = readJSONObjectSD(fileCache);
			} else{
				return null;
			}
		}
		return jArray;
	}
	public static JSONArray readJSONFromSD(String fileDiskCache) {
		try{
			File yourFile = new File(fileDiskCache);
			if (yourFile.exists()){
				FileInputStream stream = new FileInputStream(yourFile);
				String jString = null;
				try{
					FileChannel fc = stream.getChannel();
					MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
					jString = Charset.defaultCharset().decode(bb).toString();
				} finally{
					stream.close();
				}
				Log.e("String", jString);
				JSONArray jObject = new JSONArray(jString);
				return jObject;
			} else{
				return null;
			}
		} catch (Exception e){
			return null;
		}
	}
	// public static JSONArray readJSONArraySD(String file_path){
	// try {
	// // File dir = Environment.getExternalStorageDirectory();
	// File yourFile=new File(file_path);
	// FileInputStream stream=new FileInputStream(yourFile);
	// String jString=null;
	// try {
	// FileChannel fc=stream.getChannel();
	// MappedByteBuffer bb=fc.map(FileChannel.MapMode.READ_ONLY,0,fc.size());
	// /* Instead of using default, pass in a decoder. */
	// jString=Charset.defaultCharset().decode(bb).toString();
	// }
	// finally {
	// stream.close();
	// }
	// JSONArray jsonArray=new JSONArray(jString);
	// return jsonArray;
	// }
	// catch (Exception e) {
	// e.printStackTrace();
	// return null;
	// }
	// }
	public static JSONObject readJSONObjectSD(String file_path) {
		try{
			// File dir = Environment.getExternalStorageDirectory();
			File yourFile = new File(file_path);
			FileInputStream stream = new FileInputStream(yourFile);
			String jString = null;
			try{
				FileChannel fc = stream.getChannel();
				MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
				/* Instead of using default, pass in a decoder. */
				jString = Charset.defaultCharset().decode(bb).toString();
			} finally{
				stream.close();
			}
			JSONObject jsonArray = new JSONObject(jString);
			return jsonArray;
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
}