# FoodGo — Nhiệm vụ hiện tại

## Trạng thái
- Project Kotlin + XML, package `com.foodgo.app`; ViewBinding đã bật, Activity mẫu chưa dùng binding.
- Chỉ actor Khách hàng; 0B đã có model, repository contract và implementation mẫu. Chưa có Navigation, Fragment, ViewModel hoặc màn hình tính năng.
- 0A đã hoàn tất về resource và build: brand kit colors/dimens/styles/theme và font offline.
- Đã sửa gradle/gradle-daemon-jvm.properties: toolchainVersion=17; bỏ toàn bộ URL tải JDK 25 cũ.
- `gradlew --version`: Gradle 9.6.0; Launcher JVM Oracle 17.0.11; Daemon JVM compatible with Java 17.
- `gradlew.bat assembleDebug` với JAVA_HOME=C:\Program Files\Java\jdk-17: BUILD SUCCESSFUL in 23s, exit 0 (2026-11-09).
- Đã chuyển 16sp/2dp sang text_button/stroke_button; thêm touch_target_min=48dp; thêm style Card, Chip, TextInputLayout/TextInputEditText, Heading/Body/Caption. Đã bật hai fontFamily trong theme.
- Người dùng đã đổi font thành be_vietnam_pro_regular.ttf, be_vietnam_pro_medium.ttf, be_vietnam_pro_bold.ttf, be_vietnam_pro_extrabold.ttf. be_vietnam_pro.xml chỉ tham chiếu bốn font này, trọng lượng 400/500/700/800. Chưa kiểm tra trực quan trên emulator.

## Quyết định đã duyệt
- JDK 17; AGP 9.4.1; compileSdk/targetSdk 37; minSdk 24; Java target 11. Không thêm plugin Kotlin riêng.
- JAVA_HOME môi trường hiện trỏ tới C:\Program Files\Apache NetBeans\jdk không tồn tại. Các lệnh kiểm tra đã đặt JAVA_HOME=C:\Program Files\Java\jdk-17 chỉ trong process, không đổi biến hệ thống.
- MVVM đơn giản; package ui/, data/model/, data/repository/, data/sample/, viewmodel/, utils/.
- Một Activity + Fragment, Navigation Component, không Safe Args.
- Đã duyệt Fragment KTX, Lifecycle ViewModel + LiveData, Navigation Fragment/UI, RecyclerView. Chưa thêm dependency hoặc chọn phiên bản ở 0A; tới bước phù hợp phải kiểm tra bản stable tương thích compileSdk 37 và AGP 9.4.1.
- Không thêm thư viện khác; Glide/Lottie/Firebase để sau.
- UI State bằng LiveData: Loading / Empty / Error / Success.
- Repository dạng interface; BC3 dùng sample/local state, Firebase để giai đoạn sau. Group BC3 phải ghi rõ mô phỏng.
- Model chung: FoodItem (có thành phần), Restaurant, CartItem, Order, OrderStatus, GroupOrder, GroupMember, GroupCartItem, Voucher; không UserRole.
- Giỏ cá nhân/phòng nhóm chỉ một quán; đơn lưu snapshot tên và giá.
- Order có sẵn từ đầu: noPlasticCutlery mặc định true, ecoPointsAwarded, scheduledStartMillis?, scheduledEndMillis?, deliveryMode (EXPRESS/SAVER).
- Điểm Eco cộng theo orderId đúng một lần khi đơn hoàn thành và đủ điều kiện Eco.
- Chia tiền nhóm: mỗi người trả phần món của mình; phí giao và giảm giá chia đều; số dư lẻ dồn cho chủ phòng; tổng các phần phải bằng tổng đơn.
- Vùng chạm tối thiểu 48dp; icon_button 40dp chỉ là kích thước icon.
- Chỉ thành viên A sửa nav_graph.xml và menu bottom navigation.
- Giai đoạn 1B tách thành ba nhiệm vụ nhỏ: xác nhận đặt hàng; theo dõi đơn; lịch sử và hủy.
- Ảnh món dùng ảnh giữ chỗ qua imageKey trước, thay ảnh thật sau.
- Ưu tiên: luồng khách hàng cốt lõi → Group Ordering → Eco + Hẹn giờ → hiệu ứng/hoàn thiện → Allergy Guard.
- Màn Group/Eco/Hẹn giờ/Allergy cần nhóm duyệt thiết kế trước khi code.

