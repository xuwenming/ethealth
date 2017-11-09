package com.ethealth.front.controller;

import com.mobian.controller.BaseController;
import com.mobian.pageModel.BaseData;
import com.mobian.pageModel.DiveRegion;
import com.mobian.pageModel.Json;
import com.mobian.service.BasedataServiceI;
import com.mobian.service.DiveRegionServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 基础数据
 *
 * @author John
 *
 */
@Controller
@RequestMapping("/api/baseData")
public class ApiBaseDataController extends BaseController {

	@Autowired
	private BasedataServiceI basedataService;

	@Autowired
	private DiveRegionServiceI diveRegionService;

	/**
	 * 获取基础数据
	 *
	 * @return
	 */
	@RequestMapping("/basedata")
	@ResponseBody
	public Json basedata(String dataType,String pid) {
		Json j = new Json();
		try{
			BaseData baseData = new BaseData();
			baseData.setBasetypeCode(dataType);
			baseData.setPid(pid);
			j.setObj(basedataService.getBaseDatas(baseData));
			j.success();
		}catch(Exception e){
			j.fail();
			e.printStackTrace();
		}
		return j;
	}

	/**
	 * 获取行政区域列表
	 *
	 * @return
	 */
	@RequestMapping("/region")
	@ResponseBody
	public Json region(DiveRegion diveRegion) {
		Json j = new Json();
		try{
			j.setObj(diveRegionService.findAllByParams(diveRegion));
			j.success();
		}catch(Exception e){
			j.fail();
			e.printStackTrace();
		}
		return j;
	}

}