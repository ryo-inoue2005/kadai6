package seasar2.dto;

/**
 * ZipcodeDtoクラス. <br>
 * ZipcodeDtoクラスは、郵便番号と住所を格納するDTOです。
 *
 * @author Ryo.inoue
 * @version 1.00
 */
public class ZipcodeDto {

	/** 郵便番号を表します */
	public String zipCode;

	/** 市区町村を表します */
	public String prefecture;

	/** 都道府県を表します */
	public String city;

	/** 番地などを表します */
	public String address;

}
