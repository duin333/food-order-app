# FoodGo — Kế hoạch sản phẩm và phạm vi

## 1. Mục tiêu
Ứng dụng Android đặt đồ ăn có UI đẹp, mang thương hiệu riêng (logo, xanh lá nhiều tầng, ảnh món đẹp, chuyển động), một luồng demo trơn tru và các điểm nhấn: Group Ordering, Eco Rewards, Scheduled Delivery. Ưu tiên ổn định và giải thích được khi bảo vệ.

## 2. Vai trò người dùng
Chỉ có **Khách hàng**. Admin, Chủ quán, Tài xế đã bỏ khỏi phạm vi (giảng viên không yêu cầu đủ actor). Không có `UserRole`.

## 3. Tính năng cốt lõi
- Splash (logo + animation nhẹ), đăng nhập demo.
- Trang chủ, danh mục, banner, danh sách quán, tìm kiếm.
- Chi tiết quán, chi tiết món, thêm giỏ.
- Giỏ hàng, thay đổi số lượng, tổng tiền.
- Xác nhận đặt hàng (địa chỉ, phương thức giao, hẹn giờ, voucher, Eco).
- Theo dõi đơn (trạng thái mô phỏng bằng bộ đếm giờ), lịch sử đơn, hủy đơn.
- Thông báo (dữ liệu mẫu), màn Cá nhân.

## 4. Tính năng tạo khác biệt
### P1: Group Ordering + chia tiền
- Chủ phòng tạo phòng, nhận mã 6 ký tự (QR/link chỉ làm nếu dư thời gian).
- Thành viên vào bằng mã, thêm món vào giỏ chung, hiển thị rõ ai thêm món nào.
- Chia tiền: chủ trả hết hoặc mỗi người trả phần mình; chia phí ship; trừ voucher; hiển thị số tiền từng người.
- BC3: thành viên mẫu trên một máy + nút "Mời bạn (demo)"; ghi rõ là mô phỏng.
- Báo cáo cuối: Firebase; chỉ gọi realtime sau khi thử trên 2 thiết bị.
- `GroupRepository` là interface.

### P2: Eco Rewards
- Toggle "Không lấy dụng cụ nhựa" mặc định bật tại màn xác nhận đặt hàng.
- +50 điểm Eco, cộng đúng một lần khi đơn hoàn thành (không cộng lặp khi trạng thái đổi).
- Màn điểm Eco, lịch sử điểm, đổi voucher giảm giá cho đơn sau.

### P3: Scheduled Delivery
- "Giao ngay" / "Hẹn giờ giao" + chọn khung giờ (ví dụ 11:45–12:00); kiểm tra đầu vào; hiển thị lịch ở màn theo dõi đơn.
- Không có phần tự tính thời gian báo chủ quán.

### P4: Allergy Guard (nếu còn thời gian)
- Hồ sơ dị ứng/ăn kiêng ở màn Cá nhân; món có metadata thành phần.
- Badge cảnh báo trên món và cảnh báo khi thêm giỏ.
- Ghi rõ: cảnh báo dựa trên dữ liệu khai báo, cần xác nhận với nhà hàng; không bảo đảm an toàn tuyệt đối.

### Giao tiết kiệm (mô phỏng, thay cho Smart Batch Delivery)
- Chỉ là một lựa chọn phí ship ở màn xác nhận (ví dụ 8k, 35–45 phút) so với hỏa tốc (20k).
- Không tuyên bố ghép đơn/tối ưu tuyến thật.

## 5. Kịch bản demo (5–6 phút)
1. Splash → Trang chủ.
2. Chọn quán → chọn món → thêm giỏ (hiệu ứng bay vào giỏ).
3. Đặt nhóm: tạo phòng → thành viên vào → thêm món → chia tiền.
4. Xác nhận: giao ngay/hẹn giờ/tiết kiệm, bật Eco, áp voucher.
5. Theo dõi đơn tự chạy → hoàn thành → +50 điểm Eco.
6. Màn Eco: xem điểm, đổi voucher.

## 6. Mục tiêu theo đợt báo cáo
### BC3
- Project sạch, cấu trúc nhất quán, dữ liệu mẫu có tổ chức.
- Luồng khách hàng cốt lõi chạy trơn tru.
- Group Ordering mức local/sample data.
- Eco + Hẹn giờ ở màn xác nhận.
- Brand kit áp dụng nhất quán.

### Báo cáo cuối
- Hoàn thiện luồng, kiểm thử, sửa UI.
- Firebase cho Group Ordering/đơn nếu khả thi.
- Allergy Guard nếu chưa làm.
- Chuẩn bị kịch bản demo, APK và video quay màn hình dự phòng.

## 7. Thứ tự ưu tiên
1. App build được và mở được.
2. Luồng khách hàng cốt lõi.
3. Group Ordering.
4. Eco Rewards + Hẹn giờ.
5. Chất lượng UI, hiệu ứng, trạng thái trống/lỗi/loading.
6. Allergy Guard.

**Cắt theo thứ tự khi thiếu thời gian:** Allergy Guard → Hẹn giờ → đổi voucher Eco (giữ toggle + điểm) → animation phụ (giữ hiệu ứng cơ bản). Không bao giờ cắt luồng cốt lõi và Group Ordering.

## 8. Không làm
Admin, Chủ quán, Tài xế, Smart Batch thật, thanh toán thật, bản đồ/GPS thật. Firebase để dành cho báo cáo cuối.

## 9. Điều chưa được tự suy đoán
- Chi tiết màn hình phải đối chiếu `design/KhachHang_Actor/`.
- Màn hình Group Ordering, Eco, Hẹn giờ, Allergy chưa có thiết kế; cần thiết kế theo brand kit và được nhóm duyệt trước khi code.
- Chưa chốt backend; chưa có xác thực thật.
