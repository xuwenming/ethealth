//package com.mobian.service.impl;
//
//import com.mobian.absx.F;
//import com.mobian.dao.MbOrderDaoI;
//import com.mobian.model.TmbContract;
//import com.mobian.model.TmbContractItem;
//import com.mobian.model.TmbOrder;
//import com.mobian.model.TmbOrderItem;
//import com.mobian.pageModel.MbShop;
//import com.mobian.pageModel.User;
//import com.mobian.service.*;
//import com.mobian.thirdpart.mns.MNSTemplate;
//import com.mobian.thirdpart.mns.MNSUtil;
//import com.mobian.util.Constants;
//import com.mobian.util.DateUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.util.CollectionUtils;
//
//import java.util.*;
//
///**
// * Created by john on 16/10/16.
// */
//@Service
//public class TaskServiceImpl implements TaskServiceI {
//
//    @Autowired
//    private MbContractServiceI mbContractService;
//    @Autowired
//    private MbContractItemServiceI mbContractItemService;
//    @Autowired
//    private RedisUserServiceImpl redisUserService;
//    @Autowired
//    private MbOrderServiceI mbOrderService;
//    @Autowired
//    private MbOrderItemServiceI mbOrderItemService;
//    @Autowired
//    private MbOrderDaoI mbOrderDao;
//    @Autowired
//    private UserServiceI  userService;
//    @Autowired
//    private MbShopServiceI mbShopService;
//
//    @Override
//    public void setContractPrice() {
//        try {
//            List<TmbContract> l = mbContractService.queryAllMbContract();
//            if(l != null && l.size() > 0) {
//                for(TmbContract tmbContract : l) {
//                    List<TmbContractItem> lc = mbContractItemService.queryMbContractItemByShopId(tmbContract.getId());
//                    if(lc != null && lc.size() > 0) {
//                        for(TmbContractItem tmbContractItem : lc) {
//                            redisUserService.setContractPrice(tmbContract.getShopId(), tmbContractItem.getItemId(), tmbContractItem.getPrice());
//                        }
//                    }
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void setOrderQuantity() {
//        List<TmbOrder> l = mbOrderService.queryOrderListByStatus();
//        Map<String, Integer> map = new HashMap<String, Integer>();
//        if (l != null && l.size() > 0) {
//            for (TmbOrder tmbOrder : l) {
//                List<TmbOrderItem> itemList = mbOrderItemService.queryMbOrderItemByOrderId(tmbOrder.getId());
//                if (itemList != null && itemList.size() > 0) {
//                    for (TmbOrderItem tmbOrderItem : itemList) {
//                        if (tmbOrder.getDeliveryWarehouseId() == null) continue;
//                        String key = tmbOrder.getDeliveryWarehouseId() + ":" + tmbOrderItem.getItemId();
//                        Integer itemIdValue = map.get(key);
//                        if (itemIdValue == null) {
//                            map.put(key, tmbOrderItem.getQuantity());
//                        } else {
//                            map.put(key, itemIdValue += tmbOrderItem.getQuantity());
//                        }
//
//                        String keyStatus = tmbOrder.getDeliveryWarehouseId() + ":" + tmbOrderItem.getItemId() + ":" + tmbOrder.getStatus();
//                        Integer value = map.get(keyStatus);
//                        if (value == null) {
//                            map.put(keyStatus, tmbOrderItem.getQuantity());
//                        } else {
//                            map.put(keyStatus, value += tmbOrderItem.getQuantity());
//                        }
//                    }
//                }
//            }
//        }
//
//        for (String key : map.keySet()) {
//            Integer orderQuantity = map.get(key);
//            redisUserService.setOrderQuantity(key, orderQuantity);
//        }
//
//    }
//    @Override
//    public void deleteUnPayOrder(){
//       mbOrderService.deleteUnPayOrderByTime();
//    }
//
//    @Override
//    public void sendMessage() {
//
//    }
//
//    @Override
//    public void remindDrivers() {
//        String hql = " from TmbOrder t  where t.deliveryTime >=:beginDate and t.deliveryTime <=:endDate and t.status = 'OD20' and t.isdeleted = 0";
//        Calendar cal = Calendar.getInstance();
//        cal.add(Calendar.DATE, -2);
//        Date beginDate = cal.getTime();
//        cal.add(Calendar.MINUTE, 5);
//        Date endDate = cal.getTime();
//        Map<String, Object> conditons = new HashMap<String, Object>();
//        conditons.put("beginDate", beginDate);
//        conditons.put("endDate", endDate);
//        List<TmbOrder> list = mbOrderDao.find(hql, conditons);
//        if (!CollectionUtils.isEmpty(list)) {
//            for (TmbOrder t : list) {
//                User user = userService.getFromCache(t.getDeliveryDriver());
//                String phone = user.getPhone();
//                if (!F.empty(phone)) {
//                    MbShop mbShop = mbShopService.getFromCache(t.getShopId());
//                    String shopName = mbShop.getName();
//                    MNSTemplate template = new MNSTemplate();
//                    template.setTemplateCode("SMS_91795050");
//                    Map<String, String> params = new HashMap<String, String>();
//                    params.put("shopName", shopName);
//                    params.put("orderId", t.getId() + "");
//                    params.put("deliveryTime", DateUtil.format(t.getDeliveryTime(), Constants.DATE_FORMAT));
//                    template.setParams(params);
//                    MNSUtil.sendMns(phone, template);
//                }
//            }
//
//        }
//
//    }
//
//}
