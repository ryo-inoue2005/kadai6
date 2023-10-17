SELECT zip_code, prefecture, city, address
FROM zipcode
WHERE CONCAT(prefecture, city, address)
LIKE /*fullAddress*/'S' || '%'