USE worldvision_sql;

CREATE TABLE users (
	id VARCHAR(128) NOT NULL PRIMARY KEY
);

CREATE TABLE images (
	url VARCHAR(512) NOT NULL PRIMARY KEY,
    user_id VARCHAR(128) NOT NULL,
    downloads_count INT DEFAULT 0,
    creation_time DATETIME NOT NULL,
    CONSTRAINT FK_images_user_id FOREIGN KEY (user_id) REFERENCES users(id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE downloads (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	image_url VARCHAR(512) NOT NULL,
    user_id VARCHAR(128) NOT NULL,
    creation_time DATETIME NOT NULL,
    CONSTRAINT FK_downloads_image_url FOREIGN KEY (image_url) REFERENCES images(url) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT FK_downloads_user_id FOREIGN KEY (user_id) REFERENCES users(id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE tags (
    tag VARCHAR(128) NOT NULL PRIMARY KEY
);

create table image_tags (
	image_url VARCHAR(512) NOT NULL,
    tag VARCHAR(128) NOT NULL,
    CONSTRAINT PK_image_tags PRIMARY KEY (image_url, tag),
    CONSTRAINT FK_image_tags_image_url FOREIGN KEY (image_url) REFERENCES images(url) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT FK_image_tags_tag FOREIGN KEY (tag) REFERENCES tags(tag) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE colors (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    red INT DEFAULT 0,
    green INT DEFAULT 0,
    blue INT DEFAULT 0
);

CREATE TABLE image_colors (
	image_url VARCHAR(512) NOT NULL,
    color_id INT NOT NULL,
    score DOUBLE NOT NULL,
    CONSTRAINT PK_image_colors PRIMARY KEY (image_url, color_id),
    CONSTRAINT FK_image_colors_image_url FOREIGN KEY (image_url) REFERENCES images(url) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT FK_image_colors_color_id FOREIGN KEY (color_id) REFERENCES colors(id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TRIGGER inc_downloads_count BEFORE INSERT ON downloads FOR EACH ROW
    UPDATE images SET images.downloads_count = images.downloads_count + 1
    WHERE images.url = new.image_url;
