package org.adrianwalker.terminology.sctbrowser.filter;

import static java.lang.Long.valueOf;
import static java.lang.String.format;

import org.adrianwalker.terminology.sctbrowser.parameters.BrowseParameters;
import org.adrianwalker.terminology.sctbrowser.rest.RestClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(filterName = "crawler")
public final class CrawlerFilter implements Filter {

  private static final Logger LOGGER = LoggerFactory.getLogger(CrawlerFilter.class);
  private static final String USER_AGENT_HEADER = "User-Agent";
  private static final String[] CRAWLER_USER_AGENTS = {
    "bot",
    "slurp"
  };
  private static final String DETAILS_ATTR = "details";
  private static final String BROWSE_ATTR = "browse";
  private static final String BASE_URL_ATTR = "baseUrl";
  private static final String ID_PARAMETER = "id";
  private static final String VELOCITY_TEMPLATE = "index.vm";
  private static final String BASE_URL_TEMPLATE = "http://%s%s%s%s";
  private static final int DEFAULT_HTTP_PORT = 80;
  private static final String COLON = ":";
  private static final String EMPTY_STRING = "";

  @Override
  public void init(final FilterConfig filterConfig) {
  }

  @Override
  public void doFilter(
          final ServletRequest request,
          final ServletResponse response,
          final FilterChain chain) throws IOException, ServletException {

    HttpServletRequest httpRequest = (HttpServletRequest) request;
    String userAgent = httpRequest.getHeader(USER_AGENT_HEADER);

    boolean crawler = crawler(userAgent);
    if (crawler) {
      doCrawler(request, response);
    } else {
      doBrowser(chain, request, response);
    }
  }

  @Override
  public void destroy() {
  }

  private boolean crawler(final String userAgent) {

    boolean crawler = false;

    for (String crawlerUserAgent : CRAWLER_USER_AGENTS) {

      if (userAgent.contains(crawlerUserAgent)) {
        crawler = true;
        break;
      }
    }

    return crawler;
  }

  private void doBrowser(
          final FilterChain chain,
          final ServletRequest request,
          final ServletResponse response) throws IOException, ServletException {

    chain.doFilter(request, response);
  }

  private void doCrawler(final ServletRequest request, final ServletResponse response) {

    String id = request.getParameter(ID_PARAMETER);

    BrowseParameters parameters = new BrowseParameters();
    parameters.setConceptId(id == null ? null : valueOf(id));
    parameters.setConcept(id == null);

    String baseUrl = getBaseUrl(request);
    try (RestClient client = new RestClient(baseUrl)) {

      request.setAttribute(BASE_URL_ATTR, baseUrl);
      request.setAttribute(BROWSE_ATTR, client.browse(parameters));
      request.setAttribute(DETAILS_ATTR, client.details(parameters));
    }

    RequestDispatcher requestDispatcher = request.getRequestDispatcher(VELOCITY_TEMPLATE);

    try {
      requestDispatcher.include(request, response);
    } catch (final Exception e) {
      LOGGER.error(e.getMessage(), e);
    }
  }

  private String getBaseUrl(final ServletRequest request) {

    HttpServletRequest httpRequest = (HttpServletRequest) request;

    return format(BASE_URL_TEMPLATE,
            httpRequest.getServerName(),
            httpRequest.getServerPort()
            == DEFAULT_HTTP_PORT
                    ? EMPTY_STRING
                    : COLON,
            httpRequest.getServerPort()
            == DEFAULT_HTTP_PORT
                    ? EMPTY_STRING
                    : httpRequest.getServerPort(),
            httpRequest.getContextPath());
  }
}
