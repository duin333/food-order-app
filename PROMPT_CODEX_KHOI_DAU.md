# Prompt khởi tạo cho Codex (FoodGo)

Tôi đang phát triển ứng dụng Android FoodGo (ứng dụng đặt đồ ăn, đồ án sinh viên, nhóm 3 người) bằng Kotlin. Project Android Studio đã được tạo và chạy được app mẫu, nhưng CHƯA có chức năng nào.

Trước khi làm gì, hãy đọc `AGENTS.md`, `CLAUDE.md`, `CURRENT_TASK.md`, `WORKLOG.md` (mục cuối), `PROJECT_PLAN.md`, `UI_GUIDELINES.md`, `TEAM_TASKS.md`.

Nhiệm vụ (CHỈ ĐỀ XUẤT, chưa sửa file nào):
1. Kiểm tra cấu trúc project, Gradle, Android SDK và các thư viện hiện có (đọc file thật, đừng chỉ dựa vào tài liệu). Báo phiên bản Gradle thực tế.
2. Xác nhận project đang dùng XML + ViewBinding (không Compose) và theme/brand kit đã áp dụng đúng chưa (`colors.xml`, `dimens.xml`, `themes.xml`, `styles.xml`, font).
3. Đề xuất kiến trúc MVVM đơn giản cho ứng dụng đặt đồ ăn, phạm vi chỉ actor Khách hàng.
4. Đề xuất cách tổ chức package, Navigation (một Activity + Fragment, Navigation Component hoặc cách đơn giản hơn nếu phù hợp), ViewModel, Repository (dạng interface, dữ liệu mẫu ở BC3, sau này thay Firebase) và quản lý UI State (loading/empty/error/success).
5. Đề xuất model dùng chung: FoodItem (có thành phần), Restaurant, CartItem, Order, OrderStatus, GroupOrder, GroupMember, GroupCartItem, Voucher.
6. Đề xuất lộ trình từng giai đoạn dễ kiểm thử, ưu tiên: luồng khách hàng cốt lõi → Group Ordering → Eco + Hẹn giờ → hiệu ứng/hoàn thiện → Allergy Guard.
7. Trình bày bản kế hoạch bằng tiếng Việt, kèm danh sách file dự kiến tạo/sửa. KHÔNG chỉnh sửa file nào cho đến khi tôi đồng ý.

Yêu cầu: giải thích dễ hiểu, ưu tiên giải pháp đơn giản phù hợp đồ án sinh viên, không phức tạp hóa kiến trúc, không thêm thư viện ngoài kế hoạch.

Sau khi tôi duyệt và bạn làm xong, hãy cập nhật `WORKLOG.md` (thêm mục mới ở cuối) và `CURRENT_TASK.md` theo `AGENTS.md`.
