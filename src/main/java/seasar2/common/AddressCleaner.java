package seasar2.common;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * AddressCleanerクラス. <br>
 * AddressCleanerクラスは、入力された住所を加工します。
 *
 * @author Ryo.inoue
 * @version 1.00
 */
public class AddressCleaner {

	/**
	 * 入力された住所から番地等を取り除きます。
	 * 
	 * @return 番地等を取り除いた住所
	 */
	public static String removeStreetNumber(String address) {

		// NOTE:1丁目, 漢数字等で分割する方法が一部の地域で効かなかった為、県と市を別ける方法を採用
		String p1 = ".*[都]*.*[市]|";
		String p2 = ".*[道]*.*[市]|";
		String p3 = ".*[府]*.*[市]|";
		String p4 = ".*[県]*.*[市]";

		// 都道府県 + 市区町村のパターン読み込み
		Pattern prefectureAndCityPattern = Pattern.compile(p1 + p2 + p3 + p4);
		Matcher prefectureAndCityMathcer = prefectureAndCityPattern.matcher(address);

		String prefectureAndCity = null;

		// 前項パターンが見つかった場合、都道府県 + 市区町村を代入
		if (prefectureAndCityMathcer.find()) {
			prefectureAndCity = prefectureAndCityMathcer.group();
		}

		// 住所から都道府県 + 市区町村を取り除く
		String streetAddress = address.replace(prefectureAndCity, "");

		// 番地等のパターン読み込み
		String regexSt = "([0-9０-９]+|[一二三四五六七八九十百千万]+)+";
		Pattern stPattern = Pattern.compile(regexSt);
		Matcher stMatcher = stPattern.matcher(streetAddress);

		// 番地等を取り除く
		if (stMatcher.find()) {
			streetAddress = streetAddress.substring(0, stMatcher.start());
		}

		return prefectureAndCity + streetAddress;
	}

	/**
	 * 入力された住所に表記揺れが存在した場合、全てのパターンを返します。
	 * 
	 * @return 表記揺れしている住所の全てのパターン
	 */
	public static List<String> RegularzationAddress(String address) {

		List<String> possibilityList = new ArrayList<>();

		// 「か」の表記揺れ全パターン取得
		String[] kaStrings = { "ヶ", "ケ", "か", "が", "ガ" };
		Pattern kaPattern = Pattern.compile("(ヶ|ケ|か|が|ガ)");
		Matcher kaMatcher = kaPattern.matcher(address);

		if (kaMatcher.find()) {
			for (String ka : kaStrings) {
				possibilityList.add(kaMatcher.replaceAll(ka));
			}
		}

		// 「の」の表記揺れ全パターン取得
		String[] noStrings = { "之", "ノ", "の", "乃"};
		Pattern noPattern = Pattern.compile("(之|ノ|の|乃)");
		Matcher noMatcher = noPattern.matcher(address);

		if (noMatcher.find()) {
			for (String no : noStrings) {
				possibilityList.add(noMatcher.replaceAll(no));
			}
		}

		return possibilityList;
	}
}
