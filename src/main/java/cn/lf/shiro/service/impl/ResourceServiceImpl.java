package cn.lf.shiro.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.lf.shiro.dao.IResourceDao;
import cn.lf.shiro.service.IResourceService;

/**
 * 资源服务实现类
 * @author LFSenior
 *
 */
@Service
public class ResourceServiceImpl implements IResourceService {
	@Autowired
	IResourceDao resourceDao;

	@Override
	public List<Map> findAllResourceInfo(Map param) {
		return resourceDao.findAllResourceInfo(param);
	}

	@Override
	public void addResource(Map param) {
		resourceDao.addResource(param);
	}

	@Override
	public void updateResource(Map param) {
		resourceDao.updateResource(param);
	}

	@Override
	public void deleteResource(Map param) {
		resourceDao.deleteResource(param);
	}
	
	
}
