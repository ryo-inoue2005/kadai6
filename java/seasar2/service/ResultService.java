package seasar2.service;

import java.util.Date;

import javax.annotation.Resource;

import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.jdbc.where.ComplexWhere;

import seasar2.entity.Result;

public class ResultService {

	@Resource
	protected JdbcManager jdbcManager;
	
	private final Date today = new Date();

	public int getResult(String birthday) {
		
		Result result = jdbcManager.from(Result.class)
				.where(
						new ComplexWhere()
						.eq("birthday", ConvertData.toSqlDate(birthday)).eq("uranaiDate", today)).getSingleResult();
		
		if(result != null) {
			return result.omikujiCode;
		}
		
		return 0;
					
	}

	public int registerResult(int omikujiCode, String birthday) {

		Result result = new Result();
		result.uranaiDate = today;
		result.birthday = ConvertData.toSqlDate(birthday);
		result.omikujiCode = omikujiCode;
		result.createBy = "Ryo.inoue";
		result.createDate = today;

		return jdbcManager.insert(result).execute();
	}

}
