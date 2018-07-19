package cn.lf.shiro.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * 日志持久层接口
 * @author LFSenior
 *
 */
@Repository
public interface ILogDao {
	/**
	 * 插入日志
	 * @param param
	 */
	public void insertLog(Map param);
}
