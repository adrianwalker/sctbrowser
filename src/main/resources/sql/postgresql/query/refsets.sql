SELECT DISTINCT c.id, d.term
FROM concept c
INNER JOIN
(
    SELECT DISTINCT cr.refset_id
    FROM content_refset cr
    WHERE cr.active = true 
    UNION
    SELECT DISTINCT ccr.refset_id
    FROM content_component_refset ccr
    WHERE ccr.active = true
) AS cr ON cr.refset_id = c.id
INNER JOIN description d ON d.concept_id = c.id
INNER JOIN language_refset lr ON lr.referenced_component_id = d.id
WHERE c.active = ANY(ARRAY[true, false]) 
AND d.active = true
AND d.type_id = ?
AND lr.active = true
AND lr.acceptability_id = ?
ORDER BY d.term;
