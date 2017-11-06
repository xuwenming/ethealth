package com.mobian.service;

import com.mobian.pageModel.FdMedicineScience;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface FdMedicineScienceServiceI {

	/**
	 * 获取FdMedicineScience数据表格
	 * 
	 * @param fdMedicineScience
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(FdMedicineScience fdMedicineScience, PageHelper ph);

	/**
	 * 添加FdMedicineScience
	 * 
	 * @param fdMedicineScience
	 */
	public void add(FdMedicineScience fdMedicineScience);

	/**
	 * 获得FdMedicineScience对象
	 * 
	 * @param id
	 * @return
	 */
	public FdMedicineScience get(Integer id);

	/**
	 * 修改FdMedicineScience
	 * 
	 * @param fdMedicineScience
	 */
	public void edit(FdMedicineScience fdMedicineScience);

	/**
	 * 删除FdMedicineScience
	 * 
	 * @param id
	 */
	public void delete(Integer id);

}
