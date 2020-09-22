package 高并发.Java高并发编程详解多线程架构设计.Chapter7_Hook线程以及捕获线程执行异常;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @program: PhoesLeeCode
 * @description: 利用hook线程防止程序重启 --- 待测试
 * @author: Feng.H
 * @create: 2020-09-19 17:58
 **/
public class PreventDuplicated {

    private final static String LOCK_PATH = "D:\\";

    private final static String LOCK_FILE = ".lock";

    private final static String PERMISSIONS = "rw-------";

    public static void main(String[] args) throws IOException {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("The program received kill SIGNAL.");
            getLockFile().toFile().delete();
        }));

        //检查是否存在.lock文件
        checkRunning();

        //模拟程序运行
        for (; ; ) {
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void checkRunning() throws IOException {
        Path path = getLockFile();
        if (path.toFile().exists()) {
            throw new RuntimeException("The program already running!");
        }
        Set<PosixFilePermission> permissions = PosixFilePermissions.fromString(PERMISSIONS);

        Files.createFile(path, PosixFilePermissions.asFileAttribute(permissions));
    }

    public static Path getLockFile() {
        return Paths.get(LOCK_PATH, LOCK_FILE);
    }
}
