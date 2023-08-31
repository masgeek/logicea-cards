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

 Date: 31/08/2023 14:19:24
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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cards
-- ----------------------------

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
INSERT INTO `cards_migration` VALUES ('29-01', 'masgeek', 'migrations/changelog/29-01-create-users-table.xml', '2023-08-31 10:49:19', 1, 'EXECUTED', '8:34e2fa26b2108d58ba79c9cf3a6a5a15', 'createTable tableName=users', '', NULL, '4.20.0', NULL, NULL, '3478959598');
INSERT INTO `cards_migration` VALUES ('29-02', 'masgeek', 'migrations/changelog/29-02-create-cards-table.xml', '2023-08-31 10:49:19', 2, 'EXECUTED', '8:1732fe15e4789725cfbe0a2229e00764', 'createTable tableName=cards', '', NULL, '4.20.0', NULL, NULL, '3478959598');
INSERT INTO `cards_migration` VALUES ('29-02', 'masgeek', 'migrations/changelog/29-03-create-user-roles-table.xml', '2023-08-31 10:49:19', 3, 'EXECUTED', '8:1f7b896d9d0d51867e4fdcf302a834d1', 'createTable tableName=user_roles', '', NULL, '4.20.0', NULL, NULL, '3478959598');
INSERT INTO `cards_migration` VALUES ('29-03', 'masgeek', 'migrations/changelog/29-03-insert-into-user-roles-table.xml', '2023-08-31 10:49:19', 4, 'EXECUTED', '8:6feeb6cdbeed5fa79216e2edcc3e5fb3', 'insert tableName=user_roles; insert tableName=user_roles', '', NULL, '4.20.0', NULL, NULL, '3478959598');
INSERT INTO `cards_migration` VALUES ('29-04', 'masgeek', 'migrations/changelog/29-04-add-user-role-fk-to-users-table.xml', '2023-08-31 10:49:19', 5, 'EXECUTED', '8:1a64c2433326ef38bb7b16f3ed34f218', 'addForeignKeyConstraint baseTableName=users, constraintName=fk-user-role, referencedTableName=user_roles', '', NULL, '4.20.0', NULL, NULL, '3478959598');
INSERT INTO `cards_migration` VALUES ('31-01', 'masgeek', 'migrations/changelog/31-01-insert-test-user.xml', '2023-08-31 11:02:11', 6, 'EXECUTED', '8:5646f400048fd17be4148d0ac58d6c30', 'insert tableName=users; insert tableName=users', '', NULL, '4.20.0', NULL, NULL, '3479731305');

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
INSERT INTO `user_roles` VALUES ('ADMIN', '2023-08-31 10:49:19', '2023-08-31 10:49:19');
INSERT INTO `user_roles` VALUES ('MEMBER', '2023-08-31 10:49:19', '2023-08-31 10:49:19');

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
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'test@gmail.com', 'Test', 'User', 'ADMIN', '$2a$10$eAmKyH0ssFnORcJmgCqIk.ay5WUOVQ70/lsBZI8GLorVTMfzVrPkW', '2023-08-31 11:02:11', '2023-08-31 11:02:11');
INSERT INTO `users` VALUES (2, 'member@gmail.com', 'Member', 'User', 'MEMBER', '$2a$10$eAmKyH0ssFnORcJmgCqIk.ay5WUOVQ70/lsBZI8GLorVTMfzVrPkW', '2023-08-31 11:02:11', '2023-08-31 11:02:11');

SET FOREIGN_KEY_CHECKS = 1;
