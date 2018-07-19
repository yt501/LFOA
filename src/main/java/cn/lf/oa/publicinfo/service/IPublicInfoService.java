package cn.lf.oa.publicinfo.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * 公共消息管理服务接口
 * @author LFSenior
 *
 */
@Service
public interface IPublicInfoService {

	/**
	 * 查询所有消息
	 * @return
	 */
	List<Map> queryAllInfo(Map param);

	/**
	 * 根据id删除信息
	 * 
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
	 * 根据id查找对应信息
	 * @param param
	 * @return
	 */
	List<Map> queryInfoById(Map param);

	/**
	 * 根据author查找对应信息
	 * @param param
	 * @return
	 */
	List<Map> queryInfoByAuthor(Map param);

	/**
	 * 根据id查询通知
	 * @param param
	 * @return
	 */
	
	List<Map> queryNoticeById(Map param);

	/**
	 * 通过名称查询公告
	 * @param param
	 * @return
	 */
	List<Map> queryNoticeByAuthor(Map param);
}
