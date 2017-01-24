package org.adrianwalker.terminology.sctbrowser.dataaccess;

import static org.adrianwalker.terminology.sctbrowser.sql.SqlDialect.POSTGRESQL;

import org.adrianwalker.terminology.sctbrowser.sql.SqlDialect;

import javax.sql.DataSource;

public final class DataAccessFactory {

  private final SqlDialect sqlDialect;

  public DataAccessFactory(final SqlDialect sqlDialect) {
    this.sqlDialect = sqlDialect;
  }

  public DataAccess create(final DataSource dataSource) {

    DataAccess dataAccess;

    switch (sqlDialect) {

      case POSTGRESQL:
        dataAccess = new PostgresqlDataAccess(dataSource);
        break;

      default:
        dataAccess = null;
    }

    return dataAccess;
  }
}
