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

	<!-- 住所一覧ラジオボックス -->
	<div id="radioButtonsContainer"></div>
	
	<!-- 完了ボタン -->
	<button id=done>選択</button>

	<script>

		var optionsObject;
		var zipcode = '';
		
		window.addEventListener('message', function(event) {

			// メインウィンドウのデータを取得
			optionsObject = event.data.options;

			// ラジオボタンを表示する場所を取得
			const radioButtonsContainer = document.getElementById('radioButtonsContainer');

			// 住所が複数ある分、ラジオボタンを生成
			for ( let optionText in optionsObject.results) {

				let address = '';

				if(optionsObject.results[optionText].zipcode) {
					zipcode = optionsObject.results[optionText].zipcode;
				}
				
				if(optionsObject.results[optionText].address != '以下に掲載がない場合') {
						address = optionsObject.results[optionText].address
				}


				let viewText = '〒'
								+ zipcode
								+' | '
								+ optionsObject.results[optionText].prefecture
								+ optionsObject.results[optionText].city
								+ address

				let addressText = zipcode + ',' + optionsObject.results[optionText].prefecture
									+ optionsObject.results[optionText].city
									+ address
						
				// ラジオボタンを生成
				let radioButton = createRadioButton('selectRadio', 'address', addressText, viewText);
				radioButtonsContainer.appendChild(radioButton);

				// 改行
				let line = document.createElement('hr');
				radioButtonsContainer.appendChild(line);
				
			}
			// データを受信後に読み込み
			onDataReceived();
		});
	</script>

	<!--  サブウィンドウで入力した住所をメインウィンドウに返す -->
	<script>
	
		function onDataReceived() {
			
			$('#done').click(function() {

				let checkValue = '';

				  // 選択されたoptionの値を取得
				  for (let i = 0; i < selectRadio.length; i++){
				    if (selectRadio.item(i).checked){
						checkValue = selectRadio.item(i).value;
				    }
				  }

				// 値を配列に変換
				let valueAry = checkValue.split(',');
				
				if(zipcode) {
					window.opener.$('#zipcode').val(valueAry[0]);
				}
				
				window.opener.$('#address').val(valueAry[1]);
				window.close(); 
				});
			}
	</script>
	
	
	
	<!-- ラジオボタンを作成する関数 -->
	<script>
		function createRadioButton(id, name, value, text) {
        		var label = document.createElement('label');
       	 	var input = document.createElement('input');
        		input.type = 'radio';
			input.id = id;
			input.name = name;
        		input.value = value;

        		label.appendChild(input);
        		label.appendChild(document.createTextNode(text));

        		return label;
   		 }
	</script>

</body>
</html>