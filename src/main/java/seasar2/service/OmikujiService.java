package seasar2.service;

import javax.annotation.Resource;

import org.seasar.extension.jdbc.JdbcManager;

import seasar2.entity.Omikujibox;
import seasar2.entity.Unseimaster;
import seasar2.omikuji.Omikuji;
import seasar2.omikuji.OmikujiFactory;

/**
 * OmikujiServiceクラス. <br>
 * OmikujiServiceクラスは、おみくじボックステーブルからデータを取得します。
 *
 * @author Ryo.inoue
 * @version 1.00
 */
public class OmikujiService {

	/** S2JDBCを制御するオブジェクトを表します */
	@Resource
	protected JdbcManager jdbcManager;

	/**
	 * 登録されているおみくじの数の最大値を取得します。
	 * 
	 * @return 登録されているおみくじの数の最大値
	 */
	public int getCountOmikuji() {
		return (int) jdbcManager.from(Omikujibox.class).getCount();
	}

	/**
	 * おみくじを取得します。
	 * 
	 * @return Omikuji おみくじオブジェクト
	 */
	public Omikuji getOmikuji(int omikujiCode) {

		// SQL構築
		Unseimaster master = jdbcManager.from(Unseimaster.class)
				.innerJoin("omikujiList")
				.where("omikuji_code = ?", omikujiCode)
				.getSingleResult();

		// 取得したデータを元にOmikujiオブジェクトを作成
		return OmikujiFactory.create(
				master.unseiName,
				master.omikujiList.get(0).akinai,
				master.omikujiList.get(0).negaigoto,
				master.omikujiList.get(0).gakumon);
	}

}
