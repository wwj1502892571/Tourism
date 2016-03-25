package com.ddanwang.tourism.global;

import android.os.Environment;

public class Constant {

	// 图片URI保存
	public static final String IMAGE_URI_PATH = "image_uri_path";
	public static final String IMAGE_URI_NAME = "image_uri_name";
	// 相机
	public static final int CAMERA = 0x01;
	public static final int ACTION_GET_CONTENT_RESULT_CODE = 1;// 相册中去获取 result
	public static final String FILE_DIR_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/turism";
	public static final String IMAGE_SD_PATH = FILE_DIR_PATH + "/Image/";

	public static final String REGISTER_FINISH = "register_finish";
	public static final String EXIT_APP = "exit_app";
	public static final int TEXT_MAX = 200;

	public static final String IS_VOICE = "is_voice";
	public static final String IS_VIBRATION = "is_vibration";

}
