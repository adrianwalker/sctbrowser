package org.adrianwalker.terminology.sctbrowser.loader;

import java.nio.file.Path;

public interface Loader {

  void createTables() throws Exception;

  void dropTables() throws Exception;

  void loadConcepts(Path path) throws Exception;

  void loadDescriptions(Path path) throws Exception;

  void loadRelationships(Path path) throws Exception;

  void loadLanguageRefset(Path path) throws Exception;

  void loadContentRefset(Path path) throws Exception;

  void loadMapRefset(Path path) throws Exception;

  void loadSubsetToRefsetMapping(Path path) throws Exception;

  void createIndexes() throws Exception;
}
