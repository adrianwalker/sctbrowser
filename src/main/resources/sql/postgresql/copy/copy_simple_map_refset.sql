COPY simple_map_refset (id, effective_time, active, module_id, refset_id, referenced_component_id, map_target)
FROM ? WITH CSV HEADER DELIMITER E'\t' QUOTE E'\b';