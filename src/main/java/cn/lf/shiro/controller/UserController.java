package cn.lf.shiro.controller;

import java.util.Date;
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
import cn.lf.shiro.service.IUserService;

/**
 * 用户管理相关控制
 * @author LFSenior
 *
 */
@Controller
@RequestMapping(value="authority")
public class UserController {
	@Autowired
	IUserService userService;
	/**
	 * 跳转到用户管理页面
	 * @return
	 */
	@RequestMapping(value="userManager")
	public String redicterUserManager(){
		return "/authority/userManager";
	}
	
	/**
	 * 添加用户
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="addUser.json",method=RequestMethod.POST)
	@ResponseBody
	public Map addUser(HttpServletRequest request){
		Map reParam=new HashMap<>();
		try {
			Map param=ParamUtil.buildParameter(request);
			param.put("id", ParamUtil.buildUUID());
			//createname,createdate
			param.put("createname", ParamUtil.buildUserName());
			param.put("createdate", new Date());
			param.put("password", ParamUtil.buildDefaultPassword());
			userService.addUser(param);
			List<Map> data = userService.findAllUserInfo(null);
			reParam.put("data", data);
			reParam.put("state", "T");
		} catch (Exception e) {
			reParam.put("state", "F");
		}
		return reParam;
	}

	/**
	 * 根据条件查询相关用户情况
	 * @param request
	 * @return
	 */
	@RequestMapping(value="findAllUserInfo.json",method=RequestMethod.POST)
	@ResponseBody
	public Map findAllUserInfo(HttpServletRequest request){
		Map reParam=new HashMap<>();
		try {
			Map param=ParamUtil.buildParameter(request);
			List<Map> data=userService.findAllUserInfo(param);
			reParam.put("data", data);
			reParam.put("state", "T");
		} catch (Exception e) {
			reParam.put("state", "F");
		}
		return reParam;
	}
	
	/**
	 * 修改用户
	 * @param request
	 * @return
	 */
	@RequestMapping(value="editUser.json",method=RequestMethod.POST)
	@ResponseBody
	public Map editUser(HttpServletRequest request){
		Map reParam=new HashMap<>();
		try {
			Map param=ParamUtil.buildParameter(request);
			userService.updateUser(param);
			List<Map> data = userService.findAllUserInfo(null);
			reParam.put("data", data);
			reParam.put("state", "T");
		} catch (Exception e) {
			reParam.put("state", "F");
		}
		return reParam;
	}
	
	/**
	 * 删除用户
	 * @param request
	 * @return
	 */
	@RequestMapping(value="deleteUser.json",method=RequestMethod.POST)
	@ResponseBody
	public Map deleteUser(HttpServletRequest request){
		Map reParam=new HashMap<>();
		try {
			Map param=ParamUtil.buildParameter(request);
			userService.deleteUser(param);
			reParam.put("state", "T");
		} catch (Exception e) {
			reParam.put("state", "F");
		}
		return reParam;
	}
	
	/**
	 * 根据条件搜寻用户
	 * @param request
	 * @return
	 */
	@RequestMapping(value="searchUser.json",method=RequestMethod.POST)
	@ResponseBody
	public Map searchUser(HttpServletRequest request){
		Map reParam=new HashMap<>();
		try {
			Map param=ParamUtil.buildParameter(request);
			List<Map> data = userService.findAllUserInfo(param);
			if(data==null||data.size()<=0){
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
	 * 查询所有部门信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value="findAllDept.json",method=RequestMethod.POST)
	@ResponseBody
	public Map findAllDept(HttpServletRequest request){
		Map reParam=new HashMap<>();
		try {
			Map param=ParamUtil.buildParameter(request);
			List<Map> data=userService.findAllDept();
			reParam.put("data", data);
			reParam.put("state", "T");
		} catch (Exception e) {
			reParam.put("state", "F");
		}
		return reParam;
	}
}
