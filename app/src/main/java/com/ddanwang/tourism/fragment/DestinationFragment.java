package com.ddanwang.tourism.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ddanwang.tourism.R;
import com.ddanwang.tourism.activity.AroundCityActivity;
import com.ddanwang.tourism.activity.BaseActivity;
import com.ddanwang.tourism.activity.CityActivity;
import com.ddanwang.tourism.activity.CityListActivity;
import com.ddanwang.tourism.activity.DestinationActivity;
import com.ddanwang.tourism.adapter.ImagePagerAdapter;
import com.ddanwang.tourism.adapter.MainCityGridAdapter;
import com.ddanwang.tourism.adapter.MainDestinationAdapter;
import com.ddanwang.tourism.db.CityVo;
import com.ddanwang.tourism.db.dao.CityDao;
import com.ddanwang.tourism.view.MyGridView;
import com.ddanwang.tourism.view.sliding.AbSlidingPlayView;
import com.ddanwang.tourism.view.sliding.AbViewUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by WeiWenjun
 * 2016/3/22
 * 17:58
 * DestinationFragment
 */
public class DestinationFragment extends BaseFragment {
    public static final String TAG = "DestinationFragment";
    @InjectView(R.id.pocket_raiders)
    LinearLayout pocketRaiders;
    @InjectView(R.id.city_gridView)
    MyGridView cityGrid;
    @InjectView(R.id.hot_this_week)
    LinearLayout hotThisWeek;
    @InjectView(R.id.tourist_gridView)
    MyGridView touristGrid;
    @InjectView(R.id.here)
    LinearLayout here;
    @InjectView(R.id.around)
    LinearLayout around;

    private MainCityGridAdapter cityGridAdapter;
    private MainDestinationAdapter destGridAdapter;
    private List<CityVo> mCityList;
    private List<CityVo> mDestList;
    private CityDao cityDao;
    private ImagePagerAdapter mImagePagerAdapter;
    private AbSlidingPlayView mSlidingPlayView = null;
    private LinearLayout mPagerIndicator;
    private LayoutInflater mInflater;
    private String[] strings = {"http://img3.3lian.com/2013/s1/20/d/57.jpg",
            "http://image.tianjimedia.com/uploadImages/2012/366/13WI94DIZD5U.jpg",
            "http://img1.3lian.com/2015/w7/98/d/22.jpg",
            "http://img1.3lian.com/2015/w7/90/d/1.jpg",
            "http://file25.mafengwo.net/M00/FB/10/wKgB4lNwQ1eAXFr1AALE2JpaC-E02.jpeg"};

    public DestinationFragment() {
    }

    public DestinationFragment newInstance() {
        DestinationFragment fragment = new DestinationFragment();
        return fragment;
    }

    @Override
    protected int initPageLayoutId() {
        return R.layout.fragment_destination;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    protected void initPageView(View rootView) {
        mSlidingPlayView = (AbSlidingPlayView) rootView.findViewById(R.id.slidingPlayView);
        mSlidingPlayView.setHorizontalGravity(Gravity.CENTER);
        Bitmap nullBitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ALPHA_8);
        mSlidingPlayView.setPageLineImage(nullBitmap, nullBitmap);
        mSlidingPlayView.setPadding(0, 0, 0, 0);
        mPagerIndicator = (LinearLayout) rootView.findViewById(R.id.pager_indicator);
        mSlidingPlayView.startPlay();
        mSlidingPlayView.setOnPageChangeListener(new AbSlidingPlayView.AbOnChangeListener() {
            @Override
            public void onChange(int position) {
                setCurPage(position);
            }
        });
        initPagerIndicator(strings);
    }

    /**
     * 设置当前viewpager页标
     *
     * @param page
     */
    private void setCurPage(final int page) {
        for (int i = 0; i < mPagerIndicator.getChildCount(); i++) {
            if (i == page) {
                ((ImageView) mPagerIndicator.getChildAt(i))
                        .setBackgroundResource(R.drawable.pager_indicator_white_icon);
                continue;
            }
            ((ImageView) mPagerIndicator.getChildAt(i)).setBackgroundResource(R.drawable.pager_indicator_glay_icon);
        }
    }

    private void initPagerIndicator(String[] strings) {
        mPagerIndicator.removeAllViews();
        mSlidingPlayView.removeAllViews();
        List<String> imageUrlList = new ArrayList<String>();
        mSlidingPlayView.setTag(imageUrlList);
        for (int i = 0; i < strings.length; i++) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins((int) AbViewUtil.dip2px(mContext, 3), (int) AbViewUtil.dip2px(mContext, 3),
                    (int) AbViewUtil.dip2px(mContext, 3), (int) AbViewUtil.dip2px(mContext, 3));
            ImageView iv = new ImageView(mContext);
            iv.setLayoutParams(lp);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            iv.setBackgroundResource(R.drawable.pager_indicator_glay_icon);
            mPagerIndicator.addView(iv);
            imageUrlList.add(strings[i]);
            mSlidingPlayView.addView(new View(getActivity()));
        }
        mSlidingPlayView.getViewPager().setCurrentItem(0);
        mImagePagerAdapter = new ImagePagerAdapter(mContext, imageUrlList);
        mSlidingPlayView.getViewPager().setAdapter(mImagePagerAdapter);
        mSlidingPlayView.getViewPager().setCurrentItem(0);
        setCurPage(0);
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.here,R.id.around,R.id.pocket_raiders, R.id.hot_this_week})
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.pocket_raiders:
                intent = new Intent();
                intent.setClass(mContext , CityListActivity.class);
                intent.putExtra(BaseActivity.EXTRA_TITLE, "口袋攻略");
                launchActivity(intent);
                break;
            case R.id.hot_this_week:
                break;
            case R.id.here:
                intent = new Intent();
                intent.setClass(mContext , CityActivity.class);
                intent.putExtra("CITY_ID",1);
                intent.putExtra(BaseActivity.EXTRA_TITLE, "常州");
                launchActivity(intent);
                break;
            case R.id.around:
                intent = new Intent();
                intent.setClass(mContext , AroundCityActivity.class);
                intent.putExtra(BaseActivity.EXTRA_TITLE, "周边热门");
                launchActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    protected void process(Bundle savedInstanceState) {
        cityDao = new CityDao(mContext);
        mCityList = cityDao.getMainCity();
        cityGridAdapter = new MainCityGridAdapter(mContext);
        cityGridAdapter.setmList(mCityList);
        cityGrid.setAdapter(cityGridAdapter);
        cityGridAdapter.notifyDataSetChanged();
        cityGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CityVo vo = mCityList.get(position);
                Intent intent = new Intent();
                intent.setClass(mContext , DestinationActivity.class);
                intent.putExtra("CITY_ID",vo.getId());
                intent.putExtra(BaseActivity.EXTRA_TITLE, vo.getCityName());
                launchActivity(intent);
            }
        });

        mDestList = cityDao.getMainDest();
        destGridAdapter = new MainDestinationAdapter(mContext);
        destGridAdapter.setmList(mDestList);
        touristGrid.setAdapter(destGridAdapter);
        destGridAdapter.notifyDataSetChanged();
    }
}
