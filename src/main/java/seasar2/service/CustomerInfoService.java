package seasar2.service;

import java.util.Date;

import javax.annotation.Resource;

import org.seasar.extension.jdbc.JdbcManager;

import seasar2.entity.CustomerInfo;

/**
 * CustomerInfoServiceクラス. <br>
 * CustomerInfoServiceは、CustomerInfoSテーブルを管理します。
 *
 * @author Ryo.inoue
 * @version 1.00
 */
public class CustomerInfoService {

	/** S2JDBCを制御するオブジェクトを表します */
	@Resource
	protected JdbcManager jdbcManager;

	/**
	 * 顧客情報を登録します。
	 * 
	 * @return 登録件数
	 */
	public int insertCustomer(int omikujiCode,
			String lastName,
			String firstName,
			String zipcode,
			String address,
			String building) {

		// 今日の日付
		final Date today = new Date();

		// 顧客情報を登録
		CustomerInfo customerInfo = new CustomerInfo();
		customerInfo.orderDate = today;
		customerInfo.omikujiCode = omikujiCode;
		customerInfo.lastName = lastName;
		customerInfo.firstName = firstName;
		customerInfo.zipCode = zipcode;
		customerInfo.address = address;
		customerInfo.building = building;

		return jdbcManager.insert(customerInfo).execute();

	}

}