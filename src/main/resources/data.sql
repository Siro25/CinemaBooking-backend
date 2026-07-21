-- Disable SQL-based seeding in favor of Java DataSeeder
-- To enable SQL seeding, uncomment the lines below and comment out DataSeeder.java

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
-- GENRES DATA (Thể loại phim)
-- ==============================================

INSERT INTO genres (name, description, created_at) VALUES
('Hành Động', 'Phim có nhiều cảnh chiến đấu, rượt đuổi, đầy kịch tính', NOW()),
('Kinh Dị', 'Phim gây sợ hãi, hồi hộp, đầy ám ảnh', NOW()),
('Hài Hước', 'Phim mang lại tiếng cười, giải trí nhẹ nhàng', NOW()),
('Tình Cảm', 'Phim lãng mạn, cảm động, xúc tích', NOW()),
('Hoạt Hình', 'Phim hoạt hình dành cho mọi lứa tuổi', NOW()),
('Khoa Học Viễn Tưởng', 'Phim về tương lai, công nghệ, vũ trụ', NOW()),
('Phíu Lưu', 'Phim hành trình khám phá đầy gay cấn', NOW()),
('Tâm Lý', 'Phim khai thác nội tâm nhân vật sâu sắc', NOW()),
('Gia Đình', 'Phim giá trị gia đình, phù hợp mọi đối tượng', NOW()),
('Tội Phạm', 'Phim xã hội đen, trinh thám, đấu trí', NOW());

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

-- Gán rạp cho từng Manager (cinema_id theo thứ tự insert ở trên: HN=1, HCM=2, DN=3)
UPDATE users SET cinema_id = 1 WHERE email = 'manager.hanoi@sidocinemas.com';
UPDATE users SET cinema_id = 2 WHERE email = 'manager.hcm@sidocinemas.com';
UPDATE users SET cinema_id = 3 WHERE email = 'manager.danang@sidocinemas.com';

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
-- SEATS DATA (All Rooms)
-- ==============================================
-- Generate seats for Room 1
INSERT INTO seats (seat_row, seat_number, type, room_id) VALUES
('A', 1, 'STANDARD', 1), ('A', 2, 'STANDARD', 1), ('A', 3, 'STANDARD', 1), ('A', 4, 'STANDARD', 1),
('A', 5, 'STANDARD', 1), ('A', 6, 'STANDARD', 1), ('A', 7, 'STANDARD', 1), ('A', 8, 'STANDARD', 1),
('A', 9, 'STANDARD', 1), ('A', 10, 'STANDARD', 1), ('A', 11, 'STANDARD', 1), ('A', 12, 'STANDARD', 1),
('B', 1, 'STANDARD', 1), ('B', 2, 'STANDARD', 1), ('B', 3, 'STANDARD', 1), ('B', 4, 'STANDARD', 1),
('B', 5, 'STANDARD', 1), ('B', 6, 'STANDARD', 1), ('B', 7, 'STANDARD', 1), ('B', 8, 'STANDARD', 1),
('B', 9, 'STANDARD', 1), ('B', 10, 'STANDARD', 1), ('B', 11, 'STANDARD', 1), ('B', 12, 'STANDARD', 1),
('C', 1, 'STANDARD', 1), ('C', 2, 'STANDARD', 1), ('C', 3, 'VIP', 1), ('C', 4, 'VIP', 1),
('C', 5, 'VIP', 1), ('C', 6, 'VIP', 1), ('C', 7, 'VIP', 1), ('C', 8, 'VIP', 1),
('C', 9, 'VIP', 1), ('C', 10, 'VIP', 1), ('C', 11, 'STANDARD', 1), ('C', 12, 'STANDARD', 1),
('D', 1, 'STANDARD', 1), ('D', 2, 'STANDARD', 1), ('D', 3, 'VIP', 1), ('D', 4, 'VIP', 1),
('D', 5, 'VIP', 1), ('D', 6, 'VIP', 1), ('D', 7, 'VIP', 1), ('D', 8, 'VIP', 1),
('D', 9, 'VIP', 1), ('D', 10, 'VIP', 1), ('D', 11, 'STANDARD', 1), ('D', 12, 'STANDARD', 1),
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

-- Generate seats for Room 2
INSERT INTO seats (seat_row, seat_number, type, room_id) VALUES
('A', 1, 'STANDARD', 2), ('A', 2, 'STANDARD', 2), ('A', 3, 'STANDARD', 2), ('A', 4, 'STANDARD', 2),
('A', 5, 'STANDARD', 2), ('A', 6, 'STANDARD', 2), ('A', 7, 'STANDARD', 2), ('A', 8, 'STANDARD', 2),
('A', 9, 'STANDARD', 2), ('A', 10, 'STANDARD', 2), ('A', 11, 'STANDARD', 2), ('A', 12, 'STANDARD', 2),
('B', 1, 'STANDARD', 2), ('B', 2, 'STANDARD', 2), ('B', 3, 'STANDARD', 2), ('B', 4, 'STANDARD', 2),
('B', 5, 'STANDARD', 2), ('B', 6, 'STANDARD', 2), ('B', 7, 'STANDARD', 2), ('B', 8, 'STANDARD', 2),
('B', 9, 'STANDARD', 2), ('B', 10, 'STANDARD', 2), ('B', 11, 'STANDARD', 2), ('B', 12, 'STANDARD', 2),
('C', 1, 'STANDARD', 2), ('C', 2, 'STANDARD', 2), ('C', 3, 'VIP', 2), ('C', 4, 'VIP', 2),
('C', 5, 'VIP', 2), ('C', 6, 'VIP', 2), ('C', 7, 'VIP', 2), ('C', 8, 'VIP', 2),
('C', 9, 'VIP', 2), ('C', 10, 'VIP', 2), ('C', 11, 'STANDARD', 2), ('C', 12, 'STANDARD', 2),
('D', 1, 'STANDARD', 2), ('D', 2, 'STANDARD', 2), ('D', 3, 'VIP', 2), ('D', 4, 'VIP', 2),
('D', 5, 'VIP', 2), ('D', 6, 'VIP', 2), ('D', 7, 'VIP', 2), ('D', 8, 'VIP', 2),
('D', 9, 'VIP', 2), ('D', 10, 'VIP', 2), ('D', 11, 'STANDARD', 2), ('D', 12, 'STANDARD', 2),
('E', 1, 'STANDARD', 2), ('E', 2, 'STANDARD', 2), ('E', 3, 'STANDARD', 2), ('E', 4, 'STANDARD', 2),
('E', 5, 'STANDARD', 2), ('E', 6, 'STANDARD', 2), ('E', 7, 'STANDARD', 2), ('E', 8, 'STANDARD', 2),
('E', 9, 'STANDARD', 2), ('E', 10, 'STANDARD', 2), ('E', 11, 'STANDARD', 2), ('E', 12, 'STANDARD', 2),
('F', 1, 'STANDARD', 2), ('F', 2, 'STANDARD', 2), ('F', 3, 'STANDARD', 2), ('F', 4, 'STANDARD', 2),
('F', 5, 'STANDARD', 2), ('F', 6, 'STANDARD', 2), ('F', 7, 'STANDARD', 2), ('F', 8, 'STANDARD', 2),
('F', 9, 'STANDARD', 2), ('F', 10, 'STANDARD', 2), ('F', 11, 'STANDARD', 2), ('F', 12, 'STANDARD', 2),
('G', 1, 'STANDARD', 2), ('G', 2, 'STANDARD', 2), ('G', 3, 'STANDARD', 2), ('G', 4, 'STANDARD', 2),
('G', 5, 'STANDARD', 2), ('G', 6, 'STANDARD', 2), ('G', 7, 'STANDARD', 2), ('G', 8, 'STANDARD', 2),
('G', 9, 'STANDARD', 2), ('G', 10, 'STANDARD', 2), ('G', 11, 'STANDARD', 2), ('G', 12, 'STANDARD', 2),
('H', 1, 'STANDARD', 2), ('H', 2, 'STANDARD', 2), ('H', 3, 'STANDARD', 2), ('H', 4, 'STANDARD', 2),
('H', 5, 'STANDARD', 2), ('H', 6, 'STANDARD', 2), ('H', 7, 'STANDARD', 2), ('H', 8, 'STANDARD', 2),
('H', 9, 'STANDARD', 2), ('H', 10, 'STANDARD', 2), ('H', 11, 'STANDARD', 2), ('H', 12, 'STANDARD', 2),
('I', 1, 'STANDARD', 2), ('I', 2, 'STANDARD', 2), ('I', 3, 'STANDARD', 2), ('I', 4, 'STANDARD', 2),
('I', 5, 'STANDARD', 2), ('I', 6, 'STANDARD', 2), ('I', 7, 'STANDARD', 2), ('I', 8, 'STANDARD', 2),
('I', 9, 'STANDARD', 2), ('I', 10, 'STANDARD', 2), ('I', 11, 'STANDARD', 2), ('I', 12, 'STANDARD', 2),
('J', 1, 'STANDARD', 2), ('J', 2, 'STANDARD', 2), ('J', 3, 'STANDARD', 2), ('J', 4, 'STANDARD', 2),
('J', 5, 'STANDARD', 2), ('J', 6, 'STANDARD', 2), ('J', 7, 'STANDARD', 2), ('J', 8, 'STANDARD', 2),
('J', 9, 'STANDARD', 2), ('J', 10, 'STANDARD', 2), ('J', 11, 'STANDARD', 2), ('J', 12, 'STANDARD', 2);

