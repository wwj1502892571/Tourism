package com.ddanwang.tourism.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ddanwang.tourism.R;
import com.ddanwang.tourism.db.CityVo;
import com.ddanwang.tourism.util.ImageUtil;

import java.util.List;

/**
 * Created by WeiWenjun
 * 2016/3/23
 * 16:00
 * MainCityGridAdapter
 */
public class MainCityGridAdapter extends BaseAdapter{
    private LayoutInflater inflater;
    private Context mContext;
    private List<CityVo> mList;

    public void setmList(List<CityVo> mList) {
        this.mList = mList;
    }

    public MainCityGridAdapter(Context context){
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        if(mList.size()>0){
            return 3;
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
            convertView = inflater.inflate(R.layout.grid_city_item, null);
            viewHolder = new ViewHolder();
            viewHolder.cityBg = (ImageView)convertView.findViewById(R.id.city_bg) ;
            viewHolder.cityName = (TextView)convertView.findViewById(R.id.city_name);
            viewHolder.cityNameEng = (TextView)convertView.findViewById(R.id.city_name_eng);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ImageUtil.loadPic(vo.getCityBg() , viewHolder.cityBg , mContext);
        viewHolder.cityName.setText(vo.getCityName());
        viewHolder.cityNameEng.setText(vo.getCityNameEng());
        return convertView;
    }

    public static class ViewHolder{
        ImageView cityBg;
        TextView cityName;
        TextView cityNameEng;
    }
}
