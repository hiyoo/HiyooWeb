package com.hiyoo.service.impl;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.hiyoo.domain.IdSequence;

public class IdManager {
	
	private static Map<String,IdSequence> seqMap=new HashMap<String,IdSequence>();
	private static IdManager INSTANCE;
	
	public static IdManager getInstatnce() { //��ȡ����������,ȷ��ϵͳ��ÿ����ֻ��Ψһ��һ��Id����������

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
	//�ж������Ƿ��Ѿ�����
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
	
	/*�������ݱ������ȡ���ֵ����������ˮ��*/
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
	
	/*����������ѯ�������ֵ�������ڣ�������+��ˮ��, prefix������ʽ��2017101900001
	 * @param:
	 * 	prefix: �������
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
