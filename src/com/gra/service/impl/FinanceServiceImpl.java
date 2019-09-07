package com.gra.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gra.dao.FinanceDAO;
import com.gra.entity.Finance;
import com.gra.service.IFinanceService;

/**
 * finance服务层
 * @author susie
 *
 */
@Transactional
@Service
public class FinanceServiceImpl implements IFinanceService{
	
	@Autowired
	public FinanceDAO financeDAO;
	
	@Override
	public List<Finance> getAllFinance() {
		return financeDAO.getAllFinance();
	}

	@Override
	public boolean updateFinance(Finance finance) {
		try {
			financeDAO.updateFinance(finance);// 调用DAO层的更新方法
			return true; // 如果没有异常，则更新成功
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean addFinance(Finance finance) {
		try {
			financeDAO.addFinance(finance); // 调用DAO层的更新方法
			return true; // 如果没有异常，则更新成功
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Finance getFinanceByTime(String time) {
		// TODO Auto-generated method stub
		return financeDAO.getFinanceByTime(time);
	}

}
