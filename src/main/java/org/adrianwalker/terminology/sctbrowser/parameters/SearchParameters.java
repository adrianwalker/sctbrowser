package org.adrianwalker.terminology.sctbrowser.parameters;

import static org.adrianwalker.terminology.sctbrowser.parameters.ParameterConstants.EMPTY_STRING;
import static org.adrianwalker.terminology.sctbrowser.rest.RestConstants.TERMS_PARAM;

import java.io.Serializable;
import java.util.Objects;
import javax.ws.rs.QueryParam;
import static org.adrianwalker.terminology.sctbrowser.parameters.ParameterConstants.DEFAULT_COUNT;
import static org.adrianwalker.terminology.sctbrowser.parameters.ParameterConstants.DEFAULT_LIMIT;
import static org.adrianwalker.terminology.sctbrowser.parameters.ParameterConstants.DEFAULT_OFFSET;
import static org.adrianwalker.terminology.sctbrowser.rest.RestConstants.COUNT_PARAM;
import static org.adrianwalker.terminology.sctbrowser.rest.RestConstants.LIMIT_PARAM;
import static org.adrianwalker.terminology.sctbrowser.rest.RestConstants.OFFSET_PARAM;

public final class SearchParameters implements Serializable {

  @QueryParam(TERMS_PARAM)
  private String terms;
  @QueryParam(OFFSET_PARAM)
  private Integer offset;
  @QueryParam(LIMIT_PARAM)
  private Integer limit;
  @QueryParam(COUNT_PARAM)
  private Boolean count;

  public String getTerms() {

    if (null == terms) {
      terms = EMPTY_STRING;
    }

    return terms;
  }

  public void setTerms(final String terms) {
    this.terms = terms;
  }

  public final Integer getOffset() {

    if (null == offset) {
      offset = DEFAULT_OFFSET;
    }

    return offset;
  }

  public final void setOffset(final Integer offset) {
    this.offset = offset;
  }

  public final Integer getLimit() {

    if (null == limit) {
      limit = DEFAULT_LIMIT;
    }

    return limit;
  }

  public final void setLimit(final Integer limit) {
    this.limit = limit;
  }

  public final Boolean getCount() {

    if (null == count) {
      count = DEFAULT_COUNT;
    }

    return count;
  }

  public final void setCount(final Boolean count) {
    this.count = count;
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

    if (!Objects.equals(getTerms(), other.getTerms())) {
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
