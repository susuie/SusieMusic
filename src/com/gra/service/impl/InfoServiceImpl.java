package com.gra.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gra.dao.InfoDAO;
import com.gra.entity.Info;
import com.gra.service.IInfoService;

@Transactional
@Service
public class InfoServiceImpl implements IInfoService{
	@Autowired
	public InfoDAO infoDAO;
	
	@Override
	public List<Info> getAllInfo() {
		// TODO Auto-generated method stub
		return infoDAO.getAllInfo();
	}
	
	@Override
	public boolean addInfo(Info info) {
		// TODO Auto-generated method stub
		try {
			infoDAO.addInfo(info); //调用dao层添加消息
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Info> getInfoByUid(Integer u_id) {
		// TODO Auto-generated method stub
		return infoDAO.getInfoByUid(u_id);
	}

}
