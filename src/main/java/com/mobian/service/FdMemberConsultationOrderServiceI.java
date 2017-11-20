package com.mobian.service;

import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.FdMemberConsultationOrder;
import com.mobian.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface FdMemberConsultationOrderServiceI {

	/**
	 * 获取FdMemberConsultationOrder数据表格
	 * 
	 * @param fdMemberConsultationOrder
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(FdMemberConsultationOrder fdMemberConsultationOrder, PageHelper ph);

	/**
	 * 添加FdMemberConsultationOrder
	 * 
	 * @param fdMemberConsultationOrder
	 */
	public void add(FdMemberConsultationOrder fdMemberConsultationOrder);

	/**
	 * 获得FdMemberConsultationOrder对象
	 * 
	 * @param id
	 * @return
	 */
	public FdMemberConsultationOrder get(Integer id);

	/**
	 * 修改FdMemberConsultationOrder
	 * 
	 * @param fdMemberConsultationOrder
	 */
	public void edit(FdMemberConsultationOrder fdMemberConsultationOrder);

	/**
	 * 删除FdMemberConsultationOrder
	 * 
	 * @param id
	 */
	public void delete(Integer id);

	FdMemberConsultationOrder get(FdMemberConsultationOrder consultationOrder);
}
