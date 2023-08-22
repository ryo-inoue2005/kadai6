package seasar2.omikuji;

/**
 * Shokichiクラス. <br>
 * Shokichiクラスは、小吉を設定します。
 *
 * @author Ryo.inoue
 * @version 1.00
 */
public class Shokichi extends Omikuji {

	/**
	 * 小吉クラスコンストラクタ
	 *
	 * @deprecated 引数付のコンストラクタの使用を推奨
	 */
	public Shokichi() {
		setUnsei();
	}

	/**
	 * 小吉クラスコンストラクタ
	 *
	 * @param negaigoto 願い事
	 * @param akinai 商い
	 * @param gakumon 学問
	 */
	public Shokichi(String negaigoto, String akinai, String gakumon) {
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
		this.unsei = "小吉";
	}
}
