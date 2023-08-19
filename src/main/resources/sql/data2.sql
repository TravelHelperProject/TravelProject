-- travel 스키마 선택
USE travel;

-- user 테이블 생성
CREATE TABLE `user` (
                        `user_id` bigint NOT NULL,
                        `name` VARCHAR(255) NULL,
                        `password` VARCHAR(255) NULL,
                        `email` VARCHAR(255) NULL,
                        `nickname` VARCHAR(255) NULL,
                        `created_at` datetime NULL
);

-- accommodation 테이블 생성
CREATE TABLE `accommodation` (
                                 `room_id` bigint NOT NULL,
                                 `room_name` VARCHAR(255) NULL,
                                 `room_location` VARCHAR(225) NULL,
                                 `price` int NULL,
                                 `number_rooms` int NULL,
                                 `number_people` int NULL,
                                 `checkin` datetime NULL,
                                 `checkout` datetime NULL,
                                 `information` VARCHAR(255) NULL,
                                 `created_at` datetime NULL,
                                 `updated_at` datetime NULL,
                                 `latitude` Decimal NULL,
                                 `longitude` Decimal NULL
);

-- reservation 테이블 생성
CREATE TABLE `reservation` (
                               `reservation_id` bigint NOT NULL,
                               `room_id` bigint NOT NULL,
                               `user_id` bigint NOT NULL,
                               `payment_check` boolean not NULL,
                               `payment_date` datetime NULL
);

-- restaurants 테이블 생성
CREATE TABLE `restaurants` (
                               `restaurants_id` bigint NOT NULL,
                               `category` VARCHAR(255) NULL,
                               `name` VARCHAR(255) NULL,
                               `phone_number` VARCHAR(255) NULL,
                               `open_time` datetime NULL,
                               `close_time` datetime NULL,
                               `latitude` Decimal NULL,
                               `longitude` Decimal NULL
);

-- festival 테이블 생성
CREATE TABLE `festival` (
                            `festival_id` bigint NOT NULL,
                            `user_id` bigint NOT NULL,
                            `start_date` datetime NULL,
                            `end_date` datetime NULL,
                            `name` VARCHAR(255) NULL,
                            `phone_number` VARCHAR(255) NULL,
                            `latitude` Decimal NULL,
                            `longitude` Decimal NULL
);

-- festival_review 테이블 생성
CREATE TABLE `festival_review` (
                                   `review_id` bigint NOT NULL,
                                   `festival_id` bigint NOT NULL,
                                   `user_id` bigint NOT NULL,
                                   `content` VARCHAR(255) NULL,
                                   `writer` VARCHAR(255) NULL,
                                   `grade` int NULL,
                                   `create_date` datetime NULL
);

-- board_comment 테이블 생성
CREATE TABLE `board_comment` (
                                 `reply_id` bigint NOT NULL,
                                 `board_id` bigint NOT NULL,
                                 `user_id` bigint NOT NULL,
                                 `content` VARCHAR(255) NULL
);

-- accommodation_review 테이블 생성
CREATE TABLE `accommodation_review` (
                                        `review_id` bigint NOT NULL,
                                        `room_id` bigint NOT NULL,
                                        `user_id` bigint NOT NULL,
                                        `content` VARCHAR(255) NOT NULL,
                                        `writer` VARCHAR(255) NOT NULL,
                                        `created_at` datetime NULL,
                                        `updated_at` datetime NULL,
                                        `grade` int NOT NULL
);

-- board 테이블 생성
CREATE TABLE `board` (
                         `board_id` bigint NOT NULL,
                         `user_id` bigint NOT NULL,
                         `title` VARCHAR(255) NULL,
                         `writer` VARCHAR(255) NULL,
                         `created_at` datetime NULL,
                         `content` VARCHAR(255) NULL,
                         `read_cnt` int NULL
);

-- user 테이블의 기본키 설정
ALTER TABLE `user` ADD CONSTRAINT `PK_USER` PRIMARY KEY (`user_id`);

-- accommodation 테이블의 기본키 설정
ALTER TABLE `accommodation` ADD CONSTRAINT `PK_ACCOMMODATION` PRIMARY KEY (`room_id`);

-- reservation 테이블의 기본키 설정
ALTER TABLE `reservation` ADD CONSTRAINT `PK_RESERVATION` PRIMARY KEY (`reservation_id`, `room_id`, `user_id`);

