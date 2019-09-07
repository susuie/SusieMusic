package com.gra.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import com.gra.entity.Group;
import com.gra.entity.UserGroupRelation;
import com.gra.service.GroupService;
import com.gra.service.UserGroupRelationService;
import com.gra.service.IUserService;

/*
 * 群组的Controller，控制群组的创建，更新和删除
 * 
 * */

@Controller
@RequestMapping(value="/group")
public class GroupController {
	@Resource private GroupService groupService;
	@Resource private UserGroupRelationService userGroupRelationService;
	@Resource private IUserService userService;
	/**
	 * 创建群
	 * @param groupName
	 * @param groupIntroduction
	 * @param groupCreaterId
	 * @return
	 */
	@RequestMapping(value="/createGroup",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> createGroup(String groupName,String groupIntroduction,Integer groupCreaterId){
		//群组的groupId在这里随机生成（五位数字）
		Group group = new Group();
		String groupId =String.valueOf((int) (Math.random()*100000));
		while(groupService.getGroup(groupId)!=null){
			groupId =String.valueOf((int) (Math.random()*100000));
		}
		group.setGroupId(groupId);
		group.setGroupCreaterId(groupCreaterId);
		group.setGroupIntroduction(groupIntroduction);
		group.setGroupName(groupName);
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		group.setGroupCreateTime(timestamp);
		group.setGroupUserCount(0);
		group.setGroupMembers("");
		groupService.addGroup(group);
		UserGroupRelation userGroupRelation = new UserGroupRelation();
		//这里两个id不是一回事，一个是逻辑id，一个是业务id，要区分开
		Group groups = groupService.getGroup(groupId);
		userGroupRelation.setGroupId(groups.getId());
		userGroupRelation.setUserId(groupCreaterId);
		userGroupRelation.setEnterGroupTime(timestamp);
		userGroupRelation.setGroupUserNickName(userService.getUserById(groupCreaterId).getNickName());
		userGroupRelation.setGroupLevel(10);
		userGroupRelationService.addUserGroupRelation(userGroupRelation);
		groups.setGroupMembers(String.valueOf(groups.getId()+","+String.valueOf(groupCreaterId)));
		groups.setGroupUserCount(groups.getGroupUserCount()+1);
		groupService.updateGroup(groups);
		Map<String,Object> resoult = new HashMap<String,Object>();
		resoult.put("resoult", groupId);
		return resoult;
	}
	/**
	 * 根据ID查找群（这里的id是自动增加的id主键）
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/findGroupById",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> findGroupById(Integer id){
		Group group = groupService.getGroup(id);
		String JsonGroup = JSONArray.toJSONString(group,SerializerFeature.UseSingleQuotes);
		Map<String,Object> resoult = new HashMap<String,Object>();
		resoult.put("resoult", JsonGroup);
		return resoult;
	}
	/**
	 * 根据群号查找群（这里的id是建群时随机分配的号码）
	 * @param groupId
	 * @return
	 */
	@RequestMapping(value="/findGroupByGroupId",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> findGroupByGroupId(String groupId){
		Group group = groupService.getGroup(groupId);
		String JsonGroup = JSONArray.toJSONString(group,SerializerFeature.UseSingleQuotes);
		Map<String,Object> resoult = new HashMap<String,Object>();
		resoult.put("resoult", JsonGroup);
		return resoult;
	}
	
	private static SerializeConfig mapping = new SerializeConfig();  
	private static String dateFormat;  
	static {  
	    dateFormat = "yyyy-MM-dd HH:mm:ss";  
	    mapping.put(Timestamp.class, new SimpleDateFormatSerializer(dateFormat));  
	}
}
