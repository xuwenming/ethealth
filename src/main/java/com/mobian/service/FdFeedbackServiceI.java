package com.mobian.service;

import com.mobian.pageModel.FdFeedback;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface FdFeedbackServiceI {

	/**
	 * 获取FdFeedback数据表格
	 * 
	 * @param fdFeedback
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(FdFeedback fdFeedback, PageHelper ph);

	/**
	 * 添加FdFeedback
	 * 
	 * @param fdFeedback
	 */
	public void add(FdFeedback fdFeedback);

	/**
	 * 获得FdFeedback对象
	 * 
	 * @param id
	 * @return
	 */
	public FdFeedback get(Integer id);

	/**
	 * 修改FdFeedback
	 * 
	 * @param fdFeedback
	 */
	public void edit(FdFeedback fdFeedback);

	/**
	 * 删除FdFeedback
	 * 
	 * @param id
	 */
	public void delete(Integer id);

}
