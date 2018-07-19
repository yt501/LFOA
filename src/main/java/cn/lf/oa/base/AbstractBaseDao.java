package cn.lf.oa.base;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public abstract class AbstractBaseDao extends SqlSessionDaoSupport{
	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;

	@PostConstruct
	public void SqlSessionFactory() {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
	protected abstract String getNamespace();
}
