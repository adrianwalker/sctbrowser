package org.adrianwalker.terminology.sctbrowser.dataaccess;

import static java.util.stream.Collectors.joining;

import org.adrianwalker.terminology.sctbrowser.parameters.BrowseParameters;
import org.adrianwalker.terminology.sctbrowser.parameters.MembersParameters;
import org.adrianwalker.terminology.sctbrowser.parameters.SearchParameters;
import org.adrianwalker.terminology.sctbrowser.resource.ResourceFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import javax.sql.DataSource;

public final class PostgresqlDataAccess implements DataAccess {

  private static final Logger LOGGER = LoggerFactory.getLogger(PostgresqlDataAccess.class);

  private static final String CONCEPT
          = ResourceFile.readAsString("/sql/postgresql/query/concept.sql");
  private static final String CHILDREN
          = ResourceFile.readAsString("/sql/postgresql/query/children.sql");
  private static final String PATHS
          = ResourceFile.readAsString("/sql/postgresql/query/paths.sql");
  private static final String SEARCH
          = ResourceFile.readAsString("/sql/postgresql/query/search.sql");
  private static final String SEARCH_COUNT
          = ResourceFile.readAsString("/sql/postgresql/query/search_count.sql");
  private static final String DESCRIPTIONS
          = ResourceFile.readAsString("/sql/postgresql/query/descriptions.sql");
  private static final String REFSETS
          = ResourceFile.readAsString("/sql/postgresql/query/refsets.sql");
  private static final String SUBSETS
          = ResourceFile.readAsString("/sql/postgresql/query/subsets.sql");
  private static final String MAPPINGS
          = ResourceFile.readAsString("/sql/postgresql/query/mappings.sql");
  private static final String MEMBER_OF
          = ResourceFile.readAsString("/sql/postgresql/query/member_of.sql");
  private static final String MEMBER_OF_COUNT
          = ResourceFile.readAsString("/sql/postgresql/query/member_of_count.sql");
  private static final String MEMBERS
          = ResourceFile.readAsString("/sql/postgresql/query/members.sql");
  private static final String MEMBERS_COUNT
          = ResourceFile.readAsString("/sql/postgresql/query/members_count.sql");
  private static final String RELATIONSHIPS
          = ResourceFile.readAsString("/sql/postgresql/query/relationships.sql");
  private static final String PROPERTIES
          = ResourceFile.readAsString("/sql/postgresql/query/properties.sql");
  private static final String AND
          = " & ";
  private static final String PREFIX_MATCH
          = ":*";
  private static final Pattern WS
          = Pattern.compile("\\s+");

  private final DataSource dataSource;

  public PostgresqlDataAccess(final DataSource dataSource) {

    this.dataSource = dataSource;
  }

  @Override
  public List<Map<String, Object>> concept(final BrowseParameters parameters) throws Exception {

    LOGGER.debug("{}", parameters);

    List<Map<String, Object>> results;
    try (
            Connection connection = dataSource.getConnection();
            PreparedStatement concept = connection.prepareStatement(CONCEPT)) {

      concept.setLong(1, parameters.getConceptId());
      concept.setLong(2, parameters.getDescriptionTypeId());
      concept.setLong(3, parameters.getLanguageRefsetAcceptabilityId());

      LOGGER.debug("sql = \n{}", CONCEPT);

      try (ResultSet resultSet = concept.executeQuery()) {
        results = resultList(resultSet);
      }
    }

    LOGGER.debug("results = {}", results);

    return results;
  }

  @Override
  public List<Map<String, Object>> children(final BrowseParameters parameters) throws Exception {

    LOGGER.debug("{}", parameters);

    List<Map<String, Object>> results;
    try (
            Connection connection = dataSource.getConnection();
            PreparedStatement children = connection.prepareStatement(CHILDREN)) {

      children.setLong(1, parameters.getRelationshipTypeId());
      children.setLong(2, parameters.getDescriptionTypeId());
      children.setLong(3, parameters.getConceptId());
      children.setLong(4, parameters.getRelationshipTypeId());
      children.setLong(5, parameters.getLanguageRefsetAcceptabilityId());

      LOGGER.debug("sql = \n{}", CHILDREN);

      try (ResultSet resultSet = children.executeQuery()) {
        results = resultList(resultSet);
      }
    }

    LOGGER.debug("results = {}", results);

    return results;
  }

  @Override
  public List<Map<String, Object>> paths(final BrowseParameters parameters) throws Exception {

    LOGGER.debug("{}", parameters);

    List<Map<String, Object>> results;
    try (
            Connection connection = dataSource.getConnection();
            PreparedStatement paths = connection.prepareStatement(PATHS)) {

      paths.setLong(1, parameters.getConceptId());
      paths.setLong(2, parameters.getRelationshipTypeId());
      paths.setLong(3, parameters.getRelationshipTypeId());

      LOGGER.debug("sql = \n{}", PATHS);

      try (ResultSet resultSet = paths.executeQuery()) {
        results = resultList(resultSet);
      }
    }

    LOGGER.debug("results = {}", results);

    return results;
  }

