package cn.lf.oa.publicinfo.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.lf.oa.base.AbstractBaseDao;
import cn.lf.oa.publicinfo.dao.IPublicInfoDao;

/**
 * 公共消息管理持久城实现
 * @author LFSenior
 *
 */
@Repository
public class PublicInfoDaoImpl extends AbstractBaseDao implements IPublicInfoDao {

	@Override
	protected String getNamespace() {
		return "publicinfo";
	}

	@Override
	public List<Map> queryAllInfo(Map param) {
		return this.getSqlSession().selectList(getNamespace()+".queryAllInfoSql", param);
	}

	@Override
	public void deleteInfoById(Map param) {
		this.getSqlSession().update(getNamespace()+".deleteInfoByIdSql", param);
	}

	@Override
	public void addInfo(Map param) {
		this.getSqlSession().insert(getNamespace()+".addInfoSql", param);
	}

	@Override
	public void updateInfo(Map param) {
		this.getSqlSession().update(getNamespace()+".updateInfoSql", param);
	}

	@Override
	public List<Map> queryAllNotice() {
		return this.getSqlSession().selectList(getNamespace()+".queryAllNoticeSql");
	}

	@Override
	public void deleteNoticeById(Map param) {
		this.getSqlSession().delete(getNamespace()+".deleteNoticeByIdSql", param);
	}

	@Override
	public void addNotice(Map param) {
		this.getSqlSession().insert(getNamespace()+".addNoticeSql", param);
	}

	@Override
	public void updateNotice(Map param) {
		this.getSqlSession().update(getNamespace()+".updateNotcieSql", param);
	}

	@Override
	public List<Map> queryInfoById(Map param) {
		return this.getSqlSession().selectList(getNamespace()+".queryInfoByIdSql", param);
	}

	@Override
	public List<Map> queryInfoByAuthor(Map param) {
		return this.getSqlSession().selectList(getNamespace()+".queryInfoByAuthorSql", param);
	}

	@Override
	public List<Map> queryNoticeById(Map param) {
		return this.getSqlSession().selectList(getNamespace()+".queryNoticeByIdSql", param);
	}

	@Override
	public List<Map> queryNoticeByAuthor(Map param) {
		return this.getSqlSession().selectList(getNamespace()+".queryNoticeByAuthorSql", param);
	}
	
	
}
