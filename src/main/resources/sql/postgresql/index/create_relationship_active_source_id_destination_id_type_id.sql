CREATE INDEX relationship_active_source_id_destination_id_type_id
ON relationship
USING btree (active, source_id, destination_id, type_id);