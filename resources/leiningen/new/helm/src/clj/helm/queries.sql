-- :name user-create-table! :!
CREATE TABLE "user" (
  "id" SERIAL PRIMARY KEY,
  "name" TEXT NOT NULL,
  "email" TEXT NOT NULL UNIQUE
)

-- :name user-all
SELECT * FROM "user" ORDER BY "name";

-- :name user-by-id :? :1
SELECT * FROM "user" WHERE id = :id

-- :name user-exists? :? :1
SELECT * FROM "user" WHERE email = :email

-- :name user-create<! :i!
INSERT INTO "user" (
  "name",
  "email"
  ) VALUES (
  :name,
  :email
)

--:name user-update! :!
UPDATE "user" SET
  "name" = :name,
  "email" = :email
WHERE id = :id

-- :name user-delete! :!
DELETE FROM "user" WHERE id = :id
