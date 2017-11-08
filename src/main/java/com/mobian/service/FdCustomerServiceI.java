package com.mobian.service;

import com.mobian.pageModel.FdCustomer;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface FdCustomerServiceI {

	/**
	 * 获取FdCustomer数据表格
	 * 
	 * @param fdCustomer
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(FdCustomer fdCustomer, PageHelper ph);

	/**
	 * 添加FdCustomer
	 * 
	 * @param fdCustomer
	 */
	public void add(FdCustomer fdCustomer);

	/**
	 * 获得FdCustomer对象
	 * 
	 * @param id
	 * @return
	 */
	public FdCustomer get(Long userId);

	/**
	 * 修改FdCustomer
	 * 
	 * @param fdCustomer
	 */
	public void edit(FdCustomer fdCustomer);

	/**
	 * 删除FdCustomer
	 * 
	 * @param id
	 */
	public void delete(Integer id);

}
