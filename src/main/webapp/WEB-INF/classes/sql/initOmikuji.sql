
-- Fortune.CSVファイルのおみくじ情報をデータベースに保存するSQL
INSERT INTO omikujibox (omikuji_code, unsei_code, negaigoto, akinai, gakumon, create_by, create_date) 
	SELECT /*omikujiCode*/,
		(
		SELECT unsei_code 
		FROM unseimaster
		WHERE unsei_name = /*unsei*/),/*negaigoto*/, /*akinai*/, /*gakumon*/, 'Ryo.inoue', 
		CURRENT_DATE ON CONFLICT DO NOTHING