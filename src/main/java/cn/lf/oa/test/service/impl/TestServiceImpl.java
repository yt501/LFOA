package cn.lf.oa.test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.lf.oa.test.dao.ITestDao;
import cn.lf.oa.test.service.ITestService;

/**
 * 测试实现类
 * @author LFSenior
 *
 */
@Service
public class TestServiceImpl implements ITestService {
	@Autowired
	private ITestDao testDao;
	public String getNumber() {
		return testDao.getNumber();
	}
}
