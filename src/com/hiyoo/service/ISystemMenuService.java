package com.hiyoo.service;

import com.hiyoo.domain.User;

public interface ISystemMenuService extends IBaseService {
	String getAllSystemMenu(User user);
	String getMenuByParentID(User user,int parentMenuId);
	
}
