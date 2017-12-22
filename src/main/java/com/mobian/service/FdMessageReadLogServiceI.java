package com.mobian.service;

import com.mobian.pageModel.FdMessageReadLog;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface FdMessageReadLogServiceI {

	/**
	 * 获取FdMessageReadLog数据表格
	 * 
	 * @param fdMessageReadLog
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(FdMessageReadLog fdMessageReadLog, PageHelper ph);

	/**
	 * 添加FdMessageReadLog
	 * 
	 * @param fdMessageReadLog
	 */
	public void add(FdMessageReadLog fdMessageReadLog);

	/**
	 * 获得FdMessageReadLog对象
	 * 
	 * @param id
	 * @return
	 */
	public FdMessageReadLog get(Integer id);

	/**
	 * 修改FdMessageReadLog
	 * 
	 * @param fdMessageReadLog
	 */
	public void edit(FdMessageReadLog fdMessageReadLog);

	/**
	 * 删除FdMessageReadLog
	 * 
	 * @param id
	 */
	public void delete(Integer id);

	FdMessageReadLog get(Integer messageId, Integer userId);
}