-- Generate seats for Room 3
INSERT INTO seats (seat_row, seat_number, type, room_id) VALUES
('A', 1, 'STANDARD', 3), ('A', 2, 'STANDARD', 3), ('A', 3, 'STANDARD', 3), ('A', 4, 'STANDARD', 3),
('A', 5, 'STANDARD', 3), ('A', 6, 'STANDARD', 3), ('A', 7, 'STANDARD', 3), ('A', 8, 'STANDARD', 3),
('A', 9, 'STANDARD', 3), ('A', 10, 'STANDARD', 3), ('A', 11, 'STANDARD', 3), ('A', 12, 'STANDARD', 3),
('B', 1, 'STANDARD', 3), ('B', 2, 'STANDARD', 3), ('B', 3, 'STANDARD', 3), ('B', 4, 'STANDARD', 3),
('B', 5, 'STANDARD', 3), ('B', 6, 'STANDARD', 3), ('B', 7, 'STANDARD', 3), ('B', 8, 'STANDARD', 3),
('B', 9, 'STANDARD', 3), ('B', 10, 'STANDARD', 3), ('B', 11, 'STANDARD', 3), ('B', 12, 'STANDARD', 3),
('C', 1, 'STANDARD', 3), ('C', 2, 'STANDARD', 3), ('C', 3, 'VIP', 3), ('C', 4, 'VIP', 3),
('C', 5, 'VIP', 3), ('C', 6, 'VIP', 3), ('C', 7, 'VIP', 3), ('C', 8, 'VIP', 3),
('C', 9, 'VIP', 3), ('C', 10, 'VIP', 3), ('C', 11, 'STANDARD', 3), ('C', 12, 'STANDARD', 3),
('D', 1, 'STANDARD', 3), ('D', 2, 'STANDARD', 3), ('D', 3, 'VIP', 3), ('D', 4, 'VIP', 3),
('D', 5, 'VIP', 3), ('D', 6, 'VIP', 3), ('D', 7, 'VIP', 3), ('D', 8, 'VIP', 3),
('D', 9, 'VIP', 3), ('D', 10, 'VIP', 3), ('D', 11, 'STANDARD', 3), ('D', 12, 'STANDARD', 3),
('E', 1, 'STANDARD', 3), ('E', 2, 'STANDARD', 3), ('E', 3, 'STANDARD', 3), ('E', 4, 'STANDARD', 3),
('E', 5, 'STANDARD', 3), ('E', 6, 'STANDARD', 3), ('E', 7, 'STANDARD', 3), ('E', 8, 'STANDARD', 3),
('E', 9, 'STANDARD', 3), ('E', 10, 'STANDARD', 3), ('E', 11, 'STANDARD', 3), ('E', 12, 'STANDARD', 3),
('F', 1, 'STANDARD', 3), ('F', 2, 'STANDARD', 3), ('F', 3, 'STANDARD', 3), ('F', 4, 'STANDARD', 3),
('F', 5, 'STANDARD', 3), ('F', 6, 'STANDARD', 3), ('F', 7, 'STANDARD', 3), ('F', 8, 'STANDARD', 3),
('F', 9, 'STANDARD', 3), ('F', 10, 'STANDARD', 3), ('F', 11, 'STANDARD', 3), ('F', 12, 'STANDARD', 3),
('G', 1, 'STANDARD', 3), ('G', 2, 'STANDARD', 3), ('G', 3, 'STANDARD', 3), ('G', 4, 'STANDARD', 3),
('G', 5, 'STANDARD', 3), ('G', 6, 'STANDARD', 3), ('G', 7, 'STANDARD', 3), ('G', 8, 'STANDARD', 3),
('G', 9, 'STANDARD', 3), ('G', 10, 'STANDARD', 3), ('G', 11, 'STANDARD', 3), ('G', 12, 'STANDARD', 3),
('H', 1, 'STANDARD', 3), ('H', 2, 'STANDARD', 3), ('H', 3, 'STANDARD', 3), ('H', 4, 'STANDARD', 3),
('H', 5, 'STANDARD', 3), ('H', 6, 'STANDARD', 3), ('H', 7, 'STANDARD', 3), ('H', 8, 'STANDARD', 3),
('H', 9, 'STANDARD', 3), ('H', 10, 'STANDARD', 3), ('H', 11, 'STANDARD', 3), ('H', 12, 'STANDARD', 3),
('I', 1, 'STANDARD', 3), ('I', 2, 'STANDARD', 3), ('I', 3, 'STANDARD', 3), ('I', 4, 'STANDARD', 3),
('I', 5, 'STANDARD', 3), ('I', 6, 'STANDARD', 3), ('I', 7, 'STANDARD', 3), ('I', 8, 'STANDARD', 3),
('I', 9, 'STANDARD', 3), ('I', 10, 'STANDARD', 3), ('I', 11, 'STANDARD', 3), ('I', 12, 'STANDARD', 3),
('J', 1, 'STANDARD', 3), ('J', 2, 'STANDARD', 3), ('J', 3, 'STANDARD', 3), ('J', 4, 'STANDARD', 3),
('J', 5, 'STANDARD', 3), ('J', 6, 'STANDARD', 3), ('J', 7, 'STANDARD', 3), ('J', 8, 'STANDARD', 3),
('J', 9, 'STANDARD', 3), ('J', 10, 'STANDARD', 3), ('J', 11, 'STANDARD', 3), ('J', 12, 'STANDARD', 3);

