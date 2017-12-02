ALTER TABLE `fd_hospital_dept`
  ADD COLUMN `icon`  varchar(255) NULL COMMENT '小图标' AFTER `name`;
ALTER TABLE `fd_hospital_dept`
  ADD COLUMN `pid`  int(10) NULL COMMENT '父id' AFTER `icon`;

ALTER TABLE `fd_doctor_opinion`
  ADD COLUMN `file_create_time`  bigint(13) NULL COMMENT '附件创建时间' AFTER `file`;
ALTER TABLE `fd_doctor_opinion`
  ADD COLUMN `file_to_imgs`  text NULL COMMENT '附件转图片地址集合' AFTER `file_create_time`;

ALTER TABLE `fd_member`
  ADD COLUMN `hx_password`  varchar(36) NULL COMMENT '环信登录密码' AFTER `vip_end_time`;
ALTER TABLE `fd_member`
  ADD COLUMN `hx_status`  tinyint(1) NULL DEFAULT 0 COMMENT '环信是否注册，1：已注册；0：未注册' AFTER `hx_password`;
ALTER TABLE `fd_member`
  ADD COLUMN `head_image`  varchar(256) NULL COMMENT '用户头像（为空时再取pic）' AFTER `pic`;

ALTER TABLE `fd_member_appointment`
  ADD COLUMN `sourse`  varchar(6) NULL DEFAULT 'AS01' COMMENT '来源{AS}' AFTER `file`;
ALTER TABLE `fd_member_appointment`
  ADD COLUMN `appointment_no`  varchar(64) NULL COMMENT '预约订单号' AFTER `sourse`;
ALTER TABLE `fd_member_appointment`
  ADD COLUMN `amount`  bigint NULL COMMENT '订单金额' AFTER `appointment_no`;

ALTER TABLE `fd_member_doctor_sh`
  ADD COLUMN `hospital_name`  varchar(100) NULL COMMENT '医院名称',
  ADD COLUMN `department_name`  varchar(100) NULL COMMENT '科室名称';

ALTER TABLE `fd_member`
  DROP INDEX `username` ,
  ADD UNIQUE INDEX `username` (`username`, `is_admin`) USING BTREE ;

CREATE TABLE `fd_feedback` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `contact_way` varchar(50) DEFAULT NULL COMMENT '联系方式',
  `content` varchar(1000) DEFAULT NULL COMMENT '正文',
  `create_by` int(10) DEFAULT NULL COMMENT '创建人',
  `create_time` bigint(13) DEFAULT NULL COMMENT '创建时间',
  `update_by` int(10) DEFAULT NULL COMMENT '修改人',
  `update_time` bigint(13) DEFAULT NULL COMMENT '修改时间',
  `status` varchar(2) DEFAULT '0' COMMENT '是否删除 1 是 0 否',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='意见反馈信息表';

CREATE TABLE `fd_member_consultation_order` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(10) DEFAULT NULL COMMENT '病人id',
  `doctor_id` int(10) DEFAULT NULL COMMENT '医生id',
	`order_no` varchar(50) DEFAULT NULL COMMENT '订单号',
	`amount` bigint(20) DEFAULT NULL COMMENT '订单金额',
  `create_by` int(10) DEFAULT NULL COMMENT '创建人',
  `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `update_by` int(11) DEFAULT NULL COMMENT '修改人',
  `update_time` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `status` varchar(2) DEFAULT '0' COMMENT '是否删除 1 是 0 否',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='咨询订单表';

CREATE TABLE `fd_member_consultation_expire` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(10) DEFAULT NULL COMMENT '病人id',
  `doctor_id` int(10) DEFAULT NULL COMMENT '医生id',
  `create_by` int(10) DEFAULT NULL COMMENT '创建人',
  `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `update_by` int(11) DEFAULT NULL COMMENT '修改人',
  `update_time` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `status` varchar(2) DEFAULT '0' COMMENT '是否删除 1 是 0 否',
  `expire_date` datetime DEFAULT NULL COMMENT '到期时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='咨询有效期表';

CREATE TABLE `fd_balance_log` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `admin_id` bigint(11) DEFAULT '0',
  `user_id` bigint(11) NOT NULL COMMENT '用户id',
  `balance_no` varchar(64) DEFAULT NULL COMMENT '余额流水号',
  `ref_id` varchar(64) DEFAULT NULL COMMENT '业务id',
  `ref_type` varchar(10) NOT NULL COMMENT '业务类型',
  `create_time` bigint(13) DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(13) DEFAULT NULL COMMENT '更新时间',
  `amount` float(10,2) NOT NULL COMMENT '金额',
  `amount_log` float(10,2) NOT NULL COMMENT '钱包期末余额',
  `note` text COMMENT '备注',
  `status` tinyint(1) DEFAULT '0' COMMENT '是否删除1:是 0:否',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='余额流水表';

CREATE TABLE `fd_member_appointment_comment` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `doctor_id` int(10) DEFAULT NULL COMMENT '医生id',
  `appointment_id` int(10) DEFAULT NULL COMMENT '预约订单id',
  `user_id` int(10) DEFAULT NULL COMMENT '评论人',
  `create_time` bigint(20) DEFAULT NULL COMMENT '评论时间',
  `update_time` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `status` varchar(2) DEFAULT '0' COMMENT '是否删除 1 是 0 否',
  `effect` float DEFAULT NULL COMMENT '治疗效果',
  `attitude` float DEFAULT NULL COMMENT '服务态度',
  `disease` varchar(100) DEFAULT NULL COMMENT '看什么病',
  `objective` varchar(6) DEFAULT NULL COMMENT '看病目的{OT}',
  `objective_other` varchar(100) DEFAULT NULL COMMENT '看病目的_其他',
  `therapy` varchar(6) DEFAULT NULL COMMENT '治疗方式{TW}',
  `therapy_other` varchar(100) DEFAULT NULL COMMENT '治疗方式_其他',
  `comment` varchar(300) DEFAULT NULL COMMENT '评价内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='预约评价表';


