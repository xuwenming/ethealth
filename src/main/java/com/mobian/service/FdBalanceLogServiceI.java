package com.mobian.service;

import com.mobian.pageModel.FdBalanceLog;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface FdBalanceLogServiceI {

	/**
	 * 获取FdBalanceLog数据表格
	 * 
	 * @param fdBalanceLog
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(FdBalanceLog fdBalanceLog, PageHelper ph);

	/**
	 * 添加FdBalanceLog
	 * 
	 * @param fdBalanceLog
	 */
	public void add(FdBalanceLog fdBalanceLog);

	/**
	 * 获得FdBalanceLog对象
	 * 
	 * @param id
	 * @return
	 */
	public FdBalanceLog get(Integer id);

	/**
	 * 修改FdBalanceLog
	 * 
	 * @param fdBalanceLog
	 */
	public void edit(FdBalanceLog fdBalanceLog);

	/**
	 * 删除FdBalanceLog
	 * 
	 * @param id
	 */
	public void delete(Integer id);

}
