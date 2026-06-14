# Seed Data Documentation

## 📊 Overview

Hệ thống tự động tạo dữ liệu mẫu khi khởi động lần đầu tiên với **DataSeeder.java**. Data bao gồm phim xu hướng 2024-2026, rạp chiếu thực tế và lịch chiếu đầy đủ.

## 🎬 Movies Data (8 phim)

### Hollywood Blockbusters
1. **Deadpool & Wolverine** (T16) - 128 phút
   - Hành động, Hài, Siêu anh hùng
   - Poster: TMDB Official

2. **Inside Out 2** (P) - 96 phút  
   - Hoạt hình, Gia đình, Tâm lý
   - Phim Disney/Pixar hot 2024

3. **Moana 2** (P) - 100 phút
   - Hoạt hình, Phiêu lưu, Gia đình
   - Sequel được mong chờ

4. **Wicked** (T13) - 160 phút
   - Ca nhạc, Kỳ ảo, Chính kịch  
   - Broadway musical adaptation

5. **Gladiator II** (T18) - 148 phút
   - Hành động, Sử thi, Chính kịch
   - Sequel của bom tấn 2000

### Upcoming 2025-2026
6. **Avatar 3: Fire and Ash** (T13) - 190 phút
   - Khoa học viễn tưởng, Phiêu lưu
   - Status: COMING_SOON

### Vietnamese Movies  
7. **Mai** (T16) - 131 phút
   - Chính kịch, Tình cảm
   - Phim Việt hot 2024

8. **Đào, Phở và Piano** (T13) - 110 phút
   - Chính kịch, Lịch sử, Tình cảm
   - Phim Việt chất lượng cao

## 👥 Users Data (8 accounts)

### Admin (1 tài khoản)
- **Email**: `admin@sidocinemas.com`
- **Password**: `password`
- **Role**: ADMIN

### Managers (3 tài khoản)  
- **Hà Nội**: `manager.hanoi@sidocinemas.com` / `password`
- **TP.HCM**: `manager.hcm@sidocinemas.com` / `password`  
- **Đà Nẵng**: `manager.danang@sidocinemas.com` / `password`

### Customers (5 tài khoản)
- **customer1@example.com** → **customer5@example.com**
- **Password**: `password` (tất cả)

## 🏢 Cinemas Data (3 rạp)

### SIDO Cinemas Hà Nội
- **Địa chỉ**: 12 Phố Huế, Quận Hai Bà Trưng, Hà Nội
- **Manager**: Nguyễn Văn Quản
- **Rooms**: R01 (2D), R02 (IMAX), R03 (3D), R04 (2D)

### SIDO Cinemas TP.HCM  
- **Địa chỉ**: 196 Pasteur, Quận 3, TP. Hồ Chí Minh
- **Manager**: Trần Thị Mai
- **Rooms**: A1 (IMAX), A2 (2D), A3 (3D), A4 (2D)

### SIDO Cinemas Đà Nẵng
- **Địa chỉ**: 252 Võ Nguyên Giáp, Quận Sơn Trà, Đà Nẵng  
- **Manager**: Lê Minh Khôi
- **Rooms**: DN01 (2D), DN02 (3D), DN03 (2D)

## 💺 Seats Data (120 ghế)

**Sample Room R01** (10 hàng x 12 ghế):
- **Hàng A-B**: Standard seats (24 ghế)
- **Hàng C-D**: VIP seats ở giữa (vị trí 3-10), Standard ở 2 bên 
- **Hàng E-J**: Standard seats (72 ghế)
- **Total**: 96 Standard + 16 VIP = 112 ghế

## 🕐 Showtimes Data (24 suất chiếu)

**3 ngày tiếp theo** với lịch chiếu đa dạng:
- **Sáng**: 9:00 - 12:00
- **Chiều**: 14:00 - 17:00  
- **Tối**: 19:00 - 23:00

**Giá vé theo loại phòng**:
- **2D**: 100,000 - 120,000 VNĐ
- **3D**: 150,000 - 180,000 VNĐ
- **IMAX**: 200,000 - 250,000 VNĐ

## 🚀 Usage

### Automatic Seeding
```java
// DataSeeder.java runs on first startup
@Component
public class DataSeeder implements CommandLineRunner {
    // Automatically seeds if no data exists
    if (userRepository.count() == 0) {
        seedUsers();
        seedMovies();
        // ...
    }
}
```

### Test Seeded Data
```bash
# Run test script
node test-seed-data.js

# Expected output:
✅ Movies loaded: 8 movies
✅ Cinemas loaded: 3 cinemas  
✅ Showtimes loaded: X showtimes
✅ Seats loaded: 120 seats
```

### API Endpoints
```http
GET /api/v1/public/movies          # 8 movies
GET /api/v1/public/cinemas         # 3 cinemas
GET /api/v1/public/showtimes/movie/1  # Showtimes for movie 1
GET /api/v1/public/seats/room/1    # 120 seats for room R01
```

## 📸 Movie Posters

Tất cả poster sử dụng **TMDB (The Movie Database)** URLs:
- High-quality official posters
- W500 resolution (500px width)  
- CDN delivery for fast loading
- Real movie data from 2024-2026

## 🔄 Reset Data

```sql
-- Clear all data (run in DB console)
DELETE FROM showtimes;
DELETE FROM seats;  
DELETE FROM rooms;
DELETE FROM cinemas;
DELETE FROM movies;
DELETE FROM users;

-- Restart app to re-seed
```

## 🎯 Frontend Integration

Seed data được thiết kế để **frontend hiển thị ngay**:
- ✅ Movies với poster thật  
- ✅ Showtimes trong 3 ngày tới
- ✅ Seat map layout realistic
- ✅ Price tiers theo loại phòng
- ✅ User accounts để test authentication

---

**🎉 Data seeding hoàn thành! Frontend sẽ có dữ liệu phong phú để demo.**