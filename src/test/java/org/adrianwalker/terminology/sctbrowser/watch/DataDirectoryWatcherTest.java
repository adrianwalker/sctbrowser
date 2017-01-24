package org.adrianwalker.terminology.sctbrowser.watch;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Observable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class DataDirectoryWatcherTest {

  private static final String PATH = "src/test/resources/watch";
  private static final String FILE = "watch.txt";
  private static final int SLEEP = 100;

  @Test
  public void testWatch() throws Exception {

    Path dir = Paths.get(PATH);
    DataDirectoryWatcher watcher = new DataDirectoryWatcher(dir);
    watcher.addObserver((final Observable o, final Object arg) -> {
      Path file = (Path) arg;
      assertTrue(Files.exists(file));
    });

    ExecutorService service = Executors.newSingleThreadExecutor();
    service.execute(watcher);
    Path file = dir.resolve(FILE);

    Thread.sleep(SLEEP);

    Files.createFile(file);

    Thread.sleep(SLEEP);

    Files.deleteIfExists(file);
    Files.deleteIfExists(dir);

    service.shutdown();
  }
}