-- restaurants 테이블의 기본키 설정
ALTER TABLE `restaurants` ADD CONSTRAINT `PK_RESTAURANTS` PRIMARY KEY (`restaurants_id`);

-- festival 테이블의 기본키 설정
ALTER TABLE `festival` ADD CONSTRAINT `PK_FESTIVAL` PRIMARY KEY (`festival_id`, `user_id`);

-- festival_review 테이블의 기본키 설정
ALTER TABLE `festival_review` ADD CONSTRAINT `PK_FESTIVAL_REVIEW` PRIMARY KEY (`review_id`, `festival_id`, `user_id`);

-- board_comment 테이블의 기본키 설정
ALTER TABLE `board_comment` ADD CONSTRAINT `PK_BOARD_COMMENT` PRIMARY KEY (`reply_id`, `board_id`, `user_id`);

-- accommodation_review 테이블의 기본키 설정
ALTER TABLE `accommodation_review` ADD CONSTRAINT `PK_ACCOMMODATION_REVIEW` PRIMARY KEY (`review_id`, `room_id`, `user_id`);

-- board 테이블의 기본키 설정
ALTER TABLE `board` ADD CONSTRAINT `PK_BOARD` PRIMARY KEY (`board_id`, `user_id`);

-- reservation 테이블의 외래키 설정
ALTER TABLE `reservation` ADD CONSTRAINT `FK_accommodation_TO_reservation_1` FOREIGN KEY (`room_id`)
    REFERENCES `accommodation` (`room_id`);

ALTER TABLE `reservation` ADD CONSTRAINT `FK_user_TO_reservation_1` FOREIGN KEY (`user_id`)
    REFERENCES `user` (`user_id`);

-- festival 테이블의 외래키 설정
ALTER TABLE `festival` ADD CONSTRAINT `FK_user_TO_festival_1` FOREIGN KEY (`user_id`)
    REFERENCES `user` (`user_id`);

-- festival_review 테이블의 외래키 설정
ALTER TABLE `festival_review` ADD CONSTRAINT `FK_festival_TO_festival_review_1` FOREIGN KEY (`festival_id`)
    REFERENCES `festival` (`festival_id`);

ALTER TABLE `festival_review` ADD CONSTRAINT `FK_festival_TO_festival_review_2` FOREIGN KEY (`user_id`)
    REFERENCES `user` (`user_id`);

-- board_comment 테이블의 외래키 설정
ALTER TABLE `board_comment` ADD CONSTRAINT `FK_board_TO_board_comment_1` FOREIGN KEY (`board_id`)
    REFERENCES `board` (`board_id`);

-- accommodation_review 테이블의 외래키 설정
ALTER TABLE `accommodation_review` ADD CONSTRAINT `FK_accommodation_TO_accommodation_review_1` FOREIGN KEY (`room_id`)
    REFERENCES `accommodation` (`room_id`);

ALTER TABLE `accommodation_review` ADD CONSTRAINT `FK_user_TO_accommodation_review_1` FOREIGN KEY (`user_id`)
    REFERENCES `user` (`user_id`);

-- board 테이블의 외래키 설정
ALTER TABLE `board` ADD CONSTRAINT `FK_user_TO_board_1` FOREIGN KEY (`user_id`)
    REFERENCES `user` (`user_id`);

-- board_comment 테이블의 외래키 설정
ALTER TABLE `board_comment` ADD CONSTRAINT `FK_board_TO_board_comment_2` FOREIGN KEY (`user_id`)
    REFERENCES `user` (`user_id`);


-- user 테이블에 더미데이터 삽입
INSERT INTO `user` (`user_id`, `name`, `password`, `email`, `nickname`, `created_at`)
VALUES
    (1, '홍길동', '비밀번호123', 'hong@example.com', 'hong_gildong', '2023-07-30 12:00:00'),
    (2, '김영희', 'pass123', 'kim@example.com', 'yeonghee', '2023-07-30 13:00:00'),
    (3, '이철수', 'chulsoo1', 'lee@example.com', 'chulsoo', '2023-07-30 14:00:00');

