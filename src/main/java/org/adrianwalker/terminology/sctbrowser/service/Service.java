package org.adrianwalker.terminology.sctbrowser.service;

import org.adrianwalker.terminology.sctbrowser.parameters.BrowseParameters;
import org.adrianwalker.terminology.sctbrowser.parameters.MembersParameters;
import org.adrianwalker.terminology.sctbrowser.parameters.ReferencesParameters;
import org.adrianwalker.terminology.sctbrowser.parameters.SearchParameters;

import java.util.List;
import java.util.Map;

public interface Service {

  List<Map<String, Object>> browse(BrowseParameters parameters) throws Exception;

  Map<String, List<Map<String, Object>>> details(BrowseParameters parameters) throws Exception;

  List<Map<String, Object>> mappings(BrowseParameters parameters) throws Exception;

  Map<String, Object> memberOf(MembersParameters parameters) throws Exception;

  Map<String, Object> members(MembersParameters parameters) throws Exception;

  List<Map<String, Object>> refsets(BrowseParameters parameters) throws Exception;
  
  Map<String, Object> references(ReferencesParameters parameters) throws Exception;

  List<Map<String, Object>> subsets(BrowseParameters parameters) throws Exception;

  Map<String, Object> search(SearchParameters parameters) throws Exception;
}
