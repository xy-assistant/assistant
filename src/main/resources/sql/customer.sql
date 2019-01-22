-- 邮件信息表
create table qrtz_task_mail_info (
	id int auto_increment primary key,
    `subject` varchar(255),
    `from` varchar(128),
    `message` varchar(512),
    copyList varchar(256),
    sendList varchar(256),
    nickName varchar(128),
    createDate date,
    modifyDate date
);
-- sql信息表
create table qrtz_task_sql_info (
	id int auto_increment primary key,
    `sqlText` nvarchar(512),
    createDate date,
    modifyDate date
);

select * from qrtz_task_mail_info;
select * from qrtz_task_sql_info;