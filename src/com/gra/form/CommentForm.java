package com.gra.form;

import java.util.Date;

/**
 * CommentForm的创建是为了在SongController中更好的接收数据
 * @author susie
 *
 */
public class CommentForm {
	/**
	 * 被评论的歌曲ID
	 */
	private Integer s_id;
	
	/**
	 * 评论内容
	 */
	private String c_details;
	
	/**
	 * 发表评论用户ID
	 */
	private Integer u_id;
	
	/**
	 * 评论时间
	 */
	private Date c_time;
	
	/**
	 * 评论用户名
	 */
	private String username;

	public CommentForm(){}
	
	public CommentForm(Integer s_id,Integer u_id,String c_details,String username){
		this.s_id = s_id;
		this.u_id = u_id;
		this.c_details = c_details;	
		this.username = username;
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

	public Integer getU_id() {
		return u_id;
	}

	public void setU_id(Integer u_id) {
		this.u_id = u_id;
	}

	public Date getC_time() {
		return c_time;
	}

	public void setC_time(Date c_time) {
		this.c_time = c_time;
	}
	
	public String toString(){
		return "CommentForm [s_id="+s_id+",u_id="+u_id+",c_details="+c_details+",username="+username+"]";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
