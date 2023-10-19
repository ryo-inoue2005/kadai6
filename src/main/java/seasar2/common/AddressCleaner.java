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
	 * 入力された住所から検索する文字数を返します。
	 * 
	 * @return 文字数
	 */
	public static int getSearchWordCount(String address) {

		// デフォルトの検索する文字数
		final int DEFAULT_SEARCH_CHARACTER_COUNT = 5;
		
		// マッチした後の最低文字数
		final int NOT_MORETHANCHARACTERCOUNT = 3;

		/*
		 *  NOTE:
		 *  日本の住所は複雑で全てのパターンを列挙するのは難しいので一般的な組み合わせを記述
		 *  大体で切り取り、少しでも絞るように変更
		 */

		// 一般的な都道府県と市区町村の組み合わせを列挙
		StringBuilder placeNamePattern = new StringBuilder();
		placeNamePattern.append(".*[都]*.*[市]|");
		placeNamePattern.append(".*[道]*.*[市]|");
		placeNamePattern.append(".*[府]*.*[市]|");
		placeNamePattern.append(".*[県]*.*[市]|");

		placeNamePattern.append(".*[都]*.*[区]|");
		placeNamePattern.append(".*[道]*.*[区]|");
		placeNamePattern.append(".*[道]*.*[区]|");
		placeNamePattern.append(".*[府]*.*[区]|");
		placeNamePattern.append(".*[県]*.*[区]|");

		placeNamePattern.append(".*[都]*.*[町]|");
		placeNamePattern.append(".*[道]*.*[町]|");
		placeNamePattern.append(".*[府]*.*[町]|");
		placeNamePattern.append(".*[県]*.*[町]|");

		placeNamePattern.append(".*[都]*.*[村]|");
		placeNamePattern.append(".*[道]*.*[村]|");
		placeNamePattern.append(".*[府]*.*[村]|");
		placeNamePattern.append(".*[県]*.*[村]|");

		// 都道府県 + 市区町村のパターン読み込み
		Pattern prefectureAndCityPattern = Pattern.compile(placeNamePattern.toString());
		Matcher prefectureAndCityMathcer = prefectureAndCityPattern.matcher(address);

		int searchCharacterCount = DEFAULT_SEARCH_CHARACTER_COUNT;
		
		if (prefectureAndCityMathcer.find()) {
			
			// 千葉県浦安市などマッチした後の文字列が無ければデフォルト文字数を返す
			if (address.length() - prefectureAndCityMathcer.end() <= NOT_MORETHANCHARACTERCOUNT) {
				return searchCharacterCount;
			}
			 
			searchCharacterCount = address.length() - prefectureAndCityMathcer.end();
		}
		return searchCharacterCount;

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
		String[] noStrings = { "之", "ノ", "の", "乃" };
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
