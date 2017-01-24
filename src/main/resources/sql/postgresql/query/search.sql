SELECT DISTINCT
  c.id AS id,
  d.term AS term,
  d.ts_rank_cd AS rank
FROM (
  SELECT
    id,
    concept_id,
    term,
    ts_rank_cd(search_text, query, 2)
  FROM
    description d,
    TO_TSQUERY('simple', ?) AS query
  WHERE d.search_text @@ query
  AND d.active = true
) AS d
INNER JOIN concept c ON c.id = d.concept_id
INNER JOIN language_refset lr ON lr.referenced_component_id = d.id
WHERE c.active = ANY(ARRAY[true, false])
AND lr.active = true
ORDER BY
  d.ts_rank_cd DESC,
  d.term ASC,
  c.id ASC
OFFSET ?
LIMIT ?;