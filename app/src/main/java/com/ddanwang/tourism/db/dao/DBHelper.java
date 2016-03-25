package com.ddanwang.tourism.db.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	// 数据库名
	private static final String DBNAME = "express.db";
	// 当前数据库的版本
	private static final int DBVERSION = 1;

	public static final String TABLE_CHANNEL = "channel";// 数据表
	private Context context;
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// 建表语句直接使用db.execSQL(String sql)方法执行SQL建表语句
		// TODO 创建数据库后，对数据库的操作
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	private static DBHelper instance = null;

	public static DBHelper getInstance(Context context) {
		if (instance == null) {
			instance = new DBHelper(context);
		}
		return instance;
	}

	public DBHelper(Context context) {
		super(context, DBNAME, null, DBVERSION);
		this.context = context;
	}
	
	public Context getContext(){
		return context;
	}

}
