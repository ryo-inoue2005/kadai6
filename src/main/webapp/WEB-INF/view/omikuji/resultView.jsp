<%@page import="javax.xml.crypto.Data"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html:html>
<head>
<meta charset="UTF-8">
<title>結果表示画面</title>
</head>
<body>

	<h1>${f:h(birthday)}運勢結果</h1>
	
	<p>${f:h(omikuji.disp)}</p>
	<p>${f:h(omikuji.negaigoto)}</p>
	<p>${f:h(omikuji.akinai)}</p>
	<p>${f:h(omikuji.gakumon)}</p>

	<s:link href="/">フォームに戻る</s:link>
	<s:link href="/pastList">過去のおみくじ結果を見る</s:link>
	<s:link href="/stats">統計を見る</s:link>

</body>
</html:html>