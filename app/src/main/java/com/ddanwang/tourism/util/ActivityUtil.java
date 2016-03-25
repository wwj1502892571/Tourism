package com.ddanwang.tourism.util;

import android.app.Activity;

import java.util.LinkedList;
import java.util.List;
/**
 * 记录activity，关闭activity
 * @author rui
 *
 */
public class ActivityUtil {
	//运用list来保存们每一个activity是关键  
    private List<Activity> mList = new LinkedList<Activity>();  
    //为了实现每次使用该类时不创建新的对象而创建的静态对象  
    private static ActivityUtil instance;   
    //构造方法  
    private ActivityUtil(){}  
    //实例化一次  
    public synchronized static ActivityUtil getInstance(){   
        if (null == instance) {   
            instance = new ActivityUtil();   
        }   
        return instance;   
    }   
    // add Activity    
    public void addActivity(Activity activity) {   
        mList.add(activity);   
    }   
    //关闭每一个list内的activity  
    public void exit() {   
        try {   
            for (Activity activity:mList) {   
                if (activity != null)   
                    activity.finish();   
            }   
        } catch (Exception e) {   
            e.printStackTrace();   
        } finally {   
//            System.exit(0);   
        }   
    }   
    //杀进程  
//    public void onLowMemory() {   
//        super.onLowMemory();       
//        System.gc();   
//    }    
}
