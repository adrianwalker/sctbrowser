SET @I = 0;
SET @MAX = 50000;
SET @COUNT = SELECT COUNT(*) FROM concept;
SET @COUNT = CAST (@COUNT AS INTEGER);

WHILE @I <= (@COUNT / @MAX)
BEGIN
   SET @FILENAME = '/var/tmp/sitemap-@I.txt';
   COPY (SELECT 'http://sctbrowser.uk/?id=' || id FROM concept ORDER BY id OFFSET (@I * @MAX) LIMIT @MAX) TO '@FILENAME';
   SET @I = @I + 1;
END
