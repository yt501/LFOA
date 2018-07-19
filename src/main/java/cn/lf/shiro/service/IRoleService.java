package cn.lf.shiro.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * shiro授权的角色服务类
 * @author LFSenior
 *
 */
@Service
public interface IRoleService {

	/**
	 * 根据用户id查找用户的对应的角色id
	 * @param id
	 * @return
	 */
	List<String> findRoleIdListByUserId(String id);

	/**
	 * 根据角色Id查询角色拥有的资源信息
	 * @param roleId
	 * @return
	 */
	List<Map<String, String>> findRoleResourceListByRoleId(String roleId);
	
	/**
	 * 查询所有角色
	 * @param param
	 * @return
	 */
	List<Map> findAllRole(Map param);

	/**
	 * 根据条件查询添加的资源信息
	 * @param param
	 * @return
	 */
	List<Map> findAddResourceInfo(Map param);

	/**
	 * 添加角色
	 * @param param
	 * @throws Exception 
	 */
	void addRole(Map param) throws Exception;

	/**
	 * 删除橘色
	 * @param param
	 */
	void deleteRole(Map param);

	/**
	 * 跟新角色
	 * @param param
	 */
	void updateRole(Map param) throws Exception;
	
}
