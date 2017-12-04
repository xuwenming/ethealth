package com.mobian.service;

import com.mobian.pageModel.FdDoctorTime;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.PageHelper;

import java.util.List;

/**
 * 
 * @author John
 * 
 */
public interface FdDoctorTimeServiceI {

	/**
	 * 获取FdDoctorTime数据表格
	 * 
	 * @param fdDoctorTime
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(FdDoctorTime fdDoctorTime, PageHelper ph);

	/**
	 * 添加FdDoctorTime
	 * 
	 * @param fdDoctorTime
	 */
	public void add(FdDoctorTime fdDoctorTime);

	/**
	 * 获得FdDoctorTime对象
	 * 
	 * @param id
	 * @return
	 */
	public FdDoctorTime get(Integer id);

	/**
	 * 修改FdDoctorTime
	 * 
	 * @param fdDoctorTime
	 */
	public void edit(FdDoctorTime fdDoctorTime);

	/**
	 * 删除FdDoctorTime
	 * 
	 * @param id
	 */
	public void delete(Integer id);

	List<String> getGroupTimesByDoctorId(Integer doctorId);
}
