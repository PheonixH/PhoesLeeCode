package 高并发.javaConcurrency.chapter28.directory_target_monitor;

import 高并发.javaConcurrency.chapter28.Subscribe;

public class FileChangeListener {

    @Subscribe
    public void change(FileChangeEvent event) {
        System.out.printf("%s-%s\n", event.getPath(), event.getKind());
    }
}
