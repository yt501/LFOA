package cn.lf.oa.personoffice.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.lf.oa.personoffice.dao.IContentDao;
import cn.lf.oa.personoffice.service.IContentService;

/**
 * 通讯录服务层实现
 * @author LFSenior
 *
 */
@Service
public class ContentServiceImpl implements IContentService {
	@Autowired
	IContentDao contentDao;

	@Override
	public List<Map> queryAddContent(Map param) {
		return contentDao.queryAddContent(param);
	}

	@Override
	public List<Map> queryNotAddContent(Map param) {
		return contentDao.queryNotAddContent(param);
	}

	@Override
	public void addContent(Map param) {
		contentDao.addContent(param);
	}

	@Override
	public void updateContent(Map param) {
		contentDao.updateContent(param);
	}

	@Override
	public void deleteContent(Map param) {
		contentDao.deleteContent(param);
	}

	@Override
	public void addContentToMe(Map param) {
		contentDao.addContentToMe(param);
	}

	@Override
	public List<Map> queryNotAddContentByParam(Map param) {
		return contentDao.queryNotAddContentByParam(param);
	}

	@Override
	public void deleteContentToMe(Map param) {
		contentDao.deleteContentToMe(param);
	}
	
}
