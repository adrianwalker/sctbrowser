package org.adrianwalker.terminology.sctbrowser.parameters;

import static org.adrianwalker.terminology.sctbrowser.parameters.ParameterConstants.DEFAULT_COUNT;
import static org.adrianwalker.terminology.sctbrowser.parameters.ParameterConstants.DEFAULT_LIMIT;
import static org.adrianwalker.terminology.sctbrowser.parameters.ParameterConstants.DEFAULT_OFFSET;
import static org.adrianwalker.terminology.sctbrowser.parameters.ParameterConstants.PREFERRED;
import static org.adrianwalker.terminology.sctbrowser.parameters.ParameterConstants.ROOT_CONCEPT_ID;
import static org.adrianwalker.terminology.sctbrowser.parameters.ParameterConstants.SYNONYM;
import static org.junit.Assert.*;

import org.junit.Test;

public final class MembersParametersTest {

  @Test
  public void testConceptId() {

    MembersParameters parameters = new MembersParameters();

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

    MembersParameters parameters = new MembersParameters();

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

    MembersParameters parameters = new MembersParameters();

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

    MembersParameters parameters = new MembersParameters();

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

    MembersParameters parameters = new MembersParameters();

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

    MembersParameters parameters = new MembersParameters();

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

    MembersParameters parameters = new MembersParameters();

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

    MembersParameters parameters = new MembersParameters();

    int expectedHashCode = -1020750401;
    int hashCode = parameters.hashCode();
    assertEquals(expectedHashCode, hashCode);
  }

  @Test
  public void testEquals() {

    MembersParameters parameters1 = new MembersParameters();
    assertTrue(parameters1.equals(parameters1));
    assertFalse(parameters1.equals(null));
    assertFalse(parameters1.equals(new Object()));

    MembersParameters parameters2 = new MembersParameters();
    assertTrue(parameters1.equals(parameters2));

    parameters2 = new MembersParameters();
    parameters2.setConceptId(1L);
    assertFalse(parameters1.equals(parameters2));

    parameters2 = new MembersParameters();
    parameters2.setLanguageRefsetAcceptabilityId(1L);
    assertFalse(parameters1.equals(parameters2));

    parameters2 = new MembersParameters();
    parameters2.setDescriptionTypeId(1L);
    assertFalse(parameters1.equals(parameters2));    
    
    parameters2 = new MembersParameters();
    parameters2.setOffset(1);
    assertFalse(parameters1.equals(parameters2));

    parameters2 = new MembersParameters();
    parameters2.setLimit(1);
    assertFalse(parameters1.equals(parameters2));

    parameters2 = new MembersParameters();
    parameters2.setCount(false);
    assertFalse(parameters1.equals(parameters2));
  }
}
