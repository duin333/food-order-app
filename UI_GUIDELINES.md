# FoodGo — Quy chuẩn giao diện (brand kit v0.1)

Phạm vi: chỉ actor Khách hàng. Ảnh trong `design/KhachHang_Actor/` là nguồn tham chiếu bố cục; **màu sắc, font và component theo brand kit này** (các ảnh cũ chỉ có một tông xanh, chưa có thương hiệu).

## 1. Nguồn thiết kế
- Trước khi code một màn hình: đối chiếu ảnh tham khảo, xác định bố cục, nội dung, hành động chính, trạng thái, điều hướng.
- Các ảnh trong `design/ChuQuan_Actor`, `TaiXe_Actor`, `Admin_Actor` chỉ để tham khảo phong cách, không code.
- Màn Group Ordering, Eco, Hẹn giờ, Allergy chưa có ảnh: thiết kế theo brand kit, nhóm duyệt rồi mới code.
- Nếu chi tiết không rõ, ghi giả định và hỏi lại, không bịa.

## 2. Thương hiệu
Logo: chiếc lá trong ghim định vị. Phong cách: tươi, sạch, hiện đại; nhiều tầng xanh lá + một màu nhấn ấm. Không dùng mascot. Không dùng emoji làm icon chính.

| Tên | Hex | Dùng cho | Resource |
|---|---|---|---|
| Rêu đậm | #0B3B27 | chữ, tiêu đề | `foodgo_forest` |
| Xanh chủ đạo | #15803D | nút, CTA | `foodgo_primary` |
| Xanh tươi | #22C55E | icon nhấn, trạng thái | `foodgo_fresh` |
| Lá nhạt | #86D9A3 | viền, minh họa | `foodgo_leaf` |
| Bạc hà | #D5F3DF | chip, thẻ nhấn | `foodgo_mint` |
| Nền | #F1FAF4 | nền màn hình | `foodgo_mist` |
| Xoài | #F59E0B | khuyến mãi, Eco | `foodgo_mango` |
| Giá | #C2410C | giá tiền | `foodgo_price` |
| Chữ phụ | #5B6B62 | mô tả | `foodgo_text_secondary` |
| Cảnh báo | #B42318 | lỗi, dị ứng | `foodgo_error` |

Không hard-code màu trong layout; dùng `@color/...` hoặc thuộc tính theme.
Chữ trắng chỉ đặt trên `foodgo_primary` hoặc `foodgo_forest`; trên `foodgo_mango` dùng `foodgo_on_mango`.

## 3. Font
Be Vietnam Pro (Google Fonts, hỗ trợ đủ dấu tiếng Việt). Đóng gói file `.ttf` trong `res/font/` để chạy offline khi demo. Tiêu đề 800, nội dung 400, nhấn 700. Dùng `sp` cho chữ, `dp` cho kích thước.

## 4. Component dùng chung
- Nút chính/phụ/nhạt: `Widget.FoodGo.Button.Primary`, `.Secondary`, `.Soft` (cao 52dp, bo tròn).
- Thẻ: bo `radius_card` (24dp), nền trắng, bóng nhẹ, ảnh bo `radius_image`.
- Chip danh mục: bo 20dp; đang chọn nền xanh chủ đạo, chưa chọn nền bạc hà.
- Giá: `TextAppearance.FoodGo.Price`.
- Badge cảnh báo dị ứng: nền `foodgo_error_bg`, chữ `foodgo_error`.
- Voucher: ô màu xoài bên trái, thông tin bên phải.
- Bottom navigation 4 tab: Trang chủ, Đơn hàng, Thông báo, Cá nhân; tab đang chọn là nền tròn xanh chủ đạo.
- Trạng thái trống/loading (shimmer)/lỗi dùng chung cho toàn app.
Không tạo component chung phức tạp nếu chỉ dùng một lần.

## 5. Chuyển động (dùng lại toàn app)
Nút nhún khi chạm, thẻ trượt vào, shimmer khi tải, món bay vào icon giỏ, animation nhỏ khi đặt đơn thành công. Điện thoại không có hover: dùng ripple, nhún, đổi bóng khi chạm. Hiệu ứng không được cản luồng sử dụng.

## 6. Bố cục và khả năng dùng
- Phân cấp rõ: tiêu đề, thông tin phụ, giá, CTA.
- Khoảng cách theo `space_*`, bo góc theo `radius_*`.
- Nội dung cuộn được; kiểm tra tràn chữ tiếng Việt và font scale.
- Nút quan trọng không sát mép màn hình; vùng chạm tối thiểu 48dp.
- Nút phải thực hiện hành động thật trong phạm vi hiện tại.

## 7. Hình ảnh món ăn
- Nguồn Unsplash/Pexels, cùng tỉ lệ (4:3 hoặc 1:1), cùng tông, đúng món.
- Đặt trong `res/drawable/`, tên `food_com_tam_01.jpg`, dưới ~200KB mỗi ảnh. Không phụ thuộc mạng khi demo.
- Không dùng emoji hoặc placeholder trong bản demo cuối.

## 8. XML và ViewBinding
- Layout dễ đọc, hạn chế lồng nhiều cấp.
- Dùng ViewBinding, không `findViewById()` trong code mới.
- Tách strings, colors, dimens, styles.
