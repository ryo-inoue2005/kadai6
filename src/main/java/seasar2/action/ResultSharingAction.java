package seasar2.action;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.util.ResponseUtil;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.Message;

import seasar2.common.ResultAddress;
import seasar2.entity.Omikujibox;
import seasar2.entity.Unseimaster;
import seasar2.entity.Zipcode;
import seasar2.form.ResultSharingForm;
import seasar2.service.CustomerInfoService;
import seasar2.service.OmikujiboxService;
import seasar2.service.ZipcodeService;

/**
 * ResultSharingActionクラス. <br>
 * ResultSharingActionクラスは、運勢結果共有画面を制御します。
 *
 * @author Ryo.inoue
 * @version 1.00
 */
public class ResultSharingAction {

	/** 個人情報などを保管するフォームクラスを表します */
	@Resource
	@ActionForm
	protected ResultSharingForm resultSharingForm;

	/** 郵便番号から住所をデータベースから検索するサービスを表します */
	@Resource
	protected ZipcodeService zipcodeService;

	/** 郵便で届ける為に個人情報をデータベースに保存するサービスを表します */
	@Resource
	protected CustomerInfoService customerInfoService;

	/** おみくじをデータベースから取得するサービスを表します */
	@Resource
	protected OmikujiboxService omikujiboxService;

	/** セッションを表します */
	@Resource
	protected HttpSession session;

	/**
	 * 結果連携画面に遷移します。
	 * 
	 * @return 結果連携画面に遷移
	 */
	@Execute(validator = false)
	public String index() {

		// 選択した連携方法をセッションに保管
		session.setAttribute("option", resultSharingForm.selectOption);

		return "resultSharingView.jsp";
	}

	/**
	 * 住所一覧サブウィンドウを表示させます。
	 * 
	 * @return 住所一覧サブウィンドウ
	 */
	@Execute(validator = false)
	public String selectAddressList() {
		return "addressListSubWindowView.jsp";
	}

	/**
	 * 顧客情報を登録します。
	 * 
	 * @return 結果連携画面
	 */
	@Execute(validator = true, input = "resultSharingView.jsp")
	public String sendResultByPost() {

		// 顧客情報を登録
		customerInfoService.insertCustomer(
				(Integer) session.getAttribute("omikujiCode"),
				resultSharingForm.lastName,
				resultSharingForm.firstName,
				resultSharingForm.zipcode,
				resultSharingForm.address,
				resultSharingForm.building);

		resultSharingForm.message = "郵送の受付が完了しました";

		return "resultSharingView.jsp";
	}

	/**
	 * 郵便番号から住所を取得し、JSONデータを返します。
	 * 
	 * @return JSONデータ
	 */
	@Execute(validator = false)
	public String zipCodeToAddress() throws JsonProcessingException {

		if (resultSharingForm.zipcode == null) {
			return null;
		}

		// 郵便番号から住所をデータベースから取得
		List<Zipcode> zipcodeList = zipcodeService.findAddressByZipcode(resultSharingForm.zipcode);
		ResultAddress resultAddress = new ResultAddress();

		// 住所を取得出来た場合：住所をセット
		if (zipcodeList != null) {
			for (Zipcode zipcodeTable : zipcodeList) {
				Map<String, String> resultMap = new HashMap<>();
				resultMap.put("address", zipcodeTable.address);
				resultMap.put("prefecture", zipcodeTable.prefecture);
				resultMap.put("city", zipcodeTable.city);
				resultAddress.getResults().add(resultMap);
			}
		} else {
			resultAddress.setResults(null);
			resultAddress.setMessage("住所が存在しません");
		}

		// JSONに変換する
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(resultAddress);

		// JSONを送信
		ResponseUtil.write(json, "application/json", "UTF-8");

		return null;
	}

