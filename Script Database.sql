/*
 Navicat Premium Data Transfer

 Source Server         : Postgre
 Source Server Type    : PostgreSQL
 Source Server Version : 130002
 Source Host           : localhost:5432
 Source Catalog        : jewelry
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 130002
 File Encoding         : 65001

 Date: 06/09/2021 21:42:45
*/


-- ----------------------------
-- Sequence structure for jewelry_cart_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."jewelry_cart_id_seq" CASCADE;
CREATE SEQUENCE "public"."jewelry_cart_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER SEQUENCE "public"."jewelry_cart_id_seq" OWNER TO "postgres";

-- ----------------------------
-- Sequence structure for jewelry_cart_items_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."jewelry_cart_items_id_seq" CASCADE;
CREATE SEQUENCE "public"."jewelry_cart_items_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER SEQUENCE "public"."jewelry_cart_items_id_seq" OWNER TO "postgres";

-- ----------------------------
-- Sequence structure for jewelry_customer_device_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."jewelry_customer_device_id_seq" CASCADE;
CREATE SEQUENCE "public"."jewelry_customer_device_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER SEQUENCE "public"."jewelry_customer_device_id_seq" OWNER TO "postgres";

-- ----------------------------
-- Sequence structure for jewelry_customer_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."jewelry_customer_id_seq" CASCADE;
CREATE SEQUENCE "public"."jewelry_customer_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER SEQUENCE "public"."jewelry_customer_id_seq" OWNER TO "postgres";

-- ----------------------------
-- Sequence structure for jewelry_order_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."jewelry_order_id_seq" CASCADE;
CREATE SEQUENCE "public"."jewelry_order_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER SEQUENCE "public"."jewelry_order_id_seq" OWNER TO "postgres";

-- ----------------------------
-- Sequence structure for jewelry_order_item_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."jewelry_order_item_id_seq" CASCADE;
CREATE SEQUENCE "public"."jewelry_order_item_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER SEQUENCE "public"."jewelry_order_item_id_seq" OWNER TO "postgres";

-- ----------------------------
-- Sequence structure for jewelry_product_category_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."jewelry_product_category_id_seq" CASCADE;
CREATE SEQUENCE "public"."jewelry_product_category_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER SEQUENCE "public"."jewelry_product_category_id_seq" OWNER TO "postgres";

-- ----------------------------
-- Sequence structure for jewelry_product_gold_type_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."jewelry_product_gold_type_id_seq" CASCADE;
CREATE SEQUENCE "public"."jewelry_product_gold_type_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER SEQUENCE "public"."jewelry_product_gold_type_id_seq" OWNER TO "postgres";

-- ----------------------------
-- Sequence structure for jewelry_product_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."jewelry_product_id_seq" CASCADE;
CREATE SEQUENCE "public"."jewelry_product_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER SEQUENCE "public"."jewelry_product_id_seq" OWNER TO "postgres";

-- ----------------------------
-- Sequence structure for jewelry_product_images_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."jewelry_product_images_id_seq" CASCADE;
CREATE SEQUENCE "public"."jewelry_product_images_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER SEQUENCE "public"."jewelry_product_images_id_seq" OWNER TO "postgres";

-- ----------------------------
-- Sequence structure for jewelry_refreshtoken_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."jewelry_refreshtoken_id_seq" CASCADE;
CREATE SEQUENCE "public"."jewelry_refreshtoken_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER SEQUENCE "public"."jewelry_refreshtoken_id_seq" OWNER TO "postgres";

-- ----------------------------
-- Sequence structure for jewelry_role_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."jewelry_role_id_seq" CASCADE;
CREATE SEQUENCE "public"."jewelry_role_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER SEQUENCE "public"."jewelry_role_id_seq" OWNER TO "postgres";

-- ----------------------------
-- Sequence structure for jewelry_supplier_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."jewelry_supplier_id_seq" CASCADE;
CREATE SEQUENCE "public"."jewelry_supplier_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER SEQUENCE "public"."jewelry_supplier_id_seq" OWNER TO "postgres";

-- ----------------------------
-- Table structure for jewelry_cart
-- ----------------------------
DROP TABLE IF EXISTS "public"."jewelry_cart" CASCADE;
CREATE TABLE "public"."jewelry_cart" (
  "id" int8 NOT NULL GENERATED BY DEFAULT AS IDENTITY (
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 8
),
  "created_at" timestamp(6) NOT NULL,
  "created_by" varchar(255) COLLATE "pg_catalog"."default",
  "updated_at" timestamp(6) NOT NULL,
  "updated_by" varchar(255) COLLATE "pg_catalog"."default",
  "version" int4 NOT NULL,
  "total" float8,
  "customer_id" int8
)
;
ALTER TABLE "public"."jewelry_cart" OWNER TO "postgres";

-- ----------------------------
-- Records of jewelry_cart
-- ----------------------------
BEGIN;
INSERT INTO "public"."jewelry_cart" VALUES (1, '2021-09-06 01:03:55.588', 'anonymousUser', '2021-09-06 01:03:55.588', 'anonymousUser', 0, 0, 1);
INSERT INTO "public"."jewelry_cart" VALUES (2, '2021-09-06 01:06:23.731', 'anonymousUser', '2021-09-06 01:06:23.731', 'anonymousUser', 0, 0, 2);
INSERT INTO "public"."jewelry_cart" VALUES (3, '2021-09-06 01:07:57.659', 'anonymousUser', '2021-09-06 01:07:57.659', 'anonymousUser', 0, 0, 3);
INSERT INTO "public"."jewelry_cart" VALUES (4, '2021-09-06 01:09:13.711', 'anonymousUser', '2021-09-06 01:09:13.711', 'anonymousUser', 0, 0, 4);
INSERT INTO "public"."jewelry_cart" VALUES (5, '2021-09-06 01:10:02.371', 'anonymousUser', '2021-09-06 01:10:02.371', 'anonymousUser', 0, 0, 5);
INSERT INTO "public"."jewelry_cart" VALUES (6, '2021-09-06 01:10:57.453', 'anonymousUser', '2021-09-06 01:10:57.453', 'anonymousUser', 0, 0, 6);
INSERT INTO "public"."jewelry_cart" VALUES (7, '2021-09-06 01:12:03.582', 'anonymousUser', '2021-09-06 01:12:03.582', 'anonymousUser', 0, 0, 7);
COMMIT;

-- ----------------------------
-- Table structure for jewelry_cart_items
-- ----------------------------
DROP TABLE IF EXISTS "public"."jewelry_cart_items" CASCADE;
CREATE TABLE "public"."jewelry_cart_items" (
  "id" int8 NOT NULL GENERATED BY DEFAULT AS IDENTITY (
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
),
  "created_at" timestamp(6) NOT NULL,
  "created_by" varchar(255) COLLATE "pg_catalog"."default",
  "updated_at" timestamp(6) NOT NULL,
  "updated_by" varchar(255) COLLATE "pg_catalog"."default",
  "version" int4 NOT NULL,
  "quantity" int4,
  "cart_id" int8,
  "product_id" int8
)
;
ALTER TABLE "public"."jewelry_cart_items" OWNER TO "postgres";

-- ----------------------------
-- Records of jewelry_cart_items
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for jewelry_customer
-- ----------------------------
DROP TABLE IF EXISTS "public"."jewelry_customer" CASCADE;
CREATE TABLE "public"."jewelry_customer" (
  "id" int8 NOT NULL GENERATED BY DEFAULT AS IDENTITY (
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 8
),
  "created_at" timestamp(6) NOT NULL,
  "created_by" varchar(255) COLLATE "pg_catalog"."default",
  "updated_at" timestamp(6) NOT NULL,
  "updated_by" varchar(255) COLLATE "pg_catalog"."default",
  "version" int4 NOT NULL,
  "active" bool NOT NULL,
  "address" varchar(100) COLLATE "pg_catalog"."default",
  "birthday" date,
  "email" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "full_name" varchar(50) COLLATE "pg_catalog"."default",
  "gender" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "password" varchar(255) COLLATE "pg_catalog"."default",
  "phone_number" varchar(255) COLLATE "pg_catalog"."default"
)
;
ALTER TABLE "public"."jewelry_customer" OWNER TO "postgres";

-- ----------------------------
-- Records of jewelry_customer
-- ----------------------------
BEGIN;
INSERT INTO "public"."jewelry_customer" VALUES (1, '2021-09-06 01:03:55.566', 'anonymousUser', '2021-09-06 01:03:55.566', 'anonymousUser', 0, 't', 'ADMIN ADMIN ADMIN ADMIN ADMIN', '2021-09-06', 'admin@gmail.com', 'ADMIN', 'MALE', '$2a$10$vcQhjaDFpq7Qz.vvUhBqL.v0CDh2gNey59FnqZABZfFPy/FHrVatu', '0000000000');
INSERT INTO "public"."jewelry_customer" VALUES (2, '2021-09-06 01:06:23.727', 'anonymousUser', '2021-09-06 01:06:23.727', 'anonymousUser', 0, 't', 'Đường số 7, P.9, Q.12, TP.Nha Trang', '1994-12-01', 'email-1@gmail.com', 'Trần Văn A', 'MALE', '$2a$10$dn7jVKk7qJoHEdDymfY7eemZaR3rYK8z1xGFGBj766yx12mu8fsOG', '0987654545');
INSERT INTO "public"."jewelry_customer" VALUES (3, '2021-09-06 01:07:57.655', 'anonymousUser', '2021-09-06 01:07:57.655', 'anonymousUser', 0, 't', 'Đường số 12, P.10, Q.6, TP.Hồ Chí Minh', '2000-11-01', 'email-2@gmail.com', 'Nguyễn Thị A', 'FEMALE', '$2a$10$9.FKlpgQr04LAPdwDwWBGO7.xjLp.wF5SZ8zXE/rnXJh.hXvfvM/K', '0987612545');
INSERT INTO "public"."jewelry_customer" VALUES (4, '2021-09-06 01:09:13.707', 'anonymousUser', '2021-09-06 01:09:13.707', 'anonymousUser', 0, 't', 'Đường số 100, P.1, Q.1, TP.Cần Thơ', '2001-06-12', 'email-3@gmail.com', 'Lê Thị G', 'FEMALE', '$2a$10$W8zWNGTmhY8x5BIkHPvuwOOuYdcCBI1PU5/nf.kDGPwUXaAYoJOrG', '0912612545');
INSERT INTO "public"."jewelry_customer" VALUES (5, '2021-09-06 01:10:02.367', 'anonymousUser', '2021-09-06 01:10:02.367', 'anonymousUser', 0, 't', 'Đường số 300, P.10, Q.10, TP.Vĩnh Long', '2001-06-12', 'email-4@gmail.com', 'Nguyễn Văn Hết', 'MALE', '$2a$10$3NaG1hsR/PjBECC5m5knd.dvG0hBxsNDZUnVUFtxuhnTglavabjjG', '0212612545');
INSERT INTO "public"."jewelry_customer" VALUES (6, '2021-09-06 01:10:57.448', 'anonymousUser', '2021-09-06 01:10:57.448', 'anonymousUser', 0, 't', 'Đường số 600, P.120, Q.8, TP.Hồ Chí Minh', '2004-01-11', 'email-5@gmail.com', 'Nguyễn Văn Còn', 'UNKNOWN', '$2a$10$ZneHVn2f1sD14vetnMChSec8WSoshlbSrR32XLI3s/258taQtM1/K', '0212992545');
INSERT INTO "public"."jewelry_customer" VALUES (7, '2021-09-06 01:12:03.579', 'anonymousUser', '2021-09-06 01:12:03.579', 'anonymousUser', 0, 't', 'Đường số 999, P.5, Q.1, TP.Hồ Chí Minh', '1992-06-20', 'email-6@gmail.com', 'Mạc Thị Bưởi', 'FEMALE', '$2a$10$3FrXsPiwmMyuDSftFyaqbONmDm/ToCMaysEZ./E25pjAj9sRNyS52', '0222992545');
COMMIT;

