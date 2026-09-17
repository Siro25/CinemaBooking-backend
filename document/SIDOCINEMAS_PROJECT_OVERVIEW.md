# 🎬 SidoCinemas - Hệ Thống Đặt Vé Xem Phim

## 📋 Tổng Quan Dự Án

**SidoCinemas** là một hệ thống đặt vé xem phim trực tuyến hiện đại, được xây dựng với kiến trúc microservices, cung cấp trải nghiệm đặt vé mượt mà cho khách hàng và hệ thống quản lý toàn diện cho quản trị viên.

### 🎯 Mục Tiêu Dự Án
- Tạo ra một nền tảng đặt vé xem phim trực tuyến hoàn chỉnh
- Hỗ trợ quản lý chuỗi rạp chiếu phim với nhiều địa điểm
- Cung cấp giao diện thân thiện cho cả khách hàng và nhân viên quản lý
- Đảm bảo tính bảo mật cao với phân quyền người dùng chi tiết

## 🏗️ Kiến Trúc Tổng Thể

### Architecture Pattern
- **Frontend**: Single Page Application (SPA) với React
- **Backend**: Monolithic với Spring Boot, phân chia theo modules
- **Database**: PostgreSQL với Redis cache
- **Deployment**: Docker containers với Docker Compose

### System Components
```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Frontend      │    │   Backend       │    │   Database      │
│   (React)       │───▶│  (Spring Boot)  │───▶│  (PostgreSQL)   │
│   Port: 80      │    │   Port: 8080    │    │   Port: 5432    │
└─────────────────┘    └─────────────────┘    └─────────────────┘
                              │
                              ▼
                       ┌─────────────────┐
                       │   Cache         │
                       │   (Redis)       │
                       │   Port: 6379    │
                       └─────────────────┘
```

## 🚀 Tính Năng Chính

### 👥 Phân Quyền Người Dùng
- **CUSTOMER**: Khách hàng - Đặt vé, xem lịch sử, quản lý profile
- **MANAGER**: Quản lý rạp - Quản lý phim, suất chiếu, phòng chiếu, xử lý đặt vé
- **ADMIN**: Quản trị viên - Toàn quyền hệ thống, quản lý người dùng, rạp chiếu

### 🎬 Quản Lý Nội Dung
- Quản lý phim: Thông tin chi tiết, trailer, poster, thể loại
- Quản lý rạp chiếu: Địa điểm, phòng chiếu, ghế ngồi
- Quản lý suất chiếu: Lịch chiếu, giá vé, slot thời gian

### 🎫 Hệ Thống Đặt Vé
- Chọn phim, rạp, suất chiếu
- Chọn ghế ngồi realtime với seat holding
- Thanh toán trực tuyến
- Sinh QR code tự động cho vé

### 📊 Báo Cáo & Thống Kê
- Dashboard tổng quan cho từng role
- Báo cáo doanh thu theo thời gian
- Thống kê người dùng và đặt vé
- Phân tích hiệu suất rạp chiếu

## 🔧 Công Nghệ Sử Dụng

### Frontend Stack
| Công Nghệ | Version | Mục Đích |
|-----------|---------|----------|
| **React** | 19.2.6 | Core UI framework |
| **Vite** | 8.0.12 | Build tool & dev server |
| **Tailwind CSS** | 4.3.0 | Utility-first CSS framework |
| **Axios** | 1.17.0 | HTTP client cho API calls |
| **React Router** | 7.17.0 | Client-side routing |
| **React Hook Form** | 7.78.0 | Form validation |
| **Yup** | 1.7.1 | Schema validation |
| **Day.js** | 1.11.23 | Date manipulation |
| **Lucide React** | 1.18.0 | Icon library |
| **React Hot Toast** | 2.6.0 | Notification system |

