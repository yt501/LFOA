package cn.lf.oa.instation.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * 邮箱系统持久层
 * @author LFSenior
 *
 */
@Repository
public interface IInstationDao {
	/**
	 * 根据条件查询接收的邮件信息
	 * @param param select==0 or select==1 date
	 * @return
	 */
	List<Map> queryEmailInfo(Map param);
	
	/**
	 * 根据条件查询发送的邮件信息
	 * @param param
	 * @return
	 */
	List<Map> querySendEmailInfo(Map param);
	
	/**
	 * 根据id删除邮件
	 * @param param
	 */
	void deleteEmailById(Map param);
	
	/**
	 * 根据id删除发送的邮件
	 * @param param
	 */
	void deleteSendEmailById(Map param);
	
	
	/**
	 * 增加邮件信息
	 * @param param
	 */
	void addNewEmailInfo(Map param);

	/**
	 * 删除用户收到的邮件，既将state的状态设置为2
	 * @param param
	 */
	void deleteAchiveEmail(Map param);

	/**
	 * 根据id查询邮件信息
	 * @param param
	 * @return
	 */
	List<Map> queryEmailInfoById(Map param);

	/**
	 * 更新邮件状态
	 * @param param
	 */
	void updateEmailState(Map param);

	/**
	 * 从回收站删除邮件
	 * @param param
	 */
	void deleteEmalForRubbsh(Map param);

	/**
	 * 查询垃圾箱的邮件
	 * @param param
	 * @return
	 */
	List<Map> queryRubbshEmail(Map param);

	/**
	 * 查询邮件数目
	 * @param param
	 * @return
	 */
	Long queryEmailInfoTotal(Map param);
}
