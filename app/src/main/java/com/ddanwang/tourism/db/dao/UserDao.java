package com.ddanwang.tourism.db.dao;

import android.content.Context;

import com.ddanwang.tourism.baseDB.orm.dao.AbDBDaoImpl;
import com.ddanwang.tourism.db.UserInfoVo;

import java.util.List;

/**
 * 用户信息表操作
 * @author rui
 *
 */
public class UserDao extends AbDBDaoImpl<UserInfoVo> {
	
	public UserDao(Context context) {
		super(new DBSDHelper(context), UserInfoVo.class);
	}
	
	public long saveUserVO(UserInfoVo vo) {
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
	
	public List<UserInfoVo> getAllUserInfo(){
		startReadableDatabase();
		List<UserInfoVo> lst = queryList();
		closeDatabase();
		return lst;
	}
	
	public UserInfoVo getUserVO() {
		startReadableDatabase();
		List<UserInfoVo> lst = queryList();
		closeDatabase();
		if (lst != null && lst.size() > 0 ) {
			for(int i = 0;i<lst.size();i++){
				if(lst.get(i).getIsLogin().equals("1")){
					return lst.get(i);
				}
			}
		}
		return new UserInfoVo();
	}

	public void deleteUserInfo(){
		startWritableDatabase(true);
		deleteAll();
		closeDatabase();
	}

}
