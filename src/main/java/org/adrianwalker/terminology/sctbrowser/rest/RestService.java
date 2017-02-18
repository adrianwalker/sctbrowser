package org.adrianwalker.terminology.sctbrowser.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.adrianwalker.terminology.sctbrowser.rest.RestConstants.BROWSE_PATH;
import static org.adrianwalker.terminology.sctbrowser.rest.RestConstants.DETAILS_PATH;
import static org.adrianwalker.terminology.sctbrowser.rest.RestConstants.MAPPINGS_PATH;
import static org.adrianwalker.terminology.sctbrowser.rest.RestConstants.MEMBEROF_PATH;
import static org.adrianwalker.terminology.sctbrowser.rest.RestConstants.MEMBERS_PATH;
import static org.adrianwalker.terminology.sctbrowser.rest.RestConstants.REFSETS_PATH;
import static org.adrianwalker.terminology.sctbrowser.rest.RestConstants.REFERENCES_PATH;
import static org.adrianwalker.terminology.sctbrowser.rest.RestConstants.SEARCH_PATH;
import static org.adrianwalker.terminology.sctbrowser.rest.RestConstants.SUBSETS_PATH;
import static org.adrianwalker.terminology.sctbrowser.rest.RestConstants.VERSION_PATH;

import org.adrianwalker.terminology.sctbrowser.parameters.BrowseParameters;
import org.adrianwalker.terminology.sctbrowser.parameters.MembersParameters;
import org.adrianwalker.terminology.sctbrowser.parameters.ReferencesParameters;
import org.adrianwalker.terminology.sctbrowser.parameters.SearchParameters;

import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path(VERSION_PATH)
public interface RestService {

  @GET
  @Path(value = BROWSE_PATH)
  @Produces(value = APPLICATION_JSON)
  Response browse(@BeanParam BrowseParameters parameters) throws Exception;

  @GET
  @Path(value = DETAILS_PATH)
  @Produces(value = APPLICATION_JSON)
  Response details(@BeanParam BrowseParameters parameters) throws Exception;

  @GET
  @Path(value = MAPPINGS_PATH)
  @Produces(value = APPLICATION_JSON)
  Response mappings(@BeanParam BrowseParameters parameters) throws Exception;

  @GET
  @Path(value = MEMBEROF_PATH)
  @Produces(value = APPLICATION_JSON)
  Response memberOf(@BeanParam MembersParameters parameters) throws Exception;

  @GET
  @Path(value = MEMBERS_PATH)
  @Produces(value = APPLICATION_JSON)
  Response members(@BeanParam MembersParameters parameters) throws Exception;

  @GET
  @Path(value = REFSETS_PATH)
  @Produces(value = APPLICATION_JSON)
  Response refsets(@BeanParam BrowseParameters parameters) throws Exception;

  @GET
  @Path(value = REFERENCES_PATH)
  @Produces(value = APPLICATION_JSON)
  Response references(@BeanParam ReferencesParameters parameters) throws Exception;

  @GET
  @Path(value = SUBSETS_PATH)
  @Produces(value = APPLICATION_JSON)
  Response subsets(@BeanParam BrowseParameters parameters) throws Exception;

  @GET
  @Path(value = SEARCH_PATH)
  @Produces(value = APPLICATION_JSON)
  Response search(@BeanParam SearchParameters parameters) throws Exception;
}
