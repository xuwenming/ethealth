package com.mobian.service;

import com.mobian.pageModel.FdMemberConsultation;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface FdMemberConsultationServiceI {

	/**
	 * 获取FdMemberConsultation数据表格
	 * 
	 * @param fdMemberConsultation
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(FdMemberConsultation fdMemberConsultation, PageHelper ph);

	/**
	 * 添加FdMemberConsultation
	 * 
	 * @param fdMemberConsultation
	 */
	public void add(FdMemberConsultation fdMemberConsultation);

	/**
	 * 获得FdMemberConsultation对象
	 * 
	 * @param id
	 * @return
	 */
	public FdMemberConsultation get(Integer id);

	/**
	 * 修改FdMemberConsultation
	 * 
	 * @param fdMemberConsultation
	 */
	public void edit(FdMemberConsultation fdMemberConsultation);

	/**
	 * 删除FdMemberConsultation
	 * 
	 * @param id
	 */
	public void delete(Integer id);

}