-- ----------------------------
-- Table structure for jewelry_customer_device
-- ----------------------------
DROP TABLE IF EXISTS "public"."jewelry_customer_device" CASCADE;
CREATE TABLE "public"."jewelry_customer_device" (
  "id" int8 NOT NULL GENERATED BY DEFAULT AS IDENTITY (
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 6
),
  "device_id" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "device_type" varchar(255) COLLATE "pg_catalog"."default",
  "is_refresh_active" bool,
  "customer_id" int8 NOT NULL
)
;
ALTER TABLE "public"."jewelry_customer_device" OWNER TO "postgres";

-- ----------------------------
-- Records of jewelry_customer_device
-- ----------------------------
BEGIN;
INSERT INTO "public"."jewelry_customer_device" VALUES (5, 'string', 'string', 't', 1);
COMMIT;

-- ----------------------------
-- Table structure for jewelry_customer_roles
-- ----------------------------
DROP TABLE IF EXISTS "public"."jewelry_customer_roles" CASCADE;
CREATE TABLE "public"."jewelry_customer_roles" (
  "customer_id" int8 NOT NULL,
  "role_id" int8 NOT NULL
)
;
ALTER TABLE "public"."jewelry_customer_roles" OWNER TO "postgres";

-- ----------------------------
-- Records of jewelry_customer_roles
-- ----------------------------
BEGIN;
INSERT INTO "public"."jewelry_customer_roles" VALUES (2, 2);
INSERT INTO "public"."jewelry_customer_roles" VALUES (3, 2);
INSERT INTO "public"."jewelry_customer_roles" VALUES (4, 2);
INSERT INTO "public"."jewelry_customer_roles" VALUES (5, 2);
INSERT INTO "public"."jewelry_customer_roles" VALUES (6, 2);
INSERT INTO "public"."jewelry_customer_roles" VALUES (7, 2);
INSERT INTO "public"."jewelry_customer_roles" VALUES (1, 1);
COMMIT;

-- ----------------------------
-- Table structure for jewelry_order
-- ----------------------------
DROP TABLE IF EXISTS "public"."jewelry_order" CASCADE;
CREATE TABLE "public"."jewelry_order" (
  "id" int8 NOT NULL GENERATED BY DEFAULT AS IDENTITY (
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
),
  "created_at" timestamp(6) NOT NULL,
  "created_by" varchar(255) COLLATE "pg_catalog"."default",
  "updated_at" timestamp(6) NOT NULL,
  "updated_by" varchar(255) COLLATE "pg_catalog"."default",
  "version" int4 NOT NULL,
  "address" varchar(100) COLLATE "pg_catalog"."default",
  "content" varchar(500) COLLATE "pg_catalog"."default",
  "email" varchar(255) COLLATE "pg_catalog"."default",
  "full_name" varchar(50) COLLATE "pg_catalog"."default",
  "phone_number" varchar(255) COLLATE "pg_catalog"."default",
  "status" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "total" float8 NOT NULL,
  "customer_id" int8 NOT NULL
)
;
ALTER TABLE "public"."jewelry_order" OWNER TO "postgres";

-- ----------------------------
-- Records of jewelry_order
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for jewelry_order_item
-- ----------------------------
DROP TABLE IF EXISTS "public"."jewelry_order_item" CASCADE;
CREATE TABLE "public"."jewelry_order_item" (
  "id" int8 NOT NULL GENERATED BY DEFAULT AS IDENTITY (
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
),
  "created_at" timestamp(6) NOT NULL,
  "created_by" varchar(255) COLLATE "pg_catalog"."default",
  "updated_at" timestamp(6) NOT NULL,
  "updated_by" varchar(255) COLLATE "pg_catalog"."default",
  "version" int4 NOT NULL,
  "price" float8,
  "quantity" int4,
  "order_id" int8,
  "product_id" int8
)
;
ALTER TABLE "public"."jewelry_order_item" OWNER TO "postgres";

-- ----------------------------
-- Records of jewelry_order_item
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for jewelry_product
-- ----------------------------
DROP TABLE IF EXISTS "public"."jewelry_product" CASCADE;
CREATE TABLE "public"."jewelry_product" (
  "id" int8 NOT NULL GENERATED BY DEFAULT AS IDENTITY (
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 44
),
  "created_at" timestamp(6) NOT NULL,
  "created_by" varchar(255) COLLATE "pg_catalog"."default",
  "updated_at" timestamp(6) NOT NULL,
  "updated_by" varchar(255) COLLATE "pg_catalog"."default",
  "version" int4 NOT NULL,
  "avatar" varchar(255) COLLATE "pg_catalog"."default",
  "cost_price" float8,
  "description" varchar(500) COLLATE "pg_catalog"."default",
  "gold_weight" float8,
  "name" varchar(100) COLLATE "pg_catalog"."default",
  "price" float8,
  "quantity" int4,
  "sku" varchar(10) COLLATE "pg_catalog"."default",
  "slug" varchar(255) COLLATE "pg_catalog"."default",
  "category_id" int8 NOT NULL,
  "gold_type_id" int8 NOT NULL,
  "supplier_id" int8 NOT NULL
)
;
ALTER TABLE "public"."jewelry_product" OWNER TO "postgres";

