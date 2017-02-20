package org.adrianwalker.terminology.sctbrowser.parameters;

import static org.adrianwalker.terminology.sctbrowser.parameters.ParameterConstants.PREFERRED;
import static org.adrianwalker.terminology.sctbrowser.parameters.ParameterConstants.ROOT_CONCEPT_ID;
import static org.adrianwalker.terminology.sctbrowser.parameters.ParameterConstants.SYNONYM;
import static org.adrianwalker.terminology.sctbrowser.rest.RestConstants.CONCEPT_ID_PARAM;
import static org.adrianwalker.terminology.sctbrowser.rest.RestConstants.DESCRIPTION_TYPE_ID_PARAM;
import static org.adrianwalker.terminology.sctbrowser.rest.RestConstants.LANGUAGE_REFSET_ACCEPTABILITY_ID_PARAM;

import javax.ws.rs.QueryParam;

public abstract class BaseParameters extends PagedParameters {

  @QueryParam(CONCEPT_ID_PARAM)
  private Long conceptId;
  @QueryParam(LANGUAGE_REFSET_ACCEPTABILITY_ID_PARAM)
  private Long languageRefsetAcceptabilityId;
  @QueryParam(DESCRIPTION_TYPE_ID_PARAM)
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
}
