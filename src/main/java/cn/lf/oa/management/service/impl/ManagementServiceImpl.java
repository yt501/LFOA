package cn.lf.oa.management.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.lf.oa.management.dao.IManagementDao;
import cn.lf.oa.management.service.IManagementService;

/**
 * 行政管理服务成接口实现
 * @author LFSenior
 *
 */
@Service
public class ManagementServiceImpl implements IManagementService {
	@Autowired
	IManagementDao managementDao;
	@Override
	public List<Map> queryAllBoardRoom() {
		return managementDao.queryAllBoardRoom();
	}
	@Override
	public void updateBoardroomApply(Map<String, Object> param) {
		managementDao.updateBoardroomApply(param);
	}
	@Override
	public void updateRoomApply(Map<String, Object> param) {
		managementDao.updateRoomApply(param);
	}
	
	@Override
	public void createRoom(Map<String, Object> param) {
		managementDao.createRoom(param);
	}
	
	@Override
	public void updataRoom(Map<String, Object> param) {
		managementDao.updataRoom(param);
	}
	
	@Override
	public void deleteRoom(Map<String, Object> param) {
		managementDao.deleteRoom(param);
	}
	@Override
	public List<Map> findRoomById(Map<String, Object> param) {
		return managementDao.findRoomById(param);
	}
	
	
	
}
