-- ==============================================
-- SEED DATA FOR CINEMA BOOKING SYSTEM
-- ==============================================

-- Xóa toàn bộ data cũ và reset sequence (Hibernate đã tạo bảng trước khi script này chạy)
TRUNCATE TABLE payments, bookings, tickets, seats, showtimes, rooms, cinemas, movies, genres, users RESTART IDENTITY CASCADE;

-- ==============================================
-- USERS DATA
-- ==============================================

-- Admin User
INSERT INTO users (email, password, full_name, role, status, created_at, updated_at) VALUES
('admin@sidocinemas.com', '$2a$10$KjHp.hfHjr/sGgHCC20xtuDbVbIUIzgUGLUa8FScukEJ9aCz5Y2iy', 'Admin System', 'ADMIN', 'ACTIVE', NOW(), NOW());

-- Manager Users
INSERT INTO users (email, password, full_name, role, status, created_at, updated_at) VALUES
('manager.hanoi@sidocinemas.com', '$2a$10$KjHp.hfHjr/sGgHCC20xtuDbVbIUIzgUGLUa8FScukEJ9aCz5Y2iy', 'Nguyễn Văn Quản', 'MANAGER', 'ACTIVE', NOW(), NOW()),
('manager.hcm@sidocinemas.com', '$2a$10$KjHp.hfHjr/sGgHCC20xtuDbVbIUIzgUGLUa8FScukEJ9aCz5Y2iy', 'Trần Thị Mai', 'MANAGER', 'ACTIVE', NOW(), NOW()),
('manager.danang@sidocinemas.com', '$2a$10$KjHp.hfHjr/sGgHCC20xtuDbVbIUIzgUGLUa8FScukEJ9aCz5Y2iy', 'Lê Minh Khôi', 'MANAGER', 'ACTIVE', NOW(), NOW());

-- Customer Users
INSERT INTO users (email, password, full_name, role, status, created_at, updated_at) VALUES
('customer1@example.com', '$2a$10$KjHp.hfHjr/sGgHCC20xtuDbVbIUIzgUGLUa8FScukEJ9aCz5Y2iy', 'Phạm Minh Tuấn', 'CUSTOMER', 'ACTIVE', NOW(), NOW()),
('customer2@example.com', '$2a$10$KjHp.hfHjr/sGgHCC20xtuDbVbIUIzgUGLUa8FScukEJ9aCz5Y2iy', 'Hoàng Thị Lan', 'CUSTOMER', 'ACTIVE', NOW(), NOW()),
('customer3@example.com', '$2a$10$KjHp.hfHjr/sGgHCC20xtuDbVbIUIzgUGLUa8FScukEJ9aCz5Y2iy', 'Vũ Đình Nam', 'CUSTOMER', 'ACTIVE', NOW(), NOW());

-- ==============================================
-- GENRES DATA (Thể loại phim & Danh mục phim phong phú)
-- ==============================================

INSERT INTO genres (name, description, created_at) VALUES
('Hành Động', 'Phim có nhiều cảnh chiến đấu, rượt đuổi, đầy kịch tính', NOW()),
('Kinh Dị', 'Phim gây sợ hãi, hồi hộp, đầy ám ảnh', NOW()),
('Hài Hước', 'Phim mang lại tiếng cười, giải trí nhẹ nhàng', NOW()),
('Tình Cảm', 'Phim lãng mạn, cảm động, xúc tích', NOW()),
('Hoạt Hình', 'Phim hoạt hình dành cho mọi lứa tuổi', NOW()),
('Khoa Học Viễn Tưởng', 'Phim về tương lai, công nghệ, vũ trụ', NOW()),
('Phiêu Lưu', 'Phim hành trình khám phá đầy gay cấn', NOW()),
('Tâm Lý', 'Phim khai thác nội tâm nhân vật sâu sắc', NOW()),
('Gia Đình', 'Phim giá trị gia đình, phù hợp mọi đối tượng', NOW()),
('Tội Phạm', 'Phim xã hội đen, trinh thám, đấu trí', NOW()),
('Ca Nhạc', 'Phim âm nhạc, vũ đạo kết hợp nghệ thuật', NOW()),
('Lịch Sử - Sử Thi', 'Phim về sự kiện lịch sử, chiến tranh và các thời kỳ hào hùng', NOW()),
('Kỳ Ảo - Thần Thoại', 'Phim ma thuật, thế giới giả tưởng huyền bí', NOW()),
('Bí Ẩn - Trinh Thám', 'Phim suy luận phá án, tìm kiếm sự thật ẩn giấu', NOW()),
('Anime', 'Hoạt hình Nhật Bản phong cách đồ họa đặc trưng', NOW()),
('Điện Ảnh Việt', 'Phim sản xuất tại Việt Nam đậm đà bản sắc dân tộc', NOW());

