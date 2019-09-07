package com.gra.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author curry
 * 用户实体类
 */
@Entity
@Table(name = "user")
public class User {
	
	/**
	 *  主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "u_id", nullable = false, unique = true)
	private Integer u_id;
	
	/**
	 * 用户密码
	 */
	@Column(name = "password", nullable = false)
	private String password;

	/**
	 * 用户名
	 */
	@Column(name = "username", nullable = false)
	private String username;
	
	/**
	 * 用户账户积分
	 */
	@Column(name = "balance", nullable = false)
	private String balance;
	
	/**
	 * 用户角色:普通用户，VIP用户，管理员
	 */
	@Column(name = "role", nullable = false)
	private String role;
	
	
	/**
	 * 用户VIP时间，以天为单位
	 */
	@Column(name="v_time",nullable = true)
	private Integer v_time;
	
	/**
	 * 用户类型
	 */
	@Column(name="v_type",nullable=false)
	private String v_type;
	
	/**
	 * 用户性别
	 */
	@Column(name="usex",nullable=false)
	private String usex;
	
	/**
	 * 用户头像
	 */
	@Column(name="umg",nullable=false)
	private String umg;
	/**
	 * 用户是否在线
	 */
	@Column(name="user_is_online")
	private Integer userIsOnline;
	
	/**
	 * 用户昵称
	 */
	@Column(name="nickname")
	private String nickName;
	

	public User() {

	}
	
	public User(Integer userid, String password, String name, String balance, String role,Integer time,String usex,String type,String nickname) {
		this.u_id = userid;
		this.password = password;
		this.username = name;
		this.balance = balance;
		this.role = role;
		this.v_time = time;
		this.usex = usex;
		this.v_type=type;
		this.nickName = nickname;
	}

	public User(String nickName,String password){
		this.nickName = nickName;
		this.password = password;
	}
	
	public Integer getUserid() {
		return u_id;
	}

	public void setUserid(Integer u_id) {
		this.u_id = u_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return username;
	}

	public void setName(String name) {
		this.username = name;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	 public Integer getTime(){
		 return v_time;
	 }
	 
	 public void setTime(Integer time){
		 this.v_time = time;
	 }
	 
	 public String getType(){
		 return v_type;
	 }
	 
	 public void setType(String type){
		 this.v_type=type;
	 }
	 
	 public String getUsex(){
		 return usex;
	 }
	 public void setUsex(String usex){
		 this.usex = usex;
	 }
	 public String getUmg(){
		 return umg;
	 }
	 public void setUmg(String umg){
		 this.umg = umg;
	 }	 	
	public Integer getUserIsOnline() {
		return userIsOnline;
	}

	public void setUserIsOnline(Integer userIsOnline) {
		this.userIsOnline = userIsOnline;
	}
	
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	@Override
	public String toString() {
		return "User [userid=" + u_id + ", password=" + password + ", name=" + username + ", balance="
				+ balance + ", role=" + role +  ", time=" + v_time +",type="+
				v_type+",usex="+usex+",umg="+umg+",user_is_online="+userIsOnline+",nickname="+nickName+"]";
	}

	

}
