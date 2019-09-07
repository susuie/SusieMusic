package com.gra.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gra.dao.UserDAO;
import com.gra.entity.User;
import com.gra.service.IUserService;

/**
 * user服务层
 * 
 * @author curry
 *
 */
@Transactional
@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	public UserDAO userDAO;

	// 获取日志对象
	private static Logger logger = Logger.getLogger(UserServiceImpl.class);
	/**
	 * 获取所有用户信息
	 */
	@Override
	public List<User> getAllUsers() {
		return userDAO.getAllUsers(); // 调用DAO层的查询方法
	}
	/**
	 * 根据用户名查询
	 */
	@Override
	public User getUserByName(String username) {
		logger.info("==>In UserSeviceImpl getUserByName parameter username: " + username);
		return userDAO.getUserByName(username); // 调用DAO层的查询方法
	}
	/**
	 * 获取用户总数
	 */
	@Override
	public Integer getTotalRecords() {
		return userDAO.getTotalRecord(); // 调用DAO层的查询方法，返回总记录数
	}

	/**
	 * 更新用户信息
	 */
	@Override
	public boolean updateUser(User user) {
		try {
			userDAO.updateUser(user); // 调用DAO层的更新方法
			return true; // 如果没有异常，则更新成功
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * 删除单个用户
	 */
	@Override
	public boolean deleteUser(Integer u_id) {
		try {
			userDAO.deleteUser(u_id); // 调用DAO层的删除方法
			return true; // 如果没有异常，则删除成功
		} catch (Exception e) {
			return false;
		}
	}

/**
 * 添加用户信息
 */
	@Override
	public boolean addUser(User user) {
		try {
			userDAO.addUser(user); //调用dao层添加用户
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	
	@Override
	public List<User> getMessageByPage(Integer currentPage) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public User getUserById(Integer u_id) {
		// TODO Auto-generated method stub
		return userDAO.getUserById(u_id);
	}
	@Override
	public String addUsers(String path) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean updateUserPass(User user) {
		// TODO Auto-generated method stub
		try {
			userDAO.updateUserPass(user); // 调用DAO层的更新方法
			return true; // 如果没有异常，则更新成功
		} catch (Exception e) {
			return false;
		}
	}

}
