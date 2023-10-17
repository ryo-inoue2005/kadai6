package seasar2.service;

import static seasar2.entity.ZipcodeNames.*;

import java.util.List;

import javax.annotation.Resource;

import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.jdbc.where.SimpleWhere;

import seasar2.entity.Zipcode;

/**
 * ZipcodeServiceクラス. <br>
 * ZipcodeServiceクラスは、zipcodeテーブルを管理します。
 *
 * @author Ryo.inoue
 * @version 1.00
 */
public class ZipcodeService {

	/** S2JDBCを制御するオブジェクトを表します */
	@Resource
	protected JdbcManager jdbcManager;

	/**
	 * 郵便番号から住所を検索します。
	 * 
	 * @return 住所
	 */
	public List<Zipcode> findAddressByZipcode(String zipcode) {

		return jdbcManager.from(Zipcode.class)
				.where(new SimpleWhere()
						.eq(zipCode(), zipcode))
				.getResultList();
	}

	/**
	 * 住所から郵便番号を検索します。
	 * 
	 * @return 郵便番号
	 */
	public List<Zipcode> findZipcodeByFulladdress(String fullAddress) {
		return jdbcManager
				.selectBySqlFile(Zipcode.class, "sql/findZipcodeByFulladdress.sql", fullAddress)
				.getResultList();
	}

}
