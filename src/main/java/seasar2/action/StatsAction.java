package seasar2.action;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import seasar2.form.StatsForm;
import seasar2.service.UnseimasterService;

/**
 * StatsActionクラス. <br>
 * StatsActionクラスは、過去半年の運勢割合表示画面を制御します。
 *
 * @author Ryo.inoue
 * @version 1.00
 */
public class StatsAction {

	/** 運勢割合のフォームを表します */
	@Resource
	@ActionForm
	protected StatsForm statsForm;

	/** 過去の結果を取得するサービスを表します */
	@Resource
	protected UnseimasterService unseimasterService;

	/**
	 * 過去半年の割合を取得し、過去半年の運勢割合表示画面に遷移します。
	 * 
	 * @return 過去半年の統計表示画面
	 */
	@Execute(validator = false)
	public String index() {

		// 過去半年の割合を取得し、Beanに格納
		statsForm.statsList = unseimasterService.getLastSixMonthsStats();

		return "statsView.jsp";

	}

}
