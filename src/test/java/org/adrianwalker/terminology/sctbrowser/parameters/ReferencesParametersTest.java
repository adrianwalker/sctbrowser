package org.adrianwalker.terminology.sctbrowser.parameters;

import static org.adrianwalker.terminology.sctbrowser.parameters.ParameterConstants.DEFAULT_COUNT;
import static org.adrianwalker.terminology.sctbrowser.parameters.ParameterConstants.DEFAULT_LIMIT;
import static org.adrianwalker.terminology.sctbrowser.parameters.ParameterConstants.DEFAULT_OFFSET;
import static org.adrianwalker.terminology.sctbrowser.parameters.ParameterConstants.PREFERRED;
import static org.adrianwalker.terminology.sctbrowser.parameters.ParameterConstants.ROOT_CONCEPT_ID;
import static org.adrianwalker.terminology.sctbrowser.parameters.ParameterConstants.SYNONYM;

import static org.junit.Assert.*;

import org.junit.Test;

public final class ReferencesParametersTest {

  @Test
  public void testConceptId() {

    ReferencesParameters parameters = new ReferencesParameters();

    Long expectedConceptId = ROOT_CONCEPT_ID;
    Long conceptId = parameters.getConceptId();
    assertEquals(expectedConceptId, conceptId);

    expectedConceptId = 1L;
    parameters.setConceptId(expectedConceptId);
    conceptId = parameters.getConceptId();
    assertEquals(expectedConceptId, conceptId);
  }

  @Test
  public void testLanguageRefsetAcceptabilityId() {

    ReferencesParameters parameters = new ReferencesParameters();

    Long expectedLanguageRefsetAcceptabilityId = PREFERRED;
    Long languageRefsetAcceptabilityId = parameters.getLanguageRefsetAcceptabilityId();
    assertEquals(expectedLanguageRefsetAcceptabilityId, languageRefsetAcceptabilityId);

    expectedLanguageRefsetAcceptabilityId = 1L;
    parameters.setLanguageRefsetAcceptabilityId(expectedLanguageRefsetAcceptabilityId);
    languageRefsetAcceptabilityId = parameters.getLanguageRefsetAcceptabilityId();
    assertEquals(expectedLanguageRefsetAcceptabilityId, languageRefsetAcceptabilityId);
  }

  @Test
  public void testDescriptionTypeId() {

    ReferencesParameters parameters = new ReferencesParameters();

    Long expectedDescriptionTypeId = SYNONYM;
    Long descriptionTypeId = parameters.getDescriptionTypeId();
    assertEquals(expectedDescriptionTypeId, descriptionTypeId);

    expectedDescriptionTypeId = 1L;
    parameters.setDescriptionTypeId(expectedDescriptionTypeId);
    descriptionTypeId = parameters.getDescriptionTypeId();
    assertEquals(expectedDescriptionTypeId, descriptionTypeId);
  }

  @Test
  public void testOffset() {

    ReferencesParameters parameters = new ReferencesParameters();

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

    ReferencesParameters parameters = new ReferencesParameters();

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

    ReferencesParameters parameters = new ReferencesParameters();

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

    ReferencesParameters parameters = new ReferencesParameters();

    String expectedToString = "conceptId = 138875005, "
            + "languageRefsetAcceptabilityId = 900000000000548007, "
            + "descriptionTypeId = 900000000000013009, "
            + "offset = 0, "
            + "limit = 20, "
            + "count = true";
    String toString = parameters.toString();
    assertEquals(expectedToString, toString);
  }

  @Test
  public void testHashCode() {

    ReferencesParameters parameters = new ReferencesParameters();

    int expectedHashCode = 362167109;
    int hashCode = parameters.hashCode();
    assertEquals(expectedHashCode, hashCode);
  }

  @Test
  public void testEquals() {

    ReferencesParameters parameters1 = new ReferencesParameters();
    assertTrue(parameters1.equals(parameters1));
    assertFalse(parameters1.equals(null));
    assertFalse(parameters1.equals(new Object()));

    ReferencesParameters parameters2 = new ReferencesParameters();
    assertTrue(parameters1.equals(parameters2));

    parameters2 = new ReferencesParameters();
    parameters2.setConceptId(1L);
    assertFalse(parameters1.equals(parameters2));
    
    parameters2 = new ReferencesParameters();
    parameters2.setLanguageRefsetAcceptabilityId(1L);
    assertFalse(parameters1.equals(parameters2));

    parameters2 = new ReferencesParameters();
    parameters2.setDescriptionTypeId(1L);
    assertFalse(parameters1.equals(parameters2));        

    parameters2 = new ReferencesParameters();
    parameters2.setOffset(1);
    assertFalse(parameters1.equals(parameters2));

    parameters2 = new ReferencesParameters();
    parameters2.setLimit(1);
    assertFalse(parameters1.equals(parameters2));

    parameters2 = new ReferencesParameters();
    parameters2.setCount(false);
    assertFalse(parameters1.equals(parameters2));
  }
}
