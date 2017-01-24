CREATE TABLE IF NOT EXISTS simple_map_refset
(
  id bytea NOT NULL,
  effective_time date NOT NULL,
  active boolean NOT NULL,
  module_id bigint NOT NULL,
  refset_id bigint NOT NULL,
  referenced_component_id bigint NOT NULL,
  map_target text NOT NULL
);