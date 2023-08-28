package seasar2.action;

import org.seasar.struts.annotation.Execute;

/**
 * IndexActionクラス. <br>
 * IndexActionクラスは、Welcome画面を制御します。
 *
 * @author Ryo.inoue
 * @version 1.00
 */
public class IndexAction {

	/**
	 * Welcome画面に遷移します。
	 * 
	 * @return 誕生日入力画面
	 */
	@Execute(validator = false)
	public String index() {
		return "index.jsp";
	}
}
