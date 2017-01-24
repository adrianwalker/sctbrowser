package org.adrianwalker.terminology.sctbrowser.parameters;

import static org.adrianwalker.terminology.sctbrowser.parameters.ParameterConstants.DEFAULT_COUNT;
import static org.adrianwalker.terminology.sctbrowser.parameters.ParameterConstants.DEFAULT_LIMIT;
import static org.adrianwalker.terminology.sctbrowser.parameters.ParameterConstants.DEFAULT_OFFSET;
import static org.adrianwalker.terminology.sctbrowser.parameters.ParameterConstants.EMPTY_STRING;
import static org.junit.Assert.*;

import org.junit.Test;

public final class SearchParametersTest {

  @Test
  public void testTerms() {

    SearchParameters parameters = new SearchParameters();

    String expectedTerms = EMPTY_STRING;
    String terms = parameters.getTerms();
    assertEquals(expectedTerms, terms);

    expectedTerms = "s";
    parameters.setTerms(expectedTerms);
    terms = parameters.getTerms();
    assertEquals(expectedTerms, terms);
  }

  @Test
  public void testOffset() {

    SearchParameters parameters = new SearchParameters();

    Integer expectedOffset = DEFAULT_OFFSET;
    Integer offset = parameters.getOffset();
    assertEquals(expectedOffset, offset);

    expectedOffset = 1;
    parameters.setOffset(expectedOffset);
    offset = parameters.getOffset();
    assertEquals(expectedOffset, offset);
  }

  @Test
  public void testLimit() {

    SearchParameters parameters = new SearchParameters();

    Integer expectedLimit = DEFAULT_LIMIT;
    Integer limit = parameters.getLimit();
    assertEquals(expectedLimit, limit);

    expectedLimit = 1;
    parameters.setLimit(expectedLimit);
    limit = parameters.getLimit();
    assertEquals(expectedLimit, limit);
  }

  @Test
  public void testCount() {

    SearchParameters parameters = new SearchParameters();

    Boolean expectedCount = DEFAULT_COUNT;
    Boolean count = parameters.getCount();
    assertEquals(expectedCount, count);

    expectedCount = false;
    parameters.setCount(expectedCount);
    count = parameters.getCount();
    assertEquals(expectedCount, count);
  }

  @Test
  public void testToString() {

    SearchParameters parameters = new SearchParameters();

    String expectedToString = "terms = , "
            + "offset = 0, "
            + "limit = 20, "
            + "count = true";
    String toString = parameters.toString();
    assertEquals(expectedToString, toString);
  }

  @Test
  public void testHashCode() {

    SearchParameters parameters = new SearchParameters();

    int expectedHashCode = 39454696;
    int hashCode = parameters.hashCode();
    assertEquals(expectedHashCode, hashCode);
  }

  @Test
  public void testEquals() {

    SearchParameters parameters1 = new SearchParameters();
    assertTrue(parameters1.equals(parameters1));
    assertFalse(parameters1.equals(null));
    assertFalse(parameters1.equals(new Object()));

    SearchParameters parameters2 = new SearchParameters();
    assertTrue(parameters1.equals(parameters2));

    parameters2 = new SearchParameters();
    parameters2.setTerms("s");
    assertFalse(parameters1.equals(parameters2));

    parameters2 = new SearchParameters();
    parameters2.setOffset(1);
    assertFalse(parameters1.equals(parameters2));

    parameters2 = new SearchParameters();
    parameters2.setLimit(1);
    assertFalse(parameters1.equals(parameters2));

    parameters2 = new SearchParameters();
    parameters2.setCount(false);
    assertFalse(parameters1.equals(parameters2));
  }
}
