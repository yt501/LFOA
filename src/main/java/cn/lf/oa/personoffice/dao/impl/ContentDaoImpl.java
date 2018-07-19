package cn.lf.oa.personoffice.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.lf.oa.base.AbstractBaseDao;
import cn.lf.oa.personoffice.dao.IContentDao;

/**
 * 通讯录持久层实现
 * @author LFSenior
 *
 */
@Repository
public class ContentDaoImpl extends AbstractBaseDao implements IContentDao {

	@Override
	protected String getNamespace() {
		return "personoffice";
	}
	
	@Override
	public List<Map> queryAddContent(Map param) {
		return this.getSqlSession().selectList(getNamespace()+".queryAddContentSql", param);
	}

	@Override
	public List<Map> queryNotAddContent(Map param) {
		return this.getSqlSession().selectList(getNamespace()+".queryNotAddContentSql", param);
	}

	@Override
	public void addContent(Map param) {
		this.getSqlSession().insert(getNamespace()+".addContentSql", param);
	}

	@Override
	public void updateContent(Map param) {
		this.getSqlSession().update(getNamespace()+".updateContentSql", param);
	}

	@Override
	public void deleteContent(Map param) {
		this.getSqlSession().delete(getNamespace()+".deleteContentSql", param);
	}

	@Override
	public void addContentToMe(Map param) {
		this.getSqlSession().insert(getNamespace()+".addContentToMeSql", param);
	}

	@Override
	public List<Map> queryNotAddContentByParam(Map param) {
		return this.getSqlSession().selectList(getNamespace()+".queryNotAddContentByParamSql", param);
	}

	@Override
	public void deleteContentToMe(Map param) {
		this.getSqlSession().delete(getNamespace()+".deleteContentToMeSql", param);
	}
	
	
}
