package org.adrianwalker.terminology.sctbrowser.loader;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

public final class PostgreSQLLoaderTest {

  @Test
  public void testCreateTables() throws Exception {

    PostgresqlLoader loader = new PostgresqlLoader(mockDataSource());
    loader.createTables();
  }

  @Test
  public void testDropTables() throws Exception {

    PostgresqlLoader loader = new PostgresqlLoader(mockDataSource());
    loader.dropTables();
  }

  @Test
  public void testLoadConcepts() throws Exception {

    Path path = Paths.get("src/test/resources/data/test_Concept_test.txt");

    PostgresqlLoader loader = new PostgresqlLoader(mockDataSource());
    loader.loadConcepts(path);
  }

  @Test
  public void testLoadDescriptions() throws Exception {

    Path path = Paths.get("src/test/resources/data/test_Description_test.txt");

    PostgresqlLoader loader = new PostgresqlLoader(mockDataSource());
    loader.loadDescriptions(path);
  }

  @Test
  public void testLoadRelationships() throws Exception {

    Path path = Paths.get("src/test/resources/data/test_Relationship_test.txt");

    PostgresqlLoader loader = new PostgresqlLoader(mockDataSource());
    loader.loadRelationships(path);
  }

  @Test
  public void testLoadLanguageRefset() throws Exception {

    Path path = Paths.get("src/test/resources/data/test_cRefset_testLanguage_test.txt");

    PostgresqlLoader loader = new PostgresqlLoader(mockDataSource());
    loader.loadLanguageRefset(path);
  }

  @Test
  public void testLoadContentRefset() throws Exception {

    Path path = Paths.get("src/test/resources/data/test_Refset_testSimple_test.txt");

    PostgresqlLoader loader = new PostgresqlLoader(mockDataSource());
    loader.loadContentRefset(path);
  }

  @Test(expected = Exception.class)
  public void testLoadContentRefsetInvalid() throws Exception {

    Path path = Paths.get("src/test/resources/data/test_Refset_testSimple_invalid_test.txt");

    PostgresqlLoader loader = new PostgresqlLoader(mockDataSource());
    loader.loadContentRefset(path);
  }

  @Test
  public void testLoadContentComponentRefset() throws Exception {

    Path path = Paths.get("src/test/resources/data/test_cRefset_testSimple_test.txt");

    PostgresqlLoader loader = new PostgresqlLoader(mockDataSource());
    loader.loadContentRefset(path);
  }

  @Test(expected = Exception.class)
  public void testLoadContentComponentRefsetInvalid() throws Exception {

    Path path = Paths.get("src/test/resources/data/test_cRefset_testSimple_invalid_test.txt");

    PostgresqlLoader loader = new PostgresqlLoader(mockDataSource());
    loader.loadContentRefset(path);
  }

  @Test
  public void testLoadSimpleMapRefset() throws Exception {
    
    Path path = Paths.get("src/test/resources/data/test_Refset_SimpleMap_test.txt");

    PostgresqlLoader loader = new PostgresqlLoader(mockDataSource());
    loader.loadMapRefset(path);
  }

  @Test(expected = Exception.class)
  public void testLoadSimpleMapRefsetInvalid() throws Exception {
    
    Path path = Paths.get("src/test/resources/data/test_Refset_SimpleMap_invalid_test.txt");

    PostgresqlLoader loader = new PostgresqlLoader(mockDataSource());
    loader.loadMapRefset(path);
  }

  @Test
  public void testLoadExtendedMapRefset() throws Exception {
    
    Path path = Paths.get("src/test/resources/data/test_Refset_ExtendedMap_test.txt");

    PostgresqlLoader loader = new PostgresqlLoader(mockDataSource());
    loader.loadMapRefset(path);
  }

  @Test(expected = Exception.class)
  public void testLoadExtendedMapRefsetInvalid() throws Exception {
    
    Path path = Paths.get("src/test/resources/data/test_Refset_ExtendedMap_invalid_test.txt");

    PostgresqlLoader loader = new PostgresqlLoader(mockDataSource());
    loader.loadMapRefset(path);
  }

  @Test
  public void testLoadSubsetToRefsetMapping() throws Exception {
    
    Path path = Paths.get("src/test/resources/data/test_RF1SubsetToRF2ReferenceSetMapping_test.txt");

    PostgresqlLoader loader = new PostgresqlLoader(mockDataSource());
    loader.loadSubsetToRefsetMapping(path);
  }

  @Test
  public void testCreateIndexes() throws Exception {
    
    PostgresqlLoader loader = new PostgresqlLoader(mockDataSource());
    loader.createIndexes();
  }

  private DataSource mockDataSource() throws SQLException {

    DataSource dataSource = mock(DataSource.class);
    Connection connection = mock(Connection.class);
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    ResultSet resultSet = mock(ResultSet.class);

    when(dataSource.getConnection()).thenReturn(connection);
    when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
    when(preparedStatement.executeQuery()).thenReturn(resultSet);

    return dataSource;
  }
}
