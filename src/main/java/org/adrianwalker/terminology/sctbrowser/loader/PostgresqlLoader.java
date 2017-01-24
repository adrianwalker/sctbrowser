package org.adrianwalker.terminology.sctbrowser.loader;

import static java.lang.String.format;
import static org.adrianwalker.terminology.sctbrowser.resource.ResourceFile.readAsString;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;

public final class PostgresqlLoader implements Loader {

  private static final Logger LOGGER = LoggerFactory.getLogger(PostgresqlLoader.class);

  private static final String COLUMN_DELIMITER = "\t";
  private static final int CONTENT_REFSET_COLUMN_COUNT = 6;
  private static final int CONTENT_COMPONENT_REFSET_COLUMN_COUNT = 7;
  private static final int SIMPLE_MAP_REFSET_COLUMN_COUNT = 7;
  private static final int EXTENDED_MAP_REFSET_COLUMN_COUNT = 13;

  private static final String POSTGRES_SQL_DIR = "/sql/postgresql/";

  private static final String DROP_TABLES_DIR = "table/drop/";
  private static final String CREATE_TABLES_DIR = "table/create/";
  private static final String COPY_DIR = "copy/";
  private static final String ALTER_TABLES_DIR = "table/alter/";
  private static final String UPDATE_TABLES_DIR = "table/update/";
  private static final String CREATE_INDEX_DIR = "index/";
  private static final String VACUUM_TABLES_DIR = "table/vacuum/";

  private static final String[] DROP_TABLE_FILES = {
    "drop_concept.sql",
    "drop_content_component_refset.sql",
    "drop_content_refset.sql",
    "drop_description.sql",
    "drop_extended_map_refset.sql",
    "drop_language_refset.sql",
    "drop_relationship.sql",
    "drop_simple_map_refset.sql",
    "drop_subset_to_refset_mapping.sql"
  };

  private static final String[] CREATE_TABLE_FILES = {
    "create_concept.sql",
    "create_content_component_refset.sql",
    "create_content_refset.sql",
    "create_description.sql",
    "create_extended_map_refset.sql",
    "create_language_refset.sql",
    "create_relationship.sql",
    "create_simple_map_refset.sql",
    "create_subset_to_refset_mapping.sql"
  };

  private static final String COPY_CONCEPT_FILE
          = "copy_concept.sql";
  private static final String COPY_CONTENT_COMPONENT_REFSET_FILE
          = "copy_content_component_refset.sql";
  private static final String COPY_CONTENT_REFSET_FILE
          = "copy_content_refset.sql";
  private static final String COPY_DESCRIPTION_FILE
          = "copy_description.sql";
  private static final String COPY_EXTENDED_MAP_REFSET_FILE
          = "copy_extended_map_refset.sql";
  private static final String COPY_LANGUAGE_REFSET_FILE
          = "copy_language_refset.sql";
  private static final String COPY_RELATIONSHIP_FILE
          = "copy_relationship.sql";
  private static final String COPY_SIMPLE_MAP_REFSET_FILE
          = "copy_simple_map_refset.sql";
  private static final String COPY_SUBSET_TO_REFSET_MAPPING_FILE
          = "copy_subset_to_refset_mapping.sql";

  private static final String[] ALTER_TABLE_FILES = {
    "alter_description.sql"
  };

  private static final String[] UPDATE_TABLE_FILES = {
    "update_description.sql"
  };

  private static final String[] CREATE_INDEX_FILES = {
    "create_concept_active_id_module_id_effective_time.sql",
    "create_concept_active_id.sql",
    "create_content_component_refset_active_referenced_component_id.sql",
    "create_content_component_refset_active_refset_id_referenced_component_id.sql",
    "create_content_refset_active_referenced_component_id.sql",
    "create_content_refset_active_refset_id_referenced_component_id.sql",
    "create_description_active_concept_id_type_id_id_term.sql",
    "create_description_search_text.sql",
    "create_extended_map_refset_active_referenced_component_id_map_target_refset_id.sql",
    "create_language_refset_active_referenced_component_id_acceptability_id.sql",
    "create_language_refset_active_referenced_component_id.sql",
    "create_relationship_active_destination_id_source_id_type_id.sql",
    "create_relationship_active_source_id_destination_id_type_id.sql",
    "create_simple_map_refset_active_referenced_component_id_map_target_refset_id.sql"
  };

  private static final String[] VACUUM_TABLE_FILES = {
    "vacuum_concept.sql",
    "vacuum_content_refset.sql",
    "vacuum_description.sql",
    "vacuum_extended_map_refset.sql",
    "vacuum_language_refset.sql",
    "vacuum_relationship.sql",
    "vacuum_simple_map_refset.sql"
  };

  private final DataSource dataSource;

  public PostgresqlLoader(final DataSource dataSource) {

    this.dataSource = dataSource;
  }

  @Override
  public void createTables() throws Exception {

    for (String createTable : CREATE_TABLE_FILES) {

      LOGGER.info("creating table '{}'", createTable);

      execute(readAsString(POSTGRES_SQL_DIR + CREATE_TABLES_DIR + createTable));
    }
  }