-- Generate seats for Room 4
INSERT INTO seats (seat_row, seat_number, type, room_id) VALUES
('A', 1, 'STANDARD', 4), ('A', 2, 'STANDARD', 4), ('A', 3, 'STANDARD', 4), ('A', 4, 'STANDARD', 4),
('A', 5, 'STANDARD', 4), ('A', 6, 'STANDARD', 4), ('A', 7, 'STANDARD', 4), ('A', 8, 'STANDARD', 4),
('A', 9, 'STANDARD', 4), ('A', 10, 'STANDARD', 4), ('A', 11, 'STANDARD', 4), ('A', 12, 'STANDARD', 4),
('B', 1, 'STANDARD', 4), ('B', 2, 'STANDARD', 4), ('B', 3, 'STANDARD', 4), ('B', 4, 'STANDARD', 4),
('B', 5, 'STANDARD', 4), ('B', 6, 'STANDARD', 4), ('B', 7, 'STANDARD', 4), ('B', 8, 'STANDARD', 4),
('B', 9, 'STANDARD', 4), ('B', 10, 'STANDARD', 4), ('B', 11, 'STANDARD', 4), ('B', 12, 'STANDARD', 4),
('C', 1, 'STANDARD', 4), ('C', 2, 'STANDARD', 4), ('C', 3, 'VIP', 4), ('C', 4, 'VIP', 4),
('C', 5, 'VIP', 4), ('C', 6, 'VIP', 4), ('C', 7, 'VIP', 4), ('C', 8, 'VIP', 4),
('C', 9, 'VIP', 4), ('C', 10, 'VIP', 4), ('C', 11, 'STANDARD', 4), ('C', 12, 'STANDARD', 4),
('D', 1, 'STANDARD', 4), ('D', 2, 'STANDARD', 4), ('D', 3, 'VIP', 4), ('D', 4, 'VIP', 4),
('D', 5, 'VIP', 4), ('D', 6, 'VIP', 4), ('D', 7, 'VIP', 4), ('D', 8, 'VIP', 4),
('D', 9, 'VIP', 4), ('D', 10, 'VIP', 4), ('D', 11, 'STANDARD', 4), ('D', 12, 'STANDARD', 4),
('E', 1, 'STANDARD', 4), ('E', 2, 'STANDARD', 4), ('E', 3, 'STANDARD', 4), ('E', 4, 'STANDARD', 4),
('E', 5, 'STANDARD', 4), ('E', 6, 'STANDARD', 4), ('E', 7, 'STANDARD', 4), ('E', 8, 'STANDARD', 4),
('E', 9, 'STANDARD', 4), ('E', 10, 'STANDARD', 4), ('E', 11, 'STANDARD', 4), ('E', 12, 'STANDARD', 4),
('F', 1, 'STANDARD', 4), ('F', 2, 'STANDARD', 4), ('F', 3, 'STANDARD', 4), ('F', 4, 'STANDARD', 4),
('F', 5, 'STANDARD', 4), ('F', 6, 'STANDARD', 4), ('F', 7, 'STANDARD', 4), ('F', 8, 'STANDARD', 4),
('F', 9, 'STANDARD', 4), ('F', 10, 'STANDARD', 4), ('F', 11, 'STANDARD', 4), ('F', 12, 'STANDARD', 4),
('G', 1, 'STANDARD', 4), ('G', 2, 'STANDARD', 4), ('G', 3, 'STANDARD', 4), ('G', 4, 'STANDARD', 4),
('G', 5, 'STANDARD', 4), ('G', 6, 'STANDARD', 4), ('G', 7, 'STANDARD', 4), ('G', 8, 'STANDARD', 4),
('G', 9, 'STANDARD', 4), ('G', 10, 'STANDARD', 4), ('G', 11, 'STANDARD', 4), ('G', 12, 'STANDARD', 4),
('H', 1, 'STANDARD', 4), ('H', 2, 'STANDARD', 4), ('H', 3, 'STANDARD', 4), ('H', 4, 'STANDARD', 4),
('H', 5, 'STANDARD', 4), ('H', 6, 'STANDARD', 4), ('H', 7, 'STANDARD', 4), ('H', 8, 'STANDARD', 4),
('H', 9, 'STANDARD', 4), ('H', 10, 'STANDARD', 4), ('H', 11, 'STANDARD', 4), ('H', 12, 'STANDARD', 4),
('I', 1, 'STANDARD', 4), ('I', 2, 'STANDARD', 4), ('I', 3, 'STANDARD', 4), ('I', 4, 'STANDARD', 4),
('I', 5, 'STANDARD', 4), ('I', 6, 'STANDARD', 4), ('I', 7, 'STANDARD', 4), ('I', 8, 'STANDARD', 4),
('I', 9, 'STANDARD', 4), ('I', 10, 'STANDARD', 4), ('I', 11, 'STANDARD', 4), ('I', 12, 'STANDARD', 4),
('J', 1, 'STANDARD', 4), ('J', 2, 'STANDARD', 4), ('J', 3, 'STANDARD', 4), ('J', 4, 'STANDARD', 4),
('J', 5, 'STANDARD', 4), ('J', 6, 'STANDARD', 4), ('J', 7, 'STANDARD', 4), ('J', 8, 'STANDARD', 4),
('J', 9, 'STANDARD', 4), ('J', 10, 'STANDARD', 4), ('J', 11, 'STANDARD', 4), ('J', 12, 'STANDARD', 4);

-- Generate seats for Room 5
INSERT INTO seats (seat_row, seat_number, type, room_id) VALUES
('A', 1, 'STANDARD', 5), ('A', 2, 'STANDARD', 5), ('A', 3, 'STANDARD', 5), ('A', 4, 'STANDARD', 5),
('A', 5, 'STANDARD', 5), ('A', 6, 'STANDARD', 5), ('A', 7, 'STANDARD', 5), ('A', 8, 'STANDARD', 5),
('A', 9, 'STANDARD', 5), ('A', 10, 'STANDARD', 5), ('A', 11, 'STANDARD', 5), ('A', 12, 'STANDARD', 5),
('B', 1, 'STANDARD', 5), ('B', 2, 'STANDARD', 5), ('B', 3, 'STANDARD', 5), ('B', 4, 'STANDARD', 5),
('B', 5, 'STANDARD', 5), ('B', 6, 'STANDARD', 5), ('B', 7, 'STANDARD', 5), ('B', 8, 'STANDARD', 5),
('B', 9, 'STANDARD', 5), ('B', 10, 'STANDARD', 5), ('B', 11, 'STANDARD', 5), ('B', 12, 'STANDARD', 5),
('C', 1, 'STANDARD', 5), ('C', 2, 'STANDARD', 5), ('C', 3, 'VIP', 5), ('C', 4, 'VIP', 5),
('C', 5, 'VIP', 5), ('C', 6, 'VIP', 5), ('C', 7, 'VIP', 5), ('C', 8, 'VIP', 5),
('C', 9, 'VIP', 5), ('C', 10, 'VIP', 5), ('C', 11, 'STANDARD', 5), ('C', 12, 'STANDARD', 5),
('D', 1, 'STANDARD', 5), ('D', 2, 'STANDARD', 5), ('D', 3, 'VIP', 5), ('D', 4, 'VIP', 5),
('D', 5, 'VIP', 5), ('D', 6, 'VIP', 5), ('D', 7, 'VIP', 5), ('D', 8, 'VIP', 5),
('D', 9, 'VIP', 5), ('D', 10, 'VIP', 5), ('D', 11, 'STANDARD', 5), ('D', 12, 'STANDARD', 5),
('E', 1, 'STANDARD', 5), ('E', 2, 'STANDARD', 5), ('E', 3, 'STANDARD', 5), ('E', 4, 'STANDARD', 5),
('E', 5, 'STANDARD', 5), ('E', 6, 'STANDARD', 5), ('E', 7, 'STANDARD', 5), ('E', 8, 'STANDARD', 5),
('E', 9, 'STANDARD', 5), ('E', 10, 'STANDARD', 5), ('E', 11, 'STANDARD', 5), ('E', 12, 'STANDARD', 5),
('F', 1, 'STANDARD', 5), ('F', 2, 'STANDARD', 5), ('F', 3, 'STANDARD', 5), ('F', 4, 'STANDARD', 5),
('F', 5, 'STANDARD', 5), ('F', 6, 'STANDARD', 5), ('F', 7, 'STANDARD', 5), ('F', 8, 'STANDARD', 5),
('F', 9, 'STANDARD', 5), ('F', 10, 'STANDARD', 5), ('F', 11, 'STANDARD', 5), ('F', 12, 'STANDARD', 5),
('G', 1, 'STANDARD', 5), ('G', 2, 'STANDARD', 5), ('G', 3, 'STANDARD', 5), ('G', 4, 'STANDARD', 5),
('G', 5, 'STANDARD', 5), ('G', 6, 'STANDARD', 5), ('G', 7, 'STANDARD', 5), ('G', 8, 'STANDARD', 5),
('G', 9, 'STANDARD', 5), ('G', 10, 'STANDARD', 5), ('G', 11, 'STANDARD', 5), ('G', 12, 'STANDARD', 5),
('H', 1, 'STANDARD', 5), ('H', 2, 'STANDARD', 5), ('H', 3, 'STANDARD', 5), ('H', 4, 'STANDARD', 5),
('H', 5, 'STANDARD', 5), ('H', 6, 'STANDARD', 5), ('H', 7, 'STANDARD', 5), ('H', 8, 'STANDARD', 5),
('H', 9, 'STANDARD', 5), ('H', 10, 'STANDARD', 5), ('H', 11, 'STANDARD', 5), ('H', 12, 'STANDARD', 5),
('I', 1, 'STANDARD', 5), ('I', 2, 'STANDARD', 5), ('I', 3, 'STANDARD', 5), ('I', 4, 'STANDARD', 5),
('I', 5, 'STANDARD', 5), ('I', 6, 'STANDARD', 5), ('I', 7, 'STANDARD', 5), ('I', 8, 'STANDARD', 5),
('I', 9, 'STANDARD', 5), ('I', 10, 'STANDARD', 5), ('I', 11, 'STANDARD', 5), ('I', 12, 'STANDARD', 5),
('J', 1, 'STANDARD', 5), ('J', 2, 'STANDARD', 5), ('J', 3, 'STANDARD', 5), ('J', 4, 'STANDARD', 5),
('J', 5, 'STANDARD', 5), ('J', 6, 'STANDARD', 5), ('J', 7, 'STANDARD', 5), ('J', 8, 'STANDARD', 5),
('J', 9, 'STANDARD', 5), ('J', 10, 'STANDARD', 5), ('J', 11, 'STANDARD', 5), ('J', 12, 'STANDARD', 5);

