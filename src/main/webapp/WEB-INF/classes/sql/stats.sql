
-- 半年間の運勢毎の割合を取得するSQL
SELECT u.unsei_name, ROUND(CAST(CAST(COUNT(r.omikuji_code) as float) / 
	(SELECT COUNT(omikuji_code) FROM result) * 100 as numeric),1) as ratio
FROM unseimaster AS u
LEFT JOIN omikujibox AS o ON u.unsei_code = o.unsei_code
LEFT JOIN result AS r ON o.omikuji_code = r.omikuji_code
AND r.create_date BETWEEN /*halfYear*/ AND CURRENT_DATE
GROUP BY u.unsei_name
ORDER BY RATIO DESC