-- ==============================================
-- MOVIES DATA (Đa dạng thể loại 2024 - 2026)
-- ==============================================

INSERT INTO movies (title, description, duration, genre, age_rating, poster_url, status, created_at, updated_at) VALUES
-- 1. Action / Marvel
('Deadpool & Wolverine', 
'Wade Wilson sống cuộc sống yên bình khi Logan thuyết phục anh ta tham gia vào nhiệm vụ cứu thế giới của mình. Họ phải đối mặt với kẻ thù chung trong cuộc phiêu lưu đầy hành động và hài hước.',
128, 'Hành Động, Hài Hước', 'T16',
'/assets/movie/059593b6-88d8-429e-abea-c66b0314c35a.jpg',
'NOW_SHOWING', NOW(), NOW()),

-- 2. Animation / Marvel (Upcoming)
('Spider-Man: Beyond the Spider-Verse', 
'Miles Morales tiếp tục cuộc phiêu lưu đa vũ trụ với những Spider-People mới. Một câu chuyện hoành tráng về sự trưởng thành và trách nhiệm với những pha hành động đầy màu sắc.',
140, 'Hoạt Hình, Hành Động', 'T13',
'/assets/movie/27a49900-1ec6-4f56-82c0-40d1c44a9e21.jpg',
'COMING_SOON', NOW(), NOW()),

-- 3. Disney Animation
('Inside Out 2', 
'Riley giờ đã là một thiếu niên và những cảm xúc mới xuất hiện trong đầu cô. Joy, Sadness và những cảm xúc quen thuộc phải học cách làm việc với Anxiety, Envy và những người bạn mới.',
96, 'Hoạt Hình, Gia Đình, Tâm Lý', 'P',
'/assets/movie/299f9969-d455-41a0-808e-5ea2c4fc1001.jpg',
'NOW_SHOWING', NOW(), NOW()),

-- 4. Disney Animation
('Moana 2', 
'Moana bắt đầu hành trình mới đầy mạo hiểm cùng thủy thủ đoàn đặc biệt sau khi nhận được lời kêu gọi từ các tổ tiên tìm đường của mình.',
100, 'Hoạt Hình, Phiêu Lưu, Gia Đình', 'P',
'/assets/movie/4612a3d9-1544-4589-af66-9006c98f0a72.jpg',
'NOW_SHOWING', NOW(), NOW()),

-- 5. Musical / Fantasy
('Wicked', 
'Câu chuyện chưa được kể về các phù thủy xứ Oz. Elphaba, một cô gái trẻ bị hiểu lầm vì màu da xanh lá bất thường, và Glinda, một cô gái khao khát được yêu mến.',
160, 'Ca Nhạc, Kỳ Ảo - Thần Thoại', 'T13',
'/assets/movie/57719151-f0de-457d-a776-0adb6e661aae.jpg',
'NOW_SHOWING', NOW(), NOW()),

