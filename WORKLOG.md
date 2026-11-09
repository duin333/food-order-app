# FoodGo — Nhật ký làm việc (bàn giao giữa các phiên/tài khoản)

Quy tắc: chỉ THÊM mục mới ở cuối, không sửa/xóa mục cũ. Mỗi mục ngắn gọn nhưng đủ để người/agent đọc sau làm tiếp mà không cần hỏi lại.

## Mẫu một mục
```
### [YYYY-MM-DD] Công cụ: Codex/Claude Code/Claude chat — Người giao việc: <tên>
**Nhiệm vụ:** ...
**Đã làm:** (file tạo/sửa + mục đích từng file)
**Cách kiểm tra:** ...
**Build/test đã chạy:** (lệnh + kết quả; ghi "chưa chạy" nếu chưa)
**Quyết định mới:** ...
**Lỗi / điểm chưa chắc chắn:** ...
**Nhiệm vụ tiếp theo đề xuất (chỉ một):** ...
```

---

### [2026-11-05] Claude chat — Người giao việc: chủ repo
**Nhiệm vụ:** Khởi tạo project và chốt phạm vi.
**Đã làm:**
- Tạo project Android Studio (Empty Views Activity, Kotlin, package `com.foodgo.app`), AGP 9.4.1, compileSdk/targetSdk 37, minSdk 24, JDK 17.
- Bật ViewBinding trong `app/build.gradle.kts`. Gradle Sync ổn, app mẫu chạy được ("Hello World").
- Đẩy lên GitHub nhánh `main`: https://github.com/duin333/Nhom9_AppDatXeGiaoDoAn.git
- Thêm tài liệu: `CLAUDE.md`, `PROJECT_PLAN.md`, `TEAM_TASKS.md`, `CURRENT_TASK.md`, `UI_GUIDELINES.md`, thư mục `design/`.
- Brand kit: `colors.xml`, `dimens.xml`, `themes.xml`, `styles.xml` (xanh lá nhiều tầng + màu xoài, font Be Vietnam Pro).
**Quyết định mới:**
- Chỉ làm actor Khách hàng; bỏ Admin/Chủ quán/Tài xế; không mascot.
- Điểm nhấn: Group Ordering > Eco Rewards > Scheduled Delivery > Allergy Guard (nếu còn thời gian). Smart Batch chỉ là lựa chọn phí ship mô phỏng.
- BC3 dùng dữ liệu mẫu; Firebase/realtime để báo cáo cuối.
**Build/test đã chạy:** App mẫu chạy được theo người dùng báo. Brand kit: người dùng báo đã áp dụng, chưa có log build.
**Lỗi / điểm chưa chắc chắn:** Chưa xác nhận phiên bản Gradle; chưa có thiết kế màn Group Ordering/Eco/Hẹn giờ/Allergy; font Be Vietnam Pro có thể chưa thêm.
**Nhiệm vụ tiếp theo đề xuất (chỉ một):** Đề xuất kiến trúc MVVM, cây package, model dùng chung (chưa code màn hình).