-- ----------------------------
-- Records of jewelry_product
-- ----------------------------
BEGIN;
INSERT INTO "public"."jewelry_product" VALUES (1, '2021-09-06 01:46:58.114', 'admin@gmail.com', '2021-09-06 01:46:58.114', 'admin@gmail.com', 0, '1UoGmaqoQQgNuFPCzy21QfX0ZUTsi3nfd', 30000, '<p>Loại đá : Diamond</p><p><br></p>', 6, 'Nhẫn Vàng trắng Ý 18K đính đá CZ', 40000, 20, 'N10001', 'nhan-vang-trang-y-18k-dinh-da-cz-n10001', 1, 3, 1);
INSERT INTO "public"."jewelry_product" VALUES (2, '2021-09-06 07:54:24.758', 'admin@gmail.com', '2021-09-06 07:54:24.758', 'admin@gmail.com', 0, '1QQTS2IeIoK4DcJLo_rm0-GQtAlk3JQ9o', 15000, '<p><br></p>', 6, 'Nhẫn Vàng trắng Ý 18K', 20000, 6, 'N10002', 'nhan-vang-trang-y-18k-n10002', 1, 2, 2);
INSERT INTO "public"."jewelry_product" VALUES (3, '2021-09-06 08:00:41.236', 'admin@gmail.com', '2021-09-06 08:00:41.236', 'admin@gmail.com', 0, '1hsEy-BqWerxzqfSrjWJkycx8UWGgEjqc', 10000, '<p>Loại đá chính : Topaz</p>', 12, 'Nhẫn Vàng trắng Ý đính đá Topaz', 15000, 10, 'N10003', 'nhan-vang-trang-y-dinh-da-topaz-n10003', 1, 3, 5);
INSERT INTO "public"."jewelry_product" VALUES (4, '2021-09-06 08:08:35.525', 'admin@gmail.com', '2021-09-06 08:08:35.525', 'admin@gmail.com', 0, '1-nOtrwX36h7hcCZelkoDCNuCrKgpGJhl', 50000, '<p>Loại đá chính : Diamond</p><p>Cut (Giác cắt/ Hình dạng kim cương) : 57</p><p>Carat (Kích thước đá chính-tham khảo) : 4.3 ly</p><p><span style="color: rgb(40, 40, 40);">Color (Màu sắc/ Nước kim cương) : F</span></p><p><span style="color: rgb(40, 40, 40);">Clarity (Độ tinh khiết) : SI1</span></p><p><span style="color: rgb(40, 40, 40);">Giấy kiểm định : Có</span></p>', 16, 'Nhẫn Kim cương Vàng trắng 18K', 60000, 20, 'N10004', 'nhan-kim-cuong-vang-trang-18k-n10004', 1, 3, 4);
INSERT INTO "public"."jewelry_product" VALUES (5, '2021-09-06 08:12:17.236', 'admin@gmail.com', '2021-09-06 08:12:17.236', 'admin@gmail.com', 0, '1m3hBE2h3IT9BKtrGyJZSmYyXs3N4vPRI', 30000, '<p>Loại đá chính : Diamond</p><p>Cut (Giác cắt/ Hình dạng kim cương) : 57</p><p>Carat (Kích thước đá chính-tham khảo) : 5.2 ly</p><p>Color (Màu sắc/ Nước kim cương) : D</p><p>Clarity (Độ tinh khiết) : SI1</p><p>Giấy kiểm định : Có</p>', 5, 'Nhẫn Kim cương Vàng trắng 14K', 40000, 100, 'N10005', 'nhan-kim-cuong-vang-trang-14k-n10005', 1, 1, 5);
INSERT INTO "public"."jewelry_product" VALUES (6, '2021-09-06 08:16:12.957', 'admin@gmail.com', '2021-09-06 08:16:53.947', 'admin@gmail.com', 1, '1s5LXYDHoXt22Zr0rQdAVN0Hkr4DW4bsz', 7000, '<p><br></p>', 7, 'Nhẫn nữ Vàng 14K', 12000, 10, 'N10006', 'nhan-nu-vang-14k-n10006', 1, 1, 3);
INSERT INTO "public"."jewelry_product" VALUES (7, '2021-09-06 08:23:31.58', 'admin@gmail.com', '2021-09-06 08:23:31.58', 'admin@gmail.com', 0, '176G7cH2StOBDDJyQpzjGiLp1TFiLgxpz', 30000, '<p>Loại đá chính : <span style="color: rgb(40, 40, 40);">Southsea Pearl</span></p><p><span style="color: rgb(40, 40, 40);">Loại đá phụ : Diamond</span></p><p><span style="color: rgb(40, 40, 40);">Kích thước đá tham khảo : 9.5 ly</span></p>', 9, 'Nhẫn Vàng 18K đính Ngọc trai Southsea', 35000, 100, 'N10007', 'nhan-vang-18k-dinh-ngoc-trai-southsea-n10007', 1, 3, 4);
INSERT INTO "public"."jewelry_product" VALUES (8, '2021-09-06 08:29:07.869', 'admin@gmail.com', '2021-09-06 08:29:07.869', 'admin@gmail.com', 0, '1T7hR8VUYyZ-cdaQ27tvAytwUGUA94rqS', 15000, '<p><br></p>', 8, 'Nhẫn Vàng Ý 18K', 20000, 10, 'N10008', 'nhan-vang-y-18k-n10008', 1, 3, 1);
INSERT INTO "public"."jewelry_product" VALUES (9, '2021-09-06 08:32:53.572', 'admin@gmail.com', '2021-09-06 08:32:53.572', 'admin@gmail.com', 0, '1xzmb8jfndfIBS5Nc48MjLEL5Ukpvuiuj', 130000, '<p>Loại đá chính : Diamond</p><p>Cut (Giác cắt/ Hình dạng kim cương) : 57</p><p>Carat (Kích thước đá chính-tham khảo) : 6.3 ly</p><p>Color (Màu sắc/ Nước kim cương) : D</p><p>Clarity (Độ tinh khiết) : SI1</p><p>Giấy kiểm định : Có</p>', 20, 'Nhẫn Kim cương Vàng 18K', 170000, 10, 'N10009', 'nhan-kim-cuong-vang-18k-n10009', 1, 3, 3);
INSERT INTO "public"."jewelry_product" VALUES (10, '2021-09-06 08:35:38.557', 'admin@gmail.com', '2021-09-06 08:35:38.557', 'admin@gmail.com', 0, '1iyi9Oy4fjM970_F2xgzn8Tvm9tEloCMi', 50000, '<p>Loại đá chính : Xoàn mỹ</p>', 23, 'Nhẫn nam Vàng trắng Ý 18K đính đá CZ hình họa tiết rồng', 60000, 10, 'N10010', 'nhan-nam-vang-trang-y-18k-dinh-da-cz-hinh-hoa-tiet-rong-n10010', 1, 3, 4);
INSERT INTO "public"."jewelry_product" VALUES (11, '2021-09-06 08:39:03.34', 'admin@gmail.com', '2021-09-06 08:39:03.34', 'admin@gmail.com', 0, '1jaxzLTOFHTSZw4iMTaSzpOFB8yFSGDAX', 30000, '<p>Loại đá chính : Diamond</p><p>Cut (Giác cắt/ Hình dạng kim cương) : 57</p><p>Carat (Kích thước đá chính-tham khảo) : 4.3 ly</p><p>Color (Màu sắc/ Nước kim cương) : F</p><p>Clarity (Độ tinh khiết) : SI1</p><p>Giấy kiểm định : Có</p>', 18, 'Nhẫn nam Kim Cương Vàng 18K', 40000, 20, 'N10011', 'nhan-nam-kim-cuong-vang-18k-n10011', 1, 3, 3);
INSERT INTO "public"."jewelry_product" VALUES (12, '2021-09-06 08:41:17.339', 'admin@gmail.com', '2021-09-06 08:41:17.339', 'admin@gmail.com', 0, '1_08o1O7gEH923al0DqQLPsrh6hANnCVh', 140000, '<p>Loại đá chính : Diamond</p><p>Cut (Giác cắt/ Hình dạng kim cương) : 57</p><p>Carat (Kích thước đá chính-tham khảo) : 6.3 ly</p><p>Color (Màu sắc/ Nước kim cương) : D</p><p>Clarity (Độ tinh khiết) : SI1</p><p>Giấy kiểm định : Có</p>', 23, 'Nhẫn nam Kim cương Vàng 18K ', 160000, 100, 'N10012', 'nhan-nam-kim-cuong-vang-18k-n10012', 1, 3, 4);
INSERT INTO "public"."jewelry_product" VALUES (13, '2021-09-06 08:43:18.07', 'admin@gmail.com', '2021-09-06 08:43:18.07', 'admin@gmail.com', 0, '1E6QtXieyTpO1RFI-fa1LYVoKplZasa-S', 12000, '<p><br></p>', 22, 'Nhẫn nam Vàng 16K đính đá ECZ', 15000, 1000, 'N10013', 'nhan-nam-vang-16k-dinh-da-ecz-n10013', 1, 2, 2);
INSERT INTO "public"."jewelry_product" VALUES (15, '2021-09-06 08:52:19.025', 'admin@gmail.com', '2021-09-06 08:52:19.025', 'admin@gmail.com', 0, '1DIdCldJvCvujBuBzwWqcA9d2xhAI1ILk', 12000, '<p>Loại ngọc trai : <span style="color: rgb(40, 40, 40);">Akoya</span></p>', 6, 'Bông tai vàng trắng 18K đính ngọc trai Akoya', 15000, 10, 'B10002', 'bong-tai-vang-trang-18k-dinh-ngoc-trai-akoya-b10002', 2, 3, 2);
INSERT INTO "public"."jewelry_product" VALUES (16, '2021-09-06 08:54:40.142', 'admin@gmail.com', '2021-09-06 08:54:40.142', 'admin@gmail.com', 0, '1n0QS41NQmSHEXrGr5VMEcL9lkBNv-rVo', 9000, '<p>Không gắn đá</p>', 6, 'Bông tai Kim tiền Vàng trắng Ý 18K', 10000, 10, 'B10003', 'bong-tai-kim-tien-vang-trang-y-18k-b10003', 2, 3, 4);
INSERT INTO "public"."jewelry_product" VALUES (17, '2021-09-06 08:56:40.326', 'admin@gmail.com', '2021-09-06 08:56:40.326', 'admin@gmail.com', 0, '1unuOQeHAWvTMneI5rhrXNV2ZQ7qdqz5a', 18000, '<p><br></p>', 7, 'Bông tai Vàng trắng 14K đính đá Topaz', 20000, 100, 'B10004', 'bong-tai-vang-trang-14k-dinh-da-topaz-b10004', 2, 1, 5);
INSERT INTO "public"."jewelry_product" VALUES (14, '2021-09-06 08:50:04.51', 'admin@gmail.com', '2021-09-06 08:57:46.493', 'admin@gmail.com', 1, '1o-pFvm1YI1ER477oRwFgxnP3IbeE5PxU', 6000, '<p><br></p>', 3, 'Bông tai Vàng trắng 16K đính đá ECZ', 8000, 10, 'B10001', 'bong-tai-vang-trang-16k-dinh-da-ecz-b10001', 2, 2, 1);
INSERT INTO "public"."jewelry_product" VALUES (18, '2021-09-06 09:01:21.542', 'admin@gmail.com', '2021-09-06 09:01:21.542', 'admin@gmail.com', 0, '18Pbw_i0yb7fqnIOT_H0IV3Y695Y29Vrp', 6000, '<p>Kích thước đá chính : 10 ly</p>', 8, 'Bông tai Vàng 18K đính đá CZ', 9000, 100, 'B10005', 'bong-tai-vang-18k-dinh-da-cz-b10005', 2, 3, 4);
INSERT INTO "public"."jewelry_product" VALUES (19, '2021-09-06 09:03:11.179', 'admin@gmail.com', '2021-09-06 09:03:11.179', 'admin@gmail.com', 0, '14DEadedsDOURpspbPh4pgNvMpAXlESvK', 7000, '<p>Loại đá phụ : <span style="color: rgb(40, 40, 40);">ECZ - Swarovski</span></p>', 7, 'Bông tai Vàng trắng 16K đính đá ECZ', 12000, 100, 'B10006', 'bong-tai-vang-trang-16k-dinh-da-ecz-b10006', 2, 2, 4);
INSERT INTO "public"."jewelry_product" VALUES (20, '2021-09-06 09:07:25.551', 'admin@gmail.com', '2021-09-06 09:07:25.551', 'admin@gmail.com', 0, '133ZT_0R5W6Uv7NgpNQluvJdernhbdT-V', 60000, '<p>Loại đá chính : Ruby</p><p>Loại đá phụ : <span style="color: rgb(40, 40, 40);">ECZ - Swarovski</span></p><p><span style="color: rgb(40, 40, 40);">Kích thước đá tham khảo : 12 x 14 mm</span></p>', 30, 'Vòng tay Vàng 18K đính đá Ruby', 70000, 5, 'V10001', 'vong-tay-vang-18k-dinh-da-ruby-v10001', 3, 3, 5);
INSERT INTO "public"."jewelry_product" VALUES (21, '2021-09-06 09:08:48.375', 'admin@gmail.com', '2021-09-06 09:08:48.375', 'admin@gmail.com', 0, '1Tf_mNNxqSUu9Z8ZQ_6ve3WpLRQgzW4Uu', 40000, '<p>Loại đá chính : <span style="color: rgb(40, 40, 40);">Citrine</span></p>', 23, 'Vòng tay Vàng 14K đính đá Citrine', 45000, 5, 'V10002', 'vong-tay-vang-14k-dinh-da-citrine-v10002', 3, 1, 2);
INSERT INTO "public"."jewelry_product" VALUES (22, '2021-09-06 09:10:25.607', 'admin@gmail.com', '2021-09-06 09:10:25.607', 'admin@gmail.com', 0, '14CZhcuFsacfjCft04q_RpZIGXf0E1kvP', 30000, '<p>Loại đá phụ : <span style="color: rgb(40, 40, 40);">ECZ - Swarovski</span></p>', 22, 'Vòng tay Hoa Hồng', 35000, 5, 'V10003', 'vong-tay-hoa-hong-v10003', 3, 2, 1);
INSERT INTO "public"."jewelry_product" VALUES (23, '2021-09-06 09:12:20.297', 'admin@gmail.com', '2021-09-06 09:12:20.297', 'admin@gmail.com', 0, '1EejzGUahmKbPFrXVhoQL7VqlSPXr6wkx', 60000, '<p>Không gắn đá</p>', 33, 'Lắc tay Vàng trắng Ý 18K ', 70000, 5, 'L10001', 'lac-tay-vang-trang-y-18k-l10001', 3, 3, 3);
INSERT INTO "public"."jewelry_product" VALUES (24, '2021-09-06 09:16:50.229', 'admin@gmail.com', '2021-09-06 09:16:50.229', 'admin@gmail.com', 0, '1tCvd1l2HgBSSjQ6drYWO_FccnUTdciYe', 20000, '<p>Không gắn đá</p>', 15, 'Lắc tay Vàng Ý 18K', 25000, 5, 'L10002', 'lac-tay-vang-y-18k-l10002', 3, 3, 3);
INSERT INTO "public"."jewelry_product" VALUES (25, '2021-09-06 09:18:33.996', 'admin@gmail.com', '2021-09-06 09:18:33.996', 'admin@gmail.com', 0, '1C1ikR2s9QzxwApe1lrMl7aRhh-RyQ8h1', 30000, '<p><br></p>', 20, 'Lắc tay Vàng trắng 14K đính đá Topaz', 40000, 5, 'L10003', 'lac-tay-vang-trang-14k-dinh-da-topaz-l10003', 3, 1, 3);
INSERT INTO "public"."jewelry_product" VALUES (26, '2021-09-06 09:22:56.252', 'admin@gmail.com', '2021-09-06 09:22:56.252', 'admin@gmail.com', 0, '1TDhVafvg5TN3POp-VALbbZyVz_HFawOY', 20000, '<p><br></p>', 12, 'Dây chuyền vàng Ý 18K', 30000, 5, 'D10001', 'day-chuyen-vang-y-18k-d10001', 4, 3, 3);
INSERT INTO "public"."jewelry_product" VALUES (27, '2021-09-06 09:24:17.459', 'admin@gmail.com', '2021-09-06 09:24:17.459', 'admin@gmail.com', 0, '1o7yWJ-KuOwuVUDphlUQAVQ70CagvrEZ9', 15000, '<p><br></p>', 12, 'Dây chuyền vàng trắng 14K ', 20000, 5, 'D10002', 'day-chuyen-vang-trang-14k-d10002', 4, 1, 4);
INSERT INTO "public"."jewelry_product" VALUES (28, '2021-09-06 09:26:21.936', 'admin@gmail.com', '2021-09-06 09:26:21.936', 'admin@gmail.com', 0, '1TysIRAuuCQ-uZQf_Sfeh4REwZyTUiK5v', 12000, '<p><br></p>', 16, 'Dây chuyền Vàng Ý 16K', 15000, 5, 'D10003', 'day-chuyen-vang-y-16k-d10003', 4, 2, 3);
INSERT INTO "public"."jewelry_product" VALUES (29, '2021-09-06 09:30:16.598', 'admin@gmail.com', '2021-09-06 09:30:16.598', 'admin@gmail.com', 0, '1TalOqk95dOONLpPRVnNR6f3iJZtafUP6', 3000, '<p>Chưa kèm dây</p>', 4, 'Mặt dây chuyền Vàng 18K đính đá CZ', 4000, 5, 'M10001', 'mat-day-chuyen-vang-18k-dinh-da-cz-m10001', 5, 3, 3);
INSERT INTO "public"."jewelry_product" VALUES (30, '2021-09-06 09:31:45.119', 'admin@gmail.com', '2021-09-06 09:31:45.119', 'admin@gmail.com', 0, '1JBamNqpaZBSj0nqpYKFZHWsowILFY9Gh', 10000, '<p>Chưa kèm dây</p>', 6, 'Mặt dây chuyền Vàng trắng Ý 18K', 12000, 5, 'M10002', 'mat-day-chuyen-vang-trang-y-18k-m10002', 5, 3, 5);
INSERT INTO "public"."jewelry_product" VALUES (31, '2021-09-06 09:33:50.683', 'admin@gmail.com', '2021-09-06 09:33:50.683', 'admin@gmail.com', 0, '1v_Z1faIsgfwRCY1GFaWEMSELsSZgUPE_', 5000, '<p>Chưa kèm dây chuyền</p><p>Kích thước đá chính : 10 ly</p>', 8, 'Mặt dây chuyền Vàng 16K đính đá CZ', 6000, 4, 'M10003', 'mat-day-chuyen-vang-16k-dinh-da-cz-m10003', 5, 2, 4);
INSERT INTO "public"."jewelry_product" VALUES (32, '2021-09-06 09:35:16.008', 'admin@gmail.com', '2021-09-06 09:35:16.008', 'admin@gmail.com', 0, '1Sqy5fVZFsxOOYwt9P0vgXEilX569qTCG', 5000, '<p>Kích thước đá : 8 x 10 ly</p>', 8, 'Mặt dây chuyền Vàng trắng 14K đính đá Topaz', 6000, 5, 'M10004', 'mat-day-chuyen-vang-trang-14k-dinh-da-topaz-m10004', 5, 1, 1);
INSERT INTO "public"."jewelry_product" VALUES (33, '2021-09-06 09:36:46.174', 'admin@gmail.com', '2021-09-06 09:36:46.174', 'admin@gmail.com', 0, '1WMrdeCMt-tnDzFQid2Ho667AFb8hZD5K', 12000, '<p><span style="color: rgb(40, 40, 40);">Carat (Kích thước đá chính-tham khảo) : 3 ly</span></p>', 12, 'Mặt dây chuyền Kim cương Vàng trắng 14K', 15000, 4, 'M10005', 'mat-day-chuyen-kim-cuong-vang-trang-14k-m10005', 5, 1, 2);
INSERT INTO "public"."jewelry_product" VALUES (34, '2021-09-06 09:38:41.84', 'admin@gmail.com', '2021-09-06 09:38:41.84', 'admin@gmail.com', 0, '1OsurJsYuKnzrIYlhtgYGlVV1p1tGzrdQ', 8000, '<p>Loại đá : Topaz</p>', 12, 'Mặt dây chuyền Vàng 16K đính đá Topaz', 9000, 5, 'M10006', 'mat-day-chuyen-vang-16k-dinh-da-topaz-m10006', 5, 2, 3);
INSERT INTO "public"."jewelry_product" VALUES (35, '2021-09-06 09:42:29.136', 'admin@gmail.com', '2021-09-06 09:42:29.136', 'admin@gmail.com', 0, '1I1r9Teqrr-VJc4XRronHP37cWJQa9dEp', 3000, '<p>Loại đá chính : Xoàn mỹ</p>', 3, 'Hạt Charm Me Vàng 18K đính đá ECZ', 4000, 4, 'CH1001', 'hat-charm-me-vang-18k-dinh-da-ecz-ch1001', 6, 3, 4);
INSERT INTO "public"."jewelry_product" VALUES (36, '2021-09-06 09:44:11.397', 'admin@gmail.com', '2021-09-06 09:44:11.397', 'admin@gmail.com', 0, '13xvQKBIYtb5aZZxo77RAny8wXaRcblVC', 10000, '<p>Loại đá chính : <span style="color: rgb(40, 40, 40);">Sythetic</span></p>', 6, 'Hạt charm me Vàng 18K đính đá Suncut', 12000, 5, 'CH1002', 'hat-charm-me-vang-18k-dinh-da-suncut-ch1002', 6, 3, 4);
INSERT INTO "public"."jewelry_product" VALUES (37, '2021-09-06 09:45:30.525', 'admin@gmail.com', '2021-09-06 09:45:30.525', 'admin@gmail.com', 0, '1Lx3_irbuVwhp4L4noE-00RqSn5UxaD52', 8000, '<p><br></p>', 7, 'Hạt Charm Me Vàng trắng Ý 16K', 9000, 5, 'CH1003', 'hat-charm-me-vang-trang-y-16k-ch1003', 6, 2, 3);
INSERT INTO "public"."jewelry_product" VALUES (38, '2021-09-06 09:47:55.829', 'admin@gmail.com', '2021-09-06 09:47:55.829', 'admin@gmail.com', 0, '1g13r25p7DP_PnFxgABjQFSsGyzubSFEd', 10000, '<p>Loại đá chính : <span style="color: rgb(40, 40, 40);">Synthetic</span></p>', 9, 'Hạt Charm Me Vàng trắng Ý  đính đá màu hồng', 12000, 4, 'CH1004', 'hat-charm-me-vang-trang-y-dinh-da-mau-hong-ch1004', 6, 3, 5);
INSERT INTO "public"."jewelry_product" VALUES (39, '2021-09-06 09:49:03.251', 'admin@gmail.com', '2021-09-06 09:49:03.251', 'admin@gmail.com', 0, '1y2J9FEbogQrhMBPRXMsBlgEoJQtGEqkw', 3000, '<p><br></p>', 3, 'Hạt Charm Me Vàng Ý 18K đính đá CZ', 4000, 1, 'CH1005', 'hat-charm-me-vang-y-18k-dinh-da-cz-ch1005', 6, 3, 4);
INSERT INTO "public"."jewelry_product" VALUES (40, '2021-09-06 09:49:52.629', 'admin@gmail.com', '2021-09-06 09:49:52.629', 'admin@gmail.com', 0, '1RPnARSIgXxEgOimzYQRoFm3lyzJtsD-i', 4000, '<p><br></p>', 2, 'Hạt Charm Me Vàng Ý 14K đính đá CZ', 5000, 5, 'CH1006', 'hat-charm-me-vang-y-14k-dinh-da-cz-ch1006', 6, 1, 5);
INSERT INTO "public"."jewelry_product" VALUES (41, '2021-09-06 09:57:34.615', 'admin@gmail.com', '2021-09-06 09:57:34.615', 'admin@gmail.com', 0, '1nuxH2_8buto1P0XvovWprhAAYDdQK8dE', 10000, '<p><span style="color: rgb(51, 51, 51);">“Trong phong thủy, theo thuyết hình khí (hình nào khí nấy), hình dạng của Lộc bình có tác dụng thu và giữ khí rất tốt. Lộc bình khi được trang trí với các hoa văn, đường vân uốn lượn làm tăng thêm sự lưu chuyển và tụ hội của trường năng lượng. Sản phẩm tượng trưng cho sự phát tài phát lộc, may mắn và cát khánh, đồng thời tích trữ và bảo toàn tài sản, của cải cho gia chủ.”</span></p>', 12, 'Bình hoa phú quý', 12000, 4, 'VT1001', 'binh-hoa-phu-quy-vt1001', 7, 5, 4);
INSERT INTO "public"."jewelry_product" VALUES (42, '2021-09-06 09:59:06.45', 'admin@gmail.com', '2021-09-06 09:59:06.45', 'admin@gmail.com', 0, '1903dPK1LwKACAofp7AYrhFhYTLGJCEEu', 40000, '<p><span style="color: rgb(51, 51, 51);">Từ sức vươn mạnh mẽ vượt qua sóng to biển lớn, hình ảnh chiếc thuyền căng buồm no gió được liên tưởng tới việc thuận lợi trong buôn bán, kinh doanh, mang lại tấn tài tấn lộc cho người sở hữu. Tượng Thuyền buồm (Nhất phàm phong thuận) được đặt trên bàn làm việc hoặc gần cửa lối ra vào giúp chủ nhân đón nhận nhiều may mắn, vạn sự như ý.</span></p>', 30, 'Thuận buồm cát tường', 45000, 4, 'VT1002', 'thuan-buom-cat-tuong-vt1002', 7, 5, 3);
INSERT INTO "public"."jewelry_product" VALUES (43, '2021-09-06 10:00:00.321', 'admin@gmail.com', '2021-09-06 10:00:00.321', 'admin@gmail.com', 0, '1JJgST7isr6AvCORU0orWUePjLgYZ6fLA', 15000, '<p><span style="color: rgb(51, 51, 51);">Kim Cầu Van Lợi là một vật phẩm phong thủy được nhiều người ưa chuộng và chọn mua. Bởi vật phẩm này có tác dụng gia tăng khí vận, thuận lợi trong cuộc sống và làm ăn. Quả cầu vàng tượng trưng cho sự thập toàn thập mỹ, hoàn hảo, mang lại may mắn, giúp cho tình yêu, học tập và công việc trở nên tốt đẹp hơn. Không những có thể chế ngự hung khí, tránh được thị phi và bệnh tật, đem lại tài lộc và của cải cho gia chủ.</span></p>', 8, 'Kim cầu vạn lợi', 20000, 4, 'VT1003', 'kim-cau-van-loi-vt1003', 7, 5, 4);
COMMIT;

