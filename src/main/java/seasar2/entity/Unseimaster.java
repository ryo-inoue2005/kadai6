package seasar2.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Unseimasterクラス. <br>
 * Unseimasterクラスは、Unseimasterテーブルのエンティティです。
 *
 * @author Ryo.inoue
 * @version 1.00
 */
@Entity
public class Unseimaster {

	/** 運勢コードを表します */
	@Id
	@Column(name = "unsei_code")
	public String unseiCode;
	
	/** 運勢名を表します */
	@Column(name = "unsei_name")
	public String unseiName;
	
	/** 編集者名を表します */
	@Column(name = "update_by")
	public String updateBy;
	
	/** 編集日を表します */
	@Temporal(TemporalType.DATE)
	@Column(name = "update_date")
	public Date updateDate;
	
	/** 作成者名を表します */
	@Column(name = "create_by")
	public String createBy;
	
	/** 作成日を表します */
	@Temporal(TemporalType.DATE)
	@Column(name = "create_date")
	public Date createDate;
	
	/** おみくじボックスとの制約を表します */
	@OneToMany(mappedBy = "unseimaster")
	public List<Omikujibox> omikujiList;
}
