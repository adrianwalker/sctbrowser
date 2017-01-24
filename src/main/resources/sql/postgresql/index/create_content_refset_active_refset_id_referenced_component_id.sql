CREATE INDEX content_refset_active_refset_id_referenced_component_id
ON content_refset
USING btree (active, refset_id, referenced_component_id);