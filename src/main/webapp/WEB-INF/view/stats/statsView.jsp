<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html:html>
<head>
<meta charset="UTF-8">
<title>過去半年の統計表示画面</title>
</head>
<body>
	<h1>過去半年の運勢結果の割合</h1>

	<!-- 運勢の割合を表示 -->
	<c:forEach var="statsMap" items="${statsMapList}">
		<p>
			<c:out value="${statsMap.get('unseiname')}" />：
			<c:out value="${statsMap.get('ratio')}" />％
		</p>
	</c:forEach>

	<a href="javascript:history.back();">戻る</a>
	<s:link href="/">フォームに戻る</s:link>

</body>
</html:html>