## Nhiệm vụ tiếp theo duy nhất
**0C — khung app + Navigation.** Chỉ thành viên A sửa MainActivity, nav_graph.xml và menu bottom navigation; không Safe Args.

## File thay đổi phiên 2026-11-09
- gradle/gradle-daemon-jvm.properties: chỉ định JDK 17, bỏ URL JDK 25.
- app/src/main/res/values/styles.xml, dimens.xml, themes.xml và res/font/be_vietnam_pro.xml: hoàn thiện styles/font 0A.
- CURRENT_TASK.md: trạng thái thực tế và quyết định đã duyệt.
- WORKLOG.md: thêm nhật ký ở cuối.
- 0B: thêm data/model/ (9 model, 4 enum phụ), data/repository/ (5 interface), data/sample/ (SampleCatalog và 5 Sample repository), AppContainer.kt, SampleCatalogTest.kt. Không sửa Gradle hoặc UI; không commit/push.

## Kết quả 0B (2026-11-09)
- Người dùng xác nhận giữ đúng 17 món trong danh sách (không phải 18); 5 quán, số món lần lượt 4/3/4/3/3. Giá và allergenCodes giữ đúng yêu cầu. Ingredients, địa chỉ, rating, phí/thời gian giao là dữ liệu mẫu; imageKey chưa có ảnh thật.
- AppContainer ở package com.foodgo.app giữ chung Catalog/Cart/Order/Group/VoucherRepository. Không EcoRepository. BC3 dùng hàm đồng bộ, gọi/đọc lại sau mutation, chưa có stream/LiveData ở tầng data; khi làm Firebase cần điều chỉnh contract bất đồng bộ và lỗi, không gọi I/O mạng theo contract đồng bộ hiện tại.
- Repository dùng bộ nhớ, reset khi process chết; không persistence hoặc realtime giữa thiết bị. Thao tác sai ném IllegalArgumentException. Giỏ khác quán bị từ chối, chỉ clear khi khách xác nhận.
- Order lưu snapshot item/tên/giá và địa chỉ, tổng tiền tính từ snapshot; placeOrder cùng ID trả đơn có sẵn, không tạo bản sao. Huỷ chỉ khi PENDING_CONFIRMATION. advanceDemoStatus là thao tác mô phỏng tường minh, chưa chạy timer.
- Group có mã 6 ký tự trên cùng máy; thêm thành viên, thêm/xóa món, chọn split mode, host khóa phòng và đánh dấu đã đặt. Chưa tự động liên kết transaction Group/Order; caller sẽ điều phối checkout ở giai đoạn sau.
- Chia tiền theo quy tắc đã duyệt vẫn là yêu cầu cho checkout nhóm giai đoạn sau; chưa viết thuật toán chia tiền hoặc cộng điểm Eco ở 0B. Order đã chứa các trường Eco/hẹn giờ/deliveryMode từ đầu.
- testDebugUnitTest bằng JDK 17: BUILD SUCCESSFUL in 12s, exit 0; SampleCatalogTest có 1 test, 0 failures/errors/skips (test mẫu cũ giữ nguyên).
- assembleDebug bằng JDK 17: BUILD SUCCESSFUL in 3s, exit 0. Chưa test UI/emulator hoặc toàn bộ nghiệp vụ repository; unit test mới chỉ kiểm tra dữ liệu mẫu theo yêu cầu.
