package cn.lf.oa.personoffice.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.lf.oa.personoffice.dao.ICalendarDao;
import cn.lf.oa.personoffice.service.ICalendarService;

/**
 * 日历服务实现类
 * @author LFSenior
 *
 */
@Service
public class CalendarServiceImpl implements ICalendarService {
	@Autowired
	ICalendarDao calendarDao;

	@Override
	public void addCalendar(Map param) {
		calendarDao.addCalendar(param);
	}

	@Override
	public void updateCalendar(Map param) {
		calendarDao.updateCalendar(param);
	}

	@Override
	public void deleteCalendar(Map param) {
		calendarDao.deleteCalendar(param);
	}

	@Override
	public List<Map> findCalendar(Map param) {
		return calendarDao.findCalendar(param);
	}
}
