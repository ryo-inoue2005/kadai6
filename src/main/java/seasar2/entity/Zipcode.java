package seasar2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Zipcodeクラス. <br>
 * Zipcodeクラスは、zipcodeテーブルのエンティティです。
 *
 * @author Ryo.inoue
 * @version 1.00
 */
@Entity
public class Zipcode {

	/** JISコードを表します */
	@Column(name = "jis_code")
	public String jisCode;
	
	/** 旧郵便番号を表します */
	@Column(name = "old_zip_code")
	public String oldZipCode;
	
	/** 郵便番号を表します */
	@Column(name = "zip_code")
	public String zipCode;
	
	/** 都道府県フリガナを表します */
	@Column(name = "kana_prefecture")
	public String kanaPrefecture;
	
	/** 市区町村フリガナを表します */
	@Column(name = "kana_city")
	public String kanaCity;
	
	/** その他住所フリガナを表します */
	@Column(name = "kana_address")
	public String kanaAddress;
	
	/** 都道府県を表します */
	public String prefecture;
	
	/** 市区町村を表します */
	public String city;
	
	/** その他住所を表します */
	public String address;
	
	/** 補助情報1を表します */
	public Short aux1;
	
	/** 補助情報2を表します */
	public Short aux2;
	
	/** 補助情報3を表します */
	public Short aux3;
	
	/** 補助情報4を表します */
	public Short aux4;
	
	/** 補助情報5を表します */
	public Short aux5;
	
	/** 補助情報6を表します */
	public Short aux6;

}
