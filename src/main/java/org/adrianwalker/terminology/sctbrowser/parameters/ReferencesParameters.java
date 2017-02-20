package org.adrianwalker.terminology.sctbrowser.parameters;

import java.io.Serializable;
import java.util.Objects;

public final class ReferencesParameters extends BaseParameters implements Serializable {

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

    int hash = 11;
    hash = 117 * hash + Objects.hashCode(getConceptId());
    hash = 117 * hash + Objects.hashCode(getLanguageRefsetAcceptabilityId());
    hash = 117 * hash + Objects.hashCode(getDescriptionTypeId());
    hash = 117 * hash + Objects.hashCode(getOffset());
    hash = 117 * hash + Objects.hashCode(getLimit());
    hash = 117 * hash + Objects.hashCode(getCount());

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

    final ReferencesParameters other = (ReferencesParameters) obj;

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
