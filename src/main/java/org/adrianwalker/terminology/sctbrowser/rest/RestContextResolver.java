package org.adrianwalker.terminology.sctbrowser.rest;

import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

@Provider
public class RestContextResolver implements ContextResolver<ObjectMapper> {

  @Override
  public ObjectMapper getContext(final Class<?> type) {

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(Feature.WRITE_NUMBERS_AS_STRINGS, true);

    return objectMapper;
  }
}
