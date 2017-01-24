CREATE INDEX description_search_text
ON description
USING gin (search_text);