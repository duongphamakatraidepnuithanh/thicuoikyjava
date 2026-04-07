package yennhi.utils;

// Tự định nghĩa một Custom Exception kế thừa từ Exception
public class GameFileException extends Exception {
    public GameFileException(String message) {
        super(message);
    }
}
