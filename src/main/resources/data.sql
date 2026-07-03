-- Disable SQL-based seeding in favor of Java DataSeeder
-- To enable SQL seeding, uncomment the lines below and comment out DataSeeder.java

-- ==============================================
-- SEED DATA FOR CINEMA BOOKING SYSTEM
-- ==============================================

-- Xóa toàn bộ data cũ và reset sequence (Hibernate đã tạo bảng trước khi script này chạy)
TRUNCATE TABLE payments, bookings, tickets, seats, showtimes, rooms, cinemas, movies, users RESTART IDENTITY CASCADE;

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
-- MOVIES DATA - XU HƯỚNG 2024-2026
-- ==============================================

-- Hollywood Blockbusters
INSERT INTO movies (title, description, duration, genre, age_rating, poster_url, status, created_at, updated_at) VALUES
-- Marvel & DC Movies
('Deadpool & Wolverine', 
'Wade Wilson sống cuộc sống yên bình khi Logan thuyết phục anh ta tham gia vào nhiệm vụ cứu thế giới của mình. Họ phải đối mặt với kẻ thù chung trong cuộc phiêu lưu đầy hành động và hài hước.',
128, 'Hành động, Hài, Siêu anh hùng', 'T16',
'https://image.tmdb.org/t/p/w500/8cdWjvZQUExUUTzyp4t6EDMubfO.jpg',
'NOW_SHOWING', NOW(), NOW()),

('Spider-Man: Beyond the Spider-Verse', 
'Miles Morales tiếp tục cuộc phiêu lưu đa vũ trụ với những Spider-People mới. Một câu chuyện hoành tráng về sự trưởng thành và trách nhiệm với những pha hành động đầy màu sắc.',
140, 'Hoạt hình, Hành động, Siêu anh hùng', 'T13',
'https://image.tmdb.org/t/p/w500/5cAuJOfd7NrVFA93e2tdGTPzEAF.jpg',
'COMING_SOON', NOW(), NOW()),

-- Disney & Animation
('Inside Out 2', 
'Riley giờ đã là một thiếu niên và những cảm xúc mới xuất hiện trong đầu cô. Joy, Sadness và những cảm xúc quen thuộc phải học cách làm việc với Anxiety, Envy và những người bạn mới.',
96, 'Hoạt hình, Gia đình, Tâm lý', 'P',
'https://image.tmdb.org/t/p/w500/vpnVM9B6NMmQpWeZvzLvDESb2QY.jpg',
'NOW_SHOWING', NOW(), NOW()),

('Moana 2', 
'Moana embarks on an expansive new voyage alongside a crew of unlikely seafarers. After receiving an unexpected call from her wayfinding ancestors, she must journey to the far seas of Oceania.',
100, 'Hoạt hình, Phiêu lưu, Gia đình', 'P',
'https://image.tmdb.org/t/p/w500/4YZpsylmjHbqeWzjKpUEF8gcLNW.jpg',
'NOW_SHOWING', NOW(), NOW()),

-- Action & Thriller
('Wicked', 
'Câu chuyện chưa được kể về các phù thủy xứ Oz. Elphaba, một cô gái trẻ bị hiểu lầm vì màu da xanh lá bất thường, và Glinda, một cô gái dân dã khao khát được yêu mến.',
160, 'Ca nhạc, Kỳ ảo, Chính kịch', 'T13',
'https://image.tmdb.org/t/p/w500/c5Tqxeo1UpBvnAc3csUm7j3hlQl.jpg',
'NOW_SHOWING', NOW(), NOW()),

('Gladiator II', 
'Sau nhiều năm sau cái chết của Maximus, Lucius - cháu trai của Marcus Aurelius - được buộc phải vào Đấu trường La Mã sau khi quê hương anh bị chinh phục bởi các hoàng đế bạo chúa.',
148, 'Hành động, Sử thi, Chính kịch', 'T18',
'https://image.tmdb.org/t/p/w500/2cxhvwyEwRlysAmRH4iodkvo0z5.jpg',
'NOW_SHOWING', NOW(), NOW()),

