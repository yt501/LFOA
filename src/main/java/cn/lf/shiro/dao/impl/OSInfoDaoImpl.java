package cn.lf.shiro.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.lf.oa.base.AbstractBaseDao;
import cn.lf.shiro.dao.IOSInfoDao;

@Repository
public class OSInfoDaoImpl extends AbstractBaseDao implements IOSInfoDao {
	@Override
	protected String getNamespace() {
		return "osinfo";
	}
	
	@Override
	public List<Map> userStateOption(Map param) {
		return this.getSqlSession().selectList(getNamespace()+".userStateOptionSql");
	}

	@Override
	public List<Map> userOASource(Map param) {
		return this.getSqlSession().selectList(getNamespace()+".userOASourceSql");
	}

	@Override
	public List<Map> userNumber(Map param) {
		return this.getSqlSession().selectList(getNamespace()+".userNumberSql");
	}

	@Override
	public List<String> getUserOption(Map param) {
		return this.getSqlSession().selectList(getNamespace()+".getUserOptionSql",param);
	}
	
	

	@Override
	public List<String> getAllOption() {
		return this.getSqlSession().selectList(getNamespace()+".getAllOptionSql");
	}

	@Override
	public List<Map> taskNumber(Map param) {
		return getSqlSession().selectList(getNamespace()+".clickNumberSql",param);
	}

	@Override
	public List<Map> clickNumber(Map param) {
		return null;
	}

	@Override
	public void insertOSInfo(Map param) {
		getSqlSession().insert(getNamespace()+".insertOSInfoSql", param);
	}

	@Override
	public void insertUserOption(Map param) {
		getSqlSession().insert(getNamespace()+".insertUserOptionSql",param);
	}

	@Override
	public List<Map> loadNewNotice() {
		return this.getSqlSession().selectList(getNamespace()+".loadNewNoticeSql");
	}

	@Override
	public List<Map> getNewInfo() {
		return this.getSqlSession().selectList(getNamespace()+".getNewInfoSql");
	}

	@Override
	public List<Map> getClickNumber() {
		return this.getSqlSession().selectList(getNamespace()+".getClickNumberSql");
	}
	
	
	
}
