package com.ddanwang.tourism.db;


import com.ddanwang.tourism.baseDB.orm.annotation.Column;
import com.ddanwang.tourism.baseDB.orm.annotation.Id;
import com.ddanwang.tourism.baseDB.orm.annotation.Table;

import java.io.Serializable;
/**
 * 用户信息表
 * @author wangjie
 *
 */
@Table(name = "tab_user_info")
public class UserInfoVo implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id", type = "INTEGER" )
	private int id;
	@Column(name = "nickName" ,type = "TEXT")
	private String nickName;
	@Column(name = "userName" ,type = "TEXT")
	private String userName;
	@Column(name = "passWord" ,type = "TEXT")
	private String passWord;
	@Column(name = "headImage" ,type = "TEXT")
	private String headImage;
	@Column(name="type" ,type = "TEXT")      //判断注册信息
	private String type;
	@Column(name="isLogin" , type = "TEXT")    //是否使用这个账号登陆;注销登录会更改这个字段。
	private String isLogin;          //0为未登录；1为已登录
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getHeadImage() {
		return headImage;
	}
	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIsLogin() {
		return isLogin;
	}
	public void setIsLogin(String isLogin) {
		this.isLogin = isLogin;
	}
}
