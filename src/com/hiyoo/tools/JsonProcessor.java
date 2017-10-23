package com.hiyoo.tools;

import java.util.List;

import com.alibaba.fastjson.JSONArray;

public class JsonProcessor {
	
	public <T> String getJson(List<T> list) {
		/*��listת����json�ַ������õ�ali��fastjosn����һ���ȽϺ��õ�java json����*/
		JSONArray json = new JSONArray();
		json.addAll(list);
		return json.toJSONString();
	}
	
}
