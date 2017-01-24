SELECT DISTINCT
  d1.term AS relationship_term,
  c2.id AS concept_id,
  d2.term AS concept_term
FROM concept c1
INNER JOIN relationship r ON r.source_id = c1.id
INNER JOIN concept c2 ON c2.id = r.destination_id
INNER JOIN description d1 ON d1.concept_id = r.type_id
INNER JOIN description d2 ON d2.concept_id = c2.id
INNER JOIN language_refset lr1 ON lr1.referenced_component_id = d1.id
INNER JOIN language_refset lr2 ON lr2.referenced_component_id = d2.id
WHERE c1.active = true
AND c1.id = ?
AND r.active = true
AND c2.active = true
AND d1.active = true
AND d1.type_id = ?
AND d2.type_id = ?
AND d2.active = true
AND lr1.active = true
AND lr1.acceptability_id = ?
AND lr2.active = true
AND lr2.acceptability_id = ?
ORDER BY d1.term, d2.term;