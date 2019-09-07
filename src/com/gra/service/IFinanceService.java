package com.gra.service;

import java.util.List;

import com.gra.entity.Finance;

public interface IFinanceService {
	
	/**
	 * 查询所有财务信息
	 * @return
	 */
	public List<Finance> getAllFinance();
	
	/**
	 * 更新财务信息
	 * @param finance
	 * @return
	 */
	public boolean updateFinance(Finance finance); 
	
	/**
	 * 添加财务信息
	 * @param finance
	 * @return
	 */
	public boolean addFinance(Finance finance);
	
	/**
	 * 根据时间查找财务信息
	 * @param time
	 * @return
	 */
	public Finance getFinanceByTime(String time);
}
