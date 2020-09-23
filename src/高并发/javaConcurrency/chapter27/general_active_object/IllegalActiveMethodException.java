package 高并发.javaConcurrency.chapter27.general_active_object;

/**
 * 无效的 Active Method 异常
 */
public class IllegalActiveMethodException extends Exception {

    public IllegalActiveMethodException(String message) {
        super(message);
    }
}
