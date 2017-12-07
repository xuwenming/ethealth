package com.mobian.service;

import com.mobian.pageModel.FdMemberAppointmentComment;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.PageHelper;

import java.util.List;

/**
 * 
 * @author John
 * 
 */
public interface FdMemberAppointmentCommentServiceI {

	/**
	 * 获取FdMemberAppointmentComment数据表格
	 * 
	 * @param fdMemberAppointmentComment
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(FdMemberAppointmentComment fdMemberAppointmentComment, PageHelper ph);

	/**
	 * 添加FdMemberAppointmentComment
	 * 
	 * @param fdMemberAppointmentComment
	 */
	public void add(FdMemberAppointmentComment fdMemberAppointmentComment);

	/**
	 * 获得FdMemberAppointmentComment对象
	 * 
	 * @param id
	 * @return
	 */
	public FdMemberAppointmentComment get(Integer id);

	/**
	 * 修改FdMemberAppointmentComment
	 * 
	 * @param fdMemberAppointmentComment
	 */
	public void edit(FdMemberAppointmentComment fdMemberAppointmentComment);

	/**
	 * 删除FdMemberAppointmentComment
	 * 
	 * @param id
	 */
	public void delete(Integer id);

	List<FdMemberAppointmentComment> query(FdMemberAppointmentComment comment);

	Object dataGridComplex(FdMemberAppointmentComment comment, PageHelper ph);
}
