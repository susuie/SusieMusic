package com.gra.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gra.entity.Collections;
import com.gra.entity.Song;
import com.gra.service.ICollectionService;

/**
 * 歌单管理控制器
 * @author johnn
 *
 */
@Controller
@RequestMapping(value="/collection")
public class CollectionController {
	@Autowired
	public ICollectionService collectionServiceImpl;
	
	/**
	 * 将歌曲添加到歌单
	 * @param collection
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addCollection",method = RequestMethod.POST,produces={"text/html;charset=UTF-8;","application/json;"})
	public String addCollection(Collections collection){
		boolean isAdded = collectionServiceImpl.addCollection(collection);
		if(isAdded){
			return "添加成功";
		}else{
			return "添加失败";
		}		
	}
	/**
	 * 显示当前用户收藏的歌曲
	 * @param uid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/list")
	public Map<String,Object> getSongBySongId(Integer uid){
		List<Song> songs = collectionServiceImpl.getSongBySongId(uid);
		Map<String,Object> info = new HashMap<String,Object>();
		info.put("song", songs);
		return info;
	}
}