-- 6. Historical Epic / Action
('Gladiator II', 
'Nhiều năm sau cái chết của Maximus, Lucius - cháu trai của Marcus Aurelius - buộc phải bước vào Đấu trường La Mã khi quê hương anh bị thống trị bởi các hoàng đế tàn bạo.',
148, 'Hành Động, Lịch Sử - Sử Thi', 'T18',
'/assets/movie/5ab79b99-3df7-40c1-a771-31a9a08a7744.jpg',
'NOW_SHOWING', NOW(), NOW()),

-- 7. Horror
('Smile 2', 
'Sau những sự kiện kinh hoàng từ phần đầu, một ngôi sao nhạc Pop hàng đầu bắt đầu trải qua những hiện tượng đáng sợ không thể giải thích khi bắt đầu tour diễn toàn cầu.',
127, 'Kinh Dị, Bí Ẩn - Trinh Thám', 'T18',
'/assets/movie/5db2b56b-db46-47b8-90e2-e9cd41f68caa.jpg',
'NOW_SHOWING', NOW(), NOW()),

-- 8. Sci-Fi Epic (Upcoming)
('Avatar 3: Fire and Ash', 
'Jake Sully và gia đình tiếp tục cuộc phiêu lưu trên hành tinh Pandora, khám phá bộ tộc Tro Tàn nguy hiểm và đối mặt những thử thách nghiệt ngã hơn.',
190, 'Khoa Học Viễn Tưởng, Phiêu Lưu', 'T13',
'/assets/movie/910a94db-6d8c-43d2-805f-b7b8ae675f0c.jpg',
'COMING_SOON', NOW(), NOW()),

-- 9. Crime / Action (Upcoming)
('The Batman 2', 
'Người Dơi tiếp tục hành trình thực thi lý tưởng tại thành phố Gotham đen tối, đối mặt với những âm mưu tàn độc mới đe dọa sự tồn vong của thành phố.',
155, 'Tội Phạm, Hành Động', 'T16',
'/assets/movie/a97eb03e-d8a6-4015-bc9b-797d4b63cb6c.jpeg',
'COMING_SOON', NOW(), NOW()),

-- 10. Vietnamese Movie 1
('Mai', 
'Câu chuyện về người phụ nữ tên Mai và cuộc đời đầy thăng trầm của cô. Một tác phẩm điện ảnh Việt Nam sâu sắc về tình yêu, gia đình và khát vọng sống.',
131, 'Điện Ảnh Việt, Tâm Lý, Tình Cảm', 'T16',
'/assets/movie/af41e012-d0bc-491e-bbf9-ceb24497c54a.jpg',
'NOW_SHOWING', NOW(), NOW()),

-- 11. Vietnamese Movie 2
('Đào, Phở và Piano', 
'Bộ phim lấy bối cảnh Hà Nội mùa đông 1946, kể về tình yêu đẹp đẽ, lòng yêu nước và tinh thần kiên cường của người dân Hà thành trong khói lửa chiến tranh.',
110, 'Điện Ảnh Việt, Lịch Sử - Sử Thi, Tình Cảm', 'T13',
'/assets/movie/b552eeea-4fb8-477d-b0fc-bdf716a3ede5.jpg',
'NOW_SHOWING', NOW(), NOW()),

-- 12. Sci-Fi Blockbuster
('Dune: Part Two', 
'Paul Atreides hợp lực cùng Chani và người Fremen để trả thù những kẻ đã hủy hoại gia đình anh, đối mặt với lựa chọn giữa tình yêu và số phận vũ trụ.',
166, 'Khoa Học Viễn Tưởng, Phiêu Lưu', 'T13',
'/assets/movie/c52746d7-3bdd-4931-9060-e091618af82a.jpg',
'NOW_SHOWING', NOW(), NOW()),

-- 13. Anime / Mystery
('Detective Conan: The Million-dollar Pentagram', 
'Siêu đạo chích Kid nhắm đến thanh bảo kiếm tại Hakodate. Conan và các bạn bước vào trận chiến suy luận gay cấn liên quan đến kho báu lịch sử.',
111, 'Anime, Bí Ẩn - Trinh Thám', 'P',
'/assets/movie/d1d0a8bd-c0fd-4259-896b-1ac2948c0214.jpg',
'NOW_SHOWING', NOW(), NOW()),

