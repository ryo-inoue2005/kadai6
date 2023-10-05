<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>選択してください</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
</head>
<body>

	<ul>
		<li>都道府県：<span id="prefecture"><c:out value="${sessionPrefecture}"/></span></li>
		<li>市区町村：<span id="city"><c:out value="${sessionCity}"/></span></li>
		<li>番地等：<span id="address"><c:out value="${sessionAddress}"/></span></li>
	</ul>
	<hr>
	
	<c:if test="${address != null}" >
		<button id="done">住所から郵便番号を検索する</button>
	</c:if>
	<button id="close">閉じる</button>

	<c:forEach var="list" items="${zipcodeDtoList}">
		<p>
			<a href="/kadai6/resultSharing/findZipcodeByAddress?city=${list.city}"><c:out value="${list.city}" /></a>
		</p>
	</c:forEach>
	
	<c:forEach var="list" items="${zipcodeDtoList}">
		<p>
			<a href="/kadai6/resultSharing/findZipcodeByAddress?address=${list.address}" id="hoge"><c:out value="${list.address}" /></a>
		</p>
	</c:forEach>
	
	
	<!-- 各種ボタン処理 -->
	<script type="text/javascript">

	// 完了ボタンが押下時、郵便番号を取得し、メインウィンドウに市区町村とその他住所を自動入力
    $('#done').click(function() {
        	searchZipcode();
        window.opener.$('#city').val($('#city').text());
        window.opener.$('#address').val($('#address').text());
    });

	// 閉じるボタン押下時、サブウィンドウを閉じる
    $('#close').click(function() {
        window.opener.$('#city').val($('#city').text());
        window.close();
    });

	</script>
	
	
	
	<script type="text/javascript">

	// 住所から郵便番号を取得する
	function searchZipcode() {
	$.ajax({
        url: '/kadai6/resultSharing/addressToZipcode',
    	    type: 'POST',
    	    data: {
	    	    	prefecture: $('#prefecture').text(),
	    	    	city: $('#city').text(),
	    	    	address: $('#address').text() 
	    	   },
    	    dataType: "json"
		})
		.done(function (data) {
			if (data.zipcode) {
				 window.opener.$('#zipcode').val(data.zipcode);
			} else {
				alert("郵便番号が見つかりませんでした");
			}
		})
		.fail(function() {
			alert('サーバーに接続できませんでした');
		});
	}
	</script>

</body>
</html>