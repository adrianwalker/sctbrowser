package org.adrianwalker.terminology.sctbrowser.loader;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.adrianwalker.terminology.sctbrowser.sql.SqlDialect;

import javax.sql.DataSource;
import org.junit.Test;

public final class LoaderFactoryTest {

  @Test
  public void testCreatePostgreSQLLoader() {

    DataSource dataSource = mock(DataSource.class);
    LoaderFactory factory = new LoaderFactory(SqlDialect.POSTGRESQL);
    Loader loader = factory.create(dataSource);
    
    assertTrue(loader instanceof PostgresqlLoader);
  }
}
