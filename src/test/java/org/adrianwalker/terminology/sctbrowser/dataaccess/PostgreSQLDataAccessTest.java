package org.adrianwalker.terminology.sctbrowser.dataaccess;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.adrianwalker.terminology.sctbrowser.parameters.BrowseParameters;
import org.adrianwalker.terminology.sctbrowser.parameters.MembersParameters;
import org.adrianwalker.terminology.sctbrowser.parameters.SearchParameters;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import javax.sql.DataSource;
import org.adrianwalker.terminology.sctbrowser.parameters.ReferencesParameters;
import org.junit.Test;

public final class PostgreSQLDataAccessTest {

  @Test
  public void testConcept() throws Exception {

    List<Map<String, Object>> expectedResults = createExpectedResults();
    DataAccess dataAccess = new PostgresqlDataAccess(mockDataSource(expectedResults));
    List<Map<String, Object>> results = dataAccess.concept(new BrowseParameters());
    assertEquals(expectedResults, results);
  }

  @Test
  public void testChildren() throws Exception {

    List<Map<String, Object>> expectedResults = createExpectedResults();
    DataAccess dataAccess = new PostgresqlDataAccess(mockDataSource(expectedResults));
    List<Map<String, Object>> results = dataAccess.children(new BrowseParameters());
    assertEquals(expectedResults, results);
  }

  @Test
  public void testPaths() throws Exception {

    List<Map<String, Object>> expectedResults = createExpectedResults();
    DataAccess dataAccess = new PostgresqlDataAccess(mockDataSource(expectedResults));
    List<Map<String, Object>> results = dataAccess.paths(new BrowseParameters());
    assertEquals(expectedResults, results);
  }

  @Test
  public void testSearch() throws Exception {

    List<Map<String, Object>> expectedResults = createExpectedResults();
    DataAccess dataAccess = new PostgresqlDataAccess(mockDataSource(expectedResults));

    SearchParameters parameters = new SearchParameters();
    parameters.setTerms("test");
    parameters.setCount(true);

    List<Map<String, Object>> results = dataAccess.search(parameters);
    assertEquals(expectedResults, results);
  }

  @Test
  public void testSearchCount() throws Exception {

    List<Map<String, Object>> expectedResults = createExpectedResults();
    DataAccess dataAccess = new PostgresqlDataAccess(mockDataSource(expectedResults));

    SearchParameters parameters = new SearchParameters();
    parameters.setTerms("test");
    parameters.setCount(true);

    Map<String, Object> result = dataAccess.searchCount(parameters);
    assertEquals(expectedResults.get(0), result);
  }

  @Test
  public void testRefsets() throws Exception {

    List<Map<String, Object>> expectedResults = createExpectedResults();
    DataAccess dataAccess = new PostgresqlDataAccess(mockDataSource(expectedResults));
    List<Map<String, Object>> results = dataAccess.refsets(new BrowseParameters());
    assertEquals(expectedResults, results);
  }

  @Test
  public void testReferences() throws Exception {

    List<Map<String, Object>> expectedResults = createExpectedResults();
    DataAccess dataAccess = new PostgresqlDataAccess(mockDataSource(expectedResults));
    List<Map<String, Object>> results = dataAccess.references(new ReferencesParameters());
    assertEquals(expectedResults, results);
  }

  @Test
  public void tesSubsets() throws Exception {

    List<Map<String, Object>> expectedResults = createExpectedResults();
    DataAccess dataAccess = new PostgresqlDataAccess(mockDataSource(expectedResults));
    List<Map<String, Object>> results = dataAccess.subsets(new BrowseParameters());
    assertEquals(expectedResults, results);
  }

  @Test
  public void testMappings() throws Exception {

    List<Map<String, Object>> expectedResults = createExpectedResults();
    DataAccess dataAccess = new PostgresqlDataAccess(mockDataSource(expectedResults));
    List<Map<String, Object>> results = dataAccess.mappings(new BrowseParameters());
    assertEquals(expectedResults, results);
  }

