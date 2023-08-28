package seasar2.service;

import static seasar2.entity.UnseimasterNames.*;

import javax.annotation.Resource;

import org.seasar.extension.jdbc.JdbcManager;

import seasar2.entity.Omikujibox;
import seasar2.entity.OmikujiboxInsertParam;
import seasar2.entity.Unseimaster;

/**
 * OmikujiServiceクラス. <br>
 * OmikujiServiceクラスは、おみくじボックステーブルからデータを取得します。
 *
 * @author Ryo.inoue
 * @version 1.00
 */
public class OmikujiboxService {

	/** S2JDBCを制御するオブジェクトを表します */
	@Resource
	protected JdbcManager jdbcManager;

	/**
	 * 登録されているおみくじの数の最大値を取得します。
	 * 
	 * @return 登録されているおみくじの数の最大値
	 */
	public long getCountOmikuji() {
		return jdbcManager.from(Omikujibox.class).getCount();
	}

	/**
	 * おみくじを登録します。
	 * 
	 * @return 登録した件数
	 */
	public int registerOmikuji(String unsei, String negaigoto, String akinai, String gakumon) {
		OmikujiboxInsertParam param = new OmikujiboxInsertParam();

		param.omikujiCode = (int) (getCountOmikuji() + 1);
		param.unsei = unsei;
		param.negaigoto = negaigoto;
		param.akinai = akinai;
		param.gakumon = gakumon;

		return jdbcManager.updateBySqlFile("sql/initOmikuji.sql", param).execute();
	}

	/**
	 * おみくじを取得します。
	 * 
	 * @return おみくじ結果
	 */
	public Unseimaster getOmikuji(int omikujiCode) {

		// SQL構築
		return jdbcManager.from(Unseimaster.class)
				.innerJoin(omikujiList())
				.where("omikuji_code = ?", omikujiCode)
				.getSingleResult();
	}
}
