package seasar2.service;

import static seasar2.entity.ResultNames.*;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.jdbc.where.SimpleWhere;

import seasar2.common.ConvertData;
import seasar2.common.DateFetcher;
import seasar2.entity.Result;

/**
 * ResultServiceクラス. <br>
 * ResultServiceは、Resultテーブルから情報を取得します。
 *
 * @author Ryo.inoue
 * @version 1.00
 */
public class ResultService {

	/** S2JDBCを制御するオブジェクトを表します */
	@Resource
	protected JdbcManager jdbcManager;

	/** セッションを表します */
	@Resource
	protected HttpSession session;

	/** 今日の日付を表します */
	private final Date today = new Date();

	/**
	 * resultテーブルに保存されているおみくじコードを取得します。
	 * 
	 * @return おみくじコード
	 * @return 0 取得できなかった場合
	 */
	public int getResult(String birthday) {

		// SQL構築
		Result result = jdbcManager.from(Result.class)
				.where(new SimpleWhere()
						.eq(birthday(), ConvertData.toSqlDate(birthday))
						.eq(uranaiDate(), today))
				.getSingleResult();

		// 結果が取得できたらおみくじコードを返す
		if (result != null) {
			return result.omikujiCode;
		}

		// 取得できなかった場合
		return 0;

	}

	/**
	 * resultテーブルに運勢結果を登録します。
	 * 
	 * @return 登録件数
	 */
	public int registerResult(int omikujiCode, String birthday) {

		Result result = new Result();
		result.uranaiDate = today;
		result.birthday = ConvertData.toSqlDate(birthday);
		result.omikujiCode = omikujiCode;
		result.createBy = "Ryo.inoue";
		result.createDate = today;

		// 登録処理
		return jdbcManager.insert(result).execute();
	}

	/**
	 * resultテーブルに保存されている過去半年のおみくじデータを取得します。
	 * 
	 * @return List<Result> 過去半年のおみくじのリスト
	 */
	public List<Result> getLastSixMonthsList() {

		// SQL構築
		return jdbcManager.from(Result.class)
				.innerJoin(omikujibox())
				.innerJoin(omikujibox().unseimaster())
				.where(new SimpleWhere()
						.eq(birthday(), session.getAttribute("birthday"))
						.ge(createDate(), DateFetcher.getHalfYearAgo())
						.le(createDate(), today))
				.getResultList();
	}

}
