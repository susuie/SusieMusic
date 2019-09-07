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
 * 
 * @author johnn
 *财务表实体类
 */
@Entity
@Table(name = "finance")
public class Finance {
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="p_id",nullable=false,unique = true)
	private Integer p_id;
	
	/**
	 * 时间
	 */
	@Column(name="p_time",nullable=false)
	private String p_time;
	
	/**
	 * 入账金额
	 */
	@Column(name="p_in",nullable=false)
	private String p_in;
	
	/**
	 * 出账金额
	 */
	@Column(name="p_out",nullable=false)
	private String p_out;
	
	/**
	 * 当天金额计算
	 */
	@Column(name="p_balance",nullable=false)
	private String p_balance;
	
	public Finance(){
		
	}
	
	public Finance(String p_in,String p_out,String p_balance){		
		this.p_time = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
		this.p_in = p_in;
		this.p_out = p_out;
		this.p_balance = p_balance;
	}
	
	public Integer getP_id() {
		return p_id;
	}

	public void setP_id(Integer p_id) {
		this.p_id = p_id;
	}

	public String getP_time() {
		return p_time;
	}

	public void setP_time(String p_time) {
		this.p_time = p_time;
	}

	public String getP_in() {
		return p_in;
	}

	public void setP_in(String p_in) {
		this.p_in = p_in;
	}

	public String getP_out() {
		return p_out;
	}

	public void setP_out(String p_out) {
		this.p_out = p_out;
	}

	public String getP_balance() {
		return p_balance;
	}

	public void setP_balance(String p_balance) {
		this.p_balance = p_balance;
	}
	@Override
	public String toString(){
		return "Finance [p_id="+ p_id +",p_time=" + p_time +
				",p_in="+ p_in +",p_out="+p_out+"p_balance"+ "]";
	}
}
