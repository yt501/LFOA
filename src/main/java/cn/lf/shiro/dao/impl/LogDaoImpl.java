package cn.lf.shiro.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.lf.oa.base.AbstractBaseDao;
import cn.lf.shiro.dao.ILogDao;

/**
 * 日志持久层实现类
 * @author LFSenior
 *
 */
@Repository
public class LogDaoImpl extends AbstractBaseDao implements ILogDao{
	@Override
	protected String getNamespace() {
		return "log";
	}
	
	
	@Override
	public void insertLog(Map param) {
		this.getSqlSession().insert(getNamespace()+".insertLogSql",param);
	}

	
	
}
