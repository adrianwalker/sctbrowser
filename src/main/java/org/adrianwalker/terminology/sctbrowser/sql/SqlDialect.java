package org.adrianwalker.terminology.sctbrowser.sql;

import java.util.HashMap;
import java.util.Map;

public enum SqlDialect {

  POSTGRESQL("postgresql");

  private static final Map<String, SqlDialect> SQL_DIALECT = new HashMap<>();
  private final String sqlDialect;

  static {
    SQL_DIALECT.put(POSTGRESQL.toString(), POSTGRESQL);
  }

  SqlDialect(final String sqlDialect) {

    this.sqlDialect = sqlDialect;
  }

  @Override
  public String toString() {
    return sqlDialect;
  }

  public static SqlDialect toEnum(final String value) {

    return SQL_DIALECT.get(value);
  }
}