-- Horror & Thriller
('Smile 2', 
'Về sau những sự kiện kinh hoàng từ phần đầu, một ca sĩ nổi tiếng bắt đầu trải qua những hiện tượng đáng sợ khi chuẩn bị cho tour diễn thế giới của mình.',
127, 'Kinh dị, Tâm lý, Thriller', 'T18',
'https://image.tmdb.org/t/p/w500/ht8Uv9QPv9y7K0RvUyJIaXOZTfd.jpg',
'NOW_SHOWING', NOW(), NOW()),

-- Upcoming 2025-2026
('Avatar 3: Fire and Ash', 
'Jake Sully và gia đình tiếp tục cuộc phiêu lưu trên Pandora, khám phá những vùng đất mới và đối mặt với những thử thách chưa từng có.',
190, 'Khoa học viễn tưởng, Phiêu lưu, Hành động', 'T13',
'https://image.tmdb.org/t/p/w500/t6HIqrRAclMCA60NsSmeqe9RmNV.jpg',
'COMING_SOON', NOW(), NOW()),

('The Batman 2', 
'Robert Pattinson trở lại với vai Người Dơi trong cuộc phiêu lưu đen tối mới, đối mặt với những kẻ thù nguy hiểm hơn trong Gotham City.',
155, 'Hành động, Tội phạm, Thriller', 'T16',
'https://image.tmdb.org/t/p/w500/b0PlHKk2uTxlaLfKz8HbVjmdVl8.jpg',
'COMING_SOON', NOW(), NOW()),

-- Vietnamese Movies
('Mai', 
'Câu chuyện về người phụ nữ tên Mai và cuộc đời đầy thăng trầm của cô. Một tác phẩm điện ảnh Việt Nam sâu sắc về tình yêu và cuộc sống.',
131, 'Chính kịch, Tình cảm', 'T16',
'https://image.tmdb.org/t/p/w500/mai2024poster.jpg',
'NOW_SHOWING', NOW(), NOW()),

('Đào, Phở và Piano', 
'Bộ phim lấy bối cảnh Hà Nội thời kỳ kháng chiến, kể về tình yêu đẹp đẽ giữa những ngày tháng đầy khó khăn qua góc nhìn của một gia đình Việt Nam.',
110, 'Chính kịch, Lịch sử, Tình cảm', 'T13',
'https://image.tmdb.org/t/p/w500/daophopiano2023.jpg',
'NOW_SHOWING', NOW(), NOW());

-- ==============================================
-- CINEMAS DATA
-- ==============================================

INSERT INTO cinemas (name, address) VALUES
('SIDO Cinemas Hà Nội', '12 Phố Huế, Quận Hai Bà Trưng, Hà Nội'),
('SIDO Cinemas TP.HCM', '196 Pasteur, Quận 3, TP. Hồ Chí Minh'),
('SIDO Cinemas Đà Nẵng', '252 Võ Nguyên Giáp, Quận Sơn Trà, Đà Nẵng');

-- ==============================================
-- ROOMS DATA
-- ==============================================

-- Rooms for Hanoi Cinema (Cinema ID: 1)
INSERT INTO rooms (room_number, type, capacity, cinema_id) VALUES
('R01', 'TYPE_2D', 120, 1),
('R02', 'IMAX', 180, 1),
('R03', 'TYPE_3D', 80, 1),
('R04', 'TYPE_2D', 150, 1);

-- Rooms for Ho Chi Minh Cinema (Cinema ID: 2)
INSERT INTO rooms (room_number, type, capacity, cinema_id) VALUES
('A1', 'IMAX', 200, 2),
('A2', 'TYPE_2D', 140, 2),
('A3', 'TYPE_3D', 60, 2),
('A4', 'TYPE_2D', 160, 2),
('A5', 'IMAX', 220, 2);

-- Rooms for Da Nang Cinema (Cinema ID: 3)
INSERT INTO rooms (room_number, type, capacity, cinema_id) VALUES
('DN01', 'TYPE_2D', 130, 3),
('DN02', 'TYPE_3D', 70, 3),
('DN03', 'TYPE_2D', 140, 3);

-- ==============================================
-- SEATS DATA (Sample for Room 1 only)
-- ==============================================

