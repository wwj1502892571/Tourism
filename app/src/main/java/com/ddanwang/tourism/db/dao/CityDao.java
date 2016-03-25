package com.ddanwang.tourism.db.dao;

import android.content.Context;

import com.ddanwang.tourism.baseDB.orm.dao.AbDBDaoImpl;
import com.ddanwang.tourism.db.CityVo;

import java.util.List;

/**
 * Created by WeiWenjun
 * 2016/3/23
 * 15:44
 * CityDao
 */
public class CityDao extends AbDBDaoImpl<CityVo> {

    public CityDao(Context context) {
        super(new DBSDHelper(context), CityVo.class);
    }

    public long saveUserVO(CityVo vo) {
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

    public List<CityVo> getAll(){
        startReadableDatabase();
        List<CityVo> mList = queryList();
        closeDatabase();
        return mList;
    }

    /**
     * 获取城市信息
     * @serial cityId
     *
     */
    public List<CityVo> getCity(int cityId){
        String sql = "SELECT * FROM tab_city_info WHERE id = " + cityId;
        startReadableDatabase();
        List<CityVo> mList = rawQuery(sql , null ,CityVo.class);
        closeDatabase();
        return mList;
    }

    /**
     * 获取在首页显示的城市信息
     * @serial showInMain
     *
     */
    public List<CityVo> getMainCity(){
        String sql = "SELECT * FROM tab_city_info WHERE showInMainCity = 1";
        startReadableDatabase();
        List<CityVo> mList = rawQuery(sql , null ,CityVo.class);
        closeDatabase();
        return mList;
    }

    /**
     * 获取在首页显示的城市信息
     * @serial showInMain
     *
     */
    public List<CityVo> getMainDest(){
        String sql = "SELECT * FROM tab_city_info WHERE showInMainDest = 1";
        startReadableDatabase();
        List<CityVo> mList = rawQuery(sql , null ,CityVo.class);
        closeDatabase();
        return mList;
    }


}
