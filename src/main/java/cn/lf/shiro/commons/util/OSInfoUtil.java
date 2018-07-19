package cn.lf.shiro.commons.util;

import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import cn.lf.oa.common.util.ParamUtil;


/**
 * 操作系统相关工具类
 * @author LFSenior
 *
 */
public class OSInfoUtil {
	/**请求host*/
	private static String REQUESTHOST="";
	/**用户登录代理软件标识*/
	private static String USERAGENT="";
	
	public static Map getOSInfo(HttpServletRequest request){
		Map<String,Object> reParam=new HashMap<>();
		REQUESTHOST=request.getRemoteHost();
		USERAGENT=request.getHeader("User-Agent");
		String value="";
		ObjectWriter writer = new ObjectMapper().writer();
		Map<String, Object> maps=new HashMap<>();
		Enumeration<String> headers = request.getHeaderNames();
		while (headers.hasMoreElements()) {
			String string = (String) headers.nextElement();
			maps.put(string, request.getHeader(string));
			
		}
		try {
			value = writer.writeValueAsString(maps);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		/**
		 * 判断是否使用手机登录
		 */
		if(USERAGENT.toUpperCase().contains("ANDROID")){
			reParam.put("logintype", "0");
		}else{
			reParam.put("logintype", "1");
		}
		/**
		 * 添加返回map的相关值
		 */
		reParam.put("requesthost",REQUESTHOST);
		reParam.put("useragent", USERAGENT);
		reParam.put("value", value);
		reParam.put("simpledate", value);
		reParam.put("createdate",new Date());
		reParam.put("simpledate", new Date());
		reParam.put("id", ParamUtil.buildUUID());
		/**
		 * 返回相关数据
		 */
		return reParam;
	}
}
