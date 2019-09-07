package com.gra.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gra.dao.UserGroupRelationDAO;
import com.gra.entity.UserGroupRelation;
import com.gra.service.UserGroupRelationService;

@Transactional
@Service
public class UserGroupRelationServiceImplement implements
		UserGroupRelationService {

	@Autowired private UserGroupRelationDAO userGroupRelationDao;
	
	@Override
	public UserGroupRelation getUserGroupRelation(int userId, int groupId) {
		return userGroupRelationDao.getUserGroupRelation(userId, groupId);
	}

	@Override
	public void addUserGroupRelation(UserGroupRelation userGroupRelation) {
		userGroupRelationDao.addUserGroupRelation(userGroupRelation);
	}

	@Override
	public boolean deleteUserGroupRelation(int userId, int groupId) {
		return userGroupRelationDao.deleteUserGroupRelation(userId, groupId);
	}

	@Override
	public boolean updateUserGroupRelation(UserGroupRelation userGroupRelation) {
		return userGroupRelationDao.updateUserGroupRelation(userGroupRelation);
	}

	@Override
	public List<UserGroupRelation> getAllUser(int groupId) {
		return userGroupRelationDao.getAllUser(groupId);
	}

	@Override
	public List<UserGroupRelation> getAllGroup(int userId) {
		return userGroupRelationDao.getAllGroup(userId);
	}

}
