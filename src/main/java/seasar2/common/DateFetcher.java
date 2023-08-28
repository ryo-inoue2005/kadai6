package seasar2.common;

import java.util.Calendar;
import java.util.Date;

/**
 * DateFetcherクラス. <br>
 * DateFetcherは、特定の日付を取得します。
 *
 * @author Ryo.inoue
 * @version 1.00
 */
public class DateFetcher {
	
	/**
	 * 半年前の日付を取得します。
	 * 
	 * @return java.sql.Date 半年前の日付
	 */
	public static java.sql.Date getHalfYearAgo() {

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
}
