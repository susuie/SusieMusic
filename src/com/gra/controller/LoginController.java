package com.gra.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gra.entity.User;
import com.gra.service.IUserService;
import com.gra.util.PasswordHelper;

/**
 * 登录控制器
 * 
 * @author curry
 *
 */
@Controller
@RequestMapping("/susie")
@SessionAttributes(value = { "user" })
public class LoginController {

	@Autowired
	public IUserService userServiceImpl;

	//获取日志对象
	private static Logger logger = Logger.getLogger(LoginController.class);
		
	/**
	 * 登录
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping("/login")
	public String dologin(@RequestParam("username") String username,ModelMap map) {
		//前台已经验证账户密码，此处将登录用户信息加入session域中
		System.out.println("==>login info of username: " + username);
		User user = userServiceImpl.getUserByName(username);
		if(user!=null){
			map.addAttribute("user", user);		
			String b = user.getBalance();
			Integer balance = Integer.parseInt(b)+1;
			user.setBalance(String.valueOf(balance));
			userServiceImpl.updateUser(user);		
			return "index";
		}
		else{
			return "redirect:/signin.html";
		}
	}
	
	@RequestMapping("/manage")
	public String donamage() {
		return "user-manage";
	}
	@RequestMapping("/events")
	public String doevents() {
		return "events";
	}
	@RequestMapping("/blog")
	public String doblog() {
		return "blog";
	}
	@RequestMapping("/docs")
	public String dodocs() {
		return "docs";
	}
	@RequestMapping("/fullcalendar")
	public String dofull() {
		return "fullcalendar";
	}
	@RequestMapping("/genres")
	public String dogenres() {
		return "genres";
	}
	@RequestMapping("/jvectormap")
	public String domap() {
		return "jvectormap";
	}
	@RequestMapping("/listen")
	public String dolisten() {
		return "listen";
	}
	@RequestMapping("/modal")
	public String domodal() {
		return "modal";
	}
	@RequestMapping("/profile")
	public String dopro() {
		return "profile";
	}
	@RequestMapping("/track-detail")
	public String dotrack() {
		return "track-detail";
	}
	@RequestMapping("/video-detail")
	public String dovi() {
		return "video-detail";
	}
	@RequestMapping("/video")
	public String dovideo() {
		return "video";
	}
	@RequestMapping("/modal.lockme")
	public String dolockme() {
		return "modal.lockme";
	}
	
	
	@ResponseBody
	@RequestMapping("/loginverify")
	public String doverify(String username, String password,ModelMap map) {		
		System.out.println("==>loginverify info of username: " + username + " password: " + password);
		User user = userServiceImpl.getUserByName(username);		
		System.out.println(user);
		logger.info("==>is user null:" + (user == null));
		String md5pass = PasswordHelper.encryptToMD5(password);			
		//处理ajax请求验证账号密码
		if (user != null && user.getPassword().equals(md5pass)) {
			System.out.println(user.getPassword());
			//map.addAttribute("user", user);			
			return "success";
		} else {
			return "error";
		}
	}

	/**
	 * @ModelAttribute("User")相当于将session中名为"User"的对象注入user对象中
	 * sessionStatus中的setComplete方法可以将session中的内容全部清空
	 * 
	 * @param user
	 * @param sessionStatus
	 * @return
	 */
	@RequestMapping("/logout")
	public String dologout(HttpSession session) {
		logger.info("==>info of the session id:" + session.getId());
		session.removeAttribute("user");//退出系统，将session清除，然后重定向至登录界面
		return "redirect:/index.html";
	}
	
	/**
	 * 获取session，ajax请求用户数据通过这里获取
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getsession")
	public User dogetSession(HttpServletRequest request){
		User user =(User) request.getSession().getAttribute("user");
		return user;
	}
}
