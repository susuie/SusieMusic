package com.gra.dao;


import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gra.entity.User;
import com.gra.util.PasswordHelper;

/**
 * user实体类的数据库访问层
 * @author curry
 *
 */
@Repository
public class UserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	//获取日志对象
	private static Logger logger = Logger.getLogger(UserDAO.class);
		
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	/**
	 * 根据用户名查询
	 * @param username
	 * @return
	 */
	public User getUserByName(String username) {
		String hql = "from User where username = ?"; //生成hql
		//获取session生成query对象，执行查询操作
		User user = (User) this.getSession().createQuery(hql).setParameter(0, username).uniqueResult(); 
		logger.info("==>In UserDAO getUserByName hql: " + hql);
		return user;
	}
	
	/**
	 * 根据用户id获取用户信息
	 * @param u_id
	 * @return
	 */
	public User getUserById(Integer u_id){
		String hql = "from User where u_id = ?";
		User user = (User)this.getSession().createQuery(hql).setParameter(0, u_id).uniqueResult();
		return user;
	}
	/**
	 * 模糊查询
	 * @param indistinct
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<User> getUserIndistinct(String indistinct) {
		String hql = "from User where realname like '%" + indistinct + "%'"; //生成hql,使用%通配符
		//获取session生成query对象，执行查询操作
		List<User> users = this.getSession().createQuery(hql).list();
		logger.info("==>In UserDAO getUserIndistinct hql: " + hql);
		return users;
	}
	
	/**
	 * 管理员界面获取所有用户信息
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers(){
		String hql = "from User"; //生成hql
		//获取session生成query对象，执行查询操作
		List<User> users = this.getSession().createQuery(hql).list(); 
		logger.info("==>In UserDAO getAllUsers hql: " + hql);
		return users;
	}

	/**
	 * 更新用户信息
	 * @param user
	 */
	public void updateUser(User user) {
		logger.info("==>In UserDAO updateUser info of user: " + user.toString());			
		this.getSession().update(user);
	}
	/**
	 * 更新用户信息包括密码
	 * @param user
	 */
	public void updateUserPass(User user){
		//调用加密算法对密码进行加密	
		String md5Pass = PasswordHelper.encryptToMD5(user.getPassword());
		user.setPassword(md5Pass);
		this.getSession().update(user);
	}
	/**
	 * 删除单个用户
	 * @param username
	 * @return
	 */
	public int deleteUser(Integer u_id) {
		String hql = "delete User where u_id = ?"; //生成删除的hql
		//获取session生成query对象，执行删除操作
		int flag = this.getSession().createQuery(hql).setParameter(0, u_id).executeUpdate();
		logger.info("==>In UserDAO deleteUser hql " + hql);
		return flag; //返回删除的标识
	}
	
	/**
	
	/**
	 * 添加用户信息
	 * @param user
	 */
	public void addUser(User user) {
		System.out.println("==>In UserDAO addUser info of user: " + user.toString());
		//调用加密算法对密码进行加密
		String md5Pass = PasswordHelper.encryptToMD5(user.getPassword());
		user.setPassword(md5Pass);
		this.getSession().save(user);
	}

	/**
	 * 获取总记录数
	 * @return
	 */
	public Integer getTotalRecord() {
		String hql = "select count(*) from User"; //hql
		Query query = this.getSession().createQuery(hql); //获取session生成query对象
		Integer totalRecord = ((Long)query.iterate().next()).intValue();  //执行查询操作
		logger.info("==>In UserDAO getTotalRecord hql " + hql);
		return totalRecord;
	}
	
	
}
