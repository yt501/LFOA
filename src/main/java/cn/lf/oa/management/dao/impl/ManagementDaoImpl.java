package cn.lf.oa.management.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import cn.lf.oa.base.AbstractBaseDao;
import cn.lf.oa.management.dao.IManagementDao;

/**
 * 行政管理dao层接口实现类
 * @author LFSenior
 *
 */
@Repository
public class ManagementDaoImpl extends AbstractBaseDao implements IManagementDao {
	@Override
	protected String getNamespace() {
		return "management";
	}

	@Override
	public List<Map> queryAllBoardRoom() {
		return this.getSqlSession().selectList(getNamespace()+".queryAllBoardRoomSql");
	}

	@Override
	public void updateBoardroomApply(Map<String, Object> param) {
		this.getSqlSession().update(getNamespace()+".updateBoardroomApplySql", param);
	}

	@Override
	public void updateRoomApply(Map<String, Object> param) {
		this.getSqlSession().update(getNamespace()+".updateRoomApplySql",param);
	}

	@Override
	public void createRoom(Map<String, Object> param) {
		this.getSqlSession().insert(getNamespace()+".createRoomSql",param);
	}

	@Override
	public void updataRoom(Map<String, Object> param) {
		this.getSqlSession().update(getNamespace()+".updataRoomSql", param);
	}

	@Override
	public void deleteRoom(Map<String, Object> param) {
		this.getSqlSession().delete(getNamespace()+".deleteRoomSql",param);
	}

	@Override
	public List<Map> findRoomById(Map<String, Object> param) {
		return this.getSqlSession().selectList(getNamespace()+".findRoomByIdSql",param);
	}
	
	
	
}
