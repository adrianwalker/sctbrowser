CREATE INDEX language_refset_active_referenced_component_id
ON language_refset
USING btree (active, referenced_component_id);