-- Generate seats for Room 6
INSERT INTO seats (seat_row, seat_number, type, room_id) VALUES
('A', 1, 'STANDARD', 6), ('A', 2, 'STANDARD', 6), ('A', 3, 'STANDARD', 6), ('A', 4, 'STANDARD', 6),
('A', 5, 'STANDARD', 6), ('A', 6, 'STANDARD', 6), ('A', 7, 'STANDARD', 6), ('A', 8, 'STANDARD', 6),
('A', 9, 'STANDARD', 6), ('A', 10, 'STANDARD', 6), ('A', 11, 'STANDARD', 6), ('A', 12, 'STANDARD', 6),
('B', 1, 'STANDARD', 6), ('B', 2, 'STANDARD', 6), ('B', 3, 'STANDARD', 6), ('B', 4, 'STANDARD', 6),
('B', 5, 'STANDARD', 6), ('B', 6, 'STANDARD', 6), ('B', 7, 'STANDARD', 6), ('B', 8, 'STANDARD', 6),
('B', 9, 'STANDARD', 6), ('B', 10, 'STANDARD', 6), ('B', 11, 'STANDARD', 6), ('B', 12, 'STANDARD', 6),
('C', 1, 'STANDARD', 6), ('C', 2, 'STANDARD', 6), ('C', 3, 'VIP', 6), ('C', 4, 'VIP', 6),
('C', 5, 'VIP', 6), ('C', 6, 'VIP', 6), ('C', 7, 'VIP', 6), ('C', 8, 'VIP', 6),
('C', 9, 'VIP', 6), ('C', 10, 'VIP', 6), ('C', 11, 'STANDARD', 6), ('C', 12, 'STANDARD', 6),
('D', 1, 'STANDARD', 6), ('D', 2, 'STANDARD', 6), ('D', 3, 'VIP', 6), ('D', 4, 'VIP', 6),
('D', 5, 'VIP', 6), ('D', 6, 'VIP', 6), ('D', 7, 'VIP', 6), ('D', 8, 'VIP', 6),
('D', 9, 'VIP', 6), ('D', 10, 'VIP', 6), ('D', 11, 'STANDARD', 6), ('D', 12, 'STANDARD', 6),
('E', 1, 'STANDARD', 6), ('E', 2, 'STANDARD', 6), ('E', 3, 'STANDARD', 6), ('E', 4, 'STANDARD', 6),
('E', 5, 'STANDARD', 6), ('E', 6, 'STANDARD', 6), ('E', 7, 'STANDARD', 6), ('E', 8, 'STANDARD', 6),
('E', 9, 'STANDARD', 6), ('E', 10, 'STANDARD', 6), ('E', 11, 'STANDARD', 6), ('E', 12, 'STANDARD', 6),
('F', 1, 'STANDARD', 6), ('F', 2, 'STANDARD', 6), ('F', 3, 'STANDARD', 6), ('F', 4, 'STANDARD', 6),
('F', 5, 'STANDARD', 6), ('F', 6, 'STANDARD', 6), ('F', 7, 'STANDARD', 6), ('F', 8, 'STANDARD', 6),
('F', 9, 'STANDARD', 6), ('F', 10, 'STANDARD', 6), ('F', 11, 'STANDARD', 6), ('F', 12, 'STANDARD', 6),
('G', 1, 'STANDARD', 6), ('G', 2, 'STANDARD', 6), ('G', 3, 'STANDARD', 6), ('G', 4, 'STANDARD', 6),
('G', 5, 'STANDARD', 6), ('G', 6, 'STANDARD', 6), ('G', 7, 'STANDARD', 6), ('G', 8, 'STANDARD', 6),
('G', 9, 'STANDARD', 6), ('G', 10, 'STANDARD', 6), ('G', 11, 'STANDARD', 6), ('G', 12, 'STANDARD', 6),
('H', 1, 'STANDARD', 6), ('H', 2, 'STANDARD', 6), ('H', 3, 'STANDARD', 6), ('H', 4, 'STANDARD', 6),
('H', 5, 'STANDARD', 6), ('H', 6, 'STANDARD', 6), ('H', 7, 'STANDARD', 6), ('H', 8, 'STANDARD', 6),
('H', 9, 'STANDARD', 6), ('H', 10, 'STANDARD', 6), ('H', 11, 'STANDARD', 6), ('H', 12, 'STANDARD', 6),
('I', 1, 'STANDARD', 6), ('I', 2, 'STANDARD', 6), ('I', 3, 'STANDARD', 6), ('I', 4, 'STANDARD', 6),
('I', 5, 'STANDARD', 6), ('I', 6, 'STANDARD', 6), ('I', 7, 'STANDARD', 6), ('I', 8, 'STANDARD', 6),
('I', 9, 'STANDARD', 6), ('I', 10, 'STANDARD', 6), ('I', 11, 'STANDARD', 6), ('I', 12, 'STANDARD', 6),
('J', 1, 'STANDARD', 6), ('J', 2, 'STANDARD', 6), ('J', 3, 'STANDARD', 6), ('J', 4, 'STANDARD', 6),
('J', 5, 'STANDARD', 6), ('J', 6, 'STANDARD', 6), ('J', 7, 'STANDARD', 6), ('J', 8, 'STANDARD', 6),
('J', 9, 'STANDARD', 6), ('J', 10, 'STANDARD', 6), ('J', 11, 'STANDARD', 6), ('J', 12, 'STANDARD', 6);

