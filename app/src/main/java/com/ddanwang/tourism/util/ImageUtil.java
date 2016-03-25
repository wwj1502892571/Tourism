package com.ddanwang.tourism.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.ddanwang.tourism.R;
import com.ddanwang.tourism.global.Constant;
import com.ddanwang.tourism.model.ImageModel;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 图片处理公共类
 * 
 * @author sunrr.fnst
 * 
 */
public class ImageUtil {
	private static final String TAG = "ImageUtil";

	// 拍照的照片存储位置
	public static final File PHOTO_DIR = new File(Constant.IMAGE_SD_PATH);

	// 图片路劲
	private Uri mImageuri;

	// 图片路劲
	private Uri mImageCaptureUriCutted;

	// ImageUtil的声明对象
	public static ImageUtil mInstance;

	// 记录图片对象集合
	// public List<Bitmap> mPicUpList = new ArrayList<Bitmap>();

	// 记录图片对象集合
	public List<ImageModel> mPicUpList = new ArrayList<ImageModel>();

	// 记录图片对象集合的集合
	public List<ArrayList<ImageModel>> mMPicList = new ArrayList<ArrayList<ImageModel>>();

	// 记录图片路径集合
	public List<ArrayList<String>> mImgPathList = new ArrayList<ArrayList<String>>();

	/**
	 * ImageUtil 的单例
	 * 
	 * @return
	 */
	public static ImageUtil getInstance() {
		if (mInstance == null) {
			mInstance = new ImageUtil();
		}
		return mInstance;
	}

	/**
	 * 根据宽高比例缩放图片
	 * 
	 * @param width
	 * @param hight
	 * @return
	 */
	private Bitmap zoomImageBySize(int width, int hight) {
		return null;
	}

	/**
	 * 将本地图片上传到文件服务器
	 * 
	 * @param imageList
	 */
	private void uploadImageList(List<Bitmap> imageList) {

	}

	/**
	 * 图片路径选择
	 * 
	 * @param mActivity
	 */
	public void doPickPhotoAction(final Activity mActivity) {

		String status = Environment.getExternalStorageState();
		// 判断是否有SD卡
		if (status.equals(Environment.MEDIA_MOUNTED)) {
			// 从照相机获取
			doTakePhoto(mActivity);
		} else {
			Toast.makeText(mActivity, mActivity.getString(R.string.sd_not_found), Toast.LENGTH_SHORT).show();
		}

		// // Wrap our context to inflate list items using correct theme
		// final Context dialogContext = new ContextThemeWrapper(mActivity,
		// android.R.style.Theme_Light);
		//
		// String cancel = mActivity.getString(R.string.back);
		// String[] choices;
		// choices = new String[2];
		// // 拍照
		// choices[0] = mActivity.getString(R.string.from_camera);
		// // 从相册中选择
		// choices[1] = mActivity.getString(R.string.from_gallery);
		// final ListAdapter adapter = new ArrayAdapter<String>(dialogContext,
		// android.R.layout.simple_list_item_1, choices);
		//
		// final AlertDialog.Builder builder = new AlertDialog.Builder(
		// dialogContext);
		// builder.setTitle(R.string.select_pic);
		// builder.setSingleChoiceItems(adapter, -1,
		// new DialogInterface.OnClickListener() {
		// public void onClick(DialogInterface dialog, int which) {
		// dialog.dismiss();
		// switch (which) {
		// case 0: {
		// String status = Environment
		// .getExternalStorageState();
		// // 判断是否有SD卡
		// if (status.equals(Environment.MEDIA_MOUNTED)) {
		// // 从照相机获取
		// doTakePhoto(mActivity);
		// } else {
		// Toast.makeText(
		// mActivity,
		// mActivity
		// .getString(R.string.sd_not_found),
		// Toast.LENGTH_SHORT).show();
		// }
		// break;
		//
		// }
		// case 1: {
		// // 从相册中去获取
		// doPickPhotoFromGallery(mActivity);
		// break;
		// }
		// }
		// }
		// });
		// builder.setNegativeButton(cancel,
		// new DialogInterface.OnClickListener() {
		//
		// @Override
		// public void onClick(DialogInterface dialog, int which) {
		// dialog.dismiss();
		// }
		//
		// });
		// builder.create().show();
	}

