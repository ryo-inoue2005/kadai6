<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>選択してください</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
</head>
<body>

	<!-- 住所一覧プルダウンリスト -->
	<select id="selectBox"></select>
	
	<!-- 完了ボタン -->
	<button id=done>完了</button>

	<script type="text/javascript">
	
		var optionsObject;

		window.addEventListener('message', function(event) {

			// メインウィンドウのデータを取得
			optionsObject = event.data.options;
			
			const selectBox = document.getElementById('selectBox');

			// 住所が複数ある分、option要素を生成
			for ( let optionText in optionsObject.results) {

				const select = document.getElementById("select");
				const option = document.createElement("option");

				var location = optionsObject.results[optionText].prefecture
						+ " " + optionsObject.results[optionText].city + " "
						+ optionsObject.results[optionText].address

				option.text = location;
				option.value = optionText;
				selectBox.appendChild(option);

			}
			// データを受信後に読み込み
			onDataReceived();
		});
	</script>

	<script type="text/javascript">

		// サブウィンドウで入力した住所をメインウィンドウに返す
		function onDataReceived() {
			
			$('#done').click(function() {
				let selectLocaton = $('#selectBox').val()
				window.opener.$('#prefecture').val(optionsObject.results[selectLocaton].prefecture);
				window.opener.$('#city').val(optionsObject.results[selectLocaton].city);
				window.opener.$('#address').val(optionsObject.results[selectLocaton].address);
				window.close();
				});
			}
	</script>

</body>
</html>