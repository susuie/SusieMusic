package com.gra.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.gra.entity.User;
import com.gra.entity.UserRelation;
import com.gra.service.UserRelationService;
import com.gra.service.IUserService;

@Controller
@RequestMapping(value="/userRelation")
public class UserRealtionController {
	
	@Resource private IUserService userService;
	@Resource private UserRelationService userRelationService;
	
	/**
	 * 用户之间建立好友关系
	 */
	@RequestMapping(value="/buildRelation",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> buildRelation(int userIdA,int userIdB){
		UserRelation userRelation = new UserRelation();
		userRelation.setUserIdA(userIdA);
		userRelation.setUserIdB(userIdB);
		userRelation.setRelationStatus(1);
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		userRelation.setRelationStart(timestamp);
		userRelationService.addUserRelation(userRelation);
		Map<String,Object> resoult = new HashMap<String,Object>();
		resoult.put("resoult", "success");
		return resoult;
	}
	/**
	 * 解除好友关系
	 * @param userId
	 * @param httpSession
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/removeRelation",method=RequestMethod.POST)
	public String removeRelation(int userId,int currentUser){
		User user = userService.getUserById(currentUser);
		int idA = user.getUserid();
		int idB = userId;
		boolean remove = userRelationService.deleteUserRelation(idA, idB);
		//httpSession.setAttribute("friends", userRelationService.getAllFriends(user.getUserid()));
		if(remove){
			return "removeSuccess";
		}
		else{
			return "removeFail";
		}
	}
	/**
	 * 获取用户之间的关系
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getRelations",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getRelations(int userId){
		List<User> list = new ArrayList<User>();
		list = userRelationService.getAllFriends(userId);
		String relations = JSONArray.toJSONString(list);
		Map<String,Object> resoult = new HashMap<String,Object>();
		resoult.put("relations", relations);
		for(int i=0;i<resoult.size();i++){
			System.out.println(resoult.get(i));
		}
		return resoult;
	}
}
