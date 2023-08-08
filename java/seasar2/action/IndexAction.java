/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package seasar2.action;

import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import seasar2.entity.Omikujibox;
import seasar2.form.OmikujiForm;
import seasar2.service.OmikujiService;
import seasar2.service.ResultService;

public class IndexAction {

	//public JdbcManager jdbcManager;

	@Resource
	@ActionForm
	protected OmikujiForm omikujiForm;

	@Resource
	protected OmikujiService omikujiService;

	@Execute(validator = false)
	public String index() {

		//omikujiService.setOmikuji();

		//System.out.println(omikujis.get(0).gakumon);

		//System.out.println(omikujis.get(0).resultList.get(0).birthday);

		return "formView.jsp";
	}

	public List<Omikujibox> omikujiList;

	@Resource
	protected ResultService resultService;

	// チェックエラー時はinputで書いたパスに戻る
	@Execute(validator = false, input = "formView.jsp")
	public String result() {

		String birthday = String.valueOf(omikujiForm.birthday);
		
		 int omikujiCode = resultService.getResult(birthday);

		if (omikujiCode == 0) {

			// データベースからおみくじの数を取得し、ランダムにおみくじコードを発行する
			Random random = new Random();
			omikujiCode = random.nextInt(omikujiService.getCountOmikuji()) + 1;

			// 登録処理
			resultService.registerResult(omikujiCode, birthday);
		}
		
		omikujiForm.omikuji = omikujiService.getOmikuji(omikujiCode);
		
		return "resultView.jsp";
	}
}
