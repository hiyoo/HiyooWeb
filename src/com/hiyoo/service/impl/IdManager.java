package com.hiyoo.service.impl;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.hiyoo.domain.IdSequence;

public class IdManager {
	
	private static Map<String,IdSequence> seqMap=new HashMap<String,IdSequence>();
	private static IdManager INSTANCE;
	
	public static IdManager getInstatnce() { //获取单例管理器,确保系统中每个表只有唯一的一个Id生成器对象。

		if(INSTANCE!=null){
				
		}else {
			synchronized(IdManager.class) {
				if(INSTANCE==null) {
					INSTANCE=new IdManager();
				}		
			}
		}
		return INSTANCE;
	}
	//判定对象是否已经存在
	synchronized private IdSequence getExistsSequence(String tablename) {
		IdSequence seq=null;

		Iterator<Map.Entry<String, IdSequence>> iterator = seqMap.entrySet().iterator();
		while (iterator.hasNext()) {
			
			Map.Entry<String,IdSequence> entry = (Entry<String, IdSequence>) iterator.next();
			String key = entry.getKey();
			if(tablename.equals(key)) {
				seq=entry.getValue();
				break;
			}
		}
		
		return seq;
	}
	
	/*根据数据表的主键取最大值，适用于流水号*/
	public long getMaxId(String tablename,long step)  {
		// TODO Auto-generated method stub

		synchronized(seqMap) {
			IdSequence seq=getExistsSequence(tablename);

			if(seq==null) {
				seq=new IdSequence(tablename,step);
				seqMap.put(tablename, seq);
			}
			return seq.nextVal();
		}	
		
	}
	
	/*根据条件查询主键最大值，适用于：年月日+流水号, prefix传入样式：2017101900001
	 * @param:
	 * 	prefix: 序号条件
	 * */
	public long getMaxId(String tablename,long prefixId ,long step)  {
		// TODO Auto-generated method stub

		synchronized(seqMap) {
			IdSequence seq=getExistsSequence(tablename);

			if(seq==null) {
				seq=new IdSequence(tablename,prefixId,step);
				seqMap.put(tablename, seq);
			}
			return seq.nextVal();
		}	
		
	}

}
