package com.mobian.service;

import com.mobian.pageModel.FdMemberDoctorLevel;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface FdMemberDoctorLevelServiceI {

	/**
	 * 获取FdMemberDoctorLevel数据表格
	 * 
	 * @param fdMemberDoctorLevel
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(FdMemberDoctorLevel fdMemberDoctorLevel, PageHelper ph);

	/**
	 * 添加FdMemberDoctorLevel
	 * 
	 * @param fdMemberDoctorLevel
	 */
	public void add(FdMemberDoctorLevel fdMemberDoctorLevel);

	/**
	 * 获得FdMemberDoctorLevel对象
	 * 
	 * @param id
	 * @return
	 */
	public FdMemberDoctorLevel get(Integer id);

	/**
	 * 修改FdMemberDoctorLevel
	 * 
	 * @param fdMemberDoctorLevel
	 */
	public void edit(FdMemberDoctorLevel fdMemberDoctorLevel);

	/**
	 * 删除FdMemberDoctorLevel
	 * 
	 * @param id
	 */
	public void delete(Integer id);

}
