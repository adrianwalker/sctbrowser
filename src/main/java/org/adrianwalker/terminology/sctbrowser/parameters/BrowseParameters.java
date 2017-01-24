package org.adrianwalker.terminology.sctbrowser.parameters;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import static org.adrianwalker.terminology.sctbrowser.parameters.ParameterConstants.IS_A;
import static org.adrianwalker.terminology.sctbrowser.parameters.ParameterConstants.PREFERRED;
import static org.adrianwalker.terminology.sctbrowser.parameters.ParameterConstants.ROOT_CONCEPT_ID;
import static org.adrianwalker.terminology.sctbrowser.parameters.ParameterConstants.SYNONYM;

import java.io.Serializable;
import java.util.Objects;
import javax.ws.rs.QueryParam;

public final class BrowseParameters implements Serializable {

  @QueryParam("conceptId")
  private Long conceptId;
  @QueryParam("languageRefsetAcceptabilityId")
  private Long languageRefsetAcceptabilityId;
  @QueryParam("descriptionTypeId")
  private Long descriptionTypeId;
  @QueryParam("relationshipTypeId")
  private Long relationshipTypeId;
  @QueryParam("concept")
  private Boolean concept;
  @QueryParam("children")
  private Boolean children;

  public Long getConceptId() {

    if (null == conceptId) {
      conceptId = ROOT_CONCEPT_ID;
    }

    return conceptId;
  }

  public void setConceptId(final Long conceptId) {
    this.conceptId = conceptId;
  }

  public Long getLanguageRefsetAcceptabilityId() {

    if (null == languageRefsetAcceptabilityId) {
      languageRefsetAcceptabilityId = PREFERRED;
    }

    return languageRefsetAcceptabilityId;
  }

  public void setLanguageRefsetAcceptabilityId(final Long languageRefsetAcceptabilityId) {
    this.languageRefsetAcceptabilityId = languageRefsetAcceptabilityId;
  }

  public Long getDescriptionTypeId() {

    if (null == descriptionTypeId) {
      descriptionTypeId = SYNONYM;
    }

    return descriptionTypeId;
  }

  public void setDescriptionTypeId(final Long descriptionTypeId) {
    this.descriptionTypeId = descriptionTypeId;
  }

  public Long getRelationshipTypeId() {

    if (null == relationshipTypeId) {
      relationshipTypeId = IS_A;
    }

    return relationshipTypeId;
  }

  public void setRelationshipTypeId(final Long relationshipTypeId) {
    this.relationshipTypeId = relationshipTypeId;
  }

  public Boolean getConcept() {

    if (null == concept) {
      concept = FALSE;
    }

    return concept;
  }

  public void setConcept(final Boolean concept) {
    this.concept = concept;
  }

  public Boolean getChildren() {

    if (null == children) {
      children = TRUE;
    }

    return children;
  }

  public void setChildren(final Boolean children) {
    this.children = children;
  }

  @Override
  public String toString() {
    return "conceptId = " + getConceptId()
            + ", languageRefsetAcceptabilityId = " + getLanguageRefsetAcceptabilityId()
            + ", descriptionTypeId = " + getDescriptionTypeId()
            + ", relationshipTypeId = " + getRelationshipTypeId()
            + ", concept = " + getConcept()
            + ", children = " + getChildren();
  }

  @Override
  public int hashCode() {

    int hash = 3;
    hash = 31 * hash + Objects.hashCode(getConceptId());
    hash = 31 * hash + Objects.hashCode(getLanguageRefsetAcceptabilityId());
    hash = 31 * hash + Objects.hashCode(getDescriptionTypeId());
    hash = 31 * hash + Objects.hashCode(getRelationshipTypeId());
    hash = 31 * hash + Objects.hashCode(getConcept());
    hash = 31 * hash + Objects.hashCode(getChildren());

    return hash;
  }

  @Override
  public boolean equals(final Object obj) {

    if (this == obj) {
      return true;
    }

    if (obj == null) {
      return false;
    }

    if (getClass() != obj.getClass()) {
      return false;
    }

    final BrowseParameters other = (BrowseParameters) obj;

    if (!Objects.equals(getConceptId(), other.getConceptId())) {
      return false;
    }

    if (!Objects.equals(getLanguageRefsetAcceptabilityId(),
            other.getLanguageRefsetAcceptabilityId())) {
      return false;
    }

    if (!Objects.equals(getDescriptionTypeId(), other.getDescriptionTypeId())) {
      return false;
    }

    if (!Objects.equals(getRelationshipTypeId(), other.getRelationshipTypeId())) {
      return false;
    }

    if (!Objects.equals(getConcept(), other.getConcept())) {
      return false;
    }

    if (!Objects.equals(getChildren(), other.getChildren())) {
      return false;
    }

    return true;
  }
}