  @Test
  public void testMemberOf() throws Exception {

    List<Map<String, Object>> expectedResults = createExpectedResults();
    DataAccess dataAccess = new PostgresqlDataAccess(mockDataSource(expectedResults));
    List<Map<String, Object>> results = dataAccess.memberOf(new MembersParameters());
    assertEquals(expectedResults, results);
  }

  @Test
  public void testMemberOfCount() throws Exception {

    List<Map<String, Object>> expectedResults = createExpectedResults();
    DataAccess dataAccess = new PostgresqlDataAccess(mockDataSource(expectedResults));
    Map<String, Object> results = dataAccess.memberOfCount(new MembersParameters());
    assertEquals(expectedResults.get(0), results);
  }

  @Test
  public void testMembers() throws Exception {

    List<Map<String, Object>> expectedResults = createExpectedResults();
    DataAccess dataAccess = new PostgresqlDataAccess(mockDataSource(expectedResults));
    List<Map<String, Object>> results = dataAccess.members(new MembersParameters());
    assertEquals(expectedResults, results);
  }

  @Test
  public void testMembersCount() throws Exception {

    List<Map<String, Object>> expectedResults = createExpectedResults();
    DataAccess dataAccess = new PostgresqlDataAccess(mockDataSource(expectedResults));
    Map<String, Object> result = dataAccess.membersCount(new MembersParameters());
    assertEquals(expectedResults.get(0), result);
  }

  @Test
  public void testDescriptions() throws Exception {

    List<Map<String, Object>> expectedResults = createExpectedResults();
    DataAccess dataAccess = new PostgresqlDataAccess(mockDataSource(expectedResults));
    List<Map<String, Object>> results = dataAccess.descriptions(new BrowseParameters());
    assertEquals(expectedResults, results);
  }

  @Test
  public void testRelationships() throws Exception {

    List<Map<String, Object>> expectedResults = createExpectedResults();
    DataAccess dataAccess = new PostgresqlDataAccess(mockDataSource(expectedResults));
    List<Map<String, Object>> results = dataAccess.relationships(new BrowseParameters());
    assertEquals(expectedResults, results);
  }

  @Test
  public void testProperties() throws Exception {

    List<Map<String, Object>> expectedResults = createExpectedResults();
    DataAccess dataAccess = new PostgresqlDataAccess(mockDataSource(expectedResults));
    List<Map<String, Object>> results = dataAccess.properties(new BrowseParameters());
    assertEquals(expectedResults, results);
  }

  private List<Map<String, Object>> createExpectedResults() {

    List<Map<String, Object>> expectedResults = new ArrayList<>();
    Map<String, Object> expectedResult = new HashMap<>();
    expectedResult.put("testKey", "testValue");
    expectedResults.add(expectedResult);

    return expectedResults;
  }

  private DataSource mockDataSource(final List<Map<String, Object>> expectedResults) throws SQLException {

    DataSource dataSource = mock(DataSource.class);
    Connection connection = mock(Connection.class);
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    ResultSet resultSet = mock(ResultSet.class);
    ResultSetMetaData metaData = mock(ResultSetMetaData.class);

    when(dataSource.getConnection()).thenReturn(connection);
    when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
    when(preparedStatement.executeQuery()).thenReturn(resultSet);
    when(resultSet.getMetaData()).thenReturn(metaData);

    AtomicInteger rowIndex = new AtomicInteger(0);

    when(metaData.getColumnCount()).thenAnswer(invocation -> {
      return expectedResults.get(rowIndex.get()).keySet().size();
    });

    when(resultSet.next()).thenAnswer(invocation -> {
      return rowIndex.getAndIncrement() < expectedResults.size();
    });

    when(resultSet.getObject(anyInt())).thenAnswer(invocation -> {

      int columnIndex = invocation.getArgument(0);
      return new ArrayList<>(expectedResults.get(rowIndex.get() - 1).values()).get(columnIndex - 1);
    });

    when(metaData.getColumnLabel(anyInt())).thenAnswer(invocation -> {

      int columnIndex = invocation.getArgument(0);
      return new ArrayList<>(expectedResults.get(rowIndex.get() - 1).keySet()).get(columnIndex - 1);
    });

    return dataSource;
  }
}
