CREATE INDEX relationship_active_destination_id_source_id_type_id
ON relationship
USING btree (active, destination_id, source_id, type_id);