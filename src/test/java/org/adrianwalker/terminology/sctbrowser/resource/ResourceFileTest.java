package org.adrianwalker.terminology.sctbrowser.resource;

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

    ResourceFile.readAsString("/invalis.txt");
  }
}
