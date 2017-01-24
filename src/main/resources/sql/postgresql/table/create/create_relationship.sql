CREATE TABLE IF NOT EXISTS relationship
(
  id bigint NOT NULL,
  effective_time date NOT NULL,
  active boolean NOT NULL,
  module_id bigint NOT NULL,
  source_id bigint NOT NULL,
  destination_id bigint NOT NULL,
  relationship_group bigint NOT NULL,
  type_id bigint NOT NULL,
  characteristic_type_id bigint NOT NULL,
  modifier_id bigint NOT NULL
);