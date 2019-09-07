package com.gra.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.alibaba.fastjson.annotation.JSONField;

@Entity  
@Table(name="group_main") 
public class Group {
	private Integer id;
	private String groupId;
	private String groupName;
	private Integer groupCreaterId;
	private Timestamp groupCreateTime;
	private String groupIntroduction;
	private Integer groupUserCount;
	private String groupMembers;
	
	@Id
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	
	@Column(name="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="group_id")
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
	@Column(name="group_name")
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	@Column(name="group_creater_id")
	public Integer getGroupCreaterId() {
		return groupCreaterId;
	}
	public void setGroupCreaterId(Integer groupCreaterId) {
		this.groupCreaterId = groupCreaterId;
	}
	
	@Column(name="group_create_time")
	@JSONField (format="yyyy-MM-dd HH:mm:ss")
	public Timestamp getGroupCreateTime() {
		return groupCreateTime;
	}
	public void setGroupCreateTime(Timestamp groupCreateTime) {
		this.groupCreateTime = groupCreateTime;
	}
	
	@Column(name="group_introduction")
	public String getGroupIntroduction() {
		return groupIntroduction;
	}
	public void setGroupIntroduction(String groupIntroduction) {
		this.groupIntroduction = groupIntroduction;
	}
	
	@Column(name="group_user_count")
	public Integer getGroupUserCount() {
		return groupUserCount;
	}
	public void setGroupUserCount(Integer groupUserCount) {
		this.groupUserCount = groupUserCount;
	}
	
	@Column(name="group_members")
	public String getGroupMembers() {
		return groupMembers;
	}
	public void setGroupMembers(String groupMembers) {
		this.groupMembers = groupMembers;
	}
}