  @Override
  public void dropTables() throws Exception {

    for (String dropTable : DROP_TABLE_FILES) {

      LOGGER.info("dropping table '{}'", dropTable);

      execute(readAsString(POSTGRES_SQL_DIR + DROP_TABLES_DIR + dropTable));
    }
  }

  @Override
  public void loadConcepts(final Path path) throws Exception {

    LOGGER.info("loading concepts '{}'", path.getFileName());

    execute(readAsString(POSTGRES_SQL_DIR + COPY_DIR + COPY_CONCEPT_FILE)
            .replace("?", format("'%s'", path.toAbsolutePath())));
  }

  @Override
  public void loadDescriptions(final Path path) throws Exception {

    LOGGER.info("loading descriptions '{}'", path.getFileName());

    execute(readAsString(POSTGRES_SQL_DIR + COPY_DIR + COPY_DESCRIPTION_FILE)
            .replace("?", format("'%s'", path.toAbsolutePath())));
  }

  @Override
  public void loadRelationships(final Path path) throws Exception {

    LOGGER.info("loading relationships '{}'", path.getFileName());

    execute(readAsString(POSTGRES_SQL_DIR + COPY_DIR + COPY_RELATIONSHIP_FILE)
            .replace("?", format("'%s'", path.toAbsolutePath())));
  }

  @Override
  public void loadLanguageRefset(final Path path) throws Exception {

    LOGGER.info("loading language refset '{}'", path.getFileName());

    execute(readAsString(POSTGRES_SQL_DIR + COPY_DIR + COPY_LANGUAGE_REFSET_FILE)
            .replace("?", format("'%s'", path.toAbsolutePath())));
  }

  @Override
  public void loadContentRefset(final Path path) throws Exception {

    LOGGER.info("loading content refset '{}'", path.getFileName());

    int columnCount = Files.lines(path)
            .findFirst()
            .get()
            .split(COLUMN_DELIMITER).length;

    String file;
    switch (columnCount) {
      case CONTENT_REFSET_COLUMN_COUNT:
        file = COPY_CONTENT_REFSET_FILE;
        break;
      case CONTENT_COMPONENT_REFSET_COLUMN_COUNT:
        file = COPY_CONTENT_COMPONENT_REFSET_FILE;
        break;
      default:
        throw new Exception(
                "no copy statement for refset with "
                + columnCount
                + " columns");
    }

    execute(readAsString(POSTGRES_SQL_DIR + COPY_DIR + file)
            .replace("?", format("'%s'", path.toAbsolutePath())));
  }

  @Override
  public void loadMapRefset(final Path path) throws Exception {

    LOGGER.info("loading map refset '{}'", path.getFileName());

    int columnCount = Files.lines(path)
            .findFirst()
            .get()
            .split(COLUMN_DELIMITER).length;

    String file;
    switch (columnCount) {
      case SIMPLE_MAP_REFSET_COLUMN_COUNT:
        file = COPY_SIMPLE_MAP_REFSET_FILE;
        break;
      case EXTENDED_MAP_REFSET_COLUMN_COUNT:
        file = COPY_EXTENDED_MAP_REFSET_FILE;
        break;
      default:
        throw new Exception(
                "no copy statement for refset with "
                + columnCount
                + " columns");
    }

    execute(readAsString(POSTGRES_SQL_DIR + COPY_DIR + file)
            .replace("?", format("'%s'", path.toAbsolutePath())));
  }

  @Override
  public void loadSubsetToRefsetMapping(final Path path) throws Exception {

    LOGGER.info("loading subset tp refset mapping '{}'", path.getFileName());

    execute(readAsString(POSTGRES_SQL_DIR + COPY_DIR + COPY_SUBSET_TO_REFSET_MAPPING_FILE)
            .replace("?", format("'%s'", path.toAbsolutePath())));
  }

  @Override
  public void createIndexes() throws Exception {

    for (String alterTable : ALTER_TABLE_FILES) {

      LOGGER.info("altering table '{}'", alterTable);

      execute(readAsString(POSTGRES_SQL_DIR + ALTER_TABLES_DIR + alterTable));
    }

    for (String updateTable : UPDATE_TABLE_FILES) {

      LOGGER.info("updating table '{}'", updateTable);

      execute(readAsString(POSTGRES_SQL_DIR + UPDATE_TABLES_DIR + updateTable));
    }

    for (String createIndex : CREATE_INDEX_FILES) {

      LOGGER.info("creating index '{}'", createIndex);

      execute(readAsString(POSTGRES_SQL_DIR + CREATE_INDEX_DIR + createIndex));
    }

    vacuumTables();
  }

  private void vacuumTables() throws SQLException {

    for (String vacuumTable : VACUUM_TABLE_FILES) {

      LOGGER.info("vacumming '{}'", vacuumTable);

      execute(readAsString(POSTGRES_SQL_DIR + VACUUM_TABLES_DIR + vacuumTable));
    }
  }

  private void execute(final String sql) throws SQLException {

    LOGGER.debug("sql = \n{}", sql);

    try (
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {

      ps.executeUpdate();
    }
  }
}
