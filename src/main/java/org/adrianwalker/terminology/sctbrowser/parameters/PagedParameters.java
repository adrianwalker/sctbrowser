package org.adrianwalker.terminology.sctbrowser.parameters;

import static org.adrianwalker.terminology.sctbrowser.parameters.ParameterConstants.DEFAULT_COUNT;
import static org.adrianwalker.terminology.sctbrowser.parameters.ParameterConstants.DEFAULT_LIMIT;
import static org.adrianwalker.terminology.sctbrowser.parameters.ParameterConstants.DEFAULT_OFFSET;
import static org.adrianwalker.terminology.sctbrowser.rest.RestConstants.COUNT_PARAM;
import static org.adrianwalker.terminology.sctbrowser.rest.RestConstants.LIMIT_PARAM;
import static org.adrianwalker.terminology.sctbrowser.rest.RestConstants.OFFSET_PARAM;

import javax.ws.rs.QueryParam;

public abstract class PagedParameters {

  @QueryParam(OFFSET_PARAM)
  private Integer offset;
  @QueryParam(LIMIT_PARAM)
  private Integer limit;
  @QueryParam(COUNT_PARAM)
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
