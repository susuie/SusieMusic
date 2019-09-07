package com.gra.dao;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.gra.entity.Group;

@Repository
public class GroupDAO {
@Resource private SessionFactory sessionFactory;
	
	public Group getGroup(Integer id) {
		String hql = "from Group where id=?";
	    Query query = sessionFactory.getCurrentSession().createQuery(hql);
	    query.setParameter(0, id);
		return (Group)query.uniqueResult();
	}

	public Group getGroup(String groupId) {
		String hql = "from Group where groupId=?";  
	    Query query = sessionFactory.getCurrentSession().createQuery(hql);  
	    query.setParameter(0, groupId);
		return (Group)query.uniqueResult();
	}

	public void addGroup(Group group) {
		sessionFactory.getCurrentSession().save(group);
	}

	public boolean deleteGroup(int id) {
		String hql = "delete Group where id=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);  
	    query.setParameter(0, id);
	    
	    return query.executeUpdate() > 0;
	}

	public boolean updateGroup(Group group) {
		String hql = "update Group set groupName=?,groupCreaterId=?,groupIntroduction=?,groupUserCount=?,groupMembers=? where id=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);  
	    query.setParameter(0, group.getGroupName());
	    query.setParameter(1, group.getGroupCreaterId());
	    query.setParameter(2, group.getGroupIntroduction());
	    query.setParameter(3, group.getGroupUserCount());
	    query.setParameter(4, group.getGroupMembers());
	    query.setParameter(5, group.getId());
	    
	    return query.executeUpdate() > 0;
	}
}
