package com.gra.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gra.entity.Finance;
import com.gra.entity.User;
import com.gra.service.IFinanceService;
import com.gra.service.IUserService;

/**
 * 用户管理控制器
 * @author susie
 *
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
	@Autowired
	public IUserService userServiceImpl;
	@Autowired
	public IFinanceService financeServiceImpl;
	
	/**
	 * 用户注册或管理员添加用户信息
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addUser",method = RequestMethod.POST,produces={"text/html;charset=UTF-8;","application/json;"})
	public String addUser(User user){		
		//调用service层的添加方法
		boolean isAdded = userServiceImpl.addUser(user);
		if(isAdded){
			return "添加成功";
		}else{
			return "添加失败";
		}
	}
	/**
	 * 用户设置信息后对用户信息进行更新
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/update")
	public String updateUser(User user){
		boolean isUpdate;
		User users = userServiceImpl.getUserByName(user.getName());
		users.setNickName(user.getNickName());		
		users.setPassword(user.getPassword());
		//调用Service层的更新方法，返回更新成功与否标识						
		isUpdate= userServiceImpl.updateUserPass(users);				 	
		if(isUpdate){
			return "UpdateSuccess";
		}else{
			return "UpdateFailed";
		}
	}
	
	/**
	 * 管理员删除用户信息
	 * @param u_id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public String deleteUser(Integer u_id){
		boolean isDelete = userServiceImpl.deleteUser(u_id);
		if(isDelete){
			return "DeleteSuccess";
		}else{
			return "DeleteFailed";
		}
	}
	 
	/**
	 * 获取所有用户信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "lists")
	public Map<String,Object> getAllUsers(){
		List<User> users = userServiceImpl.getAllUsers();
		Map<String,Object> info = new HashMap<String,Object>();
		info.put("data", users);
		return info;
	}
	
	/**
	 * 根据用户id获取用户信息
	 * @param u_id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value ="userid")
	public User getUserById(Integer u_id){
		User users = userServiceImpl.getUserById(u_id);
		return users;
	}
	/**
	 * 积分兑换
	 * @param uid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value ="chargebalance")
	public String changeBalance(Integer uid){
		User user = userServiceImpl.getUserById(uid);
		String b = user.getBalance();
		Integer balance = Integer.parseInt(b)-300;
		user.setBalance(String.valueOf(balance));
		Integer time = user.getTime()+5;
		user.setTime(time);
		user.setRole("vip用户");
		boolean isupdate = userServiceImpl.updateUser(user);
		if(isupdate){
			return "chargeSuccess";
		}
		else{
			return "chargeFail";
		}
	}
	/**
	 * 充值vip
	 * @param value
	 * @param uid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value ="vip")
	public String vip(String value,Integer uid){
		User user = userServiceImpl.getUserById(uid);
		Integer time = user.getTime();
		Integer p_in = 0;
		String p_time = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
		Finance finance =  financeServiceImpl.getFinanceByTime(p_time);
		if(finance!=null){
			p_in = Integer.parseInt(finance.getP_in());
		}
		else{
			finance = new Finance("0","0","0");
			financeServiceImpl.addFinance(finance);
		}
		if(value.equals("1")){
			time+=30;
			p_in+=8;
		}
		else if(value.equals("2")){
			time+=90;
			p_in+=20;
		}
		else if(value.equals("3")){
			time+=365;
			p_in+=72;
		}
		user.setTime(time);
		user.setRole("vip用户");
		finance.setP_in(String.valueOf(p_in));
		boolean isupdate = userServiceImpl.updateUser(user);
		boolean financeupdate = financeServiceImpl.updateFinance(finance);
		if(isupdate&&financeupdate){
			return "rechargeSuccess";
		}
		else{
			return "rechargeFail";
		}
	}
	/**
	 * 通过用户名查找用户
	 * @param userName
	 * @return
	 */
	@RequestMapping(value="/doFindUserByName",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> findUserByName(String userName){
		User user = userServiceImpl.getUserByName(userName);
		//通过HashMap来构建Json数据，其实和Server里面通过JSONObject来构建效果是一样的
		Map<String,Object> resoult = new HashMap<String,Object>();
		if(user!=null){
			resoult.put("userId", user.getUserid());
			resoult.put("userName", user.getName());
			resoult.put("userNickName", user.getNickName());
			resoult.put("userIsOnline", user.getUserIsOnline());
			resoult.put("userRole", user.getRole());
		}
		else
			resoult=null;
		return resoult;
	};
	/**
	 * 通过用户id查找用户
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/doFindUserById",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> findUserById(Integer userId){
		User user = userServiceImpl.getUserById(userId);
		Map<String,Object> resoult = new HashMap<String,Object>();
		if(user!=null){
			resoult.put("userId", user.getUserid());
			resoult.put("userName", user.getName());
			resoult.put("userNickName", user.getNickName());
			resoult.put("userIsOnline", user.getUserIsOnline());
			resoult.put("userRole", user.getRole());
		}
		else
			resoult=null;
		return resoult;
	};
	
	@RequestMapping(value="/updateTime",method=RequestMethod.POST)
	@ResponseBody
	public String declineTime(){
		List<User> users = userServiceImpl.getAllUsers();
		int i=0;
		Integer time;
		boolean updateTime = false;
		for(i=0;i<users.size();i++){
			time = users.get(i).getTime();
			if(time>0){
				time--;
			}
			users.get(i).setTime(time);
			updateTime = userServiceImpl.updateUser(users.get(i));
		}
		if(updateTime){
			return "updateSuccess";
		}
		else{
			return "updateFail";
		}
	}
}
