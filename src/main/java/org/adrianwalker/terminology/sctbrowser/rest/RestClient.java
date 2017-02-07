package org.adrianwalker.terminology.sctbrowser.rest;

import static org.adrianwalker.terminology.sctbrowser.rest.RestConstants.APPLICATION_PATH;
import static org.adrianwalker.terminology.sctbrowser.rest.RestConstants.BROWSE_PATH;
import static org.adrianwalker.terminology.sctbrowser.rest.RestConstants.CONCEPT_ID_PARAM;
import static org.adrianwalker.terminology.sctbrowser.rest.RestConstants.CONCEPT_PARAM;
import static org.adrianwalker.terminology.sctbrowser.rest.RestConstants.DETAILS_PATH;
import static org.adrianwalker.terminology.sctbrowser.rest.RestConstants.VERSION_PATH;

import org.adrianwalker.terminology.sctbrowser.parameters.BrowseParameters;

import java.io.Closeable;
import java.util.List;
import java.util.Map;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public final class RestClient implements Closeable {

  private final Client client;
  private final WebTarget target;

  public RestClient(final String baseUrl) {

    client = ClientBuilder.newClient();
    target = client.target(baseUrl)
            .path(APPLICATION_PATH)
            .path(VERSION_PATH);
  }

  public List<Map<String, Object>> browse(final BrowseParameters parameters) {

    return target.path(BROWSE_PATH)
            .queryParam(CONCEPT_ID_PARAM, parameters.getConceptId())
            .queryParam(CONCEPT_PARAM, parameters.getConcept())
            .request()
            .get(List.class);
  }

  public Map<String, List<Map<String, Object>>> details(final BrowseParameters parameters) {

    return target
            .path(DETAILS_PATH)
            .queryParam(CONCEPT_ID_PARAM, parameters.getConceptId())
            .queryParam(CONCEPT_PARAM, parameters.getConceptId())
            .request()
            .get(Map.class);
  }

  @Override
  public void close() {

    client.close();
  }
}
