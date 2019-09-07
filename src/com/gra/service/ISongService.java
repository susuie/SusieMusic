package com.gra.service;

import java.util.List;

import com.gra.entity.Song;
import com.gra.form.QueryForm;

public interface ISongService {
	/**
	 * 显示主页歌曲
	 * @return
	 */

	public List<Song> getAllSongs();
	
	/**
	 * 根据风格显示歌曲
	 * @param kind
	 * @return
	 */
	public List<Song> getSongByKind(String kind);
	
	/**
	 * 根据点赞数显示热歌榜
	 * @param tups
	 * @return
	 */
	public List<Song> getSongByTups();
	
	/**
	 * 根据id先后顺序来显示新歌榜
	 * @param id
	 * @return
	 */
	public List<Song> getSongById();
	
	/**
	 * 根据ID查询歌曲
	 * @param id
	 * @return
	 */
	public Song getSongById(Integer id);
	/**
	 * 根据歌名进行模糊查询
	 * @param title
	 * @return
	 */
	public List<Song> getSongByTitle(String title);
	
	/**
	 * 根据歌手来进行查询
	 * @param artist
	 * @return
	 */
	public List<Song> getSongByArtist(String artist);
	
	/**
	 * 上传新的歌曲
	 * @param song
	 * @return
	 */
	public boolean addSong(Song song);
	
	/**
	 * 更新歌曲点赞数
	 * @param song
	 * @return
	 */
	public boolean updateSongTups(Song song);
}
