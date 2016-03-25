package com.ddanwang.tourism.db;

import com.ddanwang.tourism.baseDB.orm.annotation.Column;
import com.ddanwang.tourism.baseDB.orm.annotation.Id;
import com.ddanwang.tourism.baseDB.orm.annotation.Table;

/**
 * Created by WeiWenjun
 * 2016/3/23
 * 15:32
 * CityVo
 */
@Table(name = "tab_city_info")
public class CityVo {
    @Id
    @Column(name = "id", type = "INTEGER" )
    private int id;
    @Column(name = "cityName" ,type = "TEXT")   //城市名称
    private String cityName;
    @Column(name = "cityNameEng" ,type = "TEXT")  //城市名称  英文
    private String cityNameEng;
    @Column(name = "cityPic" , type = "TEXT")  //城市照片
    private String cityPic;
    @Column(name = "cityBg" ,type = "TEXT")  //城市背景
    private String cityBg;
    @Column(name = "cityDesc" ,type = "TEXT")  //城市简介
    private String cityDesc;
    @Column(name = "showInMainCity" , type = "INTEGER")    //1显示在主界面攻略，最多三条
    private int showInMainCity;
    @Column(name = "showInMainDest" ,type = "INTEGER")  //1显示在主界面热门景点，只能四条
    private int showInMainDest;
    @Column(name = "haveDest" , type = "INTEGER")  //是否有攻略
    private int haveDest;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityNameEng() {
        return cityNameEng;
    }

    public void setCityNameEng(String cityNameEng) {
        this.cityNameEng = cityNameEng;
    }

    public String getCityPic() {
        return cityPic;
    }

    public void setCityPic(String cityPic) {
        this.cityPic = cityPic;
    }

    public String getCityBg() {
        return cityBg;
    }

    public void setCityBg(String cityBg) {
        this.cityBg = cityBg;
    }

    public String getCityDesc() {
        return cityDesc;
    }

    public void setCityDesc(String cityDesc) {
        this.cityDesc = cityDesc;
    }

    public int getShowInMainCity() {
        return showInMainCity;
    }

    public void setShowInMainCity(int showInMainCity) {
        this.showInMainCity = showInMainCity;
    }

    public int getShowInMainDest() {
        return showInMainDest;
    }

    public void setShowInMainDest(int showInMainDest) {
        this.showInMainDest = showInMainDest;
    }

    public int getHaveDest() {
        return haveDest;
    }

    public void setHaveDest(int haveDest) {
        this.haveDest = haveDest;
    }
}
