package com.ddanwang.tourism.db.dao;

import android.content.Context;

import com.ddanwang.tourism.baseDB.orm.AbSDDBHelper;
import com.ddanwang.tourism.db.CityVo;
import com.ddanwang.tourism.db.DestinationVo;
import com.ddanwang.tourism.db.UserInfoVo;
import com.ddanwang.tourism.global.Constant;

import java.io.File;

/**
 * Created by WeiWenjun
 * 2016/3/2
 * 9:22
 * DBSDHelper
 */
public class DBSDHelper extends AbSDDBHelper {
    // 数据库名
    public static final String DBNAME = "turism.db";
    // 数据库 存放路径
    public static final String DBPATH = Constant.FILE_DIR_PATH;
    // 当前数据库的版本
    private static final int DBVERSION = 2;
    // 要初始化的表
    private static final Class<?>[] clazz = { UserInfoVo.class , CityVo.class , DestinationVo.class};

    public DBSDHelper(Context context) {
        super(context, DBPATH, DBNAME, null, DBVERSION, clazz);
        File destDir = new File(DBPATH);
        if (!destDir.exists()) {
            destDir.mkdirs();
        }
    }

    public static String getDBFilePath() {
        return DBPATH + "/" + DBNAME;
    }
}
