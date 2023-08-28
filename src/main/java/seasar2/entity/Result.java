package seasar2.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Resultクラス. <br>
 * Resultクラスは、Resultテーブルのエンティティです。
 *
 * @author Ryo.inoue
 * @version 1.00
 */
@Entity
public class Result {

	/** 占い日を表します */
	@Temporal(TemporalType.DATE)
	@Column(name = "uranai_date")
	public Date uranaiDate;
	
	/** 誕生日を表します */
	@Temporal(TemporalType.DATE)
	public Date birthday;
	
	/** おみくじコードを表します */
	@Column(name = "omikuji_code")
	public Integer omikujiCode;
	
	/** 編集者名を表します */
	@Column(name = "update_by")
	public String updateBy;
	
	/** 編集日を表します */
	@Temporal(TemporalType.DATE)
	@Column(name = "update_date")
	public Date updateDate;
	
	/** 作成者を表します */
	@Column(name = "create_by")
	public String createBy;
	
	/** 作成日を表します */
	@Temporal(TemporalType.DATE)
	@Column(name = "create_date")
	public Date createDate;
	
	/** おみくじボックステーブルとの制約を表します */
	@ManyToOne
	@JoinColumn(name = "omikuji_code", referencedColumnName = "omikuji_code")
	public Omikujibox omikujibox;

}
