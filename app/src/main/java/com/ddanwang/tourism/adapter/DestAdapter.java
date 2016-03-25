package com.ddanwang.tourism.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ddanwang.tourism.R;
import com.ddanwang.tourism.db.DestinationVo;
import com.ddanwang.tourism.util.ImageUtil;

import java.util.List;

/**
 * Created by WeiWenjun
 * 2016/3/24
 * 17:52
 * DestAdapter
 */
public class DestAdapter extends BaseAdapter{
    private LayoutInflater inflater;
    private Context mContext;
    private List<DestinationVo> mDestList;

    public void setmDestList(List<DestinationVo> mDestList) {
        this.mDestList = mDestList;
    }

    public DestAdapter (Context context){
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        if(mDestList.size()>0){
            return mDestList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return mDestList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DestinationVo vo = mDestList.get(position);
        ViewHolder viewHolder = null;
        if(convertView==null){
            inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.list_dest_item, null);
            viewHolder = new ViewHolder();
            viewHolder.mDestImage = (ImageView)convertView.findViewById(R.id.dest_bg) ;
            viewHolder.mDestName = (TextView)convertView.findViewById(R.id.dest_name) ;
            viewHolder.mDestLevel = (TextView)convertView.findViewById(R.id.dest_level) ;
            viewHolder.mIsTicket = (TextView)convertView.findViewById(R.id.dest_is_have_ticket) ;
            viewHolder.mIsHotel = (TextView)convertView.findViewById(R.id.dest_is_have_hotel) ;
            viewHolder.mIsPackage = (TextView)convertView.findViewById(R.id.dest_is_have_package) ;
            viewHolder.mDestPrice = (TextView)convertView.findViewById(R.id.dest_price) ;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ImageUtil.loadPic(vo.getDestPic() , viewHolder.mDestImage ,mContext);
        viewHolder.mDestName.setText(vo.getDestName());
        viewHolder.mDestLevel.setText(vo.getLevel()+"景区");
        if(vo.getIsTicket()==1){
            viewHolder.mIsTicket.setVisibility(View.VISIBLE);
        } else {
            viewHolder.mIsTicket.setVisibility(View.GONE);
        }

        if(vo.getIsHaveTotel()==1){
            viewHolder.mIsHotel.setVisibility(View.VISIBLE);
        } else {
            viewHolder.mIsHotel.setVisibility(View.GONE);
        }

        if(vo.getIsHavePackage()==1){
            viewHolder.mIsPackage.setVisibility(View.VISIBLE);
        } else {
            viewHolder.mIsPackage.setVisibility(View.GONE);
        }

        viewHolder.mDestPrice.setText(vo.getTikect()+"元");
        viewHolder.mDestPrice.setTextColor(Color.RED);
        return convertView;
    }


    public static class ViewHolder{
        private ImageView mDestImage;
        private TextView mDestName;
        private TextView mDestLevel;
        private TextView mIsTicket;
        private TextView mIsHotel;
        private TextView mIsPackage;
        private TextView mDestPrice;

    }
}
