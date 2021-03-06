/*
 * Copyright (C) 2013 www.418log.org
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ddanwang.tourism.baseDB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// TODO: Auto-generated Javadoc
/**
 * 描述：数据库操作类.
 * @author zhaoqp
 * @date：2013-3-13 下午4:30:10
 * @version v1.0
 */
public class MyDBHelper extends SQLiteOpenHelper {
	
	/** The Constant DBNAME. */
	private static final String DBNAME = "andbase.db";
	
	/** The Constant VERSION. */
	private static final int VERSION = 1;
	
	/**
	 * 构造器.
	 *
	 * @param context the context
	 */
	public MyDBHelper(Context context) {
		super(context, DBNAME, null, VERSION);
	}
	
	/**
	 * 描述：表的创建.
	 *
	 * @param db the db
	 * @see SQLiteOpenHelper#onCreate(SQLiteDatabase)
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE IF NOT EXISTS FILEDOWN (_ID INTEGER PRIMARY KEY AUTOINCREMENT,ICON TEXT,NAME TEXT,DESCRIPTION TEXT, PAKAGENAME TEXT ,DOWNURL TEXT,DOWNPATH TEXT,STATE INTEGER,DOWNLENGTH INTEGER,TOTALLENGTH INTEGER,DOWNSUFFIX TEXT)");
	}

	/**
	 * 描述：表的重建.
	 *
	 * @param db the db
	 * @param oldVersion the old version
	 * @param newVersion the new version
	 * @see SQLiteOpenHelper#onUpgrade(SQLiteDatabase, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS FILEDOWN");
		onCreate(db);
	}
	
}
