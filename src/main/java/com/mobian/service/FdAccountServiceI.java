package com.mobian.service;

import com.mobian.pageModel.FdAccount;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface FdAccountServiceI {

	/**
	 * 获取FdAccount数据表格
	 * 
	 * @param fdAccount
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(FdAccount fdAccount, PageHelper ph);

	/**
	 * 添加FdAccount
	 * 
	 * @param fdAccount
	 */
	public void add(FdAccount fdAccount);

	/**
	 * 获得FdAccount对象
	 * 
	 * @param id
	 * @return
	 */
	public FdAccount get(Long id);

	/**
	 * 修改FdAccount
	 * 
	 * @param fdAccount
	 */
	public void edit(FdAccount fdAccount);

	/**
	 * 删除FdAccount
	 * 
	 * @param id
	 */
	public void delete(Integer id);

}