-- 14. Vietnamese Movie 3
('Lật Mặt 7: Một Điều Ước', 
'Bộ phim gia đình xúc động về bà Hai và 5 người con trưởng thành. Khi biến cố xảy ra, tình thân và sự hiếu thảo được thử thách một cách chân thực nhất.',
138, 'Điện Ảnh Việt, Gia Đình, Tâm Lý', 'P',
'/assets/movie/e27e01f6-080c-4d1c-bfc0-02f0db76cb08.jpg',
'NOW_SHOWING', NOW(), NOW()),

-- 15. Action / Monster
('Godzilla x Kong: The New Empire', 
'Kong và Godzilla phải gạt bỏ mâu thuẫn để hợp sức chống lại một mối đe dọa khổng lồ mới đang ẩn nấp trong lòng Trái Đất.',
115, 'Hành Động, Khoa Học Viễn Tưởng', 'T13',
'/assets/movie/e71a4dfe-3813-49fe-a183-38cea89bdae4.jpg',
'NOW_SHOWING', NOW(), NOW()),

-- 16. Animation / Comedy
('Kung Fu Panda 4', 
'Po chuẩn bị trở thành Thủ Lĩnh Sống Của Thung Lũng Bình Yên nhưng phải tìm và huấn luyện một Chiến Binh Rồng mới để đối đầu với Phù Thủy Tắc Kè Tè.',
94, 'Hoạt Hình, Hài Hước, Hành Động', 'P',
'/assets/movie/fee5a3c1-19ce-47d9-8d70-88504f0b895a.jpg',
'NOW_SHOWING', NOW(), NOW());

-- ==============================================
-- CINEMAS DATA
-- ==============================================

INSERT INTO cinemas (name, address) VALUES
('SIDO Cinemas Hà Nội', '12 Phố Huế, Quận Hai Bà Trưng, Hà Nội'),
('SIDO Cinemas TP.HCM', '196 Pasteur, Quận 3, TP. Hồ Chí Minh'),
('SIDO Cinemas Đà Nẵng', '252 Võ Nguyên Giáp, Quận Sơn Trà, Đà Nẵng');

-- Gán rạp cho từng Manager (cinema_id: HN=1, HCM=2, DN=3)
UPDATE users SET cinema_id = 1 WHERE email = 'manager.hanoi@sidocinemas.com';
UPDATE users SET cinema_id = 2 WHERE email = 'manager.hcm@sidocinemas.com';
UPDATE users SET cinema_id = 3 WHERE email = 'manager.danang@sidocinemas.com';

-- ==============================================
-- ROOMS DATA
-- ==============================================

-- Rooms for Hanoi Cinema (Cinema ID: 1, Room IDs: 1..4)
INSERT INTO rooms (room_number, type, capacity, cinema_id) VALUES
('R01', 'TYPE_2D', 120, 1),
('R02', 'IMAX', 180, 1),
('R03', 'TYPE_3D', 80, 1),
('R04', 'TYPE_2D', 150, 1);

-- Rooms for Ho Chi Minh Cinema (Cinema ID: 2, Room IDs: 5..9)
INSERT INTO rooms (room_number, type, capacity, cinema_id) VALUES
('A1', 'IMAX', 200, 2),
('A2', 'TYPE_2D', 140, 2),
('A3', 'TYPE_3D', 60, 2),
('A4', 'TYPE_2D', 160, 2),
('A5', 'IMAX', 220, 2);

-- Rooms for Da Nang Cinema (Cinema ID: 3, Room IDs: 10..12)
INSERT INTO rooms (room_number, type, capacity, cinema_id) VALUES
('DN01', 'TYPE_2D', 130, 3),
('DN02', 'TYPE_3D', 70, 3),
('DN03', 'TYPE_2D', 140, 3);

