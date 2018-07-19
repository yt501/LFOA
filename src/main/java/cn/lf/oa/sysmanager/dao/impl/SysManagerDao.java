package cn.lf.oa.sysmanager.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.lf.oa.base.AbstractBaseDao;
import cn.lf.oa.sysmanager.dao.ISysManagerDao;

/**
 * 重置密码相关持久层
 * @author LFSenior
 *
 */
@Repository
public class SysManagerDao extends AbstractBaseDao implements ISysManagerDao {
	@Override
	protected String getNamespace() {
		return "sysmanager";
	}
	
	@Override
	public void updatePwd(Map param) {
		this.getSqlSession().update(getNamespace()+".updatePwdSql",param);
	}


}
