**# 🎮 Game Xếp Hình

> **Bài tập lớn cuối kỳ môn Lập trình Java**
> **Nhóm thực hiện: Nhóm 4 UED**

## 👥 Thông tin nhóm (Team Members)

| STT | Họ và Tên | Mã Sinh Viên | Vai trò / Nhiệm vụ | Link GitHub Cá Nhân |
|---|---|---|---|---|
| 1 | Phạm Tùng Dương (Nhóm trưởng)| 3120225036 | Code Controller, Code Model, Xử lý Thuật toán, Main, Báo cáo | [GitHub](https://github.com/duongphamakatraidepnuithanh) |
| 2 | Lê Thị Thanh Hằng | 3120225044 | Xử lý File I/O, Main, test, Báo cáo | [GitHub](https://github.com/lth29072007-a11y) |
| 3 | Hoàng Yến Nhi | 3120225110 | Vẽ Giao diện (View), KeyListener, Đồ họa  | [GitHub](https://github.com/hynhik7-dot) |

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
```
## 🚀 Hướng dẫn cài đặt và chạy (Installation)

1. **Clone repository này về máy:**
   `git clone https://github.com/duongphamakatraidepnuithanh/thicuoikyjava.git`

2. **Chạy ứng dụng:**
   * Mở project bằng IDE (IntelliJ IDEA / Eclipse / VS Code).
   * Chạy file `src/yennhi/Main.java` để bắt đầu chơi.
   * Nhấn `ENTER` để bắt đầu, dùng các phím `MŨI TÊN` để điều khiển khối gạch.

## 📸 Ảnh chụp màn hình (Screenshots)

**🎮 Màn hình bắt đầu chơi:**
![Màn hình bắt đầu chơi](https://github.com/user-attachments/assets/a345e39b-9cfb-4c61-9724-659fe545fb42)

**🎮 Giao diện lúc đang chơi:**
![Giao diện lúc đang chơi](https://github.com/user-attachments/assets/7d5cafad-d211-47be-9a31-d34405daca9d)

**❌ Khi Game Over:**
![Khi Game Over](https://github.com/user-attachments/assets/946c9f3c-d1f5-4cb5-b67d-bd8e6c896917)

**🏆 File lưu kỷ lục (Highscore):**
![Điểm cao nhất](https://github.com/user-attachments/assets/d6d0d5c8-00fa-457b-b2be-0161182557c4)
