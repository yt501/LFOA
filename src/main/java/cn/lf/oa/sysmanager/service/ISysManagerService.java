package cn.lf.oa.sysmanager.service;

import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * 重置密码相关服务接口
 * @author LFSenior
 *
 */
@Service
public interface ISysManagerService {
	/**
	 * 更新密码
	 * @param param
	 */
	public void updatePwd(Map param);
}
