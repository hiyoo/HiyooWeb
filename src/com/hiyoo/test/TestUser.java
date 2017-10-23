package com.hiyoo.test;


import java.text.SimpleDateFormat;
import java.util.Date;

import com.hiyoo.domain.SystemPermission;
import com.hiyoo.domain.User;
import com.hiyoo.service.impl.UserServiceImpl;

public class TestUser {
	public static void main(String[] args) {
		byte i=127;
		System.out.println(i);
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Date date=new Date();     //获取一个Date对象
      
		String punchTime = f.format(date);   //格式化后的时间
		 System.out.println(punchTime);
		User user=new User();
		user.setUid("10000001");
		user.setPwd("123456");
		UserServiceImpl service=new UserServiceImpl(user);
		service.login();
		for(int j=0;j<user.getPermission().size();j++) {
			System.out.println("id:"+((SystemPermission)user.getPermission().get(j)).getMenu_id()+
					         " iQuery:"+((SystemPermission)user.getPermission().get(j)).getiQuery());
		}
		
	}
}
