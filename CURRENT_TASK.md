# FoodGo — Nhiệm vụ hiện tại

## Trạng thái
- Project Kotlin + XML, package `com.foodgo.app`; ViewBinding đã bật, Activity mẫu chưa dùng binding.
- Chỉ actor Khách hàng; chưa có chức năng, model hoặc Navigation.
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
**0B — chốt cây package, model dùng chung và repository contract.** Chưa triển khai trong phiên 0A.

## File thay đổi phiên 2026-11-09
- gradle/gradle-daemon-jvm.properties: chỉ định JDK 17, bỏ URL JDK 25.
- app/src/main/res/values/styles.xml, dimens.xml, themes.xml và res/font/be_vietnam_pro.xml: hoàn thiện styles/font 0A.
- CURRENT_TASK.md: trạng thái thực tế và quyết định đã duyệt.
- WORKLOG.md: thêm nhật ký ở cuối.
- Không sửa Gradle dependency, model, màn hình hoặc Navigation. Không commit/push.
