package com.gra.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.gra.entity.UserGroupRelation;

@Repository
public class UserGroupRelationDAO {
@Resource private SessionFactory sessionFactory;
	
	public UserGroupRelation getUserGroupRelation(int userId, int groupId) {
		String hql = "from UserGroupRelation where userId=? and groupId=?";  
	    Query query = sessionFactory.getCurrentSession().createQuery(hql);  
	    query.setParameter(0, userId);
	    query.setParameter(1, groupId);
	    return (UserGroupRelation)query.uniqueResult();
	}

	public void addUserGroupRelation(UserGroupRelation userGroupRelation) {
		sessionFactory.getCurrentSession().save(userGroupRelation);
	}

	public boolean deleteUserGroupRelation(int userId, int groupId) {
		String hql = "delete UserGroupRelation where userId=? and groupId=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);  
	    query.setParameter(0, userId);
	    query.setParameter(1, groupId);
	    return query.executeUpdate() > 0;
	}

	public boolean updateUserGroupRelation(UserGroupRelation userGroupRelation) {
		String hql = "update UserGroupRelation set groupLevel=?,groupUserNickName=? where userId=? and groupId=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);  
	    query.setParameter(0, userGroupRelation.getGroupLevel());
	    query.setParameter(1, userGroupRelation.getGroupUserNickName());
	    query.setParameter(2, userGroupRelation.getUserId());
	    query.setParameter(3, userGroupRelation.getGroupId());
	    
	    return query.executeUpdate() > 0;
	}

	@SuppressWarnings("unchecked")
	public List<UserGroupRelation> getAllUser(int groupId) {
		String hql = "from UserGroupRelation where groupId=?";  
	    Query query = sessionFactory.getCurrentSession().createQuery(hql);  
	    query.setParameter(0, groupId);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<UserGroupRelation> getAllGroup(int userId) {
		String hql = "from UserGroupRelation where userId=?";  
	    Query query = sessionFactory.getCurrentSession().createQuery(hql);  
	    query.setParameter(0, userId);
		return query.list();
	}

}
