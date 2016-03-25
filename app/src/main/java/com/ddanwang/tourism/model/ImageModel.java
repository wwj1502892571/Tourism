package com.ddanwang.tourism.model;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * 图片管理对象
 * @author sunrr.fnst
 *
 */
public class ImageModel implements Serializable{
	//SD卡图片存放路径
	private String mImageUri;
	//软引用 Bitmap对象保存
	private Bitmap mImageBitmap;
	
	public String getmImageUri() {
		return mImageUri;
	}
	public void setmImageUri(String mImageUri) {
		this.mImageUri = mImageUri;
	}
	public Bitmap getmImageBitmap() {
		return mImageBitmap;
	}
	public void setmImageBitmap(Bitmap mImageBitmap) {
		this.mImageBitmap = mImageBitmap;
	}
}