-- Generate seats for Room 7
INSERT INTO seats (seat_row, seat_number, type, room_id) VALUES
('A', 1, 'STANDARD', 7), ('A', 2, 'STANDARD', 7), ('A', 3, 'STANDARD', 7), ('A', 4, 'STANDARD', 7),
('A', 5, 'STANDARD', 7), ('A', 6, 'STANDARD', 7), ('A', 7, 'STANDARD', 7), ('A', 8, 'STANDARD', 7),
('A', 9, 'STANDARD', 7), ('A', 10, 'STANDARD', 7), ('A', 11, 'STANDARD', 7), ('A', 12, 'STANDARD', 7),
('B', 1, 'STANDARD', 7), ('B', 2, 'STANDARD', 7), ('B', 3, 'STANDARD', 7), ('B', 4, 'STANDARD', 7),
('B', 5, 'STANDARD', 7), ('B', 6, 'STANDARD', 7), ('B', 7, 'STANDARD', 7), ('B', 8, 'STANDARD', 7),
('B', 9, 'STANDARD', 7), ('B', 10, 'STANDARD', 7), ('B', 11, 'STANDARD', 7), ('B', 12, 'STANDARD', 7),
('C', 1, 'STANDARD', 7), ('C', 2, 'STANDARD', 7), ('C', 3, 'VIP', 7), ('C', 4, 'VIP', 7),
('C', 5, 'VIP', 7), ('C', 6, 'VIP', 7), ('C', 7, 'VIP', 7), ('C', 8, 'VIP', 7),
('C', 9, 'VIP', 7), ('C', 10, 'VIP', 7), ('C', 11, 'STANDARD', 7), ('C', 12, 'STANDARD', 7),
('D', 1, 'STANDARD', 7), ('D', 2, 'STANDARD', 7), ('D', 3, 'VIP', 7), ('D', 4, 'VIP', 7),
('D', 5, 'VIP', 7), ('D', 6, 'VIP', 7), ('D', 7, 'VIP', 7), ('D', 8, 'VIP', 7),
('D', 9, 'VIP', 7), ('D', 10, 'VIP', 7), ('D', 11, 'STANDARD', 7), ('D', 12, 'STANDARD', 7),
('E', 1, 'STANDARD', 7), ('E', 2, 'STANDARD', 7), ('E', 3, 'STANDARD', 7), ('E', 4, 'STANDARD', 7),
('E', 5, 'STANDARD', 7), ('E', 6, 'STANDARD', 7), ('E', 7, 'STANDARD', 7), ('E', 8, 'STANDARD', 7),
('E', 9, 'STANDARD', 7), ('E', 10, 'STANDARD', 7), ('E', 11, 'STANDARD', 7), ('E', 12, 'STANDARD', 7),
('F', 1, 'STANDARD', 7), ('F', 2, 'STANDARD', 7), ('F', 3, 'STANDARD', 7), ('F', 4, 'STANDARD', 7),
('F', 5, 'STANDARD', 7), ('F', 6, 'STANDARD', 7), ('F', 7, 'STANDARD', 7), ('F', 8, 'STANDARD', 7),
('F', 9, 'STANDARD', 7), ('F', 10, 'STANDARD', 7), ('F', 11, 'STANDARD', 7), ('F', 12, 'STANDARD', 7),
('G', 1, 'STANDARD', 7), ('G', 2, 'STANDARD', 7), ('G', 3, 'STANDARD', 7), ('G', 4, 'STANDARD', 7),
('G', 5, 'STANDARD', 7), ('G', 6, 'STANDARD', 7), ('G', 7, 'STANDARD', 7), ('G', 8, 'STANDARD', 7),
('G', 9, 'STANDARD', 7), ('G', 10, 'STANDARD', 7), ('G', 11, 'STANDARD', 7), ('G', 12, 'STANDARD', 7),
('H', 1, 'STANDARD', 7), ('H', 2, 'STANDARD', 7), ('H', 3, 'STANDARD', 7), ('H', 4, 'STANDARD', 7),
('H', 5, 'STANDARD', 7), ('H', 6, 'STANDARD', 7), ('H', 7, 'STANDARD', 7), ('H', 8, 'STANDARD', 7),
('H', 9, 'STANDARD', 7), ('H', 10, 'STANDARD', 7), ('H', 11, 'STANDARD', 7), ('H', 12, 'STANDARD', 7),
('I', 1, 'STANDARD', 7), ('I', 2, 'STANDARD', 7), ('I', 3, 'STANDARD', 7), ('I', 4, 'STANDARD', 7),
('I', 5, 'STANDARD', 7), ('I', 6, 'STANDARD', 7), ('I', 7, 'STANDARD', 7), ('I', 8, 'STANDARD', 7),
('I', 9, 'STANDARD', 7), ('I', 10, 'STANDARD', 7), ('I', 11, 'STANDARD', 7), ('I', 12, 'STANDARD', 7),
('J', 1, 'STANDARD', 7), ('J', 2, 'STANDARD', 7), ('J', 3, 'STANDARD', 7), ('J', 4, 'STANDARD', 7),
('J', 5, 'STANDARD', 7), ('J', 6, 'STANDARD', 7), ('J', 7, 'STANDARD', 7), ('J', 8, 'STANDARD', 7),
('J', 9, 'STANDARD', 7), ('J', 10, 'STANDARD', 7), ('J', 11, 'STANDARD', 7), ('J', 12, 'STANDARD', 7);

-- Generate seats for Room 8
INSERT INTO seats (seat_row, seat_number, type, room_id) VALUES
('A', 1, 'STANDARD', 8), ('A', 2, 'STANDARD', 8), ('A', 3, 'STANDARD', 8), ('A', 4, 'STANDARD', 8),
('A', 5, 'STANDARD', 8), ('A', 6, 'STANDARD', 8), ('A', 7, 'STANDARD', 8), ('A', 8, 'STANDARD', 8),
('A', 9, 'STANDARD', 8), ('A', 10, 'STANDARD', 8), ('A', 11, 'STANDARD', 8), ('A', 12, 'STANDARD', 8),
('B', 1, 'STANDARD', 8), ('B', 2, 'STANDARD', 8), ('B', 3, 'STANDARD', 8), ('B', 4, 'STANDARD', 8),
('B', 5, 'STANDARD', 8), ('B', 6, 'STANDARD', 8), ('B', 7, 'STANDARD', 8), ('B', 8, 'STANDARD', 8),
('B', 9, 'STANDARD', 8), ('B', 10, 'STANDARD', 8), ('B', 11, 'STANDARD', 8), ('B', 12, 'STANDARD', 8),
('C', 1, 'STANDARD', 8), ('C', 2, 'STANDARD', 8), ('C', 3, 'VIP', 8), ('C', 4, 'VIP', 8),
('C', 5, 'VIP', 8), ('C', 6, 'VIP', 8), ('C', 7, 'VIP', 8), ('C', 8, 'VIP', 8),
('C', 9, 'VIP', 8), ('C', 10, 'VIP', 8), ('C', 11, 'STANDARD', 8), ('C', 12, 'STANDARD', 8),
('D', 1, 'STANDARD', 8), ('D', 2, 'STANDARD', 8), ('D', 3, 'VIP', 8), ('D', 4, 'VIP', 8),
('D', 5, 'VIP', 8), ('D', 6, 'VIP', 8), ('D', 7, 'VIP', 8), ('D', 8, 'VIP', 8),
('D', 9, 'VIP', 8), ('D', 10, 'VIP', 8), ('D', 11, 'STANDARD', 8), ('D', 12, 'STANDARD', 8),
('E', 1, 'STANDARD', 8), ('E', 2, 'STANDARD', 8), ('E', 3, 'STANDARD', 8), ('E', 4, 'STANDARD', 8),
('E', 5, 'STANDARD', 8), ('E', 6, 'STANDARD', 8), ('E', 7, 'STANDARD', 8), ('E', 8, 'STANDARD', 8),
('E', 9, 'STANDARD', 8), ('E', 10, 'STANDARD', 8), ('E', 11, 'STANDARD', 8), ('E', 12, 'STANDARD', 8),
('F', 1, 'STANDARD', 8), ('F', 2, 'STANDARD', 8), ('F', 3, 'STANDARD', 8), ('F', 4, 'STANDARD', 8),
('F', 5, 'STANDARD', 8), ('F', 6, 'STANDARD', 8), ('F', 7, 'STANDARD', 8), ('F', 8, 'STANDARD', 8),
('F', 9, 'STANDARD', 8), ('F', 10, 'STANDARD', 8), ('F', 11, 'STANDARD', 8), ('F', 12, 'STANDARD', 8),
('G', 1, 'STANDARD', 8), ('G', 2, 'STANDARD', 8), ('G', 3, 'STANDARD', 8), ('G', 4, 'STANDARD', 8),
('G', 5, 'STANDARD', 8), ('G', 6, 'STANDARD', 8), ('G', 7, 'STANDARD', 8), ('G', 8, 'STANDARD', 8),
('G', 9, 'STANDARD', 8), ('G', 10, 'STANDARD', 8), ('G', 11, 'STANDARD', 8), ('G', 12, 'STANDARD', 8),
('H', 1, 'STANDARD', 8), ('H', 2, 'STANDARD', 8), ('H', 3, 'STANDARD', 8), ('H', 4, 'STANDARD', 8),
('H', 5, 'STANDARD', 8), ('H', 6, 'STANDARD', 8), ('H', 7, 'STANDARD', 8), ('H', 8, 'STANDARD', 8),
('H', 9, 'STANDARD', 8), ('H', 10, 'STANDARD', 8), ('H', 11, 'STANDARD', 8), ('H', 12, 'STANDARD', 8),
('I', 1, 'STANDARD', 8), ('I', 2, 'STANDARD', 8), ('I', 3, 'STANDARD', 8), ('I', 4, 'STANDARD', 8),
('I', 5, 'STANDARD', 8), ('I', 6, 'STANDARD', 8), ('I', 7, 'STANDARD', 8), ('I', 8, 'STANDARD', 8),
('I', 9, 'STANDARD', 8), ('I', 10, 'STANDARD', 8), ('I', 11, 'STANDARD', 8), ('I', 12, 'STANDARD', 8),
('J', 1, 'STANDARD', 8), ('J', 2, 'STANDARD', 8), ('J', 3, 'STANDARD', 8), ('J', 4, 'STANDARD', 8),
('J', 5, 'STANDARD', 8), ('J', 6, 'STANDARD', 8), ('J', 7, 'STANDARD', 8), ('J', 8, 'STANDARD', 8),
('J', 9, 'STANDARD', 8), ('J', 10, 'STANDARD', 8), ('J', 11, 'STANDARD', 8), ('J', 12, 'STANDARD', 8);

