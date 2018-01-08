package com.mobian.service;

import com.mobian.pageModel.FdMemberConsultationLog;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface FdMemberConsultationLogServiceI {

	/**
	 * 获取FdMemberConsultationLog数据表格
	 * 
	 * @param fdMemberConsultationLog
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(FdMemberConsultationLog fdMemberConsultationLog, PageHelper ph);

	/**
	 * 添加FdMemberConsultationLog
	 * 
	 * @param fdMemberConsultationLog
	 */
	public void add(FdMemberConsultationLog fdMemberConsultationLog);

	/**
	 * 获得FdMemberConsultationLog对象
	 * 
	 * @param id
	 * @return
	 */
	public FdMemberConsultationLog get(Integer id);

	/**
	 * 修改FdMemberConsultationLog
	 * 
	 * @param fdMemberConsultationLog
	 */
	public void edit(FdMemberConsultationLog fdMemberConsultationLog);

	/**
	 * 删除FdMemberConsultationLog
	 * 
	 * @param id
	 */
	public void delete(Integer id);

}
