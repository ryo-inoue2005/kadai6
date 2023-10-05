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

	@Execute(validator = true, input = "resultSharingView.jsp")
	public String sendResultByPost() {

		// 顧客情報を登録
		customerInfoService.insertCustomer(
				(Integer) session.getAttribute("omikujiCode"),
				resultSharingForm.lastName,
				resultSharingForm.firstName,
				resultSharingForm.zipcode,
				resultSharingForm.prefecture,
				resultSharingForm.city,
				resultSharingForm.address);

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
		ResultAddress location = new ResultAddress();

		// 住所を取得出来た場合：住所をセット
		if (zipcodeList != null) {
			for (Zipcode zipcodeTable : zipcodeList) {
				Map<String, String> resultMap = new HashMap<>();
				resultMap.put("address", zipcodeTable.address);
				resultMap.put("prefecture", zipcodeTable.prefecture);
				resultMap.put("city", zipcodeTable.city);
				location.getResults().add(resultMap);
			}
		} else {
			location.setResults(null);
			location.setMessage("住所が存在しません");
		}

		// JSONに変換する
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(location);

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

		String prefecture = resultSharingForm.prefecture;
		String city = resultSharingForm.city;
		String address = resultSharingForm.address;

		if (prefecture.isEmpty() || city.isEmpty() || address.isEmpty()) {
			return null;
		}

		Zipcode zipcodeTable = zipcodeService.findZipcodeByAddress(prefecture, city, address);

		Map<String, String> resultMap = new HashMap<>();
		resultMap.put("zipcode", zipcodeTable.zipCode);

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(resultMap);

		// JSONを送信
		ResponseUtil.write(json, "application/json", "UTF-8");

		return null;

	}

	/**
	 * 住所から郵便番号を検索します。
	 * 
	 * @return 住所検索サブウィンドウ
	 */
	@Execute(validator = false)
	public String findZipcodeByAddress() {

		if (resultSharingForm.prefecture != null) {
			// セッション初期化
			session.removeAttribute("sessionPrefecture");
			session.removeAttribute("sessionCity");
			session.removeAttribute("sessionAddress");

			resultSharingForm.zipcodeDtoList = zipcodeService.findCityByPrefecture(resultSharingForm.prefecture);
			session.setAttribute("sessionPrefecture", resultSharingForm.prefecture);
		}

		if (resultSharingForm.city != null) {
			resultSharingForm.zipcodeDtoList = zipcodeService.findAddressByCity(
					(String) session.getAttribute("sessionPrefecture"),
					resultSharingForm.city);
			session.setAttribute("sessionCity", resultSharingForm.city);
		}

		if (resultSharingForm.address != null) {
			session.setAttribute("sessionAddress", resultSharingForm.address);
		}

		return "addressSelectionSubWindowView.jsp";
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
