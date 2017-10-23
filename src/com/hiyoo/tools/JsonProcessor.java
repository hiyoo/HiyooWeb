package com.hiyoo.tools;

import java.util.List;

import com.alibaba.fastjson.JSONArray;

public class JsonProcessor {
	
	public <T> String getJson(List<T> list) {
		/*将list转换成json字符串，用到ali的fastjosn，是一个比较好用的java json工具*/
		JSONArray json = new JSONArray();
		json.addAll(list);
		return json.toJSONString();
	}
	
}
