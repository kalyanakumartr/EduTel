CREATE TABLE `onlineexamassigned` (
  `oeExamAutoId` int(10) NOT NULL AUTO_INCREMENT,
  `oeExamId` varchar(50) NOT NULL,
  `oeAssignedExamDate` varchar(50) NOT NULL,
  `usEmployeeId` varchar(50) NOT NULL,
  `createdBy` varchar(50) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `modifiedBy` varchar(50) DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `status` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`oeExamAutoId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1$$


INSERT INTO `edutel`.`mamenu` (`maMenuId`, `maMenuName`, `maMenuActionURL`, `maMenuToolTip`, `maMenuOnClick`, `maMenuOnBlur`, `maMenuOnMouseOver`, `maParentId`, `maSubMenuOrder`, `maSubMenuCount`, `maMenuLevel`) VALUES ('M0039', 'Search Online Assigned Exam', 'createOnlinePreExamAssigned.do', '0', '0', '0', '0', 'M0020', '4', '0', 'DD');
UPDATE `edutel`.`mamenu` SET `maSubMenuOrder`='3' WHERE `maMenuId`='M0023';

INSERT INTO `edutel`.`mamenurole` (`maMenuId`, `rlRoleId`) VALUES ('M0039', 'SuperAdminRole');
INSERT INTO `edutel`.`mamenurole` (`maMenuId`, `rlRoleId`) VALUES ('M0039', 'Employee');

ALTER TABLE `edutel`.`onlineexam` ADD COLUMN `displayPublic` BIT(1) NOT NULL DEFAULT 0  AFTER `status` ;