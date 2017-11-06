package com.mobian.service;

import com.mobian.pageModel.FdMedicinePractice;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface FdMedicinePracticeServiceI {

	/**
	 * 获取FdMedicinePractice数据表格
	 * 
	 * @param fdMedicinePractice
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(FdMedicinePractice fdMedicinePractice, PageHelper ph);

	/**
	 * 添加FdMedicinePractice
	 * 
	 * @param fdMedicinePractice
	 */
	public void add(FdMedicinePractice fdMedicinePractice);

	/**
	 * 获得FdMedicinePractice对象
	 * 
	 * @param id
	 * @return
	 */
	public FdMedicinePractice get(Integer id);

	/**
	 * 修改FdMedicinePractice
	 * 
	 * @param fdMedicinePractice
	 */
	public void edit(FdMedicinePractice fdMedicinePractice);

	/**
	 * 删除FdMedicinePractice
	 * 
	 * @param id
	 */
	public void delete(Integer id);

}
