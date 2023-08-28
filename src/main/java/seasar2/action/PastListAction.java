package seasar2.action;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import seasar2.entity.Result;
import seasar2.form.PastListForm;
import seasar2.omikuji.Omikuji;
import seasar2.omikuji.OmikujiFactory;
import seasar2.service.ResultService;

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
	protected ResultService resultService;

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
			throw new UnsupportedOperationException("不正な操作が検出されました");
		}

		// 過去半年の結果をBeanに格納

		List<Result> resultList = resultService.getLastSixMonthsList();

		// おみくじオブジェクト格納リスト
		List<Omikuji> omikujiList = new LinkedList<>();

		// 取得したデータを元におみくじオブジェクトを生成し、リストに追加
		for (Result result : resultList) {
			Omikuji omikuji = OmikujiFactory.create(
					result.omikujibox.unseimaster.unseiName,
					result.omikujibox.akinai,
					result.omikujibox.negaigoto,
					result.omikujibox.gakumon);
			omikuji.setCreateDate(String.valueOf(result.createDate));

			omikujiList.add(omikuji);
		}

		pastListForm.omikujiList = omikujiList;

		return "pastListView.jsp";
	}
}