	/**
	 * 调用系统相机拍摄
	 * 
	 * @param mActivity
	 */
	protected void doTakePhoto(Activity mActivity) {
		try {
			// Launch camera to take photo for selected contact
			// 创建照片的存储目录
			PHOTO_DIR.mkdirs();
			File mCurrentPhotoFile = new File(PHOTO_DIR, getPhotoFileName());
			mImageuri = Uri.fromFile(mCurrentPhotoFile);
			mImageCaptureUriCutted = mImageuri;
			mActivity.getSharedPreferences(Constant.IMAGE_URI_PATH, 0).edit()
					.putString(Constant.IMAGE_URI_NAME, mImageCaptureUriCutted.toString()).commit();
			final Intent intent = getTakePickIntent(mImageuri);
			mActivity.startActivityForResult(intent, Constant.ACTION_GET_CONTENT_RESULT_CODE);
		} catch (ActivityNotFoundException e) {
		}
	}

	/**
	 * 从系统相册中选取照片
	 * 
	 * @param mActivity
	 */
	protected void doPickPhotoFromGallery(Activity mActivity) {
		try {
			PHOTO_DIR.mkdirs();
			File mCurrentPhotoFile = new File(PHOTO_DIR, getPhotoFileName());
			mImageuri = Uri.fromFile(mCurrentPhotoFile);
			mImageCaptureUriCutted = mImageuri;
			final Intent intent = getPhotoPickIntent(mImageuri);
			mActivity.startActivityForResult(intent, Constant.ACTION_GET_CONTENT_RESULT_CODE);
		} catch (ActivityNotFoundException e) {

		}
	}

