SELECT DISTINCT
  c.id AS concept_id,
  c.module_id AS module_id,
  d.term AS module_term,
  c.effective_time AS effective_time,
  c.active AS active
FROM concept c
INNER JOIN description d  ON d.concept_id = c.module_id
INNER JOIN language_refset lr ON lr.referenced_component_id = d.id
WHERE c.active = ANY(ARRAY[true, false])
AND c.id = ?
AND d.active = true
AND d.type_id = ?
AND lr.active = true
AND lr.acceptability_id = ?;