-- ----------------------------
-- Table structure for jewelry_product_category
-- ----------------------------
DROP TABLE IF EXISTS "public"."jewelry_product_category" CASCADE;
CREATE TABLE "public"."jewelry_product_category" (
  "id" int8 NOT NULL GENERATED BY DEFAULT AS IDENTITY (
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 8
),
  "created_at" timestamp(6) NOT NULL,
  "created_by" varchar(255) COLLATE "pg_catalog"."default",
  "updated_at" timestamp(6) NOT NULL,
  "updated_by" varchar(255) COLLATE "pg_catalog"."default",
  "version" int4 NOT NULL,
  "code" varchar(10) COLLATE "pg_catalog"."default",
  "name" varchar(30) COLLATE "pg_catalog"."default",
  "slug" varchar(255) COLLATE "pg_catalog"."default"
)
;
ALTER TABLE "public"."jewelry_product_category" OWNER TO "postgres";

-- ----------------------------
-- Records of jewelry_product_category
-- ----------------------------
BEGIN;
INSERT INTO "public"."jewelry_product_category" VALUES (1, '2021-09-06 01:16:18.822', 'admin@gmail.com', '2021-09-06 01:16:18.822', 'admin@gmail.com', 0, 'NHAN', 'Nhẫn', 'nhan');
INSERT INTO "public"."jewelry_product_category" VALUES (2, '2021-09-06 01:16:38.548', 'admin@gmail.com', '2021-09-06 01:16:38.548', 'admin@gmail.com', 0, 'BTAI', 'Bông tai', 'bong-tai');
INSERT INTO "public"."jewelry_product_category" VALUES (3, '2021-09-06 01:16:59.676', 'admin@gmail.com', '2021-09-06 01:16:59.676', 'admin@gmail.com', 0, 'LVT', 'Lắc & Vòng tay', 'lac-vong-tay');
INSERT INTO "public"."jewelry_product_category" VALUES (4, '2021-09-06 01:17:22.027', 'admin@gmail.com', '2021-09-06 01:17:22.027', 'admin@gmail.com', 0, 'DAY', 'Dây chuyền', 'day-chuyen');
INSERT INTO "public"."jewelry_product_category" VALUES (5, '2021-09-06 01:17:40.328', 'admin@gmail.com', '2021-09-06 01:17:40.328', 'admin@gmail.com', 0, 'MAT', 'Mặt dây chuyền', 'mat-day-chuyen');
INSERT INTO "public"."jewelry_product_category" VALUES (6, '2021-09-06 01:18:28.334', 'admin@gmail.com', '2021-09-06 01:18:28.334', 'admin@gmail.com', 0, 'CHARM', 'Charm', 'charm');
INSERT INTO "public"."jewelry_product_category" VALUES (7, '2021-09-06 01:20:41.137', 'admin@gmail.com', '2021-09-06 01:20:41.137', 'admin@gmail.com', 0, 'V9999', 'Vàng 9999', 'vang-9999');
COMMIT;

