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