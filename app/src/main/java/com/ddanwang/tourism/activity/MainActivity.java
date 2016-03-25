package com.ddanwang.tourism.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.widget.Toast;

import com.ddanwang.tourism.R;
import com.ddanwang.tourism.fragment.DestinationFragment;
import com.ddanwang.tourism.fragment.UserInfoFragment;
import com.ddanwang.tourism.util.ActivityUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by WeiWenjun
 * 2016/3/22
 * 17:46
 * MainActivity
 */
public class MainActivity extends BaseActivity {
    @InjectView(R.id.view_pager)
    ViewPager viewPager;
    @InjectView(R.id.tab_layout)
    TabLayout tabLayout;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private List<Fragment> mList;

    @Override
    protected int initPageLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void process(Bundle savedInstanceState) {
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        DestinationFragment f1 = new DestinationFragment();
        UserInfoFragment f2 = new UserInfoFragment();
        mList = new ArrayList<Fragment>();
        mList.add(f1);
        mList.add(f2);
        mSectionsPagerAdapter.setmList(mList);
        viewPager.setAdapter(mSectionsPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        mSectionsPagerAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> mList;

        public void setmList(List<Fragment> mList) {
            this.mList = mList;
        }

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return  mList.get(position);
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "目的地";
                case 1:
                    return "我的";
            }
            return null;
        }
    }

    private static Boolean isExit = false;
    Timer tExit = new Timer();
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            isExit = false;
            tExit.cancel();
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isExit == false) {
                isExit = true;
                Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                if (task != null) {
                    task.cancel();
                }
                task = new TimerTask() {
                    @Override
                    public void run() {
                        isExit = false;
                    }
                };
                tExit.schedule(task, 2000);
            } else {
                finish();
                ActivityUtil.getInstance().exit();// 关闭所有未关闭的activity
                System.exit(0);
            }
        }
        return false;
    }
}
