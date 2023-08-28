package seasar2.form;

import java.util.List;

import seasar2.dto.SearchStatsDto;

/**
 * StatsFormクラス. <br>
 * StatsFormクラスは、過去の運勢結果の割合に関する値を格納します。
 *
 * @author Ryo.inoue
 * @version 1.00
 */
public class StatsForm {
	
	/** 運勢結果の割合を格納するリストを表します */
	public List<SearchStatsDto> statsList;

}
