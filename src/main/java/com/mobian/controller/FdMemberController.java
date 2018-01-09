package com.mobian.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mobian.absx.F;
import com.mobian.concurrent.CacheKey;
import com.mobian.concurrent.CompletionService;
import com.mobian.concurrent.Task;
import com.mobian.pageModel.*;
import com.mobian.service.FdCustomerServiceI;
import com.mobian.service.FdMemberServiceI;

import com.mobian.service.impl.CompletionFactory;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * FdMember管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/fdMemberController")
public class FdMemberController extends BaseController {

	@Autowired
	private FdMemberServiceI fdMemberService;

	@Autowired
	private FdCustomerServiceI fdCustomerService;


	/**
	 * 跳转到FdMember管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/fdmember/fdMember";
	}

	/**
	 * 获取FdMember数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(FdMember fdMember, PageHelper ph) {
		return fdMemberService.dataGridComplex(fdMember, ph);
	}
	/**
	 * 获取FdMember数据表格excel
	 * 
	 * @param user
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws IOException 
	 */
	@RequestMapping("/download")
	public void download(FdMember fdMember, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(fdMember,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加FdMember页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		FdMember fdMember = new FdMember();
		return "/fdmember/fdMemberAdd";
	}

	/**
	 * 添加FdMember
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(FdMember fdMember) {
		Json j = new Json();		
		fdMemberService.add(fdMember);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到FdMember查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, Integer id) {
		FdMember fdMember = fdMemberService.getDetail(id);
		request.setAttribute("fdMember", fdMember);
		return "/fdmember/fdMemberView";
	}

	/**
	 * 跳转到FdMember修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Integer id) {
		FdMember fdMember = fdMemberService.get(id);
		request.setAttribute("fdMember", fdMember);
		return "/fdmember/fdMemberEdit";
	}

	/**
	 * 修改FdMember
	 * 
	 * @param fdMember
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(FdMember fdMember) {
		Json j = new Json();		
		fdMemberService.edit(fdMember);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除FdMember
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(Integer id) {
		Json j = new Json();
		fdMemberService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

	/**
	 * 根据userName查询mbUser返回List Tree
	 * @param q
	 * @return
	 */
	@RequestMapping("/selectQuery")
	@ResponseBody
	public List<Tree> selectQuery(String q, HttpServletRequest request){
		String paramsJson = request.getParameter("params");
		FdMember member = new FdMember();
		List<Tree> lt =new ArrayList<Tree>();
		if(!F.empty(q)){
			if(!F.empty(paramsJson)) {
				member = JSON.parseObject(paramsJson, FdMember.class);
			}
			member.setQ(q);
		}else {
			return lt;
		}
		PageHelper ph = new PageHelper();
		ph.setHiddenTotal(true);
		ph.setPage(100);
		DataGrid dg = fdMemberService.dataGrid(member,ph);
		List<FdMember> rows = dg.getRows();
		if(!CollectionUtils.isEmpty(rows)){
			CompletionService completionService = CompletionFactory.initCompletion();
			for(FdMember d : rows){
				Tree tree = new Tree();
				tree.setId(d.getId()+"");
				tree.setText(d.getMobile());
//				tree.setParentName(d.getMobile());
				lt.add(tree);
				completionService.submit(new Task<Tree, String>(new CacheKey("fdCustomer", tree.getId()), tree) {
					@Override
					public String call() throws Exception {
						FdCustomer customer = fdCustomerService.get(Long.valueOf(getD().getId()));
						return customer == null ? null : customer.getRealName();
					}

					protected void set(Tree d, String v) {
						if(v != null) {
							d.setParentName(v);
						}
					}
				});
			}
			completionService.sync();
		}
		return lt;
	}

}
