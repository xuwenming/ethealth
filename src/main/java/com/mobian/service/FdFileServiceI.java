package com.mobian.service;

import com.mobian.pageModel.FdFile;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface FdFileServiceI {

	/**
	 * 获取FdFile数据表格
	 * 
	 * @param fdFile
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(FdFile fdFile, PageHelper ph);

	/**
	 * 添加FdFile
	 * 
	 * @param fdFile
	 */
	public void add(FdFile fdFile);

	/**
	 * 获得FdFile对象
	 * 
	 * @param id
	 * @return
	 */
	public FdFile get(Integer id);

	/**
	 * 修改FdFile
	 * 
	 * @param fdFile
	 */
	public void edit(FdFile fdFile);

	/**
	 * 删除FdFile
	 * 
	 * @param id
	 */
	public void delete(Integer id);

}
