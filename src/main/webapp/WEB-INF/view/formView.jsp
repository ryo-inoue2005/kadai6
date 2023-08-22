<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>誕生日入力画面</title>
</head>
<body>

	<h1>生年月日で今日の運勢を占います</h1>
	<h2>yyyyMMddの形式で生年月日を入力してください</h2>

	<html:errors />

	<s:form method="POST">
		<html:text property="birthday" />
		<s:submit property="result" value="送信" />
	</s:form>

</body>
</html>