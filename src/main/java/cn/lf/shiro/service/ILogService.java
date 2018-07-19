package cn.lf.shiro.service;

import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * 日志服务接口
 * @author LFSenior
 *
 */
@Service
public interface ILogService {
	/**
	 * 插入日志
	 * @param param
	 */
	public void insertLog(Map param);
}