-- ==============================================
-- SEATS DATA (All Rooms 1..12)
-- ==============================================

-- Helper macro function equivalent: inserting seats A-J for rooms 1..12
INSERT INTO seats (seat_row, seat_number, type, room_id)
SELECT 
    row_name,
    seat_num,
    CASE WHEN row_name IN ('C', 'D') AND seat_num BETWEEN 3 AND 10 THEN 'VIP' ELSE 'STANDARD' END,
    r_id
FROM 
    (VALUES ('A'), ('B'), ('C'), ('D'), ('E'), ('F'), ('G'), ('H'), ('I'), ('J')) AS rows(row_name),
    generate_series(1, 12) AS seat_num,
    generate_series(1, 12) AS r_id;

-- ==============================================
-- SHOWTIMES DATA (LỊCH CHIẾU CÓ ĐỦ CẢ 3 RẠP TRONG MỖI NGÀY)
-- ==============================================
-- Rạp 1 (Hà Nội): Room IDs 1, 2, 3, 4
-- Rạp 2 (TP.HCM): Room IDs 5, 6, 7, 8, 9
-- Rạp 3 (Đà Nẵng): Room IDs 10, 11, 12

INSERT INTO showtimes (movie_id, room_id, start_time, end_time, base_price) VALUES

-- ==============================================
-- NGÀY 1
-- ==============================================
-- 📍 Hà Nội (Cinema 1: Rooms 1, 2, 3, 4)
(1, 1, '2026-10-18 09:00:00', '2026-10-18 11:08:00', 110000),
(1, 2, '2026-10-18 14:00:00', '2026-10-18 16:08:00', 160000),
(3, 1, '2026-10-18 11:30:00', '2026-10-18 13:06:00', 90000),
(5, 3, '2026-10-18 17:00:00', '2026-10-18 19:40:00', 150000),
(10, 4, '2026-10-18 20:00:00', '2026-10-18 22:11:00', 100000),
(14, 2, '2026-10-18 20:30:00', '2026-10-18 22:48:00', 140000),

-- 📍 TP. Hồ Chí Minh (Cinema 2: Rooms 5, 6, 7, 8, 9)
(1, 5, '2026-10-18 09:30:00', '2026-10-18 11:38:00', 170000),
(3, 6, '2026-10-18 10:00:00', '2026-10-18 11:36:00', 100000),
(4, 7, '2026-10-18 13:00:00', '2026-10-18 14:40:00', 120000),
(6, 8, '2026-10-18 15:30:00', '2026-10-18 17:58:00', 130000),
(12, 9, '2026-10-18 18:30:00', '2026-10-18 21:16:00', 180000),
(14, 5, '2026-10-18 20:00:00', '2026-10-18 22:18:00', 150000),

-- 📍 Đà Nẵng (Cinema 3: Rooms 10, 11, 12)
(1, 10, '2026-10-18 10:00:00', '2026-10-18 12:08:00', 100000),
(3, 11, '2026-10-18 14:00:00', '2026-10-18 15:36:00', 90000),
(10, 12, '2026-10-18 16:30:00', '2026-10-18 18:41:00', 95000),
(15, 10, '2026-10-18 19:30:00', '2026-10-18 21:25:00', 110000),

-- ==============================================
-- NGÀY 2
-- ==============================================
-- 📍 Hà Nội (Cinema 1)
(1, 2, '2026-09-19 10:00:00', '2026-09-19 12:08:00', 160000),
(4, 1, '2026-09-19 13:00:00', '2026-09-19 14:40:00', 100000),
(6, 4, '2026-09-19 15:30:00', '2026-09-19 17:58:00', 130000),
(11, 3, '2026-09-19 18:30:00', '2026-09-19 20:20:00', 100000),
(12, 2, '2026-09-19 20:30:00', '2026-09-19 23:16:00', 180000),