### Backend Stack
| Công Nghệ | Version | Mục Đích |
|-----------|---------|----------|
| **Java** | 21 | Core programming language |
| **Spring Boot** | 3.3.6 | Application framework |
| **Spring Security** | 6.x | Authentication & authorization |
| **Spring Data JPA** | 3.x | ORM & database access |
| **PostgreSQL** | 16 | Primary database |
| **Redis** | 7 | Caching & seat holding |
| **JWT** | 0.11.5 | Token-based authentication |
| **Lombok** | Latest | Code generation |
| **ZXing** | 3.5.3 | QR code generation |
| **SpringDoc OpenAPI** | 2.6.0 | API documentation |

### DevOps & Deployment
| Công Nghệ | Mục Đích |
|-----------|----------|
| **Docker** | Containerization |
| **Docker Compose** | Multi-container orchestration |
| **Nginx** | Web server & reverse proxy |
| **Maven** | Java build tool |
| **Git** | Version control |

## 📱 Giao Diện Người Dùng

### Design System
- **Theme**: Dark mode với accent colors
- **Typography**: Sans-serif fonts
- **Color Palette**: 
  - Primary: Blue tones (#3B82F6)
  - Success: Green (#10B981)
  - Warning: Yellow (#F59E0B)
  - Error: Red (#EF4444)
- **Components**: Material Design inspired với Tailwind customization

### Responsive Design
- **Mobile First**: Tối ưu cho điện thoại di động
- **Tablet Support**: Adaptive layout cho tablet
- **Desktop**: Full-featured desktop experience

## 🔒 Bảo Mật

### Authentication & Authorization
- **JWT Tokens**: Access token + Refresh token
- **Role-Based Access Control (RBAC)**: 3-tier permission system
- **Password Encryption**: BCrypt với salt
- **CORS Configuration**: Restricted cross-origin requests

### Data Protection
- **Input Validation**: Server-side validation cho tất cả endpoints
- **SQL Injection Prevention**: JPA/Hibernate ORM
- **XSS Protection**: Content Security Policy headers
- **Rate Limiting**: Redis-based request throttling

## 🚀 Performance Optimization

### Frontend Optimization
- **Code Splitting**: Lazy loading components
- **Bundle Optimization**: Vite tree shaking
- **Image Optimization**: Lazy loading images
- **Caching Strategy**: Browser caching cho static assets

### Backend Optimization
- **Database Indexing**: Optimized queries với proper indexes
- **Connection Pooling**: HikariCP connection pool
- **Redis Caching**: Cache frequently accessed data
- **JVM Tuning**: Memory optimization cho production

## 📈 Scalability Considerations

### Horizontal Scaling Ready
- **Stateless Design**: JWT token cho session management
- **Database Sharding**: Ready for database partitioning
- **Load Balancing**: Nginx upstream configuration
- **Microservices Migration**: Modular code structure

### Monitoring & Logging
- **Application Logging**: Structured logging với Logback
- **Health Checks**: Spring Boot Actuator endpoints
- **Metrics Collection**: Ready for Prometheus integration
- **Error Tracking**: Comprehensive error handling

## 🎯 Business Value

### For Cinema Operators
- **Revenue Optimization**: Dynamic pricing capabilities
- **Operational Efficiency**: Automated booking management
- **Customer Insights**: Comprehensive reporting
- **Multi-location Support**: Centralized management

### For Customers
- **Convenience**: 24/7 online booking
- **Real-time Updates**: Live seat availability
- **Digital Experience**: Mobile-friendly interface
- **Secure Payments**: Multiple payment options

## 🔄 Development Workflow

### Version Control
- **Git Flow**: Feature branches với pull requests
- **Code Review**: Mandatory peer reviews
- **Automated Testing**: Unit tests và integration tests
- **Continuous Integration**: Docker-based CI/CD pipeline

### Quality Assurance
- **Code Standards**: ESLint cho frontend, Checkstyle cho backend
- **Security Scanning**: Automated vulnerability checks
- **Performance Testing**: Load testing cho peak scenarios
- **User Acceptance Testing**: Comprehensive testing protocols

---

**SidoCinemas** đại diện cho một giải pháp toàn diện cho ngành công nghiệp rạp chiếu phim, kết hợp công nghệ hiện đại với trải nghiệm người dùng xuất sắc.