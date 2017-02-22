UPDATE simple_map_refset 
SET search_text = to_tsvector('simple', referenced_component_id::varchar || ' ' || map_target);