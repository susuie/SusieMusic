package com.gra.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gra.dao.CommentDAO;
import com.gra.dao.UserDAO;
import com.gra.entity.Comment;
import com.gra.entity.User;
import com.gra.service.ICommentService;

/**
 * comment服务层
 * @author susie
 *
 */
@Transactional
@Service
public class CommentServiceImpl implements ICommentService{
	@Autowired
	private CommentDAO commentDAO;
	
	@Autowired
	private UserDAO userDAO;

	@Override
	public List<Comment> getAllComments(Integer s_id) {
		// TODO Auto-generated method stub
		
		return commentDAO.getAllComments(s_id);
	}

	@Override
	public boolean addComment(Comment comment) {
		// TODO Auto-generated method stub
		try{
			commentDAO.addComment(comment);
			return true;
		}catch(Exception e){
			return false;
		}
		
	}
	
	
}
