package org.adrianwalker.terminology.sctbrowser.rest;

import org.adrianwalker.terminology.sctbrowser.parameters.BrowseParameters;
import org.adrianwalker.terminology.sctbrowser.parameters.MembersParameters;
import org.adrianwalker.terminology.sctbrowser.parameters.ReferencesParameters;
import org.adrianwalker.terminology.sctbrowser.parameters.SearchParameters;
import org.adrianwalker.terminology.sctbrowser.resource.ResourceFile;
import org.adrianwalker.terminology.sctbrowser.service.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.ws.rs.BeanParam;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Response;

public final class CachingRestService implements RestService {

  private static final Logger LOGGER = LoggerFactory.getLogger(CachingRestService.class);
  private static final String CACHE_CONFIGURATION = "/jcache.ccf";
  private static final CacheManager CACHE_MANAGER
          = Caching.getCachingProvider().getCacheManager(
                  ResourceFile.asURI(CACHE_CONFIGURATION),
                  ResourceFile.class.getClassLoader());
  private static final CacheControl CACHE_CONTROL = new CacheControl();
  private static final int CACHE_AGE = 60 * 60 * 24;

  private static final Cache<BrowseParameters, List> BROWSE_CACHE;
  private static final Cache<SearchParameters, Map> SEARCH_CACHE;
  private static final Cache<BrowseParameters, List> REFSETS_CACHE;
  private static final Cache<ReferencesParameters, Map> REFERENCES_CACHE;
  private static final Cache<BrowseParameters, List> SUBSETS_CACHE;
  private static final Cache<BrowseParameters, List> MAPPINGS_CACHE;
  private static final Cache<MembersParameters, Map> MEMBER_OF_CACHE;
  private static final Cache<MembersParameters, Map> MEMBERS_CACHE;
  private static final Cache<BrowseParameters, Map> DETAILS_CACHE;

  static {
    BROWSE_CACHE = createCache("BROWSE_CACHE", BrowseParameters.class, List.class);
    SEARCH_CACHE = createCache("SEARCH_CACHE", SearchParameters.class, Map.class);
    REFSETS_CACHE = createCache("REFSETS_CACHE", BrowseParameters.class, List.class);
    REFERENCES_CACHE = createCache("REFERENCES_CACHE", ReferencesParameters.class, Map.class);
    SUBSETS_CACHE = createCache("SUBSETS_CACHE", BrowseParameters.class, List.class);
    MAPPINGS_CACHE = createCache("MAPPINGS_CACHE", BrowseParameters.class, List.class);
    MEMBER_OF_CACHE = createCache("MEMBERS_OF_CACHE", MembersParameters.class, Map.class);
    MEMBERS_CACHE = createCache("MEMBERS_CACHE", MembersParameters.class, Map.class);
    DETAILS_CACHE = createCache("DETAILS_CACHE", BrowseParameters.class, Map.class);
    CACHE_CONTROL.setMaxAge(CACHE_AGE);
  }

  private final Service service;

  public CachingRestService(final Service service) {

    this.service = service;
  }

  @Override
  public Response browse(
          @BeanParam
          final BrowseParameters parameters) throws Exception {

    LOGGER.debug("{}", parameters);

    return Response
            .ok(doBrowse(parameters))
            .cacheControl(CACHE_CONTROL)
            .build();
  }

  @Override
  public Response refsets(
          @BeanParam
          final BrowseParameters parameters) throws Exception {

    LOGGER.debug("{}", parameters);

    return Response
            .ok(doRefsets(parameters))
            .cacheControl(CACHE_CONTROL)
            .build();
  }

  @Override
  public Response references(
          @BeanParam
          final ReferencesParameters parameters) throws Exception {

    LOGGER.debug("{}", parameters);

    return Response
            .ok(doReferences(parameters))
            .cacheControl(CACHE_CONTROL)
            .build();
  }

  @Override
  public Response subsets(
          @BeanParam
          final BrowseParameters parameters) throws Exception {

    LOGGER.debug("{}", parameters);

    return Response
            .ok(doSubsets(parameters))
            .cacheControl(CACHE_CONTROL)
            .build();
  }

  @Override
  public Response mappings(
          @BeanParam
          final BrowseParameters parameters) throws Exception {

    LOGGER.debug("{}", parameters);

    return Response
            .ok(doMappings(parameters))
            .cacheControl(CACHE_CONTROL)
            .build();
  }