-- ----------------------------
-- Table structure for jewelry_product_gold_type
-- ----------------------------
DROP TABLE IF EXISTS "public"."jewelry_product_gold_type" CASCADE;
CREATE TABLE "public"."jewelry_product_gold_type" (
  "id" int8 NOT NULL GENERATED BY DEFAULT AS IDENTITY (
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 6
),
  "created_at" timestamp(6) NOT NULL,
  "created_by" varchar(255) COLLATE "pg_catalog"."default",
  "updated_at" timestamp(6) NOT NULL,
  "updated_by" varchar(255) COLLATE "pg_catalog"."default",
  "version" int4 NOT NULL,
  "percentage" float8 NOT NULL
)
;
ALTER TABLE "public"."jewelry_product_gold_type" OWNER TO "postgres";

-- ----------------------------
-- Records of jewelry_product_gold_type
-- ----------------------------
BEGIN;
INSERT INTO "public"."jewelry_product_gold_type" VALUES (1, '2021-09-06 01:40:42.32', 'admin@gmail.com', '2021-09-06 01:40:42.32', 'admin@gmail.com', 0, 61);
INSERT INTO "public"."jewelry_product_gold_type" VALUES (2, '2021-09-06 01:40:48.156', 'admin@gmail.com', '2021-09-06 01:40:48.156', 'admin@gmail.com', 0, 68);
INSERT INTO "public"."jewelry_product_gold_type" VALUES (3, '2021-09-06 01:40:57.46', 'admin@gmail.com', '2021-09-06 01:40:57.46', 'admin@gmail.com', 0, 75);
INSERT INTO "public"."jewelry_product_gold_type" VALUES (4, '2021-09-06 01:41:05.028', 'admin@gmail.com', '2021-09-06 01:41:05.028', 'admin@gmail.com', 0, 98);
INSERT INTO "public"."jewelry_product_gold_type" VALUES (5, '2021-09-06 01:41:20.376', 'admin@gmail.com', '2021-09-06 01:41:20.376', 'admin@gmail.com', 0, 99.99);
COMMIT;

-- ----------------------------
-- Table structure for jewelry_product_images
-- ----------------------------
DROP TABLE IF EXISTS "public"."jewelry_product_images" CASCADE;
CREATE TABLE "public"."jewelry_product_images" (
  "id" int8 NOT NULL GENERATED BY DEFAULT AS IDENTITY (
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 84
),
  "created_at" timestamp(6) NOT NULL,
  "created_by" varchar(255) COLLATE "pg_catalog"."default",
  "updated_at" timestamp(6) NOT NULL,
  "updated_by" varchar(255) COLLATE "pg_catalog"."default",
  "version" int4 NOT NULL,
  "g_drive_id" varchar(255) COLLATE "pg_catalog"."default",
  "product_id" int8
)
;
ALTER TABLE "public"."jewelry_product_images" OWNER TO "postgres";

