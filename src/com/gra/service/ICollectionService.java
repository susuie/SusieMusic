package com.gra.service;

import java.util.List;

import com.gra.entity.Collections;
import com.gra.entity.Song;

public interface ICollectionService {
	/**
	 * 根据歌曲用户id查看该用户收藏的歌曲
	 * @param uid
	 * @return
	 */
	public List<Song> getSongBySongId(Integer uid);
	
	/**
	 * 添加歌曲到歌单
	 * @param comment
	 * @return
	 */
	public boolean addCollection(Collections collection);
}
