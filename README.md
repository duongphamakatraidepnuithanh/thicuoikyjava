# 🎮 Game Xếp Hình

> **Bài tập lớn cuối kỳ môn Lập trình Java**
> **Nhóm thực hiện: Nhóm 4 UED**

## 👥 Thông tin nhóm (Team Members)

| STT | Họ và Tên | Mã Sinh Viên | Vai trò / Nhiệm vụ | Link GitHub Cá Nhân |
|---|---|---|---|---|
| 1 | Phạm Tùng Dương (Nhóm trưởng)| 3120225036 | Code Controller,Code Model, Xử lý Thuật toán, Main | [GitHub](https://github.com/duongphamakatraidepnuithanh) |
| 2 | Lê Thị Thanh Hằng] | 3120225044 | Xử lý File I/O, Main, test, Báo cáo | [GitHub](https://github.com/lth29072007-a11y) |
| 3 | Hoàng Yến Nhi | 3120225110 | ,Vẽ Giao diện (View), KeyListener, Đồ họa  | [GitHub](https://github.com/hynhik7-dot) |

## 📖 Giới thiệu dự án (Description)
Đây là dự án Game Xếp Gạch (Tetris) kinh điển được thiết kế lại với phong cách đồ họa Neon Synthwave đẹp mắt. Trò chơi giúp người chơi giải trí thông qua việc sắp xếp các khối gạch rơi tự do, ăn điểm khi lấp đầy hàng ngang và liên tục thử thách bản thân với hệ thống lưu trữ kỷ lục.

## ✨ Các chức năng chính (Features)
- [x] Thuật toán ma trận 2 chiều xử lý rơi tự do, va chạm và xoay khối gạch.
- [x] Tính điểm tự động và xóa hàng ngang khi xếp đầy.
- [x] Lưu trữ điểm kỷ lục (Highscore) vĩnh viễn với File I/O (Text).
- [x] Giao diện đồ họa (GUI) 2D cực kỳ bắt mắt bằng Java Swing.
- [x] Bắt lỗi nhập/xuất file chặt chẽ với Custom Exception (`GameFileException`) và thông báo bằng `JOptionPane`.
- [x] Áp dụng luồng thời gian (Timer) để quản lý tốc độ game.

## 🛠 Công nghệ & Thư viện sử dụng (Technologies)
* **Ngôn ngữ:** Java (JDK 17+)
* **Giao diện:** Java Swing, AWT (`Graphics2D`)
* **Lưu trữ:** File Text (`highscore.txt`)
* **Công cụ khác:** Git, GitHub, IntelliJ IDEA / Eclipse

## 📂 Cấu trúc thư mục (Project Structure)
Mã nguồn được tổ chức chặt chẽ theo mô hình **MVC (Model - View - Controller)**:

```text
src
 ┣ yennhi/
 ┃ ┣ model/       # Chứa các lớp đối tượng thực thể (GameModel, Tetromino)
 ┃ ┣ view/        # Chứa các lớp giao diện đồ họa (MainFrame, GamePanel)
 ┃ ┣ controller/  # Chứa logic nghiệp vụ, thuật toán (GameController)
 ┃ ┣ utils/       # Chứa tiện ích hỗ trợ, bắt lỗi file (FileHelper, GameFileException)
 ┃ ┗ Main.java    # File Entry-point để khởi động ứng dụng
