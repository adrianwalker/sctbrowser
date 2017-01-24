package org.adrianwalker.terminology.sctbrowser.parameters;

import static org.adrianwalker.terminology.sctbrowser.parameters.ParameterConstants.IS_A;
import static org.adrianwalker.terminology.sctbrowser.parameters.ParameterConstants.PREFERRED;
import static org.adrianwalker.terminology.sctbrowser.parameters.ParameterConstants.ROOT_CONCEPT_ID;
import static org.adrianwalker.terminology.sctbrowser.parameters.ParameterConstants.SYNONYM;
import static org.junit.Assert.*;

import org.junit.Test;

public final class BrowseParametersTest {

  @Test
  public void testConceptId() {

    BrowseParameters parameters = new BrowseParameters();

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

    BrowseParameters parameters = new BrowseParameters();

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

    BrowseParameters parameters = new BrowseParameters();

    Long expectedDescriptionTypeId = SYNONYM;
    Long descriptionTypeId = parameters.getDescriptionTypeId();
    assertEquals(expectedDescriptionTypeId, descriptionTypeId);

    expectedDescriptionTypeId = 1L;
    parameters.setDescriptionTypeId(expectedDescriptionTypeId);
    descriptionTypeId = parameters.getDescriptionTypeId();
    assertEquals(expectedDescriptionTypeId, descriptionTypeId);
  }

  @Test
  public void testRelationshipTypeId() {

    BrowseParameters parameters = new BrowseParameters();

    Long expectedRelationshipTypeId = IS_A;
    Long relationshipTypeId = parameters.getRelationshipTypeId();
    assertEquals(expectedRelationshipTypeId, relationshipTypeId);

    expectedRelationshipTypeId = 1L;
    parameters.setRelationshipTypeId(expectedRelationshipTypeId);
    relationshipTypeId = parameters.getRelationshipTypeId();
    assertEquals(expectedRelationshipTypeId, relationshipTypeId);
  }

  @Test
  public void testConcept() {

    BrowseParameters parameters = new BrowseParameters();

    Boolean expectedConcept = false;
    Boolean concept = parameters.getConcept();
    assertEquals(expectedConcept, concept);

    expectedConcept = true;
    parameters.setConcept(expectedConcept);
    concept = parameters.getConcept();
    assertEquals(expectedConcept, concept);
  }

  @Test
  public void testChildren() {

    BrowseParameters parameters = new BrowseParameters();

    Boolean expectedChildren = true;
    Boolean children = parameters.getChildren();
    assertEquals(expectedChildren, children);

    expectedChildren = false;
    parameters.setChildren(expectedChildren);
    children = parameters.getChildren();
    assertEquals(expectedChildren, children);
  }

  @Test
  public void testToString() {

    BrowseParameters parameters = new BrowseParameters();

    String expectedToString = "conceptId = 138875005, "
            + "languageRefsetAcceptabilityId = 900000000000548007, "
            + "descriptionTypeId = 900000000000013009, "
            + "relationshipTypeId = 116680003, "
            + "concept = false, "
            + "children = true";
    String toString = parameters.toString();
    assertEquals(expectedToString, toString);
  }

  @Test
  public void testHashCode() {

    BrowseParameters parameters = new BrowseParameters();

    int expectedHashCode = 1841520117;
    int hashCode = parameters.hashCode();
    assertEquals(expectedHashCode, hashCode);
  }

  @Test
  public void testEquals() {

    BrowseParameters parameters1 = new BrowseParameters();
    assertTrue(parameters1.equals(parameters1));
    assertFalse(parameters1.equals(null));
    assertFalse(parameters1.equals(new Object()));

    BrowseParameters parameters2 = new BrowseParameters();
    assertTrue(parameters1.equals(parameters2));

    parameters2 = new BrowseParameters();
    parameters2.setConceptId(1L);
    assertFalse(parameters1.equals(parameters2));

    parameters2 = new BrowseParameters();
    parameters2.setLanguageRefsetAcceptabilityId(1L);
    assertFalse(parameters1.equals(parameters2));

    parameters2 = new BrowseParameters();
    parameters2.setDescriptionTypeId(1L);
    assertFalse(parameters1.equals(parameters2));

    parameters2 = new BrowseParameters();
    parameters2.setRelationshipTypeId(1L);
    assertFalse(parameters1.equals(parameters2));

    parameters2 = new BrowseParameters();
    parameters2.setConcept(true);
    assertFalse(parameters1.equals(parameters2));

    parameters2 = new BrowseParameters();
    parameters2.setChildren(false);
    assertFalse(parameters1.equals(parameters2));
  }
}
