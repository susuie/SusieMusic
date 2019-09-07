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
 * 消息回复实体类
 * @author johnn
 *
 */
@Entity
@Table(name="inforeplies")
public class InfoReplies {
	/**
	 * 回复id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="r_id",nullable=false)
	private Integer r_id;
	/**
	 * 回复内容
	 */
	@Column(name="r_detailes",nullable=false)
	private String r_detailes;
	/**
	 * 回复用户id
	 */
	@Column(name="u_id",nullable=false)
	private Integer u_id;
	/**
	 * 发布消息id
	 */
	@Column(name="i_id",nullable=false)
	private Integer i_id;
	/**
	 * 回复时间
	 */
	@Column(name="r_time",nullable=false)
	private Date r_time;
	
	/**
	 * 用户昵称
	 */
	@Column(name="nickname",nullable=false)
	private String nickName; 
	
	
	public InfoReplies(){
		
	}
	public InfoReplies(Integer u_id,String r_detailes,Integer i_id,String nickName){
		this.r_time = Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));		
		this.u_id = u_id;		
		this.r_detailes = r_detailes;
		this.i_id = i_id;
		this.nickName = nickName;
	}
	
	public Integer getR_id() {
		return r_id;
	}
	public void setR_id(Integer r_id) {
		this.r_id = r_id;
	}
	public String getR_detailes() {
		return r_detailes;
	}
	public void setR_detailes(String r_detailes) {
		this.r_detailes = r_detailes;
	}
	public Integer getU_id() {
		return u_id;
	}
	public void setU_id(Integer u_id) {
		this.u_id = u_id;
	}
	public Integer getI_id() {
		return i_id;
	}
	public void setI_id(Integer i_id) {
		this.i_id = i_id;
	}
	public Date getR_time() {
		return r_time;
	}
	public void setR_time(Date r_time) {
		this.r_time = r_time;
	}
	
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String toString(){
		return "InfoReplies [r_id="+r_id+",r_detailes="+r_detailes+",r_time="+r_time+
				",u_id="+u_id+",i_id="+i_id+",nickName="+nickName+"]";
	}
	
}
