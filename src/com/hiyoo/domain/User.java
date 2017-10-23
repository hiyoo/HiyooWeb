package com.hiyoo.domain;

import java.sql.Timestamp;
import java.util.List;

public class User {
	private int gid;
	private String uid;
	private String name;
	private String pwd;
	private String email;
	private String tel;
	private String mobile;
	private String addr;
	private Timestamp registerTime;
	private Timestamp lastLoginTime;
	private String lastLoginIP;
	private boolean state;
	private String finger;
	private boolean iTel;
	private boolean iEmail;
	private boolean iFinger;
	private List<SystemPermission> permission;
	
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public Timestamp getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(Timestamp registerTime) {
		this.registerTime = registerTime;
	}
	public Timestamp getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Timestamp lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public String getLastLoginIP() {
		return lastLoginIP;
	}
	public void setLastLoginIP(String lastLoginIP) {
		this.lastLoginIP = lastLoginIP;
	}
	
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public String getFinger() {
		return finger;
	}
	public void setFinger(String finger) {
		this.finger = finger;
	}
	public boolean isiTel() {
		return iTel;
	}
	public void setiTel(boolean iTel) {
		this.iTel = iTel;
	}
	public boolean isiEmail() {
		return iEmail;
	}
	public void setiEmail(boolean iEmail) {
		this.iEmail = iEmail;
	}
	public boolean isiFinger() {
		return iFinger;
	}
	public void setiFinger(boolean iFinger) {
		this.iFinger = iFinger;
	}
	public List<SystemPermission> getPermission() {
		return permission;
	}
	public void setPermission(List<SystemPermission> permission) {
		this.permission = permission;
	}
	
	
	
	
}
