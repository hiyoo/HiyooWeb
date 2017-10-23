package com.hiyoo.tools;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.hiyoo.domain.MenuJson;


public class TreeRecursion { //metaDataModelList �������β˵��ڵ�,����:id\name\ParentId
	public static List<MenuJson> recursionTreeJson(List<MenuJson> sourceDataList) {//sourceDataList-���нڵ�List
		 /*���´�������ȡ������ڵ�*/
	    List<MenuJson> newTreeList = new ArrayList<MenuJson>();  //newTreeList-�����ڵ�List
	    for (int i = 0,len=sourceDataList.size(); i < len; i++) {
	        if (sourceDataList.get(i).getParentId() == 0 ){
	            MenuJson newMenuJson = new MenuJson();
	            newMenuJson.setId(sourceDataList.get(i).getId());
	            newMenuJson.setText(sourceDataList.get(i).getText());
	            newMenuJson.setIconCls(sourceDataList.get(i).getIconCls());
	            newMenuJson.setChildren(null);
	            newMenuJson.setAttributes(sourceDataList.get(i).getAttributes());
	            newMenuJson.setParentId(0);
	            newTreeList.add(newMenuJson);
	        }
	    }
	    
	     /*���´������ڻ�ȡ�������ڵ�������нڵ�*/
	    List<MenuJson> paramTree = new ArrayList<MenuJson>(); //paramTree --�������ڵ�������нڵ�List
	    for (int i = 0; i < sourceDataList.size(); i++) {
	    	if (sourceDataList.get(i).getParentId() != 0) {
	    		MenuJson paramMenuJson = new MenuJson();
	    		paramMenuJson.setId(sourceDataList.get(i).getId());
	    		paramMenuJson.setParentId(sourceDataList.get(i).getParentId());
	    		paramMenuJson.setText(sourceDataList.get(i).getText());
	    		paramMenuJson.setIconCls(sourceDataList.get(i).getIconCls());
	    		paramMenuJson.setChildren(null);
	    		paramMenuJson.setAttributes(sourceDataList.get(i).getAttributes());
	    		paramTree.add(paramMenuJson);
	    	}
	    }
	    
	    /*�������յ�����list������list��list��*/
	    List<MenuJson> finalTree = new ArrayList<MenuJson>(); //finalTree 
	    for (int i = 0; i < newTreeList.size(); i++) {
	    	finalTree.add(recursionChildren(newTreeList.get(i), paramTree));
	    } 
	    
	    return finalTree;
	    
	}    
    
    
	//����:�ݹ����List��ÿһ���ӽڵ�
	public static MenuJson recursionChildren(MenuJson menuJson, List<MenuJson> list){
        /*���һ���ڵ�ĸ�id���ڲ����ڵ��id���������ڵ�д������ڵ���ӽڵ�list*/
        List<MenuJson> children = new ArrayList<MenuJson>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getParentId()==menuJson.getId()) {
                children.add(list.get(i));
            }
        }
        /*����ӽڵ�list���ж�����ڣ����õݹ�ȥѰ���ӽڵ���ӽڵ㣬ֱ���������ӽڵ�*/
        List<MenuJson> param = new ArrayList<MenuJson>();
        for (MenuJson childTree : children) {
            MenuJson grandChild = recursionChildren(childTree,list);
            param.add(grandChild);
        }
        /*���շ��ش������Ĵ��������ӽڵ�Ĳ����ڵ�*/
        menuJson.setChildren(param);
        return menuJson;
    }
	
	public static String getTreeJson(List<MenuJson> sourceDataList) {
		JSONArray json = new JSONArray();
		json.addAll(recursionTreeJson(sourceDataList));
		return json.toJSONString();
	}
}
