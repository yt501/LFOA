package cn.lf.shiro.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.lf.shiro.dao.IOSInfoDao;
import cn.lf.shiro.service.IOSInfoService;

/**
 * 系统相关图表服务实现类
 * @author LFSenior
 *
 */
@Service
public class OSInfoServiceImpl implements IOSInfoService {
	@Autowired
	IOSInfoDao osInfoDao;
	
	@Override
	public List<Map> getUserStateOption(Map param) {
		return osInfoDao.userStateOption(param);
	}

	@Override
	public List<Map> getUserOASource(Map param) {
		return osInfoDao.userOASource(param);
	}

	@Override
	public List<Map> getUserNumber(Map param) {
		return osInfoDao.userNumber(param);
	}

	@Override
	public List<String> getUserOption(Map param) {
		return osInfoDao.getUserOption(param);
	}

	@Override
	public List<Map> getTaskNumber(Map param) {
		return osInfoDao.taskNumber(param);
	}

	@Override
	public List<Map> getClickNumber(Map param) {
		return osInfoDao.clickNumber(param);
	}

	@Override
	public void insertOSInfo(Map param) {
		osInfoDao.insertOSInfo(param);
	}

	@Override
	public void insertUserOption(Map param) {
		osInfoDao.insertUserOption(param);
	}

	@Override
	public List<String> getAllOption() {
		return osInfoDao.getAllOption();
	}

	@Override
	public List<Map> getNewNotice() {
		return osInfoDao.loadNewNotice();
	}

	@Override
	public List<Map> getNewInfo() {
		return osInfoDao.getNewInfo();
	}

	@Override
	public List<Map> getClickNumber() {
		return osInfoDao.getClickNumber();
	}
	
	
	
}
