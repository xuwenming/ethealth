package com.mobian.service;

import com.mobian.pageModel.FdDoctorGroup;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface FdDoctorGroupServiceI {

	/**
	 * 获取FdDoctorGroup数据表格
	 * 
	 * @param fdDoctorGroup
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(FdDoctorGroup fdDoctorGroup, PageHelper ph);

	/**
	 * 添加FdDoctorGroup
	 * 
	 * @param fdDoctorGroup
	 */
	public void add(FdDoctorGroup fdDoctorGroup);

	/**
	 * 获得FdDoctorGroup对象
	 * 
	 * @param id
	 * @return
	 */
	public FdDoctorGroup get(Integer id);

	/**
	 * 修改FdDoctorGroup
	 * 
	 * @param fdDoctorGroup
	 */
	public void edit(FdDoctorGroup fdDoctorGroup);

	/**
	 * 删除FdDoctorGroup
	 * 
	 * @param id
	 */
	public void delete(Integer id);

}