-- Generate seats for Room 9
INSERT INTO seats (seat_row, seat_number, type, room_id) VALUES
('A', 1, 'STANDARD', 9), ('A', 2, 'STANDARD', 9), ('A', 3, 'STANDARD', 9), ('A', 4, 'STANDARD', 9),
('A', 5, 'STANDARD', 9), ('A', 6, 'STANDARD', 9), ('A', 7, 'STANDARD', 9), ('A', 8, 'STANDARD', 9),
('A', 9, 'STANDARD', 9), ('A', 10, 'STANDARD', 9), ('A', 11, 'STANDARD', 9), ('A', 12, 'STANDARD', 9),
('B', 1, 'STANDARD', 9), ('B', 2, 'STANDARD', 9), ('B', 3, 'STANDARD', 9), ('B', 4, 'STANDARD', 9),
('B', 5, 'STANDARD', 9), ('B', 6, 'STANDARD', 9), ('B', 7, 'STANDARD', 9), ('B', 8, 'STANDARD', 9),
('B', 9, 'STANDARD', 9), ('B', 10, 'STANDARD', 9), ('B', 11, 'STANDARD', 9), ('B', 12, 'STANDARD', 9),
('C', 1, 'STANDARD', 9), ('C', 2, 'STANDARD', 9), ('C', 3, 'VIP', 9), ('C', 4, 'VIP', 9),
('C', 5, 'VIP', 9), ('C', 6, 'VIP', 9), ('C', 7, 'VIP', 9), ('C', 8, 'VIP', 9),
('C', 9, 'VIP', 9), ('C', 10, 'VIP', 9), ('C', 11, 'STANDARD', 9), ('C', 12, 'STANDARD', 9),
('D', 1, 'STANDARD', 9), ('D', 2, 'STANDARD', 9), ('D', 3, 'VIP', 9), ('D', 4, 'VIP', 9),
('D', 5, 'VIP', 9), ('D', 6, 'VIP', 9), ('D', 7, 'VIP', 9), ('D', 8, 'VIP', 9),
('D', 9, 'VIP', 9), ('D', 10, 'VIP', 9), ('D', 11, 'STANDARD', 9), ('D', 12, 'STANDARD', 9),
('E', 1, 'STANDARD', 9), ('E', 2, 'STANDARD', 9), ('E', 3, 'STANDARD', 9), ('E', 4, 'STANDARD', 9),
('E', 5, 'STANDARD', 9), ('E', 6, 'STANDARD', 9), ('E', 7, 'STANDARD', 9), ('E', 8, 'STANDARD', 9),
('E', 9, 'STANDARD', 9), ('E', 10, 'STANDARD', 9), ('E', 11, 'STANDARD', 9), ('E', 12, 'STANDARD', 9),
('F', 1, 'STANDARD', 9), ('F', 2, 'STANDARD', 9), ('F', 3, 'STANDARD', 9), ('F', 4, 'STANDARD', 9),
('F', 5, 'STANDARD', 9), ('F', 6, 'STANDARD', 9), ('F', 7, 'STANDARD', 9), ('F', 8, 'STANDARD', 9),
('F', 9, 'STANDARD', 9), ('F', 10, 'STANDARD', 9), ('F', 11, 'STANDARD', 9), ('F', 12, 'STANDARD', 9),
('G', 1, 'STANDARD', 9), ('G', 2, 'STANDARD', 9), ('G', 3, 'STANDARD', 9), ('G', 4, 'STANDARD', 9),
('G', 5, 'STANDARD', 9), ('G', 6, 'STANDARD', 9), ('G', 7, 'STANDARD', 9), ('G', 8, 'STANDARD', 9),
('G', 9, 'STANDARD', 9), ('G', 10, 'STANDARD', 9), ('G', 11, 'STANDARD', 9), ('G', 12, 'STANDARD', 9),
('H', 1, 'STANDARD', 9), ('H', 2, 'STANDARD', 9), ('H', 3, 'STANDARD', 9), ('H', 4, 'STANDARD', 9),
('H', 5, 'STANDARD', 9), ('H', 6, 'STANDARD', 9), ('H', 7, 'STANDARD', 9), ('H', 8, 'STANDARD', 9),
('H', 9, 'STANDARD', 9), ('H', 10, 'STANDARD', 9), ('H', 11, 'STANDARD', 9), ('H', 12, 'STANDARD', 9),
('I', 1, 'STANDARD', 9), ('I', 2, 'STANDARD', 9), ('I', 3, 'STANDARD', 9), ('I', 4, 'STANDARD', 9),
('I', 5, 'STANDARD', 9), ('I', 6, 'STANDARD', 9), ('I', 7, 'STANDARD', 9), ('I', 8, 'STANDARD', 9),
('I', 9, 'STANDARD', 9), ('I', 10, 'STANDARD', 9), ('I', 11, 'STANDARD', 9), ('I', 12, 'STANDARD', 9),
('J', 1, 'STANDARD', 9), ('J', 2, 'STANDARD', 9), ('J', 3, 'STANDARD', 9), ('J', 4, 'STANDARD', 9),
('J', 5, 'STANDARD', 9), ('J', 6, 'STANDARD', 9), ('J', 7, 'STANDARD', 9), ('J', 8, 'STANDARD', 9),
('J', 9, 'STANDARD', 9), ('J', 10, 'STANDARD', 9), ('J', 11, 'STANDARD', 9), ('J', 12, 'STANDARD', 9);

