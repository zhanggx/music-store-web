/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : music_store

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 30/11/2020 13:58:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for album
-- ----------------------------
DROP TABLE IF EXISTS `album`;
CREATE TABLE `album`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '专辑名称',
  `picture_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '专辑封面',
  `music_count` int(0) NULL DEFAULT 0 COMMENT '音乐数',
  `description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '说明',
  `publish_time` date NULL DEFAULT NULL COMMENT '发行时间',
  `singer_id` int(0) NULL DEFAULT NULL COMMENT '歌手ID',
  `theme_id` int(0) NULL DEFAULT NULL COMMENT '专辑类型id',
  `time_stamp` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of album
-- ----------------------------
INSERT INTO `album` VALUES (1, '一起走过的日子', '/images/yqzgdrz.jpg', 3, '刘天王在1991年推出的一张专辑，也是他的成名专辑，收录了脍炙人口的歌曲，如今雨果以新技术LPCD1630对外加工，使音质更上一层楼。想当年刘天王就是凭借此张专辑而一炮而红。主打歌“一起走过的日子”早已唱的街知巷闻了。那个MV也充满悲剧色彩。这张专辑的每一首歌基本都是刘天王出演的电影、电视剧的主题曲或者插曲，相信看过这些电影或电视的朋友会有多分熟悉和亲切，整只大碟可听性很高', '1991-01-01', 1, 2, '2020-10-05 10:47:37');
INSERT INTO `album` VALUES (2, '爱不完', '/images/aibuwan.jpg', 11, '《爱不完》是刘德华演唱的粤语专辑，1991.02.11由宝艺星公司发行，当中收录了包含同名经典歌曲《爱不完》在内的11首单曲', '1991-02-11', 1, 2, '2020-10-05 13:37:59');
INSERT INTO `album` VALUES (3, '如果你是我的传说', '/images/rgnswdcs.jpg', 11, '几乎是《可不可以》的翻版：不仅十首歌有八首相同，而且这个专辑也和《可不可以》在香港一样，在台湾掀起热潮，主打歌《如果你是我的传说》至今仍是各卡拉OK厅的必备歌曲。凭着此专辑的成功，刘德华当选1990年度台湾十大最受欢迎歌手第一名，并垄断该奖项达十年之久！极其经典的专辑！', '1990-06-01', 1, 2, '2020-10-24 16:05:53');

-- ----------------------------
-- Table structure for music
-- ----------------------------
DROP TABLE IF EXISTS `music`;
CREATE TABLE `music`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `album_id` int(0) NOT NULL COMMENT '专辑id',
  `singer_id` int(0) NULL DEFAULT NULL COMMENT '歌手Id',
  `time_length` int(0) NULL DEFAULT 0 COMMENT '时长，单位秒',
  `file_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件链接',
  `file_size` int(0) NULL DEFAULT NULL COMMENT '文件长度',
  `description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `recommend_index` int(0) NOT NULL DEFAULT 0 COMMENT '0为未推荐，大于0为推荐的排序，按小到大排序',
  `time_stamp` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `ix_music_recommend`(`recommend_index`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of music
-- ----------------------------
INSERT INTO `music` VALUES (1, '一起走过的日子', 1, 1, 233, '/music/ldh_yqzgdrz.mp3', 3753107, '法大师傅士大夫', 1, '2020-10-05 13:48:17');
INSERT INTO `music` VALUES (2, '如果你是我的传说', 3, 1, 12, NULL, 0, 'vvv', 2, '2020-10-24 18:48:08');
INSERT INTO `music` VALUES (3, '可不可以', 3, 1, 221, NULL, 0, '给个给个法国复读', 3, '2020-10-24 18:48:17');
INSERT INTO `music` VALUES (5, '我的心不流泪', 3, 1, 0, NULL, NULL, NULL, 0, '2020-10-24 18:48:42');
INSERT INTO `music` VALUES (6, '找一个情人', 3, 1, 0, NULL, NULL, NULL, 5, '2020-10-24 18:48:52');
INSERT INTO `music` VALUES (7, '难免有错', 3, 1, 0, NULL, NULL, NULL, 0, '2020-10-24 18:49:04');
INSERT INTO `music` VALUES (8, '某年冬季', 3, 1, 0, NULL, NULL, NULL, 0, '2020-10-24 18:50:13');
INSERT INTO `music` VALUES (9, '关于你我的事', 3, 1, 0, NULL, NULL, NULL, 0, '2020-10-24 18:50:29');
INSERT INTO `music` VALUES (10, '握紧你的手', 3, 1, 0, NULL, NULL, NULL, 0, '2020-10-24 18:50:35');
INSERT INTO `music` VALUES (11, '一颗心换你一支舞', 3, 1, 0, NULL, NULL, NULL, 4, '2020-10-24 18:50:44');
INSERT INTO `music` VALUES (12, '呼呼呼', 3, 0, 56666, '/music/17_1605775566574.wav', 126976, '欢迎欢迎有', 0, '2020-11-19 16:43:22');

-- ----------------------------
-- Table structure for singer
-- ----------------------------
DROP TABLE IF EXISTS `singer`;
CREATE TABLE `singer`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '歌手姓名',
  `picture_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '歌手照片',
  `birthday` date NULL DEFAULT NULL COMMENT '生日',
  `description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `time_stamp` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of singer
-- ----------------------------
INSERT INTO `singer` VALUES (1, '刘德华', '/images/ldh.jpg', '1961-09-27', '刘德华（Andy Lau），1961年9月27日出生于中国香港，籍贯广东新会  ，华语影视男演员、歌手、制片人、作词人。', '2020-10-05 10:45:17');
INSERT INTO `singer` VALUES (2, '张学友', '/images/zxy.jpg', '2020-11-01', '张学友（Jacky Cheung），1961年7月10日出生于中国香港，祖籍天津市，华语流行乐男歌手、演员，毕业于香港崇文英文书院。', '2020-10-05 13:34:40');
INSERT INTO `singer` VALUES (8, '你那句啥意思', '/images/img_5739.jpg', '2020-11-18', '斤斤计较你那你看', '2020-11-18 19:31:50');
INSERT INTO `singer` VALUES (9, '几年级', '/images/mmexport1601951236347_1605699479290.jpg', '2020-11-18', '还回家', '2020-11-18 19:33:53');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `account` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(38) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `mobile_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `status` bit(1) NOT NULL DEFAULT b'1' COMMENT '状态',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `time_stamp` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '74bd427e70bfda9b5d8c34ff2ae5e696', '系统管理员', NULL, b'1', NULL, '2019-07-16 17:35:14');
INSERT INTO `sys_user` VALUES (2, 'hans', '5560c2db07b8a39c9a79a40659961add', '汉斯', '13688847757', b'0', NULL, '2019-09-26 11:55:38');

-- ----------------------------
-- Table structure for theme
-- ----------------------------
DROP TABLE IF EXISTS `theme`;
CREATE TABLE `theme`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of theme
-- ----------------------------
INSERT INTO `theme` VALUES (1, 'Classical', '古典音乐');
INSERT INTO `theme` VALUES (2, 'Pop', '流行音乐');
INSERT INTO `theme` VALUES (3, 'Blues', '蓝调歌曲');
INSERT INTO `theme` VALUES (4, 'Rock & Roll', '摇滚乐');
INSERT INTO `theme` VALUES (5, 'Jazz', '爵士乐');

SET FOREIGN_KEY_CHECKS = 1;
