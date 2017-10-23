package com.hiyoo.test;

import com.hiyoo.dao.DBUtil;

public class TestDBUtilUpdate2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] sql= {"update tUser set name='tcm1' where name=?"," update tUser1 set name='cml1' where name=?"};
		String[][] param=new String[2][1];
		param[0][0]="tcm";
		param[1][0]="cml1";
		//param=null;
		
		DBUtil.executeUpdate(sql, param);
	}

}
