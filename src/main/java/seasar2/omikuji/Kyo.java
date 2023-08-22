package seasar2.omikuji;

/**
 * Kyoクラス. <br>
 * Kyoクラスは、凶を設定します。
 *
 * @author Ryo.inoue
 * @version 1.00
 */
public class Kyo extends Omikuji {

	/**
	 * 凶クラスコンストラクタ
	 *
	 * @deprecated 引数付のコンストラクタの使用を推奨
	 */
	public Kyo() {
		setUnsei();
	}

	/**
	 * 凶クラスコンストラクタ
	 *
	 * @param negaigoto 願い事
	 * @param akinai 商い
	 * @param gakumon 学問
	 */
	public Kyo(String negaigoto, String akinai, String gakumon) {
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
		this.unsei = "凶";
	}
}
