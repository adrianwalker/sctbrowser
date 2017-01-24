CREATE INDEX concept_active_id 
ON concept 
USING btree (active, id);