package com.mobian.service;

import com.mobian.pageModel.FdMemberConsultationFriend;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface FdMemberConsultationFriendServiceI {

	/**
	 * 获取FdMemberConsultationFriend数据表格
	 * 
	 * @param fdMemberConsultationFriend
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(FdMemberConsultationFriend fdMemberConsultationFriend, PageHelper ph);

	/**
	 * 添加FdMemberConsultationFriend
	 * 
	 * @param fdMemberConsultationFriend
	 */
	public void add(FdMemberConsultationFriend fdMemberConsultationFriend);

	/**
	 * 获得FdMemberConsultationFriend对象
	 * 
	 * @param id
	 * @return
	 */
	public FdMemberConsultationFriend get(Integer id);

	/**
	 * 修改FdMemberConsultationFriend
	 * 
	 * @param fdMemberConsultationFriend
	 */
	public void edit(FdMemberConsultationFriend fdMemberConsultationFriend);

	/**
	 * 删除FdMemberConsultationFriend
	 * 
	 * @param id
	 */
	public void delete(Integer id);

	FdMemberConsultationFriend getByUserIdAndDoctorId(Integer userId, Integer doctorId);

	DataGrid dataGridComplex(FdMemberConsultationFriend consultationFriend, PageHelper ph);
}