	/**
	 * 用当前时间给取得的图片命名
	 */
	public String getPhotoFileName() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat dateFormat = new SimpleDateFormat("'IMG'_yyyyMMddHHmmss");
		return dateFormat.format(date);
	}

	/**
	 * 
	 * @param uri
	 * @return
	 */
	public Intent getTakePickIntent(Uri uri) {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
		return intent;
	}

	// 请求Gallery的intent
	public Intent getPhotoPickIntent(Uri uri) {
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT, null);
		intent.setType("image/*");
		intent.putExtra("crop", "true");
		// intent.putExtra("aspectX", 1);
		// intent.putExtra("aspectY", 1);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
		intent.putExtra("return-data", false);

		return intent;
	}

	/**
	 * 取得裁剪后的图片的uri
	 * 
	 * @return 裁剪后的图片的uri
	 */
	public Uri getImageCaptureUriCutted(Activity mActivity) {
		// 判断文件长度为0,则删除该文件,返回空
		Log.d(TAG, "=========mImageCaptureUriCutted回调=" + mImageCaptureUriCutted);
		if (mImageCaptureUriCutted == null) {
			mImageCaptureUriCutted = Uri.parse(
					mActivity.getSharedPreferences(Constant.IMAGE_URI_PATH, 0).getString(Constant.IMAGE_URI_NAME, ""));
		}
		Log.d(TAG, "=========mImageCaptureUriCutted回调2=" + mImageCaptureUriCutted);
		// Uri uri =
		// Uri.parse(mActivity.getSharedPreferences(Constants.IMAGE_URI_PATH,
		// 0).getString(Constants.IMAGE_URI_NAME,""));
		// if(mImageCaptureUriCutted!=null){
		// file = new File(filterPath(mActivity, mImageCaptureUriCutted));
		// }else{
		// String path =
		// mActivity.getSharedPreferences(Constants.IMAGE_URI_PATH,
		// 0).getString(Constants.IMAGE_URI_NAME,"");
		// Log.d(TAG, "=============path="+path+",="+mImageCaptureUriCutted);
		// file = new File(path);
		// }
		File file = new File(filterPath(mActivity, mImageCaptureUriCutted));
		if (file != null && file.length() == 0) {
			if (!file.delete()) {
				Log.e("TAG", "file delete fail");
			}
			return null;
		}
		return mImageCaptureUriCutted;
	}

	/**
	 * 获得多媒体的地址
	 * 
	 * @param context
	 *            上下文
	 * @param uri
	 *            多媒体URI
	 * @return String 图片地址
	 */
	public static String filterPath(Context context, Uri uri) {
		if (uri == null) {
			return null;
		}

		String path = null;
		if (uri.getScheme().equals("file")) {
			path = uri.getPath();
		} else {
			path = getPathFromUri(context, uri);
		}
		return path;
	}

	/**
	 * 从uri获取图片路径
	 * 
	 * @param context
	 *            上下文环境
	 * @param uri
	 *            图片的uri
	 * @return 图片路径
	 */
	public static String getPathFromUri(Context context, Uri uri) {
		if (uri == null) {
			return null;
		}
		ContentResolver cr = context.getContentResolver();

		// 查询数据库
		Cursor cursor = cr.query(uri, new String[] { MediaStore.Images.Media.DATA }, null, null, null);
		String path = null;
		if (cursor != null) {
			if (cursor.moveToFirst()) {
				// 获取数据库中的文件路径
				path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
			}
			cursor.close();
		}
		return path;
	}

	/**
	 * bitmap 转化成 byte数组
	 */
	public byte[] bitmapToByte(Bitmap bitmap) {
		if (bitmap != null) {
			// 将BitMap对象转化成byte 数组
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
			return baos.toByteArray();
		} else {
			return null;
		}
	}

	/**
	 * 图片内存优化显示
	 */
	public static BitmapFactory.Options bitmapManage(int inSampleSize) {
		// 2.为位图设置100K的缓存

		BitmapFactory.Options opts = new BitmapFactory.Options();

		opts.inTempStorage = new byte[100 * 1024];

		// 3.设置位图颜色显示优化方式

		// ALPHA_8：每个像素占用1byte内存（8位）

		// ARGB_4444:每个像素占用2byte内存（16位）

		// ARGB_8888:每个像素占用4byte内存（32位）

		// RGB_565:每个像素占用2byte内存（16位）

		// Android默认的颜色模式为ARGB_8888，这个颜色模式色彩最细腻，显示质量最高。但同样的，占用的内存//也最大。也就意味着一个像素点占用4个字节的内存。我们来做一个简单的计算题：3200*2400*4
		// bytes //=30M。如此惊人的数字！哪怕生命周期超不过10s，Android也不会答应的。

		opts.inPreferredConfig = Config.RGB_565;

		// 4.设置图片可以被回收，创建Bitmap用于存储Pixel的内存空间在系统内存不足时可以被回收

		opts.inPurgeable = true;

		// 5.设置位图缩放比例

		// width，hight设为原来的四分一（该参数请使用2的整数倍）,这也减小了位图占用的内存大小；例如，一张//分辨率为2048*1536px的图像使用inSampleSize值为4的设置来解码，产生的Bitmap大小约为//512*384px。相较于完整图片占用12M的内存，这种方式只需0.75M内存(假设Bitmap配置为//ARGB_8888)。

		opts.inSampleSize = inSampleSize;

		// 6.设置解码位图的尺寸信息

		opts.inInputShareable = true;
		return opts;
	}

	/**
	 * Uri 路径
	 * 
	 * @param uri
	 * @return
	 * @throws FileNotFoundException
	 */
	public static Bitmap getBitMapByUri(Context context, Uri uri) throws FileNotFoundException {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri), null, options);

		int w = options.outWidth;
		int h = options.outHeight;
		// 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
		float hh = 800f;// 这里设置高度为800f
		float ww = 400f;// 这里设置宽度为480f
		// 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
		// 计算缩放比
		int be = 1;
		if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
			be = Math.round((options.outWidth / hh));
		} else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
			be = Math.round((options.outHeight / hh));
		}

		if (be <= 0) {
			be = 1;
		}
		bitmap = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri), null, bitmapManage(be));
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
		int option = 100;
		int photoSize = 150;// 压缩参照大小 150KB
		if (baos.toByteArray().length / 1024 > photoSize) { // 按照比例缩放完毕后，判断图片质量任然大于100KB，再次进行缩放
			while (baos.toByteArray().length / 1024 > photoSize) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
				baos.reset();// 重置baos即清空baos
				bitmap.compress(Bitmap.CompressFormat.JPEG, option, baos);// 这里压缩options%，把压缩后的数据存放到baos中
				option -= 10;// 每次都减少10
			}
			ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
			bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片

		}
		// 处理后的图片添加时间水印
		// bitmap =
		// watermarkBitmap(bitmap,null,DateUtil.getDateFormatByType(DateUtil.DATE_TYPE18));
		// 将按要求压缩好的图片 覆盖SD卡上面原先图片
		File f = new File(Constant.IMAGE_SD_PATH + DateUtil.getFileUrl(uri.toString()));
		FileOutputStream fOut = new FileOutputStream(f);
		bitmap.compress(Bitmap.CompressFormat.JPEG, option, fOut);
		try {
			fOut.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		bitmap = ThumbnailUtils.extractThumbnail(bitmap, 100, 100);
		return bitmap;
	}

	/**
	 * Uri 路径
	 * 
	 * @param uri
	 * @return
	 * @throws FileNotFoundException
	 */
	public Bitmap getBitMapByUriForShopEnter(Context context, Uri uri) throws FileNotFoundException {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri), null, options);

		int w = options.outWidth;
		int h = options.outHeight;
		// 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
		float hh = Configure.getConfigure().photoResolutionHeight;// 这里设置高度为800f
		float ww = Configure.getConfigure().photoResolutionWidth;// 这里设置宽度为480f
		// 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
		// 计算缩放比
		int be = 1;
		if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
			be = Math.round((options.outWidth / hh));
		} else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
			be = Math.round((options.outHeight / hh));
		}

		if (be <= 0) {
			be = 1;
		}
		bitmap = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri), null, bitmapManage(be));
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
		int option = 100;
		int photoSize = Configure.getConfigure().photoSize;// 压缩参照大小 ·100KB
		if (baos.toByteArray().length / 1024 > photoSize) { // 按照比例缩放完毕后，判断图片质量任然大于100KB，再次进行缩放
			while (baos.toByteArray().length / 1024 > photoSize) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
				baos.reset();// 重置baos即清空baos
				bitmap.compress(Bitmap.CompressFormat.JPEG, option, baos);// 这里压缩options%，把压缩后的数据存放到baos中
				option -= 10;// 每次都减少10
			}
			ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
			bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片

		}
		// 处理后的图片添加时间水印
		bitmap = watermarkBitmap(bitmap, null, DateUtil.getDateFormatByType(DateUtil.DATE_TYPE1));
		// 将按要求压缩好的图片 覆盖SD卡上面原先图片
		File f = new File(Constant.IMAGE_SD_PATH + DateUtil.getFileUrl(uri.toString()));
		FileOutputStream fOut = new FileOutputStream(f);
		bitmap.compress(Bitmap.CompressFormat.JPEG, option, fOut);
		try {
			fOut.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bitmap;
	}

	/**
	 * 退出提报界面或者提报数据后清空内存图片
	 */
	public void imageListClear() {
		mMPicList.clear();
		mPicUpList.clear();
		mImgPathList.clear();
	}

	/**
	 * 删除SD卡上面的图片文件
	 */
	public void deleteImageFromSDcardForCompete() {
		if (mMPicList.size() > 0) {
			for (int i = 0; i < mMPicList.size(); i++) {
				for (int j = 0; j < mMPicList.get(i).size(); j++) {
					FileUtil.getInstances().deleteFileByName(mMPicList.get(i).get(j).getmImageUri());
				}
			}
			mPicUpList.clear();
			mMPicList.clear();
			mImgPathList.clear();
		}
	}

	/**
	 * 删除SD卡上面的图片文件
	 */
	public void deleteImageFromSDcardForOther() {
		if (mPicUpList.size() > 0) {
			for (int i = 0; i < mPicUpList.size(); i++) {
				FileUtil.getInstances().deleteFileByName(mPicUpList.get(i).getmImageUri());
			}
			mPicUpList.clear();
			mImgPathList.clear();
		}
	}

	/**
	 * 线程处理删除图片文件，防止耗时，导致UI体验不流畅
	 * 
	 * @param isCompeteGoods
	 */
	public void deleteImages(final boolean isCompeteGoods) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				if (isCompeteGoods) {
					deleteImageFromSDcardForCompete();
				} else {
					deleteImageFromSDcardForOther();
				}
			}
		}).start();
	}

	/**
	 * 根据图片SD卡路径 读取图片
	 * 
	 * @param context
	 * @param path
	 * @return
	 */
	public Bitmap getBitMapByUri(Context context, String path) {
		// 判断SD是否可用
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			File mfile = new File(path);
			if (mfile.exists()) {// 若该文件存在
				Bitmap bm = BitmapFactory.decodeFile(path);
				return bm;
			}
		}
		return null;
	}

	/**
	 * 根据图片SD卡路径 读取图片
	 * 
	 * @param context
	 * @param path
	 * @return
	 */
	public Bitmap getBitMapFromSDCard(Context context, String path) {
		// 判断SD是否可用
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			File mfile = new File(path);
			if (mfile.exists()) {// 若该文件存在
				Bitmap bm = BitmapFactory.decodeFile(path);
				bm = ThumbnailUtils.extractThumbnail(bm, 100, 100);
				return bm;
			}
		}
		return null;
	}

	// 加水印 也可以加文字
	public static Bitmap watermarkBitmap(Bitmap src, Bitmap watermark, String title) {
		if (src == null) {
			return null;
		}
		int w = src.getWidth();
		int h = src.getHeight();
		// 需要处理图片太大造成的内存超过的问题,这里我的图片很小所以不写相应代码了
		Bitmap newb = Bitmap.createBitmap(w, h, Config.ARGB_8888);// 创建一个新的和SRC长度宽度一样的位图
		Canvas cv = new Canvas(newb);
		cv.drawBitmap(src, 0, 0, null);// 在 0，0坐标开始画入src
		Paint paint = new Paint();
		// 加入图片
		if (watermark != null) {
			int ww = watermark.getWidth();
			int wh = watermark.getHeight();
			paint.setAlpha(50);
			cv.drawBitmap(watermark, w - ww + 5, h - wh + 5, paint);// 在src的右下角画入水印
		}
		// 加入文字
		if (title != null) {
			String familyName = "宋体";
			Typeface font = Typeface.create(familyName, Typeface.BOLD);
			TextPaint textPaint = new TextPaint();
			textPaint.setColor(Color.GREEN);
			textPaint.setTypeface(font);
			textPaint.setTextSize(22);
			float len = textPaint.measureText(title);
			// 这里是自动换行的
			// StaticLayout layout = new
			// StaticLayout(title,textPaint,w,Alignment.ALIGN_NORMAL,1.0F,0.0F,true);
			// layout.draw(cv);
			cv.drawText(title, w - len - 5, h - 20, textPaint);// 在src的右下角写上文字
			// 文字就加左上角算了
			// cv.drawText(title,0,40,paint);
		}
		cv.save(Canvas.ALL_SAVE_FLAG);// 保存
		cv.restore();// 存储
		return newb;
	}

	// 异步加载图片（universal）
	public static void loadPic(String imageUrl, ImageView imageView, Context context) {
		// ProgressBar spinner = ...
		File cacheDir = StorageUtils.getOwnCacheDirectory(context, "UniversalImageLoader/Cache");

		// Get singletone instance of ImageLoader
		ImageLoader imageLoader = ImageLoader.getInstance();

		// Creates display image options for custom display task (all options
		// are optional)
		DisplayImageOptions options = new DisplayImageOptions.Builder().showStubImage(R.drawable.icon_loading) // 设置图片下载期间显示的图片
				.showImageForEmptyUri(R.drawable.icon_loading_failed) // 设置图片Uri为空或是错误的时候显示的图片
				.showImageOnFail(R.drawable.icon_loading_failed) // 设置图片加载或解码过程中发生错误显示的图片
				.cacheInMemory(true) // 设置下载的图片是否缓存在内存中
				.cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
				// .displayer(new RoundedBitmapDisplayer(20)) // 设置成圆角图片
				.build(); // 创建配置过得DisplayImageOption对象
		// Load and display image
		Log.i("zcol", "imageUrl: " + imageUrl);
		imageLoader.displayImage(imageUrl, imageView, options, new ImageLoadingListener() {
			@Override
			public void onLoadingStarted(String imageUri, View view) {
				// TODO Auto-generated method stub
				Log.i("zcol", "onLoadingStarted");
			}

			@Override
			public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
				// TODO Auto-generated method stub

				Log.i("zcol", "failReason: " + failReason);
			}

			@Override
			public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
				// TODO Auto-generated method stub
				Log.i("zcol", "onLoadingComplete");
			}

			@Override
			public void onLoadingCancelled(String imageUri, View view) {
				// TODO Auto-generated method stub
				Log.i("zcol", "onLoadingCancelled");
			}
		});
	}

	/**
	 * Bitmap转换成图片并存到指定文件夹内
	 * 
	 * @param mBitmap
	 * @param filepath
	 *            图片名包括后缀
	 */
	public static void saveBitmap(Bitmap mBitmap, String filepath) {
		File f = new File(filepath);
		FileOutputStream fOut = null;
		try {
			fOut = new FileOutputStream(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
		try {
			fOut.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			fOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据Uri获取图片绝对路径，解决Android4.4以上版本Uri转换
	 * 
	 * @param activity
	 * @param imageUri
	 * @author yaoxing
	 * @date 2014-10-12
	 */
	@TargetApi(19)
	public static String getImageAbsolutePath(Activity activity, Uri imageUri) {
		if (activity == null || imageUri == null)
			return null;
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT
				&& DocumentsContract.isDocumentUri(activity, imageUri)) {
			if (isExternalStorageDocument(imageUri)) {
				String docId = DocumentsContract.getDocumentId(imageUri);
				String[] split = docId.split(":");
				String type = split[0];
				if ("primary".equalsIgnoreCase(type)) {
					return Environment.getExternalStorageDirectory() + "/" + split[1];
				}
			} else if (isDownloadsDocument(imageUri)) {
				String id = DocumentsContract.getDocumentId(imageUri);
				Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),
						Long.valueOf(id));
				return getDataColumn(activity, contentUri, null, null);
			} else if (isMediaDocument(imageUri)) {
				String docId = DocumentsContract.getDocumentId(imageUri);
				String[] split = docId.split(":");
				String type = split[0];
				Uri contentUri = null;
				if ("image".equals(type)) {
					contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
				} else if ("video".equals(type)) {
					contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
				} else if ("audio".equals(type)) {
					contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
				}
				String selection = MediaStore.Images.Media._ID + "=?";
				String[] selectionArgs = new String[] { split[1] };
				return getDataColumn(activity, contentUri, selection, selectionArgs);
			}
		} // MediaStore (and general)
		else if ("content".equalsIgnoreCase(imageUri.getScheme())) {
			// Return the remote address
			if (isGooglePhotosUri(imageUri))
				return imageUri.getLastPathSegment();
			return getDataColumn(activity, imageUri, null, null);
		}
		// File
		else if ("file".equalsIgnoreCase(imageUri.getScheme())) {
			return imageUri.getPath();
		}
		return null;
	}

	public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
		Cursor cursor = null;
		String column = MediaStore.Images.Media.DATA;
		String[] projection = { column };
		try {
			cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
			if (cursor != null && cursor.moveToFirst()) {
				int index = cursor.getColumnIndexOrThrow(column);
				return cursor.getString(index);
			}
		} finally {
			if (cursor != null)
				cursor.close();
		}
		return null;
	}

	/**
	 * @param uri
	 *            The Uri to check.
	 * @return Whether the Uri authority is ExternalStorageProvider.
	 */
	public static boolean isExternalStorageDocument(Uri uri) {
		return "com.android.externalstorage.documents".equals(uri.getAuthority());
	}

	/**
	 * @param uri
	 *            The Uri to check.
	 * @return Whether the Uri authority is DownloadsProvider.
	 */
	public static boolean isDownloadsDocument(Uri uri) {
		return "com.android.providers.downloads.documents".equals(uri.getAuthority());
	}

	/**
	 * @param uri
	 *            The Uri to check.
	 * @return Whether the Uri authority is MediaProvider.
	 */
	public static boolean isMediaDocument(Uri uri) {
		return "com.android.providers.media.documents".equals(uri.getAuthority());
	}

	/**
	 * @param uri
	 *            The Uri to check.
	 * @return Whether the Uri authority is Google Photos.
	 */
	public static boolean isGooglePhotosUri(Uri uri) {
		return "com.google.android.apps.photos.content".equals(uri.getAuthority());
	}

}
