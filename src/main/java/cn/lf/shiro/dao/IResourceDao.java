package cn.lf.shiro.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * 资源持久城接口
 * @author LFSenior
 *
 */
@Repository
public interface IResourceDao {

	/**
	 * 根据条件查找资源信息
	 * @param param
	 * @return
	 */
	List<Map> findAllResourceInfo(Map param);

	/**
	 * 增加资源信息
	 * @param param
	 */
	void addResource(Map param);

	/**
	 * 更新资源信息
	 * @param param
	 */
	void updateResource(Map param);

	/**
	 * 删除资源信息
	 * @param param
	 */
	void deleteResource(Map param);

}
