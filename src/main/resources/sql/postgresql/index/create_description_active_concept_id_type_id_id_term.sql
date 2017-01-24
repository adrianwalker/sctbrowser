CREATE INDEX description_active_concept_id_type_id_id_term
ON description
USING btree (active, concept_id, type_id, id, term ASC);