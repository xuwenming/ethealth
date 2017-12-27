package com.mobian.service;

import com.mobian.pageModel.FdMemberDoctorSh;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface FdMemberDoctorShServiceI {

	/**
	 * 获取FdMemberDoctorSh数据表格
	 * 
	 * @param fdMemberDoctorSh
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(FdMemberDoctorSh fdMemberDoctorSh, PageHelper ph);

	/**
	 * 添加FdMemberDoctorSh
	 * 
	 * @param fdMemberDoctorSh
	 */
	public void add(FdMemberDoctorSh fdMemberDoctorSh);

	/**
	 * 获得FdMemberDoctorSh对象
	 * 
	 * @param id
	 * @return
	 */
	public FdMemberDoctorSh get(Integer id);

	public FdMemberDoctorSh get(Integer id, Integer auditType);

	/**
	 * 修改FdMemberDoctorSh
	 * 
	 * @param fdMemberDoctorSh
	 */
	public void edit(FdMemberDoctorSh fdMemberDoctorSh);

	/**
	 * 删除FdMemberDoctorSh
	 * 
	 * @param id
	 */
	public void delete(Integer id);

	void addOrUpdateMemberDoctorSh(FdMemberDoctorSh sh);

	void editAudit(FdMemberDoctorSh fdMemberDoctorSh);
}