-- ----------------------------
-- Records of jewelry_product_images
-- ----------------------------
BEGIN;
INSERT INTO "public"."jewelry_product_images" VALUES (1, '2021-09-06 01:46:58.124', 'admin@gmail.com', '2021-09-06 01:46:58.124', 'admin@gmail.com', 0, '1cTFmsQEBeTYxvM55fBnP5OuvY2HmWXbz', 1);
INSERT INTO "public"."jewelry_product_images" VALUES (2, '2021-09-06 01:46:58.127', 'admin@gmail.com', '2021-09-06 01:46:58.127', 'admin@gmail.com', 0, '1k7IrWEcyqJPK_CgvLwP4NCEzZedbZoZ2', 1);
INSERT INTO "public"."jewelry_product_images" VALUES (3, '2021-09-06 01:46:58.13', 'admin@gmail.com', '2021-09-06 01:46:58.13', 'admin@gmail.com', 0, '1A2Sq2McbQUFSzcgyYyyBGC-5aUBe0kKo', 1);
INSERT INTO "public"."jewelry_product_images" VALUES (4, '2021-09-06 07:54:24.774', 'admin@gmail.com', '2021-09-06 07:54:24.774', 'admin@gmail.com', 0, '1j0bve03WhXfHH1_SsQiXm99kf0BJ-7AA', 2);
INSERT INTO "public"."jewelry_product_images" VALUES (5, '2021-09-06 07:54:24.778', 'admin@gmail.com', '2021-09-06 07:54:24.778', 'admin@gmail.com', 0, '1zC59C62BTI4WzLl6a0C0e9v-Vr9RgcqH', 2);
INSERT INTO "public"."jewelry_product_images" VALUES (6, '2021-09-06 07:54:24.78', 'admin@gmail.com', '2021-09-06 07:54:24.78', 'admin@gmail.com', 0, '1I_NVrNuOz0nGB3oJJPpmp51n-rlkDm6N', 2);
INSERT INTO "public"."jewelry_product_images" VALUES (7, '2021-09-06 07:54:24.782', 'admin@gmail.com', '2021-09-06 07:54:24.782', 'admin@gmail.com', 0, '1DI7NDP8JhylF5Gfj5PeTVQPlAuipK9-x', 2);
INSERT INTO "public"."jewelry_product_images" VALUES (8, '2021-09-06 08:00:41.241', 'admin@gmail.com', '2021-09-06 08:00:41.241', 'admin@gmail.com', 0, '1CHtHg7MFSpmDe44TTRWtjZ6PsJiMLRF3', 3);
INSERT INTO "public"."jewelry_product_images" VALUES (9, '2021-09-06 08:00:41.244', 'admin@gmail.com', '2021-09-06 08:00:41.244', 'admin@gmail.com', 0, '1UwDbps2SDtRKZN4lbn8yLm-4-hTz0c0I', 3);
INSERT INTO "public"."jewelry_product_images" VALUES (10, '2021-09-06 08:00:41.246', 'admin@gmail.com', '2021-09-06 08:00:41.246', 'admin@gmail.com', 0, '15c3wTu9tnHAMmUUCimXMC678SEh0qvnK', 3);
INSERT INTO "public"."jewelry_product_images" VALUES (11, '2021-09-06 08:08:35.53', 'admin@gmail.com', '2021-09-06 08:08:35.53', 'admin@gmail.com', 0, '18kR1GfVYnocoG4yIqQe7hEyDaFp625O2', 4);
INSERT INTO "public"."jewelry_product_images" VALUES (12, '2021-09-06 08:08:35.532', 'admin@gmail.com', '2021-09-06 08:08:35.532', 'admin@gmail.com', 0, '1jO5UdDvomRzUk3Kj2h6bPwVN-0wr1ijK', 4);
INSERT INTO "public"."jewelry_product_images" VALUES (13, '2021-09-06 08:08:35.536', 'admin@gmail.com', '2021-09-06 08:08:35.536', 'admin@gmail.com', 0, '1zHxchY7tWoW8iX_-6FDHqL2kxYYjfl4F', 4);
INSERT INTO "public"."jewelry_product_images" VALUES (14, '2021-09-06 08:12:17.242', 'admin@gmail.com', '2021-09-06 08:12:17.242', 'admin@gmail.com', 0, '1AXLh77IwCeAaKTH9T_FG6ANJoRHJCrnY', 5);
INSERT INTO "public"."jewelry_product_images" VALUES (15, '2021-09-06 08:12:17.245', 'admin@gmail.com', '2021-09-06 08:12:17.245', 'admin@gmail.com', 0, '13jSP9Un8zFXwS2iMgjzFDMSomGafaezM', 5);
INSERT INTO "public"."jewelry_product_images" VALUES (16, '2021-09-06 08:16:12.961', 'admin@gmail.com', '2021-09-06 08:16:12.961', 'admin@gmail.com', 0, '1KK4tlRLBfN2ptDCM2IY_zhJW7XkpMWgK', 6);
INSERT INTO "public"."jewelry_product_images" VALUES (17, '2021-09-06 08:16:12.963', 'admin@gmail.com', '2021-09-06 08:16:12.963', 'admin@gmail.com', 0, '19msvmhHmG-zC61_GEWSeWTKYIGMeteND', 6);
INSERT INTO "public"."jewelry_product_images" VALUES (18, '2021-09-06 08:23:31.586', 'admin@gmail.com', '2021-09-06 08:23:31.586', 'admin@gmail.com', 0, '1Ur-wn5Xp0wiJp7YVtRfGbBo7YXJSyJAc', 7);
INSERT INTO "public"."jewelry_product_images" VALUES (19, '2021-09-06 08:23:31.589', 'admin@gmail.com', '2021-09-06 08:23:31.589', 'admin@gmail.com', 0, '1wOhu2GIH-rOqs33FJNEuHqS08rxBwgx-', 7);
INSERT INTO "public"."jewelry_product_images" VALUES (20, '2021-09-06 08:23:31.591', 'admin@gmail.com', '2021-09-06 08:23:31.591', 'admin@gmail.com', 0, '1UlvrzTWU5MS_QZgR3LJilte2qelGybfT', 7);
INSERT INTO "public"."jewelry_product_images" VALUES (21, '2021-09-06 08:29:07.875', 'admin@gmail.com', '2021-09-06 08:29:07.875', 'admin@gmail.com', 0, '1Ho4JsXgq7KpIo91ANFhhS1OqDVrYn8s7', 8);
INSERT INTO "public"."jewelry_product_images" VALUES (22, '2021-09-06 08:29:07.877', 'admin@gmail.com', '2021-09-06 08:29:07.877', 'admin@gmail.com', 0, '1F1cnjKAgy3IOExLZA_OiWob6mafqg7ar', 8);
INSERT INTO "public"."jewelry_product_images" VALUES (23, '2021-09-06 08:29:07.879', 'admin@gmail.com', '2021-09-06 08:29:07.879', 'admin@gmail.com', 0, '1UQzOiHeVEwlGF_bMOPbgZyp2XXB_LNqh', 8);
INSERT INTO "public"."jewelry_product_images" VALUES (24, '2021-09-06 08:32:53.577', 'admin@gmail.com', '2021-09-06 08:32:53.577', 'admin@gmail.com', 0, '1MlFWrVh9SYts2QMhQ9UBc7_YN3HNtrdP', 9);
INSERT INTO "public"."jewelry_product_images" VALUES (25, '2021-09-06 08:32:53.579', 'admin@gmail.com', '2021-09-06 08:32:53.579', 'admin@gmail.com', 0, '1K0ORPOXoGeayAQvlZVuKt4WdmD39I2M3', 9);
INSERT INTO "public"."jewelry_product_images" VALUES (26, '2021-09-06 08:32:53.581', 'admin@gmail.com', '2021-09-06 08:32:53.581', 'admin@gmail.com', 0, '1Vtt9swnK8RmZD0VlvUT283rT8uHXDaWe', 9);
INSERT INTO "public"."jewelry_product_images" VALUES (27, '2021-09-06 08:35:38.561', 'admin@gmail.com', '2021-09-06 08:35:38.561', 'admin@gmail.com', 0, '1iucZ5rAdtQtfQdQ5enU-QKKUsbx0yW--', 10);
INSERT INTO "public"."jewelry_product_images" VALUES (28, '2021-09-06 08:35:38.563', 'admin@gmail.com', '2021-09-06 08:35:38.563', 'admin@gmail.com', 0, '1rQl9WKi71xkHACl8d0M7n-YQfgLXrAMU', 10);
INSERT INTO "public"."jewelry_product_images" VALUES (29, '2021-09-06 08:39:03.351', 'admin@gmail.com', '2021-09-06 08:39:03.351', 'admin@gmail.com', 0, '1QkNE8aRq7RP8yOaOpo-HUYRyrKI98imc', 11);
INSERT INTO "public"."jewelry_product_images" VALUES (30, '2021-09-06 08:41:17.344', 'admin@gmail.com', '2021-09-06 08:41:17.344', 'admin@gmail.com', 0, '1UL5UeO35nehhv3QkGxqmZsEefbTpOOy-', 12);
INSERT INTO "public"."jewelry_product_images" VALUES (31, '2021-09-06 08:41:17.346', 'admin@gmail.com', '2021-09-06 08:41:17.346', 'admin@gmail.com', 0, '1F7dpgH72RNGV6epGGIB9ZuDwreEIwS8P', 12);
INSERT INTO "public"."jewelry_product_images" VALUES (32, '2021-09-06 08:43:18.075', 'admin@gmail.com', '2021-09-06 08:43:18.075', 'admin@gmail.com', 0, '1vW9FL9_Q6YOY9vbV2c0ku0A9IpzR54RB', 13);
INSERT INTO "public"."jewelry_product_images" VALUES (33, '2021-09-06 08:43:18.079', 'admin@gmail.com', '2021-09-06 08:43:18.079', 'admin@gmail.com', 0, '1b6Pua9O1CzmJqRlnLZiG4LCUxJmmv7PC', 13);
INSERT INTO "public"."jewelry_product_images" VALUES (34, '2021-09-06 08:50:04.515', 'admin@gmail.com', '2021-09-06 08:50:04.515', 'admin@gmail.com', 0, '1Sa8mOn-7ashKzQNKhZAtTutBEi52OkVX', 14);
INSERT INTO "public"."jewelry_product_images" VALUES (35, '2021-09-06 08:50:04.517', 'admin@gmail.com', '2021-09-06 08:50:04.517', 'admin@gmail.com', 0, '1DTNUnK0HFo-1u5DKKX3Cy5B-Wu7hPqZy', 14);
INSERT INTO "public"."jewelry_product_images" VALUES (36, '2021-09-06 08:52:19.03', 'admin@gmail.com', '2021-09-06 08:52:19.03', 'admin@gmail.com', 0, '15dNQvXNjFQ2vHTgXjMFIYhT-3VP_ToHq', 15);
INSERT INTO "public"."jewelry_product_images" VALUES (37, '2021-09-06 08:52:19.034', 'admin@gmail.com', '2021-09-06 08:52:19.034', 'admin@gmail.com', 0, '1Gaw3MZ7LLirP6ifjf0BxOrCVsJX50ac7', 15);
INSERT INTO "public"."jewelry_product_images" VALUES (38, '2021-09-06 08:52:19.037', 'admin@gmail.com', '2021-09-06 08:52:19.037', 'admin@gmail.com', 0, '1nu5CnbXyaVbLwyRbzFHJO6FIrwZVuhN1', 15);
INSERT INTO "public"."jewelry_product_images" VALUES (39, '2021-09-06 08:54:40.149', 'admin@gmail.com', '2021-09-06 08:54:40.149', 'admin@gmail.com', 0, '198Zcqb6VSX2G1ZQRqOkGGfqR8aBJHid4', 16);
INSERT INTO "public"."jewelry_product_images" VALUES (40, '2021-09-06 08:54:40.152', 'admin@gmail.com', '2021-09-06 08:54:40.152', 'admin@gmail.com', 0, '1pXx-hvssV5GRG9Wtdu7NmGmQD0-Q7Z79', 16);
INSERT INTO "public"."jewelry_product_images" VALUES (41, '2021-09-06 09:01:21.546', 'admin@gmail.com', '2021-09-06 09:01:21.546', 'admin@gmail.com', 0, '194frS1UsxhaSnIdCOne843ac7w6mXqis', 18);
INSERT INTO "public"."jewelry_product_images" VALUES (42, '2021-09-06 09:01:21.551', 'admin@gmail.com', '2021-09-06 09:01:21.551', 'admin@gmail.com', 0, '18HLlfTDJaDddnInOnYLHB5Oq7UWuBdOi', 18);
INSERT INTO "public"."jewelry_product_images" VALUES (43, '2021-09-06 09:03:11.184', 'admin@gmail.com', '2021-09-06 09:03:11.184', 'admin@gmail.com', 0, '1Qe5a8tHwK3BXWq19_w4kce0d0yAT-zLX', 19);
INSERT INTO "public"."jewelry_product_images" VALUES (44, '2021-09-06 09:03:11.187', 'admin@gmail.com', '2021-09-06 09:03:11.187', 'admin@gmail.com', 0, '1idiwAnGwT2AT49NsS8BTw_Oz_MZVHfoz', 19);
INSERT INTO "public"."jewelry_product_images" VALUES (45, '2021-09-06 09:07:25.555', 'admin@gmail.com', '2021-09-06 09:07:25.555', 'admin@gmail.com', 0, '1qggFQPPQvFMFFP_LomYmVa2YHGBA-uuL', 20);
INSERT INTO "public"."jewelry_product_images" VALUES (46, '2021-09-06 09:10:25.611', 'admin@gmail.com', '2021-09-06 09:10:25.611', 'admin@gmail.com', 0, '1Yxs4nawyaOdsQ_zDsqebO9L6P_9v3spJ', 22);
INSERT INTO "public"."jewelry_product_images" VALUES (47, '2021-09-06 09:12:20.3', 'admin@gmail.com', '2021-09-06 09:12:20.3', 'admin@gmail.com', 0, '1oR-ygC_zJutPE6EGo9KEgzIV97_DxWzV', 23);
INSERT INTO "public"."jewelry_product_images" VALUES (48, '2021-09-06 09:12:20.303', 'admin@gmail.com', '2021-09-06 09:12:20.303', 'admin@gmail.com', 0, '121CK425BHP0d2AOGfLurDjmi7AEpAwR_', 23);
INSERT INTO "public"."jewelry_product_images" VALUES (49, '2021-09-06 09:12:20.305', 'admin@gmail.com', '2021-09-06 09:12:20.305', 'admin@gmail.com', 0, '1JDj8bAYdaJ5lEXZzH5c8PPo1yiRvgY3h', 23);
INSERT INTO "public"."jewelry_product_images" VALUES (50, '2021-09-06 09:16:50.232', 'admin@gmail.com', '2021-09-06 09:16:50.232', 'admin@gmail.com', 0, '1ZBmJJb9JG-ffEgY8zlgSd4km1HC7Dk5H', 24);
INSERT INTO "public"."jewelry_product_images" VALUES (51, '2021-09-06 09:16:50.236', 'admin@gmail.com', '2021-09-06 09:16:50.236', 'admin@gmail.com', 0, '1HfhJk7daxq6svxHibh3lHGWWtaECnwSa', 24);
INSERT INTO "public"."jewelry_product_images" VALUES (52, '2021-09-06 09:18:34.001', 'admin@gmail.com', '2021-09-06 09:18:34.001', 'admin@gmail.com', 0, '1Tx7q8h5lcyikG0oBRenwuDzvV9_EXx2E', 25);
INSERT INTO "public"."jewelry_product_images" VALUES (53, '2021-09-06 09:18:34.004', 'admin@gmail.com', '2021-09-06 09:18:34.004', 'admin@gmail.com', 0, '1YINayz4v7fSru67uKjH7FM_Ae1K2HOLE', 25);
INSERT INTO "public"."jewelry_product_images" VALUES (54, '2021-09-06 09:22:56.257', 'admin@gmail.com', '2021-09-06 09:22:56.257', 'admin@gmail.com', 0, '1phUTX5H5I3UKvFVmBZIAUu5V3FtWWvt8', 26);
INSERT INTO "public"."jewelry_product_images" VALUES (55, '2021-09-06 09:22:56.26', 'admin@gmail.com', '2021-09-06 09:22:56.26', 'admin@gmail.com', 0, '1XDiL-sWElrMJ7dKZEsIDD60JGQSAOQJR', 26);
INSERT INTO "public"."jewelry_product_images" VALUES (56, '2021-09-06 09:22:56.264', 'admin@gmail.com', '2021-09-06 09:22:56.264', 'admin@gmail.com', 0, '11clJwN3I1Rm4OSdVX-BPxVLG2CMrScR_', 26);
INSERT INTO "public"."jewelry_product_images" VALUES (57, '2021-09-06 09:24:17.463', 'admin@gmail.com', '2021-09-06 09:24:17.463', 'admin@gmail.com', 0, '1B7eRDP9TJQ3IuYRRJtq_FSw_aIFaItAy', 27);
INSERT INTO "public"."jewelry_product_images" VALUES (58, '2021-09-06 09:24:17.465', 'admin@gmail.com', '2021-09-06 09:24:17.465', 'admin@gmail.com', 0, '1sCQnZ2sIOKSiT_HD0i68_j58BWgK-_Jz', 27);
INSERT INTO "public"."jewelry_product_images" VALUES (59, '2021-09-06 09:24:17.467', 'admin@gmail.com', '2021-09-06 09:24:17.467', 'admin@gmail.com', 0, '1DIQetdNRKJPBSp8QbNsg5K30cogQPbLw', 27);
INSERT INTO "public"."jewelry_product_images" VALUES (60, '2021-09-06 09:26:21.94', 'admin@gmail.com', '2021-09-06 09:26:21.94', 'admin@gmail.com', 0, '1qEdb-yG-ZNdMVrGzMQPOIm0O_eSd9Qq0', 28);
INSERT INTO "public"."jewelry_product_images" VALUES (61, '2021-09-06 09:26:21.942', 'admin@gmail.com', '2021-09-06 09:26:21.942', 'admin@gmail.com', 0, '1UbS5N2TrIjhDbvZHYN22g2abJ_iignrV', 28);
INSERT INTO "public"."jewelry_product_images" VALUES (62, '2021-09-06 09:26:21.944', 'admin@gmail.com', '2021-09-06 09:26:21.944', 'admin@gmail.com', 0, '112Erbifd-vYyP6koJiG6dEtoORuc-IkI', 28);
INSERT INTO "public"."jewelry_product_images" VALUES (75, '2021-09-06 09:42:29.14', 'admin@gmail.com', '2021-09-06 09:42:29.14', 'admin@gmail.com', 0, '1RgSwkGmtVZkhxjd5T9I0FG8TGLRrYVCi', 35);
INSERT INTO "public"."jewelry_product_images" VALUES (76, '2021-09-06 09:42:29.142', 'admin@gmail.com', '2021-09-06 09:42:29.142', 'admin@gmail.com', 0, '1NIO9J-QJGlhpP76Q_ciMHC8Hh-In-XkQ', 35);
INSERT INTO "public"."jewelry_product_images" VALUES (77, '2021-09-06 09:42:29.143', 'admin@gmail.com', '2021-09-06 09:42:29.143', 'admin@gmail.com', 0, '1zGgCcwiAF7XQx6DZbzQhlFWcXJL4GPiP', 35);
INSERT INTO "public"."jewelry_product_images" VALUES (82, '2021-09-06 09:47:55.834', 'admin@gmail.com', '2021-09-06 09:47:55.834', 'admin@gmail.com', 0, '1INkrO6axBIXB9e99uvshvqY8weH2ijUS', 38);
INSERT INTO "public"."jewelry_product_images" VALUES (63, '2021-09-06 09:31:45.123', 'admin@gmail.com', '2021-09-06 09:31:45.123', 'admin@gmail.com', 0, '1emZF9ZIkzmJAK97GJKia59Om1NmFTiQg', 30);
INSERT INTO "public"."jewelry_product_images" VALUES (64, '2021-09-06 09:31:45.126', 'admin@gmail.com', '2021-09-06 09:31:45.126', 'admin@gmail.com', 0, '1NpL9gLEo8PczR-RZvd8Z3kPoNqFyDy3O', 30);
INSERT INTO "public"."jewelry_product_images" VALUES (65, '2021-09-06 09:33:50.688', 'admin@gmail.com', '2021-09-06 09:33:50.688', 'admin@gmail.com', 0, '1R8nNpNC4kSl3lGfqO8ArQ12qnkHpjh5L', 31);
INSERT INTO "public"."jewelry_product_images" VALUES (66, '2021-09-06 09:33:50.691', 'admin@gmail.com', '2021-09-06 09:33:50.691', 'admin@gmail.com', 0, '1YuwgyzwxzrfMm7s1pHorJyJ18mkxr4_D', 31);
INSERT INTO "public"."jewelry_product_images" VALUES (67, '2021-09-06 09:35:16.011', 'admin@gmail.com', '2021-09-06 09:35:16.011', 'admin@gmail.com', 0, '1JJfWZju4f5JklZ_W9IEme-z4DfLc5uFH', 32);
INSERT INTO "public"."jewelry_product_images" VALUES (68, '2021-09-06 09:35:16.015', 'admin@gmail.com', '2021-09-06 09:35:16.015', 'admin@gmail.com', 0, '1mjt9HMgjDRrirTzkw0fMm6wi75mjL4Yv', 32);
INSERT INTO "public"."jewelry_product_images" VALUES (69, '2021-09-06 09:36:46.178', 'admin@gmail.com', '2021-09-06 09:36:46.178', 'admin@gmail.com', 0, '1aarJBSl9bX0XZ5S3KSDOIIJK70zMCbK0', 33);
INSERT INTO "public"."jewelry_product_images" VALUES (70, '2021-09-06 09:36:46.181', 'admin@gmail.com', '2021-09-06 09:36:46.181', 'admin@gmail.com', 0, '1Ky-oStHzswnPrYljOp1lsejR1TkrQ7MY', 33);
INSERT INTO "public"."jewelry_product_images" VALUES (71, '2021-09-06 09:36:46.184', 'admin@gmail.com', '2021-09-06 09:36:46.184', 'admin@gmail.com', 0, '129c908Dd-AECkQu7CO63M14Bh6QOZSgg', 33);
INSERT INTO "public"."jewelry_product_images" VALUES (72, '2021-09-06 09:38:41.843', 'admin@gmail.com', '2021-09-06 09:38:41.843', 'admin@gmail.com', 0, '1yT-dhf_AH3VRpU_wWJ3Sbc0Ll0_6zDuB', 34);
INSERT INTO "public"."jewelry_product_images" VALUES (73, '2021-09-06 09:38:41.846', 'admin@gmail.com', '2021-09-06 09:38:41.846', 'admin@gmail.com', 0, '158l6ZSPRuudSHo0ERqJt89LMlzonYL-x', 34);
INSERT INTO "public"."jewelry_product_images" VALUES (74, '2021-09-06 09:38:41.848', 'admin@gmail.com', '2021-09-06 09:38:41.848', 'admin@gmail.com', 0, '18zSV4vMeO5hF8KXwRksqMTwA_YFK651C', 34);
INSERT INTO "public"."jewelry_product_images" VALUES (78, '2021-09-06 09:44:11.402', 'admin@gmail.com', '2021-09-06 09:44:11.402', 'admin@gmail.com', 0, '1-BZzAECU55rAvS52RYmdTc9ag2XRPwf4', 36);
INSERT INTO "public"."jewelry_product_images" VALUES (79, '2021-09-06 09:44:11.406', 'admin@gmail.com', '2021-09-06 09:44:11.406', 'admin@gmail.com', 0, '1Evg7_J3mXeLAmJWRoCmK5_zUaRCTBK17', 36);
INSERT INTO "public"."jewelry_product_images" VALUES (80, '2021-09-06 09:44:11.408', 'admin@gmail.com', '2021-09-06 09:44:11.408', 'admin@gmail.com', 0, '1XUhTSooEs3y2Vxe2p2Kalfcxc-zwmm4H', 36);
INSERT INTO "public"."jewelry_product_images" VALUES (81, '2021-09-06 09:45:30.531', 'admin@gmail.com', '2021-09-06 09:45:30.531', 'admin@gmail.com', 0, '18k5I0g_cOflM6JAptUdqVPYs_XZoxd8d', 37);
INSERT INTO "public"."jewelry_product_images" VALUES (83, '2021-09-06 09:49:03.256', 'admin@gmail.com', '2021-09-06 09:49:03.256', 'admin@gmail.com', 0, '1EfgZeUmZ8Fe4QuQLYwJsLprWA0Zqxe_L', 39);
COMMIT;

