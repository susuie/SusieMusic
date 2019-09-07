package com.gra.service;

import java.util.List;

import com.gra.entity.User;

/**
 * 用户 Service层
 * @author susie
 *
 */
public interface IUserService {
	
	/**
	 * 根据用户名查询
	 * @param username
	 * @return
	 */
	User getUserByName(String username);
	
	/**
	 * 获取总的记录数
	 * @return
	 */
	Integer getTotalRecords();
	
	/**
	 * 更新用户信息
	 * @param user
	 */
	boolean updateUser(User user);
	/**
	 * 更新用户密码
	 * @param user
	 * @return
	 */
	boolean updateUserPass(User user);
	/**
	 *  删除单个用户
	 * @param username
	 * @return
	 */
	boolean deleteUser(Integer u_id);
	
	/**
	 * 单个添加用户
	 * @param path
	 */
	boolean addUser(User user);
	
	/**
	 * 批量添加用户
	 * @param path
	 */
	String addUsers(String path);
	
	/**
	 * 获取所有用户信息
	 * @return
	 */
	List<User> getAllUsers();
	
	public User getUserById(Integer u_id);
	
	/**
	 * 获取分页数据
	 * @param currentPage
	 * @param type
	 * @return
	 */
	public List<User> getMessageByPage(Integer currentPage);

}
