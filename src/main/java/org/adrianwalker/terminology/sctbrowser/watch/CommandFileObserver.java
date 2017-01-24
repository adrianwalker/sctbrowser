package org.adrianwalker.terminology.sctbrowser.watch;

import static java.util.stream.Collectors.toList;

import org.adrianwalker.terminology.sctbrowser.loader.Loader;
import org.adrianwalker.terminology.sctbrowser.loader.TxtFileLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public final class CommandFileObserver implements Observer {

  private static final Logger LOGGER = LoggerFactory.getLogger(CommandFileObserver.class);
  private static final String LOAD_FILE = "load";
  private final TxtFileLoader txtFileLoader;

  public CommandFileObserver(final Loader loader) {

    this.txtFileLoader = new TxtFileLoader(loader);
  }

  @Override
  public void update(final Observable observable, final Object arg) {

    if (null == arg) {
      return;
    }

    Path commandFile = (Path) arg;
    if (Files.isDirectory(commandFile)) {
      return;
    }

    Path fileName = commandFile.getFileName();
    if (null == fileName) {
      return;
    }

    if (LOAD_FILE.equals(fileName.toString())) {

      try {
        load(readLoadFile(commandFile));
      } catch (final Exception e) {
        LOGGER.error(e.getMessage(), e);
      }
    }
  }

  private void load(final List<Path> dataDirs) throws Exception {

    LOGGER.info("starting load...");

    LOGGER.info("...dropping tables...");
    txtFileLoader.dropTables();

    LOGGER.info("...creating tables...");
    txtFileLoader.createTables();

    LOGGER.info("...loading data files...");

    dataDirs.forEach(dataDir -> {
      txtFileLoader.load(dataDir);
    });

    LOGGER.info("...creating indexes...");
    txtFileLoader.createIndexes();

    LOGGER.info("...done");
  }

  private List<Path> readLoadFile(final Path loadFile) throws IOException {

    return Files.lines(loadFile)
            .filter(line -> !line.isEmpty())
            .peek(line -> LOGGER.debug(line))
            .map(line -> loadFile.resolveSibling(line))
            .filter(path -> Files.isDirectory(path))
            .collect(toList());
  }
}
