SELECT `desc`, UI.desc, UI.a_id, UI.uid, UI.title
FROM users AS UI
WHERE UI.status = 1
  AND (
          UI.name = 'john' OR
          UI.name like 'john;%' OR
          UI.name like '%;john;%' OR
          UI.name like '%;john'
          )