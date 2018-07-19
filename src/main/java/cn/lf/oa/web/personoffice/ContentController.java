package cn.lf.oa.web.personoffice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.net.httpserver.HttpServer;

import cn.lf.oa.common.util.ParamUtil;
import cn.lf.oa.personoffice.service.IContentService;

/**
 * 通讯录相关控制
 * @author LFSenior
 *
 */
@Controller
@RequestMapping("personoffice")
public class ContentController {
	
	@Autowired
	IContentService contentService;
	
	/**
	 * 跳转到通讯录页面
	 * @return
	 */
	@RequestMapping("content")
	public String redicterContent(){
		return "/personoffice/content";
	}
	
	
	/**
	 * 查询未添加的通讯录
	 * @return
	 */
	@RequestMapping(value="queryNotAddContent.json",method=RequestMethod.POST)
	@ResponseBody
	public Map queryNotAddContent(){
		Map reParam=new HashMap<>();
		Map param=new HashMap<>();
		try {
			param.put("userid", ParamUtil.buildUserId());
			List<Map> data = contentService.queryNotAddContent(param);
			reParam.put("data", data);
			reParam.put("state", "T");
		} catch (Exception e) {
			reParam.put("state", "F");
		}
		return reParam;
	}
	
	/**
	 * 查询添加的通讯录
	 * @return
	 */
	@RequestMapping(value="queryAddContent.json",method=RequestMethod.POST)
	@ResponseBody
	public Map queryAddContent(){
		Map reParam=new HashMap<>();
		Map param=new HashMap<>();
		try {
			param.put("userid", ParamUtil.buildUserId());
			List<Map> data = contentService.queryAddContent(param);
			reParam.put("data", data);
			reParam.put("state", "T");
		} catch (Exception e) {
			reParam.put("state", "F");
		}
		return reParam;
	}
	
	/**
	 * 添加到我的通讯录
	 * @param request
	 * @return
	 */
	@RequestMapping(value="addContentToMe.json",method=RequestMethod.POST)
	@ResponseBody
	public Map addContentToMe(HttpServletRequest request){
		Map reParam=new HashMap<>();
		try {
			Map param =ParamUtil.buildParameter(request);
			param.put("userid", ParamUtil.buildUserId());
			contentService.addContentToMe(param);
			reParam.put("state", "T");
		} catch (Exception e) {
			reParam.put("state", "F");
		}
		return reParam;
	}
	
	/**
	 * 根据条件查询未添加到联系人的信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value="queryNotAddContentByParam.json",method=RequestMethod.POST)
	@ResponseBody
	public Map queryNotAddContentByParam(HttpServletRequest request){
		Map reParam=new HashMap<>();
		try {
			Map param=ParamUtil.buildParameter(request);
			param.put("userid", ParamUtil.buildUserId());
			List<Map> data = contentService.queryNotAddContentByParam(param);
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
	 * 跳转我的通讯录
	 * @return
	 */
	@RequestMapping("contenttome")
	public String redicterContentToMe(){
		return "/personoffice/contenttome";
	}
	
	/**
	 * 从我的通讯录中删除
	 * @param request
	 * @return
	 */
	@RequestMapping(value="deleteContentToMe.json",method=RequestMethod.POST)
	@ResponseBody
	public Map deleteContentToMe(HttpServletRequest request){
		Map reParam=new HashMap<>();
		try {
			Map param=ParamUtil.buildParameter(request);
			param.put("userid", ParamUtil.buildUserId());
			contentService.deleteContentToMe(param);
			reParam.put("state", "T");
		} catch (Exception e) {
			reParam.put("state", "F");
		}
		return reParam;
	}
	
}
