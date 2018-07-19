package cn.lf.oa.test.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import cn.lf.oa.test.dao.ITestDao;

/**
 * 实现类
 * @author LFSenior
 *
 */
@Repository
public class TestDaoImpl extends SqlSessionDaoSupport implements ITestDao {

	public String getNumber() {
		return getSqlSession().selectOne("test.testMapper");
	}
	
}
