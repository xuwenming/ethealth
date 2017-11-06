package com.ethealth.front.controller;

import com.mobian.absx.F;
import com.mobian.concurrent.CacheKey;
import com.mobian.concurrent.CompletionService;
import com.mobian.concurrent.Task;
import com.mobian.controller.BaseController;
import com.mobian.listener.Application;
import com.mobian.pageModel.*;
import com.mobian.service.FdMedicinePracticeServiceI;
import com.mobian.service.FdPictureServiceI;
import com.mobian.service.impl.CompletionFactory;
import com.mobian.util.PathUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 医学前沿接口
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/api/medicinePractice")
public class ApiMedicinePracticeController extends BaseController {

	@Autowired
	private FdMedicinePracticeServiceI fdMedicinePracticeService;

	@Autowired
	private FdPictureServiceI fdPictureService;

	/**
	 * 获取医学前沿列表接口
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public Json dataGrid(FdMedicinePractice medicinePractice, PageHelper ph) {
		Json j = new Json();
		try{
			if(ph.getRows() == 0 || ph.getRows() > 50) {
				ph.setRows(10);
			}
			if(F.empty(ph.getSort())) {
				ph.setSort("createTime");
			}
			if(F.empty(ph.getOrder())) {
				ph.setOrder("desc");
			}

			if(F.empty(medicinePractice.getIsUp())) medicinePractice.setIsUp(1); // 上架
			DataGrid dg = fdMedicinePracticeService.dataGrid(medicinePractice, ph);
			List<FdMedicinePractice> ol = dg.getRows();
			if(CollectionUtils.isNotEmpty(ol)) {
				CompletionService completionService = CompletionFactory.initCompletion();
				for (FdMedicinePractice o : ol) {
					o.setContent(null); // 内容不返回，返回url
					if(!F.empty(o.getPic()))
						completionService.submit(new Task<FdMedicinePractice, String>(new CacheKey("fdPic", o.getPic()), o) {
							@Override
							public String call() throws Exception {
								FdPicture pic = fdPictureService.get(Integer.valueOf(getD().getPic()));
								if(pic != null) return PathUtil.getPicPath(pic.getPath());
								return null;
							}

							protected void set(FdMedicinePractice d, String v) {
								if(!F.empty(v)) {
									d.setPicUrl(v);
								}
							}
						});
				}
				completionService.sync();
			}
			j.setSuccess(true);
			j.setMsg("获取医学前沿列表成功！");
			j.setObj(dg);
		}catch(Exception e){
			j.setMsg(Application.getString(EX_0001));
			logger.error("获取医学前沿列表接口异常", e);
		}

		return j;
	}

}
