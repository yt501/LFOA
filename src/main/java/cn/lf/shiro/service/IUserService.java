package cn.lf.shiro.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.lf.shiro.model.AuthenticaUser;

/**
 * shior认证的userService
 * @author LFSenior
 *
 */
@Service
public interface IUserService {

	/**
	 * 根据登陆用户名查找用户信息
	 * @param username
	 */
	AuthenticaUser findUserByLoginName(String username);

	/**
	 * 添加用户
	 * @param param
	 */
	void addUser(Map param);

	/**
	 * 根据特定条件查询素有用户信息
	 * @param param
	 * @return
	 */
	List<Map> findAllUserInfo(Map param);

	/**
	 * 修改用户
	 * @param param
	 */
	void updateUser(Map param);

	/**
	 * 删除用户
	 * @param param
	 */
	void deleteUser(Map param);

	/**
	 * 查询所有部门信息
	 * @return
	 */
	List<Map> findAllDept();
}
