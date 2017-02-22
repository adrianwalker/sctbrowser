UPDATE description 
SET search_text = to_tsvector('simple', concept_id::varchar || ' '|| term);