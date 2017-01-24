package org.adrianwalker.terminology.sctbrowser.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import static org.junit.Assert.*;

public final class RestContextResolverTest {
  
  @Test
  public void testGetContext() {

    RestContextResolver resolver = new RestContextResolver();
    ObjectMapper mapper = resolver.getContext(null);
    assertNotNull(mapper);
  }
}
