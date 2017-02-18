package org.adrianwalker.terminology.sctbrowser.service;

import static java.lang.Integer.MAX_VALUE;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.adrianwalker.terminology.sctbrowser.dataaccess.DataAccess;
import org.adrianwalker.terminology.sctbrowser.parameters.BrowseParameters;
import org.adrianwalker.terminology.sctbrowser.parameters.MembersParameters;
import org.adrianwalker.terminology.sctbrowser.parameters.SearchParameters;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.adrianwalker.terminology.sctbrowser.parameters.ReferencesParameters;

public final class ThreadedServiceTest {

  @Test
  public void testBrowseConceptWithChildren() throws Exception {

    BrowseParameters parameters = new BrowseParameters();
    parameters.setConcept(true);
    parameters.setChildren(true);

    List<Map<String, Object>> concepts = new ArrayList<>();
    Map<String, Object> concept = new HashMap<>();
    concept.put("key", "1");
    concept.put("title", "parent");
    concept.put("lazy", true);
    concept.put("folder", true);
    concepts.add(concept);

    List<Map<String, Object>> children = new ArrayList<>();
    Map<String, Object> child = new HashMap<>();
    child.put("key", "2");
    child.put("title", "child");
    child.put("lazy", false);
    child.put("folder", false);
    children.add(child);

    List<Map<String, Object>> expectedResults = new ArrayList<>();
    Map<String, Object> expectedResult = new HashMap<>(concept);
    expectedResult.put("lazy", false);
    expectedResult.put("children", children);
    expectedResults.add(expectedResult);

    DataAccess dataAccess = mock(DataAccess.class);
    when(dataAccess.concept(parameters)).thenReturn(concepts);
    when(dataAccess.children(parameters)).thenReturn(children);

    Service service = new ThreadedService(dataAccess);
    List<Map<String, Object>> results = service.browse(parameters);
    assertEquals(expectedResults, results);
  }

  @Test
  public void testBrowseConcept() throws Exception {

    BrowseParameters parameters = new BrowseParameters();
    parameters.setConcept(true);
    parameters.setChildren(false);

    List<Map<String, Object>> concepts = new ArrayList<>();
    Map<String, Object> concept = new HashMap<>();
    concept.put("key", "1");
    concept.put("title", "concept");
    concept.put("lazy", true);
    concept.put("folder", true);
    concepts.add(concept);

    List<Map<String, Object>> expectedResults = new ArrayList<>();
    Map<String, Object> expectedResult = new HashMap<>(concept);
    expectedResult.put("lazy", true);
    expectedResults.add(expectedResult);

    DataAccess dataAccess = mock(DataAccess.class);
    when(dataAccess.concept(parameters)).thenReturn(concepts);

    Service service = new ThreadedService(dataAccess);
    List<Map<String, Object>> results = service.browse(parameters);
    assertEquals(expectedResults, results);
  }

  @Test
  public void testBrowseChildren() throws Exception {

    BrowseParameters parameters = new BrowseParameters();
    parameters.setConcept(false);
    parameters.setChildren(true);

    List<Map<String, Object>> children = new ArrayList<>();

    Map<String, Object> child = new HashMap<>();
    child.put("key", "2");
    child.put("title", "child 1");
    child.put("lazy", false);
    child.put("folder", false);
    children.add(child);

    child = new HashMap<>();
    child.put("key", "3");
    child.put("title", "child 2");
    child.put("lazy", false);
    child.put("folder", false);
    children.add(child);

    List<Map<String, Object>> expectedResults = new ArrayList<>(children);

    DataAccess dataAccess = mock(DataAccess.class);
    when(dataAccess.children(parameters)).thenReturn(children);

    Service service = new ThreadedService(dataAccess);
    List<Map<String, Object>> results = service.browse(parameters);
    assertEquals(expectedResults, results);
  }

  @Test
  public void testRefsets() throws Exception {

    BrowseParameters parameters = new BrowseParameters();

    List<Map<String, Object>> refsets = new ArrayList<>();
    Map<String, Object> refset = new HashMap<>();
    refset.put("id", "1");
    refset.put("term", "test");
    refsets.add(refset);

    List<Map<String, Object>> expectedResults = new ArrayList<>(refsets);

    DataAccess dataAccess = mock(DataAccess.class);
    when(dataAccess.refsets(parameters)).thenReturn(refsets);

    Service service = new ThreadedService(dataAccess);
    List<Map<String, Object>> results = service.refsets(parameters);
    assertEquals(expectedResults, results);
  }

  @Test
  public void testReferences() throws Exception {

    /*
        MembersParameters parameters = new MembersParameters();
    parameters.setCount(true);
    parameters.setOffset(-1);
    parameters.setLimit(MAX_VALUE);

    List<Map<String, Object>> concepts = new ArrayList<>();
    Map<String, Object> concept = new HashMap<>();
    concept.put("id", "1");
    concept.put("term", "test");
    concepts.add(concept);

    Map<String, Object> count = new HashMap<>();
    count.put("count", "1");

    Map<String, Object> expectedResults = new HashMap<>();
    expectedResults.put("concepts", concepts);
    expectedResults.putAll(count);
    */
    
    ReferencesParameters parameters = new ReferencesParameters();

    List<Map<String, Object>> concepts = new ArrayList<>();
    Map<String, Object> concept = new HashMap<>();
    concept.put("relationship_term", "test");
    concept.put("concept_id", "1");
    concept.put("concept_term", "test");
    concepts.add(concept);    
    
    Map<String, Object> count = new HashMap<>();
    count.put("count", "1");

    Map<String, Object> expectedResults = new HashMap<>();
    expectedResults.put("concepts", concepts);
    expectedResults.putAll(count);
    
    DataAccess dataAccess = mock(DataAccess.class);
    when(dataAccess.references(parameters)).thenReturn(concepts);
    when(dataAccess.referencesCount(parameters)).thenReturn(count);

    Service service = new ThreadedService(dataAccess);
    Map<String, Object> results = service.references(parameters);
    assertEquals(expectedResults, results);
  }

