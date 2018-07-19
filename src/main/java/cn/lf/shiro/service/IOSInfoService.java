package cn.lf.shiro.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * 系统信息相关服务
 * @author LFSenior
 *
 */
@Service
public interface IOSInfoService {
	/**系统满意度报告*/
	public List<Map> getUserStateOption(Map param);
	
	/**访问来源统计*/
	public List<Map> getUserOASource(Map param);
	
	/**系统用户类别统计*/
	public List<Map> getUserNumber(Map param);
	
	/**用户操作分布*/
	public List<String> getUserOption(Map param);
	
	/**所有操作分布*/
	public List<String> getAllOption();
	
	/**用户任务完成量统计*/
	public List<Map> getTaskNumber(Map param);
	
	/**登录量统计*/
	public List<Map> getClickNumber(Map param);
	
	/**插入登录数据*/
	public void insertOSInfo(Map param);
	
	/**插入用户操作信息*/
	public void insertUserOption(Map param);

	/**获取最新的五条通知*/
	public List<Map> getNewNotice();

	/**获取最新的五条信息*/
	public List<Map> getNewInfo();

	/**加载用户登录情况*/
	public List<Map> getClickNumber();
}
