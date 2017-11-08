package com.mobian.service;

import com.mobian.pageModel.FdBanner;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface FdBannerServiceI {

	/**
	 * 获取FdBanner数据表格
	 * 
	 * @param fdBanner
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(FdBanner fdBanner, PageHelper ph);

	/**
	 * 添加FdBanner
	 * 
	 * @param fdBanner
	 */
	public void add(FdBanner fdBanner);

	/**
	 * 获得FdBanner对象
	 * 
	 * @param id
	 * @return
	 */
	public FdBanner get(Integer id);

	/**
	 * 修改FdBanner
	 * 
	 * @param fdBanner
	 */
	public void edit(FdBanner fdBanner);

	/**
	 * 删除FdBanner
	 * 
	 * @param id
	 */
	public void delete(Integer id);

}
