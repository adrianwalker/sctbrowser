COPY extended_map_refset (id, effective_time, active, module_id, refset_id, referenced_component_id, map_group, map_priority, map_rule, map_advice, map_target, correlation_id, map_category_id)
FROM ? WITH CSV HEADER DELIMITER E'\t' QUOTE E'\b';
