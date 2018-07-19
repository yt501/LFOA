package cn.lf.oa.personoffice.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.lf.oa.personoffice.dao.ITaskDao;
import cn.lf.oa.personoffice.service.ITaskService;

/**
 * 任务服务实现类
 * @author LFSenior
 *
 */
@Service
public class TaskServiceImpl implements ITaskService {

	@Autowired
	ITaskDao taskDao;
	
	@Override
	public List<Map> queryAllTaskInfo(Map param) {
		return taskDao.queryAllTaskInfo(param);
	}

	@Override
	public void updateTaskComple(Map param) {
		taskDao.updateTaskComple(param);
	}

	@Override
	public void deleteUserTask(Map param) {
		taskDao.deleteUserTask(param);
	}

	@Override
	public void updateTaskInfo(Map param) {
		taskDao.updateTaskInfo(param);
	}

	@Override
	public List<Map> queryTaskById(Map param) {
		return taskDao.queryTaskById(param);
	}

	@Override
	public void insertTask(Map param) throws Exception {
		taskDao.insertTask(param);
	}

	@Override
	public List<Map> queryTaskByName(Map param) {
		return taskDao.queryTaskByName(param);
	}

	@Override
	public List<Map> queryAllCompleTaskInfo(Map param) {
		return taskDao.queryAllCompleTaskInfo(param);
	}
	
	

}
