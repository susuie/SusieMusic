package com.gra.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户好友实体类
 * @author johnn
 *
 */
@Entity
@Table(name="collection")
public class Collections {
	/**
	 * ID，主键，自增
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id",nullable=false)
	private Integer id;
	
	/**
	 * 当前登录用户id
	 */
	@Column(name="userid",nullable=false)
	private Integer userid;
	
	/**
	 * 当前登录用户的好友id
	 */
	@Column(name="songid",nullable=false)
	private Integer songid;

	public Collections(){}
	
	public Collections(Integer id,Integer userid,Integer songid){
		this.id = id;
		this.userid = userid;
		this.songid = songid;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getSongid() {
		return songid;
	}

	public void setSongid(Integer songid) {
		this.songid = songid;
	}
	
	public String toString(){
		return "Collections [ id="+id+",userid="+userid+",songid="+songid+"]";
	}
}
