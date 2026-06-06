# Tái Cấu Trúc Controller Theo Single Responsibility Principle (SRP)

## Cấu Trúc Namespace API Mới

Sau khi tái cấu trúc, các controller được chia tách theo chức năng và quyền hạn rõ ràng:

### 1. `/api/v1/public/**` - Public Endpoints
**Mục đích**: Cho tất cả người dùng (không cần authentication)  
**Package**: `com.sidocinemas.cinema_booking.controller.public_`

- `PublicHealthController` - Health check và system info
  - `GET /api/v1/public/health` - Health check
  - `GET /api/v1/public/health/endpoints` - Danh sách API endpoints

- `PublicAuthController` - Đăng nhập, đăng ký
  - `POST /api/v1/public/auth/login`
  - `POST /api/v1/public/auth/register`

- `PublicMovieController` - Xem danh sách phim
  - `GET /api/v1/public/movies` - Danh sách phim đang chiếu
  - `GET /api/v1/public/movies/{id}` - Chi tiết phim

- `PublicCinemaController` - Xem thông tin rạp
  - `GET /api/v1/public/cinemas` - Danh sách rạp
  - `GET /api/v1/public/cinemas/{id}` - Chi tiết rạp

- `PublicShowtimeController` - Xem lịch chiếu
  - `GET /api/v1/public/showtimes/movie/{movieId}` - Lịch chiếu theo phim
  - `GET /api/v1/public/showtimes/{id}` - Chi tiết suất chiếu

- `PublicSeatController` - Xem thông tin ghế
  - `GET /api/v1/public/seats/room/{roomId}` - Danh sách ghế theo phòng
  - `GET /api/v1/public/seats/showtime/{showtimeId}` - Trạng thái ghế theo suất chiếu
  - `GET /api/v1/public/seats/{id}` - Chi tiết ghế

### 2. `/api/v1/customer/**` - Customer Endpoints
**Mục đích**: Cho khách hàng đã đăng nhập (role CUSTOMER)
**Package**: `com.sidocinemas.cinema_booking.controller.customer`

- `CustomerBookingController` - Quản lý đặt vé
  - `POST /api/v1/customer/bookings` - Tạo đặt vé
  - `GET /api/v1/customer/bookings` - Lịch sử đặt vé của mình
  - `GET /api/v1/customer/bookings/{id}` - Chi tiết đặt vé
  - `PATCH /api/v1/customer/bookings/{id}/cancel` - Hủy đặt vé

- `CustomerPaymentController` - Thanh toán
  - `POST /api/v1/customer/payments` - Thanh toán cho booking
  - `GET /api/v1/customer/payments` - Lịch sử thanh toán
  - `GET /api/v1/customer/payments/{id}` - Chi tiết thanh toán

- `CustomerProfileController` - Quản lý thông tin cá nhân
  - `GET /api/v1/customer/profile` - Xem profile
  - `PUT /api/v1/customer/profile` - Cập nhật profile

### 3. `/api/v1/manager/**` - Manager Endpoints
**Mục đích**: Cho quản lý rạp (role MANAGER)
**Package**: `com.sidocinemas.cinema_booking.controller.manager`

- `ManagerMovieController` - Quản lý phim
  - `POST /api/v1/manager/movies` - Tạo phim
  - `GET /api/v1/manager/movies` - Danh sách phim
  - `PUT /api/v1/manager/movies/{id}` - Cập nhật phim
  - `DELETE /api/v1/manager/movies/{id}` - Xóa phim

- `ManagerShowtimeController` - Quản lý suất chiếu
  - `POST /api/v1/manager/showtimes` - Tạo suất chiếu
  - `GET /api/v1/manager/showtimes` - Danh sách suất chiếu
  - `PUT /api/v1/manager/showtimes/{id}` - Cập nhật suất chiếu
  - `DELETE /api/v1/manager/showtimes/{id}` - Xóa suất chiếu

- `ManagerRoomController` - Quản lý phòng chiếu
  - `POST /api/v1/manager/rooms` - Tạo phòng
  - `GET /api/v1/manager/rooms` - Danh sách phòng của rạp
  - `PUT /api/v1/manager/rooms/{id}` - Cập nhật phòng
  - `DELETE /api/v1/manager/rooms/{id}` - Xóa phòng

- `ManagerBookingController` - Quản lý đặt vé
  - `GET /api/v1/manager/bookings` - Danh sách booking của rạp
  - `PATCH /api/v1/manager/bookings/{id}/confirm` - Xác nhận booking
  - `PATCH /api/v1/manager/bookings/{id}/cancel` - Hủy booking

