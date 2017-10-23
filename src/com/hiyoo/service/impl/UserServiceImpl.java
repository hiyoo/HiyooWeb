package com.hiyoo.service.impl;

import java.util.Date;
import java.util.List;

import com.hiyoo.dao.DBUtil;
import com.hiyoo.domain.SystemPermission;
import com.hiyoo.domain.User;

public class UserServiceImpl {
	private User user;
	public UserServiceImpl(User user) {
		this.user=user;
	}
	
	///用户登录验证入口
	public boolean login() {

		String sql="select gid,uid,name,pwd,email,tel,mobile,addr,registerTime,lastLoginTime," + 
				"lastLoginIP,state,finger,iTel,iEmail,iFinger " + 
				"from tuser where uid=? and pwd=?";
		String [] parameters= { user.getUid(),user.getPwd() };
		
		List<User> list=DBUtil.executeQuery(sql, parameters, User.class);
		
		if (list.size()>0) {
			user.setGid(((User)list.get(0)).getGid());
			user.setName(((User)list.get(0)).getName());
			user.setEmail(((User)list.get(0)).getEmail());
			user.setTel(((User)list.get(0)).getTel());
			user.setMobile(((User)list.get(0)).getMobile());
			user.setAddr(((User)list.get(0)).getAddr());
			user.setRegisterTime(((User)list.get(0)).getRegisterTime());
			//获取登录时间
			//SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
		    java.util.Date loginDate=new Date();       
		    java.sql.Timestamp loginTime=new java.sql.Timestamp(loginDate.getTime()); 
			user.setLastLoginTime(loginTime);
			user.setState(((User)list.get(0)).isState());
			user.setFinger(((User)list.get(0)).getFinger());
			user.setiTel(((User)list.get(0)).isiTel());
			user.setiEmail(((User)list.get(0)).isiEmail());
			user.setiFinger(((User)list.get(0)).isiFinger());
			user.setPermission(getUserPermission(user)); //设置用户权限
			System.out.println(((User)list.get(0)).getName()+" "+((User)list.get(0)).getPwd());
			
			//更新登录时间和登录地址
			if	(updateLoginInfo(user)) {
				return true;
			}else {
				return false;
			}
			
		}else {
			return false;
		}	
	}
	
	public List<User> getAllUser() {

		String sql="select id,username,password from user order by id";
		return DBUtil.executeQuery(sql,null,User.class);
			
	}
	
	public void addUser(User user) {
		/*String sql="insert into user(id,name,password) values(?,?,?)";
		String[] parameters=new String[3];
		parameters[0]=user.getId();
		parameters[1]=user.getName();
		parameters[2]=user.getPassword();
		
		DBExecutor.executeUpdate(sql, parameters);*/
		
	}
	
	public void editUser() {
		/*String sql="update user set name=?, set password=? where id=?";
		String[] parameters=new String[3];
		parameters[0]=user.getName();
		parameters[1]=user.getPassword();
		parameters[2]=user.getId();
		
		DBExecutor.executeUpdate(sql, parameters);*/
	}
	
	public void delUser(String id) {
		String sql="delete from user where uid=id";
		DBUtil.executeUpdate(sql, null);
	}
	
	//获取用户菜单权限
	private List<SystemPermission> getUserPermission(User user){
		String sql="select menu_id,if(iQuery>=1,1,0) iQuery,if(iNew>=1,1,0) iNew,if(iEdit>=1,1,0) iEdit,if(iDelete>=1,1,0) iDelete," + 
				"       if(iAudit>=1,1,0) iAudit,if(iUndo>=1,1,0) iUndo,if(iBack>=1,1,0) iBack " + 
				"from (" + 
				"	select menu_id,iQuery,iNew,iEdit,iDelete,iAudit,iUndo,iBack from trolepriv a " + 
				"	where menu_id in (select menu_id from tCompanyPriv b where b.gid="+user.getGid()+ ") and " + 
				"				exists (select 1 from tuser b,trolerelation c " + 
				"              where b.uid='"+user.getUid()+"' and " + 
				"					 b.uid=c.uid    and " + 
				"                    b.gid=c.gid    and " + 
				"                    c.role_id=a.role_id and " + 
				"                    c.gid=b.gid )" +  
				"	union all " + 
				"	select menu_id,iQuery,iNew,iEdit,iDelete,iAudit,iUndo,iBack from tuserpriv a " + 
				"   where a.uid='"+user.getUid()+"' and " + 
				"         a.menu_id in (select menu_id from tCompanyPriv b where b.gid="+user.getGid()+") " + 
				") a " + 
				"group by menu_id";
		List<SystemPermission> permission=DBUtil.executeQuery(sql, null, SystemPermission.class);
		return permission;
	}
	
	private boolean updateLoginInfo(User user) {
		boolean iSuccess=false;
		String sql="update tUser set lastLoginTime=?,lastLoginIP=? where uid=?";
		String[] parameters= { user.getLastLoginTime().toString(),user.getLastLoginIP(),user.getUid()};

		if (DBUtil.executeUpdate(sql, parameters)) {
			iSuccess=true;
		}else {
			iSuccess=false;
		}
		return iSuccess;
	}
}
