package org.adrianwalker.terminology.sctbrowser.rest;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.adrianwalker.terminology.sctbrowser.parameters.BrowseParameters;
import org.adrianwalker.terminology.sctbrowser.parameters.MembersParameters;
import org.adrianwalker.terminology.sctbrowser.parameters.SearchParameters;
import org.adrianwalker.terminology.sctbrowser.service.Service;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.Response;

public final class CachingRestServiceTest {

  @Test
  public void testBrowse() throws Exception {

    List<Map<String, Object>> concepts = new ArrayList<>();
    Map<String, Object> concept = new HashMap<>();
    concept.put("key", "1");
    concept.put("title", "concept");
    concept.put("lazy", true);
    concept.put("folder", true);
    concepts.add(concept);

    List<Map<String, Object>> expectedEntity = new ArrayList<>();
    Map<String, Object> expectedResult = new HashMap<>(concept);
    expectedResult.put("lazy", true);
    expectedEntity.add(expectedResult);

    Service service = mock(Service.class);
    when(service.browse(any(BrowseParameters.class))).thenReturn(expectedEntity);

    RestService restService = new CachingRestService(service);

    BrowseParameters parameters = new BrowseParameters();

    Response response = restService.browse(parameters);
    List<Map<String, Object>> entity
            = (List<Map<String, Object>>) response.getEntity();
    assertEquals(expectedEntity, entity);

    Response cachedResponse = restService.browse(parameters);
    List<Map<String, Object>> cachedEntity
            = (List<Map<String, Object>>) cachedResponse.getEntity();
    assertEquals(expectedEntity, cachedEntity);

    assertEquals(entity, cachedEntity);
  }

  @Test
  public void testRefsets() throws Exception {

    List<Map<String, Object>> refsets = new ArrayList<>();
    Map<String, Object> refset = new HashMap<>();
    refset.put("id", "1");
    refset.put("term", "test");
    refsets.add(refset);

    List<Map<String, Object>> expectedEntity = new ArrayList<>(refsets);

    Service service = mock(Service.class);
    when(service.refsets(any(BrowseParameters.class))).thenReturn(expectedEntity);

    RestService restService = new CachingRestService(service);

    BrowseParameters parameters = new BrowseParameters();

    Response response = restService.refsets(parameters);
    List<Map<String, Object>> entity
            = (List<Map<String, Object>>) response.getEntity();
    assertEquals(expectedEntity, entity);

    Response cachedResponse = restService.refsets(parameters);
    List<Map<String, Object>> cachedEntity
            = (List<Map<String, Object>>) cachedResponse.getEntity();
    assertEquals(expectedEntity, cachedEntity);

    assertEquals(entity, cachedEntity);
  }

  @Test
  public void testSubsets() throws Exception {

    List<Map<String, Object>> subsets = new ArrayList<>();
    Map<String, Object> subset = new HashMap<>();
    subset.put("subset_id", "1");
    subset.put("subset_term", "test");
    subset.put("refset_id", "1");
    subsets.add(subset);

    List<Map<String, Object>> expectedEntity = new ArrayList<>(subsets);

    Service service = mock(Service.class);
    when(service.subsets(any(BrowseParameters.class))).thenReturn(expectedEntity);

    RestService restService = new CachingRestService(service);

    BrowseParameters parameters = new BrowseParameters();

    Response response = restService.subsets(parameters);
    List<Map<String, Object>> entity
            = (List<Map<String, Object>>) response.getEntity();
    assertEquals(expectedEntity, entity);

    Response cachedResponse = restService.subsets(parameters);
    List<Map<String, Object>> cachedEntity
            = (List<Map<String, Object>>) cachedResponse.getEntity();
    assertEquals(expectedEntity, cachedEntity);

    assertEquals(entity, cachedEntity);
  }

  @Test
  public void testMappings() throws Exception {

    List<Map<String, Object>> mappings = new ArrayList<>();
    Map<String, Object> mapping = new HashMap<>();
    mapping.put("refset_id", "1");
    mapping.put("term", "test");
    mapping.put("map_target", "test");
    mappings.add(mapping);

    List<Map<String, Object>> expectedEntity = new ArrayList<>(mappings);

    Service service = mock(Service.class);
    when(service.mappings(any(BrowseParameters.class))).thenReturn(expectedEntity);

    RestService restService = new CachingRestService(service);

    BrowseParameters parameters = new BrowseParameters();

    Response response = restService.mappings(parameters);
    List<Map<String, Object>> entity
            = (List<Map<String, Object>>) response.getEntity();
    assertEquals(expectedEntity, entity);

    Response cachedResponse = restService.mappings(parameters);
    List<Map<String, Object>> cachedEntity
            = (List<Map<String, Object>>) cachedResponse.getEntity();
    assertEquals(expectedEntity, cachedEntity);

    assertEquals(entity, cachedEntity);
  }

