package org.adrianwalker.terminology.sctbrowser.loader;

import static org.adrianwalker.terminology.sctbrowser.sql.SqlDialect.POSTGRESQL;

import org.adrianwalker.terminology.sctbrowser.sql.SqlDialect;

import javax.sql.DataSource;

public final class LoaderFactory {

  private final SqlDialect sqlDialect;

  public LoaderFactory(final SqlDialect sqlDialect) {
    this.sqlDialect = sqlDialect;
  }

  public Loader create(final DataSource dataSource) {

    Loader loader;

    switch (sqlDialect) {

      case POSTGRESQL:
        loader = new PostgresqlLoader(dataSource);
        break;

      default:
        loader = null;
    }

    return loader;
  }
}
