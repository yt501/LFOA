package cn.lf.oa.web.index;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.lf.oa.common.util.ParamUtil;
import cn.lf.shiro.service.IOSInfoService;

/**
 * 主页内容相关控制
 * 
 * @author LFSenior
 *
 */
@Controller
@RequestMapping("/index")
public class IndexContentController {
	@Autowired
	IOSInfoService osInfoService;

	/**
	 * 跳转到内容页
	 * 
	 * @return
	 */
	@RequestMapping("/indexContent")
	public String redicterContent() {
		return "/index/content";
	}

	/**
	 * 获取任务相关细节
	 * 
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "loadTaskNumber.json", method = RequestMethod.POST)
	@ResponseBody
	public Map loadTaskNumber(HttpServletResponse response) {
		/** 设置允许跨域访问 */
		response.setHeader("Access-Control-Allow-Methods", "*");
		Map reParam = new HashMap<>();
		try {
			/**
			 * 获取相关信息
			 */
			Map param = new HashMap<>();
			param.put("userid", ParamUtil.buildUserId());
			List<Map> data = osInfoService.getTaskNumber(param);
			reParam.put("data", data);
			reParam.put("state", "T");
		} catch (Exception e) {
			reParam.put("state", "F");
		}
		return reParam;
	}

	/**
	 * 加载用户操作分布
	 * 
	 * @return
	 */
	@RequestMapping(value = "loadUserOption.json", method = RequestMethod.POST)
	@ResponseBody
	public Map loadUserOption(HttpServletResponse response) {
		/** 设置允许跨域访问 */
		response.setHeader("Access-Control-Allow-Methods", "*");
		Map reParam = new HashMap<>();
		try {
			/**
			 * 获取相关信息
			 */
			Map param = new HashMap<>();
			param.put("userid", ParamUtil.buildUserId());
			List<String> userOption = osInfoService.getUserOption(param);
			List<String> allOption = osInfoService.getAllOption();
			reParam.put("userOption", userOption);
			reParam.put("allOption", allOption);
			reParam.put("state", "T");
		} catch (Exception e) {
			reParam.put("state", "F");
		}
		return reParam;
	}

	/**
	 * 加载用户满意度报告
	 * 
	 * @return
	 */
	@RequestMapping(value = "loadUserState.json", method = RequestMethod.POST)
	@ResponseBody
	public Map loadUserState(HttpServletResponse response) {
		/** 设置允许跨域访问 */
		response.setHeader("Access-Control-Allow-Methods", "*");
		Map reParam = new HashMap<>();
		try {
			/**
			 * 获取相关信息
			 */
			List<Map> data = osInfoService.getUserStateOption(null);
			reParam.put("data", data);
			reParam.put("state", "T");
		} catch (Exception e) {
			reParam.put("state", "F");
		}
		return reParam;
	}

	/**
	 * 用户类别统计相关
	 * 
	 * @return
	 */
	@RequestMapping(value = "loadUserNumber.json", method = RequestMethod.POST)
	@ResponseBody
	public Map loadUserNumber(HttpServletResponse response) {
		/** 设置允许跨域访问 */
		response.setHeader("Access-Control-Allow-Methods", "*");
		Map reParam = new HashMap<>();
		try {
			/**
			 * 获取相关信息
			 */
			List<Map> data = osInfoService.getUserNumber(null);
			List<String> xData = new ArrayList<>();
			List<String> yData = new ArrayList<>();
			for (Map map : data) {
				xData.add(map.get("name").toString());
				yData.add(map.get("value").toString());
			}
			reParam.put("xData", xData);
			reParam.put("yData", yData);
			reParam.put("state", "T");
		} catch (Exception e) {
			reParam.put("state", "F");
		}
		return reParam;
	}

	/**
	 * 加载系统访问来源
	 * 
	 * @return
	 */
	@RequestMapping(value = "loadOaSource.json", method = RequestMethod.POST)
	@ResponseBody
	public Map loadOaSource(HttpServletResponse response) {
		/** 设置允许跨域访问 */
		response.setHeader("Access-Control-Allow-Methods", "*");
		Map reParam = new HashMap<>();
		try {
			/**
			 * 获取相关信息
			 */
			List<Map> data = osInfoService.getUserOASource(null);
			List<Map> result = new ArrayList<>();
			result.add(buildOASource(data.get(0).get("phone").toString(), "移动端"));
			result.add(buildOASource(data.get(0).get("pc").toString(), "PC端"));
			result.add(buildOASource(data.get(0).get("all").toString(), "直接访问"));
			reParam.put("data", result);
			reParam.put("state", "T");
		} catch (Exception e) {
			reParam.put("state", "F");
		}
		return reParam;
	}

	/**
	 * 构建结果集OASource
	 * 
	 * @param value
	 * @param name
	 * @return
	 */
	public Map buildOASource(String value, String name) {
		Map temp = new HashMap<>();
		temp.put("value", value);
		temp.put("name", name);
		return temp;
	}

	/**
	 * 获取最新的五条通知 id,title,descripter,content,userid,createname,createdate
	 * 
	 * @return
	 */
	@RequestMapping(value = "loadNewNotice.json", method = RequestMethod.POST)
	@ResponseBody
	public Map loadNewNotice(HttpServletResponse response) {
		/** 设置允许跨域访问 */
		response.setHeader("Access-Control-Allow-Methods", "*");
		Map reParam = new HashMap<>();
		try {
			/**
			 * 获取相关信息
			 */
			List<Map> data = osInfoService.getNewNotice();
			reParam.put("data", data);
			reParam.put("state", "T");
		} catch (Exception e) {
			reParam.put("state", "F");
		}
		return reParam;
	}

	/**
	 * 加载最新的五条信息 id,title,descripter,content,userid,createname,createdate
	 * 
	 * @return
	 */
	@RequestMapping(value = "loadNewInfo.json", method = RequestMethod.POST)
	@ResponseBody
	public Map loadNewInfo(HttpServletResponse response) {
		/** 设置允许跨域访问 */
		response.setHeader("Access-Control-Allow-Methods", "*");
		Map reParam = new HashMap<>();
		try {
			/**
			 * 获取相关信息
			 */
			List<Map> data = osInfoService.getNewInfo();
			reParam.put("data", data);
			reParam.put("state", "T");
		} catch (Exception e) {
			reParam.put("state", "F");
		}
		return reParam;
	}

	/**
	 * 加载用户登录情况
	 * 
	 * @return
	 */
	@RequestMapping(value = "loadClickNumber.json", method = RequestMethod.POST)
	@ResponseBody
	public Map loadClickNumber(HttpServletResponse response) {
		/** 设置允许跨域访问 */
		response.setHeader("Access-Control-Allow-Methods", "*");
		Map reParam = new HashMap<>();
		try {
			/**
			 * 获取相关信息
			 */
			List<Map> data = osInfoService.getClickNumber();
			List<String> xAxisData = new ArrayList<>();
			List<String> xContentData = new ArrayList<>();
			for (Map map : data) {
				xAxisData.add(map.get("name").toString());
				xContentData.add(map.get("value").toString());
			}
			reParam.put("xAxisData", xAxisData);
			reParam.put("xContentData", xContentData);
			reParam.put("state", "T");
		} catch (Exception e) {
			reParam.put("state", "F");
		}
		return reParam;
	}

}
