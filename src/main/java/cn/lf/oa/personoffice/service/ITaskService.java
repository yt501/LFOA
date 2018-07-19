package cn.lf.oa.personoffice.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
/**
 * 任务服务接口
 * @author LFSenior
 *
 */
@Service
public interface ITaskService {

	/**
	 * 查询所有未完成的任务信息
	 * @param param
	 * @return
	 */
	List<Map> queryAllTaskInfo(Map param);

	/**
	 * 完成任务
	 * @param param
	 */
	void updateTaskComple(Map param);

	/**
	 * 删除用户任务
	 * @param param
	 */
	void deleteUserTask(Map param);

	/**
	 * 修改任务信息
	 * @param param
	 */
	void updateTaskInfo(Map param);

	/**
	 * 根据id查询task信息
	 * @param param
	 * @return
	 */
	List<Map> queryTaskById(Map param);

	/**
	 * 插入任务
	 * @param param
	 * @throws Exception 
	 */
	void insertTask(Map param) throws Exception;

	/**
	 * 根据name查询task详情
	 * @param param
	 * @return
	 */
	List<Map> queryTaskByName(Map param);

	/**
	 * 查询所有完成的任务
	 * @param param
	 * @return
	 */
	List<Map> queryAllCompleTaskInfo(Map param);

}
