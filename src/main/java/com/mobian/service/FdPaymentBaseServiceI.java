package com.mobian.service;

import com.mobian.pageModel.FdPaymentBase;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface FdPaymentBaseServiceI {

	/**
	 * 获取FdPaymentBase数据表格
	 * 
	 * @param fdPaymentBase
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(FdPaymentBase fdPaymentBase, PageHelper ph);

	/**
	 * 添加FdPaymentBase
	 * 
	 * @param fdPaymentBase
	 */
	public void add(FdPaymentBase fdPaymentBase);

	/**
	 * 获得FdPaymentBase对象
	 * 
	 * @param id
	 * @return
	 */
	public FdPaymentBase get(Integer id);

	/**
	 * 修改FdPaymentBase
	 * 
	 * @param fdPaymentBase
	 */
	public void edit(FdPaymentBase fdPaymentBase);

	/**
	 * 删除FdPaymentBase
	 * 
	 * @param id
	 */
	public void delete(Integer id);

	FdPaymentBase getByOrderNo(String orderNo);

	void addOrUpdate(FdPaymentBase payment);
}
