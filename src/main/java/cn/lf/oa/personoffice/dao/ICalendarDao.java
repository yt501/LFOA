package cn.lf.oa.personoffice.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * 日历持久层接口
 * @author LFSenior
 *
 */
@Repository
public interface ICalendarDao {
	/**
	 * 增加日历信息
	 * @param param
	 */
	void addCalendar(Map param);
	
	/**
	 * 跟新日历信息
	 * @param param
	 */
	void updateCalendar(Map param);
	
	/**
	 * 删除日历信息
	 * @param param
	 */
	void deleteCalendar(Map param);
	
	/**
	 * 根据条件查询日历信息
	 * @param param
	 * @return
	 */
	List<Map> findCalendar(Map param);
}
