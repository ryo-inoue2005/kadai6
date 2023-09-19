--追加課題のテーブルです


-- 郵便番号データ保存するテーブル(日本郵便：https://www.post.japanpost.jp/zipcode/dl/kogaki-zip.html) (KEN_ALL.zip)

BEGIN;

CREATE TABLE zipcode (
			JIS_Code VARCHAR(5),
			Old_Zip_Code CHAR(5),
			Zip_Code CHAR(7),
			Kana_Prefecture VARCHAR(15),
			Kana_City VARCHAR(31),
			Kana_Address VARCHAR(255),
			Prefecture VARCHAR(15),
			City VARCHAR(31),
			Address VARCHAR(255),
			AUX1 SMALLINT,
			AUX2 SMALLINT,
			AUX3 SMALLINT,
			AUX4 SMALLINT,
			AUX5 SMALLINT,
			AUX6 SMALLINT
);

COMMIT;

-- 郵送の際、顧客情報を保存するテーブル

BEGIN;

CREATE TABLE CUSTOMER_INFO (

	Customer_ID	INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	Omikuji_Code	INTEGER		NOT NULL,
	Order_Date	DATE		NOT NULL,
	Last_name	VARCHAR(255)	NOT NULL,
	First_Name	VARCHAR(255)	NOT NULL,
	Zip_Code	VARCHAR(10)	NOT NULL,
	Prefecture	VARCHAR(15)	NOT NULL,
	City		VARCHAR(31)	NOT NULL,
	Address		VARCHAR(255)	NOT NULL,

	CONSTRAINT FK_Omikuji_Code FOREIGN KEY(Omikuji_Code) REFERENCES	OMIKUJIBOX(Omikuji_Code)
);

COMMIT;