package com.gra.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gra.entity.Finance;

/**
 * finance实体类的数据库访问层
 * @author susie
 *
 */
@Repository
public class FinanceDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	/**
	 * 查询所有财务信息
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Finance> getAllFinance(){
		String hql="from Finance";
		//获取session，生成query对象，设置参数，执行hql语句
		List<Finance> finance = this.getSession().createQuery(hql).list();
		return finance;
	}
	
	/**
	 * 通过时间查询财务信息
	 * @param time
	 * @return
	 */
	public Finance getFinanceByTime(String time){
		String hql="from Finance where p_time=?";
		Finance finance = (Finance) this.getSession().createQuery(hql).setParameter(0, time).uniqueResult();
		return finance;
	}
	/**
	 * 更新财务信息
	 * @param finance
	 */
	public void updateFinance(Finance finance) {
					
		this.getSession().update(finance);
	} 
	/**
	 * 添加财务信息
	 * @param finance
	 */
	public void addFinance(Finance finance) {
		
		this.getSession().save(finance);
	}
}
