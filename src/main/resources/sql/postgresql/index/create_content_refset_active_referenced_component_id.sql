CREATE INDEX content_refset_active_referenced_component_id
ON content_refset
USING btree (active, referenced_component_id);