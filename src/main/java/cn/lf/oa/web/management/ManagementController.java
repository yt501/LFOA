package cn.lf.oa.web.management;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.lf.oa.common.util.ParamUtil;
import cn.lf.oa.management.service.IManagementService;

@Controller
@RequestMapping("management")
public class ManagementController {
	@Autowired
	IManagementService managementService;

	/**
	 * 查询所有会议室
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("roomApplication")
	public String redicterRoomApplication(Model model) {
		// 获取会议室名称
		Map<String, Object> result = new HashMap<>();
		List<Map> map = managementService.queryAllBoardRoom();
		result.put("boardrooms", map);
		model.addAllAttributes(result);
		return "/management/roomApplication";
	}

	/**
	 * 跳转到会议室管理界面
	 * 
	 * @return
	 */
	@RequestMapping("roomManager")
	public String redicterRoomManager() {
		return "/management/roomManager";
	}
	
	/**
	 * 查询所有会议室
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "findAllRoom.json", method = RequestMethod.POST)
	@ResponseBody
	public Map findAllRoom(HttpServletRequest request, HttpServletResponse response) {
		// 获取前台参数
		Map result = new HashMap<>();
		try {
			Map<String, Object> param = ParamUtil.buildParameter(request);
			List<Map> data = managementService.queryAllBoardRoom();
			result.put("data", data);
			result.put("state", "T");
		} catch (Exception e) {
			result.put("state", "F");
		}
		return result;
	}
	
	
	/**
	 * 更新会议室申请相关/恢复会议室/
	 * @param request 请求参数id
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "updateRoomApply.json", method = RequestMethod.POST)
	@ResponseBody
	public Map updateRoomApply(HttpServletRequest request, HttpServletResponse response) {
		// 获取前台参数
		Map result = new HashMap<>();
		try {
			Map<String, Object> param = ParamUtil.buildParameter(request);
			managementService.updateRoomApply(param);
			List<Map> data = managementService.queryAllBoardRoom();
			result.put("data", data);
			result.put("state", "T");
		} catch (Exception e) {
			result.put("state", "F");
		}
		return result;
	}
	
	/**
	 * 创建会议室
	 * @param request参数列表id,address,name
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "createRoom.json", method = RequestMethod.POST)
	@ResponseBody
	public Map createRoom(HttpServletRequest request, HttpServletResponse response) {
		// 获取前台参数
		Map result = new HashMap<>();
		try {
			Map<String, Object> param = ParamUtil.buildParameter(request);
			param.put("id", ParamUtil.buildUUID());
			param.put("cratename", ParamUtil.buildUserName());
			param.put("createdate", new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
			managementService.createRoom(param);
			List<Map> data = managementService.queryAllBoardRoom();
			result.put("data", data);
			result.put("state", "T");
		} catch (Exception e) {
			result.put("state", "F");
		}
		return result;
	}
	

	/**
	 * 更新会议室内容
	 * @param request 传入参数id/address/name
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "updataRoom.json", method = RequestMethod.POST)
	@ResponseBody
	public Map updataRoom(HttpServletRequest request, HttpServletResponse response) {
		// 获取前台参数
		Map result = new HashMap<>();
		try {
			Map<String, Object> param = ParamUtil.buildParameter(request);
			managementService.updataRoom(param);
			List<Map> data = managementService.queryAllBoardRoom();
			result.put("data", data);
			result.put("state", "T");
		} catch (Exception e) {
			result.put("state", "F");
		}
		return result;
	}
	
	/**
	 * 根据id查询相关会议室信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "findRoomById.json", method = RequestMethod.POST)
	@ResponseBody
	public Map findRoomById(HttpServletRequest request, HttpServletResponse response) {
		// 获取前台参数
		Map result = new HashMap<>();
		try {
			Map<String, Object> param = ParamUtil.buildParameter(request);
			List<Map> data=managementService.findRoomById(param);
			result.put("data",data);
			result.put("state", "T");
		} catch (Exception e) {
			result.put("state", "F");
		}
		return result;
	}
	
	/**
	 * 删除会议室
	 * @param request 参数id
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "deleteRoom.json", method = RequestMethod.POST)
	@ResponseBody
	public Map deleteRoom(HttpServletRequest request, HttpServletResponse response) {
		// 获取前台参数
		Map result = new HashMap<>();
		try {
			Map<String, Object> param = ParamUtil.buildParameter(request);
			managementService.deleteRoom(param);
			result.put("state", "T");
		} catch (Exception e) {
			result.put("state", "F");
		}
		return result;
	}
	
	
	/**
	 * 更新会议室
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "updateBoardroomApply.json", method = RequestMethod.POST)
	@ResponseBody
	public Map updateBoardroomApply(HttpServletRequest request, HttpServletResponse response) {
		// 获取前台参数
		Map result = new HashMap<>();
		try {
			Map<String, Object> param = ParamUtil.buildParameter(request);
			managementService.updateBoardroomApply(param);
			List<Map> data = managementService.queryAllBoardRoom();
			result.put("data", data);
			result.put("state", "T");
		} catch (Exception e) {
			result.put("state", "F");
		}
		return result;
	}

}
