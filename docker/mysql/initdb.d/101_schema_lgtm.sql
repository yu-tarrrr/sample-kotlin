CREATE TABLE lgtm(
    image_url VARCHAR(256) NOT NULL,
    image_name VARCHAR(10),
    image_lgtm_url VARCHAR(256),
    update_datetime DATE NOT NULL,
    PRIMARY KEY(image_url)
);
