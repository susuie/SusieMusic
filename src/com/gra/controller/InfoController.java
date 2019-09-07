package com.gra.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gra.entity.Info;
import com.gra.entity.InfoReplies;
import com.gra.entity.User;
import com.gra.service.IInfoRepliesService;
import com.gra.service.IInfoService;
import com.gra.service.IUserService;

/**
 * 广场消息管理控制器
 * @author susie
 *
 */
@Controller
@RequestMapping(value = "/info")
public class InfoController {
	@Autowired
	public IUserService userServiceImpl;
	@Autowired
	public IInfoService infoServiceImpl;
	@Autowired
	public IInfoRepliesService repliesServiceImpl;
	/**
	 * 用户发布动态，将动态添加到数据库
	 * @param info
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addInfo")
	public String addInfo(Info info){
		User user = userServiceImpl.getUserById(info.getU_id());
		String username = user.getNickName();
		info.setNickName(username);
		Info infos = new Info(info.getInfo(),info.getImg_url(),info.getU_id(),info.getNickName());
		
		boolean isAdd = infoServiceImpl.addInfo(infos);
		if(isAdd){
			return "addSuccess";
		}else{
			return "addFail";
		}
	}
	
	/**
	 * 获取所有的广场动态
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/list")
	public Map<String,Object> getAllInfo(){
		List<Info> info = infoServiceImpl.getAllInfo();
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("infos", info);
		return result;
	}
	/**
	 * 获取当前动态的所有评论
	 * @param i_id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getReplay")
	public Map<String,Object> getAllReplies(Integer i_id){
		List<InfoReplies> rep = repliesServiceImpl.getAllReplies(i_id);
		Map<String,Object> replay = new HashMap<String,Object>();
		replay.put("replies", rep);
		return replay;
	}
	/**
	 * 将评论添加到数据库
	 * @param replies
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addReplay")
	public String addReplay(InfoReplies replies){
		User user = userServiceImpl.getUserById(replies.getU_id());
		String username = user.getNickName();
		replies.setNickName(username);
		InfoReplies replay = new InfoReplies(replies.getU_id(),replies.getR_detailes(),replies.getI_id(),replies.getNickName());
		//System.out.println(new Date());
		//System.out.println(new SimpleDateFormat("yyyy-MM-dd MM:mm:ss").format(new Date()));
		boolean isAdd = repliesServiceImpl.addReplies(replay);
		if(isAdd){
			return "addSuccess";
		}else{
			return "addFail";
		}
	}
	/**
	 * 获取当前用户发表过的动态
	 * @param u_id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getInfo")
	public Map<String,Object> getInfoByUid(Integer u_id){
		List<Info> info = infoServiceImpl.getInfoByUid(u_id);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("infos", info);
		return result;
	}
}
