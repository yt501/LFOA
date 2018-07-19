package cn.lf.oa.personoffice.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * 通讯录持久层接口
 * @author LFSenior
 *
 */
@Repository
public interface IContentDao {
	/**
	 * 查询添加的联系人
	 * @param param
	 * @return
	 */
	List<Map> queryAddContent(Map param);
	
	/**
	 * 查询未添加的联系人
	 * @param param
	 * @return
	 */
	List<Map> queryNotAddContent(Map param);
	
	/**
	 * 增加联系人信息
	 * @param param
	 */
	void addContent(Map param);
	
	/**
	 * 修改联系人信息
	 * @param param
	 */
	void updateContent(Map param);
	
	/**
	 * 删除联系人信息
	 * @param param
	 */
	void deleteContent(Map param);

	/**
	 * 添加联系人到我的通讯录
	 * @param param
	 */
	void addContentToMe(Map param);

	/**
	 * 根据条件查询未添加到通讯录中的联系人信息
	 * @param param
	 * @return
	 */
	List<Map> queryNotAddContentByParam(Map param);

	/**
	 * 从我的通讯录中删除信息
	 * @param param
	 */
	void deleteContentToMe(Map param);
}
