CREATE TABLE IF NOT EXISTS description
(
  id bigint NOT NULL,
  effective_time date NOT NULL,
  active boolean NOT NULL,
  module_id bigint NOT NULL,
  concept_id bigint NOT NULL,
  language_code text NOT NULL,
  type_id bigint NOT NULL,
  term text NOT NULL,
  case_significance_id bigint NOT NULL
);