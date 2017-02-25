UPDATE extended_map_refset 
SET search_text = to_tsvector('simple', map_target);