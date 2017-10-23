package com.hiyoo.test;

import com.hiyoo.dao.DBUtil;

public class TestDBUtilUpdate1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String sql="update sss set filed=1";
		DBUtil.executeUpdate(sql, null);
	}

}
