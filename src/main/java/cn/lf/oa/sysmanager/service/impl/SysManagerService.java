package cn.lf.oa.sysmanager.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.lf.oa.sysmanager.dao.ISysManagerDao;
import cn.lf.oa.sysmanager.service.ISysManagerService;

/**
 * 重置密码相关服务实现类
 * @author LFSenior
 *
 */
@Service
public class SysManagerService implements ISysManagerService {
	@Autowired
	ISysManagerDao sysManagerDao;
	@Override
	public void updatePwd(Map param) {
		sysManagerDao.updatePwd(param);
	}

}
