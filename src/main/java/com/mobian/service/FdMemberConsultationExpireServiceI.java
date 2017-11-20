package com.mobian.service;

import com.mobian.pageModel.FdMemberConsultation;
import com.mobian.pageModel.FdMemberConsultationExpire;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface FdMemberConsultationExpireServiceI {

	/**
	 * 获取FdMemberConsultationExpire数据表格
	 * 
	 * @param fdMemberConsultationExpire
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(FdMemberConsultationExpire fdMemberConsultationExpire, PageHelper ph);

	/**
	 * 添加FdMemberConsultationExpire
	 * 
	 * @param fdMemberConsultationExpire
	 */
	public void add(FdMemberConsultationExpire fdMemberConsultationExpire);

	/**
	 * 获得FdMemberConsultationExpire对象
	 * 
	 * @param id
	 * @return
	 */
	public FdMemberConsultationExpire get(Integer id);

	/**
	 * 修改FdMemberConsultationExpire
	 * 
	 * @param fdMemberConsultationExpire
	 */
	public void edit(FdMemberConsultationExpire fdMemberConsultationExpire);

	/**
	 * 删除FdMemberConsultationExpire
	 * 
	 * @param id
	 */
	public void delete(Integer id);

	FdMemberConsultationExpire getByUserIdAndDoctorId(Integer userId, Integer doctorId);
}
