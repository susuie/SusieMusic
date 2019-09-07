package com.gra.service;

import java.util.List;

import com.gra.entity.InfoReplies;

public interface IInfoRepliesService {
	/**
	 * 获取说有该动态的评论
	 * @return
	 */
	public List<InfoReplies> getAllReplies(Integer i_id);
	
	/**
	 * 添加动态评论
	 * @param replies
	 * @return
	 */
	public boolean addReplies(InfoReplies replies);
}
