<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html:html>
<head>
<meta charset="UTF-8">
<title>過去半年の結果リスト画面</title>
</head>
<body>

	<h1>${f:h(birthday)}の過去半年の結果</h1>

	<table border="5">
		<tr>
			<th>占った日</th>
			<th>運勢</th>
			<th>願い事</th>
			<th>商い</th>
			<th>学問</th>
		</tr>

		<c:forEach var="omikuji" items="${omikujiList}">
			<tr>
				<td><c:out value="${omikuji.createDate}" /></td>
				<td><c:out value="${omikuji.unsei}" /></td>
				<td><c:out value="${omikuji.negaigoto}" /></td>
				<td><c:out value="${omikuji.akinai}" /></td>
				<td><c:out value="${omikuji.gakumon}" /></td>
			</tr>
		</c:forEach>
	</table>

	<a href="javascript:history.back();">戻る</a>
	<s:link href="/">フォームに戻る</s:link>
</body>
</html:html>