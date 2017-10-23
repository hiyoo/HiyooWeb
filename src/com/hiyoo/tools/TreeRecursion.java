package com.hiyoo.tools;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.hiyoo.domain.MenuJson;


public class TreeRecursion { //metaDataModelList 传入树形菜单节点,包括:id\name\ParentId
	public static List<MenuJson> recursionTreeJson(List<MenuJson> sourceDataList) {//sourceDataList-所有节点List
		 /*以下代码用于取出最顶级节点*/
	    List<MenuJson> newTreeList = new ArrayList<MenuJson>();  //newTreeList-顶级节点List
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
	    
	     /*以下代码用于获取除顶级节点外的所有节点*/
	    List<MenuJson> paramTree = new ArrayList<MenuJson>(); //paramTree --除顶级节点外的所有节点List
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
	    
	    /*生成最终的树形list（包含list的list）*/
	    List<MenuJson> finalTree = new ArrayList<MenuJson>(); //finalTree 
	    for (int i = 0; i < newTreeList.size(); i++) {
	    	finalTree.add(recursionChildren(newTreeList.get(i), paramTree));
	    } 
	    
	    return finalTree;
	    
	}    
    
    
	//功能:递归遍历List的每一个子节点
	public static MenuJson recursionChildren(MenuJson menuJson, List<MenuJson> list){
        /*如果一个节点的父id等于参数节点的id，则把这个节点写入参数节点的子节点list*/
        List<MenuJson> children = new ArrayList<MenuJson>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getParentId()==menuJson.getId()) {
                children.add(list.get(i));
            }
        }
        /*如果子节点list中有对象存在，则用递归去寻找子节点的子节点，直到不再有子节点*/
        List<MenuJson> param = new ArrayList<MenuJson>();
        for (MenuJson childTree : children) {
            MenuJson grandChild = recursionChildren(childTree,list);
            param.add(grandChild);
        }
        /*最终返回传进来的带有所有子节点的参数节点*/
        menuJson.setChildren(param);
        return menuJson;
    }
	
	public static String getTreeJson(List<MenuJson> sourceDataList) {
		JSONArray json = new JSONArray();
		json.addAll(recursionTreeJson(sourceDataList));
		return json.toJSONString();
	}
}
