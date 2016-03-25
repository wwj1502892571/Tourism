package com.ddanwang.tourism.util;

import android.app.Application;
import android.util.DisplayMetrics;
import android.util.Log;

import com.ddanwang.tourism.global.MyApp;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 应用 配置
 * @author zl-baoy
 */
public class Configure {
	
	private static final String TAG = "Configure";

	public static final String PREFS_NAME = "config_prefs";
	public static final String PREFS_CONFIG_LASTLOGINDATE = "prefs_config_lastlogindate";
	public static final String PREFS_CONFIG_CREATE_SHORTCUT_FLAG = "prefs_config_create_shortcut_flag";
	public static final String PREFS_CONFIG_LOGOUT_FLAG = "prefs_config_logout_flag";
    public static final String PREFS_CONFIG_LAST_LOGIN_USER = "prefs_config_last_login_user";
    public static final String PREFS_CONFIG_SYNC_STATUS = "prefs_config_sync_status"; // 每天一次同步数据状态

    private static Configure mInstance = null;
	public Map<String, String> queueSyncMapping = new HashMap<String, String>(); // 同步组与显示组名的映射关系
	

	public DisplayMetrics dm;
	
	//property
	public float density;
	public int heightPixels;
	public int widhtPixels;
	public int statusBarPixels;

    public int photoNum = 8; // 配置表--单次照相照片数量
    public int photoSize = 100; // 配置表--照片大小单位 kb
    public int photoResolutionWidth = 480, photoResolutionHeight = 800;// 配置表--照片分辨率
    public int padFlow = 500; // 单位M
    public String fileUploadPath = ""; //webService 上传文件接口地址


    public volatile boolean isUploadQueueSyncing; //上传队列同步标记
	
	public static Configure getConfigure(){
		if(mInstance == null){
			mInstance = new Configure();
		}
		return mInstance;
	}
	
	public Configure() {
		Application application = MyApp.getInstance();
		init(application);
	}

	private void init(Application application){
		this.dm = application.getResources().getDisplayMetrics();
		this.density = this.dm.density;
		//取窄边
		this.heightPixels = dm.heightPixels < dm.widthPixels ? dm.heightPixels : dm.widthPixels;
		//取长边
		this.widhtPixels = dm.widthPixels < dm.heightPixels ? dm.heightPixels : dm.widthPixels;
		//取Statusbar高度
		this.statusBarPixels = getStatusBarHeight();
		Log.d(TAG, "statusBar height:" + statusBarPixels);
		Log.d(TAG, "display height:" + heightPixels + "\n display width:" + widhtPixels);
	}
	
	/**
	 * 反射获得 sdk 4.0以上statusbar高度
	 * @return
	 */
	public int getStatusBarHeight(){
		int sbar = 0;
		//大于4.0.x
		if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
			Class<?> c = null;
			Object obj = null;
			Field field = null;
			int x = 0;
			sbar = 38;// 默认38
			try {
				c = Class.forName("com.android.internal.R$dimen");
				obj = c.newInstance();
				field = c.getField("status_bar_height");
				x = Integer.parseInt(field.get(obj).toString());
				sbar = MyApp.getInstance().getResources().getDimensionPixelSize(x);
			} catch (Exception e) {
				Log.e(TAG, e.getMessage(),e);
			}
		}
		
		return sbar;
	}
	 public void release(boolean all) {
	        mInstance = null;
	        System.gc();
	    }
		
	
}