-- 📍 TP. Hồ Chí Minh (Cinema 2)
(1, 9, '2026-09-19 10:30:00', '2026-09-19 12:38:00', 180000),
(7, 6, '2026-09-19 13:30:00', '2026-09-19 15:37:00', 120000),
(13, 7, '2026-09-19 16:00:00', '2026-09-19 17:51:00', 110000),
(14, 8, '2026-09-19 18:30:00', '2026-09-19 20:48:00', 140000),
(16, 5, '2026-09-19 21:00:00', '2026-09-19 22:34:00', 150000),

-- 📍 Đà Nẵng (Cinema 3)
(4, 10, '2026-09-19 09:30:00', '2026-09-19 11:10:00', 90000),
(1, 11, '2026-09-19 14:00:00', '2026-09-19 16:08:00', 110000),
(14, 12, '2026-09-19 17:30:00', '2026-09-19 19:48:00', 110000),
(15, 10, '2026-09-19 20:15:00', '2026-09-19 22:10:00', 100000),

-- ==============================================
-- NGÀY 3
-- ==============================================
-- 📍 Hà Nội (Cinema 1)
(3, 1, '2026-11-20 09:30:00', '2026-11-20 11:06:00', 95000),
(13, 2, '2026-11-20 13:00:00', '2026-11-20 14:51:00', 150000),
(14, 4, '2026-11-20 16:00:00', '2026-11-20 18:18:00', 120000),
(1, 2, '2026-11-20 19:00:00', '2026-11-20 21:08:00', 170000),

-- 📍 TP. Hồ Chí Minh (Cinema 2)
(3, 6, '2026-11-20 10:00:00', '2026-11-20 11:36:00', 100000),
(5, 7, '2026-11-20 13:30:00', '2026-11-20 16:10:00', 140000),
(10, 8, '2026-11-20 17:00:00', '2026-11-20 19:11:00', 120000),
(12, 5, '2026-11-20 19:30:00', '2026-11-20 22:16:00', 180000),

-- 📍 Đà Nẵng (Cinema 3)
(3, 10, '2026-11-20 10:30:00', '2026-11-20 12:06:00', 85000),
(6, 11, '2026-11-20 14:30:00', '2026-11-20 16:58:00', 110000),
(1, 12, '2026-11-20 18:00:00', '2026-11-20 20:08:00', 105000),

-- ==============================================
-- NGÀY 4: 2026-08-21
-- ==============================================
-- 📍 Hà Nội (Cinema 1)
(15, 1, '2026-08-21 10:00:00', '2026-08-21 11:55:00', 110000),
(16, 4, '2026-08-21 14:00:00', '2026-08-21 15:34:00', 100000),
(1, 2, '2026-08-21 18:00:00', '2026-08-21 20:08:00', 160000),

-- 📍 TP. Hồ Chí Minh (Cinema 2)
(1, 5, '2026-08-21 11:00:00', '2026-08-21 13:08:00', 170000),
(11, 6, '2026-08-21 15:00:00', '2026-08-21 16:50:00', 110000),
(14, 9, '2026-08-21 19:00:00', '2026-08-21 21:18:00', 170000),

-- 📍 Đà Nẵng (Cinema 3)
(13, 10, '2026-08-21 11:00:00', '2026-08-21 12:51:00', 95000),
(14, 12, '2026-08-21 15:30:00', '2026-08-21 17:48:00', 105000),
(1, 11, '2026-08-21 19:30:00', '2026-08-21 21:38:00', 110000),

-- ==============================================
-- NGÀY 5
-- ==============================================
-- 📍 Hà Nội (Cinema 1)
(1, 2, '2026-12-22 09:30:00', '2026-12-22 11:38:00', 160000),
(10, 1, '2026-12-22 14:00:00', '2026-12-22 16:11:00', 100000),
(14, 3, '2026-12-22 18:30:00', '2026-12-22 20:48:00', 140000),

