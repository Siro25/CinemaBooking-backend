# Cinema Booking System

Dự án này bao gồm một hệ thống đặt vé xem phim hoàn chỉnh với Frontend (React), Backend (Spring Boot), Database (PostgreSQL) và Cache (Redis).

## 🚀 Hướng dẫn khởi chạy nhanh (Dành cho Khách hàng / Người xem)

### Yêu cầu hệ thống:
1. Đã cài đặt **Docker** và **Docker Compose**.
2. Đảm bảo Docker Desktop đang mở và chạy.

### Các bước chạy hệ thống:

**Bước 1: Tải file cấu hình**
Tải file `docker-compose.yml` từ kho lưu trữ này và đặt vào một thư mục trống bất kỳ trên máy của bạn.

**Bước 2: Mở Terminal / Command Prompt**
Mở Terminal, PowerShell hoặc Command Prompt và di chuyển đến thư mục chứa file `docker-compose.yml` vừa tải.
```bash
cd C:\cinema-demo
```

**Bước 3: Khởi chạy hệ thống**
Chạy câu lệnh sau để Docker tự động tải hệ thống về và chạy ngầm:
```bash
docker compose up -d
```

**Bước 4: Truy cập ứng dụng**
Sau khi lệnh chạy xong (hiển thị trạng thái `Started` cho tất cả các container), mở trình duyệt web và truy cập:
- **Giao diện Web (Khách hàng sử dụng):** [http://localhost](http://localhost)
- **Hệ thống API (Dành cho Dev kiểm tra):** [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

### 🔑 Các tài khoản test có sẵn:
*(Mật khẩu chung cho tất cả các tài khoản dưới đây là: `123456`)*

- **Tài khoản Admin (Toàn quyền hệ thống):** `admin@sidocinemas.com`
- **Tài khoản Quản lý Rạp (Hà Nội):** `manager.hanoi@sidocinemas.com`
- **Tài khoản Khách hàng:** `customer1@example.com`

---

### 🛑 Các lệnh hữu ích khác

**Cách tắt hệ thống an toàn:**
Khi không xem nữa, hãy chạy lệnh sau để tắt hệ thống (dữ liệu vẫn được giữ nguyên):
```bash
docker compose stop
```

**Bật lại hệ thống:**
```bash
docker compose start
```

**Cách xóa sạch hệ thống (Xóa luôn cả dữ liệu database cũ):**
```bash
docker compose down -v
```
*(Lệnh này rất hữu ích khi bạn muốn reset ứng dụng về trạng thái mới tinh).*
