package cn.lf.shiro.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.lf.shiro.dao.ILogDao;
import cn.lf.shiro.service.ILogService;

/**
 * 日志服务实现类
 * @author LFSenior
 *
 */
@Service
public class LogServiceImpl implements ILogService {
	@Autowired
	ILogDao logDao;
	@Override
	public void insertLog(Map param) {
		logDao.insertLog(param);
	}

}
