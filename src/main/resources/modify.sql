ALTER TABLE `fd_hospital_dept`
  ADD COLUMN `icon`  varchar(255) NULL COMMENT '小图标' AFTER `name`,
  ADD COLUMN `pid`  int(10) NULL COMMENT '父id' AFTER `icon`;

ALTER TABLE `fd_doctor_opinion`
  ADD COLUMN `file_create_time`  bigint(13) NULL COMMENT '附件创建时间' AFTER `file`,
  ADD COLUMN `file_to_imgs`  text NULL COMMENT '附件转图片地址集合' AFTER `file_create_time`;