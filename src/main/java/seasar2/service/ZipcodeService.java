package seasar2.service;

import javax.annotation.Resource;

import org.seasar.extension.jdbc.JdbcManager;

import seasar2.dto.ZipcodeDto;

public class ZipcodeService {

	/** S2JDBCを制御するオブジェクトを表します */
	@Resource
	protected JdbcManager jdbcManager;

	/**
	 * 顧客情報を登録します。
	 * 
	 * @return 登録件数
	 */
	public ZipcodeDto getZipcodeToAddress(String zipcode) {

		return jdbcManager.selectBySqlFile(ZipcodeDto.class, "sql/zipcodeToAddress.sql", zipcode).getSingleResult();
	}

}
