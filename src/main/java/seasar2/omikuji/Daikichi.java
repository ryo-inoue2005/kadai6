package seasar2.omikuji;

/**
 * Daikichiクラス. <br>
 * Daikichiクラスは、大吉を設定します。
 *
 * @author Ryo.inoue
 * @version 1.00
 */
public class Daikichi extends Omikuji {

	/**
	 * 大吉クラスコンストラクタ
	 *
	 * @deprecated 引数付のコンストラクタの使用を推奨
	 */
	public Daikichi() {
		setUnsei();
	}

	/**
	 * 大吉クラスコンストラクタ
	 *
	 * @param negaigoto 願い事
	 * @param akinai 商い
	 * @param gakumon 学問
	 */
	public Daikichi(String negaigoto, String akinai, String gakumon) {
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
		this.unsei = "大吉";
	}
}
