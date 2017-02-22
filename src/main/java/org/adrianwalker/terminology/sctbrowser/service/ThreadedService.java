package org.adrianwalker.terminology.sctbrowser.service;

import static java.util.Collections.EMPTY_LIST;
import static java.util.stream.Collectors.toList;

import org.adrianwalker.terminology.sctbrowser.dataaccess.DataAccess;
import org.adrianwalker.terminology.sctbrowser.parameters.BrowseParameters;
import org.adrianwalker.terminology.sctbrowser.parameters.MembersParameters;
import org.adrianwalker.terminology.sctbrowser.parameters.ReferencesParameters;
import org.adrianwalker.terminology.sctbrowser.parameters.SearchParameters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.regex.Pattern;

public final class ThreadedService implements Service {

  private static final Logger LOGGER = LoggerFactory.getLogger(ThreadedService.class);
  private static final ExecutorService EXECUTOR = Executors.newWorkStealingPool();

  private static final String DESCRIPTIONS = "descriptions";
  private static final String PROPERTIES = "properties";
  private static final String RELATIONSHIPS = "relationships";
  private static final String PATHS = "paths";
  private static final String CONCEPTS = "concepts";
  private static final String TITLE = "title";
  private static final String CHILDREN = "children";
  private static final String LAZY = "lazy";
  private static final Pattern PREFIX = Pattern.compile("\\[.\\]");
  private static final String EMPTY_STRING = "";

  private static final Comparator<Map<String, Object>> CHILDREN_COMPARATOR
          = (concept1, concept2) -> {

            String title1 = PREFIX.matcher((String) concept1.get(TITLE)).replaceAll(EMPTY_STRING);
            String title2 = PREFIX.matcher((String) concept2.get(TITLE)).replaceAll(EMPTY_STRING);

            return title1.compareToIgnoreCase(title2);
          };

  private static final Integer MIN_OFFEST = 0;
  private static final Integer MAX_LIMIT = 20;

  private final DataAccess dataAccess;

  public ThreadedService(final DataAccess dataAccess) {

    this.dataAccess = dataAccess;
  }

  @Override
  public List<Map<String, Object>> browse(final BrowseParameters parameters) throws Exception {

    LOGGER.debug("{}", parameters);

    Future<List<Map<String, Object>>> concept = null;
    if (parameters.getConcept()) {

      concept = EXECUTOR.submit(() -> dataAccess.concept(parameters));
    }

    Future<List<Map<String, Object>>> children = null;
    if (parameters.getChildren()) {

      children = EXECUTOR.submit(() -> dataAccess.children(parameters)
              .parallelStream()
              .sorted(CHILDREN_COMPARATOR)
              .collect(toList()));
    }

    List<Map<String, Object>> browse = EMPTY_LIST;
    if (null != concept) {

      browse = concept.get();

      if (null != children) {

        List<Map<String, Object>> get = children.get();
        browse.parallelStream()
                .forEach(map -> {
                  map.put(LAZY, false);
                  map.put(CHILDREN, get);
                });
      }
    } else if (null != children) {
      browse = children.get();
    }

    return browse;
  }

  @Override
  public List<Map<String, Object>> refsets(final BrowseParameters parameters) throws Exception {

    LOGGER.debug("{}", parameters);

    return dataAccess.refsets(parameters);
  }

  @Override
  public Map<String, Object> references(final ReferencesParameters parameters) throws Exception {

    LOGGER.debug("{}", parameters);

    if (parameters.getOffset() < MIN_OFFEST) {
      parameters.setOffset(MIN_OFFEST);
    }

    if (parameters.getLimit() > MAX_LIMIT) {
      parameters.setLimit(MAX_LIMIT);
    }

    Future<List<Map<String, Object>>> concepts
            = EXECUTOR.submit(() -> dataAccess.references(parameters));

    Future<Map<String, Object>> count = null;
    if (parameters.getCount()) {
      count = EXECUTOR.submit(() -> dataAccess.referencesCount(parameters));
    }

    Map<String, Object> references = new HashMap<>();
    references.put(CONCEPTS, concepts.get());

    if (null != count) {
      references.putAll(count.get());
    }

    return references;
  }

