package seasar2.omikuji;

/**
 * Suekichiクラス. <br>
 * Suekichiクラスは、末吉を設定します。
 *
 * @author Ryo.inoue
 * @version 1.00
 */
public class Suekichi extends Omikuji {

	/**
	 * 末吉クラスコンストラクタ
	 *
	 * @deprecated 引数付のコンストラクタの使用を推奨
	 */
	public Suekichi() {
		setUnsei();
	}

	/**
	 * 末吉クラスコンストラクタ
	 *
	 * @param negaigoto 願い事
	 * @param akinai 商い
	 * @param gakumon 学問
	 */
	public Suekichi(String negaigoto, String akinai, String gakumon) {
		setUnsei();
		this.negaigoto = negaigoto;
		this.akinai = akinai;
		this.gakumon = gakumon;
	}

	/**
	 * @see Omikuji#setUnsei()
	 */
	@Override
	public void setUnsei() {
		this.unsei = "末吉";
	}
}
