PAGEBOT_DATA | CREATE TABLE `PAGEBOT_DATA` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `SEED` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `DOMAIN` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `SUB_DOMAIN` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `URL` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `PARENT_URL` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `PATH` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `CONTENT_TYPE` enum('HTML','JS','OTHER') COLLATE utf8mb4_unicode_ci NOT NULL,
  `TRACKER_ID` int(11) NOT NULL,
  `TAG_CONTAINER` enum('true','false') COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '	',
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