  @Override
  public List<Map<String, Object>> subsets(final BrowseParameters parameters) throws Exception {

    LOGGER.debug("{}", parameters);

    return dataAccess.subsets(parameters);
  }

  @Override
  public List<Map<String, Object>> mappings(final BrowseParameters parameters) throws Exception {

    LOGGER.debug("{}", parameters);

    return dataAccess.mappings(parameters);
  }

  @Override
  public Map<String, Object> members(final MembersParameters parameters) throws Exception {

    LOGGER.debug("{}", parameters);

    if (parameters.getOffset() < MIN_OFFEST) {
      parameters.setOffset(MIN_OFFEST);
    }

    if (parameters.getLimit() > MAX_LIMIT) {
      parameters.setLimit(MAX_LIMIT);
    }

    Future<List<Map<String, Object>>> concepts
            = EXECUTOR.submit(() -> dataAccess.members(parameters));

    Future<Map<String, Object>> count = null;
    if (parameters.getCount()) {
      count = EXECUTOR.submit(() -> dataAccess.membersCount(parameters));
    }

    Map<String, Object> members = new HashMap<>();
    members.put(CONCEPTS, concepts.get());

    if (null != count) {
      members.putAll(count.get());
    }

    return members;
  }

  @Override
  public Map<String, Object> memberOf(final MembersParameters parameters) throws Exception {

    LOGGER.debug("{}", parameters);

    if (parameters.getOffset() < MIN_OFFEST) {
      parameters.setOffset(MIN_OFFEST);
    }

    if (parameters.getLimit() > MAX_LIMIT) {
      parameters.setLimit(MAX_LIMIT);
    }

    Future<List<Map<String, Object>>> concepts
            = EXECUTOR.submit(() -> dataAccess.memberOf(parameters));

    Future<Map<String, Object>> count = null;
    if (parameters.getCount()) {
      count = EXECUTOR.submit(() -> dataAccess.memberOfCount(parameters));
    }

    Map<String, Object> memberOf = new HashMap<>();
    memberOf.put(CONCEPTS, concepts.get());

    if (null != count) {
      memberOf.putAll(count.get());
    }

    return memberOf;
  }

  @Override
  public Map<String, Object> search(final SearchParameters parameters) throws Exception {

    LOGGER.debug("{}", parameters);

    if (parameters.getOffset() < MIN_OFFEST) {
      parameters.setOffset(MIN_OFFEST);
    }

    if (parameters.getLimit() > MAX_LIMIT) {
      parameters.setLimit(MAX_LIMIT);
    }

    Future<List<Map<String, Object>>> concepts
            = EXECUTOR.submit(() -> dataAccess.search(parameters));

    Future<Map<String, Object>> count = null;
    if (parameters.getCount()) {
      count = EXECUTOR.submit(() -> dataAccess.searchCount(parameters));
    }

    Map<String, Object> search = new HashMap<>();
    search.put(CONCEPTS, concepts.get());

    if (null != count) {
      search.putAll(count.get());
    }

    return search;
  }

  @Override
  public Map<String, List<Map<String, Object>>> details(final BrowseParameters parameters)
          throws Exception {

    Future<List<Map<String, Object>>> descriptions
            = EXECUTOR.submit(() -> dataAccess.descriptions(parameters));
    Future<List<Map<String, Object>>> properties
            = EXECUTOR.submit(() -> dataAccess.properties(parameters));
    Future<List<Map<String, Object>>> relationships
            = EXECUTOR.submit(() -> dataAccess.relationships(parameters));
    Future<List<Map<String, Object>>> paths
            = EXECUTOR.submit(() -> dataAccess.paths(parameters));

    Map<String, List<Map<String, Object>>> details = new HashMap<>();
    details.put(DESCRIPTIONS, descriptions.get());
    details.put(PROPERTIES, properties.get());
    details.put(RELATIONSHIPS, relationships.get());
    details.put(PATHS, paths.get());

    return details;
  }
}
