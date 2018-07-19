package cn.lf.shiro.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.lf.oa.base.AbstractBaseDao;
import cn.lf.shiro.dao.IUserDao;
import cn.lf.shiro.model.AuthenticaUser;

/**
 * 用户持久层实现类
 * @author LFSenior
 *
 */
@Repository
public class UserDaoImpl extends AbstractBaseDao implements IUserDao {

	@Override
	protected String getNamespace() {
		return "authority";
	}

	@Override
	public AuthenticaUser findUserByLoginName(String username) {
		return this.getSqlSession().selectOne(getNamespace()+".findUserByLoginNameSql",username);
	}

	@Override
	public void addUser(Map param) {
		this.getSqlSession().insert(getNamespace()+".addUserSql",param);
	}

	@Override
	public void addUserRole(Map param) {
		this.getSqlSession().insert(getNamespace()+".addUserRoleSql",param);
	}

	@Override
	public void addContent(Map param) {
		this.getSqlSession().insert(getNamespace()+".addContentSql",param);
	}

	@Override
	public List<Map> findAllUserInfo(Map param) {
		return this.getSqlSession().selectList(getNamespace()+".findAllUserInfoSql",param);
	}

	@Override
	public void updateUser(Map param) {
		/*更新用户表*/
		this.getSqlSession().update(getNamespace()+".updateUserSql", param);
		/*跟新用户角色表*/
		this.getSqlSession().update(getNamespace()+".updateUserRole", param);
		/*更新联系人表*/
		this.getSqlSession().update(getNamespace()+".updateContent", param);
		
	}

	@Override
	public void deleteUser(Map param) {
		//锁定用户
		this.getSqlSession().update(getNamespace()+".deleteUser",param);
		//锁定通讯录
		this.getSqlSession().update(getNamespace()+".deleteContent",param);
	}

	@Override
	public List<Map> findAllDept() {
		return this.getSqlSession().selectList(getNamespace()+".findAllDeptSql");
	}
}
