# FoodGo — Hướng dẫn làm việc cho Claude Code

## 1. Bối cảnh
FoodGo là ứng dụng Android đặt đồ ăn phục vụ đồ án môn học. Nhóm có 3 thành viên.
**Phạm vi đã chốt: chỉ làm actor KHÁCH HÀNG.** Các actor Admin, Chủ quán, Tài xế đã được bỏ (giảng viên không yêu cầu đủ actor). Ảnh thiết kế của các actor này vẫn nằm trong `design/` chỉ để tham khảo phong cách; KHÔNG code màn hình của chúng.

Nguồn thiết kế chính: `design/KhachHang_Actor/`. Màn hình đăng ký/đăng nhập có thể tham khảo thêm `design/Admin_Actor/MH_DangKy*.png` nếu cần.

## 2. Công nghệ bắt buộc
- Android Studio, Kotlin, XML layouts, ViewBinding (đã bật).
- AGP 9.4.1 (Kotlin tích hợp sẵn): KHÔNG thêm plugin Kotlin riêng.
- JDK 17, compileSdk/targetSdk 37, minSdk 24, Java target 11.
- Không dùng Jetpack Compose.
- Không tự ý thêm thư viện. Hai thư viện ĐỀ XUẤT, chỉ thêm sau khi cả nhóm đồng ý: **Glide** (tải ảnh) và **Lottie** (tùy chọn, chỉ cho vài animation nhỏ như đặt đơn thành công). Chuyển động cơ bản dùng API Android có sẵn.

## 3. Cách tổ chức code
- MVVM đơn giản, có Repository khi cần; tránh Clean Architecture phức tạp.
- Tổ chức theo package: `ui/`, `data/model/`, `data/repository/`, `data/sample/`, `viewmodel/`, `utils/`.
- Mỗi Activity/Fragment dùng ViewBinding; Fragment phải giải phóng binding trong `onDestroyView`.
- Dữ liệu mẫu tách khỏi code giao diện (`data/sample/`).
- Repository dạng **interface** (đặc biệt `GroupRepository`, `OrderRepository`) để báo cáo cuối thay bằng Firebase mà không sửa giao diện.
- Không lặp màu/kích thước/style; dùng `colors.xml`, `dimens.xml`, `themes.xml`, `styles`, drawable và component dùng chung.

## 4. Model dùng chung
`FoodItem` (có danh sách thành phần), `Restaurant`, `CartItem`, `Order`, `OrderStatus`, `GroupOrder`, `GroupMember`, `GroupCartItem`, `Voucher`.
Không còn `UserRole` (chỉ có khách hàng). Không tạo class trùng tên ở nhiều package. Báo nhóm trước khi đổi model dùng chung.

## 5. Quy tắc làm việc an toàn
Trước mỗi nhiệm vụ:
1. Đọc `CLAUDE.md`, `PROJECT_PLAN.md`, `UI_GUIDELINES.md`, `TEAM_TASKS.md`, `CURRENT_TASK.md`.
2. Kiểm tra file hiện có trước khi tạo/sửa.
3. Nêu ngắn gọn kế hoạch và danh sách file dự kiến bị ảnh hưởng.
4. Chỉ làm đúng phạm vi nhiệm vụ.
5. Không ghi đè/xóa code đang có nếu chưa giải thích lý do.
6. Không tự đổi Gradle, package name, minSdk hoặc kiến trúc.
7. Sau khi làm: liệt kê file đã tạo/sửa, cách kiểm tra, lỗi còn lại.
8. Không tuyên bố đã build/test thành công nếu chưa chạy.

## 6. Chất lượng UI (hướng thương hiệu)
- Phong cách: **xanh lá chủ đạo, nhiều tầng tông xanh** (đậm, tươi, bạc hà nhạt, rêu) + **một màu nhấn ấm** (cam/vàng) cho giá và khuyến mãi. Bảng màu chi tiết sẽ nằm trong brand kit (`colors.xml`); khi brand kit được chốt, nó thay thế palette đề xuất trong `UI_GUIDELINES.md`.
- **Không dùng mascot.** Nhận diện thương hiệu dựa vào logo, bảng màu xanh nhiều tầng, ảnh món chất lượng, typography và minh họa/animation đơn giản (splash, trạng thái trống/loading, đặt đơn thành công, Group Ordering).
- Quy tắc chuyển động dùng lại toàn app: nhún nút khi chạm, thẻ trượt vào, shimmer khi tải, món "bay" vào icon giỏ. Điện thoại không có hover; dùng ripple/nhún/đổi bóng khi chạm.
- Ảnh món: cùng tỉ lệ, cùng tông, đặt trong `res/`, mỗi ảnh dưới ~200KB, lấy từ nguồn dùng được (Unsplash/Pexels). Không phụ thuộc mạng khi demo. Tên file kiểu `food_com_tam_01.jpg`.
- Có trạng thái loading/empty/error; kiểm tra tràn chữ tiếng Việt và khả năng cuộn.
- Hiệu ứng không được phá luồng sử dụng.

## 5b. Phạm vi tính năng
**Cốt lõi:** trang chủ, tìm kiếm, chi tiết quán/món, giỏ hàng, xác nhận đặt hàng, theo dõi đơn, lịch sử đơn, hủy đơn, thông báo, cá nhân.

**Điểm nhấn (theo thứ tự ưu tiên):**
1. **Group Ordering + chia tiền** (trụ cột): tạo phòng, mã 6 ký tự, vào phòng, giỏ chung hiện tên người thêm, chia tiền (chủ trả hết / mỗi người một phần, chia phí ship, trừ voucher).
2. **Eco Rewards:** toggle "Không lấy dụng cụ nhựa" (mặc định bật) ở màn xác nhận; +50 điểm Eco đúng MỘT lần khi đơn hoàn thành; đổi voucher.
3. **Scheduled Delivery:** "Giao ngay / Hẹn giờ" + chọn khung giờ; hiển thị lịch ở màn theo dõi đơn. Không tự tính thời gian báo chủ quán (không có actor chủ quán).
4. **Allergy Guard** (nếu còn thời gian): hồ sơ dị ứng ở màn Cá nhân, badge cảnh báo trên món. Chỉ là cảnh báo dựa trên dữ liệu khai báo; luôn ghi "hãy xác nhận với nhà hàng"; không khẳng định món an toàn.
5. **Giao tiết kiệm (mô phỏng):** chỉ là lựa chọn phí ship (ví dụ 8k, 35-45 phút) ở màn xác nhận. KHÔNG tuyên bố đã ghép đơn/tối ưu tuyến thật.

## 7. Dữ liệu và backend
- BC3: dữ liệu mẫu/local state. Trạng thái đơn mô phỏng bằng bộ đếm giờ.
- Group Ordering ở BC3: thành viên khác là dữ liệu mẫu trên cùng một máy (có nút "Mời bạn (demo)"). Phải nói rõ là mô phỏng.
- Báo cáo cuối: Firebase nếu khả thi. Chỉ gọi là realtime sau khi kiểm thử trên ít nhất hai thiết bị/phiên chạy.
- Đánh dấu rõ dữ liệu mẫu. Không nhúng API key/mật khẩu. Không tích hợp thanh toán/bản đồ/GPS thật.

## 8. Tiêu chí hoàn thành một nhiệm vụ
- Code đúng package, không file trùng.
- Giao diện/luồng chạy đúng phạm vi.
- Không lỗi compile; chạy build nếu có thể.
- Có hướng dẫn kiểm tra thủ công.
- Nêu giới hạn còn lại, không giấu lỗi.
