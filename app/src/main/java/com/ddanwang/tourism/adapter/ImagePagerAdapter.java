package com.ddanwang.tourism.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ddanwang.tourism.R;
import com.ddanwang.tourism.global.MyApp;
import com.ddanwang.tourism.view.sliding.AbSlidingPlayView;
import com.ddanwang.tourism.view.sliding.RotateableView;

import net.tsz.afinal.FinalBitmap;

import java.util.ArrayList;
import java.util.List;

public class ImagePagerAdapter extends PagerAdapter implements OnClickListener {
	private List<String> urlList = new ArrayList<String>();
	private LayoutInflater inflater;
	private Context mContext;
	private AbSlidingPlayView.AbOnItemClickListener abOnItemClickListener;

	public ImagePagerAdapter(Context context, List<String> urlList) {
		this.urlList = urlList;
		this.mContext = context;
		inflater = LayoutInflater.from(mContext);
	}

	@Override
	public int getCount() {
		if (urlList != null) {
			return urlList.size();
		}
		return 0;
	}

	@Override
	public View instantiateItem(ViewGroup container, final int position) {
		View mPlayView = inflater.inflate(R.layout.image_page_layout_item,
				null);
		mPlayView.setTag(position);
		mPlayView.setOnClickListener(this);
		final RotateableView loading = (RotateableView) mPlayView
				.findViewById(R.id.loading);
		loading.setBackgroundResource(R.drawable.common_loading6_0);
		loading.startAnmation();
		final ImageView image = (ImageView) mPlayView
				.findViewById(R.id.imageView);
		final FinalBitmap finalBitmap = FinalBitmap.create(mContext);
		finalBitmap.configLoadfailImage(MyApp.getBitmapByRourceId(R.drawable.image_loading_big));
		finalBitmap.configLoadingImage(MyApp.getBitmapByRourceId(R.drawable.image_loading_big));
		finalBitmap.display(image, urlList.get(position));
		((ViewPager) container).addView(mPlayView);
		return mPlayView;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}

	@Override
	public void onClick(View v) {
		int position = (Integer) v.getTag();
		if (abOnItemClickListener != null) {
			abOnItemClickListener.onClick(position);
		}
	}

	public AbSlidingPlayView.AbOnItemClickListener getAbOnItemClickListener() {
		return abOnItemClickListener;
	}

	public void setAbOnItemClickListener(
			AbSlidingPlayView.AbOnItemClickListener abOnItemClickListener) {
		this.abOnItemClickListener = abOnItemClickListener;
	}

}
