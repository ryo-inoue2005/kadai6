package seasar2.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import seasar2.exception.UnauthorizedAccessException;
import seasar2.form.PastListForm;
import seasar2.service.PastService;

/**
 * PastListActionクラス. <br>
 * PastListActionクラスは、過去半年の結果リスト画面を制御します。
 *
 * @author Ryo.inoue
 * @version 1.00
 */
public class PastListAction {

	/** 過去リストのフォームを表します */
	@Resource
	@ActionForm
	protected PastListForm pastListForm;

	/** 過去の結果を取得するサービスを表します */
	@Resource
	protected PastService pastService;

	/** セッションを表します */
	@Resource
	protected HttpSession session;

	/**
	 * 過去半年の結果リスト画面に遷移します
	 * 
	 * @return 過去半年の結果リスト画面
	 */
	@Execute(validator = false)
	public String index() {

		// ブラウザで故意にセッションを削除された時の対処　全件取得防止
		if (session.getAttribute("birthday") == null) {
			throw new UnauthorizedAccessException("不正アクセス例外");
		}

		// 過去半年の結果をBeanに格納
		pastListForm.omikujiList = pastService.getLastSixMonthsList();

		return "pastListView.jsp";
	}
}
