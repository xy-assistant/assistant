-- MYSQL
CREATE TABLE `qrtz_task_mail_info` (
  `id` int(11) NOT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `fromAddress` varchar(128) DEFAULT NULL,
  `message` varchar(512) DEFAULT NULL,
  `copyList` varchar(256) DEFAULT NULL,
  `sendList` varchar(256) DEFAULT NULL,
  `nickName` varchar(128) DEFAULT NULL,
  `createDate` date DEFAULT NULL,
  `modifyDate` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `qrtz_task_sql_info` (
  `id` int(11) NOT NULL,
  `sqlText` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `createDate` date DEFAULT NULL,
  `modifyDate` date DEFAULT NULL,
  `defaultPath` varchar(256) DEFAULT NULL,
  `fileName` varchar(128) DEFAULT 'default',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- Oracle
CREATE TABLE qrtz_task_mail_info (
  id INTEGER NOT NULL primary key,
  subject nvarchar2(255) DEFAULT NULL,
  fromAddress nvarchar2(128) DEFAULT NULL,
  message nvarchar2(512) DEFAULT NULL,
  copyList nvarchar2(256) DEFAULT NULL,
  sendList nvarchar2(256) DEFAULT NULL,
  nickName nvarchar2(128) DEFAULT NULL,
  createDate date DEFAULT NULL,
  modifyDate date DEFAULT NULL
);

CREATE TABLE qrtz_task_sql_info (
  ID INTEGER NOT NULL,
  sqlText BLOB,
  createDate date DEFAULT NULL,
  modifyDate date DEFAULT NULL,
  defaultPath nvarchar2(256) DEFAULT NULL,
  fileName nvarchar2(128) DEFAULT 'default'
);