package cn.lf.shiro.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.web.client.RequestMatcher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ser.ResolvableSerializer;

import cn.lf.oa.common.util.ParamUtil;
import cn.lf.shiro.service.IResourceService;
import cn.lf.shiro.service.IRoleService;

/**
 * 角色控制器
 * @author LFSenior
 *
 */
@Controller
@RequestMapping("authority")
public class RoleController {
	@Autowired
	IRoleService roleService;
	
	@Autowired
	IResourceService resourceService;
	/**
	 * 跳转到角色管理界面
	 * @return
	 */
	@RequestMapping("/roleManager")
	public String redicterRoleManager(){
		return "authority/roleManager";
	}
	
	/**
	 * 根据条件查找所有角色
	 * @param request
	 * @return
	 */
	@RequestMapping(value="findAllRole",method=RequestMethod.POST)
	@ResponseBody
	public Map findAllRole(HttpServletRequest request){
		Map reParam=new HashMap<>();
		try {
			Map param=ParamUtil.buildParameter(request);
			List<Map> data=roleService.findAllRole(param);
			reParam.put("data", data);
			reParam.put("state", "T");
		} catch (Exception e) {
			reParam.put("state", "F");
		}
		return reParam;
	}
	
	
	/**
	 * 根据id查询相关角色信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value="findRoleById.json",method=RequestMethod.POST)
	@ResponseBody
	public Map findRoleById(HttpServletRequest request){
		Map reParam=new HashMap<>();
		try {
			Map param=ParamUtil.buildParameter(request);
			List<Map> data=roleService.findAllRole(param);
			if(data==null||data.size()<=0){
				reParam.put("state", "F");
				return reParam;
			}
			List<Map> resource = roleService.findAddResourceInfo(param);
			reParam.put("resources", resource);
			reParam.put("data", data.get(0));
			reParam.put("state", "T");
		} catch (Exception e) {
			reParam.put("state", "F");
		}
		return reParam;
	}
	
	/**
	 * 添加角色信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value="addRole.json",method=RequestMethod.POST)
	@ResponseBody
	public Map addRole(HttpServletRequest request){
		Map reParam=new HashMap<>();
		try {
			Map param=ParamUtil.buildParameter(request);
			param.put("id", ParamUtil.buildUUID());
			roleService.addRole(param);
			List<Map> data = roleService.findAllRole(null);
			reParam.put("data", data);
			reParam.put("state", "T");
		} catch (Exception e) {
			reParam.put("state", "F");
		}
		return reParam;
	}
	
	
	/**
	 * 删除角色
	 * @param request
	 * @return
	 */
	@RequestMapping(value="deleteRole.json",method=RequestMethod.POST)
	@ResponseBody
	public Map deleteRole(HttpServletRequest request){
		Map reParam=new HashMap<>();
		try {
			Map param=ParamUtil.buildParameter(request);
			roleService.deleteRole(param);
			reParam.put("state", "T");
		} catch (Exception e) {
			reParam.put("state", "F");
		}
		return reParam;
	}
	
	
	/**
	 * 更新角色信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value="updateRole.json",method=RequestMethod.POST)
	@ResponseBody
	public Map updateRole(HttpServletRequest request){
		Map reParam=new HashMap<>();
		try {
			Map param=ParamUtil.buildParameter(request);
			roleService.updateRole(param);
			/**查询所有数据*/
			List<Map> data = roleService.findAllRole(null);
			reParam.put("data", data);
			reParam.put("state", "T");
		} catch (Exception e) {
			reParam.put("state", "F");
		}
		return reParam;
	}
	
}
