package org.adrianwalker.terminology.sctbrowser.parameters;

import static org.adrianwalker.terminology.sctbrowser.parameters.ParameterConstants.EMPTY_STRING;

import java.io.Serializable;
import java.util.Objects;
import javax.ws.rs.QueryParam;

public final class SearchParameters extends PagedParameters implements Serializable {

  @QueryParam("terms")
  private String terms;

  public String getTerms() {

    if (null == terms) {
      terms = EMPTY_STRING;
    }

    return terms;
  }

  public void setTerms(final String terms) {
    this.terms = terms;
  }

  @Override
  public String toString() {
    return "terms = " + getTerms()
            + ", offset = " + getOffset()
            + ", limit = " + getLimit()
            + ", count = " + getCount();
  }

  @Override
  public int hashCode() {

    int hash = 5;
    hash = 53 * hash + Objects.hashCode(getTerms());
    hash = 53 * hash + Objects.hashCode(getOffset());
    hash = 53 * hash + Objects.hashCode(getLimit());
    hash = 53 * hash + Objects.hashCode(getCount());

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

    final SearchParameters other = (SearchParameters) obj;

    if (!Objects.equals(this.terms, other.terms)) {
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
