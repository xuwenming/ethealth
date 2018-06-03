package com.mobian.service;

import com.mobian.pageModel.FdWithdrawLog;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.PageHelper;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 
 * @author John
 * 
 */
public interface FdWithdrawLogServiceI {

	/**
	 * 获取FdWithdrawLog数据表格
	 * 
	 * @param fdWithdrawLog
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(FdWithdrawLog fdWithdrawLog, PageHelper ph);

	/**
	 * 添加FdWithdrawLog
	 * 
	 * @param fdWithdrawLog
	 */
	public void add(FdWithdrawLog fdWithdrawLog);

	/**
	 * 获得FdWithdrawLog对象
	 * 
	 * @param id
	 * @return
	 */
	public FdWithdrawLog get(Integer id);

	/**
	 * 修改FdWithdrawLog
	 * 
	 * @param fdWithdrawLog
	 */
	public void edit(FdWithdrawLog fdWithdrawLog);

	/**
	 * 删除FdWithdrawLog
	 * 
	 * @param id
	 */
	public void delete(Integer id);

	List<FdWithdrawLog> query(FdWithdrawLog log);

	FdWithdrawLog get(FdWithdrawLog log);

	void editAudit(FdWithdrawLog fdWithdrawLog, String loginId, HttpServletRequest request);

	void addAndBalance(FdWithdrawLog withdrawLog);
}
