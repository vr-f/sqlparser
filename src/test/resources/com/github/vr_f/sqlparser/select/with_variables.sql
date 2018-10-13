SELECT category_id, entity_id
FROM (SELECT category_id,
             @ce :=
             (SELECT entity_id
              FROM category_entity cei
              WHERE cei.category_id = ced.category_id
                AND NOT FIND_IN_SET(entity_id, @r)
              ORDER BY RAND()
              LIMIT 1) AS entity_id,
             (SELECT @r := CAST(CONCAT_WS(',', @r, @ce) AS CHAR))
      FROM (SELECT @r := '') vars,
           (SELECT DISTINCT category_id FROM category_entity ORDER BY RAND() LIMIT 15) ced) q
WHERE entity_id IS NOT NULL
LIMIT 6;