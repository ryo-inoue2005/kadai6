package seasar2.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * ResultAddressクラス. <br>
 * ResultAddressクラスは、データベースから取得した住所を格納します。
 *
 * @author Ryo.inoue
 * @version 1.00
 */
public class ResultAddress {

	/** コンストラクタ */
	public ResultAddress() {
		results = new ArrayList<>();
	}

	/** メッセージを表します */
	private String message;

	/** 住所を格納するリストを表します */
	private List<Map<String, String>> results;

	/** メッセージのゲッターを表します */
	public String getMessage() {
		return message;
	}

	/** メッセージのセッターを表します */
	public void setMessage(String message) {
		this.message = message;
	}

	/** 住所を格納するリストのゲッターを表します */
	public List<Map<String, String>> getResults() {
		return results;
	}

	/** 住所を格納するリストのセッターを表します */
	public void setResults(List<Map<String, String>> results) {
		this.results = results;
	}

}