	/**
	 * 住所から郵便番号を取得し、JSONデータを返します。
	 * 
	 * @return JSONデータ
	 */
	@Execute(validator = false)
	public String addressToZipcode() throws JsonProcessingException {

		String address = resultSharingForm.address;

		// 住所データが空なら即終了
		if (address == null) {
			return null;
		}

		String[] prefecture = { "都", "道", "府", "県" };
		int prefectureIndex = 0;

		// 都道府県の位置を検索
		for (String searchStr : prefecture) {
			prefectureIndex = address.indexOf(searchStr, 2);
			if (prefectureIndex == 2 || prefectureIndex == 3) {
				break;
			}
		}

		// 都道府県が検出されなかったか、4文字以上にあった場合は終了
		if (prefectureIndex == -1 || prefectureIndex > 3) {
			System.out.println("都道府県が検出されませんでした");
			return null;
		}

		String[] city = { "市", "区", "町", "村" };
		int cityIndex = 0;

		// 市区町村の位置を後ろから検索
		for (String searchStr : city) {
			cityIndex = address.lastIndexOf(searchStr);
			if (cityIndex != -1) {
				break;
			}
		}

		// 検索位置を指定
		int searchIndex = cityIndex + 4;

		// 入力された住所が「千葉県浦安市」など3文字増やして検索できない場合、元の長さに戻す
		if (address.length() < searchIndex) {
			searchIndex = address.length();
		}
		
		/*
		 * 表記揺れ吸収
		 */
		address = address.replaceAll("之", "☆");
		address = address.replaceAll("ノ", "☆");
		address = address.replaceAll("の", "☆");
		address = address.replaceAll("乃", "☆");
		
		address = address.replaceAll("ヵ", "★");
		address = address.replaceAll("ヶ", "★");
		address = address.replaceAll("か", "★");
		address = address.replaceAll("が", "★");
		address = address.replaceAll("ケ", "★");
		address = address.replaceAll("ガ", "★");
		

		// 住所から郵便番号を検索する
		List<Zipcode> zipcodeList = zipcodeService.findZipcodeByFulladdress(address.substring(0, searchIndex));
		

		/*
		 *  複数の郵便番号が取得した場合、1件になるまで検索文字数を増やす
		 *  検索文字が入力された文字数を超えたら終了させる
		 *  検索文字を増やして0件になった場合、前の文字に戻って複数の郵便番号を再取得する
		 */
		while (zipcodeList.size() > 1) {
			searchIndex++;
			if (address.length() < searchIndex) {
				searchIndex = address.length();
				zipcodeList = zipcodeService.findZipcodeByFulladdress(address.substring(0, searchIndex));
				break;
			}
			zipcodeList = zipcodeService.findZipcodeByFulladdress(address.substring(0, searchIndex));
			if (zipcodeList.size() == 0) {
				searchIndex--;
				zipcodeList = zipcodeService.findZipcodeByFulladdress(address.substring(0, searchIndex));
				break;
			}
		}

		// 郵便番号が0件だった場合、検索文字を1文字ずつ減らす
		while (zipcodeList.size() == 0) {
			searchIndex--;
			zipcodeList = zipcodeService.findZipcodeByFulladdress(address.substring(0, searchIndex));
		}

		// 送信するデータを加工
		ResultAddress resultAddress = new ResultAddress();
		for (Zipcode dto : zipcodeList) {
			Map<String, String> resultMap = new HashMap<>();
			resultMap.put("zipcode", dto.zipCode);
			resultMap.put("prefecture", dto.prefecture);
			resultMap.put("city", dto.city);
			resultMap.put("address", dto.address);
			resultAddress.getResults().add(resultMap);
		}

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(resultAddress);

		// JSONを送信
		ResponseUtil.write(json, "application/json", "UTF-8");

		return null;
	}

