/*********************** users ***********************/

DELIMITER //
CREATE PROCEDURE CreateUser (userId VARCHAR(128))
BEGIN
	INSERT INTO users (id) 
    VALUES (userId);
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE SelectUserCountById (userId VARCHAR(128))
BEGIN
	SELECT COUNT(id) 
    FROM users
    WHERE id = userId;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE SelectUsersDownloadedImage (imageUrl VARCHAR(512))
BEGIN
	SELECT DISTINCT user_id
	FROM downloads
	WHERE image_url = imageUrl; 
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE DeleteUser (userId VARCHAR(128))
BEGIN
	DELETE FROM users
    WHERE id = userId;
END //
DELIMITER ;

/*********************** images ***********************/

DELIMITER //
CREATE PROCEDURE CreateImage (imageUrl VARCHAR(512), userId VARCHAR(128))
BEGIN
	INSERT INTO images (url, user_id, creation_time) 
    VALUES (imageUrl, userId, CURRENT_TIME()); 
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE SelectImageByUser (userId VARCHAR(128))
BEGIN
	SELECT images.url, images.user_id, images.downloads_count, images.creation_time,
           GROUP_CONCAT(image_tags.tag) as "tags" 
    FROM ( images
    INNER JOIN image_tags ON images.url = image_tags.image_url )
	WHERE images.user_id = userId
    GROUP BY images.url
    ORDER BY images.creation_time DESC;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE SelectImageTop ()
BEGIN
	SELECT images.url, images.user_id, images.downloads_count, images.creation_time,
		   GROUP_CONCAT(image_tags.tag) as "tags" 
	FROM ( images
	INNER JOIN image_tags ON images.url = image_tags.image_url )
	GROUP BY images.url
	ORDER BY downloads_count DESC;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE SelectImageByTag (tagLabel VARCHAR(128))
BEGIN
	SELECT * 
	FROM (SELECT images.url, images.user_id, images.downloads_count, images.creation_time,
				 GROUP_CONCAT(image_tags.tag) as "tags" 
		  FROM ( images
		  INNER JOIN image_tags ON images.url = image_tags.image_url )
		  GROUP BY images.url) AS all_images
	WHERE tags LIKE CONCAT("%", tagLabel, "%");
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE SelectImageByColor (colorId INT)
BEGIN
	SELECT images.url, images.user_id, images.downloads_count, images.creation_time,
		   GROUP_CONCAT(image_tags.tag) as "tags" 
	FROM (( images
	INNER JOIN image_tags ON images.url = image_tags.image_url )
	INNER JOIN image_colors ON images.url = image_colors.image_url)
	WHERE image_colors.color_id = colorId
	GROUP BY images.url
	ORDER BY image_colors.score DESC;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE SelectImageByCreationTime (daysPassed INT)
BEGIN
	SELECT images.url, images.user_id, images.downloads_count, images.creation_time,
		   GROUP_CONCAT(image_tags.tag) as "tags" 
	FROM ( images
	INNER JOIN image_tags ON images.url = image_tags.image_url )
	WHERE DATEDIFF(CURRENT_TIME(), images.creation_time) <= daysPassed
	GROUP BY images.url
	ORDER BY images.creation_time DESC;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE DeleteImage (imageUrl VARCHAR(512))
BEGIN
	DELETE FROM images 
    WHERE url = imageUrl;
END //
DELIMITER ;

/*********************** downloads ***********************/

DELIMITER //
CREATE PROCEDURE CreateDownload (imageUrl VARCHAR(512), userId VARCHAR(128))
BEGIN
	INSERT INTO downloads (image_url, user_id, creation_time)
    VALUES (imageUrl, userId, CURRENT_TIME());
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE SelectDownloadsCountByCreationTime (daysPassed INT)
BEGIN
	SELECT COUNT(id)
	FROM downloads
	WHERE DATEDIFF(CURRENT_TIME(), creation_time) <= daysPassed; 
END //
DELIMITER ;

/*********************** tags ***********************/

DELIMITER //
CREATE PROCEDURE CreateTag (tagLabel VARCHAR(128))
BEGIN
	INSERT INTO tags (tag) 
    VALUES (tagLabel); 
END //
DELIMITER ;

/*********************** image_tags ***********************/

DELIMITER //
CREATE PROCEDURE CreateImageTag (imageUrl VARCHAR(512), tagLabel VARCHAR(128))
BEGIN
	INSERT INTO image_tags (image_url, tag) 
    VALUES (imageUrl, tagLabel); 
END //
DELIMITER ;

/*********************** colors ***********************/

DELIMITER //
CREATE PROCEDURE SelectAllColors ()
BEGIN
	SELECT id, red, green, blue
    FROM colors; 
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE SelectColorById (colorId INT)
BEGIN
	SELECT red, green, blue
    FROM colors
    WHERE id = colorId; 
END //
DELIMITER ;

/*********************** image_colors ***********************/

DELIMITER //
CREATE PROCEDURE CreateImageColor (imageUrl VARCHAR(512), colorId INT, imageColorScore DOUBLE)
BEGIN
	INSERT INTO image_colors (image_url, color_id, score) 
    VALUES (imageUrl, colorId, imageColorScore); 
END //
DELIMITER ;

