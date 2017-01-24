CREATE TABLE IF NOT EXISTS subset_to_refset_mapping
(
  original_subset_id bigint NOT NULL,
  subset_name text NOT NULL,
  concept_id bigint NOT NULL,
  fsn text NOT NULL,
  location text NOT NULL
);