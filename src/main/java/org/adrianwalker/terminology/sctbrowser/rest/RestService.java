package org.adrianwalker.terminology.sctbrowser.rest;

import org.adrianwalker.terminology.sctbrowser.parameters.BrowseParameters;
import org.adrianwalker.terminology.sctbrowser.parameters.MembersParameters;
import org.adrianwalker.terminology.sctbrowser.parameters.SearchParameters;

import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("v1.0.0")
public interface RestService {

  @GET
  @Path(value = "browse")
  @Produces(value = MediaType.APPLICATION_JSON)
  Response browse(@BeanParam BrowseParameters parameters) throws Exception;

  @GET
  @Path(value = "details")
  @Produces(value = MediaType.APPLICATION_JSON)
  Response details(@BeanParam BrowseParameters parameters) throws Exception;

  @GET
  @Path(value = "mappings")
  @Produces(value = MediaType.APPLICATION_JSON)
  Response mappings(@BeanParam BrowseParameters parameters) throws Exception;

  @GET
  @Path(value = "memberof")
  @Produces(value = MediaType.APPLICATION_JSON)
  Response memberOf(@BeanParam MembersParameters parameters) throws Exception;

  @GET
  @Path(value = "members")
  @Produces(value = MediaType.APPLICATION_JSON)
  Response members(@BeanParam MembersParameters parameters) throws Exception;

  @GET
  @Path(value = "refsets")
  @Produces(value = MediaType.APPLICATION_JSON)
  Response refsets(@BeanParam BrowseParameters parameters) throws Exception;

  @GET
  @Path(value = "subsets")
  @Produces(value = MediaType.APPLICATION_JSON)
  Response subsets(@BeanParam BrowseParameters parameters) throws Exception;

  @GET
  @Path(value = "search")
  @Produces(value = MediaType.APPLICATION_JSON)
  Response search(@BeanParam SearchParameters parameters) throws Exception;

}
