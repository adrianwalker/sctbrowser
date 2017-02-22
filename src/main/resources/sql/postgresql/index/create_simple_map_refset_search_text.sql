CREATE INDEX extended_map_refset_search_text
ON extended_map_refset
USING gin (search_text);