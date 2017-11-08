package com.mobian.service;

import com.mobian.pageModel.FdDoctorDynamic;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface FdDoctorDynamicServiceI {

	/**
	 * 获取FdDoctorDynamic数据表格
	 * 
	 * @param fdDoctorDynamic
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(FdDoctorDynamic fdDoctorDynamic, PageHelper ph);

	/**
	 * 添加FdDoctorDynamic
	 * 
	 * @param fdDoctorDynamic
	 */
	public void add(FdDoctorDynamic fdDoctorDynamic);

	/**
	 * 获得FdDoctorDynamic对象
	 * 
	 * @param id
	 * @return
	 */
	public FdDoctorDynamic get(Integer id);

	/**
	 * 修改FdDoctorDynamic
	 * 
	 * @param fdDoctorDynamic
	 */
	public void edit(FdDoctorDynamic fdDoctorDynamic);

	/**
	 * 删除FdDoctorDynamic
	 * 
	 * @param id
	 */
	public void delete(Integer id);

}
