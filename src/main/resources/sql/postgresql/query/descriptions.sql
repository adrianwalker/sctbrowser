SELECT DISTINCT
  d1.term AS concept_term,
  d2.term AS type_term,
  d3.term AS acceptability_term
FROM description d1
INNER JOIN description d2 ON d2.concept_id = d1.type_id
INNER JOIN language_refset lr1 ON lr1.referenced_component_id = d1.id
INNER JOIN description d3 ON d3.concept_id = lr1.acceptability_id
INNER JOIN language_refset lr2 ON lr2.referenced_component_id = d3.id
WHERE d1.active = true
AND d1.concept_id = ?
AND d2.active = true
AND d2.type_id = ?
AND lr1.active = true
AND d3.active = true
AND d3.type_id = ?
AND lr2.active = true
AND lr2.acceptability_id = ?
ORDER BY
d2.term ASC,
d3.term DESC,
d1.term ASC;