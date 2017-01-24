package org.adrianwalker.terminology.sctbrowser.loader;

import static org.adrianwalker.terminology.sctbrowser.sql.SqlDialect.toEnum;

import org.adrianwalker.terminology.sctbrowser.sql.SqlDialect;
import org.adrianwalker.terminology.sctbrowser.watch.CommandFileObserver;
import org.adrianwalker.terminology.sctbrowser.watch.DataDirectoryWatcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Paths;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;

@WebServlet(loadOnStartup = 1, name = "Loader", urlPatterns = {"/loader"})
public class LoaderServlet extends HttpServlet {

  private static final Logger LOGGER = LoggerFactory.getLogger(LoaderServlet.class);
  private static final String DATA_DIRECTORY = "dataDirectory";
  private static final String SQL_DIALECT = "sqlDialect";
  private static final String DATASOURCE_NAME = "java:/comp/env/jdbc/sctbrowser";

  @Override
  public void init(final ServletConfig config) throws ServletException {

    super.init(config);

    LOGGER.debug("initialising Loader servlet");

    ServletContext servletContext = config.getServletContext();
    Loader loader = new LoaderFactory(sqlDialect(servletContext))
            .create(dataSource(DATASOURCE_NAME));
    DataDirectoryWatcher watcher = watcher(dataDirectory(servletContext), loader);

    new Thread(watcher).start();
  }

  private DataDirectoryWatcher watcher(final String dataDirectory, final Loader loader) {

    DataDirectoryWatcher watcher;

    try {
      watcher = new DataDirectoryWatcher(Paths.get(dataDirectory));
    } catch (final Exception e) {
      LOGGER.error(e.getMessage(), e);
      throw new RuntimeException(e);
    }

    watcher.addObserver(new CommandFileObserver(loader));
    return watcher;
  }

  private SqlDialect sqlDialect(final ServletContext servletContext) {

    return toEnum(servletContext.getInitParameter(SQL_DIALECT));
  }

  private String dataDirectory(final ServletContext servletContext) {

    return servletContext.getInitParameter(DATA_DIRECTORY);
  }

  private DataSource dataSource(final String name) {

    try {

      InitialContext ctx = new InitialContext();
      return (DataSource) ctx.lookup(name);

    } catch (final NamingException ne) {

      LOGGER.error(ne.getMessage(), ne);
      throw new RuntimeException(ne);
    }
  }
}
