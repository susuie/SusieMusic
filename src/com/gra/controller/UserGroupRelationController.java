package com.gra.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import com.gra.entity.Group;
import com.gra.entity.User;
import com.gra.entity.UserGroupRelation;
import com.gra.service.GroupService;
import com.gra.service.UserGroupRelationService;
import com.gra.service.IUserService;

@Controller
@RequestMapping(value="/userGroup")
public class UserGroupRelationController {
	@Autowired private UserGroupRelationService userGroupRelationService;
	@Autowired private GroupService groupService;
	@Autowired private IUserService userService;
	
	/**
	 * 获取用户拥有的群组
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/getUserGroups",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getUserGroups(int userId){
		List<UserGroupRelation> list = new ArrayList<UserGroupRelation>();
		list = userGroupRelationService.getAllGroup(userId);
		List<Group> groupList = new ArrayList<Group>();
		for(int i=0;i<list.size();i++){
			Group group = groupService.getGroup(list.get(i).getGroupId());
			groupList.add(group);
		}
		String groups = JSONArray.toJSONString(groupList,SerializerFeature.UseSingleQuotes);
		Map<String,Object> resoult = new HashMap<String,Object>();
		resoult.put("groups",groups);
		return  resoult;
	}
	/**
	 * 获取群成员
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getGroupUsers",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getGroupUsers(int id){
		List<UserGroupRelation> list = new ArrayList<UserGroupRelation>();
		list = userGroupRelationService.getAllUser(id);
		List<User> userList = new ArrayList<User>();
		for(int i=0;i<list.size();i++){
			User user = userService.getUserById(list.get(i).getUserId());
			userList.add(user);
		}
		String users = JSONArray.toJSONString(userList);
		String userGroups = JSONArray.toJSONString(list,SerializerFeature.UseSingleQuotes);
		Map<String,Object> resoult = new HashMap<String,Object>();
		resoult.put("userGroups", userGroups);
		resoult.put("users",users);
		return  resoult;
	}
	/**
	 * 添加群成员
	 * @param id
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/addGroupUsers",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> addGroupUsers(int id,int userId){
		UserGroupRelation userGroupRelation = new UserGroupRelation();
		userGroupRelation.setGroupId(id);
		userGroupRelation.setGroupLevel(0);
		userGroupRelation.setUserId(userId);
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		userGroupRelation.setEnterGroupTime(timestamp);
		userGroupRelation.setGroupUserNickName(userService.getUserById(userId).getNickName());
		userGroupRelationService.addUserGroupRelation(userGroupRelation);
		Group group = groupService.getGroup(id);
		group.setGroupMembers(group.getGroupMembers()+","+String.valueOf(userId));
		group.setGroupUserCount(group.getGroupUserCount()+1);
		groupService.updateGroup(group);
		Map<String,Object> resoult = new HashMap<String,Object>();
		resoult.put("resoult", "success");
		return resoult;
	}
	
	private static SerializeConfig mapping = new SerializeConfig();  
	private static String dateFormat;  
	static {  
	    dateFormat = "yyyy-MM-dd HH:mm:ss";  
	    mapping.put(Timestamp.class, new SimpleDateFormatSerializer(dateFormat));  
	}
}
