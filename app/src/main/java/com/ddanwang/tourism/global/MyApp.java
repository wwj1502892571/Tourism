package com.ddanwang.tourism.global;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;

import com.ddanwang.tourism.R;
import com.ddanwang.tourism.db.dao.UserDao;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import io.realm.BuildConfig;
import timber.log.Timber;

public class MyApp extends Application {
    private static MyApp instance;
    private static Map<Integer, Bitmap> bitmaps;

    public static MyApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        bitmaps = new HashMap<Integer, Bitmap>();
        instance = this;
        createSDCardDir();
        initStrictMode();
        initLogger();


        //创建默认的ImageLoader配置参数
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration
                .createDefault(this);
        ImageLoader.getInstance().init(configuration);
    }


    public static String getUserName(){
        return new UserDao(instance).getUserVO().getUserName();
    }

    public static int getUserId(){
        return new UserDao(instance).getUserVO().getId();
    }

    /**
     * 初始化StrictMode
     */
    private void initStrictMode() {
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                            // .penaltyDeath()
                    .build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                            // .penaltyDeath()
                    .build());
        }
    }


    private void initLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new LogReportingTree());
        }
    }

    private static class LogReportingTree extends Timber.Tree {
        @Override
        protected void log(int priority, String tag, String message, Throwable t) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG || priority == Log.INFO) {
                return;
            }
            // 将部分日志报告服务器
        }
    }

    //在SD卡上创建一个文件夹
    public void createSDCardDir(){
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
            // 创建一个文件夹对象，赋值为外部存储器的目录
            File sdcardDir = Environment.getExternalStorageDirectory();
            //得到一个路径，内容是sdcard的文件夹路径和名字
            String path=sdcardDir.getPath()+"/carlease";
            File path1 = new File(path);
            if (!path1.exists()) {
                //若不存在，创建目录，可以在应用启动的时候创建
                path1.mkdirs();
            }
        }
        else{
            return;
        }
    }

    public static Bitmap getBitmapByRourceId(int rourceId) {
        if (bitmaps.get(rourceId) != null) {
            return bitmaps.get(rourceId);
        } else {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = false;
            if(rourceId== R.drawable.splash){
                options.inSampleSize =2; // 默认节点背景图片较大故压缩为原来的2/1
            }
            Bitmap bitmap = BitmapFactory.decodeResource(getInstance()
                    .getResources(), rourceId, options);
            bitmaps.put(rourceId, bitmap);
            return bitmap;
        }
    }

}
