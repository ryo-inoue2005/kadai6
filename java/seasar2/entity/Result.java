package seasar2.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Result {

	@Temporal(TemporalType.DATE)
	@Column(name = "uranai_date")
	public Date uranaiDate;
	
	@Temporal(TemporalType.DATE)
	public Date birthday;
	
	@Column(name = "omikuji_code")
	public Integer omikujiCode;
	
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
	@JoinColumn(name = "omikuji_code", referencedColumnName = "omikuji_code")
	public Omikujibox omikujibox;

}