  @Override
  public Response members(
          @BeanParam
          final MembersParameters parameters) throws Exception {

    LOGGER.debug("{}", parameters);

    return Response
            .ok(doMembers(parameters))
            .cacheControl(CACHE_CONTROL)
            .build();
  }

  @Override
  public Response memberOf(
          @BeanParam
          final MembersParameters parameters) throws Exception {

    LOGGER.debug("{}", parameters);

    return Response
            .ok(doMemberOf(parameters))
            .cacheControl(CACHE_CONTROL)
            .build();
  }

  @Override
  public Response search(
          @BeanParam
          final SearchParameters parameters) throws Exception {

    LOGGER.debug("{}", parameters);

    return Response
            .ok(doSearch(parameters))
            .cacheControl(CACHE_CONTROL)
            .build();
  }

  @Override
  public Response details(
          @BeanParam
          final BrowseParameters parameters) throws Exception {

    LOGGER.debug("{}", parameters);

    return Response
            .ok(doDetails(parameters))
            .cacheControl(CACHE_CONTROL)
            .build();
  }

  private static <K, V> Cache<K, V> createCache(
          final String cacheName,
          final Class<K> keyType,
          final Class<V> valueType) {

    MutableConfiguration<K, V> config = new MutableConfiguration<>();
    config.setTypes(keyType, valueType);

    return CACHE_MANAGER.createCache(cacheName, config);
  }

  private List<Map<String, Object>> doBrowse(
          final BrowseParameters parameters) throws Exception {

    List<Map<String, Object>> browse = BROWSE_CACHE.get(parameters);
    if (null != browse) {
      return browse;
    }

    browse = service.browse(parameters);

    BROWSE_CACHE.put(parameters, browse);

    return browse;
  }

  private List<Map<String, Object>> doMappings(final BrowseParameters parameters) throws Exception {

    List<Map<String, Object>> mappings = MAPPINGS_CACHE.get(parameters);
    if (null != mappings) {
      return mappings;
    }

    mappings = service.mappings(parameters);

    MAPPINGS_CACHE.put(parameters, mappings);

    return mappings;
  }

  private Map<String, Object> doSearch(final SearchParameters parameters) throws Exception {

    Map<String, Object> search = SEARCH_CACHE.get(parameters);
    if (null != search) {
      return search;
    }

    search = service.search(parameters);

    SEARCH_CACHE.put(parameters, search);

    return search;
  }

  private List<Map<String, Object>> doRefsets(final BrowseParameters parameters) throws Exception {

    List<Map<String, Object>> refsets = REFSETS_CACHE.get(parameters);
    if (null != refsets) {
      return refsets;
    }

    refsets = service.refsets(parameters);

    REFSETS_CACHE.put(parameters, refsets);

    return refsets;
  }

  private Map<String, Object> doReferences(final ReferencesParameters parameters) throws Exception {

    Map<String, Object> refsets = REFERENCES_CACHE.get(parameters);
    if (null != refsets) {
      return refsets;
    }

    refsets = service.references(parameters);

    REFERENCES_CACHE.put(parameters, refsets);

    return refsets;
  }

  private List<Map<String, Object>> doSubsets(final BrowseParameters parameters) throws Exception {

    List<Map<String, Object>> refsets = SUBSETS_CACHE.get(parameters);
    if (null != refsets) {
      return refsets;
    }

    refsets = service.subsets(parameters);

    SUBSETS_CACHE.put(parameters, refsets);

    return refsets;
  }

  private Map<String, Object> doMemberOf(final MembersParameters parameters) throws Exception {

    Map<String, Object> memberOf = MEMBER_OF_CACHE.get(parameters);
    if (null != memberOf) {
      return memberOf;
    }

    memberOf = service.memberOf(parameters);

    MEMBER_OF_CACHE.put(parameters, memberOf);

    return memberOf;
  }

  private Map<String, Object> doMembers(final MembersParameters parameters) throws Exception {

    Map<String, Object> members = MEMBERS_CACHE.get(parameters);
    if (null != members) {
      return members;
    }

    members = service.members(parameters);

    MEMBERS_CACHE.put(parameters, members);

    return members;
  }

  private Map<String, List<Map<String, Object>>> doDetails(
          final BrowseParameters parameters) throws Exception {

    Map<String, List<Map<String, Object>>> details = DETAILS_CACHE.get(parameters);
    if (null != details) {
      return details;
    }

    details = service.details(parameters);

    DETAILS_CACHE.put(parameters, details);

    return details;
  }
}
