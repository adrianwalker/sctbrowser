WITH RECURSIVE paths AS (
  SELECT 
    c.id, 
    r.destination_id
  FROM concept c INNER JOIN relationship r ON r.source_id = c.id
  WHERE c.active = ANY(ARRAY[true, false])
  AND c.id = ?
  AND r.active = true
  AND r.type_id = ?
  UNION ALL
  SELECT 
    c.id, 
    r.destination_id
  FROM concept c INNER JOIN relationship r ON r.source_id = c.id
  INNER JOIN paths ON paths.destination_id = c.id
  WHERE c.active = ANY(ARRAY[true, false])
  AND r.active = true
  AND r.type_id = ?
) 
SELECT DISTINCT
  id AS id,
  destination_id AS parent_id
FROM paths
