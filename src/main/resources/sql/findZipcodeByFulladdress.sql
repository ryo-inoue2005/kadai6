SELECT zip_code, prefecture, city, address
FROM zipcode
WHERE zip_code IN (
	SELECT zip_code
	FROM search_zipcode 
	WHERE CONCAT(prefecture, city, address)
	LIKE /*fullAddress*/'S' || '%'
)