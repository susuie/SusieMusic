package com.gra.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;

@Entity  
@Table(name="user_group_relation")
@IdClass(value=UserGroupRelationPriKey.class)
public class UserGroupRelation {
	private Integer userId;
	private Integer groupId;
	private Integer groupLevel;
	private String groupUserNickName;
	private Timestamp enterGroupTime;
	
	@Id
	@Column(name="user_id")
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	@Id
	@Column(name="group_id")
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	
	@Column(name="group_level")
	public Integer getGroupLevel() {
		return groupLevel;
	}
	public void setGroupLevel(Integer groupLevel) {
		this.groupLevel = groupLevel;
	}
	
	@Column(name="group_user_nickname")
	public String getGroupUserNickName() {
		return groupUserNickName;
	}
	public void setGroupUserNickName(String groupUserNickName) {
		this.groupUserNickName = groupUserNickName;
	}
	
	@Column(name="enter_group_time")
	@JSONField (format="yyyy-MM-dd HH:mm:ss")
	public Timestamp getEnterGroupTime() {
		return enterGroupTime;
	}
	public void setEnterGroupTime(Timestamp enterGroupTime) {
		this.enterGroupTime = enterGroupTime;
	}
}
