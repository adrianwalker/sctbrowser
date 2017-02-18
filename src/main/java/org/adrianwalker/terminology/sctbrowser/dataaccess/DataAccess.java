package org.adrianwalker.terminology.sctbrowser.dataaccess;

import org.adrianwalker.terminology.sctbrowser.parameters.BrowseParameters;
import org.adrianwalker.terminology.sctbrowser.parameters.MembersParameters;
import org.adrianwalker.terminology.sctbrowser.parameters.ReferencesParameters;
import org.adrianwalker.terminology.sctbrowser.parameters.SearchParameters;

import java.util.List;
import java.util.Map;

public interface DataAccess {

  List<Map<String, Object>> concept(BrowseParameters parameters) throws Exception;

  List<Map<String, Object>> children(BrowseParameters parameters) throws Exception;

  List<Map<String, Object>> paths(BrowseParameters parameters) throws Exception;

  List<Map<String, Object>> search(SearchParameters parameters) throws Exception;

  Map<String, Object> searchCount(SearchParameters parameters) throws Exception;

  List<Map<String, Object>> refsets(BrowseParameters parameters) throws Exception;

  List<Map<String, Object>> subsets(BrowseParameters parameters) throws Exception;

  List<Map<String, Object>> mappings(BrowseParameters parameters) throws Exception;

  List<Map<String, Object>> memberOf(MembersParameters parameters) throws Exception;

  Map<String, Object> memberOfCount(MembersParameters parameters) throws Exception;

  List<Map<String, Object>> members(MembersParameters parameters) throws Exception;

  Map<String, Object> membersCount(MembersParameters parameters) throws Exception;

  List<Map<String, Object>> descriptions(BrowseParameters parameters) throws Exception;

  List<Map<String, Object>> relationships(BrowseParameters parameters) throws Exception;
  
  List<Map<String, Object>> references(ReferencesParameters parameters) throws Exception;
  
  Map<String, Object> referencesCount(ReferencesParameters parameters) throws Exception;

  List<Map<String, Object>> properties(BrowseParameters parameters) throws Exception;
}
