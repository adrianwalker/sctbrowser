CREATE TABLE IF NOT EXISTS concept
(
  id bigint NOT NULL,
  effective_time date NOT NULL,
  active boolean NOT NULL,
  module_id bigint NOT NULL,
  definition_status_id bigint NOT NULL
);