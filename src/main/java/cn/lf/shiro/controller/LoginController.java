package cn.lf.shiro.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.lf.shiro.commons.util.OSInfoUtil;
import cn.lf.shiro.service.IOSInfoService;


/**
 * 登录相关控制器
 * @author LFSenior
 *
 */
@Controller
public class LoginController {
	
	@Autowired
	IOSInfoService osInfoService;
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	 
	/**
	 * 跳转到主页
	 * @return
	 */
	@RequestMapping(value="/")
	public String index(){
		return "redirect:/index";
	}
	
	/**
	 * 首页
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/index")
	public String index(Model model){
		return "index";
	}
	
	
	/**
	 * GET 登录
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(){
		logger.info("GET请求登录");
		if(SecurityUtils.getSubject().isAuthenticated()){
			return "redirect:/index";
		}
		return "login";
	}
	
	
	/**
	 * Post登录  shiro写法
	 * @return
	 */
	@RequestMapping(value="login",method=RequestMethod.POST)
	@ResponseBody
	public Map loginPost(String username,String password,HttpServletRequest request){
		logger.info("POST请求登录");
		Map reParam=new HashMap<>();
		if(StringUtils.isBlank(username)){
			return renderError("用户名不能为空");
		}
		if(StringUtils.isBlank(password)){
			return renderError("密码不能为空");
		}
		
		Subject user=SecurityUtils.getSubject();
		UsernamePasswordToken token=new UsernamePasswordToken(username, password.toCharArray());
		token.setRememberMe(true);
		try {
            user.login(token);
        } catch (UnknownAccountException e) {
            logger.error("账号不存在：{}", e);
            return renderError("账号不存在");
        } catch (DisabledAccountException e) {
            logger.error("账号未启用：{}", e);
            return renderError("账号未启用");
        } catch (IncorrectCredentialsException e) {
            logger.error("密码错误：{}", e);
            return renderError("密码错误");
        } catch (RuntimeException e) {
            logger.error("未知错误,请联系管理员：{}", e);
            return renderError("未知错误,请联系管理员");
        }
		reParam.put("state", "T");
		Map param = OSInfoUtil.getOSInfo(request);
		osInfoService.insertOSInfo(param);
		return reParam;
	}
	
	
	public Map renderError(String message){
		Map reParam=new HashMap<>();
		reParam.put("state", "F");
		reParam.put("message", message);
		return reParam;
	}
}
