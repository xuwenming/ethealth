package com.mobian.service;

import com.mobian.pageModel.FdMemberDoctor;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface FdMemberDoctorServiceI {

	/**
	 * 获取FdMemberDoctor数据表格
	 * 
	 * @param fdMemberDoctor
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(FdMemberDoctor fdMemberDoctor, PageHelper ph);

	/**
	 * 添加FdMemberDoctor
	 * 
	 * @param fdMemberDoctor
	 */
	public void add(FdMemberDoctor fdMemberDoctor);

	/**
	 * 获得FdMemberDoctor对象
	 * 
	 * @param id
	 * @return
	 */
	public FdMemberDoctor get(Integer id);

	/**
	 * 修改FdMemberDoctor
	 * 
	 * @param fdMemberDoctor
	 */
	public void edit(FdMemberDoctor fdMemberDoctor);

	/**
	 * 删除FdMemberDoctor
	 * 
	 * @param id
	 */
	public void delete(Integer id);

}
