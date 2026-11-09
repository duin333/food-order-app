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

### [2026-11-09] Công cụ: Codex — Người giao việc: chủ repo
**Nhiệm vụ:** Chỉ 0A: JDK 17, kiểm tra build, sau đó styles/font nếu build thành công.
**Đã làm:** Sửa gradle/gradle-daemon-jvm.properties về toolchainVersion=17, bỏ các toolchainUrl tải JDK 25; cập nhật CURRENT_TASK.md ghi các quyết định đã duyệt. Không sửa font/styles/theme vì build lỗi và người dùng yêu cầu dừng. Bốn file font untracked là thay đổi có sẵn của người dùng, giữ nguyên.
**Cách kiểm tra:** Đọc đủ bảy tài liệu, git status, git log --oneline -10; đọc cấu hình và liệt kê font.
**Build/test đã chạy:**
- gradlew.bat --version ban đầu lỗi JAVA_HOME trỏ tới C:\Program Files\Apache NetBeans\jdk không tồn tại. Đặt JAVA_HOME=C:\Program Files\Java\jdk-17 chỉ trong process. Sandbox không tạo được wrapper lock C:\.gradle nên chạy lại có escalation.
- gradlew.bat --version: exit 0; Gradle 9.6.0; Launcher JVM Oracle 17.0.11; Daemon JVM Compatible with Java 17 (from gradle/gradle-daemon-jvm.properties). Đây là thông tin --version, chưa xác nhận bản patch JVM của process daemon riêng.
- gradlew.bat assembleDebug với JAVA_HOME JDK 17, chạy ngoài sandbox: exit 1, BUILD FAILED in 17s. Không chạy test/emulator.
**Quyết định mới:** Navigation Component không Safe Args; duyệt AndroidX Fragment KTX, Lifecycle ViewModel/LiveData, Navigation Fragment/UI, RecyclerView cho giai đoạn sau, chưa thêm. Các quyết định Order/Eco/chia tiền/vùng chạm/quyền sửa navigation/tách 1B/imageKey đã ghi vào CURRENT_TASK.md.
**Lỗi / điểm chưa chắc chắn:** 0A chưa hoàn tất. Build dừng tại packageDebugResources và mergeDebugResources với lỗi nguyên văn:
```text
D:\monhoc_hk5\FoodGo\app\src\main\res\font\BeVietnamPro-Bold.ttf: Error: 'B' is not a valid file-based resource name character: File-based resource names must contain only lowercase a-z, 0-9, or underscore
```
Chưa đổi tên font, tạo be_vietnam_pro.xml, bật fontFamily hoặc thêm styles/dimens. Không tự sửa Gradle thêm. JAVA_HOME hệ thống vẫn chưa thay đổi. Chưa commit/push.
**Nhiệm vụ tiếp theo đề xuất (chỉ một):** 0B — chốt package, model và repository contract; chỉ bắt đầu sau khi người dùng cho phép xử lý lỗi và hoàn tất 0A.

### [2026-11-09] Công cụ: Codex — Người giao việc: chủ repo — Tiếp tục 0A
**Nhiệm vụ:** Hoàn tất styles/font sau khi người dùng đổi tên bốn font, build bằng JDK 17.
**Đã làm:**
- Sửa app/src/main/res/values/dimens.xml: thêm text_button=16sp, stroke_button=2dp, touch_target_min=48dp.
- Sửa styles.xml: thay giá trị 16sp/2dp bằng resource; thêm Widget.FoodGo.Card, Chip, TextInputLayout/TextInputEditText và TextAppearance.FoodGo.Heading/Body/Caption. Heading dùng font 800; body/caption dùng 400; chip giữ state styling từ Material3/theme và vùng chạm tối thiểu 48dp.
- Tạo res/font/be_vietnam_pro.xml chỉ tham chiếu bốn font người dùng đã đổi tên, trọng lượng 400/500/700/800; bật android:fontFamily và fontFamily trong themes.xml. Không sửa nội dung hoặc tên file TTF.
- Cập nhật CURRENT_TASK.md: 0A hoàn tất ở mức resource/build; giữ đầy đủ quyết định giai đoạn sau; bước tiếp theo duy nhất 0B.
**Cách kiểm tra:** Đọc tài liệu, git status/log, kiểm tra font tồn tại và diff; build xác nhận resource/style/font liên kết hợp lệ. Kiểm tra thủ công sau này: gắn styles vào màn tương ứng, xem dấu tiếng Việt, heading 800, vùng chạm và font scale trên emulator.
**Build/test đã chạy:** `$env:JAVA_HOME='C:\Program Files\Java\jdk-17'; .\gradlew.bat assembleDebug` (ngoài sandbox): exit 0, BUILD SUCCESSFUL in 23s; 37 actionable tasks: 17 executed, 20 up-to-date. Không chạy test hoặc emulator. Chỉ chỉnh khoảng trắng cuối styles.xml sau build, không đổi ngữ nghĩa.
**Quyết định mới:** Không thêm quyết định kiến trúc; chỉ thực hiện phần 0A đã duyệt. Không thêm dependency, Navigation, model hoặc màn hình. Không thay Gradle trong phiên này; cấu hình daemon 17 từ phiên trước giữ nguyên.
**Lỗi / điểm chưa chắc chắn:** Lỗi tên font trước đây đã hết trong build hiện tại. Chưa kiểm tra trực quan styles/font trên emulator; styles mới chưa được gắn vào màn tính năng. JAVA_HOME hệ thống không đổi. Chưa commit/push.
**Nhiệm vụ tiếp theo đề xuất (chỉ một):** 0B — chốt cây package, model dùng chung và repository contract.

