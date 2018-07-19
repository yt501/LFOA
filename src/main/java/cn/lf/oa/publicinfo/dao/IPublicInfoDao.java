package cn.lf.oa.publicinfo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * 公共消息管理持久层接口
 * @author LFSenior
 *
 */
@Repository
public interface IPublicInfoDao {

	/**
	 * 查询所有消息
	 * @param param
	 * @return
	 */
	List<Map> queryAllInfo(Map param);

	/**
	 * 根据id删除信息
	 * @param param
	 */
	void deleteInfoById(Map param);
	
	/**
	 * 增加信息
	 * @param param
	 */
	void addInfo(Map param);
	
	/**
	 * 修改消息
	 * @param param
	 */
	void updateInfo(Map param);
	

	/**
	 * 查询所有公告
	 * @return
	 */
	List<Map> queryAllNotice();
	
	/**
	 * 根据id删除公告
	 * @param param
	 */
	void deleteNoticeById(Map param);
	
	/**
	 * 增加公告
	 * @param param
	 */
	void addNotice(Map param);
	
	/**
	 * 修改公告
	 * @param param
	 */
	void updateNotice(Map param);

	/**
	 * 根据id查找对应消息
	 * @param param
	 * @return
	 */
	List<Map> queryInfoById(Map param);

	/**
	 * 根据作者查询相关信息
	 * @param param
	 * @return
	 */
	List<Map> queryInfoByAuthor(Map param);

	/**
	 * 根据id查询公告
	 * @param param
	 * @return
	 */
	List<Map> queryNoticeById(Map param);

	/**
	 * 根据用户名查询公告
	 * @param param
	 * @return
	 */
	List<Map> queryNoticeByAuthor(Map param);

}
