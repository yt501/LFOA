package cn.lf.oa.web.publicinfo;

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
import cn.lf.oa.publicinfo.service.IPublicInfoService;

/**
 * 公共消息管理
 * @author LFSenior
 *
 */
/**
 * @author LFSenior
 *
 */
@Controller
@RequestMapping("publicinfo")
public class PublicInfoController {
	@Autowired
	IPublicInfoService publicInfoService;
	
	/**
	 * 跳转到消息管理页面
	 * @return
	 */
	@RequestMapping("infoManger")
	public String redicterInfoManger(){
		return "/publicinfo/infoManger";
	}
	
	
	/**
	 * 查询所有消息
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="queryAllInfo.json",method=RequestMethod.POST)
	@ResponseBody
	public Map queryAllInfo(HttpServletRequest request){
		Map reParam=new HashMap<>();
		try {
			Map param=ParamUtil.buildParameter(request);
			List<Map> data=publicInfoService.queryAllInfo(param);
			reParam.put("data", data);
			reParam.put("state", "T");
		} catch (Exception e) {
			reParam.put("state", "F");
		}
		return reParam;
	}
	
	/**
	 * 根据id查询相关信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value="queryInfoById.json",method=RequestMethod.POST)
	@ResponseBody
	public Map queryInfoById(HttpServletRequest request){
		Map reParam=new HashMap<>();
		try {
			Map param=ParamUtil.buildParameter(request);
			List<Map> data=publicInfoService.queryInfoById(param);
			if(data==null||data.size()==0){
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
	 * 根据作者查询相关信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value="queryInfoByAuthor.json",method=RequestMethod.POST)
	@ResponseBody
	public Map queryInfoByAuthor(HttpServletRequest request){
		Map reParam=new HashMap<>();
		try {
			Map param=ParamUtil.buildParameter(request);
			List<Map> data=publicInfoService.queryInfoByAuthor(param);
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
	 * 根据id删除对应信息
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	@RequestMapping(value="deleteInfoById.json",method=RequestMethod.POST)
	@ResponseBody
	public Map deleteInfoById(HttpServletRequest request){
		Map reParam=new HashMap<>();
		try {
			Map param=ParamUtil.buildParameter(request);
			publicInfoService.deleteInfoById(param);
			reParam.put("state", "T");
		} catch (Exception e) {
			reParam.put("state","F");
		}
		return reParam;
	}
	
	
	/**
	 * 增加信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value="addInfo.json",method=RequestMethod.POST)
	@ResponseBody
	public Map addInfo(HttpServletRequest request){
		Map reParam=new HashMap<>();
		try {
			Map param=ParamUtil.buildParameter(request);
			param.put("id", ParamUtil.buildUUID());
			param.put("userid", ParamUtil.buildUserId());
			param.put("createname", ParamUtil.buildUserName());
			publicInfoService.addInfo(param);
			//查询所有数据
			List<Map> data = publicInfoService.queryAllInfo(null);
			reParam.put("data", data);
			reParam.put("state", "T");
		} catch (Exception e) {
			reParam.put("state", "F");
		}
		return reParam;
	}
	
	/**
	 * 修改信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value="updateInfo.json",method=RequestMethod.POST)
	@ResponseBody
	public Map updateInfo(HttpServletRequest request){
		Map reParam=new HashMap<>();
		try {
			Map param=ParamUtil.buildParameter(request);
			publicInfoService.updateInfo(param);
			List<Map> data = publicInfoService.queryAllInfo(param);
			reParam.put("data", data);
			reParam.put("state", "T");
		} catch (Exception e) {
			reParam.put("state", "F");
		}
		return reParam;
	}
	
	/**公告相关*/
	/**
	 * 跳转到公告管理界面
	 * @return
	 */
	@RequestMapping("noticeManger")
	public String redicterNoticeManger(){
		return "/publicinfo/noticeManger";
	}
	
	/**
	 * 查询所有公告
	 * @return
	 */
	@RequestMapping(value="queryAllNotice.json",method=RequestMethod.POST)
	@ResponseBody
	public Map queryAllNotice(){
		Map reParam=new HashMap<>();
		try {
			List<Map> data = publicInfoService.queryAllNotice();
			reParam.put("data", data);
			reParam.put("state", "T");
		} catch (Exception e) {
			reParam.put("state", "F");
		}
		return reParam;
	}
	
	
	/**
	 * 根据id查询相关公告
	 * @param request
	 * @return
	 */
	@RequestMapping(value="queryNoticeById.json",method=RequestMethod.POST)
	@ResponseBody
	public Map queryNoticeById(HttpServletRequest request){
		Map reParam=new HashMap<>();
		try {
			Map param=ParamUtil.buildParameter(request);
			List<Map> data=publicInfoService.queryNoticeById(param);
			if(data==null||data.size()==0){
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
	 * 根据作者查询相关公告
	 * @param request
	 * @return
	 */
	@RequestMapping(value="queryNoticeByAuthor.json",method=RequestMethod.POST)
	@ResponseBody
	public Map queryNoticeByAuthor(HttpServletRequest request){
		Map reParam=new HashMap<>();
		try {
			Map param=ParamUtil.buildParameter(request);
			List<Map> data=publicInfoService.queryNoticeByAuthor(param);
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
	 * 根据id删除公告
	 * @param request
	 * @return
	 */
	@RequestMapping(value="deleteNoticeById.json",method=RequestMethod.POST)
	@ResponseBody
	public Map deleteNoticeById(HttpServletRequest request){
		Map reParam=new HashMap<>();
		try {
			Map param=ParamUtil.buildParameter(request);
			publicInfoService.deleteNoticeById(param);
			reParam.put("state", "T");
		} catch (Exception e) {
			reParam.put("state", "F");
		}
		return reParam;
	}
	
	/**
	 * 增加公告
	 * @param request
	 * @return
	 */
	@RequestMapping(value="addNotice.json",method=RequestMethod.POST)
	@ResponseBody
	public Map addNotice(HttpServletRequest request){
		Map reParam=new HashMap<>();
		try {
			Map param=ParamUtil.buildParameter(request);
			param.put("id", ParamUtil.buildUUID());
			param.put("userid", ParamUtil.buildUserId());
			param.put("createname", ParamUtil.buildUserName());
			publicInfoService.addNotice(param);
			//查询所有数据
			List<Map> data = publicInfoService.queryAllNotice();
			reParam.put("data", data);
			reParam.put("state", "T");
		} catch (Exception e) {
			reParam.put("state", "F");
		}
		return reParam;
	}
	

	/**
	 * 更新公告
	 * @param request
	 * @return
	 */
	@RequestMapping(value="updateNotice.json",method=RequestMethod.POST)
	@ResponseBody
	public Map updateNotice(HttpServletRequest request){
		Map reParam=new HashMap<>();
		try {
			Map param=ParamUtil.buildParameter(request);
			publicInfoService.updateNotice(param);
			List<Map> data = publicInfoService.queryAllNotice();
			reParam.put("data", data);
			reParam.put("state", "T");
		} catch (Exception e) {
			reParam.put("state", "F");
		}
		return reParam;
	}
	
}
