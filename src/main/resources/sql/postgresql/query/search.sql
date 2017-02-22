SELECT s.id, s.term, s.rank FROM (
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
	UNION ALL
	SELECT DISTINCT
	  c.id AS id,
	  d.term AS term,
	  smr.ts_rank_cd AS rank
	FROM (
	  SELECT
	    referenced_component_id,
	    map_target,
	    ts_rank_cd(search_text, query, 2)
	  FROM
	    simple_map_refset smr,
	    TO_TSQUERY('simple', ?) AS query
	  WHERE smr.search_text @@ query
	  AND smr.active = true
	) AS smr
	INNER JOIN concept c ON c.id = smr.referenced_component_id
	INNER JOIN description d ON d.concept_id = c.id
	INNER JOIN language_refset lr ON lr.referenced_component_id = d.id
	WHERE c.active = ANY(ARRAY[true, false])
	AND d.active = true
	AND lr.active = true
	UNION ALL
	SELECT DISTINCT
	  c.id AS id,
	  d.term AS term,
	  emr.ts_rank_cd AS rank
	FROM (
	  SELECT
	    referenced_component_id,
	    map_target,
	    ts_rank_cd(search_text, query, 2)
	  FROM
	    extended_map_refset emr,
	    TO_TSQUERY('simple', ?) AS query
	  WHERE emr.search_text @@ query
	  AND emr.active = true
	) AS emr
	INNER JOIN concept c ON c.id = emr.referenced_component_id
	INNER JOIN description d ON d.concept_id = c.id
	INNER JOIN language_refset lr ON lr.referenced_component_id = d.id
	WHERE c.active = ANY(ARRAY[true, false])
	AND d.active = true
	AND lr.active = true
) AS s
ORDER BY
  s.rank DESC,
  s.term ASC,
  s.id ASC
OFFSET ?
LIMIT ?;