package seasar2.entity;

/**
 * OmikujiboxInsertParamクラス. <br>
 * OmikujiboxInsertParamクラスは、Omikujiboxテーブルのインサート時のパラメータです。
 *
 * @author Ryo.inoue
 * @version 1.00
 */
public class OmikujiboxInsertParam {
	
	/** おみくじコードを表します */
	public Integer omikujiCode;
	
	/** 運勢を表します */
	public String unsei;

	/** 願い事を表します */
	public String negaigoto;

	/** 商いを表します */
	public String akinai;

	/** 学問を表します */
	public String gakumon;
}
