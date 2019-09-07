package com.gra.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gra.entity.Info;

/**
 * info实体类的数据库访问层
 * @author susie
 *
 */
@Repository
public class InfoDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	/**
	 * 查询所有广场动态信息
	 * @return
	 */
	@SuppressWarnings("unchecked" )
	public List<Info> getAllInfo(){
		String hql = "from Info order by i_id DESC";
		//获取session，生成query对象，设置参数，执行hql语句
				List<Info> info = this.getSession().createQuery(hql).list();
				return info;
	}
	/**
	 * 将用户发表的动态添加到数据库
	 * @param info
	 */
	public void addInfo(Info info){
		this.getSession().save(info);
	}
	/**
	 * 根据用户id获取用户的个人动态
	 * @param u_id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Info> getInfoByUid(Integer u_id){
		String hql = "from Info where u_id=? order by i_id DESC";
		List<Info> info = this.getSession().createQuery(hql).setParameter(0, u_id).list();
		return info;
	}
}
