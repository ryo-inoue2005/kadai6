package seasar2.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * CustomerInfoクラス. <br>
 * CustomerInfoクラスは、CustomerInfoテーブルのエンティティです。
 *
 * @author Ryo.inoue
 * @version 1.00
 */
@Entity
public class CustomerInfo {

	/** テーブル名を表します */
	public static final String TABLE = "customer_info";
	
	
	/** カスタマーIDを表します */
	@Id
	@GeneratedValue
	@Column(name = "customer_id")
	public Integer customerId;

	/** おみくじコードを表します */
	@Column(name = "omikuji_code")
	public Integer omikujiCode;

	/** 郵送依頼日を表します */
	@Temporal(TemporalType.DATE)
	@Column(name = "order_date")
	public Date orderDate;

	/** 姓を表します */
	@Column(name = "last_name")
	public String lastName;

	/** 名を表します */
	@Column(name = "first_name")
	public String firstName;

	/** 郵便番号を表します */
	@Column(name = "zip_code")
	public String zipCode;

	/** 住所を表します */
	public String address;

	/** 建物等を表します */
	public String building;

	/** おみくじボックステーブルとの制約を表します */
	@ManyToOne
	@JoinColumn(name = "omikuji_code", referencedColumnName = "omikuji_code")
	public Omikujibox omikujibox;

}
