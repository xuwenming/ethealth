package com.mobian.service;

import com.mobian.pageModel.FdMemberAppointment;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface FdMemberAppointmentServiceI {

	/**
	 * 获取FdMemberAppointment数据表格
	 * 
	 * @param fdMemberAppointment
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(FdMemberAppointment fdMemberAppointment, PageHelper ph);

	/**
	 * 添加FdMemberAppointment
	 * 
	 * @param fdMemberAppointment
	 */
	public void add(FdMemberAppointment fdMemberAppointment);

	/**
	 * 获得FdMemberAppointment对象
	 * 
	 * @param id
	 * @return
	 */
	public FdMemberAppointment get(Integer id);

	/**
	 * 修改FdMemberAppointment
	 * 
	 * @param fdMemberAppointment
	 */
	public void edit(FdMemberAppointment fdMemberAppointment);

	/**
	 * 删除FdMemberAppointment
	 * 
	 * @param id
	 */
	public void delete(Integer id);

	FdMemberAppointment get(FdMemberAppointment fdMemberAppointment);
}