  @Test
  public void testSubsets() throws Exception {

    BrowseParameters parameters = new BrowseParameters();

    List<Map<String, Object>> subsets = new ArrayList<>();
    Map<String, Object> subset = new HashMap<>();
    subset.put("subset_id", "1");
    subset.put("subset_term", "test");
    subset.put("refset_id", "1");
    subsets.add(subset);

    List<Map<String, Object>> expectedResults = new ArrayList<>(subsets);

    DataAccess dataAccess = mock(DataAccess.class);
    when(dataAccess.subsets(parameters)).thenReturn(subsets);

    Service service = new ThreadedService(dataAccess);
    List<Map<String, Object>> results = service.subsets(parameters);
    assertEquals(expectedResults, results);
  }

  @Test
  public void testMappings() throws Exception {

    BrowseParameters parameters = new BrowseParameters();

    List<Map<String, Object>> mappings = new ArrayList<>();
    Map<String, Object> mapping = new HashMap<>();
    mapping.put("refset_id", "1");
    mapping.put("term", "test");
    mapping.put("map_target", "test");
    mappings.add(mapping);

    List<Map<String, Object>> expectedResults = new ArrayList<>(mappings);

    DataAccess dataAccess = mock(DataAccess.class);
    when(dataAccess.mappings(parameters)).thenReturn(mappings);

    Service service = new ThreadedService(dataAccess);
    List<Map<String, Object>> results = service.mappings(parameters);
    assertEquals(expectedResults, results);
  }

  @Test
  public void testMembers() throws Exception {

    MembersParameters parameters = new MembersParameters();
    parameters.setCount(true);
    parameters.setOffset(-1);
    parameters.setLimit(MAX_VALUE);

    List<Map<String, Object>> concepts = new ArrayList<>();
    Map<String, Object> concept = new HashMap<>();
    concept.put("refset_id", "1");
    concept.put("term", "test");
    concept.put("map_target", "test");
    concepts.add(concept);

    Map<String, Object> count = new HashMap<>();
    count.put("count", "1");

    Map<String, Object> expectedResults = new HashMap<>();
    expectedResults.put("concepts", concepts);
    expectedResults.putAll(count);

    DataAccess dataAccess = mock(DataAccess.class);
    when(dataAccess.members(parameters)).thenReturn(concepts);
    when(dataAccess.membersCount(parameters)).thenReturn(count);

    Service service = new ThreadedService(dataAccess);
    Map<String, Object> results = service.members(parameters);
    assertEquals(expectedResults, results);
  }

  @Test
  public void testMemberOf() throws Exception {

    MembersParameters parameters = new MembersParameters();
    parameters.setCount(true);
    parameters.setOffset(-1);
    parameters.setLimit(MAX_VALUE);

    List<Map<String, Object>> concepts = new ArrayList<>();
    Map<String, Object> concept = new HashMap<>();
    concept.put("id", "1");
    concept.put("term", "test");
    concepts.add(concept);

    Map<String, Object> count = new HashMap<>();
    count.put("count", "1");

    Map<String, Object> expectedResults = new HashMap<>();
    expectedResults.put("concepts", concepts);
    expectedResults.putAll(count);

    DataAccess dataAccess = mock(DataAccess.class);
    when(dataAccess.memberOf(parameters)).thenReturn(concepts);
    when(dataAccess.memberOfCount(parameters)).thenReturn(count);

    Service service = new ThreadedService(dataAccess);
    Map<String, Object> results = service.memberOf(parameters);
    assertEquals(expectedResults, results);
  }

  @Test
  public void testSearch() throws Exception {

    SearchParameters parameters = new SearchParameters();
    parameters.setTerms("test");
    parameters.setCount(true);
    parameters.setOffset(-1);
    parameters.setLimit(MAX_VALUE);

    List<Map<String, Object>> concepts = new ArrayList<>();
    Map<String, Object> concept = new HashMap<>();
    concept.put("id", "1");
    concept.put("term", "test");
    concept.put("rank", "1.0");
    concepts.add(concept);

    Map<String, Object> count = new HashMap<>();
    count.put("count", "1");

    Map<String, Object> expectedResults = new HashMap<>();
    expectedResults.put("concepts", concepts);
    expectedResults.putAll(count);

    DataAccess dataAccess = mock(DataAccess.class);
    when(dataAccess.search(parameters)).thenReturn(concepts);
    when(dataAccess.searchCount(parameters)).thenReturn(count);

    Service service = new ThreadedService(dataAccess);
    Map<String, Object> results = service.search(parameters);
    assertEquals(expectedResults, results);
  }

  @Test
  public void testDetails() throws Exception {

    BrowseParameters parameters = new BrowseParameters();

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

    Map<String, Object> expectedResults = new HashMap<>();
    expectedResults.put("descriptions", descriptions);
    expectedResults.put("properties", properties);
    expectedResults.put("relationships", relationships);
    expectedResults.put("paths", paths);

    DataAccess dataAccess = mock(DataAccess.class);
    when(dataAccess.descriptions(parameters)).thenReturn(descriptions);
    when(dataAccess.properties(parameters)).thenReturn(properties);
    when(dataAccess.relationships(parameters)).thenReturn(relationships);
    when(dataAccess.paths(parameters)).thenReturn(paths);

    Service service = new ThreadedService(dataAccess);
    Map<String, List<Map<String, Object>>> results = service.details(parameters);
    assertEquals(expectedResults, results);
  }
}