  @Override
  public List<Map<String, Object>> search(final SearchParameters parameters) throws Exception {

    LOGGER.debug("{}", parameters);

    List<Map<String, Object>> results;
    try (
            Connection connection = dataSource.getConnection();
            PreparedStatement search = connection.prepareStatement(SEARCH)) {

      search.setString(1, searchQuery(parameters.getTerms()));
      search.setInt(2, parameters.getOffset());
      search.setInt(3, parameters.getLimit());

      LOGGER.debug("sql = \n{}", SEARCH);

      try (ResultSet resultSet = search.executeQuery()) {
        results = resultList(resultSet);
      }
    }

    LOGGER.debug("results = {}", results);

    return results;
  }

  @Override
  public Map<String, Object> searchCount(final SearchParameters parameters) throws Exception {

    LOGGER.debug("{}", parameters);

    Map<String, Object> results;
    try (
            Connection connection = dataSource.getConnection();
            PreparedStatement searchCount = connection.prepareStatement(SEARCH_COUNT)) {

      searchCount.setString(1, searchQuery(parameters.getTerms()));

      LOGGER.debug("sql = \n{}", SEARCH_COUNT);

      try (ResultSet resultSet = searchCount.executeQuery()) {
        results = resultList(resultSet).get(0);
      }
    }

    LOGGER.debug("results = {}", results);

    return results;
  }

  @Override
  public List<Map<String, Object>> refsets(final BrowseParameters parameters) throws Exception {

    LOGGER.debug("{}", parameters);

    List<Map<String, Object>> results;
    try (
            Connection connection = dataSource.getConnection();
            PreparedStatement refsets = connection.prepareStatement(REFSETS)) {

      refsets.setLong(1, parameters.getDescriptionTypeId());
      refsets.setLong(2, parameters.getLanguageRefsetAcceptabilityId());

      LOGGER.debug("sql = \n{}", REFSETS);

      try (ResultSet resultSet = refsets.executeQuery()) {
        results = resultList(resultSet);
      }
    }

    LOGGER.debug("results = {}", results);

    return results;
  }

  @Override
  public List<Map<String, Object>> subsets(final BrowseParameters parameters) throws Exception {

    LOGGER.debug("{}", parameters);

    List<Map<String, Object>> results;
    try (
            Connection connection = dataSource.getConnection();
            PreparedStatement refsets = connection.prepareStatement(SUBSETS)) {

      LOGGER.debug("sql = \n{}", SUBSETS);

      try (ResultSet resultSet = refsets.executeQuery()) {
        results = resultList(resultSet);
      }
    }

    LOGGER.debug("results = {}", results);

    return results;
  }

  @Override
  public List<Map<String, Object>> mappings(final BrowseParameters parameters) throws Exception {

    LOGGER.debug("{}", parameters);

    List<Map<String, Object>> results;
    try (
            Connection connection = dataSource.getConnection();
            PreparedStatement mappings = connection.prepareStatement(MAPPINGS)) {

      mappings.setLong(1, parameters.getConceptId());
      mappings.setLong(2, parameters.getDescriptionTypeId());
      mappings.setLong(3, parameters.getLanguageRefsetAcceptabilityId());

      LOGGER.debug("sql = \n{}", MAPPINGS);

      try (ResultSet resultSet = mappings.executeQuery()) {
        results = resultList(resultSet);
      }
    }

    LOGGER.debug("results = {}", results);

    return results;
  }

  @Override
  public List<Map<String, Object>> memberOf(final MembersParameters parameters) throws Exception {

    LOGGER.debug("{}", parameters);

    List<Map<String, Object>> results;
    try (
            Connection connection = dataSource.getConnection();
            PreparedStatement memberOf = connection.prepareStatement(MEMBER_OF)) {

      memberOf.setLong(1, parameters.getConceptId());
      memberOf.setLong(2, parameters.getDescriptionTypeId());
      memberOf.setLong(3, parameters.getLanguageRefsetAcceptabilityId());
      memberOf.setInt(4, parameters.getOffset());
      memberOf.setInt(5, parameters.getLimit());

      LOGGER.debug("sql = \n{}", MEMBER_OF);

      try (ResultSet resultSet = memberOf.executeQuery()) {
        results = resultList(resultSet);
      }
    }

    LOGGER.debug("results = {}", results);

    return results;
  }

  @Override
  public Map<String, Object> memberOfCount(final MembersParameters parameters) throws Exception {

    LOGGER.debug("{}", parameters);

    Map<String, Object> results;
    try (
            Connection connection = dataSource.getConnection();
            PreparedStatement memberOfCount = connection.prepareStatement(MEMBER_OF_COUNT)) {

      memberOfCount.setLong(1, parameters.getConceptId());
      memberOfCount.setLong(2, parameters.getDescriptionTypeId());
      memberOfCount.setLong(3, parameters.getLanguageRefsetAcceptabilityId());

      LOGGER.debug("sql = \n{}", MEMBER_OF_COUNT);

      try (ResultSet resultSet = memberOfCount.executeQuery()) {
        results = resultList(resultSet).get(0);
      }
    }

    LOGGER.debug("results = {}", results);

    return results;
  }