### [2026-11-09] Công cụ: Codex — Người giao việc: chủ repo — Giai đoạn 0B
**Nhiệm vụ:** Model, repository contract và dữ liệu mẫu; không Navigation/Fragment/ViewModel/UI hoặc thư viện mới.
**Đã làm:**
- Thêm data/model/: FoodItem, Restaurant, CartItem, Order, OrderStatus, GroupOrder, GroupMember, GroupCartItem, Voucher; SplitMode, GroupOrderStatus, DiscountType, DeliveryMode. ID String, tiền Long VND, thời gian epoch millis; Order có đủ Eco/hẹn giờ/deliveryMode.
- Thêm data/repository/: CatalogRepository, CartRepository, OrderRepository, GroupRepository, VoucherRepository (interface đồng bộ BC3; không EcoRepository).
- Thêm data/sample/: SampleCatalog.kt, SampleCatalogRepository.kt, SampleCartRepository.kt, SampleOrderRepository.kt, SampleGroupRepository.kt, SampleVoucherRepository.kt. Dữ liệu và backend bộ nhớ đánh dấu sample; một quán/giỏ/phòng; order snapshot, đặt lặp cùng ID không tạo đơn mới; trạng thái đơn tiến thủ công, không timer.
- Thêm AppContainer.kt ở package gốc, giữ repository chung; thêm SampleCatalogTest.kt (1 test). Cập nhật CURRENT_TASK.md, giữ các quyết định đã chốt và chỉ một bước tiếp theo 0C.
**Cách kiểm tra:** Đọc AGENTS/CLAUDE/CURRENT_TASK/WORKLOG, kiểm tra git status và 10 commit gần nhất; kiểm tra dữ liệu, git diff --check. Unit test xác nhận 5 quán/17 món, mọi restaurantId tồn tại, giá dương, ID không trùng và mỗi quán >=3 món. Kiểm tra report XML: 1 test mới, 0 failures/errors/skips. Test mẫu cũ không sửa.
**Build/test đã chạy:** Với JAVA_HOME=C:\Program Files\Java\jdk-17, ngoài sandbox: .\gradlew.bat testDebugUnitTest — BUILD SUCCESSFUL in 12s (exit 0); .\gradlew.bat assembleDebug — BUILD SUCCESSFUL in 3s (exit 0). Không chạy emulator/instrumentation hoặc kiểm thử nghiệp vụ repository đầy đủ.
**Quyết định mới:** Người dùng xác nhận danh sách là 17 món, giữ đúng 17 thay vì tự thêm món thứ 18. Rating/địa chỉ/phí/thời gian và ingredient là dữ liệu minh họa; allergenCodes giữ theo bảng, danh sách rỗng không bảo đảm an toàn dị ứng.
**Lỗi / điểm chưa chắc chắn:** Không có lỗi build/test. Repository chỉ local đồng bộ, mất dữ liệu khi process chết; Firebase sẽ cần contract bất đồng bộ. Chưa hiện thực chia tiền checkout, ledger Eco, timer theo dõi, transaction nối Group/Order hoặc ảnh thật; đó là công việc giai đoạn tính năng. Group mã phòng chỉ dùng trên một thiết bị. Không thêm dependency, không sửa Gradle/UI, không commit/push.
**Nhiệm vụ tiếp theo đề xuất (chỉ một):** 0C — khung app + Navigation (không Safe Args; nav_graph.xml và menu bottom navigation chỉ A sửa).
