package com.hiyoo.test;

import java.util.List;

import com.hiyoo.dao.DBUtil;
import com.hiyoo.domain.User;

public class TestDBUtil {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<User> myUser=DBUtil.executeQuery("select * from tuser", null, User.class);
		System.out.println("----Util-----------------");
		for(int i=0;i<myUser.size();i++) {
			System.out.println(((User)myUser.get(i)).getGid()+" "+
								((User)myUser.get(i)).getUid()+" "+
								((User)myUser.get(i)).getName()+" "+
								((User)myUser.get(i)).getPwd());
		}
		
		
	}

}
