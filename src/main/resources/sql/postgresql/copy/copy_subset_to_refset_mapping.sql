COPY subset_to_refset_mapping (original_subset_id, subset_name, concept_id, fsn, location)
FROM ? WITH CSV HEADER DELIMITER E'\t' QUOTE E'\b';