-- ----------------------------
-- Table structure for jewelry_refreshtoken
-- ----------------------------
DROP TABLE IF EXISTS "public"."jewelry_refreshtoken" CASCADE;
CREATE TABLE "public"."jewelry_refreshtoken" (
  "id" int8 NOT NULL GENERATED BY DEFAULT AS IDENTITY (
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 6
),
  "expiry_date" timestamp(6) NOT NULL,
  "refresh_count" int8,
  "token" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "customer_device_id" int8 NOT NULL
)
;
ALTER TABLE "public"."jewelry_refreshtoken" OWNER TO "postgres";

-- ----------------------------
-- Records of jewelry_refreshtoken
-- ----------------------------
BEGIN;
INSERT INTO "public"."jewelry_refreshtoken" VALUES (5, '2021-09-06 11:10:23.009', 0, '16cdf40d-6375-4925-97e1-e6323a5ecfed', 5);
COMMIT;

-- ----------------------------
-- Table structure for jewelry_role
-- ----------------------------
DROP TABLE IF EXISTS "public"."jewelry_role" CASCADE;
CREATE TABLE "public"."jewelry_role" (
  "id" int8 NOT NULL GENERATED BY DEFAULT AS IDENTITY (
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 3
),
  "role_name" varchar(255) COLLATE "pg_catalog"."default"
)
;
ALTER TABLE "public"."jewelry_role" OWNER TO "postgres";

-- ----------------------------
-- Records of jewelry_role
-- ----------------------------
BEGIN;
INSERT INTO "public"."jewelry_role" VALUES (1, 'ROLE_ADMIN');
INSERT INTO "public"."jewelry_role" VALUES (2, 'ROLE_USER');
COMMIT;

