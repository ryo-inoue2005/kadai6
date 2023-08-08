package seasar2.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Unseimaster {

	@Id
	@GeneratedValue
	@Column(name = "unsei_code")
	public String unseiCode;
	
	@Column(name = "unsei_name")
	public String unseiName;
	
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
	
	@OneToMany(mappedBy = "unseimaster")
	public List<Omikujibox> omikujiList;
}
