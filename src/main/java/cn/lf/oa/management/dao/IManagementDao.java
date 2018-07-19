package cn.lf.oa.management.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * 行政管理的dao接口
 * @author LFSenior
 *
 */
@Repository
public interface IManagementDao {

	/**
	 * 查询所有会议室信息
	 * @return
	 */
	List<Map> queryAllBoardRoom();

	/**
	 * 申请会议室
	 * @param param
	 */
	void updateBoardroomApply(Map<String, Object> param);

	/**
	 * 管理员恢复会议室
	 * @param param
	 */
	void updateRoomApply(Map<String, Object> param);

	/**
	 * 创建会议室
	 * @param param
	 */
	void createRoom(Map<String, Object> param);

	
	/**
	 * 更新会议室内容
	 * @param param
	 */
	void updataRoom(Map<String, Object> param);

	
	/**
	 * 根据id删除会议室
	 * @param param
	 */
	void deleteRoom(Map<String, Object> param);

	/**
	 * 根据id查询会议室信息
	 * @param param
	 * @return
	 */
	List<Map> findRoomById(Map<String, Object> param);

}
