package seasar2.service;

import static seasar2.entity.ZipcodeNames.*;

import java.util.List;

import javax.annotation.Resource;

import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.jdbc.where.SimpleWhere;

import seasar2.dto.ZipcodeDto;
import seasar2.entity.Zipcode;
import seasar2.entity.ZipcodeSelectAddressParam;

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
	public Zipcode findZipcodeByAddress(String prefecture, String city, String address) {

		return jdbcManager.from(Zipcode.class)
				.where(new SimpleWhere()
						.eq(prefecture(), prefecture)
						.eq(city(), city)
						.eq(address(), address))
				.getSingleResult();
	}

	/**
	 * 都道府県から市区町村を検索します。
	 * 
	 * @return 市区町村リスト
	 */
	public List<ZipcodeDto> findCityByPrefecture(String prefecture) {

		return jdbcManager
				.selectBySqlFile(ZipcodeDto.class, "sql/findCityByPrefecture.sql", prefecture)
				.getResultList();
	}

	/**
	 * 市区町村からその他住所を検索します。
	 * 
	 * @return その他住所リスト
	 */
	public List<ZipcodeDto> findAddressByCity(String prefecture, String city) {

		ZipcodeSelectAddressParam param = new ZipcodeSelectAddressParam();
		param.prefecture = prefecture;
		param.city = city;

		return jdbcManager
				.selectBySqlFile(ZipcodeDto.class, "sql/findAddressByCity.sql", param)
				.getResultList();
	}

}
