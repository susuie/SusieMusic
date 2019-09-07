package com.gra.form;

/**
 * SongForm类的创建是为了在SongController中更方便的接收数据
 * @author susie
 *
 */
public class SongForm {
	/**
	 * 上传歌曲的歌名
	 */
	private String title;
	
	/**
	 * 歌手名
	 */
	private String artist;
	
	/**
	 * 歌曲链接
	 */
	private String mp3;
	
	/**
	 * 歌曲风格，默认流行
	 */
	private String s_kind;
	
	/**
	 * 歌曲播放时的显示图
	 */
	private String poster;
	
	/**
	 * 歌曲歌词链接,若无则显示暂无歌词显示图
	 */
	private String lrc;
	
	public SongForm(){
		
	}

	public SongForm(String title,String artist,String mp3,String s_kind,String poster,String lrc){
		this.title = title;
		this.artist = artist;
		this.mp3 = mp3;
		this.s_kind = s_kind;
		this.poster = poster;
		this.lrc = lrc;
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

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getLrc() {
		return lrc;
	}

	public void setLrc(String lrc) {
		this.lrc = lrc;
	}
	
	public String toString(){
		return "SongForm [title="+title+",artist="+artist+",mp3="+mp3+
							",s_kind="+s_kind+",poster="+poster+",lrc="+lrc+"]";
	}
}
