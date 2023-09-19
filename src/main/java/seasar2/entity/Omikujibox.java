package seasar2.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Omikujiboxクラス. <br>
 * Omikujiboxクラスは、Omikujiboxテーブルのエンティティです。
 *
 * @author Ryo.inoue
 * @version 1.00
 */
@Entity
public class Omikujibox {

	/** おみくじコードを表します */
	@Id
	@Column(name = "omikuji_code")
	public Integer omikujiCode;

	/** 運勢コードを表します */
	@Column(name = "unsei_code")
	public String unseiCode;

	/** 願い事を表します */
	public String negaigoto;

	/** 商いを表します */
	public String akinai;

	/** 学問を表します */
	public String gakumon;

	/** 編集者名を表します */
	@Column(name = "update_by")
	public String updateBy;

	/** 編集日を表します */
	@Temporal(TemporalType.DATE)
	@Column(name = "update_date")
	public Date updateDate;

	/** 登録者名を表します */
	@Column(name = "create_by")
	public String createBy;

	/** 登録日を表します */
	@Temporal(TemporalType.DATE)
	@Column(name = "create_date")
	public Date createDate;

	/** 運勢マスタテーブルとの制約を表します */
	@ManyToOne
	@JoinColumn(name = "unsei_code", referencedColumnName = "unsei_code")
	public Unseimaster unseimaster;

	/** 結果テーブルとの制約を表します */
	@OneToMany(mappedBy = "omikujibox")
	public List<Result> resultList;

	/** 結果テーブルとの制約を表します */
	@OneToMany(mappedBy = "omikujibox")
	public List<CustomerInfo> customerInfoList;
}