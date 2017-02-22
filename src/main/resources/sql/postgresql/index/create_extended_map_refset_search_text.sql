CREATE INDEX simple_map_refset_search_text
ON simple_map_refset
USING gin (search_text);