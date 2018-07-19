package cn.lf.shiro.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.lf.shiro.model.AuthenticaUser;

/**
 * 用户相关持久城服务接口
 * @author LFSenior
 *
 */
@Repository
public interface IUserDao {
	
	/**
	 * 根据用户名查询用户信息
	 * @param username
	 * @return
	 */
	AuthenticaUser findUserByLoginName(String username);

	/**
	 * 添加用户
	 * @param param
	 */
	void addUser(Map param);

	/**
	 * 添加用户角色关系
	 * @param param
	 */
	void addUserRole(Map param);

	/**
	 * 添加联系人
	 * @param param
	 */
	void addContent(Map param);

	/**
	 * 根据条件查询所有用户信息
	 * @param param
	 * @return
	 */
	List<Map> findAllUserInfo(Map param);

	
	/**
	 * 更新用户
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
