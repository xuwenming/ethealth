package com.mobian.service;

import com.mobian.pageModel.FdDoctorOpinion;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface FdDoctorOpinionServiceI {

	/**
	 * 获取FdDoctorOpinion数据表格
	 * 
	 * @param fdDoctorOpinion
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(FdDoctorOpinion fdDoctorOpinion, PageHelper ph);

	/**
	 * 添加FdDoctorOpinion
	 * 
	 * @param fdDoctorOpinion
	 */
	public void add(FdDoctorOpinion fdDoctorOpinion);

	/**
	 * 获得FdDoctorOpinion对象
	 * 
	 * @param id
	 * @return
	 */
	public FdDoctorOpinion get(Integer id);

	/**
	 * 修改FdDoctorOpinion
	 * 
	 * @param fdDoctorOpinion
	 */
	public void edit(FdDoctorOpinion fdDoctorOpinion);

	/**
	 * 删除FdDoctorOpinion
	 * 
	 * @param id
	 */
	public void delete(Integer id);

}
