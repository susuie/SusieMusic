package com.gra.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gra.dao.InfoRepliesDAO;
import com.gra.entity.InfoReplies;
import com.gra.service.IInfoRepliesService;

/**
 * Replies服务层
 * @author susie
 *
 */
@Transactional
@Service
public class InfoRepliesServiceImpl implements IInfoRepliesService{
	@Autowired
	public InfoRepliesDAO repliesDAO;
	
	@Override
	public List<InfoReplies> getAllReplies(Integer i_id) {
		// TODO Auto-generated method stub
		return repliesDAO.getAllReplies(i_id);
	}

	@Override
	public boolean addReplies(InfoReplies replies) {
		// TODO Auto-generated method stub
		try{
			repliesDAO.addReplies(replies);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

}
