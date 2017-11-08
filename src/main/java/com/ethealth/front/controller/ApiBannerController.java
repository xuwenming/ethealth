package com.ethealth.front.controller;

import com.mobian.absx.F;
import com.mobian.concurrent.CacheKey;
import com.mobian.concurrent.CompletionService;
import com.mobian.concurrent.Task;
import com.mobian.controller.BaseController;
import com.mobian.listener.Application;
import com.mobian.pageModel.FdBanner;
import com.mobian.pageModel.FdPicture;
import com.mobian.pageModel.Json;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdBannerServiceI;
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
 * 轮播图接口
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/api/banner")
public class ApiBannerController extends BaseController {

	@Autowired
	private FdBannerServiceI fdBannerService;

	@Autowired
	private FdPictureServiceI fdPictureService;

	/**
	 * 获取banner列表接口
	 */
	@RequestMapping("/list")
	@ResponseBody
	public Json list(FdBanner banner, PageHelper ph) {
		Json j = new Json();
		try{
			if(F.empty(ph.getSort())) {
				ph.setSort("num");
			}
			if(F.empty(ph.getOrder())) {
				ph.setOrder("asc");
			}
			ph.setHiddenTotal(true);
			List<FdBanner> banners = fdBannerService.dataGrid(banner, ph).getRows();

			if(CollectionUtils.isNotEmpty(banners)) {
				CompletionService completionService = CompletionFactory.initCompletion();
				for (FdBanner o : banners) {
					if(!F.empty(o.getPic()))
						completionService.submit(new Task<FdBanner, String>(new CacheKey("fdPic", o.getPic() + ""), o) {
							@Override
							public String call() throws Exception {
								FdPicture pic = fdPictureService.get(Integer.valueOf(getD().getPic()));
								if(pic != null) return PathUtil.getPicPath(pic.getPath());
								return null;
							}

							protected void set(FdBanner d, String v) {
								if(!F.empty(v)) {
									d.setPicUrl(v);
								}
							}
						});
				}
				completionService.sync();
			}
			j.setSuccess(true);
			j.setMsg("获取banner列表成功！");
			j.setObj(banners);
		}catch(Exception e){
			j.setMsg(Application.getString(EX_0001));
			logger.error("获取banner列表接口异常", e);
		}

		return j;
	}

}
