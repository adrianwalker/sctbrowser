SELECT DISTINCT
  mr.map_target, 
  mr.refset_id, 
  d.term
FROM concept c
INNER JOIN (
  SELECT smr.referenced_component_id, smr.map_target, smr.refset_id
  FROM simple_map_refset smr
  WHERE smr.active = true
  UNION ALL
  SELECT emr.referenced_component_id, emr.map_target, emr.refset_id
  FROM extended_map_refset emr
  WHERE emr.active = true
) mr ON mr.referenced_component_id = c.id
INNER JOIN description d ON d.concept_id = mr.refset_id
INNER JOIN language_refset lr ON lr.referenced_component_id = d.id
WHERE c.active = ANY(ARRAY[true, false])
AND c.id = ?
AND d.active = true
AND d.type_id = ?
AND lr.active = true
AND lr.acceptability_id = ?
ORDER BY d.term, mr.map_target