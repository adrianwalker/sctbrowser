CREATE TABLE IF NOT EXISTS extended_map_refset
(
  id bytea NOT NULL,
  effective_time date NOT NULL,
  active boolean NOT NULL,
  module_id bigint NOT NULL,
  refset_id bigint NOT NULL,
  referenced_component_id bigint NOT NULL,
  map_group int NOT NULL,
  map_priority int NOT NULL,
  map_rule text NULL,
  map_advice text NULL,
  map_target text NULL,
  correlation_id bigint NOT NULL,
  map_category_id bigint NOT NULL
);