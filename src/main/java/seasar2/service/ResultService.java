package seasar2.service;

import java.util.Date;

import javax.annotation.Resource;

import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.jdbc.where.SimpleWhere;

import seasar2.common.ConvertData;
import seasar2.entity.Result;

/**
 * ResultServiceクラス. <br>
 * ResultServiceは、結果テーブルから情報を取得します。
 *
 * @author Ryo.inoue
 * @version 1.00
 */
public class ResultService {

	/** S2JDBCを制御するオブジェクトを表します */
	@Resource
	protected JdbcManager jdbcManager;

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
				.where(
						new SimpleWhere()
								.eq("birthday", ConvertData.toSqlDate(birthday))
								.eq("uranaiDate", today))
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

}
