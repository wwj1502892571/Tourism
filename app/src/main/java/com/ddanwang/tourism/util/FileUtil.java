package com.ddanwang.tourism.util;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

	private static final String TAG = "FileUtil";

	public static FileUtil instances;

	private Context mContext;

	public static FileUtil getInstances() {
		if (instances == null) {
			instances = new FileUtil();
		}
		return instances;
	}

	public void deleteFileByName(String path) {
		Log.d(TAG, "==============path=" + path);
		File file = new File(path); // "my/voice.amr"
		if (file.exists()) {
			Log.d(TAG, "==============pɾ��");
			file.delete();
		}
	}

	public String getAmrPath(String path, String fileName) {
		File file = new File(Environment.getExternalStorageDirectory(), path
				+ fileName);
		return file.getAbsolutePath();
	}

	public byte[] getBytesFromFile(File f) throws IOException,
			FileNotFoundException {
		if (f == null) {
			return null;
		}
		FileInputStream fis = null;
		ByteArrayOutputStream out = null;
		try {
			fis = new FileInputStream(f);
			out = new ByteArrayOutputStream(1000);
			byte[] b = new byte[1000];
			int n;
			while ((n = fis.read(b)) != -1) {
				out.write(b, 0, n);
			}
			return out.toByteArray();
		} finally {
			if (fis != null)
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if (out != null)
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	public void deleteFolderFile(String filePath, boolean deleteThisPath)
			throws IOException {
		if (!TextUtils.isEmpty(filePath)) {
			File file = new File(filePath);

			if (file.isDirectory()) {// ����Ŀ¼
				File files[] = file.listFiles();
				for (int i = 0; i < files.length; i++) {
					deleteFolderFile(files[i].getAbsolutePath(), true);
				}
			}
			if (deleteThisPath) {
				if (!file.isDirectory()) {// ������ļ���ɾ��
					file.delete();
				} else {// Ŀ¼
					if (file.listFiles().length == 0) {// Ŀ¼��û���ļ�����Ŀ¼��ɾ��
						file.delete();
					}
				}
			}
		}
	}

	private static List<File> getAllFileFromFileDir(String dirPath) {
		ArrayList<File> fileList = new ArrayList<File>();
		File file = new File(dirPath);
		if (!file.exists()) {
			return fileList;
		}
		if (!file.isDirectory()) {
			fileList.add(file);
		} else if (file.isDirectory()) {
			String[] filelist = file.list();
			for (int i = 0; i < filelist.length; i++) {
				File readfile = new File(dirPath + "/" + filelist[i]);
				if (!readfile.isDirectory()) {
					fileList.add(readfile);
				} else if (readfile.isDirectory()) {
					getAllFileFromFileDir(dirPath + "/" + filelist[i]);
				}
			}

		}
		return fileList;
	}

	public static void saveCityWeatherDb(Context context) {
		String DB_PATH = "data/data/com.libo.running/databases/";
		String DB_NAME = "weather_city_info.db";
		File f = new File(DB_PATH);
		File file = new File(DB_PATH + DB_NAME);

		if (file.exists()) {
			return;
		}
		if (!f.exists()) {
			f.mkdir();
		}
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (file.exists()) {
			try {
				InputStream is = context.getAssets().open("weather/" + DB_NAME);
				OutputStream os = new FileOutputStream(DB_PATH + DB_NAME);
				byte[] buffer = new byte[1024];
				int length;
				while ((length = is.read(buffer)) > 0) {
					os.write(buffer, 0, length);
				}
				// 关闭文件流
				os.flush();
				os.close();
				is.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
