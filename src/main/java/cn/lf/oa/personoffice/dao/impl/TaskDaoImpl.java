package cn.lf.oa.personoffice.dao.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.lf.oa.base.AbstractBaseDao;
import cn.lf.oa.personoffice.dao.ITaskDao;

/**
 * 任务持久层实现类
 * @author LFSenior
 *
 */
@Repository
public class TaskDaoImpl extends AbstractBaseDao implements ITaskDao {

	@Override
	protected String getNamespace() {
		return "personoffice";
	}
	
	@Override
	public List<Map> queryAllTaskInfo(Map param) {
		return this.getSqlSession().selectList(getNamespace()+".queryAllTaskInfoSql", param);
	}

	@Override
	public void updateTaskComple(Map param) {
		this.getSqlSession().update(getNamespace()+".updateTaskCompleSql",param);
	}

	@Override
	public void deleteUserTask(Map param) {
		this.getSqlSession().delete(getNamespace()+".deleteUserTaskSql", param);
	}

	@Override
	public void updateTaskInfo(Map param) {
		this.getSqlSession().update(getNamespace()+".updateTaskInfoSql",param);
	}

	@Override
	public List<Map> queryTaskById(Map param) {
		return this.getSqlSession().selectList(getNamespace()+".queryTaskByIdSql", param);
	}

	@Override
	public void insertTask(Map param) throws Exception{
		this.getSqlSession().insert(getNamespace()+".insertTaskSql", param);
		ObjectMapper mapper=new ObjectMapper();
		String[] userids=mapper.readValue(param.get("userids").toString(), String[].class);
		for (Object userid : userids) {
			Map inParam=new HashMap<>();
			inParam.put("taskid", param.get("id"));
			inParam.put("userid", userid);
			this.getSqlSession().insert(getNamespace()+".insertTaskUserSql", inParam);
		}
		Map inParam=new HashMap<>();
		inParam.put("taskid", param.get("id"));
		inParam.put("userid", param.get("userid"));
		this.getSqlSession().insert(getNamespace()+".insertTaskUserSql", inParam);
	}

	@Override
	public List<Map> queryTaskByName(Map param) {
		return this.getSqlSession().selectList(getNamespace()+".queryTaskByNameSql", param);
	}

	@Override
	public List<Map> queryAllCompleTaskInfo(Map param) {
		return this.getSqlSession().selectList(getNamespace()+".queryAllCompleTaskInfoSql", param);
	}
	

}
