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

import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import seasar2.form.OmikujiForm;
import seasar2.service.OmikujiService;
import seasar2.service.ResultService;

/**
 * IndexActionクラス. <br>
 * IndexActionクラスは、誕生日入力画面と結果表示画面を制御します。
 *
 * @author Ryo.inoue
 * @version 1.00
 */
public class IndexAction {

	/** おみくじのフォームを表します */
	@Resource
	@ActionForm
	protected OmikujiForm omikujiForm;

	/** おみくじを取得するサービスを表します */
	@Resource
	protected OmikujiService omikujiService;

	/** おみくじ結果を取得するサービスを表します */
	@Resource
	protected ResultService resultService;

	/** セッションを表します */
	@Resource
	protected HttpSession session;

	/**
	 * 誕生日入力画面に遷移します。
	 * 
	 * @return 誕生日入力画面
	 */
	@Execute(validator = false)
	public String index() {

		// セッション初期化
		session.invalidate();

		return "formView.jsp";
	}

	/**
	 * 結果表示画面に遷移します。
	 * 
	 * @return 結果表示画面
	 */
	@Execute(validator = true, input = "formView.jsp")
	public String result() {

		// resultテーブルにおみくじコードが存在するか確認
		String birthday = String.valueOf(omikujiForm.birthday);
		int omikujiCode = resultService.getResult(birthday);

		// おみくじコードがない場合、おみくじコードを発行し、テーブルに登録する
		if (omikujiCode == 0) {
			// データベースからおみくじの数を取得し、ランダムにおみくじコードを発行する
			Random random = new Random();
			omikujiCode = random.nextInt(omikujiService.getCountOmikuji()) + 1;

			// 運勢結果を登録
			resultService.registerResult(omikujiCode, birthday);
		}

		// おみくじを引き、Beanに格納
		omikujiForm.omikuji = omikujiService.getOmikuji(omikujiCode);

		// 誕生日をセッションに登録
		session.setAttribute("birthday", birthday);

		return "resultView.jsp";
	}
}
