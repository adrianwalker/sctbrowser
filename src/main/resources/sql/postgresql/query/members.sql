SELECT DISTINCT
  c.id AS id,
  d.term AS term
FROM concept c
INNER JOIN (
  SELECT refset_id, referenced_component_id
  FROM content_refset cr
  WHERE cr.active = true
  UNION ALL
  SELECT refset_id, referenced_component_id
  FROM content_component_refset ccr
  WHERE ccr.active = true
) AS cr ON cr.referenced_component_id = c.id
INNER JOIN description d ON d.concept_id = c.id
INNER JOIN language_refset lr ON lr.referenced_component_id = d.id
WHERE c.active = ANY(ARRAY[true, false])
AND cr.refset_id = ?
AND d.active = true
AND d.type_id = ?
AND lr.active = true
AND lr.acceptability_id = ?
ORDER BY d.term
OFFSET ?
LIMIT ?;