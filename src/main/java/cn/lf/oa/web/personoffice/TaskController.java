package cn.lf.oa.web.personoffice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.lf.oa.common.util.ParamUtil;
import cn.lf.oa.personoffice.service.ITaskService;

/**
 * 任务相关控制器
 * @author LFSenior
 *
 */
@Controller
@RequestMapping("personoffice")
public class TaskController {
	@Autowired
	ITaskService taskService;
	/**
	 * 跳转到任务页面
	 * @return
	 */
	@RequestMapping("task")
	public String redicterTask(){
		return "/personoffice/task";
	}
	
	/**
	 * 查询所有未完成的任务信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value="queryAllTaskInfo.json",method=RequestMethod.POST)
	@ResponseBody
	public Map queryAllTaskInfo(HttpServletRequest request){
		Map reParam=new HashMap<>();
		Map param=new HashMap<>();
		try {
			param.put("userid", ParamUtil.buildUserId());
			List<Map> data=taskService.queryAllTaskInfo(param);
			reParam.put("data", data);
			reParam.put("state", "T");
		} catch (Exception e) {
			reParam.put("state", "F");
		}
		
		return reParam;
	}
	
	/**
	 * 完成的任务
	 * @param request
	 * @return
	 */
	@RequestMapping(value="updateTaskComple.json",method=RequestMethod.POST)
	@ResponseBody
	public Map updateTaskComple(HttpServletRequest request){
		Map reParam=new HashMap<>();
		try {
			Map param=ParamUtil.buildParameter(request);
			taskService.updateTaskComple(param);
			reParam.put("state", "T");
		} catch (Exception e) {
			reParam.put("state", "F");
		}
		return reParam;
	}
	
	/**
	 * 删除任务当前用户任务
	 * @param request
	 * @return
	 */
	@RequestMapping(value="deleteUserTask.json",method=RequestMethod.POST)
	@ResponseBody
	public Map deleteUserTask(HttpServletRequest request){
		Map reParam=new HashMap<>();
		try {
			Map param=ParamUtil.buildParameter(request);
			param.put("userid", ParamUtil.buildUserId());
			taskService.deleteUserTask(param);
			reParam.put("state", "T");
		} catch (Exception e) {
			reParam.put("state", "F");
		}
		return reParam;
	}
	
	/**
	 * 更新任务信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value="updateTaskInfo.json",method=RequestMethod.POST)
	@ResponseBody
	public Map updateTaskInfo(HttpServletRequest request){
		Map reParam=new HashMap<>();
		try {
			Map param=ParamUtil.buildParameter(request);
			taskService.updateTaskInfo(param);
			param.put("userid", ParamUtil.buildUserId());
			List<Map> data=taskService.queryAllTaskInfo(param);
			reParam.put("data", data);
			reParam.put("state", "T");
		} catch (Exception e) {
			reParam.put("state", "F");
		}
		return reParam;
	}
	
	
	/**
	 * 根据id查询任务信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value="queryTaskById.json",method=RequestMethod.POST)
	@ResponseBody
	public Map queryTaskById(HttpServletRequest request){
		Map reParam=new HashMap<>();
		try {
			Map param=ParamUtil.buildParameter(request);
			List<Map> data=taskService.queryTaskById(param);
			if(data==null||data.size()<=0){
				reParam.put("state", "F");
				return reParam;
			}
			reParam.put("data", data.get(0));
			reParam.put("state", "T");
		} catch (Exception e) {
			reParam.put("state", "F");
		}
		return reParam;
	}
	
	
	/**
	 * 创建新的任务
	 * @param request
	 * @return
	 */
	@RequestMapping(value="insertTask.json",method=RequestMethod.POST)
	@ResponseBody
	public Map insertTask(HttpServletRequest request){
		Map reParam=new HashMap<>();
		try {
			Map param=ParamUtil.buildParameter(request);
			param.put("userid", ParamUtil.buildUserId());
			param.put("id", ParamUtil.buildUUID());
			taskService.insertTask(param);
			List<Map> data=taskService.queryAllTaskInfo(param);
			reParam.put("data", data);
			reParam.put("state", "T");
		} catch (Exception e) {
			reParam.put("state", "F");
		}
		return reParam;
	}
	
	
	/**
	 * 根据发布人查询任务
	 * @param request
	 * @return
	 */
	@RequestMapping(value="queryTaskByName.json",method=RequestMethod.POST)
	@ResponseBody
	public Map queryTaskByName(HttpServletRequest request){
		Map reParam=new HashMap<>();
		try {
			Map param=ParamUtil.buildParameter(request);
			param.put("userid", ParamUtil.buildUserId());
			List<Map> data=taskService.queryTaskByName(param);
			if(data==null||data.size()==0){
				reParam.put("state", "F");
				return reParam;
			}
			reParam.put("data", data);
			reParam.put("state", "T");
		} catch (Exception e) {
			reParam.put("state", "F");
		}
		return reParam;
	}
	
	/**
	 * 跳转到已完成的任务页面
	 * @return
	 */
	@RequestMapping("completask")
	public String redicterCompleTask(){
		return "/personoffice/completask";
	}
	
	/**
	 * 查询所有完成的任务信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value="queryAllCompleTaskInfo.json",method=RequestMethod.POST)
	@ResponseBody
	public Map queryAllCompleTaskInfo(HttpServletRequest request){
		Map reParam=new HashMap<>();
		Map param=new HashMap<>();
		try {
			param.put("userid", ParamUtil.buildUserId());
			List<Map> data=taskService.queryAllCompleTaskInfo(param);
			reParam.put("data", data);
			reParam.put("state", "T");
		} catch (Exception e) {
			reParam.put("state", "F");
		}
		return reParam;
	}
	
	
	/**
	 * 跟新完成的任务
	 * @param request
	 * @return
	 */
	@RequestMapping(value="updateCompleTaskInfo.json",method=RequestMethod.POST)
	@ResponseBody
	public Map updateCompleTaskInfo(HttpServletRequest request){
		Map reParam=new HashMap<>();
		try {
			Map param=ParamUtil.buildParameter(request);
			taskService.updateTaskInfo(param);
			param.put("userid", ParamUtil.buildUserId());
			List<Map> data=taskService.queryAllCompleTaskInfo(param);
			reParam.put("data", data);
			reParam.put("state", "T");
		} catch (Exception e) {
			reParam.put("state", "F");
		}
		return reParam;
	}
}
