package com.mobian.service;

import com.mobian.pageModel.FdPicture;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface FdPictureServiceI {

	/**
	 * 获取FdPicture数据表格
	 * 
	 * @param fdPicture
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(FdPicture fdPicture, PageHelper ph);

	/**
	 * 添加FdPicture
	 * 
	 * @param fdPicture
	 */
	public void add(FdPicture fdPicture);

	/**
	 * 获得FdPicture对象
	 * 
	 * @param id
	 * @return
	 */
	public FdPicture get(Integer id);

	/**
	 * 修改FdPicture
	 * 
	 * @param fdPicture
	 */
	public void edit(FdPicture fdPicture);

	/**
	 * 删除FdPicture
	 * 
	 * @param id
	 */
	public void delete(Integer id);

}
