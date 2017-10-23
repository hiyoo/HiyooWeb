package com.hiyoo.test;

import com.hiyoo.domain.User;
import com.hiyoo.service.ISystemMenuService;
import com.hiyoo.service.impl.SystemMenuServiceImpl;

public class TestMenuJson {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		User usr=new User();
		ISystemMenuService is=new SystemMenuServiceImpl();
		is.getAllSystemMenu(usr);
	}

}
