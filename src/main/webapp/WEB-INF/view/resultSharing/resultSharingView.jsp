<!DOCTYPE html>
<html lang="ja">
	<head>
    	<meta charset="UTF-8">
    <title>結果連携画面</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
</head>
<body>
	<h1>運勢結果を連携します</h1>

    <h2>結果連携方法を選んでください</h2>
    <s:form method="POST">
    	<html:select property="selectOption">
    	    <option value="selectPost">郵便で受け取る</option>
    	    
    	    <!-- メールが選択されたら固定する -->
        		<option value="selectMail"
        			<c:if test="${option == 'selectMail'}">
    				selected
    			</c:if>
        		>メールで受け取る</option>
        		
        		<p><s:submit property="index" value="選択" /></p>
    	</html:select>
    </s:form>
    
    <!-- エラーメッセージ -->
    <html:errors />
    
    <!-- 連携成功メッセージ -->
    <c:if test="${message != null}">
    	${f:h(message)}
    </c:if>
    	
    
    <!-- メールで受け取る場合のフォーム -->
    <!-- メールが選択されたら表示する -->
    <c:if test="${option == 'selectMail'}">
		<div id="mail">
    		<h2>メールで受け取る</h2>
    		<p>名前とメールアドレスを入力してください</p>
    		
    		<s:form method="POST">
        			<p>お名前</p>
    			<p>姓：<html:text property="lastName"/></p>
    			<p>名：<html:text property="firstName"/></p>
				<p>メールアドレス：<html:text property="email"/></p>
			
				<!-- validator対策 -->
				<html:hidden property="zipcode" value="123"/>
				<html:hidden property="prefecture" value="null"/>
				<html:hidden property="city" value="null"/>
				<html:hidden property="address" value="null"/>
			
				<p><s:submit property="sendResultByGmail" value="送信" /></p>
			</s:form>
		</div>
	</c:if>
    
    <!-- 郵送する場合のフォーム -->
    <!-- 郵送が選択されたら表示する -->
    <c:if test="${option == 'selectPost'}">
    	<div id="post">
    		<h2>郵便で受け取る</h2>
    		<h2>お住まい記載欄</h2>
    		<p>※郵便番号を入力すると住所が自動で挿入されます　例：4101432</p>
    		<p>※都道府県を選択するとサブウィンドウが出現します</p>
    		<p>※サブウィンドウで入力した住所で郵便番号を検索することが出来ます</p>
    		
    		<s:form method="POST" styleId="personalinfo">
    			<p>お名前</p>
    			<p>姓：<html:text property="lastName"/></p>
    			<p>名：<html:text property="firstName"/></p>
    			<p>郵便番号：<html:text property="zipcode" styleId="zipcode" maxlength="7"/></p>
    			
				<p>都道府県：<html:select property="prefecture" styleId="prefecture"></p>
  					<option value="" selected>都道府県</option>
					<option value="北海道">北海道</option>
  					<option value="青森県">青森県</option>
  					<option value="岩手県">岩手県</option>
					<option value="宮城県">宮城県</option>
					<option value="秋田県">秋田県</option>
					<option value="山形県">山形県</option>
					<option value="福島県">福島県</option>
					<option value="茨城県">茨城県</option>
					<option value="栃木県">栃木県</option>
					<option value="群馬県">群馬県</option>
					<option value="埼玉県">埼玉県</option>
					<option value="千葉県">千葉県</option>
					<option value="東京都">東京都</option>
					<option value="神奈川県">神奈川県</option>
					<option value="新潟県">新潟県</option>
					<option value="富山県">富山県</option>
					<option value="石川県">石川県</option>
					<option value="福井県">福井県</option>
					<option value="山梨県">山梨県</option>
					<option value="長野県">長野県</option>
					<option value="岐阜県">岐阜県</option>
					<option value="静岡県">静岡県</option>
					<option value="愛知県">愛知県</option>
					<option value="三重県">三重県</option>
					<option value="滋賀県">滋賀県</option>
					<option value="京都府">京都府</option>
					<option value="大阪府">大阪府</option>
					<option value="兵庫県">兵庫県</option>
					<option value="奈良県">奈良県</option>
					<option value="和歌山県">和歌山県</option>
					<option value="鳥取県">鳥取県</option>
					<option value="島根県">島根県</option>
					<option value="岡山県">岡山県</option>
					<option value="広島県">広島県</option>
					<option value="山口県">山口県</option>
					<option value="徳島県">徳島県</option>
					<option value="香川県">香川県</option>
					<option value="愛媛県">愛媛県</option>
					<option value="高知県">高知県</option>
					<option value="福岡県">福岡県</option>
					<option value="佐賀県">佐賀県</option>
					<option value="長崎県">長崎県</option>
					<option value="熊本県">熊本県</option>
					<option value="大分県">大分県</option>
					<option value="宮崎県">宮崎県</option>
					<option value="鹿児島県">鹿児島県</option>
					<option value="沖縄県">沖縄県</option>
				</html:select>
				<p>市区町村：<html:text property="city" styleId="city"/></p>
				<p>番地など：<html:text property="address" styleId="address"/></p>
				
				<!-- validator対策 -->
				<html:hidden property="email" value="exeample@gmail.com"/>
				
				<p><s:submit property="sendResultByPost" value="送信" /></p>
			</s:form>
		</div>
	</c:if>
	
	<a href="javascript:history.back();">戻る</a>
	<s:link href="/">フォームに戻る</s:link>
	
	<!-- 県が選択されたらサブウィンドウを開いて残りの住所を選択させる -->
	<script type="text/javascript">

	$('#prefecture').change(function() {
		window.open('/kadai6/resultSharing/findZipcodeByAddress?prefecture=' + $('#prefecture').val(), 
				"SubWindow", 'width=400,height=300');
	});
	</script>
	
	<!-- 郵便番号を入力後に自動で住所を取得します -->
    <script type="text/javascript">
	 $('#zipcode').change(function() {
        $.ajax({
            url: '/kadai6/resultSharing/zipCodeToAddress',
    	    type: 'POST',
    	    data: { zipcode: $('#zipcode').val() },
    	    dataType: 'json',
		})
		.done(function (data) {
			
			// 1つの通便番号で2つ以上住所を受け取った場合、サブウィンドウを開く
			if (data.results.length >= 2) {
				var subWindow = window.open('/kadai6/resultSharing/selectAddressList', 'obj_window', 'width=400,height=300');

				// サブウィンドウが読み込みを完了したら住所データをサブウィンドウに送信
				subWindow.onload = function() {
					  subWindow.postMessage({ messageType: 'options', options: data }, '*');
				};
				
			} else if (data.results != 0) {
				// フォーム入力欄に値をセット
				let result = data.results[0];
				$('#prefecture').val(result.prefecture);
				$('#city').val(result.city);
				$('#address').val(result.address);
			} else {
				alert('住所が見つかりませんでした');
			}
		})
		.fail(function() {
			alert('サーバーに接続できませんでした');
		});
	});
    	</script>
</body>
</html>