package cn.lf.oa.web.personoffice;

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

import com.mysql.fabric.xmlrpc.base.Param;

import cn.lf.oa.common.util.ParamUtil;
import cn.lf.oa.personoffice.service.ICalendarService;

/**
 * 日历相关控制层
 * @author LFSenior
 *
 */
@Controller
@RequestMapping("personoffice")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class CalendarController {
	@Autowired
	ICalendarService calendarService;
	
	@RequestMapping("calendar")
	public String redicterCalendar(){
		return "/personoffice/calendar";
	}
	
	
	/**
	 * 增加日历标签
	 * @param request
	 * @return
	 */
	@RequestMapping(value="addCalendar.json",method=RequestMethod.POST)
	@ResponseBody
	public Map addCalendar(HttpServletRequest request){
		Map reParam=new HashMap<>();
		try {
			Map param=ParamUtil.buildParameter(request);
			param.put("userid", ParamUtil.buildUserId());
			param.put("createname", ParamUtil.buildUserName());
			param.put("createdate", new Date());
			param.put("id", ParamUtil.buildUUID());
			calendarService.addCalendar(param);
			reParam.put("state", "T");
		} catch (Exception e) {
			reParam.put("state", "F");
		}
		return reParam;
	}
	
	
	/**
	 * 更新日历信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value="updateCalendar.json",method=RequestMethod.POST)
	@ResponseBody
	public Map updateCalendar(HttpServletRequest request){
		Map reParam=new HashMap<>();
		try {
			Map param=ParamUtil.buildParameter(request);
			calendarService.updateCalendar(param);
			reParam.put("state", "T");
		} catch (Exception e) {
			reParam.put("state", "F");
		}
		return reParam;
	}
	
	/**
	 * 删除日历信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value="deleteCalendar.json",method=RequestMethod.POST)
	@ResponseBody
	public Map deleteCalendar(HttpServletRequest request){
		Map reParam=new HashMap<>();
		try {
			Map param=ParamUtil.buildParameter(request);
			calendarService.deleteCalendar(param);;
			reParam.put("state", "T");
		} catch (Exception e) {
			reParam.put("state", "F");
		}
		return reParam;
	}
	
	/**
	 * 根据条件查询日历信息
	 * @return
	 */
	@RequestMapping(value="findCalendar.json",method=RequestMethod.POST)
	@ResponseBody
	public Map findCalendar(HttpServletRequest request){
		Map reParam=new HashMap<>();
		try {
			Map param=ParamUtil.buildParameter(request);
			param.put("userid",ParamUtil.buildUserId());
			List<Map> data = calendarService.findCalendar(param);
			reParam.put("data", data);
			reParam.put("state", "T");
		} catch (Exception e) {
			reParam.put("state", "F");
		}
		return reParam;
	}
}
