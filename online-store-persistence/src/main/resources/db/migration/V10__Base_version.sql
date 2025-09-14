CREATE TABLE IF NOT EXISTS item
(
    id                 BIGINT       NOT NULL PRIMARY KEY,
    name               VARCHAR(250) NOT NULL,
    price              DECIMAL      NOT NULL,
    description        VARCHAR(200) NOT NULL,
    image_blob         BYTEA,
    image_content_type VARCHAR(64)
    );

ALTER SEQUENCE item_id_seq OWNED BY item.id;