-- Generate seats for Room R01 (120 seats: 10 rows x 12 seats each)
INSERT INTO seats (seat_row, seat_number, type, room_id) VALUES
-- Row A
('A', 1, 'STANDARD', 1), ('A', 2, 'STANDARD', 1), ('A', 3, 'STANDARD', 1), ('A', 4, 'STANDARD', 1),
('A', 5, 'STANDARD', 1), ('A', 6, 'STANDARD', 1), ('A', 7, 'STANDARD', 1), ('A', 8, 'STANDARD', 1),
('A', 9, 'STANDARD', 1), ('A', 10, 'STANDARD', 1), ('A', 11, 'STANDARD', 1), ('A', 12, 'STANDARD', 1),

-- Row B
('B', 1, 'STANDARD', 1), ('B', 2, 'STANDARD', 1), ('B', 3, 'STANDARD', 1), ('B', 4, 'STANDARD', 1),
('B', 5, 'STANDARD', 1), ('B', 6, 'STANDARD', 1), ('B', 7, 'STANDARD', 1), ('B', 8, 'STANDARD', 1),
('B', 9, 'STANDARD', 1), ('B', 10, 'STANDARD', 1), ('B', 11, 'STANDARD', 1), ('B', 12, 'STANDARD', 1),

-- Row C (VIP seats in the middle)
('C', 1, 'STANDARD', 1), ('C', 2, 'STANDARD', 1), ('C', 3, 'VIP', 1), ('C', 4, 'VIP', 1),
('C', 5, 'VIP', 1), ('C', 6, 'VIP', 1), ('C', 7, 'VIP', 1), ('C', 8, 'VIP', 1),
('C', 9, 'VIP', 1), ('C', 10, 'VIP', 1), ('C', 11, 'STANDARD', 1), ('C', 12, 'STANDARD', 1),

-- Row D (VIP seats in the middle)
('D', 1, 'STANDARD', 1), ('D', 2, 'STANDARD', 1), ('D', 3, 'VIP', 1), ('D', 4, 'VIP', 1),
('D', 5, 'VIP', 1), ('D', 6, 'VIP', 1), ('D', 7, 'VIP', 1), ('D', 8, 'VIP', 1),
('D', 9, 'VIP', 1), ('D', 10, 'VIP', 1), ('D', 11, 'STANDARD', 1), ('D', 12, 'STANDARD', 1),

-- Rows E-J (Standard seats)
('E', 1, 'STANDARD', 1), ('E', 2, 'STANDARD', 1), ('E', 3, 'STANDARD', 1), ('E', 4, 'STANDARD', 1),
('E', 5, 'STANDARD', 1), ('E', 6, 'STANDARD', 1), ('E', 7, 'STANDARD', 1), ('E', 8, 'STANDARD', 1),
('E', 9, 'STANDARD', 1), ('E', 10, 'STANDARD', 1), ('E', 11, 'STANDARD', 1), ('E', 12, 'STANDARD', 1),

('F', 1, 'STANDARD', 1), ('F', 2, 'STANDARD', 1), ('F', 3, 'STANDARD', 1), ('F', 4, 'STANDARD', 1),
('F', 5, 'STANDARD', 1), ('F', 6, 'STANDARD', 1), ('F', 7, 'STANDARD', 1), ('F', 8, 'STANDARD', 1),
('F', 9, 'STANDARD', 1), ('F', 10, 'STANDARD', 1), ('F', 11, 'STANDARD', 1), ('F', 12, 'STANDARD', 1),

('G', 1, 'STANDARD', 1), ('G', 2, 'STANDARD', 1), ('G', 3, 'STANDARD', 1), ('G', 4, 'STANDARD', 1),
('G', 5, 'STANDARD', 1), ('G', 6, 'STANDARD', 1), ('G', 7, 'STANDARD', 1), ('G', 8, 'STANDARD', 1),
('G', 9, 'STANDARD', 1), ('G', 10, 'STANDARD', 1), ('G', 11, 'STANDARD', 1), ('G', 12, 'STANDARD', 1),

('H', 1, 'STANDARD', 1), ('H', 2, 'STANDARD', 1), ('H', 3, 'STANDARD', 1), ('H', 4, 'STANDARD', 1),
('H', 5, 'STANDARD', 1), ('H', 6, 'STANDARD', 1), ('H', 7, 'STANDARD', 1), ('H', 8, 'STANDARD', 1),
('H', 9, 'STANDARD', 1), ('H', 10, 'STANDARD', 1), ('H', 11, 'STANDARD', 1), ('H', 12, 'STANDARD', 1),

