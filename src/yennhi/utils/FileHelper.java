package yennhi.utils;

import java.io.*;
import javax.swing.JOptionPane;

public class FileHelper {
    private static final String FILE_NAME = "highscore.txt";

    // Đọc điểm cao nhất từ file text
    public static int readHighScore() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line = reader.readLine();
            if (line != null) {
                return Integer.parseInt(line.trim());
            }
        } catch (IOException | NumberFormatException e) {
            return 0; // Nếu file chưa tồn tại hoặc lỗi, trả về 0
        }
        return 0;
    }

    // Ghi điểm cao nhất mới vào file text, có ném ngoại lệ tự định nghĩa
    public static void saveHighScore(int score) throws GameFileException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write(String.valueOf(score));
        } catch (IOException e) {
            // Hiện thông báo lỗi trực quan cho người chơi
            JOptionPane.showMessageDialog(null, "Lỗi khi lưu điểm: " + e.getMessage(), "Lỗi Hệ Thống", JOptionPane.ERROR_MESSAGE);
            // Ném Custom Exception theo yêu cầu đề bài
            throw new GameFileException("Không thể ghi dữ liệu xuống file " + FILE_NAME);
        }
    }
}
