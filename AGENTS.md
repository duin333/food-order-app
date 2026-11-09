# FoodGo — Hướng dẫn cho AI coding agent (Codex / Claude Code)

File này được Codex tự động đọc. Nguồn quy tắc chính nằm ở `CLAUDE.md`; file này chỉ thêm quy trình bàn giao giữa các phiên/tài khoản.

## Bắt đầu MỖI phiên làm việc
1. Đọc theo thứ tự: `AGENTS.md` → `CLAUDE.md` → `CURRENT_TASK.md` → `WORKLOG.md` (đọc kỹ mục mới nhất ở cuối file) → `PROJECT_PLAN.md`, `UI_GUIDELINES.md`, `TEAM_TASKS.md` khi cần.
2. Chạy `git status` và `git log --oneline -10` để biết tình trạng thật của code, không chỉ tin vào tài liệu.
3. Nếu tài liệu và code mâu thuẫn: nêu rõ chỗ mâu thuẫn, hỏi người dùng, không tự đoán.
4. Nêu kế hoạch ngắn và danh sách file sẽ tạo/sửa TRƯỚC khi code.

## Trong phiên
- Chỉ làm đúng nhiệm vụ người dùng giao. Một nhiệm vụ nhỏ mỗi lần.
- Kotlin + XML + ViewBinding. Không Compose. Không thêm thư viện/plugin khi chưa được đồng ý.
- Phạm vi: chỉ actor Khách hàng. Không code Admin/Chủ quán/Tài xế.
- Không sửa Gradle, package name, minSdk, model dùng chung khi chưa nêu lý do.
- Không hard-code màu/kích thước; dùng `colors.xml`, `dimens.xml`, `styles.xml`.
- Không commit secret, API key, `local.properties`, `.idea/`.
- Không tuyên bố build/test thành công nếu chưa thực sự chạy; ghi rõ lệnh đã chạy và kết quả.

## Kết thúc MỖI phiên (bắt buộc, để tài khoản khác làm tiếp được)
1. Thêm một mục mới vào CUỐI `WORKLOG.md` theo mẫu trong file đó.
2. Cập nhật `CURRENT_TASK.md`: trạng thái, quyết định mới, **một** nhiệm vụ tiếp theo.
3. Nhắc người dùng commit và push (`git add .`, `git commit`, `git push`) trước khi đổi tài khoản.
4. Không xóa mục cũ trong `WORKLOG.md`; chỉ thêm.
