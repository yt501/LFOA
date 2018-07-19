package cn.lf.oa.personoffice.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * 任务持久层接口
 * @author LFSenior
 *
 */
@Repository
public interface ITaskDao {

	/**
	 * 查询所有未完成的任务
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
	 * 更新任务信息
	 * @param param
	 */
	void updateTaskInfo(Map param);

	/**
	 * 根据id查询任务信息
	 * @param param
	 * @return
	 */
	List<Map> queryTaskById(Map param);

	/**
	 * 插入任务
	 * @param param
	 * @return
	 * @throws Exception 
	 */
	void insertTask(Map param) throws Exception;

	/**
	 * 根据姓名查询相关信息
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
