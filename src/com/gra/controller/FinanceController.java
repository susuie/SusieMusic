package com.gra.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gra.entity.Finance;
import com.gra.service.IFinanceService;

@Controller
@RequestMapping(value = "/finance")
public class FinanceController {
	@Autowired
	public IFinanceService financeServiceImpl;
	
	/**
	 * 显示所有财务信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "lists")
	public Map<String , Object> getAllFinance(){
		List<Finance> finance = financeServiceImpl.getAllFinance();
		Map<String , Object> info = new HashMap<String,Object>();
		info.put("data", finance);
		return info;
	}
}
