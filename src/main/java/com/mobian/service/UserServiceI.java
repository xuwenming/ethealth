package com.mobian.service;

import java.util.List;
import java.util.Map;

import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.User;
import com.mobian.pageModel.PageHelper;
import com.mobian.pageModel.SessionInfo;

/**
 * 用户Service
 * 
 * @author John
 * 
 */
public interface UserServiceI {

	/**
	 * 用户登录
	 * 
	 * @param user
	 *            里面包含登录名和密码
	 * @return 用户对象
	 */
	public User login(User user);

	/**
	 * 用户注册
	 * 
	 * @param user
	 *            里面包含登录名和密码
	 * @throws Exception
	 */
	public void reg(User user) throws Exception;

	/**
	 * 获取用户数据表格
	 * 
	 * @param user
	 * @return
	 */
	public DataGrid dataGrid(User user, PageHelper ph);
	
	/**
	 * 感兴趣的
	 * @param user
	 * @param ph
	 * @return
	 */
	public DataGrid dataGridHobby(User user, PageHelper ph);
	
	/**
	 * api查询用的
	 * @param user
	 * @param ph
	 * @return
	 */
	public DataGrid dataGridForApi(User user, PageHelper ph);
	
	/**
	 * 新好友
	 * @param user
	 * @param ph
	 * @return
	 */
	public DataGrid dataGridNewFriend(User user, PageHelper ph);

	/**
	 * 添加用户
	 * 
	 * @param user
	 */
	public void add(User user) throws Exception;

	/**
	 * 获得用户对象
	 * 
	 * @param id
	 * @return
	 */
	User get(String id);

	User getIncludeRole(String id);

	/**
	 * 编辑用户
	 * 
	 * @param user
	 */
	public void edit(User user) throws Exception;

	/**
	 * 删除用户
	 * 
	 * @param id
	 */
	public void delete(String id);

	/**
	 * 用户授权
	 * 
	 * @param ids
	 * @param user
	 *            需要user.roleIds的属性值
	 */
	public void grant(String ids, User user);

	/**
	 * 获得用户能访问的资源地址
	 * 
	 * @param id
	 *            用户ID
	 * @return
	 */
	public List<String> resourceList(String id);

	/**
	 * 编辑用户密码
	 * 
	 * @param user
	 */
	public void editPwd(User user);

	/**
	 * 修改用户自己的密码
	 * 
	 * @param sessionInfo
	 * @param oldPwd
	 * @param pwd
	 * @return
	 */
	public boolean editCurrentUserPwd(SessionInfo sessionInfo, String oldPwd, String pwd);

	/**
	 * 用户登录时的autocomplete
	 * 
	 * @param q
	 *            参数
	 * @return
	 */
	public List<User> loginCombobox(String q);

	/**
	 * 用户登录时的combogrid
	 * 
	 * @param q
	 * @param ph
	 * @return
	 */
	public DataGrid loginCombogrid(String q, PageHelper ph);

	/**
	 * 用户创建时间图表
	 * 
	 * @return
	 */
	public List<Long> userCreateDatetimeChart();
		
	
	/**
	 * 我首页
	 * @param userId
	 * @return
	 */
	public Map<String,Object> userIndex(String userId);

	User getFromCache(String  id);

}
