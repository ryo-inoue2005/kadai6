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
    		
    		<s:form method="POST" styleId="personalinfo">
    			<p>お名前</p>
    			<p>姓：<html:text property="lastName"/></p>
    			<p>名：<html:text property="firstName"/></p>
    			<p>郵便番号：<html:text property="zipcode" styleId="zipcode" maxlength="7"/></p>
    			<button type="button" id="addressConvertButton">〒↔住所</button>
				<p>ご住所：<html:text property="address" styleId="address"/></p>
				<p>建物名：<html:text property="building" styleId="building"/></p>
				
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
    <script>
	 $('#zipcode').change(function() {
			if($('#zipcode').val().length) {
				zipcodeToAddress();
			}
	});
    </script>
    
    <!-- 〒↔住所ボタン制御 -->
    <script>
	$('#addressConvertButton').on('click', function() {
		
		if($('#zipcode').val() && $('#address').val()) {
			alert("住所と郵便番号は何方か一つだけ入力してください");
			return;
		} 
		
		
		if($('#zipcode').val().length) {
			zipcodeToAddress();
		}

		if($('#address').val().length) {
			addressToZipcode();
		}
		
	});
    </script>
    	
    	
    <!-- 郵便番号から住所変換処理 -->
    <script>
    	function zipcodeToAddress() {

        		// 7文字未満なら即終了
			if ($('#zipcode').val().length >= 1 && $('#zipcode').val().length != 7) {
				alert('郵便番号は7文字入力してください')
				return;
			}
			
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
    				$('#address').val(result.prefecture + result.city + result.address);
    			} else {
    				alert('結果が見つかりません…');
    			}
    		})
    		.fail(function() {
    			alert('サーバーに接続できませんでした');
    		});
        	}
    </script>
    
    
    <!-- 住所から郵便番号変換処理 -->
    <script>
	function addressToZipcode() {
		// 6文字未満なら即終了
		if($('#address').val().length < 6) {
			alert('住所は6文字以上入力してください')
			return;
		}
	$.ajax({
        url: '/kadai6/resultSharing/addressToZipcode',
    	    type: 'POST',
    	    data: {
	    	    	address: $('#address').val()
	    	   },
    	    dataType: "json"
		})
		.done(function (data) {
			if (data.results.length >= 2) {
				var subWindow = window.open('/kadai6/resultSharing/selectAddressList', 'obj_window', 'width=400,height=300');

				// サブウィンドウが読み込みを完了したら住所データをサブウィンドウに送信
				subWindow.onload = function() {
					  subWindow.postMessage({ messageType: 'options', options: data }, '*');
				};
			} else if (data.results != 0) {
				// フォーム入力欄に値をセット
				let result = data.results[0];
				$('#zipcode').val(result.zipcode);
			} else {
				alert('結果が見つかりません…');
			}
		})
		.fail(function() {
			alert('サーバーに接続できませんでした');
		});
	}
	</script>
</body>
</html>