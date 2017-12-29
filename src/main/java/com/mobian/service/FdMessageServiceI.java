package com.mobian.service;

import com.mobian.pageModel.FdMessage;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface FdMessageServiceI {

	/**
	 * 获取FdMessage数据表格
	 * 
	 * @param fdMessage
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(FdMessage fdMessage, PageHelper ph);

	/**
	 * 添加FdMessage
	 * 
	 * @param fdMessage
	 */
	public void add(FdMessage fdMessage);

	/**
	 * 获得FdMessage对象
	 * 
	 * @param id
	 * @return
	 */
	public FdMessage get(Integer id);

	/**
	 * 修改FdMessage
	 * 
	 * @param fdMessage
	 */
	public void edit(FdMessage fdMessage);

	/**
	 * 删除FdMessage
	 * 
	 * @param id
	 */
	public void delete(Integer id);

	void addAndPushMessage(FdMessage message);

	void editAndPushMessage(FdMessage fdMessage);

	int getUnreadMsgCount(Integer userId, Integer isAdmin);
}
