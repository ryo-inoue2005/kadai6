package seasar2.csv;

import org.seasar.s2csv.csv.annotation.column.CSVColumn;
import org.seasar.s2csv.csv.annotation.entity.CSVEntity;

/**
 * FortuneCsvクラス. <br>
 * FortuneCsvクラスは、FortuneCSVファイルのエンティティです。
 *
 * @author Ryo.inoue
 * @version 1.00
 */
@CSVEntity(header = false)
public class FortuneCsv {

	/** 運勢を表します */
	@CSVColumn(columnIndex = 0)
	public String unsei;

	/** 願い事を表します */
	@CSVColumn(columnIndex = 1)
	public String negaigoto;

	/** 商いを表します */
	@CSVColumn(columnIndex = 2)
	public String akinai;

	/** 学問を表します */
	@CSVColumn(columnIndex = 3)
	public String gakumon;
}
