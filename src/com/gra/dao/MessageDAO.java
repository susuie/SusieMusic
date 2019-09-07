package com.gra.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.gra.entity.Message;

@Repository
public class MessageDAO {

	@Resource private SessionFactory sessionFactory;
	@SuppressWarnings("unchecked")
	public List<Message> getMessageByFromId(int from) {
		String hql = "from Message where from=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);  
	    query.setParameter(0, from);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<Message> getMessageByToId(int to) {
		String hql = "from Message where to=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);  
	    query.setParameter(0, to);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<Message> getMessageByType(int type) {
		String hql = "from Message where type=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);  
	    query.setParameter(0, type);
		return query.list();
	}

	public void addMessage(Message message) {
		sessionFactory.getCurrentSession().save(message);
	}

	public boolean deleteMessage(Message message) {
		String hql = "delete Message where id=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);  
	    query.setParameter(0, message.getId());
	    return query.executeUpdate() > 0;

	}

	public boolean updateMessage(Message message) {
		String hql = "update Message set isTransport=? where id=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);  
	    query.setParameter(0, message.getIsTransport());
	    query.setParameter(1, message.getId());
	    return query.executeUpdate() > 0;
	}

	@SuppressWarnings("unchecked")
	public List<Message> getMessageUnReceive(int to) {
		String hql = "from Message where to=? and isTransport=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);  
	    query.setParameter(0, to);
	    query.setParameter(1, 0);
		return query.list();
	}
}
