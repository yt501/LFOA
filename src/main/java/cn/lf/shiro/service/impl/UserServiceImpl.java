package cn.lf.shiro.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.lf.oa.personoffice.dao.IContentDao;
import cn.lf.shiro.dao.IUserDao;
import cn.lf.shiro.model.AuthenticaUser;
import cn.lf.shiro.service.IUserService;

/**
 * shiro用户服务实现类
 * @author LFSenior
 *
 */
@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	IUserDao userDao;
	
	@Autowired
	IContentDao contentDao;
	
	public AuthenticaUser findUserByLoginName(String username) {
		return userDao.findUserByLoginName(username);
	}

	@Override
	public void addUser(Map param) {
		//添加用户
		userDao.addUser(param);
		//添加用户角色关系
		userDao.addUserRole(param);
		//添加联系人表
		userDao.addContent(param);
		
	}

	@Override
	public List<Map> findAllUserInfo(Map param) {
		return userDao.findAllUserInfo(param);
	}

	@Override
	public void updateUser(Map param) {
		userDao.updateUser(param);
	}

	@Override
	public void deleteUser(Map param) {
		userDao.deleteUser(param);
	}

	@Override
	public List<Map> findAllDept() {
		return userDao.findAllDept();
	}

}
