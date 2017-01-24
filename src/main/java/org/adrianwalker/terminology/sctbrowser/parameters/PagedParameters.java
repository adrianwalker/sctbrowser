package org.adrianwalker.terminology.sctbrowser.parameters;

import static org.adrianwalker.terminology.sctbrowser.parameters.ParameterConstants.DEFAULT_COUNT;
import static org.adrianwalker.terminology.sctbrowser.parameters.ParameterConstants.DEFAULT_LIMIT;
import static org.adrianwalker.terminology.sctbrowser.parameters.ParameterConstants.DEFAULT_OFFSET;

import javax.ws.rs.QueryParam;

public abstract class PagedParameters {

  @QueryParam("offset")
  private Integer offset;
  @QueryParam("limit")
  private Integer limit;
  @QueryParam("count")
  private Boolean count;

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
}
