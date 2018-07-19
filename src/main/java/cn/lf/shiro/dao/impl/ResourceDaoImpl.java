package cn.lf.shiro.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.lf.oa.base.AbstractBaseDao;
import cn.lf.shiro.dao.IResourceDao;

/**
 * 资源持久城实现类
 * @author LFSenior
 *
 */
@Repository
public class ResourceDaoImpl extends AbstractBaseDao implements IResourceDao {

	@Override
	protected String getNamespace() {
		return "authority";
	}

	@Override
	public List<Map> findAllResourceInfo(Map param) {
		return this.getSqlSession().selectList(getNamespace()+".findAllResourceInfoSql", param);
	}

	@Override
	public void addResource(Map param) {
		this.getSqlSession().insert(getNamespace()+".addResourceSql",param);
	}

	@Override
	public void updateResource(Map param) {
		this.getSqlSession().update(getNamespace()+".updateResourceSql",param);
	}

	@Override
	public void deleteResource(Map param) {
		this.getSqlSession().update(getNamespace()+".deleteResourceSql", param);
	}
	
}
