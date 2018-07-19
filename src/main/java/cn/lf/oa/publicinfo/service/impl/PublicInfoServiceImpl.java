package cn.lf.oa.publicinfo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.lf.oa.publicinfo.dao.IPublicInfoDao;
import cn.lf.oa.publicinfo.service.IPublicInfoService;

/**
 * 公共消息管理服务实现类
 * @author LFSenior
 *
 */
@Service
public class PublicInfoServiceImpl implements IPublicInfoService {
	@Autowired
	IPublicInfoDao publicInfoDao;

	@Override
	public List<Map> queryAllInfo(Map param) {
		return publicInfoDao.queryAllInfo(param);
	}

	@Override
	public void deleteInfoById(Map param) {
		publicInfoDao.deleteInfoById(param);
	}

	@Override
	public void addInfo(Map param) {
		publicInfoDao.addInfo(param);
	}

	@Override
	public void updateInfo(Map param) {
		publicInfoDao.updateInfo(param);
	}

	@Override
	public List<Map> queryAllNotice() {
		return publicInfoDao.queryAllNotice();
	}

	@Override
	public void deleteNoticeById(Map param) {
		publicInfoDao.deleteNoticeById(param);
	}

	@Override
	public void addNotice(Map param) {
		publicInfoDao.addNotice(param);
	}

	@Override
	public void updateNotice(Map param) {
		publicInfoDao.updateNotice(param);
	}

	@Override
	public List<Map> queryInfoById(Map param) {
		return publicInfoDao.queryInfoById(param);
	}

	@Override
	public List<Map> queryInfoByAuthor(Map param) {
		return publicInfoDao.queryInfoByAuthor(param);
	}

	@Override
	public List<Map> queryNoticeById(Map param) {
		return publicInfoDao.queryNoticeById(param);
	}

	@Override
	public List<Map> queryNoticeByAuthor(Map param) {
		return publicInfoDao.queryNoticeByAuthor(param);
	}
	
	
	
}
