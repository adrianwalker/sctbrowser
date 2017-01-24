package org.adrianwalker.terminology.sctbrowser.dataaccess;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.adrianwalker.terminology.sctbrowser.sql.SqlDialect;

import org.junit.Test;
import javax.sql.DataSource;

public final class DataAccessFactoryTest {

  @Test
  public void testCreatePostgreSQLDataAccess() {

    DataSource dataSource = mock(DataSource.class);
    DataAccessFactory factory = new DataAccessFactory(SqlDialect.POSTGRESQL);
    DataAccess loader = factory.create(dataSource);

    assertTrue(loader instanceof PostgresqlDataAccess);
  }
}
