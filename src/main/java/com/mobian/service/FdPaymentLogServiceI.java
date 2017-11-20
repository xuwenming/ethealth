package com.mobian.service;

import com.mobian.pageModel.FdPaymentLog;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface FdPaymentLogServiceI {

	/**
	 * 获取FdPaymentLog数据表格
	 * 
	 * @param fdPaymentLog
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(FdPaymentLog fdPaymentLog, PageHelper ph);

	/**
	 * 添加FdPaymentLog
	 * 
	 * @param fdPaymentLog
	 */
	public void add(FdPaymentLog fdPaymentLog);

	/**
	 * 获得FdPaymentLog对象
	 * 
	 * @param id
	 * @return
	 */
	public FdPaymentLog get(Integer id);

	/**
	 * 修改FdPaymentLog
	 * 
	 * @param fdPaymentLog
	 */
	public void edit(FdPaymentLog fdPaymentLog);

	/**
	 * 删除FdPaymentLog
	 * 
	 * @param id
	 */
	public void delete(Integer id);

}