-- ----------------------------
-- Table structure for jewelry_supplier
-- ----------------------------
DROP TABLE IF EXISTS "public"."jewelry_supplier" CASCADE;
CREATE TABLE "public"."jewelry_supplier" (
  "id" int8 NOT NULL GENERATED BY DEFAULT AS IDENTITY (
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 6
),
  "created_at" timestamp(6) NOT NULL,
  "created_by" varchar(255) COLLATE "pg_catalog"."default",
  "updated_at" timestamp(6) NOT NULL,
  "updated_by" varchar(255) COLLATE "pg_catalog"."default",
  "version" int4 NOT NULL,
  "address" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "code" varchar(255) COLLATE "pg_catalog"."default",
  "email" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "logo" varchar(255) COLLATE "pg_catalog"."default",
  "name" varchar(255) COLLATE "pg_catalog"."default",
  "phone_number" varchar(255) COLLATE "pg_catalog"."default" NOT NULL
)
;
ALTER TABLE "public"."jewelry_supplier" OWNER TO "postgres";

-- ----------------------------
-- Records of jewelry_supplier
-- ----------------------------
BEGIN;
INSERT INTO "public"."jewelry_supplier" VALUES (1, '2021-09-06 01:21:27.396', 'admin@gmail.com', '2021-09-06 01:21:27.396', 'admin@gmail.com', 0, 'Quận 1', 'NCC-1', 'ncc-1@gmail.com', NULL, 'Nhà cung cấp 1', '0787656543');
INSERT INTO "public"."jewelry_supplier" VALUES (2, '2021-09-06 01:21:46.925', 'admin@gmail.com', '2021-09-06 01:21:46.925', 'admin@gmail.com', 0, 'Quận 2', 'NCC-2', 'ncc-2@gmail.com', NULL, 'Nhà cung cấp 2', '0787548543');
INSERT INTO "public"."jewelry_supplier" VALUES (3, '2021-09-06 01:22:00.798', 'admin@gmail.com', '2021-09-06 01:22:00.798', 'admin@gmail.com', 0, 'Quận 3', 'NCC-3', 'ncc-3@gmail.com', NULL, 'Nhà cung cấp 3', '0787548123');
INSERT INTO "public"."jewelry_supplier" VALUES (4, '2021-09-06 01:22:17.722', 'admin@gmail.com', '2021-09-06 01:22:17.722', 'admin@gmail.com', 0, 'Quận 4', 'NCC-4', 'ncc-4@gmail.com', NULL, 'Nhà cung cấp 4', '0787897123');
INSERT INTO "public"."jewelry_supplier" VALUES (5, '2021-09-06 01:22:41.184', 'admin@gmail.com', '2021-09-06 01:22:41.184', 'admin@gmail.com', 0, 'Quận 5', 'NCC-5', 'ncc-5@gmail.com', NULL, 'Nhà cung cấp 5', '0787555123');
COMMIT;


-- ----------------------------
-- Primary Key structure for table jewelry_cart
-- ----------------------------
ALTER TABLE "public"."jewelry_cart" ADD CONSTRAINT "jewelry_cart_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Checks structure for table jewelry_cart_items
-- ----------------------------
ALTER TABLE "public"."jewelry_cart_items" ADD CONSTRAINT "jewelry_cart_items_quantity_check" CHECK (quantity >= 1);

-- ----------------------------
-- Primary Key structure for table jewelry_cart_items
-- ----------------------------
ALTER TABLE "public"."jewelry_cart_items" ADD CONSTRAINT "jewelry_cart_items_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table jewelry_customer
-- ----------------------------
ALTER TABLE "public"."jewelry_customer" ADD CONSTRAINT "uk_golhklk2bmd1y1gbbq9v1whyy" UNIQUE ("email");
ALTER TABLE "public"."jewelry_customer" ADD CONSTRAINT "uk_5m29kalcb3co0ikvth5ye1w9p" UNIQUE ("phone_number");

-- ----------------------------
-- Primary Key structure for table jewelry_customer
-- ----------------------------
ALTER TABLE "public"."jewelry_customer" ADD CONSTRAINT "jewelry_customer_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table jewelry_customer_device
-- ----------------------------
ALTER TABLE "public"."jewelry_customer_device" ADD CONSTRAINT "jewelry_customer_device_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table jewelry_customer_roles
-- ----------------------------
ALTER TABLE "public"."jewelry_customer_roles" ADD CONSTRAINT "jewelry_customer_roles_pkey" PRIMARY KEY ("customer_id", "role_id");

-- ----------------------------
-- Primary Key structure for table jewelry_order
-- ----------------------------
ALTER TABLE "public"."jewelry_order" ADD CONSTRAINT "jewelry_order_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table jewelry_order_item
-- ----------------------------
ALTER TABLE "public"."jewelry_order_item" ADD CONSTRAINT "jewelry_order_item_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table jewelry_product
-- ----------------------------
ALTER TABLE "public"."jewelry_product" ADD CONSTRAINT "uk_ijl2j697qj7g7qnhy48bibc8i" UNIQUE ("sku");

-- ----------------------------
-- Primary Key structure for table jewelry_product
-- ----------------------------
ALTER TABLE "public"."jewelry_product" ADD CONSTRAINT "jewelry_product_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table jewelry_product_category
-- ----------------------------
ALTER TABLE "public"."jewelry_product_category" ADD CONSTRAINT "uk_keatugoba5fyqvqakhagsi2l7" UNIQUE ("code");

-- ----------------------------
-- Primary Key structure for table jewelry_product_category
-- ----------------------------
ALTER TABLE "public"."jewelry_product_category" ADD CONSTRAINT "jewelry_product_category_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table jewelry_product_gold_type
-- ----------------------------
ALTER TABLE "public"."jewelry_product_gold_type" ADD CONSTRAINT "uk_83idyes5vtuwpf7g5a0pf7g0g" UNIQUE ("percentage");

-- ----------------------------
-- Primary Key structure for table jewelry_product_gold_type
-- ----------------------------
ALTER TABLE "public"."jewelry_product_gold_type" ADD CONSTRAINT "jewelry_product_gold_type_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table jewelry_product_images
-- ----------------------------
ALTER TABLE "public"."jewelry_product_images" ADD CONSTRAINT "jewelry_product_images_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table jewelry_refreshtoken
-- ----------------------------
ALTER TABLE "public"."jewelry_refreshtoken" ADD CONSTRAINT "uk_pn6sdvq9hd85is3g2tfwv2cuf" UNIQUE ("customer_device_id");
ALTER TABLE "public"."jewelry_refreshtoken" ADD CONSTRAINT "uk_l744q9n8yq8d39ecj950uhm1t" UNIQUE ("token");

-- ----------------------------
-- Primary Key structure for table jewelry_refreshtoken
-- ----------------------------
ALTER TABLE "public"."jewelry_refreshtoken" ADD CONSTRAINT "jewelry_refreshtoken_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table jewelry_role
-- ----------------------------
ALTER TABLE "public"."jewelry_role" ADD CONSTRAINT "jewelry_role_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table jewelry_supplier
-- ----------------------------
ALTER TABLE "public"."jewelry_supplier" ADD CONSTRAINT "uk_ipr8v4u8owifbdeasnw8vbqd9" UNIQUE ("code");

-- ----------------------------
-- Primary Key structure for table jewelry_supplier
-- ----------------------------
ALTER TABLE "public"."jewelry_supplier" ADD CONSTRAINT "jewelry_supplier_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Foreign Keys structure for table jewelry_cart
-- ----------------------------
ALTER TABLE "public"."jewelry_cart" ADD CONSTRAINT "fkfouo6n1s655xtcoe7ghqhyf2n" FOREIGN KEY ("customer_id") REFERENCES "public"."jewelry_customer" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table jewelry_cart_items
-- ----------------------------
ALTER TABLE "public"."jewelry_cart_items" ADD CONSTRAINT "fkaf6rdn8qxn3vy3mms4lueew63" FOREIGN KEY ("cart_id") REFERENCES "public"."jewelry_cart" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."jewelry_cart_items" ADD CONSTRAINT "fkcdhp4gm7vqg0r4myskse16sg8" FOREIGN KEY ("product_id") REFERENCES "public"."jewelry_product" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table jewelry_customer_device
-- ----------------------------
ALTER TABLE "public"."jewelry_customer_device" ADD CONSTRAINT "fkrt4kc1umuip4lcrc7a1n5dmtj" FOREIGN KEY ("customer_id") REFERENCES "public"."jewelry_customer" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table jewelry_customer_roles
-- ----------------------------
ALTER TABLE "public"."jewelry_customer_roles" ADD CONSTRAINT "fk8j32eyr8ijsmd49xx4flm781t" FOREIGN KEY ("customer_id") REFERENCES "public"."jewelry_customer" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."jewelry_customer_roles" ADD CONSTRAINT "fktkv9mx6b6sobfayd4rsrwdo5m" FOREIGN KEY ("role_id") REFERENCES "public"."jewelry_role" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table jewelry_order
-- ----------------------------
ALTER TABLE "public"."jewelry_order" ADD CONSTRAINT "fkd9qo63bdbrnox7ump1an1ha38" FOREIGN KEY ("customer_id") REFERENCES "public"."jewelry_customer" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table jewelry_order_item
-- ----------------------------
ALTER TABLE "public"."jewelry_order_item" ADD CONSTRAINT "fk8s0set2c9i87bulgi0qo83hsi" FOREIGN KEY ("product_id") REFERENCES "public"."jewelry_product" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."jewelry_order_item" ADD CONSTRAINT "fka6u1fqll4kidquwneonfilq92" FOREIGN KEY ("order_id") REFERENCES "public"."jewelry_order" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table jewelry_product
-- ----------------------------
ALTER TABLE "public"."jewelry_product" ADD CONSTRAINT "fk5c8g717cvfnuu0qshc4igiey2" FOREIGN KEY ("category_id") REFERENCES "public"."jewelry_product_category" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."jewelry_product" ADD CONSTRAINT "fkfcy74mp7xailqq0k0dsnfg6ov" FOREIGN KEY ("supplier_id") REFERENCES "public"."jewelry_supplier" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."jewelry_product" ADD CONSTRAINT "fksug3nkbmnr23rm5aarxi3ah85" FOREIGN KEY ("gold_type_id") REFERENCES "public"."jewelry_product_gold_type" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table jewelry_product_images
-- ----------------------------
ALTER TABLE "public"."jewelry_product_images" ADD CONSTRAINT "fkwsh32jkc8uc6t9dnukspbmni" FOREIGN KEY ("product_id") REFERENCES "public"."jewelry_product" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table jewelry_refreshtoken
-- ----------------------------
ALTER TABLE "public"."jewelry_refreshtoken" ADD CONSTRAINT "fk5gdmr0qlu4qpuphqsln19rqw9" FOREIGN KEY ("customer_device_id") REFERENCES "public"."jewelry_customer_device" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