('I', 1, 'STANDARD', 1), ('I', 2, 'STANDARD', 1), ('I', 3, 'STANDARD', 1), ('I', 4, 'STANDARD', 1),
('I', 5, 'STANDARD', 1), ('I', 6, 'STANDARD', 1), ('I', 7, 'STANDARD', 1), ('I', 8, 'STANDARD', 1),
('I', 9, 'STANDARD', 1), ('I', 10, 'STANDARD', 1), ('I', 11, 'STANDARD', 1), ('I', 12, 'STANDARD', 1),

('J', 1, 'STANDARD', 1), ('J', 2, 'STANDARD', 1), ('J', 3, 'STANDARD', 1), ('J', 4, 'STANDARD', 1),
('J', 5, 'STANDARD', 1), ('J', 6, 'STANDARD', 1), ('J', 7, 'STANDARD', 1), ('J', 8, 'STANDARD', 1),
('J', 9, 'STANDARD', 1), ('J', 10, 'STANDARD', 1), ('J', 11, 'STANDARD', 1), ('J', 12, 'STANDARD', 1);

-- ==============================================
-- SHOWTIMES DATA (Sample for next 3 days)
-- ==============================================

-- Today's showtimes
INSERT INTO showtimes (movie_id, room_id, start_time, end_time, base_price) VALUES
-- Deadpool & Wolverine
(1, 1, '2026-06-30 09:00:00', '2026-06-30 11:08:00', 120000),
(1, 2, '2026-06-30 14:30:00', '2026-06-30 16:38:00', 180000),
(1, 3, '2026-06-30 20:00:00', '2026-06-30 22:08:00', 250000),

-- Inside Out 2
(3, 1, '2026-06-30 11:30:00', '2026-06-30 13:06:00', 100000),
(3, 4, '2026-06-30 16:00:00', '2026-06-30 17:36:00', 120000),

-- Moana 2
(4, 1, '2026-06-30 15:00:00', '2026-06-30 16:40:00', 110000),
(4, 2, '2026-06-30 18:30:00', '2026-06-30 20:10:00', 150000),

-- Wicked
(5, 3, '2026-06-30 13:00:00', '2026-06-30 15:40:00', 200000),
(5, 2, '2026-06-30 21:00:00', '2026-06-30 23:40:00', 220000),

-- Gladiator II
(6, 4, '2026-06-30 19:30:00', '2026-06-30 21:58:00', 140000);

-- Tomorrow's showtimes
INSERT INTO showtimes (movie_id, room_id, start_time, end_time, base_price) VALUES
-- Deadpool & Wolverine
(1, 1, '2026-07-01 10:00:00', '2026-07-01 12:08:00', 120000),
(1, 2, '2026-07-01 15:30:00', '2026-07-01 17:38:00', 180000),
(1, 3, '2026-07-01 21:00:00', '2026-07-01 23:08:00', 250000),

-- Inside Out 2
(3, 1, '2026-07-01 12:30:00', '2026-07-01 14:06:00', 100000),
(3, 4, '2026-07-01 17:00:00', '2026-07-01 18:36:00', 120000),

-- Vietnamese Movies
(10, 4, '2026-07-01 14:00:00', '2026-07-01 16:11:00', 90000),
(11, 1, '2026-07-01 18:00:00', '2026-07-01 19:50:00', 95000);

-- ==============================================
-- SUMMARY
-- ==============================================
-- This seed data includes:
-- ✅ 7 Users (1 Admin, 3 Managers, 3 Customers)
-- ✅ 11 Movies (Hollywood blockbusters + Vietnamese films)
-- ✅ 3 Cinemas (Hanoi, Ho Chi Minh, Da Nang)
-- ✅ 12 Rooms (Different types: STANDARD, IMAX, VIP)
-- ✅ 120 Seats (Sample for Room R01)
-- ✅ 17 Showtimes (Next 2 days)
-- 
-- Password for all users: "password" (encoded with BCrypt)
-- Movie posters use TMDB image URLs for realistic data
-- Showtimes include popular movies with realistic pricing
-- ==============================================