package com.mobian.service;

import com.mobian.pageModel.FdMember;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.PageHelper;

import java.util.List;

/**
 * 
 * @author John
 * 
 */
public interface FdMemberServiceI {

	/**
	 * 获取FdMember数据表格
	 * 
	 * @param fdMember
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(FdMember fdMember, PageHelper ph);

	/**
	 * 添加FdMember
	 * 
	 * @param fdMember
	 */
	public void add(FdMember fdMember);

	/**
	 * 获得FdMember对象
	 * 
	 * @param id
	 * @return
	 */
	public FdMember get(Integer id);

	/**
	 * 修改FdMember
	 * 
	 * @param fdMember
	 */
	public void edit(FdMember fdMember);

	/**
	 * 删除FdMember
	 * 
	 * @param id
	 */
	public void delete(Integer id);

	List<FdMember> queryMembersByGroupId(Integer groupId);

	FdMember get(FdMember fdMember);

	boolean checkUsername(String username, Integer isAdmin);

	void editMember(FdMember member);

	void addMember(FdMember member);

	FdMember getDetail(Integer id);

	FdMember getSimple(Integer id);

	List<FdMember> getByMobiles(String mobiles);

	DataGrid dataGridComplex(FdMember fdMember, PageHelper ph);

	List<FdMember> queryAllByDelHxAccount();
}
