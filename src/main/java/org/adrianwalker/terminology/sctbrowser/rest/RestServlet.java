package org.adrianwalker.terminology.sctbrowser.rest;

import static org.adrianwalker.terminology.sctbrowser.sql.SqlDialect.toEnum;

import org.adrianwalker.terminology.sctbrowser.dataaccess.DataAccessFactory;
import org.adrianwalker.terminology.sctbrowser.service.ThreadedService;
import org.adrianwalker.terminology.sctbrowser.sql.SqlDialect;

import org.glassfish.jersey.message.GZipEncoder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.EncodingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.sql.DataSource;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Context;

@WebServlet(loadOnStartup = 1)
@ApplicationPath("rest")
public class RestServlet extends ResourceConfig {

  private static final Logger LOGGER = LoggerFactory.getLogger(RestServlet.class);
  private static final String SQL_DIALECT = "sqlDialect";
  private static final String DATASOURCE_NAME = "java:/comp/env/jdbc/sctbrowser";

  public RestServlet(
          @Context
          final ServletContext servletContext) {

    super();
    init(servletContext);
  }

  private void init(final ServletContext servletContext) {

    LOGGER.debug("initialising REST servlet");

    register(RestContextResolver.class);
    registerInstances(
            new CachingRestService(
                    new ThreadedService(
                            new DataAccessFactory(sqlDialect(servletContext))
                                    .create(dataSource(DATASOURCE_NAME))
                    )));

    EncodingFilter.enableFor(this, GZipEncoder.class);
  }

  private SqlDialect sqlDialect(final ServletContext servletContext) {

    return toEnum(servletContext.getInitParameter(SQL_DIALECT));
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
