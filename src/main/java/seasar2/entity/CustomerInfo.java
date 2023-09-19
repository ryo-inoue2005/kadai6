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

@Entity
public class CustomerInfo {

	public static final String TABLE = "customer_info";
	
	@Id
	@GeneratedValue
	@Column(name = "customer_id")
	public Integer customerId;

	@Column(name = "omikuji_code")
	public Integer omikujiCode;

	@Temporal(TemporalType.DATE)
	@Column(name = "order_date")
	public Date orderDate;

	@Column(name = "last_name")
	public String lastName;

	@Column(name = "first_name")
	public String firstName;

	@Column(name = "zip_code")
	public String zipCode;

	public String prefecture;

	public String city;

	public String address;

	/** おみくじボックステーブルとの制約を表します */
	@ManyToOne
	@JoinColumn(name = "omikuji_code", referencedColumnName = "omikuji_code")
	public Omikujibox omikujibox;

}
