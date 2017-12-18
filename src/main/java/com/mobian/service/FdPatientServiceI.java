package com.mobian.service;

import com.mobian.pageModel.FdPatient;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface FdPatientServiceI {

	/**
	 * 获取FdPatient数据表格
	 * 
	 * @param fdPatient
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(FdPatient fdPatient, PageHelper ph);

	/**
	 * 添加FdPatient
	 * 
	 * @param fdPatient
	 */
	public void add(FdPatient fdPatient);

	/**
	 * 获得FdPatient对象
	 * 
	 * @return
	 */
	public FdPatient get(Integer userId);

	/**
	 * 修改FdPatient
	 * 
	 * @param fdPatient
	 */
	public void edit(FdPatient fdPatient);

	/**
	 * 删除FdPatient
	 * 
	 * @param id
	 */
	public void delete(Integer id);

}
