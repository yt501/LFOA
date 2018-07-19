package cn.lf.oa.web.instation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.lf.oa.common.util.ParamUtil;
import cn.lf.oa.instation.service.IInstationService;

/**
 * 邮件系统
 * @author LFSenior
 *
 */
@Controller
@RequestMapping("instation")
public class InstationController {
	@Autowired
	IInstationService instationService;
	/**
	 * 跳转到收件相关
	 * @return
	 */
	@RequestMapping("inbox")
	public String redicterInbox(){
		return "/instation/inbox";
	}
	
	/**
	 * 查询EmailInfo通过传入的select不同查找不同信息
	 * @param request select==0表示查询今天，select==1表示查询OLder同事传入createdate
	 * @return
	 */
	@RequestMapping(value="queryEmailInfo.json",method=RequestMethod.POST)
	@ResponseBody
	public Map queryEmailInfo(HttpServletRequest request){
		Map reParam=new HashMap<>();
		try {
			Map param=ParamUtil.buildParameter(request);
			param.put("userid", ParamUtil.buildUserId());
			param.put("createdate", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			List<Map> data = instationService.queryEmailInfo(param);
			reParam.put("data", data);
			reParam.put("state", "T");
		} catch (Exception e) {
			reParam.put("state", "F");
		}
		return reParam;
	}
	
	/**
	 * 删除接受的邮件
	 * @return
	 */
	@RequestMapping(value="deleteAchiveEmail.json",method=RequestMethod.POST)
	@ResponseBody
	public Map deleteAchiveEmail(HttpServletRequest request){
		Map reParam=new HashMap<>();
		try {
			Map param=ParamUtil.buildParameter(request);
			//设置邮箱的状态为2
			instationService.deleteAchiveEmail(param);
			reParam.put("state", "T");
		} catch (Exception e) {
			reParam.put("state", "F");
		}
		return reParam;
	}
	
	
	
	/**
	 * 跳转到邮件详细信息
	 * @return
	 */
	@RequestMapping("emailinfo")
	public String redicterEmailinfo(String id,Model model){
		Map param=new HashMap<>();
		List<Map> data;
		try {
			param.put("id", id);
			data = instationService.queryEmailInfoById(param);
			//更新邮件状态
			param.put("state", "1");
			instationService.updateEmailState(param);
			if(data==null||data.size()==0){
				return "error";	
			}
			model.addAttribute("data", data.get(0));
		} catch (Exception e) {
			return "error";
		}
		return "/instation/emailinfo";
	}
	
	
	/**
	 * 跳转到垃圾箱
	 * @return
	 */
	@RequestMapping("rubbsh")
	public String redicterRubbsh(){
		return "/instation/rubbsh";
	}
	
	
	/**
	 * 从回收站删除邮件
	 * @return
	 */
	@RequestMapping(value="deleteEmalForRubbsh.json",method=RequestMethod.POST)
	@ResponseBody
	public Map deleteEmalForRubbsh(HttpServletRequest request){
		Map reParam =new HashMap<>();
		try {
			Map param=ParamUtil.buildParameter(request);
			param.put("userid", ParamUtil.buildUserId());
			instationService.deleteEmalForRubbsh(param);
			reParam.put("state", "T");
		} catch (Exception e) {
			reParam.put("state", "F");
		}
		return reParam;
	}
	
	
	/**
	 * 查询回收站邮件
	 * @return
	 */
	@RequestMapping(value="queryRubbshEmail.json",method=RequestMethod.POST)
	@ResponseBody
	public Map queryRubbshEmail(){
		Map reParam=new HashMap<>();
		Map param=new HashMap<>();
		try {
			param.put("userid", ParamUtil.buildUserId());
			List<Map> data=instationService.queryRubbshEmail(param);
			reParam.put("data", data);
			reParam.put("state", "T");
		} catch (Exception e) {
			reParam.put("state", "F");
		}
		return reParam;
	}
	
	
	/**
	 * 恢复回收站的邮件
	 * @param request
	 * @return
	 */
	@RequestMapping(value="updateRubbshEmailRecover.json",method=RequestMethod.POST)
	@ResponseBody
	public Map updateRubbshEmailRecover(HttpServletRequest request){
		Map reParam=new HashMap<>();
		try {
			Map param=ParamUtil.buildParameter(request);
			param.put("state", "1");
			instationService.updateEmailState(param);
			reParam.put("state", "T");
		} catch (Exception e) {
			reParam.put("state", "F");
		}
		return reParam;
	}
	
	
	/**
	 * 跳转到发邮件
	 * @return
	 */
	@RequestMapping("compose")
	public String redicterCompose(){
		return "/instation/compose";
	}
	
	
	/**
	 * 增加邮件
	 * @param request
	 * @return
	 */
	@RequestMapping(value="addNewEmailInfo.json",method=RequestMethod.POST)
	@ResponseBody
	public Map addNewEmailInfo(HttpServletRequest request){
		Map reParam=new HashMap<>();
		try {
			Map param=ParamUtil.buildParameter(request);
			param.put("id", ParamUtil.buildUUID());
			param.put("userid", ParamUtil.buildUserId());
			param.put("createname", ParamUtil.buildUserName());
			param.put("createdate", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			instationService.addNewEmailInfo(param);
			reParam.put("state", "T");
		} catch (Exception e) {
			reParam.put("state", "F");
		}
		return reParam;
	}
	
	/**
	 * 查询未读邮件数目
	 * @return
	 */
	@RequestMapping(value="queryEmailInfoTotal.json",method=RequestMethod.POST)
	@ResponseBody
	public Map queryEmailInfoTotal(){
		Map reParam=new HashMap<>();
		Map param=new HashMap<>();
		try {
			param.put("userid", ParamUtil.buildUserId());
			Long total=instationService.queryEmailInfoTotal(param);
			reParam.put("total", total);
			reParam.put("state", "T");
		} catch (Exception e) {
			reParam.put("state", "F");
		}
		return reParam;
	}
}
