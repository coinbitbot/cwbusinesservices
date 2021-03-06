ALTER TABLE `cwbusinesservices`.`POST` ADD COLUMN `POST_TEXT` TEXT NOT NULL;

INSERT INTO `PERMISSIONS` (`NAME`) VALUES ('CREATE_BLOG_CATEGORY');
INSERT INTO `PERMISSIONS` (`NAME`) VALUES ('EDIT_BLOG_CATEGORY');
INSERT INTO `PERMISSIONS` (`NAME`) VALUES ('DELETE_BLOG_CATEGORY');

insert into `ROLE_PERMISSIONS` (`ROLE_ID`, `PERMISSION_ID`) values ('3', '45');
insert into `ROLE_PERMISSIONS` (`ROLE_ID`, `PERMISSION_ID`) values ('3', '46');
insert into `ROLE_PERMISSIONS` (`ROLE_ID`, `PERMISSION_ID`) values ('3', '47');

insert into `ROLE_PERMISSIONS` (`ROLE_ID`, `PERMISSION_ID`) values ('2', '45');
insert into `ROLE_PERMISSIONS` (`ROLE_ID`, `PERMISSION_ID`) values ('2', '46');


INSERT INTO `PERMISSIONS` (`NAME`) VALUES ('CREATE_POST');
INSERT INTO `PERMISSIONS` (`NAME`) VALUES ('EDIT_POST');
INSERT INTO `PERMISSIONS` (`NAME`) VALUES ('DELETE_POST');

insert into `ROLE_PERMISSIONS` (`ROLE_ID`, `PERMISSION_ID`) values ('3', '48');
insert into `ROLE_PERMISSIONS` (`ROLE_ID`, `PERMISSION_ID`) values ('3', '49');
insert into `ROLE_PERMISSIONS` (`ROLE_ID`, `PERMISSION_ID`) values ('3', '50');

insert into `ROLE_PERMISSIONS` (`ROLE_ID`, `PERMISSION_ID`) values ('2', '48');
insert into `ROLE_PERMISSIONS` (`ROLE_ID`, `PERMISSION_ID`) values ('2', '49');
