CREATE INDEX simple_map_refset_active_referenced_component_id_map_target_refset_id
ON simple_map_refset
USING btree (active, referenced_component_id, map_target, refset_id);

