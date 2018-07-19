package cn.lf.oa.instation.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.lf.oa.base.AbstractBaseDao;
import cn.lf.oa.instation.dao.IInstationDao;

/**
 * 邮箱系统给持久层实现
 * @author LFSenior
 *
 */
@Repository
public class InstationDaoImpl extends AbstractBaseDao implements IInstationDao {
	
	@Override
	protected String getNamespace() {
		return "instation";
	}
	
	@Override
	public List<Map> queryEmailInfo(Map param) {
		return this.getSqlSession().selectList(getNamespace()+".queryEmailInfoSql", param);
	}

	@Override
	public List<Map> querySendEmailInfo(Map param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteEmailById(Map param) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteSendEmailById(Map param) {
		
	}

	@Override
	public void addNewEmailInfo(Map param) {
		this.getSqlSession().insert(getNamespace()+".addNewEmailInfoSql",param);
		/*插入邮件用户关系*/
		this.getSqlSession().insert(getNamespace()+".addEmailUserInfoSql",param);
	}

	@Override
	public void deleteAchiveEmail(Map param) {
		this.getSqlSession().update(getNamespace()+".deleteAchiveEmailSql", param);
	}

	@Override
	public List<Map> queryEmailInfoById(Map param) {
		return this.getSqlSession().selectList(getNamespace()+".queryEmailInfoByIdSql", param);
	}

	@Override
	public void updateEmailState(Map param) {
		this.getSqlSession().update(getNamespace()+".updateEmailStateSql",param);
	}

	@Override
	public void deleteEmalForRubbsh(Map param) {
		this.getSqlSession().delete(getNamespace()+".deleteEmalForRubbshSql",param);
	}

	@Override
	public List<Map> queryRubbshEmail(Map param) {
		return this.getSqlSession().selectList(getNamespace()+".queryRubbshEmailSql", param);
	}

	@Override
	public Long queryEmailInfoTotal(Map param) {
		return this.getSqlSession().selectOne(getNamespace()+".queryEmailInfoTotalSql", param);
	}
	
}
