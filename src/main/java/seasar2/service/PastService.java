package seasar2.service;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.jdbc.SelectHandler;
import org.seasar.extension.jdbc.where.SimpleWhere;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.S2ContainerFactory;

import seasar2.entity.Result;
import seasar2.omikuji.Omikuji;
import seasar2.omikuji.OmikujiFactory;

/**
 * PastServiceクラス. <br>
 * PastServiceクラスは、運勢結果の過去データを各種取得します。
 *
 * @author Ryo.inoue
 * @version 1.00
 */
public class PastService {

	/** S2JDBCを制御するオブジェクトを表します */
	@Resource
	protected JdbcManager jdbcManager;

	/** セッションを表します */
	@Resource
	protected HttpSession session;

	/** 今日の日付を表します */
	private final Date today = new Date();

	/**
	 * resultテーブルに保存されている過去半年のおみくじデータを取得します。
	 * 
	 * @return List<Omikuji> おみくじリスト
	 */
	public List<Omikuji> getLastSixMonthsList() {

		// SQL構築
		List<Result> master = jdbcManager.from(Result.class)
				.innerJoin("omikujibox")
				.innerJoin("omikujibox.unseimaster")
				.where(new SimpleWhere()
						.eq("birthday", session.getAttribute("birthday"))
						.ge("createDate", getHalfYearAgo())
						.le("createDate", today))
				.getResultList();

		// おみくじオブジェクト格納リスト
		List<Omikuji> omikujiList = new LinkedList<>();

		// 取得したデータを元におみくじオブジェクトを生成し、リストに追加
		for (Result result : master) {
			Omikuji omikuji = OmikujiFactory.create(
					result.omikujibox.unseimaster.unseiName,
					result.omikujibox.akinai,
					result.omikujibox.negaigoto,
					result.omikujibox.gakumon);
			omikuji.setCreateDate(String.valueOf(result.createDate));

			omikujiList.add(omikuji);
		}

		return omikujiList;
	}

	/**
	 * 過去半年の運勢結果の割合を取得します。
	 * 
	 * @return List<Map<String, Object>> 各運勢の割合
	 */
	public List<Map<String, Object>> getLastSixMonthsStats() {

		// SQLを実行する為のハンドラを取得
		S2Container container = S2ContainerFactory.create("sql/statsSql.dicon");
		container.init();
		SelectHandler handler = (SelectHandler) container.getComponent("selectStatsMap");

		// 過去半年の運勢結果の割合を取得
		List<Map<String, Object>> listMap = castListMap(handler.execute(new Object[] { getHalfYearAgo() }));

		return listMap;
	}

	/**
	 * 半年前の日付を取得します。
	 * 
	 * @return java.sql.Date 半年前の日付
	 */
	public java.sql.Date getHalfYearAgo() {

		// 今日の日付を取得
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		// 半年前に設定し、SQL.DATE型に合わせる為に0を代入
		cal.add(Calendar.MONTH, -6);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return new java.sql.Date(cal.getTimeInMillis());
	}

	/**
	 * オブジェクトをList<Map<String, Object>に変換します。
	 * 
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> castListMap(Object obj) {
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> listMap = (List<Map<String, Object>>) obj;
		return listMap;
	}

}
