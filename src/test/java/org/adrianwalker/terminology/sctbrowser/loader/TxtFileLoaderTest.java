package org.adrianwalker.terminology.sctbrowser.loader;

import static org.mockito.Mockito.mock;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.Test;

public final class TxtFileLoaderTest {

  @Test
  public void testLoad() {

    Loader loader = mock(Loader.class);
    TxtFileLoader txtFileLoader = new TxtFileLoader(loader);

    Path path = Paths.get("src/test/resources/resource.txt");
    txtFileLoader.load(path);

    path = Paths.get("src/test/resources/data");
    txtFileLoader.load(path);
  }
}
