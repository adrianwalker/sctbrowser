SELECT
  q.id AS key,
  q.term AS title,
  q.children AS lazy,
  q.children AS folder
FROM (
  SELECT DISTINCT
    c.id AS id,
    d.term AS term,
    EXISTS (
      SELECT r.id
      FROM relationship r
      WHERE r.active = true      
      AND r.destination_id = c.id
      AND r.type_id = ?
    ) AS children
  FROM concept c
  INNER JOIN description d ON d.concept_id = c.id
  INNER JOIN relationship r ON r.source_id = c.id
  INNER JOIN language_refset lr ON lr.referenced_component_id = d.id
  WHERE c.active = ANY(ARRAY[true, false])
  AND d.active = true
  AND d.type_id = ?
  AND r.active = true
  AND r.destination_id = ?
  AND r.type_id = ?
  AND lr.active = true
  AND lr.acceptability_id = ?
  ORDER BY d.term
) AS q;