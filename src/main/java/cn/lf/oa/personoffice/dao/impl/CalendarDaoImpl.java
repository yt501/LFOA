package cn.lf.oa.personoffice.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.lf.oa.base.AbstractBaseDao;
import cn.lf.oa.personoffice.dao.ICalendarDao;

/**
 * 日历持久层实现类
 * @author LFSenior
 *
 */
@Repository
public class CalendarDaoImpl extends AbstractBaseDao implements ICalendarDao {

	@Override
	protected String getNamespace() {
		return "personoffice";
	}

	@Override
	public void addCalendar(Map param) {
		this.getSqlSession().insert(getNamespace()+".addCalendarSql", param);
	}

	@Override
	public void updateCalendar(Map param) {
		this.getSqlSession().update(getNamespace()+".updateCalendarSql", param);
	}

	@Override
	public void deleteCalendar(Map param) {
		this.getSqlSession().update(getNamespace()+".deleteCalendarSql", param);
	}

	@Override
	public List<Map> findCalendar(Map param) {
		return this.getSqlSession().selectList(getNamespace()+".findCalendarSql", param);
	}

}
