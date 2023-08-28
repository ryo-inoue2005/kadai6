package seasar2.form;

import org.seasar.struts.annotation.IntegerType;
import org.seasar.struts.annotation.Required;

import seasar2.anotation.ExistsDate;
import seasar2.anotation.OmikujiDateType;
import seasar2.omikuji.Omikuji;

/**
 * OmikujiFormクラス. <br>
 * OmikujiFormクラスは、おみくじに関する値を格納します。
 *
 * @author Ryo.inoue
 * @version 1.00
 */
public class OmikujiForm {
	
	/** 誕生日を表します */
	@Required
	@IntegerType
	@OmikujiDateType
	@ExistsDate
	public String birthday;
	
	/** おみくじオブジェクトを表します */
	public Omikuji omikuji;
}
