package com.ddanwang.tourism.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ddanwang.tourism.R;
import com.ddanwang.tourism.adapter.DestAdapter;
import com.ddanwang.tourism.db.DestinationVo;
import com.ddanwang.tourism.db.dao.DestinationDao;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by WeiWenjun
 * 2016/3/24
 * 17:37
 * DestListActivity
 */
public class DestListActivity extends BaseActivity {
    @InjectView(R.id.city_list_view)
    ListView cityListView;

    private int cityId;
    private DestinationDao destDao;
    private List<DestinationVo> mDestList;
    private DestAdapter destAdapter;

    @Override
    protected int initPageLayoutID() {
        return R.layout.activity_city_list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
    }

    @Override
    protected void process(Bundle savedInstanceState) {
        cityId = getIntent().getIntExtra("CITY_ID",0);
        destDao = new DestinationDao(this);
        mDestList = destDao.getHotDestInfo(cityId);
        destAdapter = new DestAdapter(this);
        destAdapter.setmDestList(mDestList);
        cityListView.setAdapter(destAdapter);
        destAdapter.notifyDataSetChanged();

        cityListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DestinationVo vo = mDestList.get(position);
                Intent intent = new Intent();
                intent.setClass(mContext , DestinationActivity.class);
                intent.putExtra("DESTINATION_VO", vo);
                intent.putExtra(BaseActivity.EXTRA_TITLE, vo.getDestName());
                launchActivity(intent);

            }
        });
    }
}
