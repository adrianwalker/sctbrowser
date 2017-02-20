package org.adrianwalker.terminology.sctbrowser.resource;

import java.net.URI;
import static org.junit.Assert.*;

import org.junit.Test;

public final class ResourceFileTest {

  @Test
  public void testReadAsString() {

    String s = ResourceFile.readAsString("/resource.txt");
    assertEquals("test", s);
  }

  @Test(expected = Exception.class)
  public void testReadAsStringInvalid() {

    ResourceFile.readAsString("/invalid.txt");
  }

  @Test
  public void testAsURI() {

    URI uri = ResourceFile.asURI("/resource.txt");
    assertNotNull(uri);
  }

  @Test(expected = Exception.class)
  public void testAsURIInvalid() {

    URI uri = ResourceFile.asURI("/invalid.txt");
  }
}
