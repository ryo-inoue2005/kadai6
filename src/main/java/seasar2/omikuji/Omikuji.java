package seasar2.omikuji;

/**
 * Omikujiクラス. <br>
 * Omikujiクラスは、おみくじ周りを管理します。
 *
 * @author Ryo.inoue
 * @version 1.00
 */
public abstract class Omikuji implements Fortune {

	/** 登録日を表します */
	protected String createDate;
	/** 運勢を表します */
	protected String unsei;
	/** 願い事を表します */
	protected String negaigoto;
	/** 商いを表します */
	protected String akinai;
	/** 学問を表します */
	protected String gakumon;

	/**
	 * 登録日の取得
	 */
	public String getCreateDate() {
		return createDate;
	}

	/**
	 * 登録日のセット
	 */
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	/**
	 * 運勢の取得
	 */
	public String getUnsei() {
		return unsei;
	}

	/**
	 * 願い事の取得
	 */
	public String getNegaigoto() {
		return negaigoto;
	}

	/**
	 * 商いの取得
	 */
	public String getAkinai() {
		return akinai;
	}

	/**
	 * 学問の取得
	 */
	public String getGakumon() {
		return gakumon;
	}

	/**
	 * 運勢をセットします。	
	 */
	public abstract void setUnsei();

	/**
	 * @see Fortune#disp()
	 */
	@Override
	public String disp() {
		return String.format(DISP_STR, getUnsei());
	}
	
	public String getDisp() {
		return disp();
	}
}
