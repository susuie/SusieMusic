package com.gra.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 歌曲实体类
 * @author johnn
 *
 */
@Entity
@Table(name = "song")
public class Song {
	/**
	 * 歌曲主键,自增,也可作为歌曲发布时间先后，id越大则说明时间越晚
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="s_id",nullable=false,unique = true)
	private Integer s_id;
	
	/**
	 * 歌名
	 */
	@Column(name="title",nullable=false)
	private String title;
	
	/**
	 * 歌曲对应的歌手，不能为空
	 */
	@Column(name="artist",nullable=false)
	private String artist;
	
	/**
	 * 歌曲链接,不能为空
	 */
	@Column(name="mp3",nullable=false)
	private String mp3;
	
	/**
	 * 歌曲风格
	 */
	@Column(name="s_kind",nullable=false)
	private String s_kind;
	/**
	 * 歌曲被试听次数
	 */
	@Column(name="s_hears",nullable=false)
	private Integer s_hears;
	
	/**
	 * 歌曲被下载次数
	 */
	@Column(name="s_download",nullable=false)
	private Integer s_download;
	
	/**
	 * 歌曲被评论次数
	 */
	@Column(name="s_comment",nullable=false)
	private Integer s_comment;
	
	/**
	 * 歌曲被点赞次数
	 */
	@Column(name="s_tup",nullable=false)
	private Integer s_tup;

	/**
	 * 歌曲播放时的图片
	 */
	@Column(name="poster",nullable=false)
	private String poster;
	/**
	 * 歌词链接
	 */
	private String lrc;
	
	public Song(){
		
	}
	
	public Song(String title,String mp3,String poster,String artist,String lrc,String s_kind){		
		this.title=title;
		this.mp3 = mp3;
		this.poster = poster;
		this.artist = artist;
		this.lrc = lrc;
		this.s_kind = s_kind;
	}
	
	public Integer getS_id() {
		return s_id;
	}

	public void setS_id(Integer s_id) {
		this.s_id = s_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getMp3() {
		return mp3;
	}

	public void setMp3(String mp3) {
		this.mp3 = mp3;
	}

	public String getS_kind() {
		return s_kind;
	}

	public void setS_kind(String s_kind) {
		this.s_kind = s_kind;
	}

	public Integer getS_hears() {
		return s_hears;
	}

	public void setS_hears(Integer s_hears) {
		this.s_hears = s_hears;
	}

	public Integer getS_download() {
		return s_download;
	}

	public void setS_download(Integer s_download) {
		this.s_download = s_download;
	}

	public Integer getS_comment() {
		return s_comment;
	}

	public void setS_comment(Integer s_comment) {
		this.s_comment = s_comment;
	}

	public Integer getS_tup() {
		return s_tup;
	}

	public void setS_tup(Integer s_tup) {
		this.s_tup = s_tup;
	}
	
	public String getPoster(){
		return poster;
	}
	public void setPoster(String poster){
		this.poster = poster;
	}
	
	public String getLrc() {
		return lrc;
	}

	public void setLrc(String lrc) {
		this.lrc = lrc;
	}
	
	public String toString(){
		return "Song [s_id="+s_id+",title="+title+",artist="+artist+",s_kind="+s_kind+
				",mp3="+mp3+",s_hears="+s_hears+",s_comment="+s_comment+",s_download="+s_download+
				",s_tup="+s_tup+",poster="+poster+",lrc="+lrc+"]";
	}

	
}
