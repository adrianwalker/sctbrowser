package org.adrianwalker.terminology.sctbrowser.resource;

import static java.nio.charset.StandardCharsets.US_ASCII;
import static java.util.stream.Collectors.joining;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

public final class ResourceFile {

  private static final Logger LOGGER = LoggerFactory.getLogger(ResourceFile.class);
  private static final String LINE_SEPERATOR = System.getProperty("line.separator");

  private ResourceFile() {
  }

  public static String readAsString(final String filename) {

    LOGGER.debug("Reading resource '{}'", filename);

    try (BufferedReader reader = new BufferedReader(
            new InputStreamReader(
                    ResourceFile.class.getResourceAsStream(filename),
                    US_ASCII))) {

      return reader
              .lines()
              .collect(joining(LINE_SEPERATOR));

    } catch (final Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static URI asURI(final String filename) {
    
    LOGGER.debug("Creating URI for '{}'", filename);

    try {
      return ResourceFile.class.getResource(filename).toURI();
    } catch (final Exception e) {
      throw new RuntimeException(e);
    }
  }
}
