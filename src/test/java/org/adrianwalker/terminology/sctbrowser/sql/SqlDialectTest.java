package org.adrianwalker.terminology.sctbrowser.sql;

import static org.adrianwalker.terminology.sctbrowser.sql.SqlDialect.POSTGRESQL;
import static org.junit.Assert.*;

import org.junit.Test;

public final class SqlDialectTest {

  @Test
  public void testValues() {

    SqlDialect[] expectedDialects = new SqlDialect[] {POSTGRESQL};
    SqlDialect[] dialects = SqlDialect.values();

    assertArrayEquals(expectedDialects, dialects);
  }

  @Test
  public void testValueOf() {

    SqlDialect expectedDialect = POSTGRESQL;
    SqlDialect dialect = SqlDialect.valueOf("POSTGRESQL");
    
    assertEquals(expectedDialect, dialect);
  }

  @Test
  public void testToString() {

    String expectedString = "postgresql";
    String string = POSTGRESQL.toString();
    
    assertEquals(expectedString, string);
  }

  @Test
  public void testToEnum() {

    SqlDialect expectedDialect = null;
    SqlDialect dialect = SqlDialect.toEnum("postgres");
    
    assertEquals(expectedDialect, dialect);
  }
}
