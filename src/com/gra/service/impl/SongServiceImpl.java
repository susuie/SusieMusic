package com.gra.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gra.dao.SongDAO;
import com.gra.entity.Song;
import com.gra.service.ISongService;

/**
 * song服务层
 * @author susie
 *
 */
@Transactional
@Service
public class SongServiceImpl implements ISongService{

	@Autowired
	public SongDAO songDAO;
	
	@Override
	public List<Song> getAllSongs() {
		
		return songDAO.getAllSongs();
	}

	@Override
	public List<Song> getSongByKind(String kind) {
		// TODO Auto-generated method stub
		return songDAO.getSongByKind(kind);
	}

	@Override
	public List<Song> getSongByTups() {
		// TODO Auto-generated method stub
		return songDAO.getSongByTups();
	}

	@Override
	public List<Song> getSongById() {
		// TODO Auto-generated method stub
		return songDAO.getSongById();
	}

	@Override
	public List<Song> getSongByTitle(String title) {
		// TODO Auto-generated method stub
		return songDAO.getSongByTitle(title);
	}

	@Override
	public List<Song> getSongByArtist(String artist) {
		// TODO Auto-generated method stub
		return songDAO.getSongByArtist(artist);
	}

	@Override
	public boolean addSong(Song song) {
		// TODO Auto-generated method stub
		try{
			songDAO.addsong(song);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}	
	}

	@Override
	public boolean updateSongTups(Song song) {
		// TODO Auto-generated method stub
		try {			
			songDAO.updateSongTups(song); //调用DAO层的更新方法
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Song getSongById(Integer id) {
		// TODO Auto-generated method stub
		return songDAO.getSongById(id);
	}
}
