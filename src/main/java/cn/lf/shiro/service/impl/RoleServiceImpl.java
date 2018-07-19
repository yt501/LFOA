package cn.lf.shiro.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.lf.shiro.dao.IRoleDao;
import cn.lf.shiro.service.IRoleService;

/**
 * shrio角色服务实现类
 * @author LFSenior
 *
 */
@Service
public class RoleServiceImpl implements IRoleService{
	@Autowired
	IRoleDao roleDao;
	public List<String> findRoleIdListByUserId(String id) {
		return roleDao.findRoleIdListByUserId(id);
	}

	public List<Map<String, String>> findRoleResourceListByRoleId(String roleId) {
		return roleDao.findRoleResourceListByRoleId(roleId);
	}

	@Override
	public List<Map> findAllRole(Map param) {
		return roleDao.findAllRole(param);
	}

	@Override
	public List<Map> findAddResourceInfo(Map param) {
		return roleDao.findAddResourceInfo(param);
	}

	@Override
	public void addRole(Map param) throws Exception {
		roleDao.addRole(param);
	}

	@Override
	public void deleteRole(Map param) {
		roleDao.deleteRole(param);
	}

	@Override
	public void updateRole(Map param) throws Exception {
		roleDao.updateRole(param);
	}

}
