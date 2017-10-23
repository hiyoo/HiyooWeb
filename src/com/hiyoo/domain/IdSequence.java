package com.hiyoo.domain;

import java.util.List;

import com.hiyoo.dao.DBUtil;

public class IdSequence {
	private String tablename;
	private String groupExpr;
	private String idField;
	private String whereExpr;
	private long currId;
	private long step;
	
	/*初始化：支持根据数据表的主键取最大值，适用于流水号*/
	public IdSequence(String tablename,long step) {
		this.tablename=tablename;
		this.step=step;	
		
		String sql="select groupExpr,idField from tablelist where tablename='"+tablename+"'";
		List<TableList> tblList=DBUtil.executeQuery(sql, null,TableList.class);
		
		if(tblList!=null) {
			groupExpr=((TableList)tblList.get(0)).getGroupExpr();
			idField=((TableList)tblList.get(0)).getIdField();
		}else {
			throw new RuntimeException("获取group by表字段出错。");
		}
		
		
		sql="select max("+idField+") maxId from "+tablename+" group by "+groupExpr;
		currId=DBUtil.executeQuery(sql, null);
		if(currId<=0) {
			currId=0;
		}
	}
	/*初始化：支持根据条件查询主键最大值，适用于：年月日+流水号*/
	public IdSequence(String tablename,long prefixId,long step) {
		this.tablename=tablename;
		this.step=step;	
		
		String sql="select groupExpr,idField from tablelist where tablename='"+tablename+"'";
		List<TableList> tblList=DBUtil.executeQuery(sql, null,TableList.class);
		
		if(tblList!=null) {
			groupExpr=((TableList)tblList.get(0)).getGroupExpr();
			idField=((TableList)tblList.get(0)).getIdField();
		}else {
			throw new RuntimeException("获取group by表字段出错。");
		}
		
		
		sql="select max("+idField+") maxId from "+tablename+" where "+idField+">="+prefixId+" group by "+groupExpr;
		currId=DBUtil.executeQuery(sql, null);
		if(currId<=0) {
			currId=prefixId;
		}
	}
	
	public String getTablename() {
		return tablename;
	}
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public String getGroupExpr() {
		return groupExpr;
	}
	public void setGroupExpr(String groupExpr) {
		this.groupExpr = groupExpr;
	}
	
	public String getIdField() {
		return idField;
	}
	public void setIdField(String idField) {
		this.idField = idField;
	}
	
	public String getWhereExpr() {
		return whereExpr;
	}

	public void setWhereExpr(String whereExpr) {
		this.whereExpr = whereExpr;
	}

	public long getStep() {
		return step;
	}

	public void setStep(long step) {
		this.step = step;
	}

	public long getCurrId() {
		return currId;
	}
	public void setCurrId(long currId) {
		this.currId = currId;
	}

	synchronized public long nextVal() {
		currId=currId+step;
		return currId;
	}

}
