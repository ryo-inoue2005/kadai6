package seasar2.service;

import javax.annotation.Resource;

import org.seasar.extension.jdbc.JdbcManager;

import seasar2.entity.Omikujibox;
import seasar2.entity.Unseimaster;
import seasar2.omikuji.Omikuji;
import seasar2.omikuji.OmikujiFactory;

public class OmikujiService {

	@Resource
	protected JdbcManager jdbcManager;

	public int getCountOmikuji() {
		return (int) jdbcManager.from(Omikujibox.class).getCount();
	}

	public Omikuji getOmikuji(int omikujiCode) {

		Unseimaster master = jdbcManager.from(Unseimaster.class)
				.innerJoin("omikujiList")
				.where("omikuji_code = ?", omikujiCode)
				.getSingleResult();

		return OmikujiFactory.create(master.unseiName,
				master.omikujiList.get(0).akinai,
				master.omikujiList.get(0).negaigoto,
				master.omikujiList.get(0).gakumon);
	}
}
