package com.ddanwang.tourism.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ddanwang.tourism.R;
import com.ddanwang.tourism.adapter.HotDestinationAdapter;
import com.ddanwang.tourism.db.CityVo;
import com.ddanwang.tourism.db.DestinationVo;
import com.ddanwang.tourism.db.dao.CityDao;
import com.ddanwang.tourism.db.dao.DestinationDao;
import com.ddanwang.tourism.util.ImageUtil;
import com.ddanwang.tourism.view.MyGridView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by WeiWenjun
 * 2016/3/24
 * 13:56
 * CityActivity
 */
public class CityActivity extends BaseActivity {
    @InjectView(R.id.city_bg_big)
    ImageView cityBgBig;
    @InjectView(R.id.city_dest)
    TextView cityDest;
    @InjectView(R.id.city_bg_small)
    ImageView cityBgSmall;
    @InjectView(R.id.fram_city_bg)
    FrameLayout framCityBg;
    @InjectView(R.id.hot_destination)
    LinearLayout hotDestination;
    @InjectView(R.id.hot_destination_gridView)
    MyGridView hotDestinationGridView;
    @InjectView(R.id.tunnel_food)
    LinearLayout tunnelFood;
    @InjectView(R.id.tunnel_food_gridView)
    MyGridView tunnelFoodGridView;
    @InjectView(R.id.destination_linear)
    LinearLayout destinationLinear;
    @InjectView(R.id.hotel_linear)
    LinearLayout hotelLinear;
    @InjectView(R.id.food_linear)
    LinearLayout foodLinear;

    private int cityId;
    private CityDao cityDao;
    private List<CityVo> mCityList;

    private DestinationDao destDao;
    private List<DestinationVo> mDestList;
    private HotDestinationAdapter destAdapter;

    @Override
    protected int initPageLayoutID() {
        return R.layout.activity_city_layout;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }

    @Override
    protected void process(Bundle savedInstanceState) {
        cityId = getIntent().getIntExtra("CITY_ID" , 0);
        cityDao = new CityDao(this);
        mCityList = cityDao.getCity(cityId);
        if(mCityList.size()>0){
            CityVo cityVo = mCityList.get(0);
            ImageUtil.loadPic(cityVo.getCityBg(),cityBgBig ,mContext);
            ImageUtil.loadPic(cityVo.getCityPic(),cityBgSmall,mContext);
            cityDest.setText(cityVo.getCityName() + "口袋攻略");
        }

        destDao = new DestinationDao(this);
        mDestList = destDao.getHotDestInfo(cityId);
        destAdapter = new HotDestinationAdapter(this);
        destAdapter.setmDestList(mDestList);
        hotDestinationGridView.setAdapter(destAdapter);
        destAdapter.notifyDataSetChanged();

    }

    @OnClick({R.id.fram_city_bg, R.id.destination_linear , R.id.hotel_linear, R.id.food_linear, R.id.hot_destination, R.id.tunnel_food})
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.fram_city_bg:
                break;
            case R.id.destination_linear:
                break;
            case R.id.hotel_linear:
                break;
            case R.id.food_linear:
                break;
            case R.id.hot_destination:
                intent = new Intent();
                intent.setClass(mContext , DestListActivity.class);
                intent.putExtra("CITY_ID", cityId);
                intent.putExtra(BaseActivity.EXTRA_TITLE, "热门景点");
                launchActivity(intent);
                break;
            case R.id.tunnel_food:
                break;
        }
    }
}
