/*
 * Copyright (C) 2012 www.amsoft.cn
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
package com.ddanwang.tourism.view.sliding;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.ddanwang.tourism.util.L;


// TODO: Auto-generated Javadoc

/**
 * © 2012 amsoft.cn 名称：AbFileUtil.java 描述：文件操作类.
 * 
 * @author 还如一梦中
 * @version v1.0
 * @date：2013-01-18 下午11:52:13
 */
public class AbFileUtil {
	/** The tag. */
	private static String TAG = "AbFileUtil";
	
	/** The Constant D. */
	private static final boolean D = false;
	
	/**
	 * 描述：获取src中的图片资源.
	 * 
	 * @param src
	 *            图片的src路径，如（“image/arrow.png”）
	 * @return Bitmap 图片
	 */
	public static Bitmap getBitmapFromSrc(String src) {
		Bitmap bit = null;
		try {
			bit = BitmapFactory.decodeStream(AbFileUtil.class
					.getResourceAsStream(src));
		} catch (Exception e) {
			L.v(TAG, "获取图片异常：" + e.getMessage());
		}
		return bit;
	}


}
