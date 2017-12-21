package com.mobian.service;

import com.mobian.pageModel.FdDoctorCloseTime;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface FdDoctorCloseTimeServiceI {

	/**
	 * 获取FdDoctorCloseTime数据表格
	 * 
	 * @param fdDoctorCloseTime
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(FdDoctorCloseTime fdDoctorCloseTime, PageHelper ph);

	/**
	 * 添加FdDoctorCloseTime
	 * 
	 * @param fdDoctorCloseTime
	 */
	public void add(FdDoctorCloseTime fdDoctorCloseTime);

	/**
	 * 获得FdDoctorCloseTime对象
	 * 
	 * @param id
	 * @return
	 */
	public FdDoctorCloseTime get(Integer id);

	/**
	 * 修改FdDoctorCloseTime
	 * 
	 * @param fdDoctorCloseTime
	 */
	public void edit(FdDoctorCloseTime fdDoctorCloseTime);

	/**
	 * 删除FdDoctorCloseTime
	 * 
	 * @param id
	 */
	public void delete(Integer id);

	void addCloseTime(FdDoctorCloseTime closeTime);
}