-- Generate seats for Room 10
INSERT INTO seats (seat_row, seat_number, type, room_id) VALUES
('A', 1, 'STANDARD', 10), ('A', 2, 'STANDARD', 10), ('A', 3, 'STANDARD', 10), ('A', 4, 'STANDARD', 10),
('A', 5, 'STANDARD', 10), ('A', 6, 'STANDARD', 10), ('A', 7, 'STANDARD', 10), ('A', 8, 'STANDARD', 10),
('A', 9, 'STANDARD', 10), ('A', 10, 'STANDARD', 10), ('A', 11, 'STANDARD', 10), ('A', 12, 'STANDARD', 10),
('B', 1, 'STANDARD', 10), ('B', 2, 'STANDARD', 10), ('B', 3, 'STANDARD', 10), ('B', 4, 'STANDARD', 10),
('B', 5, 'STANDARD', 10), ('B', 6, 'STANDARD', 10), ('B', 7, 'STANDARD', 10), ('B', 8, 'STANDARD', 10),
('B', 9, 'STANDARD', 10), ('B', 10, 'STANDARD', 10), ('B', 11, 'STANDARD', 10), ('B', 12, 'STANDARD', 10),
('C', 1, 'STANDARD', 10), ('C', 2, 'STANDARD', 10), ('C', 3, 'VIP', 10), ('C', 4, 'VIP', 10),
('C', 5, 'VIP', 10), ('C', 6, 'VIP', 10), ('C', 7, 'VIP', 10), ('C', 8, 'VIP', 10),
('C', 9, 'VIP', 10), ('C', 10, 'VIP', 10), ('C', 11, 'STANDARD', 10), ('C', 12, 'STANDARD', 10),
('D', 1, 'STANDARD', 10), ('D', 2, 'STANDARD', 10), ('D', 3, 'VIP', 10), ('D', 4, 'VIP', 10),
('D', 5, 'VIP', 10), ('D', 6, 'VIP', 10), ('D', 7, 'VIP', 10), ('D', 8, 'VIP', 10),
('D', 9, 'VIP', 10), ('D', 10, 'VIP', 10), ('D', 11, 'STANDARD', 10), ('D', 12, 'STANDARD', 10),
('E', 1, 'STANDARD', 10), ('E', 2, 'STANDARD', 10), ('E', 3, 'STANDARD', 10), ('E', 4, 'STANDARD', 10),
('E', 5, 'STANDARD', 10), ('E', 6, 'STANDARD', 10), ('E', 7, 'STANDARD', 10), ('E', 8, 'STANDARD', 10),
('E', 9, 'STANDARD', 10), ('E', 10, 'STANDARD', 10), ('E', 11, 'STANDARD', 10), ('E', 12, 'STANDARD', 10),
('F', 1, 'STANDARD', 10), ('F', 2, 'STANDARD', 10), ('F', 3, 'STANDARD', 10), ('F', 4, 'STANDARD', 10),
('F', 5, 'STANDARD', 10), ('F', 6, 'STANDARD', 10), ('F', 7, 'STANDARD', 10), ('F', 8, 'STANDARD', 10),
('F', 9, 'STANDARD', 10), ('F', 10, 'STANDARD', 10), ('F', 11, 'STANDARD', 10), ('F', 12, 'STANDARD', 10),
('G', 1, 'STANDARD', 10), ('G', 2, 'STANDARD', 10), ('G', 3, 'STANDARD', 10), ('G', 4, 'STANDARD', 10),
('G', 5, 'STANDARD', 10), ('G', 6, 'STANDARD', 10), ('G', 7, 'STANDARD', 10), ('G', 8, 'STANDARD', 10),
('G', 9, 'STANDARD', 10), ('G', 10, 'STANDARD', 10), ('G', 11, 'STANDARD', 10), ('G', 12, 'STANDARD', 10),
('H', 1, 'STANDARD', 10), ('H', 2, 'STANDARD', 10), ('H', 3, 'STANDARD', 10), ('H', 4, 'STANDARD', 10),
('H', 5, 'STANDARD', 10), ('H', 6, 'STANDARD', 10), ('H', 7, 'STANDARD', 10), ('H', 8, 'STANDARD', 10),
('H', 9, 'STANDARD', 10), ('H', 10, 'STANDARD', 10), ('H', 11, 'STANDARD', 10), ('H', 12, 'STANDARD', 10),
('I', 1, 'STANDARD', 10), ('I', 2, 'STANDARD', 10), ('I', 3, 'STANDARD', 10), ('I', 4, 'STANDARD', 10),
('I', 5, 'STANDARD', 10), ('I', 6, 'STANDARD', 10), ('I', 7, 'STANDARD', 10), ('I', 8, 'STANDARD', 10),
('I', 9, 'STANDARD', 10), ('I', 10, 'STANDARD', 10), ('I', 11, 'STANDARD', 10), ('I', 12, 'STANDARD', 10),
('J', 1, 'STANDARD', 10), ('J', 2, 'STANDARD', 10), ('J', 3, 'STANDARD', 10), ('J', 4, 'STANDARD', 10),
('J', 5, 'STANDARD', 10), ('J', 6, 'STANDARD', 10), ('J', 7, 'STANDARD', 10), ('J', 8, 'STANDARD', 10),
('J', 9, 'STANDARD', 10), ('J', 10, 'STANDARD', 10), ('J', 11, 'STANDARD', 10), ('J', 12, 'STANDARD', 10);

-- Generate seats for Room 11
INSERT INTO seats (seat_row, seat_number, type, room_id) VALUES
('A', 1, 'STANDARD', 11), ('A', 2, 'STANDARD', 11), ('A', 3, 'STANDARD', 11), ('A', 4, 'STANDARD', 11),
('A', 5, 'STANDARD', 11), ('A', 6, 'STANDARD', 11), ('A', 7, 'STANDARD', 11), ('A', 8, 'STANDARD', 11),
('A', 9, 'STANDARD', 11), ('A', 10, 'STANDARD', 11), ('A', 11, 'STANDARD', 11), ('A', 12, 'STANDARD', 11),
('B', 1, 'STANDARD', 11), ('B', 2, 'STANDARD', 11), ('B', 3, 'STANDARD', 11), ('B', 4, 'STANDARD', 11),
('B', 5, 'STANDARD', 11), ('B', 6, 'STANDARD', 11), ('B', 7, 'STANDARD', 11), ('B', 8, 'STANDARD', 11),
('B', 9, 'STANDARD', 11), ('B', 10, 'STANDARD', 11), ('B', 11, 'STANDARD', 11), ('B', 12, 'STANDARD', 11),
('C', 1, 'STANDARD', 11), ('C', 2, 'STANDARD', 11), ('C', 3, 'VIP', 11), ('C', 4, 'VIP', 11),
('C', 5, 'VIP', 11), ('C', 6, 'VIP', 11), ('C', 7, 'VIP', 11), ('C', 8, 'VIP', 11),
('C', 9, 'VIP', 11), ('C', 10, 'VIP', 11), ('C', 11, 'STANDARD', 11), ('C', 12, 'STANDARD', 11),
('D', 1, 'STANDARD', 11), ('D', 2, 'STANDARD', 11), ('D', 3, 'VIP', 11), ('D', 4, 'VIP', 11),
('D', 5, 'VIP', 11), ('D', 6, 'VIP', 11), ('D', 7, 'VIP', 11), ('D', 8, 'VIP', 11),
('D', 9, 'VIP', 11), ('D', 10, 'VIP', 11), ('D', 11, 'STANDARD', 11), ('D', 12, 'STANDARD', 11),
('E', 1, 'STANDARD', 11), ('E', 2, 'STANDARD', 11), ('E', 3, 'STANDARD', 11), ('E', 4, 'STANDARD', 11),
('E', 5, 'STANDARD', 11), ('E', 6, 'STANDARD', 11), ('E', 7, 'STANDARD', 11), ('E', 8, 'STANDARD', 11),
('E', 9, 'STANDARD', 11), ('E', 10, 'STANDARD', 11), ('E', 11, 'STANDARD', 11), ('E', 12, 'STANDARD', 11),
('F', 1, 'STANDARD', 11), ('F', 2, 'STANDARD', 11), ('F', 3, 'STANDARD', 11), ('F', 4, 'STANDARD', 11),
('F', 5, 'STANDARD', 11), ('F', 6, 'STANDARD', 11), ('F', 7, 'STANDARD', 11), ('F', 8, 'STANDARD', 11),
('F', 9, 'STANDARD', 11), ('F', 10, 'STANDARD', 11), ('F', 11, 'STANDARD', 11), ('F', 12, 'STANDARD', 11),
('G', 1, 'STANDARD', 11), ('G', 2, 'STANDARD', 11), ('G', 3, 'STANDARD', 11), ('G', 4, 'STANDARD', 11),
('G', 5, 'STANDARD', 11), ('G', 6, 'STANDARD', 11), ('G', 7, 'STANDARD', 11), ('G', 8, 'STANDARD', 11),
('G', 9, 'STANDARD', 11), ('G', 10, 'STANDARD', 11), ('G', 11, 'STANDARD', 11), ('G', 12, 'STANDARD', 11),
('H', 1, 'STANDARD', 11), ('H', 2, 'STANDARD', 11), ('H', 3, 'STANDARD', 11), ('H', 4, 'STANDARD', 11),
('H', 5, 'STANDARD', 11), ('H', 6, 'STANDARD', 11), ('H', 7, 'STANDARD', 11), ('H', 8, 'STANDARD', 11),
('H', 9, 'STANDARD', 11), ('H', 10, 'STANDARD', 11), ('H', 11, 'STANDARD', 11), ('H', 12, 'STANDARD', 11),
('I', 1, 'STANDARD', 11), ('I', 2, 'STANDARD', 11), ('I', 3, 'STANDARD', 11), ('I', 4, 'STANDARD', 11),
('I', 5, 'STANDARD', 11), ('I', 6, 'STANDARD', 11), ('I', 7, 'STANDARD', 11), ('I', 8, 'STANDARD', 11),
('I', 9, 'STANDARD', 11), ('I', 10, 'STANDARD', 11), ('I', 11, 'STANDARD', 11), ('I', 12, 'STANDARD', 11),
('J', 1, 'STANDARD', 11), ('J', 2, 'STANDARD', 11), ('J', 3, 'STANDARD', 11), ('J', 4, 'STANDARD', 11),
('J', 5, 'STANDARD', 11), ('J', 6, 'STANDARD', 11), ('J', 7, 'STANDARD', 11), ('J', 8, 'STANDARD', 11),
('J', 9, 'STANDARD', 11), ('J', 10, 'STANDARD', 11), ('J', 11, 'STANDARD', 11), ('J', 12, 'STANDARD', 11);

