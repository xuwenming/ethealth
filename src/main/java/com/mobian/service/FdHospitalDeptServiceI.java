package com.mobian.service;

import com.mobian.pageModel.FdHospitalDept;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface FdHospitalDeptServiceI {

	/**
	 * 获取FdHospitalDept数据表格
	 * 
	 * @param fdHospitalDept
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(FdHospitalDept fdHospitalDept, PageHelper ph);

	/**
	 * 添加FdHospitalDept
	 * 
	 * @param fdHospitalDept
	 */
	public void add(FdHospitalDept fdHospitalDept);

	/**
	 * 获得FdHospitalDept对象
	 * 
	 * @param id
	 * @return
	 */
	public FdHospitalDept get(Integer id);

	/**
	 * 修改FdHospitalDept
	 * 
	 * @param fdHospitalDept
	 */
	public void edit(FdHospitalDept fdHospitalDept);

	/**
	 * 删除FdHospitalDept
	 * 
	 * @param id
	 */
	public void delete(Integer id);

}
