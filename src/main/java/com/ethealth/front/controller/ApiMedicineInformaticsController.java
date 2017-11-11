package com.ethealth.front.controller;

import com.mobian.absx.F;
import com.mobian.controller.BaseController;
import com.mobian.listener.Application;
import com.mobian.pageModel.*;
import com.mobian.service.FdDoctorOpinionServiceI;
import com.mobian.service.FdFileServiceI;
import com.mobian.service.FdMedicinePracticeServiceI;
import com.mobian.service.FdMedicineScienceServiceI;
import com.mobian.util.ICEPdfUtil;
import com.mobian.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 医学资讯接口
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/api/medicineInformatics")
public class ApiMedicineInformaticsController extends BaseController {

	@Autowired
	private FdMedicinePracticeServiceI fdMedicinePracticeService;

	@Autowired
	private FdMedicineScienceServiceI fdMedicineScienceService;

	@Autowired
	private FdDoctorOpinionServiceI fdDoctorOpinionService;

	@Autowired
	private FdFileServiceI fdFileService;

	/**
	 * 获取医学资讯接口
	 */
	@RequestMapping("/dataList")
	@ResponseBody
	public Json dataList(String key) {
		Json j = new Json();
		try{
			Map<String, Object> obj = new HashMap<String, Object>();

			// 医学前沿
			FdMedicinePractice medicinePractice = new FdMedicinePractice();
			medicinePractice.setKey(key);
			PageHelper ph = new PageHelper();
			ph.setPage(1);
			ph.setRows(2);
			ph.setHiddenTotal(true);
			List<FdMedicinePractice> medicinePractices = fdMedicinePracticeService.dataGridComplex(medicinePractice, ph).getRows();
			obj.put("medicinePractices", medicinePractices);

			// 医学科普
			FdMedicineScience medicineScience = new FdMedicineScience();
			medicineScience.setKey(key);
			ph = new PageHelper();
			ph.setPage(1);
			ph.setRows(2);
			ph.setHiddenTotal(true);
			List<FdMedicineScience> medicineSciences = fdMedicineScienceService.dataGridComplex(medicineScience, ph).getRows();
			obj.put("medicineSciences", medicineSciences);

			// 专家笔谈
			FdDoctorOpinion doctorOpinion = new FdDoctorOpinion();
			doctorOpinion.setKey(key);
			ph = new PageHelper();
			ph.setPage(1);
			ph.setRows(2);
			ph.setHiddenTotal(true);
			List<FdDoctorOpinion> doctorOpinions = fdDoctorOpinionService.dataGridComplex(doctorOpinion, ph).getRows();
			obj.put("doctorOpinions", doctorOpinions);

			j.setSuccess(true);
			j.setMsg("获取医学资讯成功！");
			j.setObj(obj);
		}catch(Exception e){
			j.setMsg(Application.getString(EX_0001));
			logger.error("获取医学资讯接口异常", e);
		}

		return j;
	}

	/**
	 * 获取医学前沿列表接口
	 */
	@RequestMapping("/medicinePracticeDataGrid")
	@ResponseBody
	public Json medicinePracticeDataGrid(FdMedicinePractice medicinePractice, PageHelper ph) {
		Json j = new Json();
		try{
			j.setObj(fdMedicinePracticeService.dataGridComplex(medicinePractice, ph));
			j.setSuccess(true);
			j.setMsg("获取医学前沿列表成功！");

		}catch(Exception e){
			j.setMsg(Application.getString(EX_0001));
			logger.error("获取医学前沿列表接口异常", e);
		}

		return j;
	}

	/**
	 * 获取医学科普列表接口
	 */
	@RequestMapping("/medicineScienceDataGrid")
	@ResponseBody
	public Json medicineScienceDataGrid(FdMedicineScience medicineScience, PageHelper ph) {
		Json j = new Json();
		try{
			j.setObj(fdMedicineScienceService.dataGridComplex(medicineScience, ph));
			j.setSuccess(true);
			j.setMsg("获取医学科普列表成功！");

		}catch(Exception e){
			j.setMsg(Application.getString(EX_0001));
			logger.error("获取医学科普列表接口异常", e);
		}

		return j;
	}

	/**
	 * 获取专家笔谈列表接口
	 */
	@RequestMapping("/doctorOpinionDataGrid")
	@ResponseBody
	public Json doctorOpinionDataGrid(FdDoctorOpinion doctorOpinion, PageHelper ph) {
		Json j = new Json();
		try{
			j.setObj(fdDoctorOpinionService.dataGridComplex(doctorOpinion, ph));
			j.setSuccess(true);
			j.setMsg("获取专家笔谈列表成功！");

		}catch(Exception e){
			j.setMsg(Application.getString(EX_0001));
			logger.error("获取专家笔谈列表接口异常", e);
		}

		return j;
	}

	/**
	 * 获取专家笔谈详情接口
	 */
	@RequestMapping("/getDoctorOpinionDetail")
	@ResponseBody
	public Json getDoctorOpinionDetail(Integer id) {
		Json j = new Json();
		try{
			FdDoctorOpinion doctorOpinion = fdDoctorOpinionService.getDetail(id);

			if(!F.empty(doctorOpinion.getFile())) {
				FdFile file = fdFileService.get(doctorOpinion.getFile());
				if(F.empty(doctorOpinion.getFileCreateTime())
						|| (file != null && file.getCreateTime().longValue() != doctorOpinion.getFileCreateTime().longValue())) {
					String fileToImgs = ICEPdfUtil.pdfToImg(PathUtil.getFilePath(file.getSavepath() + file.getSavename()), "doctorOpinion");

					doctorOpinion.setFileToImgs(fileToImgs);
					doctorOpinion.setFileCreateTime(file.getCreateTime());
					fdDoctorOpinionService.edit(doctorOpinion);
				}
			}
			j.setObj(doctorOpinion);
			j.setSuccess(true);
			j.setMsg("获取专家笔谈详情成功！");

		}catch(Exception e){
			j.setMsg(Application.getString(EX_0001));
			logger.error("获取专家笔谈详情接口异常", e);
		}

		return j;
	}

}
