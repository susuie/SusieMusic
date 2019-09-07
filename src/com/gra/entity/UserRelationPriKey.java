package com.gra.entity;

import java.io.Serializable;

public class UserRelationPriKey implements Serializable{
	/**
	 * UserRelation的联合主键
	 */
	private static final long serialVersionUID = 1L;
	private Integer userIdA;
	private Integer userIdB;
	
	public Integer getUserIdA() {
		return userIdA;
	}
	public void setUserIdA(Integer userIdA) {
		this.userIdA = userIdA;
	}
	public Integer getUserIdB() {
		return userIdB;
	}
	public void setUserIdB(Integer userIdB) {
		this.userIdB = userIdB;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + userIdA;
		result = prime * result + userIdB;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRelationPriKey other = (UserRelationPriKey) obj;
		if (userIdA != other.userIdA)
			return false;
		if (userIdB != other.userIdB)
			return false;
		return true;
	}
}
