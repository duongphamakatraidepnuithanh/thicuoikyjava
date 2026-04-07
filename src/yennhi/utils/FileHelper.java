package yennhi.utils;

import java.io.*;

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
            // Nếu file chưa tồn tại hoặc lỗi, trả về 0
            return 0;
        }
        return 0;
    }

    // Ghi điểm cao nhất mới vào file text
    public static void saveHighScore(int score) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write(String.valueOf(score));
        } catch (IOException e) {
            System.err.println("Lỗi khi lưu điểm: " + e.getMessage());
        }
    }
}
