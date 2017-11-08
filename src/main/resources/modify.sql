ALTER TABLE `fd_hospital_dept`
  ADD COLUMN `icon`  varchar(255) NULL COMMENT '小图标' AFTER `name`,
  ADD COLUMN `pid`  int(10) NULL COMMENT '父id' AFTER `icon`;