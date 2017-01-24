COPY description (id, effective_time, active, module_id, concept_id, language_code, type_id, term, case_significance_id) 
FROM ? WITH CSV HEADER DELIMITER E'\t' QUOTE E'\b'