  @Override
  public List<Map<String, Object>> members(final MembersParameters parameters) throws Exception {

    LOGGER.debug("{}", parameters);

    List<Map<String, Object>> results;
    try (
            Connection connection = dataSource.getConnection();
            PreparedStatement members = connection.prepareStatement(MEMBERS)) {

      members.setLong(1, parameters.getConceptId());
      members.setLong(2, parameters.getDescriptionTypeId());
      members.setLong(3, parameters.getLanguageRefsetAcceptabilityId());
      members.setInt(4, parameters.getOffset());
      members.setInt(5, parameters.getLimit());

      LOGGER.debug("sql = \n{}", MEMBERS);

      try (ResultSet resultSet = members.executeQuery()) {
        results = resultList(resultSet);
      }
    }

    LOGGER.debug("results = {}", results);

    return results;
  }

  @Override
  public Map<String, Object> membersCount(final MembersParameters parameters) throws Exception {

    LOGGER.debug("{}", parameters);

    Map<String, Object> results;
    try (
            Connection connection = dataSource.getConnection();
            PreparedStatement membersCount = connection.prepareStatement(MEMBERS_COUNT)) {

      membersCount.setLong(1, parameters.getConceptId());
      membersCount.setLong(2, parameters.getDescriptionTypeId());
      membersCount.setLong(3, parameters.getLanguageRefsetAcceptabilityId());

      LOGGER.debug("sql = \n{}", MEMBERS_COUNT);

      try (ResultSet resultSet = membersCount.executeQuery()) {
        results = resultList(resultSet).get(0);
      }
    }

    LOGGER.debug("results = {}", results);

    return results;
  }

  @Override
  public List<Map<String, Object>> descriptions(final BrowseParameters parameters)
          throws Exception {

    LOGGER.debug("{}", parameters);

    List<Map<String, Object>> results;
    try (
            Connection connection = dataSource.getConnection();
            PreparedStatement descriptions = connection.prepareStatement(DESCRIPTIONS)) {

      descriptions.setLong(1, parameters.getConceptId());
      descriptions.setLong(2, parameters.getDescriptionTypeId());
      descriptions.setLong(3, parameters.getDescriptionTypeId());
      descriptions.setLong(4, parameters.getLanguageRefsetAcceptabilityId());

      LOGGER.debug("sql = \n{}", DESCRIPTIONS);

      try (ResultSet resultSet = descriptions.executeQuery()) {
        results = resultList(resultSet);
      }
    }

    LOGGER.debug("results = {}", results);

    return results;
  }

  @Override
  public List<Map<String, Object>> relationships(final BrowseParameters parameters) 
          throws Exception {

    LOGGER.debug("{}", parameters);

    List<Map<String, Object>> results;
    try (
            Connection connection = dataSource.getConnection();
            PreparedStatement relationships = connection.prepareStatement(RELATIONSHIPS)) {

      relationships.setLong(1, parameters.getConceptId());
      relationships.setLong(2, parameters.getDescriptionTypeId());
      relationships.setLong(3, parameters.getDescriptionTypeId());
      relationships.setLong(4, parameters.getLanguageRefsetAcceptabilityId());
      relationships.setLong(5, parameters.getLanguageRefsetAcceptabilityId());

      LOGGER.debug("sql = \n{}", RELATIONSHIPS);

      try (ResultSet resultSet = relationships.executeQuery()) {
        results = resultList(resultSet);
      }
    }

    LOGGER.debug("results = {}", results);

    return results;
  }

  @Override
  public List<Map<String, Object>> properties(final BrowseParameters parameters) throws Exception {

    LOGGER.debug("{}", parameters);

    List<Map<String, Object>> results;
    try (
            Connection connection = dataSource.getConnection();
            PreparedStatement properties = connection.prepareStatement(PROPERTIES)) {

      properties.setLong(1, parameters.getConceptId());
      properties.setLong(2, parameters.getDescriptionTypeId());
      properties.setLong(3, parameters.getLanguageRefsetAcceptabilityId());

      LOGGER.debug("sql = \n{}", PROPERTIES);

      try (ResultSet resultSet = properties.executeQuery()) {
        results = resultList(resultSet);
      }
    }

    LOGGER.debug("results = {}", results);

    return results;
  }

  private static String searchQuery(final String terms) {

    return WS.splitAsStream(terms)
            .map(term -> term + PREFIX_MATCH)
            .collect(joining(AND));
  }

  private List<Map<String, Object>> resultList(final ResultSet resultSet) throws SQLException {

    List<Map<String, Object>> resultList = new ArrayList<>();

    ResultSetMetaData metaData = resultSet.getMetaData();
    int columnCount = metaData.getColumnCount();

    while (resultSet.next()) {

      Map<String, Object> resultMap = new HashMap<>(columnCount);

      for (int column = 1; column <= columnCount; column++) {

        resultMap.put(
                metaData.getColumnLabel(column),
                resultSet.getObject(column));
      }

      resultList.add(resultMap);
    }

    return resultList;
  }
}
