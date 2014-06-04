package com.tmd.internet;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import org.apache.commons.io.FilenameUtils;
import android.content.Context;
import android.util.Log;





public class FileIO{
	public static String saveFileToSD(Context mContext,String Dir,String fileURL,String Filename){
		int count;
		String from=fileURL;
		String baseName=FilenameUtils.getBaseName(fileURL);
		String extension=FilenameUtils.getExtension(fileURL);
		File mypath=new File(Dir,Filename);
		try {
			URL url=new URL(from);
			URLConnection conexion=url.openConnection();
			conexion.connect();
			InputStream input=new BufferedInputStream(url.openStream());
			String fileName=from.substring(from.lastIndexOf('/') + 1,from.length());
			OutputStream output=new FileOutputStream(mypath); // save to parh sd card
			byte data[]=new byte[1024];
			while ((count=input.read(data)) != -1) {
				output.write(data,0,count);
			}
			output.flush();
			output.close();
			input.close();
		}
		catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.e("saveFileToSD",mypath.getAbsolutePath());
		return mypath.getAbsolutePath();
	}
}