-- accommodation 테이블에 더미데이터 삽입
INSERT INTO `accommodation` (`room_id`, `room_name`,`room_location`, `price`, `number_rooms`, `number_people`, `checkin`, `checkout`, `information`, `created_at`, `updated_at`, `latitude`, `longitude`)
VALUES
    (1, '아늑한 산장', '경기도', 150, 2, 4, '2023-08-01', '2023-08-05', '자연 속 아름다운 산장', '2023-07-30 12:00:00', '2023-07-30 12:00:00', 40.7128, -74.0060),
    (2, '럭셔리 리조트','서울', 500, 3, 6, '2023-09-10', '2023-09-15', '편안한 리조트와 스파', '2023-07-30 13:00:00', '2023-07-30 13:00:00', 34.0522, -118.2437),
    (3, '해변별장','부산', 300, 2, 4, '2023-08-20', '2023-08-25', '아름다운 해변가의 별장', '2023-07-30 14:00:00', '2023-07-30 14:00:00', 25.7617, -80.1918);

-- reservation 테이블에 더미데이터 삽입
INSERT INTO `reservation` (`reservation_id`, `room_id`, `user_id`, `payment_check`, `payment_date`)
VALUES
    (1, 1, 1, true, '2023-07-31 10:00:00'),
    (2, 2, 2, true, '2023-07-30 15:00:00'),
    (3, 3, 3, false, NULL);

-- restaurants 테이블에 더미데이터 삽입
INSERT INTO `restaurants` (`restaurants_id`, `category`, `name`, `phone_number`, `open_time`, `close_time`, `latitude`, `longitude`)
VALUES
    (1, '이탈리안', '파스타 파라다이스', '+1 123-456-7890', '2023-07-30 18:00:00', '2023-07-30 23:00:00', 40.7128, -74.0060),
    (2, '일본식', '스시 델라이트', '+1 987-654-3210', '2023-07-30 12:00:00', '2023-07-30 22:00:00', 34.0522, -118.2437),
    (3, '아메리칸', '버거 조인트', '+1 555-123-4567', '2023-07-30 11:30:00', '2023-07-30 21:00:00', 25.7617, -80.1918);

-- festival 테이블에 더미데이터 삽입
INSERT INTO `festival` (`festival_id`, `user_id`, `start_date`, `end_date`, `name`, `phone_number`, `latitude`, `longitude`)
VALUES
    (1, 1, '2023-08-10', '2023-08-15', '여름 음악 페스티벌', '+1 123-456-7890', 40.7128, -74.0060),
    (2, 2, '2023-09-20', '2023-09-25', '가을 푸드 페스티벌', '+1 987-654-3210', 34.0522, -118.2437),
    (3, 3, '2023-08-30', '2023-09-03', '해변 카니발', '+1 555-123-4567', 25.7617, -80.1918);

-- festival_review 테이블에 더미데이터 삽입
INSERT INTO `festival_review` (`review_id`, `festival_id`, `user_id`, `content`, `writer`, `grade`, `create_date`)
VALUES
    (1, 1, 1, '페스티벌에서 즐거운 시간 보냈습니다!', '홍길동', 5, '2023-08-12 14:00:00'),
    (2, 2, 2, '음식이 너무 맛있었습니다!', '김영희', 4, '2023-09-22 12:30:00'),
    (3, 3, 3, '다양한 놀이가 있어서 즐거웠어요!', '이철수', 4, '2023-08-31 16:45:00');

-- accommodation_review 테이블에 더미데이터 삽입
INSERT INTO `accommodation_review` (`review_id`, `room_id`,`user_id`, `content`, `writer`, `created_at`, `updated_at`, `grade`)
VALUES
    (1, 1, 1, '좋은 숙박이었습니다!', '홍길동', '2023-08-02 11:00:00', '2023-08-03 09:30:00', 5),
    (2, 2, 2, '리조트가 훌륭했습니다!', '김영희', '2023-09-12 15:30:00', '2023-09-13 10:00:00', 4),
    (3, 3, 3, '별장에서 멋진 뷰를 감상했습니다!', '이철수', '2023-08-23 13:45:00', '2023-08-24 11:15:00', 4);

-- board 테이블에 더미데이터 삽입
INSERT INTO `board` (`board_id`, `user_id`, `title`, `writer`, `created_at`, `content`, `read_cnt`)
VALUES
    (1, 1, '여행 이야기', '홍길동', '2023-07-30 15:00:00', '여행 이야기를 공유합니다.', 10),
    (2, 2, '축제 정보', '김영희', '2023-07-30 16:00:00', '축제 정보를 알려드립니다.', 5),
    (3, 3, '숙박 후기', '이철수', '2023-07-30 17:00:00', '숙박 후기를 남깁니다.', 8);