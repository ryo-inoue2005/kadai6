package seasar2.form;

import java.util.List;

import org.seasar.struts.annotation.EmailType;
import org.seasar.struts.annotation.IntegerType;
import org.seasar.struts.annotation.Required;

import seasar2.dto.ZipcodeDto;

/**
 * ResultSharingFormクラス. <br>
 * ResultSharingFormクラスは、運勢結果を連携する為の住所やメールアドレスなどの個人情報を格納します。
 *
 * @author Ryo.inoue
 * @version 1.00
 */
public class ResultSharingForm {
	
	public List<ZipcodeDto> zipcodeDtoList;

	/** 成功メッセージを表します */
	public String message;

	/** 選択した連携方法を表します */
	public String selectOption;

	/** 姓を表します */
	@Required
	public String lastName;

	/** 名を表します */
	@Required
	public String firstName;

	/** 郵便番号を表します */
	@Required
	@IntegerType
	public String zipcode;

	/** 住所を表します */
	@Required
	public String address;
	
	/** 建物等を表します */
	@Required
	public String building;

	/** メールアドレスを表します */
	@Required
	@EmailType
	public String email;

}
