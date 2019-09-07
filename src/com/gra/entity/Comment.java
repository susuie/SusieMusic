package com.gra.entity;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 歌曲评论实体类
 * @author johnn
 *
 */
@Entity
@Table(name="comment")
public class Comment {
	/**
	 * 评论ID,主键,自增
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="c_id",nullable=false)
	private Integer c_id;
	
	/**
	 * 被评论的歌曲ID
	 */
	@Column(name="s_id",nullable=false)
	private Integer s_id;
	
	/**
	 * 评论内容
	 */
	@Column(name="c_details",nullable=false)
	private String c_details;
	
	/**
	 * 评论时间
	 */
	@Column(name="c_time",nullable=false)
	private Date c_time;
	
	/**
	 * 发表评论用户ID
	 */
	@Column(name="u_id",nullable=false)
	private Integer u_id;
	
	@Column(name="username",nullable=false)
	private String username;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Comment(){}
	public Comment(Integer s_id,Integer u_id,String c_details,String username){
		this.s_id = s_id;		
		this.u_id = u_id;
		this.c_time = Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		this.c_details = c_details;
		this.username = username;
	}
	
	public Integer getC_id() {
		return c_id;
	}

	public void setC_id(Integer c_id) {
		this.c_id = c_id;
	}

	public Integer getS_id() {
		return s_id;
	}

	public void setS_id(Integer s_id) {
		this.s_id = s_id;
	}

	public String getC_details() {
		return c_details;
	}

	public void setC_details(String c_details) {
		this.c_details = c_details;
	}

	public Date getC_time() {
		return c_time;
	}

	public void setC_time(Date c_time) {
		this.c_time = c_time;
	}

	public Integer getU_id() {
		return u_id;
	}

	public void setU_id(Integer u_id) {
		this.u_id = u_id;
	}
	
	public String toString(){
		return "Comment [ c_id="+c_id+",c_details="+c_details+",c_time="+c_time
				+",u_id="+u_id+",s_id="+s_id+"]";
	}
}
