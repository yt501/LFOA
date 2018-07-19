package cn.lf.oa.sysmanager.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * 重置密码相关持久层
 * @author LFSenior
 *
 */
@Repository
public interface ISysManagerDao {
	/**
	 * 更新密码
	 * @param param
	 */
	public void updatePwd(Map param);
}
