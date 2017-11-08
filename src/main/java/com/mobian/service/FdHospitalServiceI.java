package com.mobian.service;

import com.mobian.pageModel.FdHospital;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface FdHospitalServiceI {

	/**
	 * 获取FdHospital数据表格
	 * 
	 * @param fdHospital
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(FdHospital fdHospital, PageHelper ph);

	/**
	 * 添加FdHospital
	 * 
	 * @param fdHospital
	 */
	public void add(FdHospital fdHospital);

	/**
	 * 获得FdHospital对象
	 * 
	 * @param id
	 * @return
	 */
	public FdHospital get(Integer id);

	/**
	 * 修改FdHospital
	 * 
	 * @param fdHospital
	 */
	public void edit(FdHospital fdHospital);

	/**
	 * 删除FdHospital
	 * 
	 * @param id
	 */
	public void delete(Integer id);

}
