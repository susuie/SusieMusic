package com.gra.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gra.entity.Song;


/**
 * Song实体类的数据库访问层
 * @author susie
 *
 */
@Repository
public class SongDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	//获取日志对象
	private static Logger logger = Logger.getLogger(SongDAO.class);
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	/**
	 * 显示主页歌曲
	 * @return List<Song>
	 */
	@SuppressWarnings("unchecked")
	public List<Song> getAllSongs(){
		String hql = "from Song";//生成hql
		//获取session,生成query对象，设置参数，执行查询操作		
		List<Song> songs = this.getSession().createQuery(hql).list();
		logger.info("==>In SongDAO getAllSongs hql: " + hql);
//		for(Song song:songs){
//			System.out.println(song);
//		}
		return songs;		
	}
	
	/**
	 * 根据歌曲风格在主题歌曲页面显示
	 * @param kind
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Song> getSongByKind(String kind){
		String hql = "from Song where s_kind=?";
		// 获取session，生成query对象，设置参数，执行查询操作
		List<Song> songs = this.getSession().createQuery(hql).setParameter(0, kind).list();
		return songs;
	}
	
	/**
	 * 显示热歌榜
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Song> getSongByTups(){
		String hql = "from Song order by s_tup DESC";
		//获取session，生成query对象，设置参数，执行查询操作
		Query query = this.getSession().createQuery(hql); 
		query.setMaxResults(5);
		List<Song> songs  = query.list();
		return songs;
	}
	
	/**
	 * 显示新歌榜
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Song> getSongById(){
		String hql = "from Song order by s_id DESC";
		//获取session，生成query对象，设置参数，执行查询操作
		Query query = this.getSession().createQuery(hql);
		query.setMaxResults(8);
		List<Song> songs = query.list();
		return songs;
	}
	
	/**
	 * 根据Id查找歌曲
	 * @param id
	 * @return
	 */
	public Song getSongById(Integer s_id){
		String hql = "from Song where s_id=?";
		//获取session，生成query对象，设置参数，执行查询操作
		Song song = (Song)this.getSession().createQuery(hql).setParameter(0, s_id).uniqueResult();			
		return song;
	}
	
	/**
	 * 根据歌名搜索歌曲
	 * @param title
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Song> getSongByTitle(String title){
		//hql,使用了通配符
		String hql = "from Song where title like '%" + title + "%'";
		//获取session，生成query对象，设置参数，执行查询操作
		List<Song> songs = this.getSession().createQuery(hql).list();
		System.out.println(songs.size());
		return songs;
	}
	
	/**
	 * 根据歌手名查询歌曲
	 * @param artist
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Song> getSongByArtist(String artist){
		//hql,使用了通配符
		String hql = "from Song where artist = ?";
		//获取session,生成query对象，设置参数，执行查询操作
		List<Song> songs = this.getSession().createQuery(hql).setParameter(0, artist).list();
		return songs;
	}
	
	/**
	 * 上传歌曲
	 * @param song
	 */
	public void addsong(Song song){
		this.getSession().save(song);
	}
	
	/**
	 * 更新点赞信息
	 * @param song
	 */
	public void updateSongTups(Song song) {
		this.getSession().update(song);
	}
	
	public Integer getTotalRecords(){
		String hql = "select count(*) from Song";
		Query query = this.getSession().createQuery(hql);
		Integer totalRecord = ((Long) query.iterate().next()).intValue();
		logger.info("==>In SongDAO getTotalRecords hql: " + hql);
		return totalRecord;
	}
	
}
