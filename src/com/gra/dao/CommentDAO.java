package com.gra.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gra.entity.Comment;

/**
 * comment实体类的数据访问层
 * @author susie
 *
 */
@Repository
public class CommentDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	/**
	 * 根据歌曲id返回所有的评论
	 * @param s_id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Comment> getAllComments(Integer s_id){
		String hql = "from Comment where s_id = ?";
		//获取session，生成query对象，设置参数，执行查询操作
		List<Comment> comments = this.getSession().createQuery(hql).setParameter(0, s_id).list();
		return comments;
	}
	
	/**
	 * 给歌曲添加评论
	 * @param comment
	 */
	public void addComment(Comment comment){
		this.getSession().save(comment);
	}
}
