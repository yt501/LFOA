package cn.lf.shiro.dao.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.lf.oa.base.AbstractBaseDao;
import cn.lf.oa.common.util.ParamUtil;
import cn.lf.shiro.dao.IRoleDao;

/**
 * 角色持久层实现类
 * @author LFSenior
 *
 */
@Repository
public class RoleDaoImpl extends AbstractBaseDao implements IRoleDao {

	@Override
	protected String getNamespace() {
		return "authority";
	}

	@Override
	public List<Map> findAllRole(Map param) {
		return this.getSqlSession().selectList(getNamespace()+".findAllRoleSql",param);
	}

	@Override
	public List<Map> findAddResourceInfo(Map param) {
		return this.getSqlSession().selectList(getNamespace()+".findAddResourceInfoSql",param);
	}

	@Override
	public void addRole(Map param) throws Exception {
		this.getSqlSession().insert(getNamespace()+".addRoleSql", param);
		/*删除存在的关系*/
		this.getSqlSession().delete(getNamespace()+".deleteRoleResourceSql",param);
		/*
		 * 插入关系
		 * */
		ObjectMapper mapper=new ObjectMapper();
		String[] resources=mapper.readValue(param.get("resources").toString(), String[].class);
		for (Object resource : resources) {
			Map inParam=new HashMap<>();
			inParam.put("resourceId", resource);
			inParam.put("roleId", param.get("id"));
			inParam.put("id",ParamUtil.buildUUID());
			/*建立新关系*/
			this.getSqlSession().insert(getNamespace()+".insertRoleResourceSql", inParam);
		}
	}

	@Override
	public void deleteRole(Map param) {
		this.getSqlSession().update(getNamespace()+".deleteRoleSql", param);
	}

	@Override
	public void updateRole(Map param) throws Exception {
		/*更新角色*/
		this.getSqlSession().update(getNamespace()+".updateRoleSql", param);
		/*删除关系*/
		this.getSqlSession().delete(getNamespace()+".deleteRoleResourceSql",param);
		/**建立新关系*/
		ObjectMapper mapper=new ObjectMapper();
		String[] resources=mapper.readValue(param.get("resources").toString(), String[].class);
		for (Object resource : resources) {
			Map inParam=new HashMap<>();
			inParam.put("resourceId", resource);
			inParam.put("roleId", param.get("id"));
			inParam.put("id",ParamUtil.buildUUID());
			/*建立新关系*/
			this.getSqlSession().insert(getNamespace()+".insertRoleResourceSql", inParam);
		}
		
	}

	@Override
	public List<String> findRoleIdListByUserId(String id) {
		return this.getSqlSession().selectList(getNamespace()+".findRoleIdListByUserIdSql", id);
	}

	@Override
	public List<Map<String, String>> findRoleResourceListByRoleId(String roleId) {
		return this.getSqlSession().selectList(getNamespace()+".findRoleResourceListByRoleIdSql",roleId);
	}
	

}
