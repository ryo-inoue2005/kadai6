package seasar2.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Omikujibox {

	@Id
	@GeneratedValue
	@Column(name = "omikuji_code")
	public Integer omikujiCode;
	
	@Column(name = "unsei_code")
	public String unseiCode;
	
	public String negaigoto;
	
	public String akinai;
	
	public String gakumon;
	
	@Column(name = "update_by")
	public String updateBy;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "update_date")
	public Date updateDate;
	
	@Column(name = "create_by")
	public String createBy;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "create_date")
	public Date createDate;
	
	@ManyToOne
	@JoinColumn(name = "unsei_code", referencedColumnName = "unsei_code")
	public Unseimaster unseimaster;
	
	@OneToMany(mappedBy = "omikujibox")
	public List<Result> resultList;

}