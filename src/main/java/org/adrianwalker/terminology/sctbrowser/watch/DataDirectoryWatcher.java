package org.adrianwalker.terminology.sctbrowser.watch;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Observable;

public final class DataDirectoryWatcher extends Observable implements Watcher, Runnable {

  private static final Logger LOGGER = LoggerFactory.getLogger(DataDirectoryWatcher.class);
  private final Path dataDirectory;

  public DataDirectoryWatcher(final Path dataDirectory) throws Exception {

    this.dataDirectory = dataDirectory;

    if (!Files.exists(dataDirectory)) {

      LOGGER.debug("creating directory '{}'", dataDirectory);

      Files.createDirectories(dataDirectory);
    }
  }

  @Override
  public void run() {

    watch();
  }

  @Override
  public void watch() {

    LOGGER.info("watching directory '{}'", dataDirectory);

    WatchService watcher;
    try {
      watcher = FileSystems.getDefault().newWatchService();
      dataDirectory.register(watcher, ENTRY_CREATE, ENTRY_MODIFY);
    } catch (final IOException ioe) {
      LOGGER.error(ioe.getMessage(), ioe);
      return;
    }

    while (true) {

      WatchKey key;
      try {
        key = watcher.take();
      } catch (final InterruptedException ie) {
        LOGGER.error(ie.getMessage(), ie);
        break;
      }

      key.pollEvents()
              .stream()
              .filter(event -> !(event.kind() == OVERFLOW))
              .map(event -> (Path) event.context())
              .forEach(dataFile -> {
                LOGGER.info("processing file '{}'", dataFile);
                setChanged();
                notifyObservers(dataDirectory.resolve(dataFile));
              });

      if (!key.reset()) {
        LOGGER.error("watch key could not be reset");
        break;
      }
    }
  }
}
