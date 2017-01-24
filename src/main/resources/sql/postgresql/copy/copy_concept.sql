COPY concept (id, effective_time, active, module_id, definition_status_id) 
FROM ? WITH CSV HEADER DELIMITER E'\t' QUOTE E'\b';