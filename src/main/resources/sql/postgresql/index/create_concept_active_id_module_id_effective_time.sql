CREATE INDEX concept_active_id_module_id_effective_time
ON concept
USING btree (active, id, module_id, effective_time);