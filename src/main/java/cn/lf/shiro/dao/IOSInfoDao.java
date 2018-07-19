package cn.lf.shiro.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;

/**
 * 系统图表持久层
 * @author LFSenior
 *
 */
@Controller
public interface IOSInfoDao {
	/**系统满意度报告*/
	public List<Map> userStateOption(Map param);
	
	/**访问来源统计*/
	public List<Map> userOASource(Map param);
	
	/**系统用户类别统计*/
	public List<Map> userNumber(Map param);
	
	/**用户操作分布*/
	public List<String> getUserOption(Map param);
	
	/**所有操作*/
	public List<String> getAllOption();
	
	/**用户任务完成量统计*/
	public List<Map> taskNumber(Map param);
	
	/**登录量统计*/
	public List<Map> clickNumber(Map param);
	
	/**插入登录数据*/
	public void insertOSInfo(Map param);

	/**插入用户操作信息*/
	public void insertUserOption(Map param);

	/**加载最新的五条通知*/
	public List<Map> loadNewNotice();

	/**获取最新的5条信息*/
	public List<Map> getNewInfo();

	/**加载用户登录量情况*/
	public List<Map> getClickNumber();

}
