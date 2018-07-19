package cn.lf.shiro.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * 角色持久城接口
 * @author LFSenior
 *
 */
@Repository
public interface IRoleDao {

	/**
	 * 根据条件查询Role信息
	 * @param param
	 * @return
	 */
	List<Map> findAllRole(Map param);

	/**
	 * 根据条件插座资源信息
	 * @param param
	 * @return
	 */
	List<Map> findAddResourceInfo(Map param);

	/**
	 * 增加用户
	 * @param param
	 * @throws Exception
	 */
	void addRole(Map param) throws Exception;

	/**
	 * 删除用户
	 * @param param
	 */
	void deleteRole(Map param);

	/**
	 * 更新角色
	 * @param param
	 * @throws Exception
	 */
	void updateRole(Map param) throws Exception;

	/**
	 * 根据用户id查找角色id
	 * @param id
	 * @return
	 */
	List<String> findRoleIdListByUserId(String id);

	/**
	 * 根据角色id查询资源列表
	 * @param roleId
	 * @return
	 */
	List<Map<String, String>> findRoleResourceListByRoleId(String roleId);
}