	/**
	 * 運勢結果をGmailで送信します。
	 * 認証方式はOAuth2.0を使用し、アクセストークンを取得後、Gmailの送信権限を与えています。
	 * 
	 * @return 結果連携画面に遷移
	 */
	@Execute(validator = true, input = "resultSharingView.jsp")
	public String sendResultByGmail() throws IOException {

		// JSON読み込みを行うインスタンス
		final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
		// OAuthクライアントID
		final String CREDENTIALS_FILE_PATH = "/credentials.json";
		// OAuthで要求する機能を指定(Gmailの送信機能のみを指定)
		final List<String> SCOPES = Collections.singletonList(GmailScopes.GMAIL_SEND);
		// アクセストークンの保存場所
		final String TOKENS_DIRECTORY_PATH = "tokens";

		try {
			final NetHttpTransport TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

			// プロジェクトのOAuth2.0クライアントID読込
			InputStream in = ResultSharingAction.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
			GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

			// 送信元メールアドレス所有者の承認を得てアクセストークン取得(初回時、任意のブラウザが立ち上がる)
			GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(TRANSPORT, JSON_FACTORY,
					clientSecrets, SCOPES)
							.setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
							.setAccessType("offline").setApprovalPrompt("force").build();

			// コールバックを別ポートに指定し、Googleサーバーに反応を返す
			// NOTE:Webアプリ経由だとコールバックを返せず、認証時にフリーズする為、別ポートを指定して返すようにしています
			LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
			Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");

			// gmailサービスをビルド
			Gmail service = new Gmail.Builder(TRANSPORT, JSON_FACTORY, credential)
					.setApplicationName("kadai6Project")
					.build();

			// おみくじコードを元に運勢結果を取得
			Unseimaster unsei = omikujiboxService.getOmikuji((Integer) session.getAttribute("omikujiCode"));
			Omikujibox omikuji = unsei.omikujiList.get(0);

			// メール作成
			String to = resultSharingForm.email;
			String subject = "運勢結果のお知らせ";
			String bodyText = "あなたの運勢は" + unsei.unseiName + "\r\n" +
					"願い事：" + omikuji.negaigoto + "\r\n" +
					"商い：" + omikuji.akinai + "\r\n" +
					"学問：" + omikuji.gakumon;
			MimeMessage mimeMessage = createEmail(to, null, subject, bodyText);

			// メール送信
			sendMessage(service, "me", mimeMessage);
			resultSharingForm.message = "運勢結果をメールで送信しました";

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "resultSharingView.jsp";
	}

	/**
	 * Create a MimeMessage using the parameters provided.
	 * 指定されたパラメータを使ってMimeMessageを作成する。
	 * 
	 * 引用元：https://developers.google.com/gmail/api/guides/sending
	 * 
	 * @param toEmailAddress   email address of the receiver 受信者のEメールアドレス
	 * @param fromEmailAddress email address of the sender, the mailbox account 送信者の電子メールアドレス
	 * @param subject          subject of the email メールの件名
	 * @param bodyText         body text of the email メール本文
	 * @return the MimeMessage to be used to send email メール送信に使用
	 * @throws MessagingException - if a wrongly formatted address is encountered. アドレスの書式が間違っている場合
	 */
	public static MimeMessage createEmail(String to, String from, String subject, String bodyText)
			throws MessagingException {

		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		MimeMessage email = new MimeMessage(session);

		email.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to));
		email.setSubject(subject);
		email.setText(bodyText);
		return email;
	}

	/**
	 * Create a message from an email.
	 * Eメールからメッセージを作成する。
	 * 
	 * 引用元：https://developers.google.com/gmail/api/guides/sending
	 *
	 * @param emailContent Email to be set to raw of message メッセージの原文に設定される電子メール
	 * @return a message containing a base64url encoded email base64urlエンコードされた電子メールを含むメッセージ
	 * @throws IOException        - if service account credentials file not found. サービスアカウントの資格情報ファイルがない場合
	 * @throws MessagingException - if a wrongly formatted address is encountered. アドレスの書式が間違っている場合
	 */
	public static Message createMessageWithEmail(MimeMessage emailContent) throws MessagingException, IOException {

		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		emailContent.writeTo(buffer);
		byte[] bytes = buffer.toByteArray();
		String encodedEmail = Base64.encodeBase64URLSafeString(bytes);
		Message message = new Message();
		message.setRaw(encodedEmail);
		return message;
	}

	/**
	 * Send an email from the user's mailbox to its recipient.
	 * ユーザーのメールボックスから受信者にメールを送信する。
	 * 
	 * 引用元：https://developers.google.com/gmail/api/guides/sending
	 *
	 * @param fromEmailAddress - Email address to appear in the from: header ヘッダーに表示されるメールアドレス
	 * @param toEmailAddress   - Email address of the recipient 受信者の電子メールアドレス
	 * @return the sent message, {@code null} otherwise. メッセージを送信する でなければ{@code null}となる。
	 * @throws MessagingException - if a wrongly formatted address is encountered. アドレスの書式が間違っている場合
	 * @throws IOException        - if service account credentials file not found. サービスアカウント認証ファイルがない場合
	 */
	public static Message sendMessage(Gmail service, String userId, MimeMessage emailContent)
			throws MessagingException, IOException {

		Message message = createMessageWithEmail(emailContent);
		message = service.users().messages().send(userId, message).execute();

		System.out.println("Message id: " + message.getId());
		System.out.println(message.toPrettyString());
		return message;
	}

}
