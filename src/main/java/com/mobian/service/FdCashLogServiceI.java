package com.mobian.service;

import com.mobian.pageModel.FdCashLog;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface FdCashLogServiceI {

	/**
	 * 获取FdCashLog数据表格
	 * 
	 * @param fdCashLog
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(FdCashLog fdCashLog, PageHelper ph);

	/**
	 * 添加FdCashLog
	 * 
	 * @param fdCashLog
	 */
	public void add(FdCashLog fdCashLog);

	/**
	 * 获得FdCashLog对象
	 * 
	 * @param id
	 * @return
	 */
	public FdCashLog get(Integer id);

	/**
	 * 修改FdCashLog
	 * 
	 * @param fdCashLog
	 */
	public void edit(FdCashLog fdCashLog);

	/**
	 * 删除FdCashLog
	 * 
	 * @param id
	 */
	public void delete(Integer id);

}
