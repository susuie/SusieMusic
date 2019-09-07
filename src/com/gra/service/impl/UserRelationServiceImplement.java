package com.gra.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gra.dao.UserDAO;
import com.gra.dao.UserRelationDAO;
import com.gra.entity.User;
import com.gra.entity.UserRelation;
import com.gra.service.UserRelationService;

@Transactional
@Service
public class UserRelationServiceImplement implements UserRelationService {

	@Autowired private UserRelationDAO userRelationDao;
	@Autowired private UserDAO userDao;
	
	@Override
	public UserRelation getUserRelation(int idA, int idB) {
		return userRelationDao.getUserRelation(idA, idB);
	}

	@Override
	public void addUserRelation(UserRelation userRelation) {
		userRelationDao.addUserRelation(userRelation);
	}

	@Override
	public boolean deleteUserRelation(int idA, int idB) {
		try {
			userRelationDao.deleteUserRelation(idA, idB);; // 调用DAO层的删除方法
			return true; // 如果没有异常，则更新成功
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean updateUser(UserRelation userRelation) {
		return userRelationDao.updateUser(userRelation);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getAllFriends(int id) {
		List list = userRelationDao.getAllFriends(id);
		ArrayList<User> lists = new ArrayList<User>();
		for(int i=0;i<list.size();i++){
			User user = userDao.getUserById((Integer) list.get(i));
			lists.add(user);
		}
		return lists;
	}

}
