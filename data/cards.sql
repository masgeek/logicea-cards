/*
 Navicat Premium Data Transfer

 Source Server         : Local MySQL
 Source Server Type    : MySQL
 Source Server Version : 80034
 Source Host           : 127.0.0.1:3306
 Source Schema         : cards

 Target Server Type    : MySQL
 Target Server Version : 80034
 File Encoding         : 65001

 Date: 31/08/2023 13:03:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cards
-- ----------------------------
DROP TABLE IF EXISTS `cards`;
CREATE TABLE `cards`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `card_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `card_color` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `card_status` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'TODO',
  `card_description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `created_at` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk-user-id`(`user_id` ASC) USING BTREE,
  CONSTRAINT `fk-user-id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 209 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cards
-- ----------------------------
INSERT INTO `cards` VALUES (1, 3, 'Harada Shino', NULL, 'TODO', NULL, '2023-08-28 22:33:50', '2007-05-11 09:15:45');
INSERT INTO `cards` VALUES (2, 1, 'Tracy Wells', NULL, 'TODO', NULL, '2023-08-27 02:39:42', '2012-06-15 12:59:35');
INSERT INTO `cards` VALUES (3, 7, 'Tong Kwok Yin', NULL, 'TODO', NULL, '2023-08-28 20:04:24', '2011-04-22 18:28:21');
INSERT INTO `cards` VALUES (4, 1, 'Steven Moreno', NULL, 'TODO', NULL, '2023-08-30 12:57:09', '2000-11-23 22:55:48');
INSERT INTO `cards` VALUES (5, 6, 'Ueno Hikaru', NULL, 'TODO', NULL, '2023-08-28 06:37:47', '2014-06-25 20:03:58');
INSERT INTO `cards` VALUES (6, 3, 'Tian Yuning', NULL, 'TODO', NULL, '2023-08-30 16:33:55', '2004-03-09 18:32:45');
INSERT INTO `cards` VALUES (7, 3, 'Justin Ortiz', NULL, 'TODO', NULL, '2023-08-29 13:46:20', '2019-08-22 02:56:58');
INSERT INTO `cards` VALUES (8, 4, 'Kobayashi Kazuma', NULL, 'TODO', NULL, '2023-08-30 11:55:11', '2012-12-27 05:17:25');
INSERT INTO `cards` VALUES (9, 7, 'Marvin Murphy', NULL, 'TODO', NULL, '2023-08-27 14:57:30', '2019-01-27 07:21:53');
INSERT INTO `cards` VALUES (10, 7, 'Tin Hiu Tung', NULL, 'TODO', NULL, '2023-08-29 17:19:02', '2003-07-22 19:51:00');
INSERT INTO `cards` VALUES (11, 6, 'Chin Kwok Ming', NULL, 'TODO', NULL, '2023-08-27 06:56:33', '2015-01-20 12:07:01');
INSERT INTO `cards` VALUES (12, 6, 'Chu Ho Yin', NULL, 'TODO', NULL, '2023-08-28 14:59:25', '2003-12-01 18:14:27');
INSERT INTO `cards` VALUES (13, 7, 'Aoki Yota', NULL, 'TODO', NULL, '2023-08-27 10:36:04', '2006-11-23 10:13:35');
INSERT INTO `cards` VALUES (14, 7, 'Pak Sze Yu', NULL, 'TODO', NULL, '2023-08-29 02:51:12', '2004-11-04 08:35:09');
INSERT INTO `cards` VALUES (15, 4, 'Phillip Olson', NULL, 'TODO', NULL, '2023-08-28 18:25:42', '2018-07-02 18:19:48');
INSERT INTO `cards` VALUES (17, 7, 'Yung Tin Lok', NULL, 'TODO', NULL, '2023-08-28 19:46:03', '2014-02-27 03:23:06');
INSERT INTO `cards` VALUES (18, 4, 'Ti Tak Wah', NULL, 'TODO', NULL, '2023-08-30 01:35:19', '2022-09-20 15:10:18');
INSERT INTO `cards` VALUES (19, 2, 'new UON invoice preparation', '#EEFFFF', 'DONE', 'This is a new card description', '2023-08-28 09:41:29', '2023-08-31 09:55:22');
INSERT INTO `cards` VALUES (20, 6, 'Gu Zitao', NULL, 'TODO', NULL, '2023-08-30 16:21:16', '2006-09-19 13:34:04');
INSERT INTO `cards` VALUES (21, 1, 'Sandra Jordan', NULL, 'TODO', NULL, '2023-08-30 20:04:45', '2009-06-09 19:39:24');
INSERT INTO `cards` VALUES (22, 4, 'Cui Zhennan', NULL, 'TODO', NULL, '2023-08-29 02:10:57', '2006-06-24 06:07:44');
INSERT INTO `cards` VALUES (23, 5, 'Sasaki Rin', NULL, 'TODO', NULL, '2023-08-28 12:28:11', '2020-10-17 21:03:24');
INSERT INTO `cards` VALUES (24, 1, 'Pak Wai Lam', NULL, 'TODO', NULL, '2023-08-27 04:53:02', '2014-08-09 15:58:11');
INSERT INTO `cards` VALUES (25, 5, 'Kao Wing Sze', NULL, 'TODO', NULL, '2023-08-28 02:31:19', '2009-03-07 03:46:24');
INSERT INTO `cards` VALUES (26, 7, 'Tam Hiu Tung', NULL, 'TODO', NULL, '2023-08-30 19:50:07', '2010-12-07 07:10:05');
INSERT INTO `cards` VALUES (27, 3, 'Ito Kasumi', NULL, 'TODO', NULL, '2023-08-30 01:39:15', '2019-05-12 20:10:25');
INSERT INTO `cards` VALUES (28, 7, 'Sakai Nanami', NULL, 'TODO', NULL, '2023-08-29 19:40:59', '2002-01-26 21:06:31');
INSERT INTO `cards` VALUES (29, 7, 'Hu Jialun', NULL, 'TODO', NULL, '2023-08-27 11:46:34', '2012-12-06 20:51:10');
INSERT INTO `cards` VALUES (30, 7, 'Paula Black', NULL, 'TODO', NULL, '2023-08-29 08:41:38', '2012-10-22 06:12:56');
INSERT INTO `cards` VALUES (31, 3, 'Kaneko Airi', NULL, 'TODO', NULL, '2023-08-30 12:03:33', '2006-09-01 23:56:53');
INSERT INTO `cards` VALUES (32, 5, 'Fu Yuning', NULL, 'TODO', NULL, '2023-08-29 16:59:28', '2007-01-12 08:43:47');
INSERT INTO `cards` VALUES (33, 2, 'UON invoice preparation', '#AFFFFF', 'IN_PROGRESS', 'This is a new card description', '2023-08-28 07:18:55', '2023-08-30 19:39:17');
INSERT INTO `cards` VALUES (34, 4, 'Fan Shihan', NULL, 'TODO', NULL, '2023-08-28 19:45:04', '2017-02-22 19:17:14');
INSERT INTO `cards` VALUES (35, 6, 'Ashley Owens', NULL, 'TODO', NULL, '2023-08-30 01:11:21', '2005-03-19 12:12:28');
INSERT INTO `cards` VALUES (36, 7, 'Dai Rui', NULL, 'TODO', NULL, '2023-08-30 00:22:11', '2014-09-19 19:00:21');
INSERT INTO `cards` VALUES (37, 2, 'Abe Mai', NULL, 'TODO', NULL, '2023-08-29 13:45:53', '2014-07-09 04:30:08');
INSERT INTO `cards` VALUES (38, 4, 'Yuen Wing Fat', NULL, 'TODO', NULL, '2023-08-30 20:19:28', '2002-03-13 11:43:34');
INSERT INTO `cards` VALUES (39, 2, 'Charlotte Jones', NULL, 'TODO', NULL, '2023-08-29 09:48:04', '2008-08-31 20:59:55');
INSERT INTO `cards` VALUES (40, 1, 'Sit On Kay', NULL, 'TODO', NULL, '2023-08-27 17:47:52', '2013-02-07 02:00:45');
INSERT INTO `cards` VALUES (41, 6, 'Tan Rui', NULL, 'TODO', NULL, '2023-08-28 02:12:50', '2021-03-08 14:53:07');
INSERT INTO `cards` VALUES (42, 4, 'Dai Xiaoming', NULL, 'TODO', NULL, '2023-08-28 18:22:50', '2013-06-25 18:29:37');
INSERT INTO `cards` VALUES (43, 3, 'Hou Jiehong', NULL, 'TODO', NULL, '2023-08-29 16:38:25', '2002-06-14 11:48:02');
INSERT INTO `cards` VALUES (44, 3, 'Yamazaki Minato', NULL, 'TODO', NULL, '2023-08-30 14:41:12', '2022-02-10 18:54:19');
INSERT INTO `cards` VALUES (45, 5, 'Yu Zhiyuan', NULL, 'TODO', NULL, '2023-08-30 00:43:49', '2015-09-23 05:16:01');
INSERT INTO `cards` VALUES (46, 7, 'Lam Wing Sze', NULL, 'TODO', NULL, '2023-08-30 13:35:37', '2005-09-06 05:55:05');
INSERT INTO `cards` VALUES (47, 6, 'Tian Xiuying', NULL, 'TODO', NULL, '2023-08-27 02:47:36', '2013-11-16 21:40:53');
INSERT INTO `cards` VALUES (48, 5, 'Tang Ka Ling', NULL, 'TODO', NULL, '2023-08-29 05:29:09', '2011-06-16 09:52:28');
INSERT INTO `cards` VALUES (49, 5, 'Takada Eita', NULL, 'TODO', NULL, '2023-08-29 09:40:42', '2009-05-29 14:31:24');
INSERT INTO `cards` VALUES (50, 7, 'Ishii Itsuki', NULL, 'TODO', NULL, '2023-08-27 16:19:25', '2005-02-02 14:18:42');
INSERT INTO `cards` VALUES (51, 7, 'Wang Shihan', NULL, 'TODO', NULL, '2023-08-30 16:43:42', '2022-04-17 03:35:21');
INSERT INTO `cards` VALUES (52, 6, 'Kinoshita Kasumi', NULL, 'TODO', NULL, '2023-08-29 08:22:50', '2020-08-09 19:13:07');
INSERT INTO `cards` VALUES (53, 5, 'Hui Ho Yin', NULL, 'TODO', NULL, '2023-08-29 21:23:58', '2014-01-25 02:31:08');
INSERT INTO `cards` VALUES (54, 6, 'Tiffany Holmes', NULL, 'TODO', NULL, '2023-08-27 20:57:36', '2003-10-06 20:39:05');
INSERT INTO `cards` VALUES (55, 6, 'Lin Xiuying', NULL, 'TODO', NULL, '2023-08-27 07:58:34', '2017-09-04 05:34:14');
INSERT INTO `cards` VALUES (56, 2, 'Zeng Yuning', NULL, 'TODO', NULL, '2023-08-27 15:11:37', '2006-01-06 13:38:25');
INSERT INTO `cards` VALUES (57, 1, 'Lok Kar Yan', NULL, 'TODO', NULL, '2023-08-28 08:41:34', '2023-03-01 19:42:11');
INSERT INTO `cards` VALUES (58, 2, 'Nakayama Hikari', NULL, 'TODO', NULL, '2023-08-29 09:46:06', '2008-05-09 12:54:02');
INSERT INTO `cards` VALUES (59, 7, 'Sakamoto Ren', NULL, 'TODO', NULL, '2023-08-27 08:29:38', '2018-01-21 00:01:34');
INSERT INTO `cards` VALUES (60, 4, 'Meng Yunxi', NULL, 'TODO', NULL, '2023-08-27 20:03:26', '2020-04-01 15:15:47');
INSERT INTO `cards` VALUES (61, 1, 'Mak Tsz Ching', NULL, 'TODO', NULL, '2023-08-29 14:09:26', '2012-06-24 20:33:50');
INSERT INTO `cards` VALUES (62, 3, 'Harold Mason', NULL, 'TODO', NULL, '2023-08-27 22:05:48', '2014-10-10 10:21:40');
INSERT INTO `cards` VALUES (63, 2, 'Yamada Daichi', NULL, 'TODO', NULL, '2023-08-29 19:13:14', '2004-01-18 10:48:59');
INSERT INTO `cards` VALUES (64, 5, 'Wu Anqi', NULL, 'TODO', NULL, '2023-08-27 13:54:16', '2015-01-05 16:00:04');
INSERT INTO `cards` VALUES (65, 3, 'Inoue Rena', NULL, 'TODO', NULL, '2023-08-30 15:49:59', '2005-06-18 13:59:08');
INSERT INTO `cards` VALUES (66, 3, 'Tiffany Cruz', NULL, 'TODO', NULL, '2023-08-29 01:13:17', '2009-09-26 06:12:02');
INSERT INTO `cards` VALUES (67, 3, 'Takahashi Mio', NULL, 'TODO', NULL, '2023-08-30 20:52:12', '2003-10-01 09:34:00');
INSERT INTO `cards` VALUES (68, 3, 'Juanita Hunter', NULL, 'TODO', NULL, '2023-08-28 04:40:20', '2011-12-21 10:21:37');
INSERT INTO `cards` VALUES (69, 6, 'Wu Jiehong', NULL, 'TODO', NULL, '2023-08-28 18:50:37', '2009-09-17 15:15:10');
INSERT INTO `cards` VALUES (70, 4, 'Maruyama Aoshi', NULL, 'TODO', NULL, '2023-08-30 23:40:15', '2002-03-06 03:45:52');
INSERT INTO `cards` VALUES (71, 4, 'Travis Bennett', NULL, 'TODO', NULL, '2023-08-29 10:18:43', '2018-06-22 05:20:29');
INSERT INTO `cards` VALUES (72, 5, 'Wong Chi Ming', NULL, 'TODO', NULL, '2023-08-29 06:39:32', '2019-06-01 09:11:36');
INSERT INTO `cards` VALUES (73, 3, 'Wu Yuning', NULL, 'TODO', NULL, '2023-08-27 09:10:13', '2020-06-06 08:45:20');
INSERT INTO `cards` VALUES (74, 4, 'Luo Zitao', NULL, 'TODO', NULL, '2023-08-27 16:03:13', '2017-05-11 13:07:07');
INSERT INTO `cards` VALUES (75, 4, 'Ruth Flores', NULL, 'TODO', NULL, '2023-08-29 08:45:01', '2019-06-06 19:43:52');
INSERT INTO `cards` VALUES (76, 4, 'Fukuda Ikki', NULL, 'TODO', NULL, '2023-08-29 19:30:10', '2018-06-29 15:40:45');
INSERT INTO `cards` VALUES (77, 3, 'Ando Rin', NULL, 'TODO', NULL, '2023-08-28 09:00:59', '2009-11-06 23:11:09');
INSERT INTO `cards` VALUES (78, 2, 'Mike Murray', NULL, 'TODO', NULL, '2023-08-28 19:54:31', '2007-05-09 00:01:52');
INSERT INTO `cards` VALUES (79, 3, 'Miura Itsuki', NULL, 'TODO', NULL, '2023-08-27 14:07:47', '2016-01-10 06:36:30');
INSERT INTO `cards` VALUES (80, 7, 'Abe Daisuke', NULL, 'TODO', NULL, '2023-08-29 15:01:40', '2022-03-06 13:52:34');
INSERT INTO `cards` VALUES (81, 2, 'Liu Jialun', NULL, 'TODO', NULL, '2023-08-28 20:49:49', '2014-12-12 09:20:47');
INSERT INTO `cards` VALUES (82, 1, 'Valerie Myers', NULL, 'TODO', NULL, '2023-08-28 16:34:59', '2021-03-18 10:40:34');
INSERT INTO `cards` VALUES (83, 2, 'Yao Yuning', NULL, 'TODO', NULL, '2023-08-27 18:17:26', '2023-05-26 18:10:58');
INSERT INTO `cards` VALUES (84, 1, 'Carolyn Wright', NULL, 'TODO', NULL, '2023-08-27 07:22:01', '2022-12-24 06:12:39');
INSERT INTO `cards` VALUES (85, 5, 'Fujiwara Yamato', NULL, 'TODO', NULL, '2023-08-27 09:16:13', '2023-07-03 12:45:58');
INSERT INTO `cards` VALUES (86, 5, 'Xu Jiehong', NULL, 'TODO', NULL, '2023-08-29 11:58:20', '2012-07-04 05:58:10');
INSERT INTO `cards` VALUES (87, 2, 'Lau On Kay', NULL, 'TODO', NULL, '2023-08-28 04:22:57', '2012-06-22 14:46:24');
INSERT INTO `cards` VALUES (88, 1, 'Hsuan Ka Ling', NULL, 'TODO', NULL, '2023-08-30 19:12:16', '2000-04-17 04:45:16');
INSERT INTO `cards` VALUES (89, 6, 'Gregory Bennett', NULL, 'TODO', NULL, '2023-08-29 16:50:09', '2016-09-10 18:19:30');
INSERT INTO `cards` VALUES (90, 7, 'Duan Jiehong', NULL, 'TODO', NULL, '2023-08-30 17:49:23', '2002-09-06 10:28:19');
INSERT INTO `cards` VALUES (91, 4, 'Chin Wing Kuen', NULL, 'TODO', NULL, '2023-08-29 13:26:04', '2018-06-01 16:44:17');
INSERT INTO `cards` VALUES (92, 4, 'Wang Jialun', NULL, 'TODO', NULL, '2023-08-28 00:21:23', '2004-12-25 04:25:47');
INSERT INTO `cards` VALUES (93, 4, 'Judy Brown', NULL, 'TODO', NULL, '2023-08-30 21:59:38', '2001-12-13 21:59:43');
INSERT INTO `cards` VALUES (94, 4, 'Lok Wai Han', NULL, 'TODO', NULL, '2023-08-29 07:12:58', '2008-07-09 03:39:47');
INSERT INTO `cards` VALUES (95, 2, 'Shi Yunxi', NULL, 'TODO', NULL, '2023-08-27 07:40:32', '2012-11-29 13:33:16');
INSERT INTO `cards` VALUES (96, 2, 'Hsuan Ting Fung', NULL, 'TODO', NULL, '2023-08-28 15:12:49', '2020-11-09 19:19:13');
INSERT INTO `cards` VALUES (97, 7, 'Fung Tin Wing', NULL, 'TODO', NULL, '2023-08-30 20:57:37', '2014-04-16 11:22:44');
INSERT INTO `cards` VALUES (98, 7, 'Chung Wing Suen', NULL, 'TODO', NULL, '2023-08-29 09:09:24', '2001-01-20 01:32:43');
INSERT INTO `cards` VALUES (99, 6, 'Cheung Ka Fai', NULL, 'TODO', NULL, '2023-08-27 21:03:03', '2020-12-03 06:39:54');
INSERT INTO `cards` VALUES (100, 7, 'Fred Ferguson', NULL, 'TODO', NULL, '2023-08-28 08:29:01', '2012-06-10 21:54:07');
INSERT INTO `cards` VALUES (101, 3, 'Alan Powell', NULL, 'TODO', NULL, '2023-08-27 13:38:37', '2022-09-24 22:07:51');
INSERT INTO `cards` VALUES (102, 1, 'Wei Xiaoming', NULL, 'TODO', NULL, '2023-08-28 00:58:24', '2009-12-12 12:56:13');
INSERT INTO `cards` VALUES (103, 7, 'Lau Fu Shing', NULL, 'TODO', NULL, '2023-08-29 17:07:07', '2015-04-25 19:09:54');
INSERT INTO `cards` VALUES (104, 7, 'Edwin Gordon', NULL, 'TODO', NULL, '2023-08-30 05:41:40', '2009-07-09 06:03:41');
INSERT INTO `cards` VALUES (105, 5, 'Sugawara Rena', NULL, 'TODO', NULL, '2023-08-27 15:07:37', '2014-01-06 22:24:52');
INSERT INTO `cards` VALUES (106, 6, 'Sano Yuto', NULL, 'TODO', NULL, '2023-08-30 02:07:36', '2002-12-15 22:00:50');
INSERT INTO `cards` VALUES (107, 3, 'Emma Castro', NULL, 'TODO', NULL, '2023-08-29 11:49:21', '2019-04-29 11:12:58');
INSERT INTO `cards` VALUES (108, 1, 'Tang Wing Fat', NULL, 'TODO', NULL, '2023-08-28 09:32:53', '2007-06-23 08:48:40');
INSERT INTO `cards` VALUES (109, 7, 'Kato Ayano', NULL, 'TODO', NULL, '2023-08-29 14:10:00', '2000-06-30 20:04:20');
INSERT INTO `cards` VALUES (110, 7, 'Nishimura Takuya', NULL, 'TODO', NULL, '2023-08-29 09:11:46', '2020-09-21 23:39:28');
INSERT INTO `cards` VALUES (111, 2, 'Sakamoto Momoe', NULL, 'TODO', NULL, '2023-08-30 03:45:45', '2021-03-20 18:50:34');
INSERT INTO `cards` VALUES (112, 1, 'Harry Adams', NULL, 'TODO', NULL, '2023-08-27 07:49:16', '2005-12-17 00:06:48');
INSERT INTO `cards` VALUES (113, 6, 'Joel Holmes', NULL, 'TODO', NULL, '2023-08-27 08:57:02', '2010-07-14 17:14:07');
INSERT INTO `cards` VALUES (114, 6, 'Yamada Itsuki', NULL, 'TODO', NULL, '2023-08-28 12:08:10', '2015-09-07 21:29:56');
INSERT INTO `cards` VALUES (115, 3, 'Cheung Kwok Yin', NULL, 'TODO', NULL, '2023-08-30 16:00:51', '2010-08-04 13:57:55');
INSERT INTO `cards` VALUES (116, 4, 'Fang Lan', NULL, 'TODO', NULL, '2023-08-28 00:06:50', '2001-06-11 21:21:26');
INSERT INTO `cards` VALUES (117, 3, 'Alice Schmidt', NULL, 'TODO', NULL, '2023-08-30 18:24:43', '2013-05-01 12:16:58');
INSERT INTO `cards` VALUES (118, 6, 'Tanaka Miu', NULL, 'TODO', NULL, '2023-08-27 20:40:38', '2009-10-04 16:01:11');
INSERT INTO `cards` VALUES (119, 5, 'Ma Lan', NULL, 'TODO', NULL, '2023-08-27 15:59:33', '2001-11-05 04:15:15');
INSERT INTO `cards` VALUES (120, 1, 'Ruby Gibson', NULL, 'TODO', NULL, '2023-08-28 05:02:15', '2016-06-24 16:05:30');
INSERT INTO `cards` VALUES (121, 2, 'Fung Tak Wah', NULL, 'TODO', NULL, '2023-08-29 21:01:04', '2006-09-20 12:48:53');
INSERT INTO `cards` VALUES (122, 4, 'Anthony Owens', NULL, 'TODO', NULL, '2023-08-28 16:31:17', '2022-07-13 05:45:47');
INSERT INTO `cards` VALUES (123, 2, 'Meng Zhiyuan', NULL, 'TODO', NULL, '2023-08-30 13:23:02', '2000-08-15 01:49:14');
INSERT INTO `cards` VALUES (124, 2, 'Ye Jialun', NULL, 'TODO', NULL, '2023-08-27 16:54:50', '2000-11-06 20:27:51');
INSERT INTO `cards` VALUES (125, 5, 'Kathleen Carter', NULL, 'TODO', NULL, '2023-08-27 13:22:38', '2018-07-08 13:04:03');
INSERT INTO `cards` VALUES (126, 4, 'Shimada Ayano', NULL, 'TODO', NULL, '2023-08-28 09:59:21', '2013-10-22 01:42:13');
INSERT INTO `cards` VALUES (127, 1, 'Miu Wing Kuen', NULL, 'TODO', NULL, '2023-08-30 01:10:39', '2021-10-14 16:04:46');
INSERT INTO `cards` VALUES (128, 2, 'Uchida Momoka', NULL, 'TODO', NULL, '2023-08-29 20:02:16', '2014-08-24 07:00:20');
INSERT INTO `cards` VALUES (129, 4, 'Sato Ayato', NULL, 'TODO', NULL, '2023-08-27 06:19:23', '2022-01-09 00:32:45');
INSERT INTO `cards` VALUES (130, 1, 'Thomas Stone', NULL, 'TODO', NULL, '2023-08-29 14:55:46', '2003-11-22 09:42:42');
INSERT INTO `cards` VALUES (131, 1, 'Yan Ziyi', NULL, 'TODO', NULL, '2023-08-27 06:24:06', '2007-04-17 10:24:48');
INSERT INTO `cards` VALUES (132, 7, 'Frederick Perry', NULL, 'TODO', NULL, '2023-08-30 09:28:44', '2018-05-25 04:55:13');
INSERT INTO `cards` VALUES (133, 6, 'Murata Shino', NULL, 'TODO', NULL, '2023-08-28 19:33:19', '2004-06-02 14:59:21');
INSERT INTO `cards` VALUES (134, 5, 'Don Holmes', NULL, 'TODO', NULL, '2023-08-27 05:11:46', '2019-02-20 04:01:02');
INSERT INTO `cards` VALUES (135, 1, 'Ueda Daisuke', NULL, 'TODO', NULL, '2023-08-28 13:33:29', '2014-10-14 00:28:00');
INSERT INTO `cards` VALUES (136, 5, 'Sheh Wing Fat', NULL, 'TODO', NULL, '2023-08-27 09:44:32', '2016-11-08 20:59:00');
INSERT INTO `cards` VALUES (137, 6, 'Roger Jordan', NULL, 'TODO', NULL, '2023-08-30 20:54:57', '2021-01-26 08:55:34');
INSERT INTO `cards` VALUES (138, 7, 'Imai Yuto', NULL, 'TODO', NULL, '2023-08-28 09:27:25', '2015-11-17 10:19:20');
INSERT INTO `cards` VALUES (139, 1, 'Philip Hill', NULL, 'TODO', NULL, '2023-08-29 06:50:43', '2022-12-10 04:24:50');
INSERT INTO `cards` VALUES (140, 6, 'Yam Sze Yu', NULL, 'TODO', NULL, '2023-08-28 19:48:18', '2020-09-05 01:02:20');
INSERT INTO `cards` VALUES (141, 1, 'Kwok Suk Yee', NULL, 'TODO', NULL, '2023-08-28 19:17:35', '2009-10-19 22:14:08');
INSERT INTO `cards` VALUES (142, 6, 'Tam Wing Fat', NULL, 'TODO', NULL, '2023-08-27 12:57:02', '2017-11-04 19:22:12');
INSERT INTO `cards` VALUES (143, 4, 'Randall Hamilton', NULL, 'TODO', NULL, '2023-08-28 08:36:36', '2002-11-19 04:31:44');
INSERT INTO `cards` VALUES (144, 4, 'Eric Dixon', NULL, 'TODO', NULL, '2023-08-29 12:50:45', '2006-12-17 04:33:21');
INSERT INTO `cards` VALUES (145, 2, 'Tiffany Ryan', NULL, 'TODO', NULL, '2023-08-30 15:15:35', '2022-06-30 07:04:34');
INSERT INTO `cards` VALUES (146, 7, 'Sara Rose', NULL, 'TODO', NULL, '2023-08-27 15:40:22', '2017-03-24 03:09:28');
INSERT INTO `cards` VALUES (147, 3, 'Kondo Misaki', NULL, 'TODO', NULL, '2023-08-28 07:10:22', '2004-03-21 05:51:49');
INSERT INTO `cards` VALUES (148, 5, 'Long Shihan', NULL, 'TODO', NULL, '2023-08-27 23:44:53', '2012-05-24 14:16:05');
INSERT INTO `cards` VALUES (149, 6, 'Ota Akina', NULL, 'TODO', NULL, '2023-08-28 16:57:57', '2017-08-03 10:25:51');
INSERT INTO `cards` VALUES (150, 4, 'Denise Sanchez', NULL, 'TODO', NULL, '2023-08-30 21:58:52', '2014-06-11 06:43:20');
INSERT INTO `cards` VALUES (151, 7, 'Ma Xiuying', NULL, 'TODO', NULL, '2023-08-30 19:44:13', '2018-05-27 01:44:42');
INSERT INTO `cards` VALUES (152, 7, 'Yamamoto Riku', NULL, 'TODO', NULL, '2023-08-28 04:13:26', '2015-09-11 14:19:18');
INSERT INTO `cards` VALUES (153, 4, 'He Rui', NULL, 'TODO', NULL, '2023-08-29 14:15:27', '2018-01-03 09:12:50');
INSERT INTO `cards` VALUES (154, 2, 'Kong Jiehong', NULL, 'TODO', NULL, '2023-08-30 10:36:58', '2001-05-24 13:21:00');
INSERT INTO `cards` VALUES (155, 6, 'Kam Kwok Wing', NULL, 'TODO', NULL, '2023-08-27 08:50:31', '2001-10-03 02:18:32');
INSERT INTO `cards` VALUES (156, 6, 'Chic Tak Wah', NULL, 'TODO', NULL, '2023-08-27 00:23:43', '2004-02-25 23:43:46');
INSERT INTO `cards` VALUES (157, 7, 'Au Sum Wing', NULL, 'TODO', NULL, '2023-08-30 06:04:04', '2015-06-29 20:20:59');
INSERT INTO `cards` VALUES (158, 2, 'Gloria Simpson', NULL, 'TODO', NULL, '2023-08-30 05:51:48', '2020-03-23 20:19:34');
INSERT INTO `cards` VALUES (159, 7, 'Abe Kaito', NULL, 'TODO', NULL, '2023-08-27 23:53:18', '2006-07-16 20:43:36');
INSERT INTO `cards` VALUES (160, 7, 'Shibata Sakura', NULL, 'TODO', NULL, '2023-08-27 06:14:11', '2019-09-16 15:23:02');
INSERT INTO `cards` VALUES (161, 6, 'Tsui Sze Yu', NULL, 'TODO', NULL, '2023-08-28 16:44:33', '2017-03-08 01:29:54');
INSERT INTO `cards` VALUES (162, 4, 'Ishida Daichi', NULL, 'TODO', NULL, '2023-08-27 10:10:31', '2014-05-12 19:12:20');
INSERT INTO `cards` VALUES (163, 7, 'Hashimoto Akina', NULL, 'TODO', NULL, '2023-08-28 20:02:59', '2021-08-20 19:24:26');
INSERT INTO `cards` VALUES (164, 4, 'Chan Lai Yan', NULL, 'TODO', NULL, '2023-08-30 18:37:48', '2019-05-03 09:40:26');
INSERT INTO `cards` VALUES (165, 3, 'Mok Yun Fat', NULL, 'TODO', NULL, '2023-08-30 15:21:01', '2020-02-27 06:15:10');
INSERT INTO `cards` VALUES (166, 4, 'Noguchi Minato', NULL, 'TODO', NULL, '2023-08-30 19:00:55', '2017-07-05 12:24:56');
INSERT INTO `cards` VALUES (167, 6, 'Sato Ayato', NULL, 'TODO', NULL, '2023-08-30 13:59:06', '2013-08-01 10:35:48');
INSERT INTO `cards` VALUES (168, 6, 'Yu Zhiyuan', NULL, 'TODO', NULL, '2023-08-30 00:22:22', '2021-04-28 17:26:34');
INSERT INTO `cards` VALUES (169, 4, 'Kono Minato', NULL, 'TODO', NULL, '2023-08-28 13:25:02', '2000-06-04 01:48:26');
INSERT INTO `cards` VALUES (170, 7, 'Peng Zhennan', NULL, 'TODO', NULL, '2023-08-28 03:21:52', '2007-03-26 01:59:49');
INSERT INTO `cards` VALUES (171, 5, 'Yoshida Daisuke', NULL, 'TODO', NULL, '2023-08-29 19:21:36', '2021-09-03 17:48:27');
INSERT INTO `cards` VALUES (172, 1, 'Watanabe Kasumi', NULL, 'TODO', NULL, '2023-08-30 22:03:14', '2005-11-05 09:01:37');
INSERT INTO `cards` VALUES (173, 2, 'Sit On Na', NULL, 'TODO', NULL, '2023-08-30 02:05:16', '2001-06-18 17:50:48');
INSERT INTO `cards` VALUES (174, 3, 'Arimura Ren', NULL, 'TODO', NULL, '2023-08-27 22:45:50', '2002-11-03 23:29:23');
INSERT INTO `cards` VALUES (175, 1, 'Du Jiehong', NULL, 'TODO', NULL, '2023-08-29 06:35:10', '2000-11-08 06:59:21');
INSERT INTO `cards` VALUES (176, 1, 'Terry Miller', NULL, 'TODO', NULL, '2023-08-28 19:44:42', '2005-07-13 05:31:52');
INSERT INTO `cards` VALUES (177, 3, 'Kong Wai Lam', NULL, 'TODO', NULL, '2023-08-27 00:48:29', '2020-08-26 04:28:04');
INSERT INTO `cards` VALUES (178, 2, 'Ueno Tsubasa', NULL, 'TODO', NULL, '2023-08-28 06:56:42', '2007-11-03 06:50:11');
INSERT INTO `cards` VALUES (179, 2, 'Hung Wai Han', NULL, 'TODO', NULL, '2023-08-30 15:51:07', '2018-10-01 17:46:44');
INSERT INTO `cards` VALUES (180, 2, 'Gong Shihan', NULL, 'TODO', NULL, '2023-08-28 02:32:13', '2008-04-12 09:42:50');
INSERT INTO `cards` VALUES (181, 2, 'Kao Ka Keung', NULL, 'TODO', NULL, '2023-08-28 21:40:10', '2016-09-13 21:53:23');
INSERT INTO `cards` VALUES (182, 5, 'David Romero', NULL, 'TODO', NULL, '2023-08-28 10:03:43', '2006-09-05 05:42:07');
INSERT INTO `cards` VALUES (183, 7, 'Yeung On Kay', NULL, 'TODO', NULL, '2023-08-30 12:42:33', '2013-06-20 02:05:47');
INSERT INTO `cards` VALUES (184, 4, 'Kelly Holmes', NULL, 'TODO', NULL, '2023-08-28 16:10:21', '2020-11-22 17:44:18');
INSERT INTO `cards` VALUES (185, 6, 'Zhang Anqi', NULL, 'TODO', NULL, '2023-08-29 02:38:36', '2007-04-01 20:21:36');
INSERT INTO `cards` VALUES (186, 5, 'Man Chiu Wai', NULL, 'TODO', NULL, '2023-08-30 06:04:02', '2015-02-19 07:49:28');
INSERT INTO `cards` VALUES (187, 4, 'Endo Rena', NULL, 'TODO', NULL, '2023-08-30 18:00:09', '2002-08-17 02:30:59');
INSERT INTO `cards` VALUES (188, 6, 'Martha Dixon', NULL, 'TODO', NULL, '2023-08-28 04:13:33', '2003-01-08 00:09:35');
INSERT INTO `cards` VALUES (189, 2, 'Maruyama Daisuke', NULL, 'TODO', NULL, '2023-08-30 23:21:54', '2019-10-04 13:50:40');
INSERT INTO `cards` VALUES (190, 5, 'Xue Lu', NULL, 'TODO', NULL, '2023-08-30 13:48:20', '2019-05-21 17:09:42');
INSERT INTO `cards` VALUES (191, 7, 'Long Zhennan', NULL, 'TODO', NULL, '2023-08-28 10:21:55', '2021-06-29 06:38:13');
INSERT INTO `cards` VALUES (192, 5, 'Ota Aoi', NULL, 'TODO', NULL, '2023-08-30 12:08:34', '2005-07-28 14:23:58');
INSERT INTO `cards` VALUES (193, 3, 'Shimizu Mio', NULL, 'TODO', NULL, '2023-08-30 13:33:29', '2001-10-16 23:57:43');
INSERT INTO `cards` VALUES (194, 2, 'Yue Tin Lok', NULL, 'TODO', NULL, '2023-08-27 02:13:07', '2022-11-07 10:24:18');
INSERT INTO `cards` VALUES (195, 2, 'Virginia Mendoza', NULL, 'TODO', NULL, '2023-08-28 14:11:17', '2022-11-14 22:08:47');
INSERT INTO `cards` VALUES (196, 2, 'Irene Jenkins', NULL, 'TODO', NULL, '2023-08-28 23:23:26', '2004-04-17 18:20:20');
INSERT INTO `cards` VALUES (197, 2, 'Tang Sau Man', NULL, 'TODO', NULL, '2023-08-29 04:22:31', '2013-10-30 02:21:10');
INSERT INTO `cards` VALUES (198, 5, 'Qian Yuning', NULL, 'TODO', NULL, '2023-08-28 12:09:48', '2022-01-09 03:18:46');
INSERT INTO `cards` VALUES (199, 3, 'Zhang Xiaoming', NULL, 'TODO', NULL, '2023-08-29 06:37:28', '2008-09-21 22:59:25');
INSERT INTO `cards` VALUES (200, 1, 'Carol Palmer', NULL, 'TODO', NULL, '2023-08-27 13:16:17', '2004-11-27 14:57:26');
INSERT INTO `cards` VALUES (201, 1, 'string', '#FFFFFF', 'TODO', '#000', NULL, NULL);
INSERT INTO `cards` VALUES (202, 1, 'string', '#FFFFFF', 'TODO', '#000', NULL, NULL);
INSERT INTO `cards` VALUES (203, 1, 'string', '#FFFFFF', 'TODO', '#000', NULL, NULL);
INSERT INTO `cards` VALUES (204, 1, 'string', '#FFFFFF', 'TODO', '#000', '2023-08-30 16:15:15', '2023-08-30 13:15:15');
INSERT INTO `cards` VALUES (205, 2, 'string', '#FFFFFF', 'TODO', '#000', '2023-08-30 16:15:47', '2023-08-30 13:15:47');
INSERT INTO `cards` VALUES (206, 2, 'Test new card', NULL, 'TODO', NULL, '2023-08-30 18:46:34', '2023-08-30 15:46:42');
INSERT INTO `cards` VALUES (207, 2, 'Test new card', '#FFFFFF', 'TODO', '#000', '2023-08-30 18:46:43', '2023-08-30 15:46:50');
INSERT INTO `cards` VALUES (208, 2, 'AATest new card', '#FFFFFF', 'TODO', '#000', '2023-08-30 19:38:48', '2023-08-30 16:38:56');

-- ----------------------------
-- Table structure for cards_migration
-- ----------------------------
DROP TABLE IF EXISTS `cards_migration`;
CREATE TABLE `cards_migration`  (
  `ID` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `AUTHOR` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `FILENAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int NOT NULL,
  `EXECTYPE` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `MD5SUM` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `DESCRIPTION` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `COMMENTS` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `TAG` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `LIQUIBASE` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `CONTEXTS` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `LABELS` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cards_migration
-- ----------------------------
INSERT INTO `cards_migration` VALUES ('29-01', 'masgeek', 'migrations/changelog/29-01-create-users-table.xml', '2023-08-30 10:05:22', 1, 'EXECUTED', '8:b9461926bdeae54f7201f649f8f9ee1c', 'createTable tableName=users', '', NULL, '4.20.0', NULL, NULL, '3389922068');
INSERT INTO `cards_migration` VALUES ('29-02', 'masgeek', 'migrations/changelog/29-02-create-cards-table.xml', '2023-08-30 10:05:22', 2, 'EXECUTED', '8:2656de8f71aa7e8b726f24ad62e5c8be', 'createTable tableName=cards', '', NULL, '4.20.0', NULL, NULL, '3389922068');
INSERT INTO `cards_migration` VALUES ('29-02', 'masgeek', 'migrations/changelog/29-03-create-user-roles-table.xml', '2023-08-30 10:05:22', 3, 'EXECUTED', '8:58ea9600276398ed690c64dee4e262c2', 'createTable tableName=user_roles', '', NULL, '4.20.0', NULL, NULL, '3389922068');
INSERT INTO `cards_migration` VALUES ('29-03', 'masgeek', 'migrations/changelog/29-03-insert-into-user-roles-table.xml', '2023-08-30 10:05:22', 4, 'EXECUTED', '8:6feeb6cdbeed5fa79216e2edcc3e5fb3', 'insert tableName=user_roles; insert tableName=user_roles', '', NULL, '4.20.0', NULL, NULL, '3389922068');
INSERT INTO `cards_migration` VALUES ('29-04', 'masgeek', 'migrations/changelog/29-04-add-user-role-fk-to-users-table.xml', '2023-08-30 10:05:22', 5, 'EXECUTED', '8:1a64c2433326ef38bb7b16f3ed34f218', 'addForeignKeyConstraint baseTableName=users, constraintName=fk-user-role, referencedTableName=user_roles', '', NULL, '4.20.0', NULL, NULL, '3389922068');

-- ----------------------------
-- Table structure for cards_migration_lock
-- ----------------------------
DROP TABLE IF EXISTS `cards_migration_lock`;
CREATE TABLE `cards_migration_lock`  (
  `ID` int NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime NULL DEFAULT NULL,
  `LOCKEDBY` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cards_migration_lock
-- ----------------------------
INSERT INTO `cards_migration_lock` VALUES (1, b'0', NULL, NULL);

-- ----------------------------
-- Table structure for user_roles
-- ----------------------------
DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE `user_roles`  (
  `role_name` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`role_name`) USING BTREE,
  UNIQUE INDEX `uk-user-role`(`role_name` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_roles
-- ----------------------------
INSERT INTO `user_roles` VALUES ('ADMIN', '2023-08-30 10:05:22', '2023-08-31 10:38:52');
INSERT INTO `user_roles` VALUES ('MEMBER', '2023-08-30 10:05:22', '2023-08-31 10:38:52');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `first_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `last_name` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `user_role` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_password` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk-user-email`(`user_email` ASC) USING BTREE,
  INDEX `fk-user-role`(`user_role` ASC) USING BTREE,
  CONSTRAINT `fk-user-role` FOREIGN KEY (`user_role`) REFERENCES `user_roles` (`role_name`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'barsamms@gmail.com', 'Anqi', 'Kong', 'ADMIN', '$2a$10$eAmKyH0ssFnORcJmgCqIk.ay5WUOVQ70/lsBZI8GLorVTMfzVrPkW', '2000-06-20 20:27:06', '2004-02-22 04:49:00');
INSERT INTO `users` VALUES (2, 'member@gmail.com', 'Chun Yu', 'Mo', 'MEMBER', '$2a$10$eAmKyH0ssFnORcJmgCqIk.ay5WUOVQ70/lsBZI8GLorVTMfzVrPkW', '2004-01-12 20:31:39', '2002-07-21 22:15:55');
INSERT INTO `users` VALUES (3, 'suanqi@mail.com', 'Anqi', 'Su', 'ADMIN', '$2a$10$eAmKyH0ssFnORcJmgCqIk.ay5WUOVQ70/lsBZI8GLorVTMfzVrPkW', '2005-11-27 16:52:04', '2017-11-26 05:15:52');
INSERT INTO `users` VALUES (4, 'raymc87@hotmail.com', 'Ray', 'Mcdonald', 'MEMBER', '$2a$10$eAmKyH0ssFnORcJmgCqIk.ay5WUOVQ70/lsBZI8GLorVTMfzVrPkW', '2019-01-11 01:18:26', '2015-02-25 20:50:39');
INSERT INTO `users` VALUES (5, 'yamyuit123@gmail.com', 'Yuito', 'Yamaguchi', 'MEMBER', '$2a$10$eAmKyH0ssFnORcJmgCqIk.ay5WUOVQ70/lsBZI8GLorVTMfzVrPkW', '2007-07-31 03:46:10', '2017-11-18 21:57:03');
INSERT INTO `users` VALUES (6, 'tsubasai@gmail.com', 'Tsubasa', 'Imai', 'MEMBER', '$2a$10$eAmKyH0ssFnORcJmgCqIk.ay5WUOVQ70/lsBZI8GLorVTMfzVrPkW', '2023-06-20 11:03:30', '2005-08-27 10:40:16');
INSERT INTO `users` VALUES (7, 'morristeresa10@icloud.com', 'Teresa', 'Morris', 'MEMBER', '$2a$10$eAmKyH0ssFnORcJmgCqIk.ay5WUOVQ70/lsBZI8GLorVTMfzVrPkW', '2015-03-24 07:44:54', '2022-05-03 13:41:06');

SET FOREIGN_KEY_CHECKS = 1;
