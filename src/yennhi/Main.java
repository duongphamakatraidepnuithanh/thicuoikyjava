package yennhi;

import yennhi.controller.GameController;
import yennhi.model.GameModel;
import yennhi.view.GamePanel;
import yennhi.view.MainFrame;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Chạy giao diện trên luồng (Thread) an toàn của Swing
        SwingUtilities.invokeLater(() -> {
            // Khởi tạo MVC
            GameModel model = new GameModel();
            GamePanel panel = new GamePanel(model);
            GameController controller = new GameController(model, panel);
            
            // Lắp Panel vào Cửa sổ chính và hiển thị
            new MainFrame(panel);
        });
    }
}
