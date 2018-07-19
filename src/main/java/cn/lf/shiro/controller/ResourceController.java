package cn.lf.shiro.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.lf.oa.common.util.ParamUtil;
import cn.lf.shiro.service.IResourceService;

/**
 * 资源管理控制器
 * @author LFSenior
 *
 */
@Controller
@RequestMapping("authority")
public class ResourceController {
	@Autowired
	IResourceService resourceService;
	/**
	 * 条转到资源管理页面
	 * @return
	 */
	@RequestMapping("resourceManager")
	public String redicterResourceManager(){
		return "authority/resourceManager";
	}
	
	
	/**
	 * 根据条件查找相关资源信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value="findAllResourceInfo.json",method=RequestMethod.POST)
	@ResponseBody
	public Map findAllResourceInfo(HttpServletRequest request){
		Map reParam=new HashMap<>();
		try {
			Map param=ParamUtil.buildParameter(request);
			List<Map> data=resourceService.findAllResourceInfo(param);
			reParam.put("data", data);
			reParam.put("state", "T");
		} catch (Exception e) {
			reParam.put("state", "F");
		}
		return reParam;
	}
	
	/**
	 * 查找目录资源
	 * @return
	 */
	@RequestMapping(value="findMenuResource.json",method=RequestMethod.POST)
	@ResponseBody
	public Map findMenuResource(){
		Map reParam=new HashMap<>();
		//传递selectMenu的阐述
		Map param=new HashMap<>();
		param.put("selectMenu", true);
		try {
			List<Map> data = resourceService.findAllResourceInfo(param);
			reParam.put("data", data);
			reParam.put("state", "T");
		} catch (Exception e) {
			reParam.put("state", "F");
		}
		return reParam;
	}
	
	
	/**
	 * 增加资源数据
	 * @param request
	 * @return
	 */
	@RequestMapping(value="addResource.json",method=RequestMethod.POST)
	@ResponseBody
	public Map addResource(HttpServletRequest request){
		Map reParam=new HashMap<>();
		try {
			Map param=ParamUtil.buildParameter(request);
			param.put("id", ParamUtil.buildUUID());
			resourceService.addResource(param);
			List<Map> data = resourceService.findAllResourceInfo(null);
			reParam.put("data", data);
			reParam.put("state", "T");
		} catch (Exception e) {
			reParam.put("state", "F");
		}
		return reParam;
	}
	
	
	/**
	 * 根据id查询资源信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value="findResourceById.json",method=RequestMethod.POST)
	@ResponseBody
	public Map findResourceById(HttpServletRequest request){
		Map reParam=new HashMap<>();
		try {
			Map param=ParamUtil.buildParameter(request);
			List<Map> data = resourceService.findAllResourceInfo(param);
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
	 * 更新资源数据
	 * @param request
	 * @return
	 */
	@RequestMapping(value="updateResource.json",method=RequestMethod.POST)
	@ResponseBody
	public Map updateResource(HttpServletRequest request){
		Map reParam=new HashMap<>();
		try {
			Map param=ParamUtil.buildParameter(request);
			resourceService.updateResource(param);
			List<Map> data = resourceService.findAllResourceInfo(null);
			reParam.put("data", data);
			reParam.put("state", "T");
		} catch (Exception e) {
			reParam.put("state", "F");
		}
		return reParam;
	}
	
	
	/**
	 * 删除资源信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value="deleteResource.json",method=RequestMethod.POST)
	@ResponseBody
	public Map deleteResource(HttpServletRequest request){
		Map reParam=new HashMap<>();
		try {
			Map param=ParamUtil.buildParameter(request);
			resourceService.deleteResource(param);
			reParam.put("state", "T");
		} catch (Exception e) {
			reParam.put("state", "F");
		}
		return reParam;
	}
	
}
