package com.ddanwang.tourism.db.dao;

import android.content.Context;

import com.ddanwang.tourism.baseDB.orm.dao.AbDBDaoImpl;
import com.ddanwang.tourism.db.DestinationVo;

import java.util.List;

/**
 * Created by WeiWenjun
 * 2016/3/24
 * 16:31
 * DestinationDao
 */
public class DestinationDao extends AbDBDaoImpl<DestinationVo> {

    public DestinationDao(Context context) {
        super(new DBSDHelper(context), DestinationVo.class);
    }

    public long saveVO(DestinationVo vo) {
        long id = 0;
        startWritableDatabase(true);
        if (queryOne(vo.getId()) != null) {
            id = update(vo);
        } else {
            id = insert(vo,true);
        }
        closeDatabase();
        return id;
    }

    public List<DestinationVo> getAll(){
        startReadableDatabase();
        List<DestinationVo> mList = queryList();
        closeDatabase();
        return mList;
    }

    /**
     * 查找城市热门景点
     * @param cityId
     * @return
     */
    public List<DestinationVo> getHotDestInfo(int cityId){
        String sql = "SELECT * FROM tab_destination_vo WHERE cityId = " + cityId + " AND isHotInCity = 1 ";
        startReadableDatabase();
        List<DestinationVo> mList = rawQuery(sql , null , DestinationVo.class );
        closeDatabase();
        return mList;
    }

}
