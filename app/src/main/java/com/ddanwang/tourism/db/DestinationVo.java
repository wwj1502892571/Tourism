package com.ddanwang.tourism.db;

import com.ddanwang.tourism.baseDB.orm.annotation.Column;
import com.ddanwang.tourism.baseDB.orm.annotation.Id;
import com.ddanwang.tourism.baseDB.orm.annotation.Table;

import java.io.Serializable;

/**
 * Created by WeiWenjun
 * 2016/3/24
 * 11:12
 * DestinationVo
 */
@Table(name = "tab_destination_vo")
public class DestinationVo implements Serializable{
    @Id
    @Column(name = "id", type = "INTEGER" )
    private int id;
    @Column(name = "cityId" , type = "INTEGER")
    private int cityId;
    @Column(name = "destName" ,type = "TEXT")
    private String destName;
    @Column(name = "destNameEng" ,type = "TEXT")
    private String destNameEng;
    @Column(name = "highlights" , type = "TEXT") //亮点
    private String highlights;
    @Column(name = "destAddr" , type = "TEXT") //地址
    private String destAddr;
    @Column(name = "destPic" ,type = "TEXT") //照片
    private String destPic;
    @Column(name = "level" ,type = "TEXT")  //景区评级
    private String level;
    @Column(name = "isTicket" , type = "INTEGER") //是否需要门票
    private int isTicket;
    @Column(name = "tikect" ,type = "TEXT")  //门票价格
    private String tikect;
    @Column(name = "ticketDesc" ,type = "TEXT") //
    private String ticketDesc;
    @Column(name = "openTime" , type = "TEXT") //开放时间
    private String openTime;
    @Column(name = "telephone" , type = "TEXT") //电话
    private String telephone;
    @Column(name = "website" , type = "TEXT") //网站
    private String website;
    @Column(name = "isHotInCity" ,type = "INTEGER") //是否城市热门
    private int isHotInCity;
    @Column(name = "isHaveDest" , type = "INTEGER")  //是否有攻略
    private int isHaveDest;
    @Column(name = "isHavePackage" , type = "INTEGER") //是否有套餐
    private int isHavePackage;
    @Column(name = "isHaveHotel" , type = "INTEGER") //是否有酒店
    private int isHaveTotel;
    @Column(name = "isHaveComment" , type = "INTEGER") //是否有评论
    private int isHaveComment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getDestName() {
        return destName;
    }

    public void setDestName(String destName) {
        this.destName = destName;
    }

    public String getDestNameEng() {
        return destNameEng;
    }

    public void setDestNameEng(String destNameEng) {
        this.destNameEng = destNameEng;
    }

    public String getHighlights() {
        return highlights;
    }

    public void setHighlights(String highlights) {
        this.highlights = highlights;
    }

    public String getDestAddr() {
        return destAddr;
    }

    public void setDestAddr(String destAddr) {
        this.destAddr = destAddr;
    }

    public String getDestPic() {
        return destPic;
    }

    public void setDestPic(String destPic) {
        this.destPic = destPic;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getIsTicket() {
        return isTicket;
    }

    public void setIsTicket(int isTicket) {
        this.isTicket = isTicket;
    }

    public String getTikect() {
        return tikect;
    }

    public void setTikect(String tikect) {
        this.tikect = tikect;
    }

    public String getTicketDesc() {
        return ticketDesc;
    }

    public void setTicketDesc(String ticketDesc) {
        this.ticketDesc = ticketDesc;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public int getIsHaveDest() {
        return isHaveDest;
    }

    public int getIsHotInCity() {
        return isHotInCity;
    }

    public void setIsHotInCity(int isHotInCity) {
        this.isHotInCity = isHotInCity;
    }

    public void setIsHaveDest(int isHaveDest) {
        this.isHaveDest = isHaveDest;
    }

    public int getIsHavePackage() {
        return isHavePackage;
    }

    public void setIsHavePackage(int isHavePackage) {
        this.isHavePackage = isHavePackage;
    }

    public int getIsHaveTotel() {
        return isHaveTotel;
    }

    public void setIsHaveTotel(int isHaveTotel) {
        this.isHaveTotel = isHaveTotel;
    }

    public int getIsHaveComment() {
        return isHaveComment;
    }

    public void setIsHaveComment(int isHaveComment) {
        this.isHaveComment = isHaveComment;
    }
}
