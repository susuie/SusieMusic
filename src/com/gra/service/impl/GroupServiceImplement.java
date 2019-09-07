package com.gra.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gra.dao.GroupDAO;
import com.gra.entity.Group;
import com.gra.service.GroupService;

@Transactional
@Service
public class GroupServiceImplement implements GroupService {
	
	@Autowired private GroupDAO groupDao;
    
	@Override
	public Group getGroup(int id) {
		return groupDao.getGroup(id);
	}

	@Override
	public Group getGroup(String groupId) {
		return groupDao.getGroup(groupId);
	}

	@Override
	public void addGroup(Group group) {
		groupDao.addGroup(group);
	}

	@Override
	public boolean deleteGroup(int id) {
		return groupDao.deleteGroup(id);
	}

	@Override
	public boolean updateGroup(Group group) {
		return groupDao.updateGroup(group);
	}

}
