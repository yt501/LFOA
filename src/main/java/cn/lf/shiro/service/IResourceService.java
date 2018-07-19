package cn.lf.shiro.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * 资源相关服务
 * @author LFSenior
 *
 */
@Service
public interface IResourceService {

	/**
	 * 根据条件查找所有资源信息
	 * @param param
	 * @return
	 */
	List<Map> findAllResourceInfo(Map param);

	/**
	 * 增加资源
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
