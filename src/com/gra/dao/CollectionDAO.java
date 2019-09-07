package com.gra.dao;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gra.entity.Collections;
import com.gra.entity.Song;

/**
 * collection实体类的数据访问层
 * @author susie
 *
 */
@Repository
public class CollectionDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	/**
	 * 将歌曲添加到歌单
	 * @param comment
	 */
	public void addCollection(Collections collection){
		this.getSession().save(collection);
	}
	
	/**
	 * 获取歌曲到我的歌单
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Song> getSongBySongId(Integer uid){
		String hql = "from Song where s_id in (select songid from Collections where userid=?)";
		//获取session，生成query对象，设置参数，执行查询操作
		Query query = this.getSession().createQuery(hql).setParameter(0, uid);
		List<Song> songs = query.list();
		return songs;
	}
}
