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
        		<option value="selectMail">メールで受け取る</option>
        		<option value="selectPost">郵便で受け取る</option>
        		<p><s:submit property="index" value="選択" /></p>
    	</html:select>
    </s:form>
    
    <html:errors />
    
    <!-- 連携成功メッセージ -->
    <c:if test="${message != null}">
    	${f:h(message)}
    </c:if>
    	
    
    <!-- メールで受け取る場合のフォーム -->
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
    <c:if test="${option == 'selectPost'}">
    	<div id="post">
    		<h2>郵便で受け取る</h2>
    		<h2>お住まい記載欄</h2>
    		<p>※郵便番号を入力すると住所が自動で挿入されます　例：4101432</p>
    		
    		<s:form method="POST">
    			<p>お名前</p>
    			<p>姓：<html:text property="lastName"/></p>
    			<p>名：<html:text property="firstName"/></p>
    			<p>郵便番号：<html:text property="zipcode" styleId="zipcode" maxlength="7"/></p>
				<p>都道府県：<html:text property="prefecture" styleId="prefecture"/></p>
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
	
	<!-- 郵便番号から住所を取得するAjax -->
	<!-- 郵便番号を入力後に自動で住所を取得します -->
    <script type="text/javascript">
    $('#zipcode').change(function() {
        $.ajax({
            url: "/kadai6/resultSharing/zipCodeToAddress",
    	    type: "POST",
    	    data: { zipcode: $("#zipcode").val() },
    	    dataType: "json",
		})
		.done(function (data) {
			if (data.results) {
				// 住所情報を取得
				let result = data.results[0];
				// フォーム入力欄に値をセット
				$("#prefecture").val(result.prefecture);
				$("#city").val(result.city);
				$("#address").val(result.address);
			} else {
				alert("住所が見つかりませんでした");
			}
		})
		.fail(function() {
			alert('サーバーに接続できませんでした');
		});
	});
    </script>

</body>
</html>