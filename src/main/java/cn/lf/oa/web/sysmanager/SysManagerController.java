package cn.lf.oa.web.sysmanager;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.lf.oa.common.util.ParamUtil;
import cn.lf.oa.sysmanager.service.ISysManagerService;

/**
 * 修改密码相关控制
 * @author LFSenior
 *
 */
@Controller
@RequestMapping("sysmanager")
public class SysManagerController {
	@Autowired
	ISysManagerService sysManagerService;
	
	/**
	 * 跳转到重置密码界面
	 * @return
	 */
	@RequestMapping("rePwdManager")
	public String redicterRePwd(){
		return "/sysmanager/rePwdManager";
	}
	
	/**
	 * 重置密码相关请求
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "rePwdManager.json", method = RequestMethod.POST)
	@ResponseBody
	public Map rePwdManager(HttpServletRequest request){
		Map reParam = new HashMap<>();
		try {
			/**
			 * 重置密码
			 */
			Map param = ParamUtil.buildParameter(request);
			param.put("id", ParamUtil.buildUserId());
			param.put("pwd", ParamUtil.md5Transformation(param.get("pwd").toString()));
			sysManagerService.updatePwd(param);
			reParam.put("state", "T");
		} catch (Exception e) {
			reParam.put("state", "F");
		}
		return reParam;
	}
	
	/**
	 * 获取用户名
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getUserName.json", method = RequestMethod.POST)
	@ResponseBody
	public Map getUserName() {
		Map reParam = new HashMap<>();
		try {
			/**
			 * 获取相关信息
			 */
			reParam.put("userName", ParamUtil.buildUserName());
			reParam.put("state", "T");
		} catch (Exception e) {
			reParam.put("state", "F");
		}
		return reParam;
	}

}
