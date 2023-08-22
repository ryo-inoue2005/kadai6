package seasar2.init;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;

import seasar2.service.CsvService;

/**
 * InitServiceクラス. <br>
 * InitServiceクラスは、アプリの初期化処理を行います。
 *
 * @author Ryo.inoue
 * @version 1.00
 */
public class InitService {

	/** CSVまわりを制御するオブジェクトを表します */
	@Resource
	protected CsvService csvService;

	/**
	 * データベースの初期化を行います。
	 * 
	 * @throws FileNotFoundException ファイル未発見例外
	 * @throws UnsupportedEncodingException 文字エンコーディング例外
	 * @return 誕生日入力画面
	 */
	public void init() throws FileNotFoundException, UnsupportedEncodingException {

		System.out.println("おみくじ初期化処理");

		// CSVからデータベースにおみくじを登録する
		System.out.println(csvService.initOmikuji() + "件登録しました");
	}

}
