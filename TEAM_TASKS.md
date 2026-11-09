# FoodGo — Phân chia công việc cho nhóm 3 người (chỉ actor Khách hàng)

## Nguyên tắc phối hợp
- Chia theo module/feature. Mỗi người một branch riêng; không code trực tiếp lên `main`.
- Chốt contract giao diện và model chung trước khi chia code (Giai đoạn 0).
- Không để hai người cùng sửa `MainActivity`, điều hướng, theme hoặc cùng một layout cùng lúc.
- Commit/PR nhỏ, có mô tả và cách kiểm tra.
- Trước khi đổi chữ ký hàm/model dùng chung, báo cả nhóm.

## Giai đoạn 0: làm chung, xong rồi mới chia code
Đề xuất A làm, B và C duyệt:
1. **Brand kit:** `colors.xml` (bảng xanh nhiều tầng + 1 màu nhấn ấm), `themes.xml`, font, `dimens.xml`, style nút/thẻ/ô nhập.
2. **Cây package + model dùng chung:** `FoodItem`, `Restaurant`, `CartItem`, `Order`, `OrderStatus`, `GroupOrder`, `GroupMember`, `GroupCartItem`, `Voucher`.
3. **Dữ liệu mẫu:** 4–5 quán, 15–20 món, ảnh đồng bộ, mỗi món có danh sách thành phần.
4. **Khung điều hướng:** `MainActivity` + bottom navigation 4 tab (Trang chủ, Đơn hàng, Thông báo, Cá nhân).

Chỉ A được sửa `MainActivity`, điều hướng và theme trong giai đoạn này.

## Thành viên A — Khung app & luồng duyệt món
- Splash có logo và animation nhẹ.
- Trang chủ (tìm kiếm, danh mục, banner, danh sách quán), màn Tìm kiếm.
- Chi tiết quán, chi tiết món (chọn số lượng, thêm giỏ).
- Hiệu ứng dùng chung: nhún nút, trượt thẻ, shimmer, món bay vào giỏ.
- Trạng thái trống/lỗi/loading dùng chung.
- Logo FoodGo và bộ icon/minh họa đơn giản (trống, loading, thành công).
- Giữ model, `Voucher`, điều hướng, theme.

## Thành viên B — Giỏ hàng, đặt đơn, Group Ordering
- Giỏ hàng cá nhân (số lượng, tổng tiền).
- Xác nhận đặt hàng: địa chỉ, phương thức giao (hỏa tốc / tiết kiệm mô phỏng), giao ngay/hẹn giờ + chọn khung giờ, áp voucher, tổng tiền.
- `OrderRepository`, tạo đơn mẫu; giữ `OrderStatus`.
- **Group Ordering:** tạo phòng + mã 6 ký tự, vào phòng bằng mã, giỏ chung hiện tên người thêm, thành viên mẫu + nút "Mời bạn (demo)", chia tiền (chủ trả hết / mỗi người một phần, chia phí ship, trừ voucher).
- `GroupRepository` dạng interface.
- Checklist test: tổng tiền, thêm/xóa món, thêm/xóa thành viên, chia tiền.

## Thành viên C — Đơn hàng, Eco, Cá nhân, Allergy Guard
- **Theo dõi đơn:** trạng thái tự chạy bằng bộ đếm giờ (Chờ xác nhận → Đang chuẩn bị → Đang giao → Hoàn thành); hiển thị lịch hẹn giờ nếu có.
- **Lịch sử đơn** (gồm đơn đã hủy), màn **Hủy đơn**, **Thông báo** (dữ liệu mẫu).
- **Eco Rewards:** toggle "Không lấy dụng cụ nhựa" (mặc định bật; làm thành component dùng chung để B nhúng vào màn xác nhận), cộng 50 điểm đúng một lần khi đơn hoàn thành, màn điểm Eco + lịch sử, đổi voucher.
- **Màn Cá nhân:** thông tin, đăng xuất, hồ sơ sức khỏe/dị ứng.
- **Allergy Guard** (nếu còn thời gian): badge cảnh báo trên món, cảnh báo khi thêm giỏ, ghi rõ "dựa trên dữ liệu khai báo".

## Điểm giao nhau cần báo trước
| Điểm giao | Ai giữ | Ai dùng |
|---|---|---|
| Model và `Voucher` | A | B, C |
| Màn xác nhận đặt hàng | B | C gắn component Eco |
| `OrderStatus`, `OrderRepository` | B | C đọc để theo dõi đơn |
| Màn Cá nhân | C | A (tab điều hướng) |
| Badge dị ứng trên thẻ món | C | A (thẻ món) |

## Quy tắc chung về code
- Một file/feature có một người chịu trách nhiệm chính.
- Không tạo class trùng tên ở nhiều package.
- Không commit secret, API key, `local.properties`.
- Mỗi thành viên phải hiểu và giải thích được phần mình code khi bảo vệ.
- Mọi người dùng JDK 17.

## Git workflow
- `main`: bản ổn định có thể demo.
- Branch: `feature/browse-home` (A), `feature/cart-group-order` (B), `feature/orders-eco-profile` (C).
- Trước khi merge: pull code mới, kiểm tra conflict, chạy build.
- Commit rõ nghĩa, ví dụ: `feat: add food detail screen`, `fix: recalculate group split`.

## Nhịp phối hợp
- Đầu buổi: ai làm gì, đụng file nào.
- Cuối buổi: đã làm gì, còn vướng gì, việc tiếp theo.
- Tích hợp sớm và thường xuyên; review chéo.
