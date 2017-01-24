package org.adrianwalker.terminology.sctbrowser.watch;

import static org.mockito.Mockito.mock;

import org.adrianwalker.terminology.sctbrowser.loader.Loader;

import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

public final class CommandFileObserverTest {

  @Test
  public void testUpdate() {

    Loader loader = mock(Loader.class);

    CommandFileObserver observer = new CommandFileObserver(loader);
    
    observer.update(null, null);
    
    Path path = Paths.get("src/test/resources/");
    observer.update(null, path);
    
    path = Paths.get("");
    observer.update(null, path);

    path = Paths.get("src/test/resources/load");
    observer.update(null, path);
  }
}