-- 📍 TP. Hồ Chí Minh (Cinema 2)
(4, 7, '2026-12-22 10:00:00', '2026-12-22 11:40:00', 120000),
(12, 5, '2026-12-22 14:30:00', '2026-12-22 17:16:00', 180000),
(1, 9, '2026-12-22 19:30:00', '2026-12-22 21:38:00', 180000),

-- 📍 Đà Nẵng (Cinema 3)
(3, 10, '2026-12-22 10:00:00', '2026-12-22 11:36:00', 90000),
(10, 11, '2026-12-22 15:00:00', '2026-12-22 17:11:00', 95000),
(15, 12, '2026-12-22 19:00:00', '2026-12-22 20:55:00', 105000);

-- ==============================================
-- COMBO ITEMS DATA (Bắp nước cho từng rạp)
-- ==============================================

INSERT INTO combo_items (cinema_id, name, description, price, image_url, is_available, created_at, updated_at) VALUES
-- 📍 Rạp 1 (Hà Nội)
(1, 'Combo Solo', '1 Bắp lớn + 1 Nước ngọt lớn', 85000, 'https://cdn.galaxycine.vn/media/2024/3/29/combo-1-big_1711698822998.jpg', true, NOW(), NOW()),
(1, 'Combo Couple', '1 Bắp lớn + 2 Nước ngọt lớn', 105000, 'https://cdn.galaxycine.vn/media/2024/3/29/combo-2-big_1711698829562.jpg', true, NOW(), NOW()),
(1, 'Combo Family', '2 Bắp lớn + 4 Nước ngọt lớn', 195000, 'https://cdn.galaxycine.vn/media/2024/3/29/combo-2-big_1711698829562.jpg', true, NOW(), NOW()),

-- 📍 Rạp 2 (TP.HCM)
(2, 'Combo Cơ Bản', '1 Bắp + 1 Nước', 75000, 'https://cdn.galaxycine.vn/media/2024/3/29/combo-1-big_1711698822998.jpg', true, NOW(), NOW()),
(2, 'Combo Tình Yêu', '1 Bắp phô mai + 2 Nước', 115000, 'https://cdn.galaxycine.vn/media/2024/3/29/combo-2-big_1711698829562.jpg', true, NOW(), NOW()),
(2, 'Snack Thêm', 'Xúc xích + Khoai tây chiên', 65000, 'https://cdn.galaxycine.vn/media/2024/3/29/combo-1-big_1711698822998.jpg', true, NOW(), NOW()),

-- 📍 Rạp 3 (Đà Nẵng)
(3, 'Đà Nẵng Combo 1', '1 Bắp vừa + 1 Nước', 70000, 'https://cdn.galaxycine.vn/media/2024/3/29/combo-1-big_1711698822998.jpg', true, NOW(), NOW()),
(3, 'Đà Nẵng Combo 2', '1 Bắp lớn + 2 Nước', 95000, 'https://cdn.galaxycine.vn/media/2024/3/29/combo-2-big_1711698829562.jpg', true, NOW(), NOW());

-- ==============================================
-- SUMMARY
-- ==============================================
-- Seed data hoàn chỉnh bao gồm:
-- ✅ 7 Nguời dùng (1 Admin, 3 Managers cho 3 rạp, 3 Khách hàng)
-- ✅ 16 Thể loại / Danh mục phim phong phú
-- ✅ 16 Phim đa dạng (Blockbuster Mỹ, Anime Nhật, Điện Ảnh Việt, Đang chiếu & Sắp chiếu)
-- ✅ 3 Rạp chiếu (Hà Nội, TP.HCM, Đà Nẵng)
-- ✅ 12 Phòng chiếu (2D, 3D, IMAX)
-- ✅ 1440 Ghế (Thường, VIP cho cả 12 phòng)
-- ✅ 50+ Suất chiếu trải dài liên tục, ĐẢM BẢO CẢ 3 RẠP ĐỀU CÓ SUẤT CHIẾU TRONG MỖI NGÀY
-- ==============================================
