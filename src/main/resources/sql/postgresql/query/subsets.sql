SELECT
  strm.original_subset_id AS subset_id,
  strm.subset_name AS subset_term,
  strm.concept_id AS refset_id
FROM subset_to_refset_mapping strm
INNER JOIN concept c ON c.id = strm.concept_id
WHERE c.active = ANY(ARRAY[true, false])
ORDER BY strm.subset_name;
