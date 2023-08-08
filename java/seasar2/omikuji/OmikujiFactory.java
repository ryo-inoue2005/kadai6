package seasar2.omikuji;

/**
 * OmikujiFactoryクラス. <br>
 * OmikujiFactoryクラスは、おみくじオブジェクトを生成します。
 *
 * @author Ryo.inoue
 * @version 1.00
 */
public class OmikujiFactory {

	/**
	 * 運勢を元におみくじオブジェクトを生成して返します。
	 * 
	 * @return omikuji おみくじオブジェクト
	 * @return null 不正な運勢の場合
	 */
	public static Omikuji create(String unsei, String negaigoto, String akinai, String gakumon) {

		switch (unsei) {

		case "大吉":
			return new Daikichi(negaigoto,akinai,gakumon);

		case "中吉":
			return new Chukichi(negaigoto,akinai,gakumon);

		case "吉":
			return new Kichi(negaigoto,akinai,gakumon);

		case "小吉":
			return new Shokichi(negaigoto,akinai,gakumon);

		case "末吉":
			return new Suekichi(negaigoto,akinai,gakumon);
			
		case "凶":
			return new Kyo(negaigoto,akinai,gakumon);
			
		default:
			System.out.println("不正な運勢です");
			return null;
		}
	}
}
