package com.gra.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户朋友圈消息实体类
 * @author johnn
 *
 */
@Entity
@Table(name="info")
public class Info {
	/**
	 * 朋友圈消息，主键自增
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="i_id",nullable=false)
	private Integer i_id;
	/**
	 * 朋友圈消息内容
	 */
	@Column(name="info",nullable=false,length=1024)
	private String info;
	/**
	 * 图片链接
	 */
	@Column(name="img_url")
	private String img_url;	
	/**
	 * 用户id
	 */
	@Column(name="u_id",nullable=false)
	private Integer u_id;
	/**
	 * 用户昵称
	 */
	@Column(name="nickname",nullable=false)
	private String nickName; 
	
	/**
	 * 消息发表时间
	 */
	@Column(name="i_time",nullable=false)
	private String i_time;
	
	public Info(){
		
	}
	public Info(String info,String img_url,Integer u_id,String nickName){
		this.i_time = new SimpleDateFormat("yyyy-MM-dd MM:mm:ss").format(new Date());		
		this.info = info;
		this.img_url = img_url;
		this.u_id = u_id;
		this.nickName = nickName;
	}
	public Integer getI_id() {
		return i_id;
	}
	public void setI_id(Integer i_id) {
		this.i_id = i_id;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getImg_url() {
		return img_url;
	}
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	
	public Integer getU_id() {
		return u_id;
	}
	public void setU_id(Integer u_id) {
		this.u_id = u_id;
	}
	
	public String getI_time() {
		return i_time;
	}
	public void setI_time(String i_time) {
		this.i_time = i_time;
	}
	
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String toString(){
		return "Info [i_id="+i_id+",info="+info+",img_url="+img_url
				+",i_time="+i_time+",u_id"+u_id+",nickName="+nickName+"]";
	}
	
}
