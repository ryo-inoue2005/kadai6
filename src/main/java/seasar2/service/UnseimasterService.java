package seasar2.service;

import java.util.List;

import javax.annotation.Resource;

import org.seasar.extension.jdbc.JdbcManager;

import seasar2.common.DateFetcher;
import seasar2.dto.SearchStatsDto;

/**
 * UnseimasterServiceクラス. <br>
 * UnseimasterServiceは、Unseimasterテーブルを管理します。
 *
 * @author Ryo.inoue
 * @version 1.00
 */
public class UnseimasterService {

	/** S2JDBCを制御するオブジェクトを表します */
	@Resource
	protected JdbcManager jdbcManager;

	/**
	 * 過去半年の運勢結果の割合を取得します。
	 * 
	 * @return List<SearchStatsDto> 各運勢の割合
	 */
	public List<SearchStatsDto> getLastSixMonthsStats() {

		return jdbcManager
				.selectBySqlFile(SearchStatsDto.class, "sql/stats.sql", DateFetcher.getHalfYearAgo()).getResultList();

	}

}
