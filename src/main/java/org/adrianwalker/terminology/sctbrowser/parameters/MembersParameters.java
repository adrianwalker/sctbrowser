package org.adrianwalker.terminology.sctbrowser.parameters;

import static org.adrianwalker.terminology.sctbrowser.parameters.ParameterConstants.PREFERRED;
import static org.adrianwalker.terminology.sctbrowser.parameters.ParameterConstants.ROOT_CONCEPT_ID;
import static org.adrianwalker.terminology.sctbrowser.parameters.ParameterConstants.SYNONYM;

import java.io.Serializable;
import java.util.Objects;
import javax.ws.rs.QueryParam;

public final class MembersParameters extends PagedParameters implements Serializable {

  @QueryParam("conceptId")
  private Long conceptId;
  @QueryParam("languageRefsetAcceptabilityId")
  private Long languageRefsetAcceptabilityId;
  @QueryParam("descriptionTypeId")
  private Long descriptionTypeId;

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

  @Override
  public String toString() {
    return "conceptId = " + getConceptId()
            + ", languageRefsetAcceptabilityId = " + getLanguageRefsetAcceptabilityId()
            + ", descriptionTypeId = " + getDescriptionTypeId()
            + ", offset = " + getOffset()
            + ", limit = " + getLimit()
            + ", count = " + getCount();
  }

  @Override
  public int hashCode() {

    int hash = 7;
    hash = 71 * hash + Objects.hashCode(getConceptId());
    hash = 71 * hash + Objects.hashCode(getLanguageRefsetAcceptabilityId());
    hash = 71 * hash + Objects.hashCode(getDescriptionTypeId());
    hash = 71 * hash + Objects.hashCode(getOffset());
    hash = 71 * hash + Objects.hashCode(getLimit());
    hash = 71 * hash + Objects.hashCode(getCount());

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

    final MembersParameters other = (MembersParameters) obj;

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

    if (!Objects.equals(getOffset(), other.getOffset())) {
      return false;
    }

    if (!Objects.equals(getLimit(), other.getLimit())) {
      return false;
    }

    if (!Objects.equals(getCount(), other.getCount())) {
      return false;
    }

    return true;
  }
}
