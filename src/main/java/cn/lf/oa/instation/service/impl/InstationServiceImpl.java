package cn.lf.oa.instation.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.lf.oa.instation.dao.IInstationDao;
import cn.lf.oa.instation.service.IInstationService;

/**
 * 邮箱系统服务实现类
 * @author LFSenior
 *
 */
@Service
public class InstationServiceImpl implements IInstationService {
	@Autowired
	IInstationDao instationDao;

	@Override
	public List<Map> queryEmailInfo(Map param) {
		return instationDao.queryEmailInfo(param);
	}

	@Override
	public List<Map> querySendEmailInfo(Map param) {
		return instationDao.querySendEmailInfo(param);
	}

	@Override
	public void deleteEmailById(Map param) {
		instationDao.deleteEmailById(param);
	}

	@Override
	public void deleteSendEmailById(Map param) {
		instationDao.deleteSendEmailById(param);
	}

	@Override
	public void addNewEmailInfo(Map param) {
		instationDao.addNewEmailInfo(param);
	}

	@Override
	public void deleteAchiveEmail(Map param) {
		instationDao.deleteAchiveEmail(param);
	}

	@Override
	public List<Map> queryEmailInfoById(Map param) {
		return instationDao.queryEmailInfoById(param);
	}

	@Override
	public void updateEmailState(Map param) {
		instationDao.updateEmailState(param);
	}

	@Override
	public void deleteEmalForRubbsh(Map param) {
		instationDao.deleteEmalForRubbsh(param);
	}

	@Override
	public List<Map> queryRubbshEmail(Map param) {
		return instationDao.queryRubbshEmail(param);
	}

	@Override
	public Long queryEmailInfoTotal(Map param) {
		return instationDao.queryEmailInfoTotal(param);
	}
	
}