- `ManagerReportController` - Báo cáo doanh thu
  - `GET /api/v1/manager/reports` - Báo cáo doanh thu rạp

### 4. `/api/v1/admin/**` - Admin Endpoints
**Mục đích**: Cho quản trị hệ thống (role ADMIN)
**Package**: `com.sidocinemas.cinema_booking.controller.admin`

- `AdminUserController` - Quản lý người dùng
  - `GET /api/v1/admin/users` - Danh sách người dùng
  - `GET /api/v1/admin/users/{id}` - Chi tiết người dùng
  - `PUT /api/v1/admin/users/{id}` - Cập nhật người dùng
  - `DELETE /api/v1/admin/users/{id}` - Xóa người dùng

- `AdminCinemaController` - Quản lý rạp phim
  - `POST /api/v1/admin/cinemas` - Tạo rạp
  - `GET /api/v1/admin/cinemas` - Danh sách rạp
  - `PUT /api/v1/admin/cinemas/{id}` - Cập nhật rạp
  - `DELETE /api/v1/admin/cinemas/{id}` - Xóa rạp

- `AdminSeatController` - Quản lý ghế
  - `POST /api/v1/admin/seats` - Tạo ghế
  - `PUT /api/v1/admin/seats/{id}` - Cập nhật ghế
  - `DELETE /api/v1/admin/seats/{id}` - Xóa ghế

- `AdminRoleController` - Quản lý phân quyền
  - `GET /api/v1/admin/roles` - Danh sách role
  - `PATCH /api/v1/admin/roles/{userId}` - Gán role cho user

- `AdminDashboardController` - Dashboard tổng quan
  - `GET /api/v1/admin/dashboard` - Thống kê tổng quan

- `AdminReportController` - Báo cáo hệ thống
  - `GET /api/v1/admin/reports` - Báo cáo toàn hệ thống

## Lợi Ích Của Cấu Trúc Mới

### 1. **Single Responsibility Principle (SRP)**
- Mỗi controller chỉ chịu trách nhiệm cho một nhóm chức năng cụ thể
- Dễ dàng maintain và debug

### 2. **Namespace Rõ Ràng**
- `/api/v1/public/**`: Không cần authentication
- `/api/v1/customer/**`: Chỉ cho customer
- `/api/v1/manager/**`: Chỉ cho manager rạp
- `/api/v1/admin/**`: Chỉ cho admin hệ thống

### 3. **Security Tốt Hơn**
- Phân quyền rõ ràng theo từng endpoint
- Dễ kiểm soát ai có thể truy cập chức năng gì

### 4. **Scalability**
- Dễ thêm chức năng mới vào đúng namespace
- Có thể tách thành microservice nếu cần

### 5. **Documentation Tự Động**
- URL path đã nói lên được ai có thể dùng API đó
- Swagger/OpenAPI sẽ group API theo namespace

## Các File Hỗ Trợ Mới

### 1. `ApiConstants.java`
Định nghĩa các namespace path constants để tránh hardcode URL trong controller.

### 2. `SecurityUtils.java`
Utility class để xử lý SecurityContext, lấy thông tin user hiện tại từ JWT token.

### 3. `PublicHealthController.java`
Controller cung cấp health check và danh sách API endpoints cho frontend.

## Migration Notes

### Cần Cập Nhật Service Layer
Một số method trong service cần được thêm để hỗ trợ tách biệt logic:

```java
// BookingService
BookingResponse createBookingForCurrentUser(BookingRequest request);
List<BookingResponse> getBookingsForCurrentUser();
BookingResponse getBookingByIdForCurrentUser(Long id);
BookingResponse cancelBookingForCurrentUser(Long id);

// PaymentService  
PaymentResponse processPaymentForCurrentUser(PaymentRequest request);
List<PaymentResponse> getPaymentsForCurrentUser();
PaymentResponse getPaymentByIdForCurrentUser(Long id);

// UserService
UserResponse getCurrentUserProfile();
UserResponse updateCurrentUserProfile(UserUpdateRequest request);
```

### Frontend Migration
Frontend cần cập nhật API calls theo namespace mới:
- Trang chủ: `/api/v1/public/**`
- Sau khi đăng nhập customer: `/api/v1/customer/**`
- Dashboard manager: `/api/v1/manager/**`
- Dashboard admin: `/api/v1/admin/**`