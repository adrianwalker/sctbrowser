package org.adrianwalker.terminology.sctbrowser.loader;

import static java.util.stream.Collectors.toList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public final class TxtFileLoader implements Loader {

  private static final Logger LOGGER = LoggerFactory.getLogger(TxtFileLoader.class);
  private static final PathMatcher TXT_MATCHER
          = FileSystems.getDefault().getPathMatcher("glob:*.txt");
  private static final Predicate<Path> TXT_FILTER
          = path -> TXT_MATCHER.matches(path.getFileName());
  private static final Pattern CONCEPTS_PATTERN
          = Pattern.compile("^.*_Concept_.*$");
  private static final Pattern DESCRIPTIONS_PATTERN
          = Pattern.compile("^.*_Description_.*$");
  private static final Pattern RELATIONSHIPS_PATTERN
          = Pattern.compile("^.*_Relationship_.*$");
  private static final Pattern LANGUAGE_REFSETS_PATTERN
          = Pattern.compile("^.*_cRefset_.*Language.*$");
  private static final Pattern CONTENT_REFSETS_PATTERN
          = Pattern.compile("^.*(_|_c)Refset_(AssociationReference|AttributeValue|Simple).*$");
  private static final Pattern MAP_REFSETS_PATTERN
          = Pattern.compile("^.*Refset_(Simple|Extended|Complex)Map.*$");
  private static final Pattern SUBSET_TO_REFSET_MAPPING_PATTERN
          = Pattern.compile("^.*RF1SubsetToRF2ReferenceSetMapping.*$");
  private final Loader loader;

  public TxtFileLoader(final Loader loader) {

    this.loader = loader;
  }

  public void load(final Path dataDir) {

    if (!Files.isDirectory(dataDir)) {
      return;
    }

    List<Path> txtFiles = walk(dataDir);

    try {
      load(txtFiles);
    } catch (final Exception e) {
      LOGGER.error(e.getMessage(), e);
    }
  }

  private void load(final List<Path> txtFiles) throws Exception {

    for (Path concept : filter(txtFiles, CONCEPTS_PATTERN)) {
      loadConcepts(concept);
    }

    for (Path description : filter(txtFiles, DESCRIPTIONS_PATTERN)) {
      loadDescriptions(description);
    }

    for (Path relationship : filter(txtFiles, RELATIONSHIPS_PATTERN)) {
      loadRelationships(relationship);
    }

    for (Path languageRefset : filter(txtFiles, LANGUAGE_REFSETS_PATTERN)) {
      loadLanguageRefset(languageRefset);
    }

    for (Path contentRefset : filter(txtFiles, CONTENT_REFSETS_PATTERN)) {
      loadContentRefset(contentRefset);
    }

    for (Path contentRefset : filter(txtFiles, MAP_REFSETS_PATTERN)) {
      loadMapRefset(contentRefset);
    }

    for (Path contentRefset : filter(txtFiles, SUBSET_TO_REFSET_MAPPING_PATTERN)) {
      loadSubsetToRefsetMapping(contentRefset);
    }
  }

  private List<Path> walk(final Path dataDir) {

    List<Path> txtFiles;

    try {

      txtFiles = Files
              .walk(dataDir)
              .filter(TXT_FILTER)
              .peek(txtFile -> LOGGER.debug("{}", txtFile))
              .collect(toList());

    } catch (final IOException ioe) {

      LOGGER.error(ioe.getMessage(), ioe);
      txtFiles = Collections.EMPTY_LIST;
    }

    return txtFiles;
  }

  private static List<Path> filter(
          final List<Path> txtFiles,
          final Pattern pattern) {

    return txtFiles
            .stream()
            .filter(path -> pattern
            .matcher(path.toAbsolutePath().toString())
            .matches())
            .collect(toList());
  }

  @Override
  public void createTables() throws Exception {
    loader.createTables();
  }

  @Override
  public void dropTables() throws Exception {
    loader.dropTables();
  }

  @Override
  public void loadConcepts(final Path path) throws Exception {
    loader.loadConcepts(path);
  }

  @Override
  public void loadDescriptions(final Path path) throws Exception {
    loader.loadDescriptions(path);
  }

  @Override
  public void loadRelationships(final Path path) throws Exception {
    loader.loadRelationships(path);
  }

  @Override
  public void loadLanguageRefset(final Path path) throws Exception {
    loader.loadLanguageRefset(path);
  }

  @Override
  public void loadContentRefset(final Path path) throws Exception {
    loader.loadContentRefset(path);
  }

  @Override
  public void loadMapRefset(final Path path) throws Exception {
    loader.loadMapRefset(path);
  }

  @Override
  public void loadSubsetToRefsetMapping(final Path path) throws Exception {
    loader.loadSubsetToRefsetMapping(path);
  }
  
  @Override
  public void createIndexes() throws Exception {
    loader.createIndexes();
  }
}
