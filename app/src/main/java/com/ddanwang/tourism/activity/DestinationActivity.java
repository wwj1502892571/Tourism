package com.ddanwang.tourism.activity;

import com.ddanwang.tourism.R;
import com.ddanwang.tourism.db.DestinationVo;

/**
 * Created by WeiWenjun
 * 2016/3/24
 * 16:05
 * DestinationActivity
 */
public class DestinationActivity extends BaseActivity{
    public static final String TAG = "DestinationActivity";
    private DestinationVo destVo;


    @Override
    protected int initPageLayoutID() {
        return R.layout.activity_destination_layout;
    }
}
