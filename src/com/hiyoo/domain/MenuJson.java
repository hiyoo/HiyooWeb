package com.hiyoo.domain;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class MenuJson{
	
	@JSONField(ordinal=1)
	private int id;
	
	@JSONField(serialize=false)
	private int parentId;
	@JSONField(ordinal=2)
	private String text;
	
	@JSONField(ordinal=3)
	private String iconCls;
	
	@JSONField(ordinal=4)
	private List<MenuJson> children=new ArrayList<MenuJson>();
	
	@JSONField(ordinal=5)
	private MenuJsonAttributes attributes;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public List<MenuJson> getChildren() {
		return children;
	}
	public void setChildren(List<MenuJson> children) {
		this.children = children;
	}
	
	public void addChildren(MenuJson children1) {
		children.add(children1);
	}
	public MenuJsonAttributes getAttributes() {
		return attributes;
	}
	public void setAttributes(MenuJsonAttributes attributes) {
		this.attributes = attributes;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	
	
}