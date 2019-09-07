package com.gra.service;

import java.util.List;

import com.gra.entity.Info;

/**
 * 广场消息service层
 * @author susie
 *
 */
public interface IInfoService {
	
	/**
	 * 查询所有的广场动态消息
	 * @return
	 */
	public List<Info> getAllInfo();
	
	/**
	 * 添加动态信息
	 * @param info
	 * @return
	 */
	public boolean addInfo(Info info);
	
	/**
	 * 根据用户id获取相应的动态信息
	 * @param u_id
	 * @return
	 */
	public List<Info> getInfoByUid(Integer u_id);
}
