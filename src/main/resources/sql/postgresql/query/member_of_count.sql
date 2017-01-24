SELECT COUNT (DISTINCT (
  c.id,
  d.term))
FROM concept c
INNER JOIN (
  SELECT active, refset_id, referenced_component_id
  FROM content_refset cr
  UNION ALL
  SELECT active, refset_id, referenced_component_id
  FROM content_component_refset ccr
) AS cr ON c.id = cr.referenced_component_id
INNER JOIN description d ON d.concept_id = cr.refset_id
INNER JOIN language_refset lr ON lr.referenced_component_id = d.id
WHERE c.active = ANY(ARRAY[true, false])
AND c.id = ?
AND cr.active = true
AND d.active = true
AND d.type_id = ?
AND lr.active = true
AND lr.acceptability_id = ?;