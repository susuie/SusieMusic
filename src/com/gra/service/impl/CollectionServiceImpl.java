package com.gra.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gra.dao.CollectionDAO;
import com.gra.dao.SongDAO;
import com.gra.entity.Collections;
import com.gra.entity.Song;
import com.gra.service.ICollectionService;

/**
 * collection服务层
 * @author susie
 *
 */
@Transactional
@Service
public class CollectionServiceImpl implements ICollectionService{
	
	@Autowired
	private CollectionDAO collectionDAO;
	
	@Autowired
	private SongDAO songDAO;
	
	@Override
	public List<Song> getSongBySongId(Integer uid) {
		// TODO Auto-generated method stub		
		return collectionDAO.getSongBySongId(uid);
	}

	@Override
	public boolean addCollection(Collections collection) {
		// TODO Auto-generated method stub
		try{
			collectionDAO.addCollection(collection);
			return true;
		}catch(Exception e){
			return false;
		}
	}

}
