package com.tmd.internet;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
public class Downloader{
	public static void DownloadFile(String fileURL, File directory) throws IOException {
		FileOutputStream f = new FileOutputStream(directory);
		URL u = new URL(fileURL);
		HttpURLConnection c = (HttpURLConnection) u.openConnection();
		c.setRequestMethod("GET");
		c.setDoOutput(true);
		c.connect();
		InputStream in = c.getInputStream();
		byte[] buffer = new byte[1024];
		int len1 = 0;
		while ((len1 = in.read(buffer)) > 0){
			f.write(buffer, 0, len1);
		}
		f.close();
	}
}
