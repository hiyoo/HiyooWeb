package com.hiyoo.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.hiyoo.dao.DBUtil;
import com.hiyoo.domain.MenuJson;
import com.hiyoo.domain.MenuJsonAttributes;
import com.hiyoo.domain.SystemMenu;
import com.hiyoo.domain.User;
import com.hiyoo.service.ISystemMenuService;
import com.hiyoo.tools.TreeRecursion;


public class SystemMenuServiceImpl implements ISystemMenuService {

	public String getAllSystemMenu(User user) {
		// TODO Auto-generated method stub
		
		MenuJson iMenuJson=null;
		List<MenuJson> jsonList=new ArrayList<MenuJson>();
		String sql="select menu_id,pmenu_id,menu_name,"+
		            "icon,url from tSystemMenu "+
		            "order by level,order_num";
		List<SystemMenu> list=DBUtil.executeQuery(sql,null,SystemMenu.class);
		for(int i=0,len=list.size();i<len;i++) {
					
			iMenuJson=new MenuJson();
			iMenuJson.setId(((SystemMenu)list.get(i)).getMenu_id());
			iMenuJson.setParentId(((SystemMenu)list.get(i)).getPmenu_id());
			iMenuJson.setText(((SystemMenu)list.get(i)).getMenu_name());
			iMenuJson.setIconCls(((SystemMenu)list.get(i)).getIcon());
			
			if( ("".equals(((SystemMenu)list.get(i)).getUrl()))==false ) {
				MenuJsonAttributes attr=new MenuJsonAttributes();
				attr.setUrl(((SystemMenu)list.get(i)).getUrl());
				iMenuJson.setAttributes(attr);
			}
			
			jsonList.add(iMenuJson);
			
		}	
		
		return TreeRecursion.getTreeJson(jsonList);
	}

	@Override
	public String getMenuByParentID(User user, int parentMenuId) {
		// TODO Auto-generated method stub
		return null;
	}

}
