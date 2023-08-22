package seasar2.init;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;

import org.seasar.s2csv.csv.exception.runtime.CSVValidationResultException;

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

	public void init() throws FileNotFoundException, CSVValidationResultException, UnsupportedEncodingException {
		System.out.println("おみくじ初期化処理");
		
		// CSVからデータベースにおみくじを登録する
		System.out.println(csvService.initOmikuji() + "件登録しました");
	}

}
