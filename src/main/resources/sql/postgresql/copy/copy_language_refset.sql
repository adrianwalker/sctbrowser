COPY language_refset (id, effective_time, active, module_id, refset_id, referenced_component_id, acceptability_id)
FROM ? WITH CSV HEADER DELIMITER E'\t' QUOTE E'\b';