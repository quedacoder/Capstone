CREATE TABLE `project` (
  `project_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `changed_by` varchar(255) DEFAULT NULL,
  `changed_date` datetime(6) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `process_area` int(11) DEFAULT NULL,
  `project_name` varchar(255) DEFAULT NULL,
  `project_owner` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`project_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `task` (
  `task_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `changed_by` varchar(255) DEFAULT NULL,
  `changed_date` datetime(6) DEFAULT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `start_date` datetime(6) DEFAULT NULL,
  `status` varchar(15) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `task_name` varchar(255) DEFAULT NULL,
  `task_type` varchar(15) DEFAULT NULL,
  `ticket_number` varchar(255) DEFAULT NULL,
  `project_id` bigint(20) NOT NULL,
  PRIMARY KEY (`task_id`),
  KEY `FK_task_project` (`project_id`),
  CONSTRAINT `FK_task_project` FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `task_replicated_data` (
  `task_replicated_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `changed_by` varchar(255) DEFAULT NULL,
  `changed_date` datetime(6) DEFAULT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `start_date` datetime(6) DEFAULT NULL,
  `status` varchar(15) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `task_name` varchar(255) DEFAULT NULL,
  `task_type` varchar(15) DEFAULT NULL,
  `ticket_number` varchar(255) DEFAULT NULL,
  `task_id` bigint(20) NOT NULL,
  PRIMARY KEY (`task_replicated_id`),
  KEY `FKk8qrwowg31kx7hp93sru1pdqa` (`task_id`),
  CONSTRAINT `TASK_CHANDED_DATA_TB` FOREIGN KEY (`task_id`) REFERENCES `task` (`task_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `GET_RECENT_TASK_UPDATES`(IN pTask_id BIGINT(20))
BEGIN
	SELECT task_replicated_id, task_name, task_type, ticket_number, status, description, comments, changed_by, changed_date 
      FROM task_replicated_data
      INNER JOIN (
		SELECT MAX(task_replicated_id) as max_task_id
          FROM task_replicated_data
          GROUP BY task_replicated_id
      ) trd ON task_replicated_data.task_replicated_id = trd.max_task_id
	 WHERE task_id = pTask_id
     ORDER BY task_replicated_id desc
     LIMIT 4;
END$$
DELIMITER ;