-- Generate seats for Room 12
INSERT INTO seats (seat_row, seat_number, type, room_id) VALUES
('A', 1, 'STANDARD', 12), ('A', 2, 'STANDARD', 12), ('A', 3, 'STANDARD', 12), ('A', 4, 'STANDARD', 12),
('A', 5, 'STANDARD', 12), ('A', 6, 'STANDARD', 12), ('A', 7, 'STANDARD', 12), ('A', 8, 'STANDARD', 12),
('A', 9, 'STANDARD', 12), ('A', 10, 'STANDARD', 12), ('A', 11, 'STANDARD', 12), ('A', 12, 'STANDARD', 12),
('B', 1, 'STANDARD', 12), ('B', 2, 'STANDARD', 12), ('B', 3, 'STANDARD', 12), ('B', 4, 'STANDARD', 12),
('B', 5, 'STANDARD', 12), ('B', 6, 'STANDARD', 12), ('B', 7, 'STANDARD', 12), ('B', 8, 'STANDARD', 12),
('B', 9, 'STANDARD', 12), ('B', 10, 'STANDARD', 12), ('B', 11, 'STANDARD', 12), ('B', 12, 'STANDARD', 12),
('C', 1, 'STANDARD', 12), ('C', 2, 'STANDARD', 12), ('C', 3, 'VIP', 12), ('C', 4, 'VIP', 12),
('C', 5, 'VIP', 12), ('C', 6, 'VIP', 12), ('C', 7, 'VIP', 12), ('C', 8, 'VIP', 12),
('C', 9, 'VIP', 12), ('C', 10, 'VIP', 12), ('C', 11, 'STANDARD', 12), ('C', 12, 'STANDARD', 12),
('D', 1, 'STANDARD', 12), ('D', 2, 'STANDARD', 12), ('D', 3, 'VIP', 12), ('D', 4, 'VIP', 12),
('D', 5, 'VIP', 12), ('D', 6, 'VIP', 12), ('D', 7, 'VIP', 12), ('D', 8, 'VIP', 12),
('D', 9, 'VIP', 12), ('D', 10, 'VIP', 12), ('D', 11, 'STANDARD', 12), ('D', 12, 'STANDARD', 12),
('E', 1, 'STANDARD', 12), ('E', 2, 'STANDARD', 12), ('E', 3, 'STANDARD', 12), ('E', 4, 'STANDARD', 12),
('E', 5, 'STANDARD', 12), ('E', 6, 'STANDARD', 12), ('E', 7, 'STANDARD', 12), ('E', 8, 'STANDARD', 12),
('E', 9, 'STANDARD', 12), ('E', 10, 'STANDARD', 12), ('E', 11, 'STANDARD', 12), ('E', 12, 'STANDARD', 12),
('F', 1, 'STANDARD', 12), ('F', 2, 'STANDARD', 12), ('F', 3, 'STANDARD', 12), ('F', 4, 'STANDARD', 12),
('F', 5, 'STANDARD', 12), ('F', 6, 'STANDARD', 12), ('F', 7, 'STANDARD', 12), ('F', 8, 'STANDARD', 12),
('F', 9, 'STANDARD', 12), ('F', 10, 'STANDARD', 12), ('F', 11, 'STANDARD', 12), ('F', 12, 'STANDARD', 12),
('G', 1, 'STANDARD', 12), ('G', 2, 'STANDARD', 12), ('G', 3, 'STANDARD', 12), ('G', 4, 'STANDARD', 12),
('G', 5, 'STANDARD', 12), ('G', 6, 'STANDARD', 12), ('G', 7, 'STANDARD', 12), ('G', 8, 'STANDARD', 12),
('G', 9, 'STANDARD', 12), ('G', 10, 'STANDARD', 12), ('G', 11, 'STANDARD', 12), ('G', 12, 'STANDARD', 12),
('H', 1, 'STANDARD', 12), ('H', 2, 'STANDARD', 12), ('H', 3, 'STANDARD', 12), ('H', 4, 'STANDARD', 12),
('H', 5, 'STANDARD', 12), ('H', 6, 'STANDARD', 12), ('H', 7, 'STANDARD', 12), ('H', 8, 'STANDARD', 12),
('H', 9, 'STANDARD', 12), ('H', 10, 'STANDARD', 12), ('H', 11, 'STANDARD', 12), ('H', 12, 'STANDARD', 12),
('I', 1, 'STANDARD', 12), ('I', 2, 'STANDARD', 12), ('I', 3, 'STANDARD', 12), ('I', 4, 'STANDARD', 12),
('I', 5, 'STANDARD', 12), ('I', 6, 'STANDARD', 12), ('I', 7, 'STANDARD', 12), ('I', 8, 'STANDARD', 12),
('I', 9, 'STANDARD', 12), ('I', 10, 'STANDARD', 12), ('I', 11, 'STANDARD', 12), ('I', 12, 'STANDARD', 12),
('J', 1, 'STANDARD', 12), ('J', 2, 'STANDARD', 12), ('J', 3, 'STANDARD', 12), ('J', 4, 'STANDARD', 12),
('J', 5, 'STANDARD', 12), ('J', 6, 'STANDARD', 12), ('J', 7, 'STANDARD', 12), ('J', 8, 'STANDARD', 12),
('J', 9, 'STANDARD', 12), ('J', 10, 'STANDARD', 12), ('J', 11, 'STANDARD', 12), ('J', 12, 'STANDARD', 12);

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

-- August showtimes
INSERT INTO showtimes (movie_id, room_id, start_time, end_time, base_price) VALUES
-- Hanoi Cinema
(1, 1, '2026-08-10 09:00:00', '2026-08-10 11:08:00', 120000),
(3, 2, '2026-08-10 14:30:00', '2026-08-10 16:38:00', 180000),
(5, 3, '2026-08-10 20:00:00', '2026-08-10 22:08:00', 250000),
(10, 4, '2026-08-10 18:00:00', '2026-08-10 20:11:00', 90000),
-- HCM Cinema
(4, 5, '2026-08-12 11:30:00', '2026-08-12 13:06:00', 100000),
(6, 6, '2026-08-12 16:00:00', '2026-08-12 17:36:00', 120000),
(1, 7, '2026-08-12 15:00:00', '2026-08-12 16:40:00', 110000),
(11, 8, '2026-08-12 18:30:00', '2026-08-12 20:10:00', 150000),
(3, 9, '2026-08-12 13:00:00', '2026-08-12 15:40:00', 200000),
-- Da Nang Cinema
(1, 10, '2026-08-15 09:00:00', '2026-08-15 11:08:00', 120000),
(5, 11, '2026-08-15 14:30:00', '2026-08-15 17:10:00', 180000),
(6, 12, '2026-08-15 20:00:00', '2026-08-15 22:28:00', 150000);

-- ==============================================
-- SUMMARY
-- ==============================================
-- This seed data includes:
-- ✅ 7 Users (1 Admin, 3 Managers, 3 Customers)
-- ✅ 11 Movies (Hollywood blockbusters + Vietnamese films)
-- ✅ 3 Cinemas (Hanoi, Ho Chi Minh, Da Nang)
-- ✅ 12 Rooms (Different types: STANDARD, IMAX, VIP)
-- ✅ 1440 Seats (All 12 Rooms)
-- ✅ 29 Showtimes (Next 2 days + August)
-- 
-- Password for all users: "password" (encoded with BCrypt)
-- Movie posters use TMDB image URLs for realistic data
-- Showtimes include popular movies with realistic pricing
-- ==============================================