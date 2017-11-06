package com.ethealth.front.controller;

import com.mobian.controller.BaseController;
import com.mobian.pageModel.BaseData;
import com.mobian.pageModel.DiveRegion;
import com.mobian.pageModel.Json;
import com.mobian.service.BasedataServiceI;
import com.mobian.service.DiveRegionServiceI;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 基础数据
 *
 * @author John
 *
 */
@Controller
@RequestMapping("/api/apiBaseDataController")
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
	 * 获取基础数据
	 *
	 * @return
	 */
	@RequestMapping("/goodsType")
	@ResponseBody
	public Json baseDataWithGoodsType() {
		Json j = new Json();
		try{
			BaseData baseData = new BaseData();
			baseData.setBasetypeCode("GN");
			List<BaseData> baseDataList = basedataService.getBaseDatas(baseData);
			if(!CollectionUtils.isEmpty(baseDataList)){
				Iterator<BaseData> iterator = baseDataList.iterator();
				while (iterator.hasNext()){
					BaseData bd = iterator.next();
					if(StringUtils.isNotEmpty(bd.getPid())){
						for (BaseData data : baseDataList) {
							if(data.getId().equals(bd.getPid())){
								List<BaseData> children = data.getChildren();
								if(children == null){
									children = new ArrayList<BaseData>();
									data.setChildren(children);
								}
								children.add(bd);
								iterator.remove();
								break;
							}
						}
					}
				}
			}
			j.setObj(baseDataList);
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