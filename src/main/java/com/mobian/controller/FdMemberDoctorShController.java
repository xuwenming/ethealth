package com.mobian.controller;

import com.alibaba.fastjson.JSON;
import com.mobian.absx.F;
import com.mobian.concurrent.CacheKey;
import com.mobian.concurrent.CompletionService;
import com.mobian.concurrent.Task;
import com.mobian.pageModel.*;
import com.mobian.service.*;
import com.mobian.service.impl.CompletionFactory;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * FdMemberDoctorSh管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/fdMemberDoctorShController")
public class FdMemberDoctorShController extends BaseController {

	@Autowired
	private FdMemberDoctorShServiceI fdMemberDoctorShService;

	@Autowired
	private FdMemberServiceI fdMemberService;

	@Autowired
	private FdMemberDoctorLevelServiceI fdMemberDoctorLevelService;

	@Autowired
	private FdHospitalServiceI fdHospitalService;

	@Autowired
	private FdHospitalDeptServiceI fdHospitalDeptService;


	/**
	 * 跳转到FdMemberDoctorSh管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/fdmemberdoctorsh/fdMemberDoctorSh";
	}

	/**
	 * 获取FdMemberDoctorSh数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(FdMemberDoctorSh fdMemberDoctorSh, PageHelper ph) {
		DataGrid dg =  fdMemberDoctorShService.dataGrid(fdMemberDoctorSh, ph);
		List<FdMemberDoctorSh> list = dg.getRows();
		if(CollectionUtils.isNotEmpty(list)) {
			CompletionService completionService = CompletionFactory.initCompletion();
			for(FdMemberDoctorSh sh : list) {
				completionService.submit(new Task<FdMemberDoctorSh, String>(new CacheKey("fdMember", sh.getId() + ""), sh) {
					@Override
					public String call() throws Exception {
						FdMember member = fdMemberService.get(getD().getId());
						return member.getMobile();
					}

					protected void set(FdMemberDoctorSh d, String v) {
						if(!F.empty(v)) {
							d.setMobile(v);
						}
					}
				});

				if(!F.empty(sh.getLevel()))
					completionService.submit(new Task<FdMemberDoctorSh, String>(new CacheKey("fdMemberDoctorLevel", sh.getLevel() + ""), sh) {
						@Override
						public String call() throws Exception {
							FdMemberDoctorLevel level = fdMemberDoctorLevelService.get(getD().getLevel());
							return level.getName();
						}

						protected void set(FdMemberDoctorSh d, String v) {
							if(!F.empty(v)) {
								d.setLevelName(v);
							}
						}
					});

				if(!F.empty(sh.getHospital()))
					completionService.submit(new Task<FdMemberDoctorSh, String>(new CacheKey("fdHospital", sh.getHospital() + ""), sh) {
						@Override
						public String call() throws Exception {
							FdHospital hospital = fdHospitalService.get(getD().getHospital());
							return hospital.getHospitalName();
						}

						protected void set(FdMemberDoctorSh d, String v) {
							if(!F.empty(v)) {
								d.setHospitalName(v);
							}
						}
					});
				if(!F.empty(sh.getDepartment()))
					completionService.submit(new Task<FdMemberDoctorSh, String>(new CacheKey("fdHospitalDept", sh.getDepartment() + ""), sh) {
						@Override
						public String call() throws Exception {
							FdHospitalDept dept = fdHospitalDeptService.get(getD().getHospital());
							return dept.getName();
						}

						protected void set(FdMemberDoctorSh d, String v) {
							if(!F.empty(v)) {
								d.setDepartmentName(v);
							}
						}
					});
			}
			completionService.sync();
		}

		return dg;
	}
	/**
	 * 获取FdMemberDoctorSh数据表格excel
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
	public void download(FdMemberDoctorSh fdMemberDoctorSh, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(fdMemberDoctorSh,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加FdMemberDoctorSh页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		FdMemberDoctorSh fdMemberDoctorSh = new FdMemberDoctorSh();
		return "/fdmemberdoctorsh/fdMemberDoctorShAdd";
	}

	/**
	 * 添加FdMemberDoctorSh
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(FdMemberDoctorSh fdMemberDoctorSh) {
		Json j = new Json();		
		fdMemberDoctorShService.add(fdMemberDoctorSh);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到FdMemberDoctorSh查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, Integer id) {
		FdMemberDoctorSh fdMemberDoctorSh = fdMemberDoctorShService.get(id);
		request.setAttribute("fdMemberDoctorSh", fdMemberDoctorSh);
		return "/fdmemberdoctorsh/fdMemberDoctorShView";
	}

	/**
	 * 跳转到FdMemberDoctorSh修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Integer id, Integer auditType) {
		FdMemberDoctorSh fdMemberDoctorSh = fdMemberDoctorShService.get(id, auditType);
		FdMember member = fdMemberService.get(id);
		fdMemberDoctorSh.setMobile(member.getUsername());
		if(!F.empty(fdMemberDoctorSh.getLevel())) {
			FdMemberDoctorLevel level = fdMemberDoctorLevelService.get(fdMemberDoctorSh.getLevel());
			if(level != null) fdMemberDoctorSh.setLevelName(level.getName());
		}
		if(!F.empty(fdMemberDoctorSh.getHospital())) {
			FdHospital hospital = fdHospitalService.get(fdMemberDoctorSh.getHospital());
			fdMemberDoctorSh.setHospitalName(hospital.getHospitalName());
		}

		if(!F.empty(fdMemberDoctorSh.getDepartment())) {
			FdHospitalDept dept = fdHospitalDeptService.get(fdMemberDoctorSh.getDepartment());
			fdMemberDoctorSh.setDepartmentName(dept.getName());
		}

		request.setAttribute("fdMemberDoctorSh", fdMemberDoctorSh);
		return "/fdmemberdoctorsh/fdMemberDoctorShEdit";
	}

	/**
	 * 修改FdMemberDoctorSh
	 * 
	 * @param fdMemberDoctorSh
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(FdMemberDoctorSh fdMemberDoctorSh) {
		Json j = new Json();		
		fdMemberDoctorShService.editAudit(fdMemberDoctorSh);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除FdMemberDoctorSh
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(Integer id) {
		Json j = new Json();
		fdMemberDoctorShService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
