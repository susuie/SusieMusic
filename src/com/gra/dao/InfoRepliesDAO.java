package com.gra.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gra.entity.InfoReplies;

/**
 * infoReplies实体类的数据库访问层
 * @author susie
 *
 */
@Repository
public class InfoRepliesDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	/**
	 * 根据动态id查看评论
	 * @param i_id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<InfoReplies> getAllReplies(Integer i_id){
		String hql = "from InfoReplies where i_id =?";
		List<InfoReplies> replies = this.getSession().createQuery(hql).setParameter(0, i_id).list();
		return replies;
	}
	/**
	 * 添加评论
	 * @param replies
	 */
	public void addReplies(InfoReplies replies){
		this.getSession().save(replies);
	}
}