  @Test
  public void testMembers() throws Exception {

    List<Map<String, Object>> concepts = new ArrayList<>();
    Map<String, Object> concept = new HashMap<>();
    concept.put("refset_id", "1");
    concept.put("term", "test");
    concept.put("map_target", "test");
    concepts.add(concept);

    Map<String, Object> count = new HashMap<>();
    count.put("count", "1");

    Map<String, Object> expectedEntity = new HashMap<>();
    expectedEntity.put("concepts", concepts);
    expectedEntity.putAll(count);

    Service service = mock(Service.class);
    when(service.members(any(MembersParameters.class))).thenReturn(expectedEntity);

    RestService restService = new CachingRestService(service);

    MembersParameters parameters = new MembersParameters();

    Response response = restService.members(parameters);
    Map<String, Object> entity
            = (Map<String, Object>) response.getEntity();
    assertEquals(expectedEntity, entity);

    Response cachedResponse = restService.members(parameters);
    Map<String, Object> cachedEntity
            = (Map<String, Object>) cachedResponse.getEntity();
    assertEquals(expectedEntity, cachedEntity);

    assertEquals(entity, cachedEntity);
  }

  @Test
  public void testMemberOf() throws Exception {

    List<Map<String, Object>> concepts = new ArrayList<>();
    Map<String, Object> concept = new HashMap<>();
    concept.put("id", "1");
    concept.put("term", "test");
    concepts.add(concept);

    Map<String, Object> count = new HashMap<>();
    count.put("count", "1");

    Map<String, Object> expectedEntity = new HashMap<>();
    expectedEntity.put("concepts", concepts);
    expectedEntity.putAll(count);

    Service service = mock(Service.class);
    when(service.memberOf(any(MembersParameters.class))).thenReturn(expectedEntity);

    RestService restService = new CachingRestService(service);

    MembersParameters parameters = new MembersParameters();

    Response response = restService.memberOf(parameters);
    Map<String, Object> entity
            = (Map<String, Object>) response.getEntity();
    assertEquals(expectedEntity, entity);

    Response cachedResponse = restService.memberOf(parameters);
    Map<String, Object> cachedEntity
            = (Map<String, Object>) cachedResponse.getEntity();
    assertEquals(expectedEntity, cachedEntity);

    assertEquals(entity, cachedEntity);
  }

  @Test
  public void testSearch() throws Exception {

    List<Map<String, Object>> concepts = new ArrayList<>();
    Map<String, Object> concept = new HashMap<>();
    concept.put("id", "1");
    concept.put("term", "test");
    concept.put("rank", "1.0");
    concepts.add(concept);

    Map<String, Object> count = new HashMap<>();
    count.put("count", "1");

    Map<String, Object> expectedEntity = new HashMap<>();
    expectedEntity.put("concepts", concepts);
    expectedEntity.putAll(count);

    Service service = mock(Service.class);
    when(service.search(any(SearchParameters.class))).thenReturn(expectedEntity);

    RestService restService = new CachingRestService(service);

    SearchParameters parameters = new SearchParameters();

    Response response = restService.search(parameters);
    Map<String, Object> entity
            = (Map<String, Object>) response.getEntity();
    assertEquals(expectedEntity, entity);

    Response cachedResponse = restService.search(parameters);
    Map<String, Object> cachedEntity
            = (Map<String, Object>) cachedResponse.getEntity();
    assertEquals(expectedEntity, cachedEntity);

    assertEquals(entity, cachedEntity);
  }

  @Test
  public void testDetails() throws Exception {

    List<Map<String, Object>> descriptions = new ArrayList<>();
    Map<String, Object> description = new HashMap<>();
    description.put("type_term", "test");
    description.put("acceptability_term", "test");
    description.put("concept_term", "test");
    descriptions.add(description);

    List<Map<String, Object>> properties = new ArrayList<>();
    Map<String, Object> property = new HashMap<>();
    property.put("type_term", "test");
    property.put("acceptability_term", "test");
    property.put("concept_term", "test");
    properties.add(description);

    List<Map<String, Object>> relationships = new ArrayList<>();
    Map<String, Object> relationship = new HashMap<>();
    relationship.put("type_term", "test");
    relationship.put("acceptability_term", "test");
    relationship.put("concept_term", "test");
    relationships.add(description);

    List<Map<String, Object>> paths = new ArrayList<>();
    Map<String, Object> path = new HashMap<>();
    path.put("id", "2");
    path.put("parent_id", "1");
    paths.add(path);

    Map<String, List<Map<String, Object>>> expectedEntity = new HashMap<>();
    expectedEntity.put("descriptions", descriptions);
    expectedEntity.put("properties", properties);
    expectedEntity.put("relationships", relationships);
    expectedEntity.put("paths", paths);

    Service service = mock(Service.class);
    when(service.details(any(BrowseParameters.class))).thenReturn(expectedEntity);

    RestService restService = new CachingRestService(service);

    BrowseParameters parameters = new BrowseParameters();

    Response response = restService.details(parameters);
    Map<String, List<Map<String, Object>>> entity
            = (Map<String, List<Map<String, Object>>>) response.getEntity();
    assertEquals(expectedEntity, entity);

    Response cachedResponse = restService.details(parameters);
    Map<String, List<Map<String, Object>>> cachedEntity
            = (Map<String, List<Map<String, Object>>>) cachedResponse.getEntity();
    assertEquals(expectedEntity, cachedEntity);

    assertEquals(entity, cachedEntity);
  }
}
