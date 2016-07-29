package com.tom.school.core.io;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class IOUtility {

	public static void write(String str, String filePath, Boolean... append) throws IOException {
		write(str.getBytes(), filePath, append);
	}

	public static void write(byte[] bytes, String filePath, Boolean... append) throws IOException {
		FileOutputStream fops = null;
		if(append != null && append.length > 0){
			fops = new FileOutputStream(new File(filePath), append[0]);
		} else {
			fops = new FileOutputStream(new File(filePath));
		}
		fops.write(bytes);
		fops.flush();
		fops.close();
	}

	public static String readString(String path) throws Exception {
		return new String(readBytes(path));
	}

	public static String readString(InputStream in) throws Exception {
		return new String(readBytes(in));
	}

	public static byte[] readBytes(String filePath) throws FileNotFoundException, Exception {
		File file = new File(filePath);
		if (file.exists()) {
			return readBytes(new BufferedInputStream(new FileInputStream(file)));
		} else {
			throw new FileNotFoundException("Can't find file " + filePath);
		}
	}

	public static byte[] readBytes(InputStream in) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = in.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		in.close();
		return outStream.toByteArray();
	}

}
