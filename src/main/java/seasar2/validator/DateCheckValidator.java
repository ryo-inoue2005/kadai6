package seasar2.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.Field;
import org.apache.commons.validator.Validator;
import org.apache.commons.validator.ValidatorAction;
import org.apache.commons.validator.util.ValidatorUtils;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.validator.Resources;
import org.seasar.struts.validator.S2FieldChecks;

/**
 * DateCheckValidatorクラス. <br>
 * DateCheckValidatorクラスは、日付のバリデーションを提供します。
 *
 * @author Ryo.inoue
 * @version 1.00
 */
public class DateCheckValidator extends S2FieldChecks {

	/** 日付の型を表します */
	private static final SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");

	/** 日付の型チェックが通ったかを表します */
	private static boolean isDateValidator = false;

	/**
	 * 日付の型が正しいかチェックします
	 * 
	 * @param bean Bean
	 * @param va バリデーターアクション
	 * @param field フィールド
	 * @param validator レスポンス
	 * @param request リクエスト
	 * @return チェック結果
	 */
	public static boolean isDateValidator(Object bean,
			ValidatorAction va,
			Field field,
			ActionMessages errors,
			Validator validator,
			HttpServletRequest request) {

		// 入力された値を取得する
		String value = ValidatorUtils.getValueAsString(bean, field.getProperty());

		if (value.isEmpty() || !value.matches("^[0-9]+$|-[0-9]+$")) {
			return true;
		}

		// 正しい形式かチェック
		try {
			df.format(df.parse(value));
		} catch (Exception e) {
			// 入力チェックエラーの為、エラーメッセージをセットする
			errors.add(field.getKey(), Resources.getActionMessage(validator, request, va, field));
			return false;
		}
		isDateValidator = true;
		return true;
	}

	/**
	 * 日付が存在するかチェックします
	 * 
	 * @param bean Bean
	 * @param va バリデーターアクション
	 * @param field フィールド
	 * @param validator レスポンス
	 * @param request リクエスト
	 * @throws ParseException
	 * @return チェック結果
	 */
	public static boolean isExistsDateValidator(Object bean,
			ValidatorAction va,
			Field field,
			ActionMessages errors,
			Validator validator,
			HttpServletRequest request) throws ParseException {

		// 日付の型チェックが通っていない場合、終了させる
		if (!isDateValidator) {
			return false;
		}

		// 入力された値を取得する
		String value = ValidatorUtils.getValueAsString(bean, field.getProperty());

		String fs = df.format(df.parse(value));

		// 存在する日付かチェック
		if (!value.equals(fs)) {
			errors.add(field.getKey(), Resources.getActionMessage(validator, request, va, field));
			return false;
		}
		// 初期化
		isDateValidator = false;
		return true;
	}
}
