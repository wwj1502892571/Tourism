package com.ddanwang.tourism.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ddanwang.tourism.R;
import com.ddanwang.tourism.db.CityVo;
import com.ddanwang.tourism.db.dao.CityDao;
import com.ddanwang.tourism.util.ImageUtil;

import java.util.List;

/**
 * Created by WeiWenjun
 * 2016/3/24
 * 9:39
 * AroundCityActivity
 */
public class AroundCityActivity extends BaseActivity{
    public static final String TAG = "CityListActivity";
    private ListView mListView;
    private List<CityVo> mCityList;
    private CityDao cityDao;
    private AroundCityAdapter cityAdapter;
    @Override
    protected int initPageLayoutID() {
        return R.layout.activity_city_list;
    }

    @Override
    protected void initPageView() {
        mListView = (ListView)findViewById(R.id.city_list_view);
    }

    @Override
    protected void process(Bundle savedInstanceState) {
        cityDao = new CityDao(this);
        mCityList = cityDao.getAll();
        cityAdapter = new AroundCityAdapter(this);
        cityAdapter.setmList(mCityList);
        mListView.setAdapter(cityAdapter);
        cityAdapter.notifyDataSetChanged();


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
    }


    public class AroundCityAdapter extends BaseAdapter {
        private LayoutInflater inflater;
        private Context mContext;
        private List<CityVo> mList;

        public void setmList(List<CityVo> mList) {
            this.mList = mList;
        }

        public AroundCityAdapter(Context context){
            this.mContext = context;
            inflater = LayoutInflater.from(mContext);
        }
        @Override
        public int getCount() {
            if(mList.size()>0){
                return mList.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            CityVo vo = mList.get(position);
            ViewHolder viewHolder = null;
            if (convertView == null) {
                inflater = LayoutInflater.from(parent.getContext());
                convertView = inflater.inflate(R.layout.activity_around_city_item, null);
                viewHolder = new ViewHolder();
                viewHolder.cityBg = (ImageView)convertView.findViewById(R.id.city_bg) ;
                viewHolder.cityNameR = (TextView)convertView.findViewById(R.id.city_name_r);
                viewHolder.cityDescR = (TextView)convertView.findViewById(R.id.city_desc_r);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            ImageUtil.loadPic(vo.getCityBg() , viewHolder.cityBg , mContext);
            viewHolder.cityNameR.setText(vo.getCityName());
            viewHolder.cityDescR.setText(vo.getCityDesc());
            return convertView;
        }
    }

    public static class ViewHolder{
        ImageView cityBg;
        TextView cityNameR;
        TextView cityDescR;
    }


}
