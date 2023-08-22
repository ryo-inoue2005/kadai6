package seasar2.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.seasar.extension.jdbc.UpdateHandler;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.S2ContainerFactory;
import org.seasar.s2csv.csv.S2CSVParseCtrl;
import org.seasar.s2csv.csv.factory.S2CSVCtrlFactory;
import org.seasar.struts.util.ServletContextUtil;

import seasar2.csv.FortuneCsv;

/**
 * CsvServiceクラス. <br>
 * CsvServiceクラスは、CSVまわりを制御します。
 *
 * @author Ryo.inoue
 * @version 1.00
 */
public class CsvService {

	/** CSVを管理するオブジェクトを表します */
	@Resource
	protected S2CSVCtrlFactory s2csvCtrlFactory;

	/** おみくじを取得するサービスを表します */
	@Resource
	protected OmikujiService omikujiService;

	/**
	 * CSVファイルに保存している運勢結果をデータベースに登録します。
	 * 
	 * @throws FileNotFoundException ファイル未発見例外
	 * @throws UnsupportedEncodingException 文字エンコーディング例外
	 */
	public int initOmikuji()
			throws FileNotFoundException, UnsupportedEncodingException {

		// SQLを実行する為のハンドラを取得
		S2Container container = S2ContainerFactory.create("sql/initSql.dicon");
		container.init();
		UpdateHandler handler = (UpdateHandler) container.getComponent("insertHandler");

		// CSVファイル読み込み
		ServletContext app = ServletContextUtil.getServletContext();
		File file = new File(app.getRealPath("data/fortune.csv"));
		Reader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "Shift-JIS"));
		S2CSVParseCtrl<FortuneCsv> controller = s2csvCtrlFactory.getParseController(FortuneCsv.class, reader);

		// 登録されているおみくじコードの次の値を格納
		int omikujiCode = omikujiService.getCountOmikuji() + 1;

		// 登録した数を格納する変数
		int result = 0;

		// CSVファイルを一行ずつ読み込み、データベースに登録
		while (controller.readNext()) {
			// おみくじが登録されたらおみくじコードと登録数をインクリメント
			if (handler.execute(
					new Object[] {
							omikujiCode,
							controller.parse().unsei,
							controller.parse().negaigoto,
							controller.parse().akinai,
							controller.parse().gakumon }) != 0) {
				omikujiCode++;
				result++;
			}
		}
		return result;
	}
}
