package com.ddanwang.tourism.activity;

import android.content.Intent;
import android.os.Handler;

import com.ddanwang.tourism.R;


/**
 * Created by WeiWenjun
 * 2016/2/24
 * 10:01
 * SplashActivity
 */
public class SplashActivity extends BaseActivity{
    @Override
    protected int initPageLayoutID() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initPageView() {
        Handler x = new Handler();
        x.postDelayed(new splashhandler(), 2000);
    }

    class splashhandler implements Runnable {

        public void run() {
            Intent intent = new Intent();
            intent.setClass(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.activity_open_enter, R.anim.activity_open_exit);
            SplashActivity.this.finish();
        }
    }
}
