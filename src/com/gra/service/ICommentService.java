package com.gra.service;

import java.util.List;

import com.gra.entity.Comment;

public interface ICommentService {
	
	/**
	 * 根据歌曲id查看歌曲的评论
	 * @param s_id
	 * @return
	 */
	public List<Comment> getAllComments(Integer s_id);
	
	/**
	 * 添加评论
	 * @param comment
	 * @return
	 */
	public boolean addComment(Comment comment);

}
